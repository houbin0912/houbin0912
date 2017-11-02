package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import java.util.Date;

public class SysMenu extends BaseModel implements Serializable {
    /**
     * 菜单ID
     */
    private Integer menuId;

    /**
     * 菜单名称
     */
    protected String menuName;

    /**
     * 父菜单ID
     */
    private Integer parentMenuId;

    /**
     * 菜单URL
     */
    private String menuUrl;

    /**
     * URL类型（0父菜单无URL，1为html，2为后台接口）
     */
    protected String urlType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Date createdate;

    /**
     * 最后修改时间
     */
    private Date lastupdatetime;

    private static final long serialVersionUID = 1L;
    
    public SysMenu() {
		super();
	}

	public SysMenu(Integer menuId, String menuUrl) {
		super();
		this.menuId = menuId;
		this.menuUrl = menuUrl;
	}

	public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getUrlType() {
        return urlType;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType == null ? null : urlType.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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