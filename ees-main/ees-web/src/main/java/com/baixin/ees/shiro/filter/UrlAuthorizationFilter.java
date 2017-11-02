package com.baixin.ees.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

public class UrlAuthorizationFilter extends PermissionsAuthorizationFilter {
	@Override
	public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws IOException {
		String requestUrl = ((HttpServletRequest) request).getRequestURI();
		Subject subject = getSubject(request, response);
		if (subject.hasRole("admin")) {
			return true;
		}
		return subject.isPermitted(requestUrl);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
		Subject subject = getSubject(request, response);
		if (subject.getPrincipal() == null) {
			saveRequestAndRedirectToLogin(request, response);
		} else {
			String unauthorizedUrl = getUnauthorizedUrl();
			if (StringUtils.hasText(unauthorizedUrl)) {
				WebUtils.issueRedirect(request, response, unauthorizedUrl);
			} else {
				try {
					request.getRequestDispatcher("/view/page_403.html").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				}
				// WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}
		}
		return false;
	}

	// 判断当前的其实是不是一个HTTP的POST请求
	protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
		return (request instanceof HttpServletRequest)
				&& WebUtils.toHttp(request).getMethod().equalsIgnoreCase(POST_METHOD);
	}

}
