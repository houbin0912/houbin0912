package com.baixin.ees.util;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 日期转换工具类
 * @author zhangguoshuai
 *
 */
public class DateUtils {
	
	 public static String date(Date date) {
    	SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
    }
}
