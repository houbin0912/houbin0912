package com.baixin.ees.quartz;

import com.baixin.ees.Constants;
import com.baixin.ees.util.PropertyUtil;
import com.baixin.ees.web.dao.mapper.AcctErrorMapper;
import com.baixin.ees.web.dao.mapper.SysOrgMapper;
import com.baixin.ees.web.dao.mapper.SysUserMapper;
import com.baixin.ees.web.dao.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class AlarmQuartzJobForMail implements Job {
	private static final Logger logger = LoggerFactory.getLogger(AlarmQuartzJob.class);
	@Autowired
	private   SqlSession eesSqlSession;

	private static AlarmQuartzJobForMail alarmQuartzJobForMail=new AlarmQuartzJobForMail();

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String myEmailAccount = PropertyUtil.getPropertyValue(Constants.PropertiesFileName.MAIL_PROP,"myEmailAccount");
		String myEmailPassword = PropertyUtil.getPropertyValue(Constants.PropertiesFileName.MAIL_PROP,"myEmailPassword");
		String smtpPort = PropertyUtil.getPropertyValue(Constants.PropertiesFileName.MAIL_PROP,"smtpPort");
		String myEmailSMTPHost= PropertyUtil.getPropertyValue(Constants.PropertiesFileName.MAIL_PROP,"myEmailSMTPHost");
		// 参数配置
		Properties props = new Properties();
		// 使用的协议（JavaMail规范要求）
		props.setProperty("mail.transport.protocol", "smtp");
		// 发件人的邮箱的 SMTP 服务器地址
		props.setProperty("mail.smtp.host", myEmailSMTPHost);
		props.setProperty("mail.smtp.auth", "true");

		props.setProperty("mail.smtp.port", smtpPort);
		props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.setProperty("mail.smtp.socketFactory.fallback", "false");
		props.setProperty("mail.smtp.socketFactory.port", smtpPort);

		Session session = Session.getDefaultInstance(props);

		AcctErrorMapper mapper = eesSqlSession.getMapper(AcctErrorMapper.class);
		AcctErrorExample example = new AcctErrorExample();
		List<AcctErrorResult> sysErrorAcct = mapper.selectByExample(example);
		if (sysErrorAcct.size() > 0) {
			for (int i = 0; i < sysErrorAcct.size(); i++) {
				if (null != sysErrorAcct.get(i).getStatus() && "0".equals(sysErrorAcct.get(i).getStatus())) {
					AcctError acct = sysErrorAcct.get(i);
					String orgCode=acct.getOrgCode();
					logger.info("处理一笔错误账单,时间："+ new Date());
					try {
						MimeMessage message = alarmQuartzJobForMail.createMimeMessage(session, myEmailAccount,acct);

						Transport transport = session.getTransport();

						final DeliveredStateFuture future = new DeliveredStateFuture();
						transport.addTransportListener(new TransportListener() {
							public void messageDelivered(TransportEvent arg0) {
								future.setState(DeliveredState.MESSAGE_DELIVERED);
							}
							public void messageNotDelivered(TransportEvent arg0) {
								future.setState(DeliveredState.MESSAGE_NOT_DELIVERED);
							}

							public void messagePartiallyDelivered(TransportEvent arg0) {
								future.setState(DeliveredState.MESSAGE_PARTIALLY_DELIVERED);
							}
						});

						transport.connect(myEmailAccount, myEmailPassword);

						transport.sendMessage(message, alarmQuartzJobForMail.Address(orgCode));

						future.waitForReady();
						transport.close();

						DeliveredState state = future.getState();

						logger.info("错误账单信息发送到邮箱系统处理，返回数据为：" + state.toString());

						if (StringUtils.isNotBlank(state.toString())) {
							if (state == DeliveredState.valueOf("MESSAGE_DELIVERED")||state==DeliveredState.valueOf("MESSAGE_PARTIALLY_DELIVERED")) {
								logger.info("错误账单已成功发往邮件系统处理");
								String id = acct.getAcctErrId();
								AcctErrorExample example1 = new AcctErrorExample();
								AcctErrorMapper mapper1 = eesSqlSession.getMapper(AcctErrorMapper.class);
								AcctErrorExample.Criteria criteria = example1.createCriteria();
								criteria.andAcctErrIdEqualTo(id);
								acct.setStatus("1");
								int num = mapper1.updateByExample(acct, example1);
								if (num == 1) {
									logger.info("已成功将错误账单数据库STATUS状态由0(刚创建)更改为1(已报警)");
								} else {
									logger.info("未能成功将错误账单数据库STATUS状态由0(刚创建)更改为1(已报警)");
								}
							} else if(state == DeliveredState.valueOf("MESSAGE_NOT_DELIVERED")){
								logger.info("错误账单提交邮件系统处理失败！");
							}
							else {
								logger.info("错误账单发送到邮件系统处理失败！");
							}
						}

					} catch (Exception e) {
						logger.error("程序发生错误" + e.getMessage());
					}
				}
			}
		}

	}

	public  MimeMessage createMimeMessage(Session session, String sendMail,AcctError acct) throws Exception {

		MimeMessage message = new MimeMessage(session);

		message.setFrom(new InternetAddress(sendMail, "百信鹰眼系统", "UTF-8"));

		message.setSubject("错账系统消息", "UTF-8");

		message.setContent("错账信息:<br/>"+"全局交易流水号:"+acct.getChannelSeqNo()+"<br/>交易的子交易序号:"+acct.getSysSeqNo()+"<br/>对账类型（端到端0，系统内部分布式1）:"
				+acct.getProofType()+"<br/>账务所属系统编码:"+acct.getOrgCode()+"<br/>对账给出的推断错账原因:"+acct.getInferReason()+"<br/>错账被确认时间:"+acct.getCreateTime()
				+"<br/>错账状态标识(0：刚创建；1：已报警；2：已处理并反馈结果):"+acct.getStatus(), "text/html;charset=UTF-8");

		message.setSentDate(new Date());

		message.saveChanges();

		return message;
	}

	private enum DeliveredState {
		INITIAL, MESSAGE_DELIVERED, MESSAGE_NOT_DELIVERED, MESSAGE_PARTIALLY_DELIVERED;
	}

	private static class  DeliveredStateFuture {
		private DeliveredState state = DeliveredState.INITIAL;
		synchronized void waitForReady() throws InterruptedException {
			if (state == DeliveredState.INITIAL) {
				wait();
			}
		}
		synchronized DeliveredState getState() {
			return state;
		}

		synchronized void setState(DeliveredState newState) {
			state = newState;
			notifyAll();
		}
	}
	public  InternetAddress[]  Address(String orgCode){
		InternetAddress[] address=null;
		List<String> receiveMailAccount = new ArrayList<>();
		SysUserMapper mapper = eesSqlSession.getMapper(SysUserMapper.class);
		SysUserExample example = new SysUserExample();
		List<SysUser> sysUsers =mapper.selectByExample(example);
		if (sysUsers.size()>0){
			for (int i=0; i< sysUsers.size();i++){
				if (null !=sysUsers.get(i).getReceiveEmail() && "1".equals(sysUsers.get(i).getReceiveEmail())){
					SysUser sysUser=sysUsers.get(i);
					SysOrgMapper orgMapper = eesSqlSession.getMapper(SysOrgMapper.class);
					if (null != (orgMapper.selectByPrimaryKey(sysUser.getOrgId())).getOrgCode()&&(orgMapper.selectByPrimaryKey(sysUser.getOrgId()).getOrgCode()).equals(orgCode)){
						receiveMailAccount.add(sysUser.getEmail());
					}

				}
			}
		}
		try {
			List<InternetAddress> list = new ArrayList<InternetAddress>();
			int size=receiveMailAccount.size();
			String[] arr=(String[])receiveMailAccount.toArray(new String[size]);
			for(int i=0;i<arr.length;i++){
				list.add(new InternetAddress(arr[i]));
			}
			address =(InternetAddress[])list.toArray(new InternetAddress[list.size()]);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return address;
	}


}
