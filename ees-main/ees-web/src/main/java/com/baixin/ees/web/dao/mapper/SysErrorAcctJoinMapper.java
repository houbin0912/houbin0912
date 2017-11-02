package com.baixin.ees.web.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import com.baixin.ees.web.dao.model.AcctErrorResult;

public interface SysErrorAcctJoinMapper {

	 @Select({
	    	"SELECT ACCT_ERR_ID,CHANNEL_SEQ_NO,SYS_SEQ_NO,PROOF_TYPE,ORG_CODE,INFER_REASON,CREATE_TIME,STATUS,"
	    	+ "REPORT_TIME,PROCESS_RESULT,ERR_REASON,PROCESS_TIME FROM `acct_error` a WHERE "
	    	+ " SYS_SEQ_NO like CONCAT('%',#{sysSeqNo,jdbcType=VARCHAR},'%') AND CHANNEL_SEQ_NO like CONCAT"
	    	+ "('%',#{channelSeqNo,jdbcType=VARCHAR},'%') LIMIT #{start},#{length}"
	    })
	    @Results({
	        @Result(column="ACCT_ERR_ID", property="acctErrId", jdbcType=JdbcType.INTEGER, id=true),
	        @Result(column="CHANNEL_SEQ_NO", property="channelSeqNo", jdbcType=JdbcType.VARCHAR),
	        @Result(column="SYS_SEQ_NO", property="sysSeqNo", jdbcType=JdbcType.VARCHAR),
	        @Result(column="PROOF_TYPE", property="proofType", jdbcType=JdbcType.VARCHAR),
	        @Result(column="ORG_CODE", property="orgCode", jdbcType=JdbcType.VARCHAR),
	        @Result(column="INFER_REASON", property="inferReason", jdbcType=JdbcType.VARCHAR),
	        @Result(column="CREATE_TIME", property="createTime", jdbcType=JdbcType.VARCHAR),
	        @Result(column="STATUS", property="status", jdbcType=JdbcType.VARCHAR),
	        @Result(column="REPORT_TIME", property="reportTime", jdbcType=JdbcType.INTEGER),
	        @Result(column="PROCESS_RESULT", property="processResult", jdbcType=JdbcType.VARCHAR),
	        @Result(column="ERR_REASON", property="errReason", jdbcType=JdbcType.VARCHAR),
	        @Result(column="PROCESS_TIME", property="processTime", jdbcType=JdbcType.VARCHAR),
	    })
	    List<AcctErrorResult> selectListResult(AcctErrorResult acct);
	    
	    @Select({
			"SELECT COUNT(ACCT_ERR_ID),CHANNEL_SEQ_NO,SYS_SEQ_NO,PROOF_TYPE,ORG_CODE,INFER_REASON,CREATE_TIME,STATUS,"
			+ "REPORT_TIME,PROCESS_RESULT,ERR_REASON,PROCESS_TIME FROM `acct_error` a WHERE "
			+ " SYS_SEQ_NO like CONCAT('%',#{sysSeqNo,jdbcType=VARCHAR},'%') AND CHANNEL_SEQ_NO like CONCAT"
			+ "('%',#{channelSeqNo,jdbcType=VARCHAR},'%')"
	    })
	    int countListResult(AcctErrorResult acct);
}
