package com.baixin.ees.web.listener;

import com.baixin.ees.util.HbaseUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;

public class TomcatListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		HbaseUtils.closeConn();
		System.out.println("tomcat关闭了.........."); 
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			HbaseUtils.initConn();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("hbase连接初始化失败");
		}
		System.out.println("tomcate启动了.............."); 
	}
	
}
