package com.baixin.ees.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

public class MyFormAuthenticationFilter extends FormAuthenticationFilter {


	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
			ServletResponse response) throws Exception {
		WebUtils.getAndClearSavedRequest(request);
		System.out.println("执行onsuccess");
//		WebUtils.redirectToSavedRequest(request, response, "/view/user_tables.html");
		return false;
	}

	
}
