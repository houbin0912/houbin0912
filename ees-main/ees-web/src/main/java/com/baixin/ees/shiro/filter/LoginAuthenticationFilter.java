package com.baixin.ees.shiro.filter;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.baixin.ees.Constants;
import com.baixin.ees.shiro.SessionObject;
import com.baixin.ees.util.UserUtils;
import com.baixin.ees.web.dao.mapper.SysAuthMapper;
import com.baixin.ees.web.dao.mapper.SysMenuJoinMapper;
import com.baixin.ees.web.dao.mapper.SysRoleMapper;
import com.baixin.ees.web.dao.model.SysAuth;
import com.baixin.ees.web.dao.model.SysAuthExample;
import com.baixin.ees.web.dao.model.SysRole;
import com.baixin.ees.web.dao.model.SysUser;
import com.baixin.ees.web.dao.model.SysAuthExample.Criteria;

/**
 * 
 * 登录成功之后初始化权限信息
 * 
 * @author: xjj
 * @CreateDate:2016年1月22日
 */

public class LoginAuthenticationFilter extends AdviceFilter {

	@Override
	protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("执行LoginAuthenticationFilter过滤器");
		if (UserUtils.isLogin()) {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession();
			// 登录成功设置缓存信息
			SessionObject sessionObject = new SessionObject();
			// 缓存用户信息
			SysUser sysUser = UserUtils.getUser();
			sessionObject.setUser(sysUser);

			WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
			SqlSessionTemplate eesSqlSession = wac.getBean("eesSqlSession", SqlSessionTemplate.class);
			// 缓存角色信息
			SysRoleMapper roleMapper = eesSqlSession.getMapper(SysRoleMapper.class);
			SysRole role = roleMapper.selectByPrimaryKey(sysUser.getRoleId());
			sessionObject.setSysRole(role);
			// 缓存权限菜单信息
			SysAuthMapper authMapper = eesSqlSession.getMapper(SysAuthMapper.class);
			SysAuthExample example = new SysAuthExample();
			Criteria criteria = example.createCriteria();
			criteria.andRoleIdEqualTo(sysUser.getRoleId());
			List<SysAuth> sysAuthList = authMapper.selectByExample(example);
			sessionObject.setSysAuth(sysAuthList.get(0));
			// 访问授权
			SysMenuJoinMapper menuMapper = eesSqlSession.getMapper(SysMenuJoinMapper.class);
			List<String> selectMenuByIds = menuMapper.selectMenuUrlByIds(sysAuthList.get(0).getMenuIds());
			selectMenuByIds.add(role.getHomeUrl());
			selectMenuByIds.add("/view/content.html");
			sessionObject.setAuthMenuList(selectMenuByIds);
			// 将缓存放入session
			session.setAttribute(Constants.SYS_SESSION_OBJECT_CACHE, sessionObject);
		}
	}
}