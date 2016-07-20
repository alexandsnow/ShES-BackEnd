package com.GH.king.controller;

import com.GH.king.domain.User;
import com.GH.king.repository.UserRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

/**
 * Created by gy on 2016/7/19.
 */
@RestController
@RequestMapping(value = "v1")
@Api( value ="User-API",description = "get/modify/delete user information")
public class UserController {
    @Inject
    private UserRepository userRepo;

    @RequestMapping(value="/getUsers" ,method = RequestMethod.GET)
    @ApiOperation(value = "return all users",response = User.class ,responseContainer ="LIST")
    public ResponseEntity<Iterable<?>> getAllUser(){
        Iterable<User> users = userRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

}
