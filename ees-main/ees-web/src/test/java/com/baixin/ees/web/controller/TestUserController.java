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
 * User controller 测试
 * 
 * @author zhangguoshuai
 *
 */
public class TestUserController {
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
		String responseString = mockMvc.perform(post("/sys/user/list") // 请求的url,请求的方法是post
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
	 * 登录名唯一
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkLoginName() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/check/loginName") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("loginName", "admin") // 添加参数(可以添加多个)
		).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 注册邮箱唯一
	 *
	 * @throws Exception
	 */
	@Test
	public void checkMail() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/check/mail") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("mail", "446977790@qq.com") // 添加参数(可以添加多个)
		).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 注册电话号
	 *
	 * @throws Exception
	 */
	@Test
	public void checkPhoneNumber() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/check/phoneNumber") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("phoneNumber", "13810014563") // 添加参数(可以添加多个)
		).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 添加用户
	 * 
	 * @throws Exception
	 */
	/*@Test
	public void addUser() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/add") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("userName", "测试") // 添加参数(可以添加多个)
				.param("loginName", "test").param("sex", "1").param("orgId", "1").param("roleId", "1")
				.param("status", "1").param("remark", "测试数据").param("mail","446977790@qq.com").param("autoSendMail","0").param("phoneNumber","13810014563").param("autoSendPhoneMessage","0")).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}*/

	/**
	 * 修改用户
	 * 
	 * @throws Exception
	 */
	/*@Test
	public void updateUser() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/update") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("userId", "9") // 添加参数(可以添加多个)
				.param("userName", "测试2") // 添加参数(可以添加多个)
				.param("loginName", "test").param("sex", "1").param("orgId", "1").param("roleId", "1")
				.param("status", "1").param("remark", "测试数据").param("mail","446977790@qq.com").param("autoSendMail","1").param("phoneNumber","13810014563").param("autoSendPhoneMessage","1")).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}*/

	/**
	 * 删除用户
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteByIdsUser() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/deleteByIds") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("userIds", "9") // 添加参数(可以添加多个)
		).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 批量启用用户状态 1：是启用
	 * 
	 * @throws Exception
	 */
	@Test
	public void upStatusUser() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/upStatus") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("userIds", "5,6,7,8") // 添加参数(可以添加多个)
		).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 批量禁用用户状态 0：是禁用
	 * 
	 * @throws Exception
	 */
	@Test
	public void downStatusUser() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/downStatus") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("userIds", "5,6,7,8") // 添加参数(可以添加多个)
		).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 初始化密码
	 * 
	 * @throws Exception
	 */
	@Test
	public void initPwdUser() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/initPwd") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("userIds", "5") // 添加参数(可以添加多个)
		).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 修改密码
	 * 
	 * @throws Exception
	 */
	@Test
	public void updatePwdUser() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/updatePwd") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("userId", "5") // 添加参数(可以添加多个)
				.param("password", "测试") // 添加参数(可以添加多个)
		).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 用户菜单 登录状态下可测
	 * 
	 * @throws Exception
	 */
	@Test
	public void menuUser() throws Exception {
		String responseString = mockMvc.perform(post("/sys/user/menu") // 请求的url,请求的方法是post
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)// 数据的格式
				.param("userId", "5") // 添加参数(可以添加多个)
				.param("password", "测试") // 添加参数(可以添加多个)
		).andExpect(status().isOk()) // 返回的状态是200
				.andDo(print()) // 打印出请求和相应的内容
				.andReturn().getResponse().getContentAsString(); // 将相应的数据转换为字符串
		System.out.println("-----返回的json = " + responseString);
	}

}
