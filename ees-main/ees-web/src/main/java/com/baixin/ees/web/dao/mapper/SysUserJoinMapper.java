package com.baixin.ees.web.dao.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;

import com.baixin.ees.web.dao.model.SysUserResult;

public interface SysUserJoinMapper {

	@Select({
			"SELECT USER_ID,USER_NAME,LOGIN_NAME,SEX,u.ORG_ID,u.ROLE_ID,u.STATUS,u.EMAIL,u.RECEIVE_EMAIL,u.PHONE_NUMBER,u.RECEIVE_MESSAGE,u.REMARK,"
					+ "r.ROLE_NAME,o.ORG_NAME ,u.CREATEDATE,u.LASTUPDATETIME FROM `sys_user` u,sys_role r,sys_org o where "
					+ "u.ROLE_ID = r.ROLE_ID AND u.ORG_ID = o.ORG_ID AND u.USER_NAME like CONCAT('%',#{userName,jdbcType=VARCHAR},'%')"
					+ "AND u.LOGIN_NAME like CONCAT('%',#{loginName,jdbcType=VARCHAR},'%') AND o.ORG_NAME like CONCAT('%',#{orgName,jdbcType=VARCHAR},'%')"
					+ "AND r.ROLE_NAME like CONCAT('%',#{roleName,jdbcType=VARCHAR},'%') LIMIT #{start},#{length}" })
	@Results({ @Result(column = "USER_ID", property = "userId", jdbcType = JdbcType.VARCHAR, id = true),
			@Result(column = "USER_NAME", property = "userName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "LOGIN_NAME", property = "loginName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "PASSWORD", property = "password", jdbcType = JdbcType.VARCHAR),
			@Result(column = "SEX", property = "sex", jdbcType = JdbcType.VARCHAR),
			@Result(column = "ORG_ID", property = "orgId", jdbcType = JdbcType.VARCHAR),
			@Result(column = "ROLE_ID", property = "roleId", jdbcType = JdbcType.VARCHAR),
			@Result(column = "STATUS", property = "status", jdbcType = JdbcType.VARCHAR),
			@Result(column = "SORT_NO", property = "sortNo", jdbcType = JdbcType.INTEGER),
			@Result(column = "EMAIL", property = "email", jdbcType = JdbcType.INTEGER),
			@Result(column = "RECEIVE_EMAIL", property = "receiveEmail", jdbcType = JdbcType.INTEGER),
			@Result(column = "PHONE_NUMBER", property = "phoneNumber", jdbcType = JdbcType.INTEGER),
			@Result(column = "RECEIVE_MESSAGE", property = "receiveMessage", jdbcType = JdbcType.INTEGER),
			@Result(column = "REMARK", property = "remark", jdbcType = JdbcType.VARCHAR),
			@Result(column = "BELONG_ORG", property = "belongOrg", jdbcType = JdbcType.VARCHAR),
			@Result(column = "ROLE_NAME", property = "roleName", jdbcType = JdbcType.VARCHAR),
			@Result(column = "ORG_NAME", property = "orgName", jdbcType = JdbcType.VARCHAR),
			@Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
		    @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)})
	List<SysUserResult> selectListResult(SysUserResult user);

	@Select({ "SELECT COUNT(USER_ID) FROM `sys_user` u,sys_role r,sys_org o WHERE "
			+ "u.ROLE_ID = r.ROLE_ID AND u.ORG_ID = o.ORG_ID AND u.USER_NAME like CONCAT('%',#{userName,jdbcType=VARCHAR},'%')"
			+ "AND u.LOGIN_NAME like CONCAT('%',#{loginName,jdbcType=VARCHAR},'%') AND o.ORG_NAME like CONCAT('%',#{orgName,jdbcType=VARCHAR},'%')"
			+ "AND r.ROLE_NAME like CONCAT('%',#{roleName,jdbcType=VARCHAR},'%')" })
	int countListResult(SysUserResult user);

	@Delete({ "DELETE FROM sys_user WHERE USER_ID IN (${ids})" })
	int deleteByIds(@Param("ids") String ids);

	@Update({ "UPDATE sys_user SET PASSWORD=#{password}," + "LASTUPDATETIME= #{lastupdatetime,jdbcType=TIMESTAMP} "
			+ "WHERE USER_ID IN (${ids})"

	})
	int initPwd(@Param("password") String password, @Param("lastupdatetime") Date lastupdatetime,
			@Param("ids") String ids);

	@Update({ "UPDATE sys_user SET STATUS='1'," + "LASTUPDATETIME= #{lastupdatetime,jdbcType=TIMESTAMP} "
			+ "WHERE USER_ID IN (${ids})"

	})
	int upUserListStatus(@Param("lastupdatetime") Date lastupdatetime, @Param("ids") String ids);

	@Update({ "UPDATE sys_user SET STATUS='0'," + "LASTUPDATETIME= #{lastupdatetime,jdbcType=TIMESTAMP} "
			+ "WHERE USER_ID IN (${ids})"

	})
	int downUserListStatus(@Param("lastupdatetime") Date lastupdatetime, @Param("ids") String ids);
}
