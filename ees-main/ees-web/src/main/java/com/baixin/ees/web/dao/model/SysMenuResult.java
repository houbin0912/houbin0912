package com.baixin.ees.web.dao.model;

import java.io.Serializable;

public class SysMenuResult extends SysMenu implements Serializable {

    private static final long serialVersionUID = 1L;
    private String parentName;

    public String getUrlType() {
    	if("0".equals(urlType)) {
    		return "菜单";
    	}
    	if("1".equals(urlType)) {
    		return "页面";
    	}
    	if("2".equals(urlType)) {
    		return "接口";
    	}
        return "未知";
    }
    
	@Override
	public String getMenuName() {
		if(menuName == null) {
			return "";
		}
		
		return menuName;
	}

	public String getParentName() {
		if(parentName == null) {
			return "";
		}
		
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
}