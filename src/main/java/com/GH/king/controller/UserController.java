package com.GH.king.controller;

import com.GH.king.domain.User;
import com.GH.king.expection.ResourceNotFoundException;
import com.GH.king.repository.UserRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.hibernate.mapping.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "registerUser" ,method = RequestMethod.POST)
    @ApiOperation(value = "register a user in the database",response = User.class)
    public ResponseEntity<User> RegisterUser(@RequestParam(value = "Name") String userName ,
                                             @RequestParam(value = "Password")String password,
                                             @RequestParam(value = "Department")String department){
        User user = new User();
        user.setName(userName);
        user.setPassword(password);
        user.setDepartment(department);
        userRepo.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @RequestMapping(value="/modifyPassword/{Id}",method= RequestMethod.PUT)
    @ApiOperation("modify password of user by Id")
    public ResponseEntity<User> modifyPassword(@PathVariable("Id") int Id, @RequestParam String password){
        User user = userRepo.findOne(Id);
        if(user==null){
            throw new ResourceNotFoundException("There is no such User in database");
        }else{
            user.setPassword(password);
            userRepo.save(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ApiOperation("check login")
    public int login(@RequestParam("userName")String userName,
                     @RequestParam("password")String password){
        User user = userRepo.findByName(userName);
        if(user==null){
            throw new ResourceNotFoundException("there is no such user");
        }
        if(user.getPassword().equals(password)){
            return 1;
        }
        else
            return 0;

    }


}
