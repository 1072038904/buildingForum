package com.action.AccountManage;

import com.opensymphony.xwork2.ActionSupport;
import com.service.AccountManage.LoginService;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.action.common.BaseAction;
import com.model.Account;

@Controller
@ParentPackage("struts-default")
@Namespace("/user")
@Result(name="success",location = "/index.jsp")
public class LoginAction extends ActionSupport{
    @Autowired
    private LoginService loginService;

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    private Account account =new Account();
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	public LoginAction() {
	}
	public String execute() throws Exception{
		return "error";
	}
	@Action(value="login",results = { @Result(name = "success", location = "/index.jsp") })
	public String Login()throws Exception{	
		if(loginService.isValid(account)==1){
			//session.remove("account");
			account=loginService.findAccount(account);
//		/	UserInfor userInfor=userInforService.findEnetityByAccount(account, UserInfor.class);
			//session.put("account",account);
			//session.put("userInfor", userInfor);
			
			if(account.getJurisdiction()==1)
			return "admin";
			else {
				
			//	Commodity  commodity = commodityService.recommendCommodity(userInfor);
				//session.put("reCommodity", commodity);
			return "success";
			}
		}
		else {
			//session.put("tipAccount", 1);
			return "error";
		}
	}
	public String logout() {
		//session.remove("account");
		return "success";
	}
}
