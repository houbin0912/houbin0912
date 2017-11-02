package com.baixin.ees.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.web.dao.model.Page;
import com.baixin.ees.web.dao.model.QuartzJob;
import com.baixin.ees.web.dao.model.QuartzJobResult;
import com.baixin.ees.web.service.QuartzService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring-context.xml", "classpath:spring-mvc.xml",
		"classpath:spring-context-shiro.xml", "classpath:mybatis-config.xml" })
/**
 * Quartz Service 测试
 * 
 * @author zhangguoshuai
 *
 */
public class TestQuartzService {

	@Autowired
	private QuartzService quartzService;

	/**
	 * 查询列表
	 * 
	 * @throws Exception
	 */
	@Test
	public void list() throws Exception {
		QuartzJobResult quartzJob = new QuartzJobResult();
		quartzJob.setStart(0);
		quartzJob.setLength(3);
		quartzJob.setDraw(1);
		Page<QuartzJobResult> selectListResult = quartzService.quaryQuartzJob(quartzJob);
		System.out.println(JSONObject.toJSON(selectListResult));
	}

	/**
	 * 添加定时任务
	 * 
	 * @throws Exception
	 */
	@Test
	public void addQuartz() throws Exception {
		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setJobName("error_quartz4");
		quartzJob.setJobGroupName("acct4");
		quartzJob.setJobTriggerGroupName("acct_tarigger_group4");
		quartzJob.setJobTriggerName("error_tarigger4");
		quartzJob.setCronExpression("* 0/1 * * * ?");
		quartzJob.setJobDesc("测试报警定时任务修改4");
		quartzJob.setJobImplClass("com.baixin.ees.quartz.AlarmQuartzJobForMail");
		quartzJob.setJobStatus("1");
		ResultMessage addRole = quartzService.addQuartzJob(quartzJob);
		System.out.println(JSONObject.toJSON(addRole));
	}

	/**
	 * 修改定时任务
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateQuartz() throws Exception {
		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setJobId(4);
		quartzJob.setJobName("error_quartz");
		quartzJob.setJobGroupName("acct");
		quartzJob.setJobTriggerGroupName("acct_tarigger_group");
		quartzJob.setJobTriggerName("error_tarigger");
		quartzJob.setCronExpression("0/3 * * * * ?");
		quartzJob.setJobDesc("测试报警update");
		quartzJob.setJobImplClass("com.baixin.ees.quartz.MyJob");
		quartzJob.setJobStatus("1");
		ResultMessage updateRole = quartzService.updateQuartzJob(quartzJob);
		System.out.println(JSONObject.toJSON(updateRole));
	}

	/**
	 * 启用禁用
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateQuartzStatus() throws Exception {
		Integer jobId = 4;
		String jobStatus = "0";
		ResultMessage updateRole = quartzService.updateJobStatus(jobId, jobStatus);
		System.out.println(JSONObject.toJSON(updateRole));
	}

	/**
	 * 删除定时任务
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteByIdsQuartz() throws Exception {
		QuartzJob quartzJob = new QuartzJob();
		quartzJob.setJobId(1);
		quartzJob.setJobName("error_quartz");
		quartzJob.setJobGroupName("acct");
		quartzJob.setJobTriggerGroupName("acct_tarigger_group");
		quartzJob.setJobTriggerName("error_tarigger");
		quartzJob.setCronExpression("0/3 * * * * ?");
		quartzJob.setJobDesc("测试报警1");
		quartzJob.setJobImplClass("com.baixin.ees.quartz.MyJob");
		quartzJob.setJobStatus("1");
		ResultMessage deleteListById = quartzService.deleteQuartzJob(quartzJob);
		System.out.println(JSONObject.toJSON(deleteListById));
	}
	
	/**
	 * 检测任务名称和任务组名唯一
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkJobNameAndGroup() throws Exception {
		QuartzJobResult quartzJob = new QuartzJobResult();
		quartzJob.setJobName("error_quartz");
		quartzJob.setJobGroupName("acct");
		ResultMessage deleteListById = quartzService.checkJobNameAndGroup(quartzJob);
		System.out.println(JSONObject.toJSON(deleteListById));
	}
	
	/**
	 * 检测触发器名称和触发器组唯一
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkTriggerNameAndGroup() throws Exception {
		QuartzJobResult quartzJob = new QuartzJobResult();
		quartzJob.setJobTriggerGroupName("acct_tarigger_group");
		quartzJob.setJobTriggerName("error_tarigger");
		ResultMessage deleteListById = quartzService.checkTriggerNameAndGroup(quartzJob);
		System.out.println(JSONObject.toJSON(deleteListById));
	}
}
