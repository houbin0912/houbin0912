package com.baixin.ees.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath:spring-context.xml", "classpath:spring-mvc.xml",
		"classpath:spring-context-shiro.xml", "classpath:mybatis-config.xml" })
/*
 * //这里可以声明一个事务管理 每个单元测试都进行事务回滚 无论成功与否
 * 
 * @TransactionConfiguration(defaultRollback = true)
 * 
 * @Transactional
 */
/**
 * Quartz controller 测试
 * 
 * @author zhangguoshuai
 *
 */
public class TestQuartzController {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/**
	 * 查询定时任务列表
	 * 
	 * @throws Exception
	 */
	@Test
	public void list() throws Exception {
		String responseString = mockMvc.perform(post("/sys/quartz/list") // 请求的url,请求的方法是post
				// get("/user/showUser2") //请求的url,请求的方法是get
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("start", "0") // 添加参数(可以添加多个)
				.param("length", "3") // 添加参数(可以添加多个)
				.param("draw", "1") // 添加参数(可以添加多个)
		).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 修改定时任务列表 1.注意要确保你修改的数据数据库中存在 2.定时任务禁用状态下才可以修改 否则会报异常
	 * 3.说明：启用的状态下，是不具有修改其他数据功能的，只有状态可修改
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateQuartzJob() throws Exception {
		String responseString = mockMvc.perform(post("/sys/quartz/updateQuartzJob") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("jobId", "1") // 添加参数(可以添加多个)
				.param("jobName", "error_quartz").param("jobGroupName", "acct")
				.param("jobTriggerName", "error_tarigger").param("jobTriggerGroupName", "acct_tarigger_group")
				.param("jobImplClass", "com.baixin.ees.quartz.MyJob").param("cronExpression", "0/3 * * * * ?")
				.param("jobStatus", "1").param("jobDesc", "测试报警定时任务修改1").param("start", "0").param("length", "3")
				.param("draw", "1")).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 查询定时任务状态
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateJobStatus() throws Exception {
		String responseString = mockMvc.perform(post("/sys/quartz/updateJobStatus") // 请求的url,请求的方法是post
				// get("/user/showUser2") //请求的url,请求的方法是get
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("jobId", "1") // 添加参数(可以添加多个)
				.param("jobStatus", "0")).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 添加定时任务
	 * 
	 * @throws Exception
	 */
	@Test
	public void addQuartzJob() throws Exception {
		String responseString = mockMvc.perform(post("/sys/quartz/addQuartzJob") // 请求的url,请求的方法是post
				// get("/user/showUser2") //请求的url,请求的方法是get
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("jobId", "2") // 添加参数(可以添加多个)
				.param("jobName", "error_quartz2").param("jobGroupName", "acct2")
				.param("jobTriggerName", "error_tarigger2").param("jobTriggerGroupName", "acct_tarigger_group2")
				.param("jobImplClass", "com.baixin.ees.quartz.MyJob").param("cronExpression", "0/8 * * * * ?")
				.param("jobStatus", "1").param("jobDesc", "测试报警定时任务修改3")).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 删除定时任务
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteQuartzJob() throws Exception {
		String responseString = mockMvc.perform(post("/sys/quartz/deleteQuartzJob") // 请求的url,请求的方法是post
				// get("/user/showUser2") //请求的url,请求的方法是get
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("jobId", "2") // 添加参数(可以添加多个)
				.param("jobName", "error_quartz2").param("jobGroupName", "acct2")
				.param("jobTriggerName", "error_tarigger2").param("jobTriggerGroupName", "acct_tarigger_group2")
				.param("jobImplClass", "com.baixin.ees.quartz.MyJob").param("cronExpression", "0/8 * * * * ?")
				.param("jobStatus", "1").param("jobDesc", "测试报警定时任务修改3")).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}
	
	/**
	 * 检测任务名称和任务组名唯一
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkJobNameAndGroup() throws Exception {
		String responseString = mockMvc.perform(post("/sys/quartz/checkJobNameAndGroup") // 请求的url,请求的方法是post
				// get("/user/showUser2") //请求的url,请求的方法是get
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("jobName", "error_quartz").param("jobGroupName", "acct")
				 ).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}
	
	/**
	 * 检测触发器名称和触发器组唯一
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkTriggerNameAndGroup() throws Exception {
		String responseString = mockMvc.perform(post("/sys/quartz/checkTriggerNameAndGroup") // 请求的url,请求的方法是post
				// get("/user/showUser2") //请求的url,请求的方法是get
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("jobTriggerName", "error_tarigger").param("jobTriggerGroupName", "acct_tarigger_group")
				 ).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}
}
