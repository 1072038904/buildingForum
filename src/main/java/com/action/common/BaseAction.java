package com.action.common;

import java.util.Map;
import javax.annotation.Resource;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.stereotype.Controller;
import com.service.AccountManage.LoginService;

@Controller
public class BaseAction implements SessionAware {
	@Resource
	protected LoginService loginService;
	protected Map<String, Object> session;

	public BaseAction() {
		// TODO Auto-generated constructor stub
	}


	public Map<String, Object> getSession() {
		return session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
}
