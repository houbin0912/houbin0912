package com.baixin.ees.web.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baixin.ees.Constants;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.mapper.SysAuthMapper;
import com.baixin.ees.web.dao.mapper.SysMenuMapper;
import com.baixin.ees.web.dao.model.MenuTree;
import com.baixin.ees.web.dao.model.SysAuth;
import com.baixin.ees.web.dao.model.SysAuthExample;
import com.baixin.ees.web.dao.model.SysAuthExample.Criteria;
import com.baixin.ees.web.dao.model.SysMenu;
import com.baixin.ees.web.dao.model.SysMenuExample;
import com.baixin.ees.web.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	private SqlSession eesSqlSession;


	@Override
	public ResultMessage deleteListByRoleId(Integer roleId) {
		SysAuthMapper mapper = eesSqlSession.getMapper(SysAuthMapper.class);
		SysAuthExample example = new SysAuthExample();
	    Criteria criteria = example.createCriteria();
	    criteria.andRoleIdEqualTo(roleId);
	    if(mapper.deleteByExample(example) >= 0) {
	    	return ResultMessage.isOk();
	    }
		return ResultMessage.isError();
	}
		
	@Override
	public List<MenuTree> queryMenuList(Integer roleId) {
		// 查询 菜单
		SysMenuMapper mapper = eesSqlSession.getMapper(SysMenuMapper.class);
		SysMenuExample example = new SysMenuExample();
		com.baixin.ees.web.dao.model.SysMenuExample.Criteria menuCriteria = example.createCriteria();
		menuCriteria.andMenuIdNotEqualTo(0);
		List<SysMenu> sysMenuList = mapper.selectByExample(example);
		// 查询 权限
		Map<String,Integer> map = new HashMap<String,Integer>();
		if(roleId != null && roleId > 0){
			SysAuthMapper authMapper = eesSqlSession.getMapper(SysAuthMapper.class);
			SysAuthExample authExample = new SysAuthExample();
			Criteria criteria = authExample.createCriteria();
			criteria.andRoleIdEqualTo(roleId);
			List<SysAuth> sysAuthList = authMapper.selectByExample(authExample);
			for(SysAuth sysAuth:sysAuthList){
				for(String menuId:sysAuth.getMenuIds().split(",")){
					map.put(menuId, 1);
				}
			}
		}
		return getMenuTree(Constants.ROOT_MENU_ID, map, sysMenuList);
	}
	
	public List<MenuTree> getMenuTree(Integer parId,Map<String,Integer> map,List<SysMenu> sysMenuList){
		List<MenuTree> treeList = new ArrayList<MenuTree>();
		for(SysMenu menu:sysMenuList){
			if(parId.equals(menu.getParentMenuId())){
				MenuTree tree = new MenuTree();
				tree.setText(menu.getMenuName());
				tree.setTags(menu.getMenuId().toString());
				if(map.get(menu.getMenuId().toString()) != null){
					tree.getState().setChecked(true);
				}else{
					tree.getState().setChecked(false);
				}
				tree.setNodes(getMenuTree(menu.getMenuId(), map, sysMenuList));
				treeList.add(tree);
			}
		}
		return treeList;
	}

	@Override
	@Transactional
	public ResultMessage initRolesAuth(String roleIds, String auths) {
		SysAuthMapper authMapper = eesSqlSession.getMapper(SysAuthMapper.class);
		for(String roleIdStr:roleIds.split(",",-1)){
			if(StringUtils.isNotBlank(roleIdStr)){
				Integer roleId = Integer.parseInt(roleIdStr);
				if(deleteListByRoleId(roleId).isSuccess()){
					SysAuth sysAuth = new SysAuth();
					sysAuth.setRoleId(roleId);
					sysAuth.setMenuIds(auths);
					sysAuth.setEnPowerTime(new Date());
					authMapper.insert(sysAuth);
				}
			}
		}
		return ResultMessage.isOk();
	}

	@Override
	public ResultMessage addOrUpdateAuth(String anthList, Integer roleId) {
		SysAuthMapper authMapper = eesSqlSession.getMapper(SysAuthMapper.class);
		if(deleteListByRoleId(roleId).isSuccess()){
			SysAuth sysAuth = new SysAuth();
			sysAuth.setRoleId(roleId);
			sysAuth.setMenuIds(anthList);
			sysAuth.setEnPowerTime(new Date());
			if (authMapper.insert(sysAuth) > 0) {
				return ResultMessage.isOk();
			}
		}
		return ResultMessage.isError();
	}

}
