package com.baixin.ees.web.dao.mapper;

import com.baixin.ees.web.dao.model.SysMenu;
import com.baixin.ees.web.dao.model.SysMenuResult;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;

public interface SysMenuJoinMapper {

	@Select({
		
		"SELECT distinct m.MENU_ID,m.MENU_NAME,m2.MENU_NAME PARENT_NAME, m.PARENT_MENU_ID ,m.MENU_URL,"
		+ "m.URL_TYPE,m.REMARK,m.CREATEDATE,m.LASTUPDATETIME FROM `sys_menu` m ,`sys_menu` m2 "
		+ "WHERE m.PARENT_MENU_ID = m2.MENU_ID AND m.MENU_ID > 0 "
		+ "AND m.MENU_NAME like CONCAT('%',#{menuName,jdbcType=VARCHAR},'%') "
		+ "AND m2.MENU_NAME like CONCAT('%',#{parentName,jdbcType=VARCHAR},'%') "
		+ " LIMIT #{start},#{length}"
    })
    @Results({
    	 @Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER, id=true),
         @Result(column="MENU_NAME", property="menuName", jdbcType=JdbcType.VARCHAR),
         @Result(column="PARENT_NAME", property="parentName", jdbcType=JdbcType.VARCHAR),
         @Result(column="PARENT_MENU_ID", property="parentMenuId", jdbcType=JdbcType.INTEGER),
         @Result(column="MENU_URL", property="menuUrl", jdbcType=JdbcType.VARCHAR),
         @Result(column="URL_TYPE", property="urlType", jdbcType=JdbcType.VARCHAR),
         @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
         @Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
         @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysMenuResult> selectListResult(SysMenuResult menu);
	@Select({
		"SELECT COUNT(m.MENU_ID) FROM `sys_menu` m ,`sys_menu` m2 "
		+ "WHERE m.PARENT_MENU_ID = m2.MENU_ID AND m.MENU_ID > 0 "
		+ " AND m.MENU_NAME like CONCAT('%',#{menuName,jdbcType=VARCHAR},'%')"
		+ " AND m2.MENU_NAME like CONCAT('%',#{parentName,jdbcType=VARCHAR},'%') "
    })
    int countListResult(SysMenuResult menu);
	
	@Select({
		"SELECT * FROM sys_menu m WHERE m.MENU_ID IN (${ids})"
    })
    @Results({
    	 @Result(column="MENU_ID", property="menuId", jdbcType=JdbcType.INTEGER, id=true),
         @Result(column="MENU_NAME", property="menuName", jdbcType=JdbcType.VARCHAR),
         @Result(column="PARENT_MENU_ID", property="parentMenuId", jdbcType=JdbcType.INTEGER),
         @Result(column="MENU_URL", property="menuUrl", jdbcType=JdbcType.VARCHAR),
         @Result(column="URL_TYPE", property="urlType", jdbcType=JdbcType.VARCHAR),
         @Result(column="REMARK", property="remark", jdbcType=JdbcType.VARCHAR),
         @Result(column="CREATEDATE", property="createdate", jdbcType=JdbcType.TIMESTAMP),
         @Result(column="LASTUPDATETIME", property="lastupdatetime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<SysMenu> selectMenuByIds(@Param("ids")String ids);
	
	@Select({
		"SELECT m.MENU_URL FROM sys_menu m WHERE m.MENU_URL != '' and m.MENU_ID IN (${ids})"
    })
    List<String> selectMenuUrlByIds(@Param("ids")String ids);
	
	@Delete({
		"DELETE FROM sys_menu WHERE MENU_ID IN (${ids})"
    })
    int deleteByIds(@Param("ids")String ids);
}