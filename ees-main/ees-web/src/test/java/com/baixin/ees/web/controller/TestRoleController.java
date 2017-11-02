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
 * Role controller 测试
 * 
 * @author zhangguoshuai
 *
 */
public class TestRoleController {
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
		String responseString = mockMvc.perform(post("/sys/role/list") // 请求的url,请求的方法是post
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
	 * 角色名唯一
	 * 
	 * @throws Exception
	 */
	@Test
	public void checkRoleName() throws Exception {
		String responseString = mockMvc
				.perform(post("/sys/role/check/roleName").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("roleName", "q11"))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 添加角色
	 * 
	 * @throws Exception
	 */
	@Test
	public void addRole() throws Exception {
		String responseString = mockMvc
				.perform(post("/sys/role/add").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("roleName", "q2").param("homeUrl", "q1").param("remark", "qwqw").param("belongOrg", "2"))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 修改角色
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateRole() throws Exception {
		String responseString = mockMvc
				.perform(post("/sys/role/update").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("roleName", "q2").param("homeUrl", "q1").param("remark", "修改数据").param("roleId", "5")
						.param("belongOrg", "1"))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 删除角色
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteByIdsRole() throws Exception {
		String responseString = mockMvc
				.perform(post("/sys/role/deleteByIds").contentType(MediaType.APPLICATION_FORM_URLENCODED)
						.param("roleIds", "5"))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
		System.out.println("-----返回的json = " + responseString);
	}

	/**
	 * 查询角色
	 * 
	 * @throws Exception
	 */
	@Test
	public void selectRoles() throws Exception {
		String responseString = mockMvc.perform(
				post("/sys/role/selectRoles").contentType(MediaType.APPLICATION_FORM_URLENCODED).param("orgId", "1"))
				.andExpect(status().isOk()).andDo(print()).andReturn().getResponse().getContentAsString();
		System.out.println("-----返回的json = " + responseString);
	}
}
