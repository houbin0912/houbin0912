package com.baixin.ees.web.service.impl;

import java.util.Date;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baixin.ees.util.MyException;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.mapper.SysMenuJoinMapper;
import com.baixin.ees.web.dao.mapper.SysMenuMapper;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.SysMenu;
import com.baixin.ees.web.dao.model.SysMenuExample;
import com.baixin.ees.web.dao.model.SysMenuExample.Criteria;
import com.baixin.ees.web.dao.model.SysMenuResult;
import com.baixin.ees.web.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private SqlSession eesSqlSession;
	
	@Override
	public ResultMessage add(SysMenu sysMenu) {
		try {
			SysMenuMapper mapper = eesSqlSession.getMapper(SysMenuMapper.class);
			sysMenu.setCreatedate(new Date());
			sysMenu.setLastupdatetime(new Date());
			mapper.insert(sysMenu);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage update(SysMenu sysMenu) {
		try {
			SysMenuMapper mapper = eesSqlSession.getMapper(SysMenuMapper.class);
			sysMenu.setLastupdatetime(new Date());
			mapper.updateByPrimaryKeySelective(sysMenu);
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}
	
	@Override
	public ResultMessage delete(SysMenu sysMenu) {
		try {
			eesSqlSession.getMapper(SysMenuMapper.class).deleteByPrimaryKey(sysMenu.getMenuId());
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage deleteListById(String menuIds) {
		try {
			SysMenuJoinMapper mapper = eesSqlSession.getMapper(SysMenuJoinMapper.class);
			if(StringUtils.isNotBlank(menuIds)) {
				mapper.deleteByIds(menuIds);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.isError();
		}
		return ResultMessage.isOk();
	}

	@Override
	public Page<SysMenuResult> queryListByMenu(SysMenuResult sysMenu) throws MyException {
		Page<SysMenuResult> page = null;
		try {
			page = new Page<SysMenuResult>();
			SysMenuJoinMapper mapper = eesSqlSession.getMapper(SysMenuJoinMapper.class);
			SysMenuExample example = new SysMenuExample();
			Criteria criteria = example.createCriteria();
			if(StringUtils.isNotBlank(sysMenu.getMenuName())){
				criteria.andMenuNameLike("%"+sysMenu.getMenuName()+"%");
			}
			example.setLimitStart(sysMenu.getStart());
			example.setLimitEnd(sysMenu.getLength());
			page.setData(mapper.selectListResult(sysMenu));
			page.setDraw(sysMenu.getDraw());
			int total = mapper.countListResult(sysMenu);
			page.setRecordsFiltered(total);
			page.setRecordsTotal(total);
		} catch (Exception e) {
			e.printStackTrace();
			throw new MyException(500,"查询菜单列表时发生未知异常");
		}
		return page;
	}
	
}
