package com.baixin.ees.web.dao.model;

import java.io.Serializable;
import com.baixin.ees.util.DateUtils;

public class QuartzJobResult extends QuartzJob implements Serializable {


    private static final long serialVersionUID = 1L;

    public String getJobStatus() {
    	if ("0".equals(jobStatus)) {
    		return "启用";
		}
    	if ("1".equals(jobStatus)) {
    		return "禁用";
    	}
        return "未知";
    }

    public String getCreatedates() {
        return DateUtils.date(createdate);
    }

    public String getLastupdatetimes() {
        return DateUtils.date(lastupdatetime);
    }
}