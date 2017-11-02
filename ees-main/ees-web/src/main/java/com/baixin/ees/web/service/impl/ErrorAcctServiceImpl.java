package com.baixin.ees.web.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.mapper.SysErrorAcctMapper;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysErrorAcct;
import com.baixin.ees.web.dao.model.SysErrorAcctExample;
import com.baixin.ees.web.dao.model.SysErrorAcctExample.Criteria;
import com.baixin.ees.web.dao.model.SysErrorAcctResult;
import com.baixin.ees.web.service.ErrorAcctService;
@Service
public class ErrorAcctServiceImpl implements ErrorAcctService {
	
	@Autowired
	private SqlSession eesSqlSession;
	
	@Override
	public Page<SysErrorAcctResult> selectListResult(SysErrorAcctResult acct) throws MyException {
		/*Page<SysErrorAcctResult> page = new Page<SysErrorAcctResult>();
		SysErrorAcctJoinMapper mapper = eesSqlSession.getMapper(SysErrorAcctJoinMapper.class);
	    List<SysErrorAcctResult> selectListResult = mapper.selectListResult(acct);
		page.setData(selectListResult);
		page.setDraw(acct.getDraw());
		int countListResult = mapper.countListResult(acct);
		page.setRecordsFiltered(countListResult);
		page.setRecordsTotal(countListResult);
		return page;*/
		
		Page<SysErrorAcctResult> page = null;
		try {
			SysErrorAcctMapper mapper = eesSqlSession.getMapper(SysErrorAcctMapper.class);
			SysErrorAcctExample example = new SysErrorAcctExample();
			Criteria criteria = example.createCriteria();
			if(StringUtils.isNotBlank(acct.getChannelSeqNo())){
				criteria.andChannelSeqNoLike("%" + acct.getChannelSeqNo() +"%");
			}
			if(StringUtils.isNotBlank(acct.getSysSeqNo())){
				criteria.andSysSeqNoLike("%" + acct.getSysSeqNo() +"%");
			}
			example.setLimitStart(acct.getStart());
			example.setLimitEnd(acct.getLength());
			List<SysErrorAcctResult> jobList = mapper.selectByExample(example);
			int countNum = mapper.countByExample(example);
			page = new Page<SysErrorAcctResult>(acct.getDraw(), countNum, countNum, jobList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(500,"查询错账列表时发生未知异常");
		}
		return page;
		
	}

	@Override
	public ResultMessage update(SysErrorAcct acct) {
		try {
			eesSqlSession.getMapper(SysErrorAcctMapper.class).updateByPrimaryKeySelective(acct);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}
	
	public Date StrToDate(String str) { 
	   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	   Date date = null; 
	    try {
			date = format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
	   return date; 
	}
}
