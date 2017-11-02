package com.baixin.ees.web.service;

import com.baixin.ees.util.ResultMessage;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 王岚枫
 * @Datetime 2017年10月23日 14:37
 */
public interface SSOService {

    ResultMessage SSOLogin(String loginName, String password, String rs, HttpServletRequest resquest);

}
