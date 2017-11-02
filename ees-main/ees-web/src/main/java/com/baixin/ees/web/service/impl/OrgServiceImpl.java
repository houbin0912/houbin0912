package com.baixin.ees.web.service.impl;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.mapper.SysOrgJoinMapper;
import com.baixin.ees.web.dao.mapper.SysOrgMapper;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysOrg;
import com.baixin.ees.web.dao.model.SysOrgExample;
import com.baixin.ees.web.dao.model.SysOrgExample.Criteria;
import com.baixin.ees.web.service.OrgService;

@Service
public class OrgServiceImpl implements OrgService{

	@Autowired
	private SqlSession eesSqlSession;
	
	@Override
	public ResultMessage add(SysOrg sysOrg) {
		try {
			SysOrgMapper mapper = eesSqlSession.getMapper(SysOrgMapper.class);
			sysOrg.setCreatedate(new Date());
			sysOrg.setLastupdatetime(new Date());
			mapper.insert(sysOrg);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage update(SysOrg sysOrg) {
		try {
			SysOrgMapper mapper = eesSqlSession.getMapper(SysOrgMapper.class);
			sysOrg.setLastupdatetime(new Date());
			mapper.updateByPrimaryKeySelective(sysOrg);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}
	
	@Override
	public ResultMessage delete(SysOrg sysOrg) {
		try {
			eesSqlSession.getMapper(SysOrgMapper.class).deleteByPrimaryKey(sysOrg.getOrgId()); 
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage deleteListById(String orgIds) {
		try {
			SysOrgJoinMapper mapper = eesSqlSession.getMapper(SysOrgJoinMapper.class);
			if(StringUtils.isNotBlank(orgIds)) {
				mapper.deleteByIds(orgIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public Page<SysOrg> queryListByOrg(SysOrg sysOrg) throws MyException {
		Page<SysOrg> page = null;
		try {
			page = new Page<SysOrg>();
			SysOrgMapper mapper = eesSqlSession.getMapper(SysOrgMapper.class);
			SysOrgExample example = new SysOrgExample();
			Criteria criteria = example.createCriteria();
			if(StringUtils.isNotBlank(sysOrg.getOrgName())){
				criteria.andOrgNameLike("%"+sysOrg.getOrgName()+"%");
			}
			if(StringUtils.isNotBlank(sysOrg.getOrgCode())){
				criteria.andOrgCodeEqualTo(sysOrg.getOrgCode());
			}
			example.setLimitStart(sysOrg.getStart());
			example.setLimitEnd(sysOrg.getLength());
			page.setData(mapper.selectByExample(example));
			page.setDraw(sysOrg.getDraw());
			page.setRecordsFiltered(mapper.countByExample(example));
			page.setRecordsTotal(mapper.countByExample(example));
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(500,"查询机构列表时发生未知异常");
		}
		return page;
	}

	@Override
	public List<SysOrg> selectAllOrg() {
		SysOrgMapper mapper = eesSqlSession.getMapper(SysOrgMapper.class);
		SysOrgExample example = new SysOrgExample();
		return mapper.selectByExample(example);
	}
	
}
