package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AcctErrorResult extends AcctError implements Serializable {

    private static final long serialVersionUID = 1L;
    public String getChannelSeqNo() {
    	if (super.getChannelSeqNo() == null) {
			return "";
		}
        return super.getChannelSeqNo();
    }

    public String getSysSeqNo() {
    	if (super.getSysSeqNo() == null) {
			return "";
		}
        return super.getSysSeqNo();
    }
    
	public String getCreateTimes() {
    	if (super.getCreateTime() == null){
    		return "";
		}
		return date(super.getCreateTime());
	}

	public String getReportTimes() {
    	if (super.getReportTime() == null){
    		return "";
		}
		return date(super.getReportTime());
	}

	public String getProcessTimes() {
		if (super.getProcessTime() == null){
			return "";
		}
		return date(super.getProcessTime());
	}

	public String getProofType() {
		if ("0".equals(super.getProofType())) {
			return "端到端";
		}
    	if ("1".equals(super.getProofType())) {
			return "内部分布式";
		}
        return "";
    }

    public String getOrgCode() {
        return super.getOrgCode();
    }

    public String getStatus() {
    	if ("0".equals(super.getStatus())) {
			return "刚创建";
		}
    	if ("1".equals(super.getStatus())) {
			return "已报警";
		}
    	if ("2".equals(super.getStatus())) {
			return "已处理";
		}
        return "";
    }

	@Override
	public String getProcessResult() {
    	if ("0".equals(super.getProcessResult())) {
			return "是";
		}
    	if ("1".equals(super.getProcessResult())) {
			return "不是";
		}
		return "";
	}

	public String date(Date date) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
    }
}