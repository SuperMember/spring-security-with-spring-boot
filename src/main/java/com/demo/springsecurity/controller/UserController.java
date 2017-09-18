package com.demo.springsecurity.controller;

import com.demo.springsecurity.config.JwtUtil;
import com.demo.springsecurity.entity.ResponseMessage;
import com.demo.springsecurity.entity.SysUser;
import com.demo.springsecurity.service.CustomUserService;
import com.demo.springsecurity.util.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserService customUserService;

    @GetMapping("/log")
    @ResponseBody
    public String login() {

        return "登录成功";
    }

    @PostMapping("/auth")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody Map<String, Object> params) {
        //进行验证
        Authentication authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                (String) params.get("username"),
                (String) params.get("password")
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //生成jwt
        SysUser sysUser = (SysUser) customUserService.loadUserByUsername((String) params.get("username"));
        String token = jwtUtil.generateToken(sysUser);
        ResponseMessage responseMessage = new ResponseMessage(token, Const.KEY);
        return new ResponseEntity<Object>(responseMessage, HttpStatus.OK);
    }

    @GetMapping("/auth/info")
    @ResponseBody
    public String info() {
        return "info";
    }

    @GetMapping("/index")
    @ResponseBody
    public String index() {
        return "index";
    }


}
