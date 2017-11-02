package com.baixin.ees.web.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

public interface SysOrgJoinMapper {

	@Delete({
		"DELETE FROM sys_org WHERE ORG_ID IN (${ids})"
    })
    int deleteByIds(@Param("ids")String ids);
}