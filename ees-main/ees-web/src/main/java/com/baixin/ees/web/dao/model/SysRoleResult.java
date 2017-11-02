package com.baixin.ees.web.dao.model;

import java.io.Serializable;

public class SysRoleResult extends SysRole implements Serializable {

    /**
     * 所属机构名称
     */
    private String orgName;


    private static final long serialVersionUID = 1L;

    public String getRoleName() {
    	if(roleName == null) {
    		return "";
    	}
        return roleName;
    }

	public String getOrgName() {
		if(orgName == null) {
    		return "";
    	}
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}