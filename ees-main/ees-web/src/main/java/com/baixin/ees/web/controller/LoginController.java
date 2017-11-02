package com.baixin.ees.web.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.util.UserUtils;

@Controller
@RequestMapping("/sys")
public class LoginController {
	
	@RequestMapping("/login")
	@ResponseBody
	public ResultMessage loginSystem(String loginName,String password,HttpServletRequest resquest){
		try {
			if(UserUtils.login(loginName, password, true, resquest.getRemoteHost())){
				return ResultMessage.isOk();
			}
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			return ResultMessage.isError("账户不存在");
		}catch(Exception e) {
			e.printStackTrace();
			return ResultMessage.isError("登陆失败");
		}
		return ResultMessage.isError("登陆失败");
	}
	
	
//	@RequestMapping("/logout")
//	public ResultMessage skipSuccess(){
//		if(UserUtils.logout()) {
//			return ResultMessage.isOk("/view/login.html");
//		}
//		return ResultMessage.isError("退出异常", "");
//	}

	@RequestMapping("/logout")
	public String skipSuccess(){
		if(UserUtils.logout()) {
			return "redirect:/view/login.html";
		}
		return "redirect:/view/login.html";
	}
}
