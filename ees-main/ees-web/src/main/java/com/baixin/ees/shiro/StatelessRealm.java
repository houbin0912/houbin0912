package com.baixin.ees.shiro;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import com.baixin.ees.util.UserUtils;
import com.baixin.ees.web.dao.mapper.SysUserMapper;
import com.baixin.ees.web.dao.model.SysRole;
import com.baixin.ees.web.dao.model.SysUser;
import com.baixin.ees.web.dao.model.SysUserExample;
import com.baixin.ees.web.dao.model.SysUserExample.Criteria;

/**
 * 
 * 用户名密码认证
 * 
 * @author: wh_xiaojj
 * @CreateDate:2016年1月21日
 * 
 * 
 * @UpdateUser:wh_xiaojj
 * @UpdateDate:2016年1月21日
 * @UpdateRemark:修改具体的内容；
 * 
 */
public class StatelessRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(StatelessRealm.class);

    private SqlSessionFactory sqlSessionFactory;// MyBatis模板


    public SqlSession getMybatisSession() {
        return this.sqlSessionFactory.openSession();
    }


    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }


    public StatelessRealm() {
        super(); // 设定密码校验的Hash算法与迭代次数
        setCredentialsMatcher(new CustomCredentialsMatcher());
        setAuthenticationTokenClass(UsernamePasswordToken.class);
    }


    public boolean supports(AuthenticationToken token) {
        // 仅支持StatelessToken类型的Token
        return token instanceof UsernamePasswordToken;
    }


    /**
     * 
     * @Description: 用户授权认证
     * @param @param token
     * @param @return
     * @param @throws AuthenticationException 设定文件
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
    	System.out.println("执行StatelessRealm登陆信息设置");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();

        SysUserMapper mapper = getMybatisSession().getMapper(SysUserMapper.class);
		SysUserExample example = new SysUserExample();
	    Criteria criteria = example.createCriteria();
	    criteria.andLoginNameEqualTo(username);
        List<SysUser> userList = mapper.selectByExample(example);
        if(userList == null || userList.isEmpty()){
        	log.error("权限校验未通过！登陆用户名【" + username + "】不存在!");
            throw new UnknownAccountException();
        }
        if(userList.size() != 1){
        	log.error("权限校验未通过！登陆用户名【" + username + "】不存在!");
            throw new UnknownAccountException();
        }
        SysUser sysUser = userList.get(0);
        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), getName());
    }


    /**
     * 
     * @Description: 用户权限信息设置
     * @param @param principals
     * @param @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SessionObject sessionObject = UserUtils.getSessionFromCache();
        if (sessionObject != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            List<String> moduleMap = sessionObject.getAuthMenuList();// 模块权限信息
            if (moduleMap != null) {
                // 制作菜单验证
                for (String sysMenu : moduleMap) {
                    if (StringUtils.hasText(sysMenu)) {
                        info.addStringPermission(sysMenu);
                    }
                }
            }
            SysRole sysRole = sessionObject.getSysRole();
            if(sysRole != null){
            	info.addRole(sysRole.getRoleName());
            }
            return info;
        }
        return null;
    }
}