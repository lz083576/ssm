package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll()throws Exception;

    void save(Role role)throws Exception;

    Role findById(String id)throws Exception;

    List<Permission> findOtherPermissions(String roleId)throws Exception;

    void addPermissionToRole(String roleId, String[] ids)throws Exception;
}
