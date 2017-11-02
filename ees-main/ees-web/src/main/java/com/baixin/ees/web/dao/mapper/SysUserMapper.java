package com.baixin.ees.web.dao.mapper;

import com.baixin.ees.web.dao.model.SysUser;
import com.baixin.ees.web.dao.model.SysUserExample;
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

public interface SysUserMapper {
    @SelectProvider(type=SysUserSqlProvider.class, method="countByExample")
    int countByExample(SysUserExample example);

    @DeleteProvider(type=SysUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysUserExample example);

    @Delete({
        "delete from sys_user",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer userId);

    @Insert({
        "insert into sys_user (USER_ID, USER_NAME, ",
        "LOGIN_NAME, PASSWORD, ",
        "SEX, ORG_ID, ROLE_ID, ",
        "STATUS, EMAIL, RECEIVE_EMAIL, ",
        "PHONE_NUMBER, RECEIVE_MESSAGE, ",
        "REMARK, CREATEDATE, ",
        "LASTUPDATETIME)",
        "values (#{userId,jdbcType=INTEGER}, #{userName,jdbcType=VARCHAR}, ",
        "#{loginName,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=VARCHAR}, #{orgId,jdbcType=INTEGER}, #{roleId,jdbcType=INTEGER}, ",
        "#{status,jdbcType=VARCHAR}, #{email,jdbcType=VARCHAR}, #{receiveEmail,jdbcType=VARCHAR}, ",
        "#{phoneNumber,jdbcType=VARCHAR}, #{receiveMessage,jdbcType=VARCHAR}, ",
        "#{remark,jdbcType=VARCHAR}, #{createdate,jdbcType=TIMESTAMP}, ",
        "#{lastupdatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysUser record);

    @InsertProvider(type=SysUserSqlProvider.class, method="insertSelective")
    int insertSelective(SysUser record);

    @SelectProvider(type=SysUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOGIN_NAME", property="loginName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEX", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_ID", property="orgId", jdbcType=JdbcType.INTEGER),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECEIVE_EMAIL", property="receiveEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE_NUMBER", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECEIVE_MESSAGE", property="receiveMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysUser> selectByExample(SysUserExample example);

    @Select({
        "select",
        "USER_ID, USER_NAME, LOGIN_NAME, PASSWORD, SEX, ORG_ID, ROLE_ID, STATUS, EMAIL, ",
        "RECEIVE_EMAIL, PHONE_NUMBER, RECEIVE_MESSAGE, REMARK, CREATEDATE, LASTUPDATETIME",
        "from sys_user",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="USER_ID", property="userId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="USER_NAME", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="LOGIN_NAME", property="loginName", jdbcType=JdbcType.VARCHAR),
        @Result(column="PASSWORD", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="SEX", property="sex", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_ID", property="orgId", jdbcType=JdbcType.INTEGER),
        @Result(column="ROLE_ID", property="roleId", jdbcType=JdbcType.INTEGER),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="EMAIL", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECEIVE_EMAIL", property="receiveEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="PHONE_NUMBER", property="phoneNumber", jdbcType=JdbcType.VARCHAR),
        @Result(column="RECEIVE_MESSAGE", property="receiveMessage", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysUser selectByPrimaryKey(Integer userId);

    @UpdateProvider(type=SysUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysUser record, @Param("example") SysUserExample example);

    @UpdateProvider(type=SysUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysUser record, @Param("example") SysUserExample example);

    @UpdateProvider(type=SysUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysUser record);

    @Update({
        "update sys_user",
        "set USER_NAME = #{userName,jdbcType=VARCHAR},",
          "LOGIN_NAME = #{loginName,jdbcType=VARCHAR},",
          "PASSWORD = #{password,jdbcType=VARCHAR},",
          "SEX = #{sex,jdbcType=VARCHAR},",
          "ORG_ID = #{orgId,jdbcType=INTEGER},",
          "ROLE_ID = #{roleId,jdbcType=INTEGER},",
          "STATUS = #{status,jdbcType=VARCHAR},",
          "EMAIL = #{email,jdbcType=VARCHAR},",
          "RECEIVE_EMAIL = #{receiveEmail,jdbcType=VARCHAR},",
          "PHONE_NUMBER = #{phoneNumber,jdbcType=VARCHAR},",
          "RECEIVE_MESSAGE = #{receiveMessage,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "CREATEDATE = #{createdate,jdbcType=TIMESTAMP},",
          "LASTUPDATETIME = #{lastupdatetime,jdbcType=TIMESTAMP}",
        "where USER_ID = #{userId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysUser record);
}