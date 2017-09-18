package com.demo.springsecurity.dao;

import com.demo.springsecurity.entity.Permission;
import com.demo.springsecurity.entity.Role;
import com.demo.springsecurity.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public SysUser getUserByName(String username) {
        List<SysUser> list = jdbcTemplate.query("select * from sys_login where username=?", new Object[]{username}, new RowMapper<SysUser>() {
            @Override
            public SysUser mapRow(ResultSet resultSet, int i) throws SQLException {
                SysUser sysUser = new SysUser();
                sysUser.setId(resultSet.getInt("login_id"));
                sysUser.setUsername(resultSet.getString("username"));
                sysUser.setPassword(resultSet.getString("password"));
                return sysUser;
            }
        });
        if (list.size() != 0) {
            return list.get(0);
        }
        return null;
    }

    public List<Role> getRolesByName(String username) {
        return jdbcTemplate.query("select sr.* from sys_login_role slr,sys_role sr where  slr.role_id=sr.role_id and slr.username=?", new Object[]{username}, new RowMapper<Role>() {
            @Override
            public Role mapRow(ResultSet resultSet, int i) throws SQLException {
                Role role = new Role();
                role.setId(resultSet.getInt("role_id"));
                role.setRoleName(resultSet.getString("role_name"));
                return role;
            }
        });
    }

    public List<Permission> getPermissionsByRoleName(String roleName) {
        return jdbcTemplate.query("select sp.* from sys_permission sp,sys_role_permission srp where srp.permission_id=sp.permission_id and srp.role_name=?", new Object[]{roleName}, new RowMapper<Permission>() {
            @Override
            public Permission mapRow(ResultSet resultSet, int i) throws SQLException {
                Permission permission = new Permission();
                permission.setId(resultSet.getInt("permission_id"));
                permission.setPermissionName(resultSet.getString("permission_name"));
                return permission;
            }
        });
    }
}
