package com.demo.springsecurity.service;

import com.demo.springsecurity.dao.UserDao;
import com.demo.springsecurity.entity.Permission;
import com.demo.springsecurity.entity.Role;
import com.demo.springsecurity.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public SysUser getUserByName(String username) {
        return userDao.getUserByName(username);
    }

    public List<Role> getRolesByName(String userName) {
        return userDao.getRolesByName(userName);
    }

    public List<Permission> getPermissionsByRoleName(String roleName) {
        return userDao.getPermissionsByRoleName(roleName);
    }
}
