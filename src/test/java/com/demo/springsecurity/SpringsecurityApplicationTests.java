package com.demo.springsecurity;

import com.demo.springsecurity.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringsecurityApplicationTests {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {
        //logger.info(userService.getUserByName("admin1").toString());
        //logger.info(userService.getRolesByName("admin1").toString());
        logger.info(userService.getPermissionsByRoleName("admin").toString());
    }

}
