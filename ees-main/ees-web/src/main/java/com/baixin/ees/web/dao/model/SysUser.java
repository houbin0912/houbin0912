package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import java.util.Date;

public class SysUser extends BaseModel implements Serializable {
    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 登陆名称
     */
    private String loginName;

    /**
     * 密码MD5加密
     */
    private String password;

    /**
     * 性别（0男，1女）
     */
    private String sex;

    /**
     * 机构ID，机构表主键
     */
    private Integer orgId;

    /**
     * 角色ID，角色表主键
     */
    private Integer roleId;

    /**
     * 用户状态(0禁用,1启用)
     */
    private String status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否接收邮件(0接收，1不接收)
     */
    private String receiveEmail;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 是否接收短信(0接收，1不接收)
     */
    private String receiveMessage;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最后创建时间
     */
    private Date createdate;

    /**
     * 最后修改时间
     */
    private Date lastupdatetime;

    private static final long serialVersionUID = 1L;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName == null ? null : loginName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getReceiveEmail() {
        return receiveEmail;
    }

    public void setReceiveEmail(String receiveEmail) {
        this.receiveEmail = receiveEmail == null ? null : receiveEmail.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getReceiveMessage() {
        return receiveMessage;
    }

    public void setReceiveMessage(String receiveMessage) {
        this.receiveMessage = receiveMessage == null ? null : receiveMessage.trim();
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