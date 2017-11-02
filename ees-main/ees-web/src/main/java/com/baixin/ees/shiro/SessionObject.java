package com.baixin.ees.shiro;

import java.io.Serializable;
import java.util.List;

import com.baixin.ees.web.dao.model.SysAuth;
/*import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;*/
import com.baixin.ees.web.dao.model.SysOrg;
import com.baixin.ees.web.dao.model.SysRole;
import com.baixin.ees.web.dao.model.SysUser;

/*@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)*/
public class SessionObject implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -4040593280205361898L;
    private SysUser user;
    private SysOrg sysOrg;
    private SysRole sysRole;
    private SysAuth sysAuth;
    private List<String> authMenuList;// 授权菜单

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

	public SysOrg getSysOrg() {
		return sysOrg;
	}

	public void setSysOrg(SysOrg sysOrg) {
		this.sysOrg = sysOrg;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public List<String> getAuthMenuList() {
		return authMenuList;
	}

	public void setAuthMenuList(List<String> authMenuList) {
		this.authMenuList = authMenuList;
	}

	public SysAuth getSysAuth() {
		return sysAuth;
	}

	public void setSysAuth(SysAuth sysAuth) {
		this.sysAuth = sysAuth;
	}
	
}
