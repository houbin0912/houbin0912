package com.baixin.ees.web.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.mapper.SysAuthMapper;
import com.baixin.ees.web.dao.mapper.SysRoleJoinMapper;
import com.baixin.ees.web.dao.mapper.SysRoleMapper;
import com.baixin.ees.web.dao.mapper.SysUserMapper;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysAuthExample;
import com.baixin.ees.web.dao.model.SysRole;
import com.baixin.ees.web.dao.model.SysRoleExample;
import com.baixin.ees.web.dao.model.SysRoleExample.Criteria;
import com.baixin.ees.web.dao.model.SysRoleResult;
import com.baixin.ees.web.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private SqlSession eesSqlSession;

	@Override
	public ResultMessage add(SysRole role) {
		try {
			SysRoleMapper mapper = eesSqlSession.getMapper(SysRoleMapper.class);
			role.setRoleId(null);//主键置空，表内主键自增
			role.setCreatedate(new Date());
			role.setLastupdatetime(new Date());
			mapper.insert(role);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	public ResultMessage update(SysRole role) {
		try {
			SysRoleMapper mapper = eesSqlSession.getMapper(SysRoleMapper.class);
			role.setLastupdatetime(new Date());
			mapper.updateByPrimaryKeySelective(role);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}


	@Override
	public ResultMessage delete(SysRole role) {
		try {
			eesSqlSession.getMapper(SysUserMapper.class).deleteByPrimaryKey(role.getRoleId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage deleteById(Integer roleId) {
		try {
			eesSqlSession.getMapper(SysUserMapper.class).deleteByPrimaryKey(roleId);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage deleteListById(String roleIds) {
		try {
			SysRoleJoinMapper mapper = eesSqlSession.getMapper(SysRoleJoinMapper.class);
			SysAuthMapper mapper2 = eesSqlSession.getMapper(SysAuthMapper.class);
			SysAuthExample auth = new SysAuthExample();
			com.baixin.ees.web.dao.model.SysAuthExample.Criteria criteria = auth.createCriteria();
			if(StringUtils.isNotBlank(roleIds)) {
				mapper.deleteByIds(roleIds);
				criteria.andRoleIdEqualTo(Integer.parseInt(roleIds));
				mapper2.deleteByExample(auth);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public List<SysRole> selectRolesByOrgId(Integer roleId) throws MyException {
		try {
			SysRoleMapper mapper = eesSqlSession.getMapper(SysRoleMapper.class);
			SysRoleExample example = new SysRoleExample();
			Criteria criteria = example.createCriteria();
			criteria.andBelongOrgEqualTo(roleId);
			return mapper.selectByExample(example);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(500,"查询角色时发生未知异常");
		}
	}

	@Override
	public ResultMessage checkroleNameExsit(String roleName) {
		try {
			if(StringUtils.isNotBlank(roleName)) {
				SysRoleMapper mapper = eesSqlSession.getMapper(SysRoleMapper.class);
				SysRoleExample example = new SysRoleExample();
			    Criteria criteria = example.createCriteria();
			    criteria.andRoleNameEqualTo(roleName);
			    if(mapper.countByExample(example) > 0){
			    	return ResultMessage.isOk("该用户存在", null);
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError("检验发生错误");
		}
		return ResultMessage.isError("该用户可用");
	}



	@Override
	public Page<SysRoleResult> selectListResult(SysRoleResult role) throws MyException {
		Page<SysRoleResult> page = null;
		try {
			page = new Page<SysRoleResult>();
			SysRoleJoinMapper mapper = eesSqlSession.getMapper(SysRoleJoinMapper.class);
			page.setData(mapper.selectListResult(role));
			page.setDraw(role.getDraw());
			int count = mapper.countListResult(role);
			page.setRecordsFiltered(count);
			page.setRecordsTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(500,"查询角色列表时发生未知异常");
		}
		return page;
	}
}
