package com.baixin.ees.web.dao.mapper;

import com.baixin.ees.web.dao.model.AcctError;
import com.baixin.ees.web.dao.model.AcctErrorExample;
import com.baixin.ees.web.dao.model.AcctErrorResult;

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

public interface AcctErrorMapper {
    @SelectProvider(type=AcctErrorSqlProvider.class, method="countByExample")
    int countByExample(AcctErrorExample example);

    @DeleteProvider(type=AcctErrorSqlProvider.class, method="deleteByExample")
    int deleteByExample(AcctErrorExample example);

    @Delete({
        "delete from acct_error",
        "where ACCT_ERR_ID = #{acctErrId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String acctErrId);

    @Insert({
        "insert into acct_error (ACCT_ERR_ID, CHANNEL_SEQ_NO, ",
        "SYS_SEQ_NO, PROOF_TYPE, ",
        "ORG_CODE, INFER_REASON, ",
        "CREATE_TIME, STATUS, ",
        "REPORT_TIME, PROCESS_RESULT, ",
        "ERR_REASON, PROCESS_TIME)",
        "values (#{acctErrId,jdbcType=VARCHAR}, #{channelSeqNo,jdbcType=VARCHAR}, ",
        "#{sysSeqNo,jdbcType=VARCHAR}, #{proofType,jdbcType=VARCHAR}, ",
        "#{orgCode,jdbcType=VARCHAR}, #{inferReason,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{status,jdbcType=VARCHAR}, ",
        "#{reportTime,jdbcType=TIMESTAMP}, #{processResult,jdbcType=VARCHAR}, ",
        "#{errReason,jdbcType=VARCHAR}, #{processTime,jdbcType=TIMESTAMP})"
    })
    int insert(AcctError record);

    @InsertProvider(type=AcctErrorSqlProvider.class, method="insertSelective")
    int insertSelective(AcctError record);

    @SelectProvider(type=AcctErrorSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="ACCT_ERR_ID", property="acctErrId", jdbcType=JdbcType.VARCHAR, id=true),
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
    List<AcctErrorResult> selectByExample(AcctErrorExample example);

    @Select({
        "select",
        "ACCT_ERR_ID, CHANNEL_SEQ_NO, SYS_SEQ_NO, PROOF_TYPE, ORG_CODE, INFER_REASON, ",
        "CREATE_TIME, STATUS, REPORT_TIME, PROCESS_RESULT, ERR_REASON, PROCESS_TIME",
        "from acct_error",
        "where ACCT_ERR_ID = #{acctErrId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="ACCT_ERR_ID", property="acctErrId", jdbcType=JdbcType.VARCHAR, id=true),
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
    AcctError selectByPrimaryKey(String acctErrId);

    @UpdateProvider(type=AcctErrorSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AcctError record, @Param("example") AcctErrorExample example);

    @UpdateProvider(type=AcctErrorSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AcctError record, @Param("example") AcctErrorExample example);

    @UpdateProvider(type=AcctErrorSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AcctError record);

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
        "where ACCT_ERR_ID = #{acctErrId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(AcctError record);
}