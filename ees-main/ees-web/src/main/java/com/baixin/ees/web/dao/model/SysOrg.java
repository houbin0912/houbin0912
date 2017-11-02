package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import java.util.Date;

public class SysOrg extends BaseModel implements Serializable {
    /**
     * 机构ID
     */
    private Integer orgId;

    /**
     * 机构名称
     */
    private String orgName;

    /**
     * 机构代码，全行统一标识
     */
    private String orgCode;

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

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
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