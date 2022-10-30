package com.safi.myfirst.controller;

import com.safi.myfirst.domaino.User;
import com.safi.myfirst.service.UserService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
public class UserController
{

    Logger log= LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    @PostMapping
    public void create(@RequestBody User user){
        this.userService.create(user);
    }


}
