package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import com.baixin.ees.util.DateUtils;

public class SysUserResult extends SysUser implements Serializable {
    
    /**
     * 所属机构
     */
    private String orgName;
    
    /**
     * 所属角色ID
     */
    private String roleName;

    private static final long serialVersionUID = 1L;

    public String getUserName() {
    	if(super.getUserName() == null) {
    		return "";
    	}
        return super.getUserName();
    }

    public String getLoginName() {
    	if(super.getLoginName() == null) {
    		return "";
    	}
        return super.getLoginName();
    }

    public String getSex() {
    	if("0".equals(super.getSex())) {
    		return "男";
    	}
    	if("1".equals(super.getSex())) {
    		return "女";
    	}
        return "";
    }

    public String getStatus() {
    	if("0".equals(super.getStatus())) {
    		return "禁用";
    	}
    	if("1".equals(super.getStatus())) {
    		return "启用";
    	}
    	return "未知状态";
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

	public String getRoleName() {
		if(roleName == null) {
    		return "";
    	}
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getReceiveEmail() {
		if("0".equals(super.getReceiveEmail())) {
    		return "接受";
    	}
		if("1".equals(super.getReceiveEmail())) {
    		return "不接受";
    	}
		return "未知";
	}

	@Override
	public String getReceiveMessage() {
		if("0".equals(super.getReceiveMessage())) {
    		return "接受";
    	}
		if("1".equals(super.getReceiveMessage())) {
    		return "不接受";
    	}
		return "未知";
	}

	public String getCreatedates() {
		if(super.getCreatedate() == null) {
			return "";
		}
		return DateUtils.date(super.getCreatedate());
	}
	
	public String getLastupdatetimes() {
		if(super.getLastupdatetime() == null) {
			return "";
		}
		return DateUtils.date(super.getLastupdatetime());
	}	
	
}