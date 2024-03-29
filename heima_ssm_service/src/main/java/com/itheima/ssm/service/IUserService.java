package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public List<UserInfo> findAll()throws Exception;

    void save(UserInfo userInfo)throws Exception;

    UserInfo findById(String id)throws Exception;


    //UserInfo findByUserId(String UserId)throws Exception;

    List<Role> findOtherRoles(String userId)throws Exception;

    void addRoleToUser(String userId, String[] ids)throws Exception;
}

