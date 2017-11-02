package com.baixin.ees.web.dao.mapper;

import com.baixin.ees.web.dao.model.SysAuth;
import com.baixin.ees.web.dao.model.SysAuthExample;
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

public interface SysAuthMapper {
    @SelectProvider(type=SysAuthSqlProvider.class, method="countByExample")
    int countByExample(SysAuthExample example);

    @DeleteProvider(type=SysAuthSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysAuthExample example);

    @Delete({
        "delete from sys_auth",
        "where AUTH_ID = #{authId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer authId);

    @Insert({
        "insert into sys_auth (AUTH_ID, ROLE_ID, ",
        "MENU_IDS, EN_POWER_TIME)",
        "values (#{authId,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER}, ",
        "#{menuIds,jdbcType=VARCHAR}, #{enPowerTime,jdbcType=TIMESTAMP})"
    })
    int insert(SysAuth record);

    @InsertProvider(type=SysAuthSqlProvider.class, method="insertSelective")
    int insertSelective(SysAuth record);

    @SelectProvider(type=SysAuthSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="AUTH_ID", property="authId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="MENU_IDS", property="menuIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="EN_POWER_TIME", property="enPowerTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysAuth> selectByExample(SysAuthExample example);

    @Select({
        "select",
        "AUTH_ID, ROLE_ID, MENU_IDS, EN_POWER_TIME",
        "from sys_auth",
        "where AUTH_ID = #{authId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="AUTH_ID", property="authId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="MENU_IDS", property="menuIds", jdbcType=JdbcType.VARCHAR),
        @Result(column="EN_POWER_TIME", property="enPowerTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysAuth selectByPrimaryKey(Integer authId);

    @UpdateProvider(type=SysAuthSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysAuth record, @Param("example") SysAuthExample example);

    @UpdateProvider(type=SysAuthSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysAuth record, @Param("example") SysAuthExample example);

    @UpdateProvider(type=SysAuthSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysAuth record);

    @Update({
        "update sys_auth",
        "set ROLE_ID = #{roleId,jdbcType=INTEGER},",
          "MENU_IDS = #{menuIds,jdbcType=VARCHAR},",
          "EN_POWER_TIME = #{enPowerTime,jdbcType=TIMESTAMP}",
        "where AUTH_ID = #{authId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysAuth record);
}