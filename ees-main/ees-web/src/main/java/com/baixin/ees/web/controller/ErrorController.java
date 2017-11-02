package com.baixin.ees.web.controller;


import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baixin.ees.util.MyException;
import com.baixin.ees.web.dao.model.SysUser;
import com.baixin.ees.web.service.UserService;

@RequestMapping("/error")
@Controller
public class ErrorController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/MyException")
	public String error(MyException ex){
		ex.printStackTrace();
		return "redirect:/view/500.html";
	}
	
	@RequestMapping("/testError")
	public void testError() throws MyException{
		throw new MyException(401,"error");
	}
	
	@RequestMapping("/testAddUser")
	public void testAddUser() throws SQLException {
		userService.add(new SysUser());
	}
}
