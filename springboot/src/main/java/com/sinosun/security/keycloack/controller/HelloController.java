package com.sinosun.security.keycloack.controller;

import org.keycloak.KeycloakPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class HelloController {

    @Autowired
    private HttpServletRequest request;

    /**
     * 首页
     * @param principal
     * @param map
     * @return
     */
    @RequestMapping("/")
    public String indexPage(Principal principal, ModelMap map) {
        System.out.println("/");
        map.addAttribute("host", "www.baidu.com");
        if (principal != null){
            map.addAttribute("user", principal.getName());
        }
        return "index";
    }

    /**
     * 用户页面，登录才能访问
     * @param principal
     * @param map
     * @return
     */
    @RequestMapping("/customer")
    public String customer(Principal principal, ModelMap map) {
        System.out.println("/customer");
        map.addAttribute("role", "customer");
        map.addAttribute("name", principal.getName());
        return "customer";
    }


    /**
     * 登出
     * @param principal
     * @param map
     * @return
     * @throws ServletException
     */
    @RequestMapping("/logout")
    public String logout(Principal principal, ModelMap map) throws ServletException {
        System.out.println("/logout");
        if (principal == null || !(principal instanceof KeycloakPrincipal)){
            return indexPage(principal,  map);
        }
        request.logout();
        return "redirect:/";
    }

    /**
     * 管理员页面
     * @param map
     * @return
     */
    @RequestMapping("/admin")
    public String admin(ModelMap map) {
        System.out.println("/admin");
        map.addAttribute("role", "admin");
        return "admin";
    }



}
