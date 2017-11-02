package com.baixin.ees.quartz;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baixin.ees.web.dao.mapper.AcctErrorMapper;
import com.baixin.ees.web.dao.model.AcctError;
import com.baixin.ees.web.dao.model.AcctErrorExample;
import com.baixin.ees.web.dao.model.AcctErrorResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class AlarmQuartzJob implements Job {
	private  final String KEY_HOST = "host";
	private  final String KEY_CHECKER = "checker";
	private  final String KEY_ALARMTIME = "alarmTime";
	private  final String KEY_CLASSNAME = "className";
	private  final String KEY_OBJECT = "object";
	private  final String KEY_METRICS = "metrics";
	private  final String KEY_ALARMLEVEL = "alarmLevel";
	private  final String KEY_VALUE = "value";
	private  final String KEY_THRESHOLD = "threshold";
	private  final String KEY_NOTE = "note";
	private  final String KEY_URL = "url";
	private  final String ERRORMESSAGE_KEY_CHANNEL_SEQ_NO="CHANNEL_SEQ_NO";
	private  final String ERRORMESSAGE_KEY_SYS_SEQ_NO="SYS_SEQ_NO";
	private  final String ERRORMESSAGE_KEY_INFER_REASON="INFER_REASON";
	private  final String ERRORMESSAGE_KEY_CREATE_TIME="CREATE_TIME";
	private  final String ERRORMESSAGE_KEY_STATUS="STATUS";

	private static final Logger logger = LoggerFactory.getLogger(AlarmQuartzJob.class);

	@Autowired
	private SqlSession eesSqlSession;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		AcctErrorMapper mapper = eesSqlSession.getMapper(AcctErrorMapper.class);
		AcctErrorExample example = new AcctErrorExample();
		List<AcctErrorResult> sysErrorAcct = mapper.selectByExample(example);
		if (sysErrorAcct.size() > 0) {
			for (int i = 0; i < sysErrorAcct.size(); i++) {
				if (null !=sysErrorAcct.get(i).getStatus() && "0".equals(sysErrorAcct.get(i).getStatus())) {
					AcctError acct=sysErrorAcct.get(i);
					logger.info("处理一笔错误账单，具体信息为：");
					JSONObject POSTParam = new JSONObject();
					POSTParam.put(KEY_HOST, "null");
					POSTParam.put(KEY_CHECKER, acct.getOrgCode());
					POSTParam.put(KEY_ALARMTIME, acct.getReportTime());
					POSTParam.put(KEY_CLASSNAME,acct.getProofType() );
					POSTParam.put(KEY_OBJECT, "账务监控");
					POSTParam.put(KEY_METRICS, "null");
					POSTParam.put(KEY_ALARMLEVEL, "major");
					POSTParam.put(KEY_VALUE,"null");
					POSTParam.put(KEY_THRESHOLD, "0");
					JSONObject errorMessage = new JSONObject();
					errorMessage.put(ERRORMESSAGE_KEY_CHANNEL_SEQ_NO, acct.getChannelSeqNo());
					errorMessage.put(ERRORMESSAGE_KEY_SYS_SEQ_NO, acct.getSysSeqNo());
					errorMessage.put(ERRORMESSAGE_KEY_INFER_REASON, acct.getInferReason());
					errorMessage.put(ERRORMESSAGE_KEY_CREATE_TIME, acct.getCreateTime());
					errorMessage.put(ERRORMESSAGE_KEY_STATUS, acct.getStatus());
					POSTParam.put(KEY_NOTE, errorMessage.toString());
					POSTParam.put(KEY_URL, "null");
					try {
						String message = sendPost("http://10.3.2.39/portal/api/warning/extern/third_party/add.do", POSTParam.toString());
						logger.info("错误账单信息发送到处理系统，返回数据为："+message);
						@SuppressWarnings("rawtypes")
						Map map = (Map) JSON.parse(message);
						String code = (String) map.get("code");
						if (StringUtils.isNotBlank(code)) {
							if ("0".equals(code)) {
								logger.info("错误账单已成功提交系统处理");
								String id = acct.getAcctErrId();
								AcctErrorExample example1 = new AcctErrorExample();
								AcctErrorMapper mapper1 = eesSqlSession.getMapper(AcctErrorMapper.class);
								AcctErrorExample.Criteria criteria = example1.createCriteria();
								criteria.andAcctErrIdEqualTo(id);
								acct.setStatus("1");
								int num = mapper1.updateByExample(acct, example1);
								if (num==1) {
									logger.info("已成功将错误账单数据库STATUS状态由0(刚创建)更改为1(已报警)");
								}else {
									logger.info("未能成功将错误账单数据库STATUS状态由0(刚创建)更改为1(已报警)");
								}
							}else {
								logger.info("错误账单提交系统处理失败！");
							}
						}
					}catch(Exception e){
						logger.error("程序发生错误"+e.getMessage());
					}
				}
			}
		}
	}
	public static String sendPost(String url, String param) {
		PrintWriter out = null;
		BufferedReader in = null;
		String result = "";
		try {
			URL realUrl = new URL(url);
			URLConnection conn = realUrl.openConnection();
			conn.setRequestProperty("content-type", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			out = new PrintWriter(conn.getOutputStream());
			out.print(param);
			out.flush();
			in = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}


		return result;
	}


}


