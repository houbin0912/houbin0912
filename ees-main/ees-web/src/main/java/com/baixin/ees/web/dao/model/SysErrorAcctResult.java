package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SysErrorAcctResult extends SysErrorAcct implements Serializable {

    private static final long serialVersionUID = 1L;
    public String getChannelSeqNo() {
    	if (channelSeqNo == null) {
			return "";
		}
        return channelSeqNo;
    }

    public String getSysSeqNo() {
    	if (sysSeqNo == null) {
			return "";
		}
        return sysSeqNo;
    }
    
	public String getCreateTimes() {
    	if (createTime == null){
    		return "";
		}
		return date(createTime);
	}

	public String getReportTimes() {
    	if (reportTime == null){
    		return "";
		}
		return date(reportTime);
	}

	public String getProcessTimes() {
		if (processTime == null){
			return "";
		}
		return date(processTime);
	}

	public String getProofType() {
		if ("0".equals(proofType)) {
			return "端到端";
		}
    	if ("1".equals(proofType)) {
			return "内部分布式";
		}
        return "";
    }

    public String getOrgCode() {
        return orgCode;
    }

    public String getStatus() {
    	if ("0".equals(status)) {
			return "刚创建";
		}
    	if ("1".equals(status)) {
			return "已报警";
		}
    	if ("2".equals(status)) {
			return "已处理";
		}
        return "";
    }

	@Override
	public String getProcessResult() {
    	if ("0".equals(processResult)) {
			return "是";
		}
    	if ("1".equals(processResult)) {
			return "不是";
		}
		return "";
	}

	public String date(Date date) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
    }
}