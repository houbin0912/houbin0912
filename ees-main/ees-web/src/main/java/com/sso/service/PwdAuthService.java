package com.sso.service;

import cn.com.agree.bxbank.sso.domain.PWDAuthAPI;
import cn.com.agree.bxbank.sso.domain.PWDAuthRequest;
import cn.com.agree.bxbank.sso.domain.PWDAuthResponse;
import com.baidu.ub.msoa.container.support.governance.annotation.BundleService;
import com.baidu.ub.msoa.rpc.RPCProtocol;
import org.springframework.stereotype.Service;

/**
 * 单点登录认证接口服务
 * @author duxianchao
 */
@Service
public class PwdAuthService {

	@BundleService(provider = "${msoa.sso.provider}", service = PWDAuthAPI.SERVICE_NAME, version = "${msoa.sso.version}",protocol = RPCProtocol.NAVI2JSON, interfaceType = PWDAuthAPI.class)
	private PWDAuthAPI pwdAuthAPI;
	
	/**
	 * SSO密码认证
	 * @param userId
	 * @param userPwd
	 * @param systemName
	 * @return
	 */
	public PWDAuthResponse authBySSO(String userId,String userPwd,String systemName){
		PWDAuthRequest req = new PWDAuthRequest();
		req.setUserID(userId);
		req.setUserPWD(userPwd);
		req.setSystemName(systemName);
		return pwdAuthAPI.checkPWD(req);
	}
}
