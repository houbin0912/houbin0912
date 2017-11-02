package com.baixin.ees.web.dao.mapper;

import com.baixin.ees.web.dao.model.SysMenu;
import com.baixin.ees.web.dao.model.SysMenuExample;
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

public interface SysMenuMapper {
    @SelectProvider(type=SysMenuSqlProvider.class, method="countByExample")
    int countByExample(SysMenuExample example);

    @DeleteProvider(type=SysMenuSqlProvider.class, method="deleteByExample")
    int deleteByExample(SysMenuExample example);

    @Delete({
        "delete from sys_menu",
        "where MENU_ID = #{menuId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer menuId);

    @Insert({
        "insert into sys_menu (MENU_ID, MENU_NAME, ",
        "PARENT_MENU_ID, MENU_URL, ",
        "URL_TYPE, REMARK, ",
        "CREATEDATE, LASTUPDATETIME)",
        "values (#{menuId,jdbcType=INTEGER}, #{menuName,jdbcType=VARCHAR}, ",
        "#{parentMenuId,jdbcType=INTEGER}, #{menuUrl,jdbcType=VARCHAR}, ",
        "#{urlType,jdbcType=VARCHAR}, #{remark,jdbcType=VARCHAR}, ",
        "#{createdate,jdbcType=TIMESTAMP}, #{lastupdatetime,jdbcType=TIMESTAMP})"
    })
    int insert(SysMenu record);

    @InsertProvider(type=SysMenuSqlProvider.class, method="insertSelective")
    int insertSelective(SysMenu record);

    @SelectProvider(type=SysMenuSqlProvider.class, method="selectByExample")
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
    List<SysMenu> selectByExample(SysMenuExample example);

    @Select({
        "select",
        "MENU_ID, MENU_NAME, PARENT_MENU_ID, MENU_URL, URL_TYPE, REMARK, CREATEDATE, ",
        "LASTUPDATETIME",
        "from sys_menu",
        "where MENU_ID = #{menuId,jdbcType=INTEGER}"
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
    SysMenu selectByPrimaryKey(Integer menuId);

    @UpdateProvider(type=SysMenuSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    @UpdateProvider(type=SysMenuSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") SysMenu record, @Param("example") SysMenuExample example);

    @UpdateProvider(type=SysMenuSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(SysMenu record);

    @Update({
        "update sys_menu",
        "set MENU_NAME = #{menuName,jdbcType=VARCHAR},",
          "PARENT_MENU_ID = #{parentMenuId,jdbcType=INTEGER},",
          "MENU_URL = #{menuUrl,jdbcType=VARCHAR},",
          "URL_TYPE = #{urlType,jdbcType=VARCHAR},",
          "REMARK = #{remark,jdbcType=VARCHAR},",
          "CREATEDATE = #{createdate,jdbcType=TIMESTAMP},",
          "LASTUPDATETIME = #{lastupdatetime,jdbcType=TIMESTAMP}",
        "where MENU_ID = #{menuId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(SysMenu record);
}