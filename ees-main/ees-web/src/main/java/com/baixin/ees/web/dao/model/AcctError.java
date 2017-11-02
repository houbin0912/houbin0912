package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import java.util.Date;

public class AcctError extends BaseModel implements Serializable {
    /**
     * 错账表主键
     */
    private String acctErrId;

    /**
     * 全局交易流水号
     */
    private String channelSeqNo;

    /**
     * 各系统内部唯一确认一笔交易的子交易序号
     */
    private String sysSeqNo;

    /**
     * 对账类型（端到端0，系统内部分布式1）
     */
    private String proofType;

    /**
     * 账务所属系统编码
     */
    private String orgCode;

    /**
     * 对账给出的推断错账原因
     */
    private String inferReason;

    /**
     * 错账被确认时间
     */
    private Date createTime;

    /**
     * 错账状态标识(0：刚创建；1：已报警；2：已处理并反馈结果)
     */
    private String status;

    /**
     * 错账报警时间
     */
    private Date reportTime;

    /**
     * 是否错账(0：是；1：不是）
     */
    private String processResult;

    /**
     * 错账产生原因
     */
    private String errReason;

    /**
     * 错账处理时间
     */
    private Date processTime;

    private static final long serialVersionUID = 1L;

    public String getAcctErrId() {
        return acctErrId;
    }

    public void setAcctErrId(String acctErrId) {
        this.acctErrId = acctErrId == null ? null : acctErrId.trim();
    }

    public String getChannelSeqNo() {
        return channelSeqNo;
    }

    public void setChannelSeqNo(String channelSeqNo) {
        this.channelSeqNo = channelSeqNo == null ? null : channelSeqNo.trim();
    }

    public String getSysSeqNo() {
        return sysSeqNo;
    }

    public void setSysSeqNo(String sysSeqNo) {
        this.sysSeqNo = sysSeqNo == null ? null : sysSeqNo.trim();
    }

    public String getProofType() {
        return proofType;
    }

    public void setProofType(String proofType) {
        this.proofType = proofType == null ? null : proofType.trim();
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode == null ? null : orgCode.trim();
    }

    public String getInferReason() {
        return inferReason;
    }

    public void setInferReason(String inferReason) {
        this.inferReason = inferReason == null ? null : inferReason.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    public String getProcessResult() {
        return processResult;
    }

    public void setProcessResult(String processResult) {
        this.processResult = processResult == null ? null : processResult.trim();
    }

    public String getErrReason() {
        return errReason;
    }

    public void setErrReason(String errReason) {
        this.errReason = errReason == null ? null : errReason.trim();
    }

    public Date getProcessTime() {
        return processTime;
    }

    public void setProcessTime(Date processTime) {
        this.processTime = processTime;
    }
}