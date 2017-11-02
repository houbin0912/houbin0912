package com.baixin.ees.web.service;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.baixin.ees.util.MyException;
import com.baixin.ees.web.dao.model.SysMenu;
import com.baixin.ees.web.dao.model.SysMenuResult;

import junit.framework.TestCase;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context.xml", "classpath*:spring-mvc.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class MenuServiceTest {

	//private static Logger LOGGER = LoggerFactory.getLogger(MenuServiceTest.class);


	@Autowired
	public MenuService mnuService;

	// @Repeat(30)
	@Test
	public void testAddSysMenu() {

		SysMenu menu = new SysMenu();
		menu.setMenuName("test");
		menu.setMenuUrl("/test/t");
		menu.setParentMenuId(0);
		menu.setCreatedate(new Date());
		menu.setLastupdatetime(new Date());
		// menu.setUrlType(0);
		TestCase.assertEquals(true, mnuService.add(menu).isSuccess());
		//LOGGER.debug("" + mnuService.add(menu).isSuccess());

	}

	@Test
	public void testUpdateSysMenu() {

		SysMenu menu = new SysMenu();

		menu.setMenuId(99);
		menu.setMenuName("test-modify");
		menu.setLastupdatetime(new Date());
		TestCase.assertEquals(true,  mnuService.update(menu).isSuccess());

	}
	
	@Test
	public void testDelSysMenu() {

		SysMenu menu = new SysMenu();
		menu.setMenuId(99);
		TestCase.assertEquals(true,  mnuService.delete(menu).isSuccess());

	}
	
	
	@Test
	public void testDelSysMenuListById() {
		String orgIds = "99,101";
		TestCase.assertEquals(true,  mnuService.deleteListById(orgIds).isSuccess());
	}
	
	
	
	
	@Test
	public void testQueryListByMenu() throws MyException {
		SysMenuResult sysMenu = new SysMenuResult();
		
		sysMenu.setStart(0);
		sysMenu.setDraw(10);
		sysMenu.setLength(10);

		sysMenu.setMenuName("用户管理");
		
		//LOGGER.info(""+mnuService.queryListByMenu(sysMenu));
		
		TestCase.assertEquals(true,  mnuService.queryListByMenu(sysMenu).data.size()>0);
	}

}