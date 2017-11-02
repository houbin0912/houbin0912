package com.baixin.ees.web.dao.mapper;

import com.baixin.ees.web.dao.model.SysErrorAcct;
import com.baixin.ees.web.dao.model.SysErrorAcctExample;
import com.baixin.ees.web.dao.model.SysErrorAcctResult;

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

public interface SysErrorAcctMapper {
    @SelectProvider(type=SysErrorAcctSqlProvider.class, method="countByExample")
    int countByExample(SysErrorAcctExample example);

    @DeleteProvider(type=SysErrorAcctSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysErrorAcctExample example);

    @Delete({
        "delete from acct_error",
        "where ACCT_ERR_ID = #{acctErrId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer acctErrId);

    @Insert({
        "insert into acct_error (ACCT_ERR_ID, CHANNEL_SEQ_NO, ",
        "SYS_SEQ_NO, PROOF_TYPE, ",
        "ORG_CODE, INFER_REASON, ",
        "CREATE_TIME, STATUS, ",
        "REPORT_TIME, PROCESS_RESULT, ",
        "ERR_REASON, PROCESS_TIME)",
        "values (#{acctErrId,jdbcType=INTEGER}, #{channelSeqNo,jdbcType=VARCHAR}, ",
        "#{sysSeqNo,jdbcType=VARCHAR}, #{proofType,jdbcType=VARCHAR}, ",
        "#{orgCode,jdbcType=VARCHAR}, #{inferReason,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{status,jdbcType=VARCHAR}, ",
        "#{reportTime,jdbcType=TIMESTAMP}, #{processResult,jdbcType=VARCHAR}, ",
        "#{errReason,jdbcType=VARCHAR}, #{processTime,jdbcType=TIMESTAMP})"
    })
    int insert(SysErrorAcct record);

    @InsertProvider(type=SysErrorAcctSqlProvider.class, method="insertSelective")
    int insertSelective(SysErrorAcct record);

    @SelectProvider(type=SysErrorAcctSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ACCT_ERR_ID", property="acctErrId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CHANNEL_SEQ_NO", property="channelSeqNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="SYS_SEQ_NO", property="sysSeqNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROOF_TYPE", property="proofType", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CODE", property="orgCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="INFER_REASON", property="inferReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="REPORT_TIME", property="reportTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="PROCESS_RESULT", property="processResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERR_REASON", property="errReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROCESS_TIME", property="processTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysErrorAcctResult> selectByExample(SysErrorAcctExample example);

    @Select({
        "select",
        "ACCT_ERR_ID, CHANNEL_SEQ_NO, SYS_SEQ_NO, PROOF_TYPE, ORG_CODE, INFER_REASON, ",
        "CREATE_TIME, STATUS, REPORT_TIME, PROCESS_RESULT, ERR_REASON, PROCESS_TIME",
        "from acct_error",
        "where ACCT_ERR_ID = #{acctErrId,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="ACCT_ERR_ID", property="acctErrId", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="CHANNEL_SEQ_NO", property="channelSeqNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="SYS_SEQ_NO", property="sysSeqNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROOF_TYPE", property="proofType", jdbcType=JdbcType.VARCHAR),
        @Result(column="ORG_CODE", property="orgCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="INFER_REASON", property="inferReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="STATUS", property="status", jdbcType=JdbcType.VARCHAR),
        @Result(column="REPORT_TIME", property="reportTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="PROCESS_RESULT", property="processResult", jdbcType=JdbcType.VARCHAR),
        @Result(column="ERR_REASON", property="errReason", jdbcType=JdbcType.VARCHAR),
        @Result(column="PROCESS_TIME", property="processTime", jdbcType=JdbcType.TIMESTAMP)
    })
    SysErrorAcct selectByPrimaryKey(Integer acctErrId);

    @UpdateProvider(type=SysErrorAcctSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysErrorAcct record, @Param("example") SysErrorAcctExample example);

    @UpdateProvider(type=SysErrorAcctSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysErrorAcct record, @Param("example") SysErrorAcctExample example);

    @UpdateProvider(type=SysErrorAcctSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysErrorAcct record);

    @Update({
        "update acct_error",
        "set CHANNEL_SEQ_NO = #{channelSeqNo,jdbcType=VARCHAR},",
          "SYS_SEQ_NO = #{sysSeqNo,jdbcType=VARCHAR},",
          "PROOF_TYPE = #{proofType,jdbcType=VARCHAR},",
          "ORG_CODE = #{orgCode,jdbcType=VARCHAR},",
          "INFER_REASON = #{inferReason,jdbcType=VARCHAR},",
          "CREATE_TIME = #{createTime,jdbcType=TIMESTAMP},",
          "STATUS = #{status,jdbcType=VARCHAR},",
          "REPORT_TIME = #{reportTime,jdbcType=TIMESTAMP},",
          "PROCESS_RESULT = #{processResult,jdbcType=VARCHAR},",
          "ERR_REASON = #{errReason,jdbcType=VARCHAR},",
          "PROCESS_TIME = #{processTime,jdbcType=TIMESTAMP}",
        "where ACCT_ERR_ID = #{acctErrId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysErrorAcct record);
}