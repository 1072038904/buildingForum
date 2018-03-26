package com.action.BoardManage;

import com.model.Board;
import com.opensymphony.xwork2.ActionSupport;
import com.service.BoardManage.BoardService;
import com.util.PageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;
@Controller
@ParentPackage("default")
@Namespace("/user")
@Result(name="success",location = "/index.jsp")
public class BoardAction extends ActionSupport{
    @Autowired
    private BoardService boardService;
    private String json;
    @Action("getAllBoard")
    public String getAllBoard(){
        System.out.println("123");
        PageBean pageBean=boardService.obtainAllBoardOfSection(1,3);
       // System.out.println(pageBean.getList().get(1).toString());
        JSONArray jsonObject = JSONArray.fromObject(pageBean.getList());
        json = jsonObject.toString();
        System.out.println(json);
        return "success";
    }
}
