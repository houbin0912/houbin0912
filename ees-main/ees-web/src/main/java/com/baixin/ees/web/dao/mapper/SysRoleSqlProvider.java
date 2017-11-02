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

import com.baixin.ees.web.dao.model.SysRole;
import com.baixin.ees.web.dao.model.SysRoleExample.Criteria;
import com.baixin.ees.web.dao.model.SysRoleExample.Criterion;
import com.baixin.ees.web.dao.model.SysRoleExample;
import java.util.List;
import java.util.Map;

public class SysRoleSqlProvider {

    public String countByExample(SysRoleExample example) {
        BEGIN();
        SELECT("count(*)");
        FROM("sys_role");
        applyWhere(example, false);
        return SQL();
    }

    public String deleteByExample(SysRoleExample example) {
        BEGIN();
        DELETE_FROM("sys_role");
        applyWhere(example, false);
        return SQL();
    }

    public String insertSelective(SysRole record) {
        BEGIN();
        INSERT_INTO("sys_role");
        
        if (record.getRoleId() != null) {
            VALUES("ROLE_ID", "#{roleId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleName() != null) {
            VALUES("ROLE_NAME", "#{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getHomeUrl() != null) {
            VALUES("HOME_URL", "#{homeUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            VALUES("REMARK", "#{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getBelongOrg() != null) {
            VALUES("BELONG_ORG", "#{belongOrg,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedate() != null) {
            VALUES("CREATEDATE", "#{createdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastupdatetime() != null) {
            VALUES("LASTUPDATETIME", "#{lastupdatetime,jdbcType=TIMESTAMP}");
        }
        
        return SQL();
    }

    public String selectByExample(SysRoleExample example) {
        BEGIN();
        if (example != null && example.isDistinct()) {
            SELECT_DISTINCT("ROLE_ID");
        } else {
            SELECT("ROLE_ID");
        }
        SELECT("ROLE_NAME");
        SELECT("HOME_URL");
        SELECT("REMARK");
        SELECT("BELONG_ORG");
        SELECT("CREATEDATE");
        SELECT("LASTUPDATETIME");
        FROM("sys_role");
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
        SysRole record = (SysRole) parameter.get("record");
        SysRoleExample example = (SysRoleExample) parameter.get("example");
        
        BEGIN();
        UPDATE("sys_role");
        
        if (record.getRoleId() != null) {
            SET("ROLE_ID = #{record.roleId,jdbcType=INTEGER}");
        }
        
        if (record.getRoleName() != null) {
            SET("ROLE_NAME = #{record.roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getHomeUrl() != null) {
            SET("HOME_URL = #{record.homeUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        }
        
        if (record.getBelongOrg() != null) {
            SET("BELONG_ORG = #{record.belongOrg,jdbcType=INTEGER}");
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
        UPDATE("sys_role");
        
        SET("ROLE_ID = #{record.roleId,jdbcType=INTEGER}");
        SET("ROLE_NAME = #{record.roleName,jdbcType=VARCHAR}");
        SET("HOME_URL = #{record.homeUrl,jdbcType=VARCHAR}");
        SET("REMARK = #{record.remark,jdbcType=VARCHAR}");
        SET("BELONG_ORG = #{record.belongOrg,jdbcType=INTEGER}");
        SET("CREATEDATE = #{record.createdate,jdbcType=TIMESTAMP}");
        SET("LASTUPDATETIME = #{record.lastupdatetime,jdbcType=TIMESTAMP}");
        
        SysRoleExample example = (SysRoleExample) parameter.get("example");
        applyWhere(example, true);
        return SQL();
    }

    public String updateByPrimaryKeySelective(SysRole record) {
        BEGIN();
        UPDATE("sys_role");
        
        if (record.getRoleName() != null) {
            SET("ROLE_NAME = #{roleName,jdbcType=VARCHAR}");
        }
        
        if (record.getHomeUrl() != null) {
            SET("HOME_URL = #{homeUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getRemark() != null) {
            SET("REMARK = #{remark,jdbcType=VARCHAR}");
        }
        
        if (record.getBelongOrg() != null) {
            SET("BELONG_ORG = #{belongOrg,jdbcType=INTEGER}");
        }
        
        if (record.getCreatedate() != null) {
            SET("CREATEDATE = #{createdate,jdbcType=TIMESTAMP}");
        }
        
        if (record.getLastupdatetime() != null) {
            SET("LASTUPDATETIME = #{lastupdatetime,jdbcType=TIMESTAMP}");
        }
        
        WHERE("ROLE_ID = #{roleId,jdbcType=INTEGER}");
        
        return SQL();
    }

    protected void applyWhere(SysRoleExample example, boolean includeExamplePhrase) {
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