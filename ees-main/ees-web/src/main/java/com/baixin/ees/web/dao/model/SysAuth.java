package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import java.util.Date;

public class SysAuth extends BaseModel implements Serializable {
    /**
     * 
     */
    private Integer authId;

    /**
     * 
     */
    private Integer roleId;

    /**
     * 
     */
    private String menuIds;

    /**
     * 
     */
    private Date enPowerTime;

    private static final long serialVersionUID = 1L;

    public Integer getAuthId() {
        return authId;
    }

    public void setAuthId(Integer authId) {
        this.authId = authId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds == null ? null : menuIds.trim();
    }

    public Date getEnPowerTime() {
        return enPowerTime;
    }

    public void setEnPowerTime(Date enPowerTime) {
        this.enPowerTime = enPowerTime;
    }
}