package com.baixin.ees.web.service.impl;

import cn.com.agree.bxbank.sso.domain.UserInfoResponse;
import com.baixin.ees.util.DesUtil;
import com.baixin.ees.util.ResultMessage;
import com.baixin.ees.util.UserUtils;
import com.baixin.ees.web.dao.model.SysUser;
import com.baixin.ees.web.service.SSOService;
import com.baixin.ees.web.service.UserService;
import com.sso.service.PwdAuthService;
import com.sso.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author 王岚枫
 * @Datetime 2017年10月23日 14:38
 */
@Service
public class SSOServiceImpl implements SSOService {

    @Autowired
    private PwdAuthService pwdAuthService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserService userService;

    public ResultMessage SSOLogin(String userId, String userPwd, String systemName, HttpServletRequest resquest) {
        if (pwdAuthService.authBySSO(userId, userPwd, systemName).getCode().equals("BCGSSO00000")) {
            ResultMessage resultMessage = userService.add(UserInfoResponseToSysUser(userInfoService.queryUserInfo(userId,
                    null), userPwd));
            if (resultMessage.isSuccess() == true) {
                if (UserUtils.login(userId, userPwd, true, resquest.getRemoteHost())) {
                    return ResultMessage.isOk(UserUtils.initSessionObject().getSysRole().getHomeUrl());
                }
            }
        }
        return ResultMessage.isError("认证失败");
    }

    private SysUser UserInfoResponseToSysUser(UserInfoResponse userInfoResponse, String userPwd) {
        SysUser user = new SysUser();
//        System.out.println(JSON.toJSONString(userInfoResponse));
        user.setUserName(userInfoResponse.getUserName());
        user.setPassword(DesUtil.encryptStr(userPwd));
        user.setOrgId(1);
        user.setRoleId(1);
        user.setSex("0");
        user.setRemark("SSO");
        user.setLoginName(userInfoResponse.getUserId());
        return user;
    }
}
