package com.baixin.ees.util;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.baixin.ees.Constants;
import com.baixin.ees.shiro.SessionObject;
import com.baixin.ees.web.dao.mapper.SysAuthMapper;
import com.baixin.ees.web.dao.mapper.SysMenuJoinMapper;
import com.baixin.ees.web.dao.mapper.SysRoleMapper;
import com.baixin.ees.web.dao.model.SysAuth;
import com.baixin.ees.web.dao.model.SysAuthExample;
import com.baixin.ees.web.dao.model.SysAuthExample.Criteria;
import com.baixin.ees.web.dao.model.SysRole;
import com.baixin.ees.web.dao.model.SysUser;


/**
 * 用户工具类
 * 
 * @Description
 * @author: xjj
 * @CreateDate:2016年1月23日
 */
public class UserUtils {
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;


    /**
     * 获取当前登陆用户信息
     * 
     * @return
     */
    public static SysUser getUser() {
        return (SysUser) SecurityUtils.getSubject().getPrincipal();

    }

    public static SessionObject getSessionFromCache() {
        return (SessionObject) SecurityUtils.getSubject().getSession()
            .getAttribute(Constants.SYS_SESSION_OBJECT_CACHE);
    }

    /**
     * 是否登录
     * 
     * @return
     */
    public static boolean isLogin() {
        return UserUtils.getUser() != null && SecurityUtils.getSubject().isAuthenticated();
    }

    public static boolean logout() {
        SecurityUtils.getSubject().logout();
        return !SecurityUtils.getSubject().isAuthenticated();
    }

    /**
     * 
     * @author xjj @Title: isLogin @Description: 用户登录 @return boolean @throws
     */
    // public static void ensureUserIsLoggedOut(){
    // Subject currentUser = SecurityUtils.getSubject();
    // if(currentUser==null){
    // return;
    // }
    // currentUser.logout();
    // Session session = currentUser.getSession(false);
    // if(session==null)
    // return;
    // session.stop();
    // }

    public static boolean login(String userId, String password, boolean rememberMe) {
        UsernamePasswordToken token = new UsernamePasswordToken(userId, password, rememberMe);
        SecurityUtils.getSubject().login(token);
        return UserUtils.getUser() != null && SecurityUtils.getSubject().isAuthenticated();
    }


    public static boolean login(String userId, String password, boolean rememberMe, String host) {
        UsernamePasswordToken token = new UsernamePasswordToken(userId, password, rememberMe, host);
        SecurityUtils.getSubject().login(token);
        return UserUtils.getUser() != null && SecurityUtils.getSubject().isAuthenticated();
    }

    /**
     * 
     * @author xjj @Title: getSession @Description: 得到当前用户的session @return Session @throws
     */
    // public static Session getSession(boolean paramBoolean) {
    // return SecurityUtils.getSubject().getSession(paramBoolean);
    // }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession(false);
    }

    public static String getSessionId() {
        return getSession().getId().toString();
    }

    public static String decryptStr(String token) {
        return new DesUtil().decryptStr(token);

    }

    public static String encryptStr(String token) {
        return DesUtil.encryptStr(token);
    }

    /**
     * 
     * @Title: getSessionIdByToken @Description: 根据token得到sessionId @author xjj @return String @throws
     */
    public static String getSessionIdByToken(String token) {
        String str = decryptStr(token);
        String[] arr = str.split(":");
        return arr[0];
    }

    /**
     * 
     * @Title: getSessionIdByToken @Description: 根据token得到host @author xjj @return String @throws
     */
    public static String getHostByToken(String token) {
        String str = decryptStr(token);
        String[] arr = str.split(":");
        return arr[1];
    }

    /**
     * 
     * @Title: getTokenBySessionId @Description: 根据sessionId得到token @author xjj @return String @throws
     */
    public static String getTokenBySessionId(String sessionId, String host) {
        return encryptStr(sessionId + ":" + host);
    }

    /**
     * initSessionObject 登陆成功之后，初始化权限信息
     * @return
     */
    public static SessionObject initSessionObject() {
    	if (UserUtils.isLogin() && getSessionFromCache() == null) {
    		 Subject subject = SecurityUtils.getSubject();
    		 Session session = subject.getSession();
    		// 登录成功设置缓存信息
        	SessionObject sessionObject = new SessionObject();
        	//缓存用户信息
        	SysUser sysUser = UserUtils.getUser();
            sessionObject.setUser(sysUser);
            
            WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();
            SqlSessionTemplate eesSqlSession = wac.getBean("eesSqlSession", SqlSessionTemplate.class);
            //缓存角色信息
            SysRoleMapper roleMapper = eesSqlSession.getMapper(SysRoleMapper.class);
            SysRole role = roleMapper.selectByPrimaryKey(sysUser.getRoleId());
            sessionObject.setSysRole(role);
            //缓存权限菜单信息
            SysAuthMapper authMapper = eesSqlSession.getMapper(SysAuthMapper.class);
            SysAuthExample example = new SysAuthExample();
    	    Criteria criteria = example.createCriteria();
    	    criteria.andRoleIdEqualTo(sysUser.getRoleId());
            List<SysAuth> sysAuthList = authMapper.selectByExample(example);
            sessionObject.setSysAuth(sysAuthList.get(0));
            //访问授权
            SysMenuJoinMapper menuMapper = eesSqlSession.getMapper(SysMenuJoinMapper.class);
            List<String> selectMenuByIds = menuMapper.selectMenuUrlByIds(sysAuthList.get(0).getMenuIds());
            selectMenuByIds.add(role.getHomeUrl());
			selectMenuByIds.add("/view/content.html");
            sessionObject.setAuthMenuList(selectMenuByIds);
            //将缓存放入session
            session.setAttribute(Constants.SYS_SESSION_OBJECT_CACHE, sessionObject);
        }
    	return getSessionFromCache();
    }
}