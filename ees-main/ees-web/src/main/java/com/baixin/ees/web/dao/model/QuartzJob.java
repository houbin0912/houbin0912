package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import java.util.Date;

public class QuartzJob extends BaseModel implements Serializable {
    /**
     * 任务ID
     */
    private Integer jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * 任务组名
     */
    private String jobGroupName;

    /**
     * 触发器名称
     */
    private String jobTriggerName;

    /**
     * 触发器组
     */
    private String jobTriggerGroupName;

    /**
     * 
     */
    private String jobImplClass;

    /**
     * 任务定时表达式
     */
    private String cronExpression;

    /**
     * 任务状态（0启用，1禁用）
     */
    protected String jobStatus;

    /**
     * 任务中文描述
     */
    private String jobDesc;

    /**
     * 创建时间
     */
    protected Date createdate;

    /**
     * 最后修改时间
     */
    protected Date lastupdatetime;

    private static final long serialVersionUID = 1L;

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobGroupName() {
        return jobGroupName;
    }

    public void setJobGroupName(String jobGroupName) {
        this.jobGroupName = jobGroupName == null ? null : jobGroupName.trim();
    }

    public String getJobTriggerName() {
        return jobTriggerName;
    }

    public void setJobTriggerName(String jobTriggerName) {
        this.jobTriggerName = jobTriggerName == null ? null : jobTriggerName.trim();
    }

    public String getJobTriggerGroupName() {
        return jobTriggerGroupName;
    }

    public void setJobTriggerGroupName(String jobTriggerGroupName) {
        this.jobTriggerGroupName = jobTriggerGroupName == null ? null : jobTriggerGroupName.trim();
    }

    public String getJobImplClass() {
        return jobImplClass;
    }

    public void setJobImplClass(String jobImplClass) {
        this.jobImplClass = jobImplClass == null ? null : jobImplClass.trim();
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression == null ? null : cronExpression.trim();
    }

    public String getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(String jobStatus) {
        this.jobStatus = jobStatus == null ? null : jobStatus.trim();
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc == null ? null : jobDesc.trim();
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