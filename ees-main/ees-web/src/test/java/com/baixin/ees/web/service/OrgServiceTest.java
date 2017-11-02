package com.baixin.ees.web.service;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.baixin.ees.util.MyException;
import com.baixin.ees.web.dao.model.SysOrg;

import junit.framework.TestCase;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring-context.xml", "classpath*:spring-mvc.xml" })
//@Transactional
//@TransactionConfiguration(defaultRollback = false)
public class OrgServiceTest {

	//private static Logger LOGGER = LoggerFactory.getLogger(OrgServiceTest.class);


	@Autowired
	public OrgService orgService;

	// @Repeat(30)
	@Test
	public void testAdd() {

		SysOrg org = new SysOrg();
		org.setOrgName("testName");
		org.setOrgCode("tst");
		org.setCreatedate(new Date());
		org.setLastupdatetime(new Date());
		TestCase.assertEquals(true,  orgService.add(org).isSuccess());
	}
	
	@Test
	public void testUpdate() {

		SysOrg org = new SysOrg();
		org.setOrgId(12);
		org.setOrgName("testName--");
	
		org.setLastupdatetime(new Date());
		TestCase.assertEquals(true,  orgService.update(org).isSuccess());
	}
	
	@Test
	public void testDel() {

		SysOrg org = new SysOrg();
		org.setOrgId(11);
		
		TestCase.assertEquals(true,  orgService.delete(org).isSuccess());
	}
	
	@Test
	public void testDelSysOrgListById() {
		String orgIds = "12,13";
		TestCase.assertEquals(true,  orgService.deleteListById(orgIds).isSuccess());
	}
	
	
	
	
	@Test
	public void testQueryByOrg() throws MyException {
		SysOrg org = new SysOrg();
		org.setOrgName("23");
		org.setStart(0);
		org.setLength(10);
		org.setDraw(10);
		
		TestCase.assertTrue(orgService.queryListByOrg(org).getData().size()>0);
	}
	
	
	@Test
	public void testQueryAllOrg() {
		
		TestCase.assertTrue(orgService.selectAllOrg().size()>0);
	}
	
	

}