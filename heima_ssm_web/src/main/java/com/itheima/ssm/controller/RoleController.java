package com.itheima.ssm.controller;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll()throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll();
        mv.addObject("roleList", roles);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Role role)throws Exception {
        try {
            roleService.save(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String id)throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);

        mv.addObject("role", role);
        mv.setViewName("role-show");
        return mv;
    }

    @RequestMapping("/addPermissionToRole.do")
    public String addPermissionToRole(@RequestParam(name = "roleId") String roleId,@RequestParam(name = "ids") String[] ids)throws Exception{
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermission.do")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id")String RoleId)throws Exception{
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(RoleId);

        List<Permission> otherPermissions = roleService.findOtherPermissions(RoleId);
        mv.addObject("role", role);
        mv.addObject("permissionList", otherPermissions);

        mv.setViewName("role-permission-add");
        return mv;
    }
}
