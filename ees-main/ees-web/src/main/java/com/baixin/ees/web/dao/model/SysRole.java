package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import java.util.Date;

public class SysRole extends BaseModel implements Serializable {
    /**
     * 
     */
	protected Integer roleId;

    /**
     * 
     */
    protected String roleName;

    /**
     * 
     */
    protected String homeUrl;

    /**
     * 
     */
    protected String remark;

    /**
     * 
     */
    protected Integer belongOrg;

    /**
     * 
     */
    protected Date createdate;

    /**
     * 
     */
    protected Date lastupdatetime;

    private static final long serialVersionUID = 1L;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl == null ? null : homeUrl.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getBelongOrg() {
        return belongOrg;
    }

    public void setBelongOrg(Integer belongOrg) {
        this.belongOrg = belongOrg;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}