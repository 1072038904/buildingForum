package com.controller.register;

import com.model.Account;
import com.model.UserInfor;
import com.service.AccountManage.AccountService;
import com.service.UserInforManage.UserInforService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    @Qualifier("userInforServiceImple")
    private UserInforService userService;
    @Autowired
    @Qualifier("accountServiceImple")
    private AccountService accountService;
    public RegisterController() {
        // TODO ×Ô¶¯Éú³ÉµÄ¹¹Ôìº¯Êý´æ¸ù
    }

    @RequestMapping("registerpage")
    public String toRegisterPage(HttpServletResponse response, HttpSession session) throws IOException {
        return"register";
    }

    @RequestMapping("getIdencode")
    @ResponseBody
    public void getIdencode(HttpServletResponse response, HttpSession session) throws IOException {
        BufferedImage image = com.utils.ImgCodeGenerate.getImage(session);
        ImageIO.write(image, "JPEG", response.getOutputStream());
    }

    @RequestMapping("identityCode")
    @ResponseBody
    public boolean identityCode(@RequestBody Map<String,String> request, HttpSession session){
        int code=(Integer)session.getAttribute("checkcode");
        if(Integer.parseInt(request.get("idencode"))==code)
            return true;
        else return false;
    }
    @RequestMapping("isExist")
    @ResponseBody
    public boolean isExist(@RequestBody Map<String,String> request){
        Account account=new Account();
        account.setUsername(request.get("username"));
        return accountService.isExist(account);
    }
    @RequestMapping("/add")
    @ResponseBody
    public Map<String,String> add(HttpSession session,@RequestBody Map<String,String> request){
        Account account=new Account();
        account.setAccount((int)(System.currentTimeMillis()/1000-1510000000));
        account.setUsername(request.get("username"));
        account.setPassword(request.get("password"));
        UserInfor userInfor=new UserInfor();
        userInfor.setTelephone(request.get("phonenum"));
        userInfor.setEmail(request.get("email"));
        userInfor.setSex(request.get("sex"));
        userInfor.setHobby(request.get("hobby"));
        userInfor.setCredit(50);
        userInfor.setBalance(10);
        userInfor.setLevel(1);
        account.setUserInfor(userInfor);
        userService.saveNewEntity(userInfor);
        accountService.saveNewEntity(account);
        session.setAttribute("user",account);
        Map<String,String> map=new HashMap<String,String>();
        map.put("isSuccess","true");
        return map;
    }
}