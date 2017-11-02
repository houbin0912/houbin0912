package com.baixin.ees.web.dao.mapper;

import com.baixin.ees.web.dao.model.SysOrg;
import com.baixin.ees.web.dao.model.SysOrgExample;
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

public interface SysOrgMapper {
    @SelectProvider(type=SysOrgSqlProvider.class, method="countByExample")
    int countByExample(SysOrgExample example);

    @DeleteProvider(type=SysOrgSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysOrgExample example);

    @Delete({
        "delete from sys_org",
        "where ORG_ID = #{orgId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer orgId);

    @Insert({
        "insert into sys_org (ORG_ID, ORG_NAME, ",
        "ORG_CODE, REMARK, ",
        "CREATEDATE, LASTUPDATETIME)",
        "values (#{orgId,jdbcType=INTEGER}, #{orgName,jdbcType=VARCHAR}, ",
        "#{orgCode,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{createdate,jdbcType=TIMESTAMP}, #{lastupdatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysOrg record);

    @InsertProvider(type=SysOrgSqlProvider.class, method="insertSelective")
    int insertSelective(SysOrg record);

    @SelectProvider(type=SysOrgSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ORG_ID", property="orgId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ORG_NAME", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CODE", property="orgCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysOrg> selectByExample(SysOrgExample example);

    @Select({
        "select",
        "ORG_ID, ORG_NAME, ORG_CODE, REMARK, CREATEDATE, LASTUPDATETIME",
        "from sys_org",
        "where ORG_ID = #{orgId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ORG_ID", property="orgId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="ORG_NAME", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CODE", property="orgCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysOrg selectByPrimaryKey(Integer orgId);

    @UpdateProvider(type=SysOrgSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    @UpdateProvider(type=SysOrgSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysOrg record, @Param("example") SysOrgExample example);

    @UpdateProvider(type=SysOrgSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysOrg record);

    @Update({
        "update sys_org",
        "set ORG_NAME = #{orgName,jdbcType=VARCHAR},",
          "ORG_CODE = #{orgCode,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "CREATEDATE = #{createdate,jdbcType=TIMESTAMP},",
          "LASTUPDATETIME = #{lastupdatetime,jdbcType=TIMESTAMP}",
        "where ORG_ID = #{orgId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysOrg record);
}