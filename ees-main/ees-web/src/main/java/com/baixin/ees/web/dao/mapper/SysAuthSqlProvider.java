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

import com.baixin.ees.web.dao.model.SysAuth;
import com.baixin.ees.web.dao.model.SysAuthExample.Criteria;
import com.baixin.ees.web.dao.model.SysAuthExample.Criterion;
import com.baixin.ees.web.dao.model.SysAuthExample;
import java.util.List;
import java.util.Map;

public class SysAuthSqlProvider {

    public String countByExample(SysAuthExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("sys_auth");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(SysAuthExample example) {
        BEGIN();
        DELETE_FROM("sys_auth");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(SysAuth record) {
        BEGIN();
        INSERT_INTO("sys_auth");
        
        if (record.getAuthId() != null) {
            VALUES("AUTH_ID", "#{authId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleId() != null) {
            VALUES("ROLE_ID", "#{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getMenuIds() != null) {
            VALUES("MENU_IDS", "#{menuIds,jdbcType=VARCHAR}");
        }
        
        if (record.getEnPowerTime() != null) {
            VALUES("EN_POWER_TIME", "#{enPowerTime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String selectByExample(SysAuthExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("AUTH_ID");
        } else {
            SELECT("AUTH_ID");
        }
        SELECT("ROLE_ID");
        SELECT("MENU_IDS");
        SELECT("EN_POWER_TIME");
        FROM("sys_auth");
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
        SysAuth record = (SysAuth) parameter.get("record");
        SysAuthExample example = (SysAuthExample) parameter.get("example");
        
        BEGIN();
        UPDATE("sys_auth");
        
        if (record.getAuthId() != null) {
            SET("AUTH_ID = #{record.authId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleId() != null) {
            SET("ROLE_ID = #{record.roleId,jdbcType=INTEGER}");
        }
        
        if (record.getMenuIds() != null) {
            SET("MENU_IDS = #{record.menuIds,jdbcType=VARCHAR}");
        }
        
        if (record.getEnPowerTime() != null) {
            SET("EN_POWER_TIME = #{record.enPowerTime,jdbcType=TIMESTAMP}");
        }
        
        applyWhere(example, true);
        return SQL();
    }

    public String updateByExample(Map<String, Object> parameter) {
        BEGIN();
        UPDATE("sys_auth");
        
        SET("AUTH_ID = #{record.authId,jdbcType=INTEGER}");
        SET("ROLE_ID = #{record.roleId,jdbcType=INTEGER}");
        SET("MENU_IDS = #{record.menuIds,jdbcType=VARCHAR}");
        SET("EN_POWER_TIME = #{record.enPowerTime,jdbcType=TIMESTAMP}");
        
        SysAuthExample example = (SysAuthExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysAuth record) {
        BEGIN();
        UPDATE("sys_auth");
        
        if (record.getRoleId() != null) {
            SET("ROLE_ID = #{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getMenuIds() != null) {
            SET("MENU_IDS = #{menuIds,jdbcType=VARCHAR}");
        }
        
        if (record.getEnPowerTime() != null) {
            SET("EN_POWER_TIME = #{enPowerTime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("AUTH_ID = #{authId,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(SysAuthExample example, boolean includeExamplePhrase) {
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