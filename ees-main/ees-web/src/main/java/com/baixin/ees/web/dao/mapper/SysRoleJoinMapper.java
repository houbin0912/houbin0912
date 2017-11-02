package com.baixin.ees.web.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

import com.baixin.ees.web.dao.model.SysRoleResult;

public interface SysRoleJoinMapper {

	
	@Select({
    	"SELECT ROLE_ID,ROLE_NAME,HOME_URL,r.REMARK,r.BELONG_ORG,o.ORG_NAME FROM sys_role r, "
    	+ "sys_org o WHERE o.ORG_ID = r.BELONG_ORG AND o.ORG_NAME like CONCAT('%',#{orgName,jdbcType=VARCHAR},'%')"
    	+ "AND r.ROLE_NAME like CONCAT('%',#{roleName,jdbcType=VARCHAR},'%') LIMIT #{start},#{length}" 
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOME_URL", property="homeUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="BELONG_ORG", property="belongOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_NAME", property="orgName", jdbcType=JdbcType.VARCHAR)
    })
    List<SysRoleResult> selectListResult(SysRoleResult role);
    @Select({
    	"SELECT COUNT(ROLE_ID) FROM sys_role r, sys_org o WHERE o.ORG_ID = r.BELONG_ORG AND o.ORG_NAME like CONCAT('%',#{orgName,jdbcType=VARCHAR},'%')"
    	+ "AND r.ROLE_NAME like CONCAT('%',#{roleName,jdbcType=VARCHAR},'%')" 
    })
    int countListResult(SysRoleResult role);
    
    @Delete({
		"DELETE FROM sys_role WHERE ROLE_ID IN (${ids})"
    })
    int deleteByIds(@Param("ids")String ids);
}
