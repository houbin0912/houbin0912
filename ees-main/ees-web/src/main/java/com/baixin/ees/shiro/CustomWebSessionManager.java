package com.baixin.ees.shiro;

import java.io.Serializable;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import com.baixin.ees.Constants;


public class CustomWebSessionManager extends DefaultWebSessionManager {

    public CustomWebSessionManager() {
        super();
    }


    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        String jsessionid = httpRequest.getHeader(Constants.EX_JSESSIONID);

        if (StringUtils.isNotBlank(jsessionid)) {
            // 是否将EX-SysAuthToken保存到cookie，浏览器模式下使用此参数。
            if (WebUtils.isTrue(request, "__cookie")) {
                HttpServletResponse rs = (HttpServletResponse) response;
                Cookie template = getSessionIdCookie();
                Cookie cookie = new SimpleCookie(template);
                cookie.setValue(jsessionid);
                cookie.saveTo(httpRequest, rs);
            }
            // 设置当前session状态
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,
                ShiroHttpServletRequest.URL_SESSION_ID_SOURCE); // session来源与url
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, jsessionid);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);
            return jsessionid;
        } else {
            //return null;
             return super.getSessionId(request, response);
        }
    }

}
