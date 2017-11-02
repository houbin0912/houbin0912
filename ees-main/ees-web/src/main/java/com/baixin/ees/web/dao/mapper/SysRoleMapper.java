package com.baixin.ees.web.dao.mapper;

import com.baixin.ees.web.dao.model.SysRole;
import com.baixin.ees.web.dao.model.SysRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface SysRoleMapper {
    @SelectProvider(type=SysRoleSqlProvider.class, method="countByExample")
    int countByExample(SysRoleExample example);

    @DeleteProvider(type=SysRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysRoleExample example);

    @Delete({
        "delete from sys_role",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer roleId);

    @Insert({
        "insert into sys_role (ROLE_ID, ROLE_NAME, ",
        "HOME_URL, REMARK, ",
        "BELONG_ORG, CREATEDATE, ",
        "LASTUPDATETIME)",
        "values (#{roleId,jdbcType=INTEGER}, #{roleName,jdbcType=VARCHAR}, ",
        "#{homeUrl,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{belongOrg,jdbcType=INTEGER}, #{createdate,jdbcType=TIMESTAMP}, ",
        "#{lastupdatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysRole record);

    @InsertProvider(type=SysRoleSqlProvider.class, method="insertSelective")
    int insertSelective(SysRole record);

    @SelectProvider(type=SysRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOME_URL", property="homeUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="BELONG_ORG", property="belongOrg", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysRole> selectByExample(SysRoleExample example);

    @Select({
        "select",
        "ROLE_ID, ROLE_NAME, HOME_URL, REMARK, BELONG_ORG, CREATEDATE, LASTUPDATETIME",
        "from sys_role",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_NAME", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="HOME_URL", property="homeUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="BELONG_ORG", property="belongOrg", jdbcType=JdbcType.INTEGER),
        @Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysRole selectByPrimaryKey(Integer roleId);

    @UpdateProvider(type=SysRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    @UpdateProvider(type=SysRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysRole record, @Param("example") SysRoleExample example);

    @UpdateProvider(type=SysRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysRole record);

    @Update({
        "update sys_role",
        "set ROLE_NAME = #{roleName,jdbcType=VARCHAR},",
          "HOME_URL = #{homeUrl,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "BELONG_ORG = #{belongOrg,jdbcType=INTEGER},",
          "CREATEDATE = #{createdate,jdbcType=TIMESTAMP},",
          "LASTUPDATETIME = #{lastupdatetime,jdbcType=TIMESTAMP}",
        "where ROLE_ID = #{roleId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysRole record);
}