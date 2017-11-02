package com.baixin.ees.web.dao.mapper;

import static org.apache.ibatis.jdbc.SqlBuilder.BEGIN;
import static org.apache.ibatis.jdbc.SqlBuilder.DELETE_FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.FROM;
import static org.apache.ibatis.jdbc.SqlBuilder.INSERT_INTO;
import static org.apache.ibatis.jdbc.SqlBuilder.ORDER_BY;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT;
import static org.apache.ibatis.jdbc.SqlBuilder.SELECT_DISTINCT;
import static org.apache.ibatis.jdbc.SqlBuilder.SET;
import static org.apache.ibatis.jdbc.SqlBuilder.SQL;
import static org.apache.ibatis.jdbc.SqlBuilder.UPDATE;
import static org.apache.ibatis.jdbc.SqlBuilder.VALUES;
import static org.apache.ibatis.jdbc.SqlBuilder.WHERE;

import com.baixin.ees.web.dao.model.QuartzJob;
import com.baixin.ees.web.dao.model.QuartzJobExample.Criteria;
import com.baixin.ees.web.dao.model.QuartzJobExample.Criterion;
import com.baixin.ees.web.dao.model.QuartzJobExample;
import java.util.List;
import java.util.Map;

public class QuartzJobSqlProvider {

    public String countByExample(QuartzJobExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("quartz_job");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(QuartzJobExample example) {
        BEGIN();
        DELETE_FROM("quartz_job");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(QuartzJob record) {
        BEGIN();
        INSERT_INTO("quartz_job");
        
        if (record.getJobId() != null) {
            VALUES("JOB_ID", "#{jobId,jdbcType=INTEGER}");
        }
        
        if (record.getJobName() != null) {
            VALUES("JOB_NAME", "#{jobName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobGroupName() != null) {
            VALUES("JOB_GROUP_NAME", "#{jobGroupName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobTriggerName() != null) {
            VALUES("JOB_TRIGGER_NAME", "#{jobTriggerName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobTriggerGroupName() != null) {
            VALUES("JOB_TRIGGER_GROUP_NAME", "#{jobTriggerGroupName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobImplClass() != null) {
            VALUES("JOB_IMPL_CLASS", "#{jobImplClass,jdbcType=VARCHAR}");
        }
        
        if (record.getCronExpression() != null) {
            VALUES("CRON_EXPRESSION", "#{cronExpression,jdbcType=VARCHAR}");
        }
        
        if (record.getJobStatus() != null) {
            VALUES("JOB_STATUS", "#{jobStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getJobDesc() != null) {
            VALUES("JOB_DESC", "#{jobDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedate() != null) {
            VALUES("CREATEDATE", "#{createdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastupdatetime() != null) {
            VALUES("LASTUPDATETIME", "#{lastupdatetime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String selectByExample(QuartzJobExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("JOB_ID");
        } else {
            SELECT("JOB_ID");
        }
        SELECT("JOB_NAME");
        SELECT("JOB_GROUP_NAME");
        SELECT("JOB_TRIGGER_NAME");
        SELECT("JOB_TRIGGER_GROUP_NAME");
        SELECT("JOB_IMPL_CLASS");
        SELECT("CRON_EXPRESSION");
        SELECT("JOB_STATUS");
        SELECT("JOB_DESC");
        SELECT("CREATEDATE");
        SELECT("LASTUPDATETIME");
        FROM("quartz_job");
        applyWhere(example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            ORDER_BY(example.getOrderByClause());
        }
        
        String sql = SQL();
        if (example != null && example.getLimitStart() != -1 && example.getLimitEnd() != -1) {
            sql += " LIMIT " + example.getLimitStart() + "," + example.getLimitEnd();
        }
        return sql;
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        QuartzJob record = (QuartzJob) parameter.get("record");
        QuartzJobExample example = (QuartzJobExample) parameter.get("example");
        
        BEGIN();
        UPDATE("quartz_job");
        
        if (record.getJobId() != null) {
            SET("JOB_ID = #{record.jobId,jdbcType=INTEGER}");
        }
        
        if (record.getJobName() != null) {
            SET("JOB_NAME = #{record.jobName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobGroupName() != null) {
            SET("JOB_GROUP_NAME = #{record.jobGroupName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobTriggerName() != null) {
            SET("JOB_TRIGGER_NAME = #{record.jobTriggerName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobTriggerGroupName() != null) {
            SET("JOB_TRIGGER_GROUP_NAME = #{record.jobTriggerGroupName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobImplClass() != null) {
            SET("JOB_IMPL_CLASS = #{record.jobImplClass,jdbcType=VARCHAR}");
        }
        
        if (record.getCronExpression() != null) {
            SET("CRON_EXPRESSION = #{record.cronExpression,jdbcType=VARCHAR}");
        }
        
        if (record.getJobStatus() != null) {
            SET("JOB_STATUS = #{record.jobStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getJobDesc() != null) {
            SET("JOB_DESC = #{record.jobDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedate() != null) {
            SET("CREATEDATE = #{record.createdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastupdatetime() != null) {
            SET("LASTUPDATETIME = #{record.lastupdatetime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("quartz_job");
        
        SET("JOB_ID = #{record.jobId,jdbcType=INTEGER}");
        SET("JOB_NAME = #{record.jobName,jdbcType=VARCHAR}");
        SET("JOB_GROUP_NAME = #{record.jobGroupName,jdbcType=VARCHAR}");
        SET("JOB_TRIGGER_NAME = #{record.jobTriggerName,jdbcType=VARCHAR}");
        SET("JOB_TRIGGER_GROUP_NAME = #{record.jobTriggerGroupName,jdbcType=VARCHAR}");
        SET("JOB_IMPL_CLASS = #{record.jobImplClass,jdbcType=VARCHAR}");
        SET("CRON_EXPRESSION = #{record.cronExpression,jdbcType=VARCHAR}");
        SET("JOB_STATUS = #{record.jobStatus,jdbcType=VARCHAR}");
        SET("JOB_DESC = #{record.jobDesc,jdbcType=VARCHAR}");
        SET("CREATEDATE = #{record.createdate,jdbcType=TIMESTAMP}");
        SET("LASTUPDATETIME = #{record.lastupdatetime,jdbcType=TIMESTAMP}");
        
        QuartzJobExample example = (QuartzJobExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(QuartzJob record) {
        BEGIN();
        UPDATE("quartz_job");
        
        if (record.getJobName() != null) {
            SET("JOB_NAME = #{jobName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobGroupName() != null) {
            SET("JOB_GROUP_NAME = #{jobGroupName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobTriggerName() != null) {
            SET("JOB_TRIGGER_NAME = #{jobTriggerName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobTriggerGroupName() != null) {
            SET("JOB_TRIGGER_GROUP_NAME = #{jobTriggerGroupName,jdbcType=VARCHAR}");
        }
        
        if (record.getJobImplClass() != null) {
            SET("JOB_IMPL_CLASS = #{jobImplClass,jdbcType=VARCHAR}");
        }
        
        if (record.getCronExpression() != null) {
            SET("CRON_EXPRESSION = #{cronExpression,jdbcType=VARCHAR}");
        }
        
        if (record.getJobStatus() != null) {
            SET("JOB_STATUS = #{jobStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getJobDesc() != null) {
            SET("JOB_DESC = #{jobDesc,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedate() != null) {
            SET("CREATEDATE = #{createdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastupdatetime() != null) {
            SET("LASTUPDATETIME = #{lastupdatetime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("JOB_ID = #{jobId,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(QuartzJobExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            WHERE(sb.toString());
        }
    }
}