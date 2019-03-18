package com.itheima.ssm.controller;


import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;
    @RequestMapping("findAll.do")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfo = userService.findAll();
        mv.addObject("userList", userInfo);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    @PreAuthorize("authentication.principal.username=='tom'")
    public String save(UserInfo userInfo)throws Exception {
        try {
            userService.save(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id)throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(id);

        mv.addObject("user", user);
        mv.setViewName("user-show");
        return mv;
    }



    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId") String userId,@RequestParam(name = "ids") String[] ids)throws Exception{
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id")String UserId)throws Exception{
        ModelAndView mv = new ModelAndView();
        UserInfo user = userService.findById(UserId);

        List<Role> otherRoles = userService.findOtherRoles(UserId);
        mv.addObject("user", user);
        mv.addObject("roleList", otherRoles);

        mv.setViewName("user-role-add");
        return mv;
    }

}
