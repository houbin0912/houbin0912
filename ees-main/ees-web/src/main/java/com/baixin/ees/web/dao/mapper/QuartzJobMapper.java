package com.baixin.ees.web.dao.mapper;

import com.baixin.ees.web.dao.model.QuartzJob;
import com.baixin.ees.web.dao.model.QuartzJobExample;
import com.baixin.ees.web.dao.model.QuartzJobResult;

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

public interface QuartzJobMapper {
    @SelectProvider(type=QuartzJobSqlProvider.class, method="countByExample")
    int countByExample(QuartzJobExample example);

    @DeleteProvider(type=QuartzJobSqlProvider.class, method="deleteByExample")
    int deleteByExample(QuartzJobExample example);

    @Delete({
        "delete from quartz_job",
        "where JOB_ID = #{jobId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer jobId);

    @Insert({
        "insert into quartz_job (JOB_ID, JOB_NAME, ",
        "JOB_GROUP_NAME, JOB_TRIGGER_NAME, ",
        "JOB_TRIGGER_GROUP_NAME, JOB_IMPL_CLASS, ",
        "CRON_EXPRESSION, JOB_STATUS, ",
        "JOB_DESC, CREATEDATE, ",
        "LASTUPDATETIME)",
        "values (#{jobId,jdbcType=INTEGER}, #{jobName,jdbcType=VARCHAR}, ",
        "#{jobGroupName,jdbcType=VARCHAR}, #{jobTriggerName,jdbcType=VARCHAR}, ",
        "#{jobTriggerGroupName,jdbcType=VARCHAR}, #{jobImplClass,jdbcType=VARCHAR}, ",
        "#{cronExpression,jdbcType=VARCHAR}, #{jobStatus,jdbcType=VARCHAR}, ",
        "#{jobDesc,jdbcType=VARCHAR}, #{createdate,jdbcType=TIMESTAMP}, ",
        "#{lastupdatetime,jdbcType=TIMESTAMP})"
    })
    int insert(QuartzJob record);

    @InsertProvider(type=QuartzJobSqlProvider.class, method="insertSelective")
    int insertSelective(QuartzJob record);

    @SelectProvider(type=QuartzJobSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="JOB_ID", property="jobId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="JOB_NAME", property="jobName", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_GROUP_NAME", property="jobGroupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_TRIGGER_NAME", property="jobTriggerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_TRIGGER_GROUP_NAME", property="jobTriggerGroupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_IMPL_CLASS", property="jobImplClass", jdbcType=JdbcType.VARCHAR),
        @Result(column="CRON_EXPRESSION", property="cronExpression", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_STATUS", property="jobStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_DESC", property="jobDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<QuartzJobResult> selectByExample(QuartzJobExample example);

    @Select({
        "select",
        "JOB_ID, JOB_NAME, JOB_GROUP_NAME, JOB_TRIGGER_NAME, JOB_TRIGGER_GROUP_NAME, ",
        "JOB_IMPL_CLASS, CRON_EXPRESSION, JOB_STATUS, JOB_DESC, CREATEDATE, LASTUPDATETIME",
        "from quartz_job",
        "where JOB_ID = #{jobId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="JOB_ID", property="jobId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="JOB_NAME", property="jobName", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_GROUP_NAME", property="jobGroupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_TRIGGER_NAME", property="jobTriggerName", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_TRIGGER_GROUP_NAME", property="jobTriggerGroupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_IMPL_CLASS", property="jobImplClass", jdbcType=JdbcType.VARCHAR),
        @Result(column="CRON_EXPRESSION", property="cronExpression", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_STATUS", property="jobStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="JOB_DESC", property="jobDesc", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    QuartzJob selectByPrimaryKey(Integer jobId);

    @UpdateProvider(type=QuartzJobSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") QuartzJob record, @Param("example") QuartzJobExample example);

    @UpdateProvider(type=QuartzJobSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") QuartzJob record, @Param("example") QuartzJobExample example);

    @UpdateProvider(type=QuartzJobSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(QuartzJob record);

    @Update({
        "update quartz_job",
        "set JOB_NAME = #{jobName,jdbcType=VARCHAR},",
          "JOB_GROUP_NAME = #{jobGroupName,jdbcType=VARCHAR},",
          "JOB_TRIGGER_NAME = #{jobTriggerName,jdbcType=VARCHAR},",
          "JOB_TRIGGER_GROUP_NAME = #{jobTriggerGroupName,jdbcType=VARCHAR},",
          "JOB_IMPL_CLASS = #{jobImplClass,jdbcType=VARCHAR},",
          "CRON_EXPRESSION = #{cronExpression,jdbcType=VARCHAR},",
          "JOB_STATUS = #{jobStatus,jdbcType=VARCHAR},",
          "JOB_DESC = #{jobDesc,jdbcType=VARCHAR},",
          "CREATEDATE = #{createdate,jdbcType=TIMESTAMP},",
          "LASTUPDATETIME = #{lastupdatetime,jdbcType=TIMESTAMP}",
        "where JOB_ID = #{jobId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(QuartzJob record);
}