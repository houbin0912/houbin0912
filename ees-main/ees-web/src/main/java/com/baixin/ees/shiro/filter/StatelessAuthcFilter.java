package com.baixin.ees.shiro.filter;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.session.Session;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.alibaba.fastjson.JSONObject;
import com.baixin.ees.Constants;
import com.baixin.ees.util.UserUtils;


/**
 * 无状态认证
 * 
 * @Description
 * @author: xjj
 * @CreateDate:2016年1月28日
 */
public class StatelessAuthcFilter extends AccessControlFilter {

	@Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        return ((isAccessAllowed(request, response, mappedValue))
                && (onAccessDenied(request, response, mappedValue)));
    }


    /**
     * 
     * @Description: 登录或者注销直接访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpresponse = (HttpServletResponse) response;
        httpresponse.setHeader("Access-Control-Allow-Origin", "*");

        if (UserUtils.isLogin()) {
        	//这个值啥时候设置的？
            String authToken = httpRequest.getHeader(Constants.EX_SYSAUTHTOKEN);// 得到authToken值
            if (authToken == null) {
                sendFailMsg(response, "登录失效信息为空,请重新登录", "authTokenIsNull");
                return false;
            }

//            String host = request.getRemoteHost();
//            if (!host.equals(UserUtils.getHostByToken(authToken))) {
//                sendFailMsg(response, "登录地址不一致!", "hostIsDiff");
//                return false;
//
//            }
            Session session = UserUtils.getSession();
            if (session == null) {
                sendFailMsg(response, "登录已失效或未登录,请重新登录！", "sessionInvalid");
                return false;
            }

            return true;
        }

        sendFailMsg(response, "登录已失效或未登录,请重新登录！", "sessionInvalid");
        return false;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

        // 得到当前请求地址
        String requestURI = getPathWithinApplication(request);
        String paths = requestURI.replace("/services/", "");
        if (paths.indexOf("?") > 0) {
            paths = paths.substring(0, paths.indexOf("?"));
        }

        paths = paths.substring(0, paths.lastIndexOf("/"));

        // 对比当前人员是否有权限
        try {
            SecurityUtils.getSubject().checkPermission(paths);
        } catch (UnauthorizedException e) {
            e.printStackTrace();
            sendFailMsg(response, "没有权限", "noPermission");
            return false;
        }
        return true;
    }


    private void sendFailMsg(ServletResponse response, String msg, String errorCode) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        JSONObject json = new JSONObject();
        json.put("state", false);
        json.put("msg", msg);
        json.put("errorCode", errorCode);
        httpResponse.setContentType("application/json;charset=UTF-8");
        httpResponse.setHeader("Cache-Control", "no-cache");
        httpResponse.getWriter().write(json.toString());
    }

}