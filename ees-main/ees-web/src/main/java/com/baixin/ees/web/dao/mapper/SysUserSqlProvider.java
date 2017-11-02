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

import com.baixin.ees.web.dao.model.SysUser;
import com.baixin.ees.web.dao.model.SysUserExample.Criteria;
import com.baixin.ees.web.dao.model.SysUserExample.Criterion;
import com.baixin.ees.web.dao.model.SysUserExample;
import java.util.List;
import java.util.Map;

public class SysUserSqlProvider {

    public String countByExample(SysUserExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("sys_user");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(SysUserExample example) {
        BEGIN();
        DELETE_FROM("sys_user");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(SysUser record) {
        BEGIN();
        INSERT_INTO("sys_user");
        
        if (record.getUserId() != null) {
            VALUES("USER_ID", "#{userId,jdbcType=INTEGER}");
        }
        
        if (record.getUserName() != null) {
            VALUES("USER_NAME", "#{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginName() != null) {
            VALUES("LOGIN_NAME", "#{loginName,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            VALUES("PASSWORD", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            VALUES("SEX", "#{sex,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgId() != null) {
            VALUES("ORG_ID", "#{orgId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleId() != null) {
            VALUES("ROLE_ID", "#{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            VALUES("STATUS", "#{status,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            VALUES("EMAIL", "#{email,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiveEmail() != null) {
            VALUES("RECEIVE_EMAIL", "#{receiveEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getPhoneNumber() != null) {
            VALUES("PHONE_NUMBER", "#{phoneNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiveMessage() != null) {
            VALUES("RECEIVE_MESSAGE", "#{receiveMessage,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            VALUES("REMARK", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedate() != null) {
            VALUES("CREATEDATE", "#{createdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastupdatetime() != null) {
            VALUES("LASTUPDATETIME", "#{lastupdatetime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String selectByExample(SysUserExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("USER_ID");
        } else {
            SELECT("USER_ID");
        }
        SELECT("USER_NAME");
        SELECT("LOGIN_NAME");
        SELECT("PASSWORD");
        SELECT("SEX");
        SELECT("ORG_ID");
        SELECT("ROLE_ID");
        SELECT("STATUS");
        SELECT("EMAIL");
        SELECT("RECEIVE_EMAIL");
        SELECT("PHONE_NUMBER");
        SELECT("RECEIVE_MESSAGE");
        SELECT("REMARK");
        SELECT("CREATEDATE");
        SELECT("LASTUPDATETIME");
        FROM("sys_user");
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
        SysUser record = (SysUser) parameter.get("record");
        SysUserExample example = (SysUserExample) parameter.get("example");
        
        BEGIN();
        UPDATE("sys_user");
        
        if (record.getUserId() != null) {
            SET("USER_ID = #{record.userId,jdbcType=INTEGER}");
        }
        
        if (record.getUserName() != null) {
            SET("USER_NAME = #{record.userName,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginName() != null) {
            SET("LOGIN_NAME = #{record.loginName,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            SET("PASSWORD = #{record.password,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            SET("SEX = #{record.sex,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgId() != null) {
            SET("ORG_ID = #{record.orgId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleId() != null) {
            SET("ROLE_ID = #{record.roleId,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            SET("STATUS = #{record.status,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            SET("EMAIL = #{record.email,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiveEmail() != null) {
            SET("RECEIVE_EMAIL = #{record.receiveEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getPhoneNumber() != null) {
            SET("PHONE_NUMBER = #{record.phoneNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiveMessage() != null) {
            SET("RECEIVE_MESSAGE = #{record.receiveMessage,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
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
        UPDATE("sys_user");
        
        SET("USER_ID = #{record.userId,jdbcType=INTEGER}");
        SET("USER_NAME = #{record.userName,jdbcType=VARCHAR}");
        SET("LOGIN_NAME = #{record.loginName,jdbcType=VARCHAR}");
        SET("PASSWORD = #{record.password,jdbcType=VARCHAR}");
        SET("SEX = #{record.sex,jdbcType=VARCHAR}");
        SET("ORG_ID = #{record.orgId,jdbcType=INTEGER}");
        SET("ROLE_ID = #{record.roleId,jdbcType=INTEGER}");
        SET("STATUS = #{record.status,jdbcType=VARCHAR}");
        SET("EMAIL = #{record.email,jdbcType=VARCHAR}");
        SET("RECEIVE_EMAIL = #{record.receiveEmail,jdbcType=VARCHAR}");
        SET("PHONE_NUMBER = #{record.phoneNumber,jdbcType=VARCHAR}");
        SET("RECEIVE_MESSAGE = #{record.receiveMessage,jdbcType=VARCHAR}");
        SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        SET("CREATEDATE = #{record.createdate,jdbcType=TIMESTAMP}");
        SET("LASTUPDATETIME = #{record.lastupdatetime,jdbcType=TIMESTAMP}");
        
        SysUserExample example = (SysUserExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysUser record) {
        BEGIN();
        UPDATE("sys_user");
        
        if (record.getUserName() != null) {
            SET("USER_NAME = #{userName,jdbcType=VARCHAR}");
        }
        
        if (record.getLoginName() != null) {
            SET("LOGIN_NAME = #{loginName,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            SET("PASSWORD = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            SET("SEX = #{sex,jdbcType=VARCHAR}");
        }
        
        if (record.getOrgId() != null) {
            SET("ORG_ID = #{orgId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleId() != null) {
            SET("ROLE_ID = #{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            SET("STATUS = #{status,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            SET("EMAIL = #{email,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiveEmail() != null) {
            SET("RECEIVE_EMAIL = #{receiveEmail,jdbcType=VARCHAR}");
        }
        
        if (record.getPhoneNumber() != null) {
            SET("PHONE_NUMBER = #{phoneNumber,jdbcType=VARCHAR}");
        }
        
        if (record.getReceiveMessage() != null) {
            SET("RECEIVE_MESSAGE = #{receiveMessage,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            SET("REMARK = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getCreatedate() != null) {
            SET("CREATEDATE = #{createdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastupdatetime() != null) {
            SET("LASTUPDATETIME = #{lastupdatetime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("USER_ID = #{userId,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(SysUserExample example, boolean includeExamplePhrase) {
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