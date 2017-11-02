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
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.mapper.AcctErrorMapper;
import com.baixin.ees.web.dao.model.AcctError;
import com.baixin.ees.web.dao.model.AcctErrorExample;
import com.baixin.ees.web.dao.model.AcctErrorExample.Criteria;
import com.baixin.ees.web.dao.model.AcctErrorResult;
import com.baixin.ees.web.service.AcctErrorService;
@Service
public class AcctErrorServiceImpl implements AcctErrorService {
	
	@Autowired
	private SqlSession eesSqlSession;
	
	@Override
	public Page<AcctErrorResult> selectListResult(AcctErrorResult acct) throws MyException {
		/*Page<SysErrorAcctResult> page = new Page<SysErrorAcctResult>();
		SysErrorAcctJoinMapper mapper = eesSqlSession.getMapper(SysErrorAcctJoinMapper.class);
	    List<SysErrorAcctResult> selectListResult = mapper.selectListResult(acct);
		page.setData(selectListResult);
		page.setDraw(acct.getDraw());
		int countListResult = mapper.countListResult(acct);
		page.setRecordsFiltered(countListResult);
		page.setRecordsTotal(countListResult);
		return page;*/
		
		Page<AcctErrorResult> page = null;
		try {
			AcctErrorMapper mapper = eesSqlSession.getMapper(AcctErrorMapper.class);
			AcctErrorExample example = new AcctErrorExample();
			Criteria criteria = example.createCriteria();
			if(StringUtils.isNotBlank(acct.getChannelSeqNo())){
				criteria.andChannelSeqNoLike("%" + acct.getChannelSeqNo() +"%");
			}
			if(StringUtils.isNotBlank(acct.getSysSeqNo())){
				criteria.andSysSeqNoLike("%" + acct.getSysSeqNo() +"%");
			}
			example.setLimitStart(acct.getStart());
			example.setLimitEnd(acct.getLength());
			List<AcctErrorResult> jobList =  mapper.selectByExample(example);
			int countNum = mapper.countByExample(example);
			page = new Page<AcctErrorResult>(acct.getDraw(), countNum, countNum, jobList);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(500,"查询错账列表时发生未知异常");
		}
		return page;
		
	}

	@Override
	public ResultMessage update(AcctError acct) {
		try {
			eesSqlSession.getMapper(AcctErrorMapper.class).updateByPrimaryKeySelective(acct);
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
