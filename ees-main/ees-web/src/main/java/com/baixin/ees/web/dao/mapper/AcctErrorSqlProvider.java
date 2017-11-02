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

import com.baixin.ees.web.dao.model.AcctError;
import com.baixin.ees.web.dao.model.AcctErrorExample.Criteria;
import com.baixin.ees.web.dao.model.AcctErrorExample.Criterion;
import com.baixin.ees.web.dao.model.AcctErrorExample;
import java.util.List;
import java.util.Map;

public class AcctErrorSqlProvider {

    public String countByExample(AcctErrorExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("acct_error");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(AcctErrorExample example) {
        BEGIN();
        DELETE_FROM("acct_error");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(AcctError record) {
        BEGIN();
        INSERT_INTO("acct_error");
        
        if (record.getAcctErrId() != null) {
            VALUES("ACCT_ERR_ID", "#{acctErrId,jdbcType=VARCHAR}");
        }
        
        if (record.getChannelSeqNo() != null) {
            VALUES("CHANNEL_SEQ_NO", "#{channelSeqNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSysSeqNo() != null) {
            VALUES("SYS_SEQ_NO", "#{sysSeqNo,jdbcType=VARCHAR}");
        }
        
        if (record.getProofType() != null) {
            VALUES("PROOF_TYPE", "#{proofType,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCode() != null) {
            VALUES("ORG_CODE", "#{orgCode,jdbcType=VARCHAR}");
        }
        
        if (record.getInferReason() != null) {
            VALUES("INFER_REASON", "#{inferReason,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            VALUES("CREATE_TIME", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            VALUES("STATUS", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getReportTime() != null) {
            VALUES("REPORT_TIME", "#{reportTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getProcessResult() != null) {
            VALUES("PROCESS_RESULT", "#{processResult,jdbcType=VARCHAR}");
        }
        
        if (record.getErrReason() != null) {
            VALUES("ERR_REASON", "#{errReason,jdbcType=VARCHAR}");
        }
        
        if (record.getProcessTime() != null) {
            VALUES("PROCESS_TIME", "#{processTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String selectByExample(AcctErrorExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ACCT_ERR_ID");
        } else {
            SELECT("ACCT_ERR_ID");
        }
        SELECT("CHANNEL_SEQ_NO");
        SELECT("SYS_SEQ_NO");
        SELECT("PROOF_TYPE");
        SELECT("ORG_CODE");
        SELECT("INFER_REASON");
        SELECT("CREATE_TIME");
        SELECT("STATUS");
        SELECT("REPORT_TIME");
        SELECT("PROCESS_RESULT");
        SELECT("ERR_REASON");
        SELECT("PROCESS_TIME");
        FROM("acct_error");
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
        AcctError record = (AcctError) parameter.get("record");
        AcctErrorExample example = (AcctErrorExample) parameter.get("example");
        
        BEGIN();
        UPDATE("acct_error");
        
        if (record.getAcctErrId() != null) {
            SET("ACCT_ERR_ID = #{record.acctErrId,jdbcType=VARCHAR}");
        }
        
        if (record.getChannelSeqNo() != null) {
            SET("CHANNEL_SEQ_NO = #{record.channelSeqNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSysSeqNo() != null) {
            SET("SYS_SEQ_NO = #{record.sysSeqNo,jdbcType=VARCHAR}");
        }
        
        if (record.getProofType() != null) {
            SET("PROOF_TYPE = #{record.proofType,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCode() != null) {
            SET("ORG_CODE = #{record.orgCode,jdbcType=VARCHAR}");
        }
        
        if (record.getInferReason() != null) {
            SET("INFER_REASON = #{record.inferReason,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("CREATE_TIME = #{record.createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            SET("STATUS = #{record.status,jdbcType=VARCHAR}");
        }
        
        if (record.getReportTime() != null) {
            SET("REPORT_TIME = #{record.reportTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getProcessResult() != null) {
            SET("PROCESS_RESULT = #{record.processResult,jdbcType=VARCHAR}");
        }
        
        if (record.getErrReason() != null) {
            SET("ERR_REASON = #{record.errReason,jdbcType=VARCHAR}");
        }
        
        if (record.getProcessTime() != null) {
            SET("PROCESS_TIME = #{record.processTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("acct_error");
        
        SET("ACCT_ERR_ID = #{record.acctErrId,jdbcType=VARCHAR}");
        SET("CHANNEL_SEQ_NO = #{record.channelSeqNo,jdbcType=VARCHAR}");
        SET("SYS_SEQ_NO = #{record.sysSeqNo,jdbcType=VARCHAR}");
        SET("PROOF_TYPE = #{record.proofType,jdbcType=VARCHAR}");
        SET("ORG_CODE = #{record.orgCode,jdbcType=VARCHAR}");
        SET("INFER_REASON = #{record.inferReason,jdbcType=VARCHAR}");
        SET("CREATE_TIME = #{record.createTime,jdbcType=TIMESTAMP}");
        SET("STATUS = #{record.status,jdbcType=VARCHAR}");
        SET("REPORT_TIME = #{record.reportTime,jdbcType=TIMESTAMP}");
        SET("PROCESS_RESULT = #{record.processResult,jdbcType=VARCHAR}");
        SET("ERR_REASON = #{record.errReason,jdbcType=VARCHAR}");
        SET("PROCESS_TIME = #{record.processTime,jdbcType=TIMESTAMP}");
        
        AcctErrorExample example = (AcctErrorExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(AcctError record) {
        BEGIN();
        UPDATE("acct_error");
        
        if (record.getChannelSeqNo() != null) {
            SET("CHANNEL_SEQ_NO = #{channelSeqNo,jdbcType=VARCHAR}");
        }
        
        if (record.getSysSeqNo() != null) {
            SET("SYS_SEQ_NO = #{sysSeqNo,jdbcType=VARCHAR}");
        }
        
        if (record.getProofType() != null) {
            SET("PROOF_TYPE = #{proofType,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgCode() != null) {
            SET("ORG_CODE = #{orgCode,jdbcType=VARCHAR}");
        }
        
        if (record.getInferReason() != null) {
            SET("INFER_REASON = #{inferReason,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            SET("CREATE_TIME = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getStatus() != null) {
            SET("STATUS = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getReportTime() != null) {
            SET("REPORT_TIME = #{reportTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getProcessResult() != null) {
            SET("PROCESS_RESULT = #{processResult,jdbcType=VARCHAR}");
        }
        
        if (record.getErrReason() != null) {
            SET("ERR_REASON = #{errReason,jdbcType=VARCHAR}");
        }
        
        if (record.getProcessTime() != null) {
            SET("PROCESS_TIME = #{processTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("ACCT_ERR_ID = #{acctErrId,jdbcType=VARCHAR}");
        
        return SQL();
    }

    protected void applyWhere(AcctErrorExample example, boolean includeExamplePhrase) {
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