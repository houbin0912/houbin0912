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
/**
 * Acct controller 测试
 * 
 * @author zhangguoshuai
 *
 */
public class TestAcctController {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	/**
	 * 查询列表
	 * 
	 * @throws Exception
	 */
	@Test
	public void list() throws Exception {
		String responseString = mockMvc.perform(post("/sys/acct/list") // 请求的url,请求的方法是post
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
	 * 修改错账 processResult 0 是错账 1不是错账 errReason 错账原因
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateAcct() throws Exception {
		String responseString = mockMvc.perform(post("/sys/acct/update") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("acctErrId", "7") // 添加参数(可以添加多个)
				.param("processResult", "1") // 添加参数(可以添加多个)
				.param("errReason", "不是错账")).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}
}
