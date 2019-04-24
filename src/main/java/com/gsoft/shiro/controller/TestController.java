package com.gsoft.shiro.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gsoft.shiro.entity.LoginResult;
import com.gsoft.shiro.service.LoginService;

@Controller
public class TestController {
  
	    @Resource
	    private LoginService loginService;

	    @RequestMapping({"/","/index"})
	    public String index(){
	        return"/index";
	    }

	    @RequestMapping("/403")
	    public String unauthorizedRole(){
	        System.out.println("------没有权限-------");
	        return "/user/403";
	    }

	    @RequestMapping(value = "/login",method = RequestMethod.GET)
	    public String toLogin(Map<String, Object> map,HttpServletRequest request)
	    {
	        loginService.logout();
	        return "/user/login";
	    }

	    @RequestMapping(value = "/login",method = RequestMethod.POST)
	    public String login(Map<String, Object> map,HttpServletRequest request) throws Exception{
	        System.out.println("login()");
	        String userName = request.getParameter("userName");
	        String password = request.getParameter("password");

	        LoginResult loginResult = loginService.login(userName,password);
	        if(loginResult.isLogin())
	        {
	            return "/index";
	        }
	        else {
	            map.put("msg",loginResult.getResult());
	            map.put("userName",userName);
	            return "/user/login";
	        }
	    }

	    @RequestMapping("/logout")
	    public String logOut(HttpSession session) {
	        loginService.logout();
	        return "/user/login";
	    }
	    @RequiresPermissions("resource:view")  
	    @RequestMapping("/testPem")
	    public String testPem() {
	    	System.out.println("-----------test");
	    	 return"/index";
	    }
}
