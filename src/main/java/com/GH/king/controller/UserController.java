package com.GH.king.controller;

import com.GH.king.domain.AppStatus;
import com.GH.king.domain.User;
import com.GH.king.expection.ResourceNotFoundException;
import com.GH.king.repository.UserRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by gy on 2016/7/19.
 */
@RestController
@RequestMapping(value = "v1")
@Api( value ="User-API",description = "get/modify/delete user information")
public class UserController {
    @Inject
    private UserRepository userRepo;

    @Value("${ShES.userPic.dir}")
    private String picDir;

    @RequestMapping(value="/getUsers" ,method = RequestMethod.GET)
    @ApiOperation(value = "return all users",response = User.class ,responseContainer ="LIST")
    public ResponseEntity<Iterable<?>> getAllUser(){
        Iterable<User> users = userRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "registerUser" ,
                    method = RequestMethod.POST,
                    produces  = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "register a user in the database",response = User.class)
    public ResponseEntity<Object> RegisterUser( @RequestParam(value = "Name") String userName ,
                                                @RequestParam(value = "Password")String password,
                                                @RequestParam(value = "Department")String department){
        User userTemp = userRepo.findByName(userName);
        if(userTemp==null){
            User user = new User();
            user.setName(userName);
            user.setPassword(password);
            user.setDepartment(department);
            userRepo.save(user);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }
        else{
            AppStatus appStatus = new AppStatus("401","userName had been used!");
            return new ResponseEntity<>(appStatus,HttpStatus.OK);
        }

    }

    @RequestMapping(value="/modifyPassword/{userName}",method= RequestMethod.PUT)
    @ApiOperation("modify password of user by Id")
    public ResponseEntity<User> modifyPassword(@PathVariable("userName") String userName, @RequestParam String password){
        User user = userRepo.findByName(userName);
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

//    获取用户头像
    @RequestMapping(value = "/userPic/{userName}",method = RequestMethod.GET)
    @ApiOperation("返回用户头像")
    public void getUserPic(@PathVariable("userName")String userName, HttpServletResponse response){
        User user = userRepo.findByName(userName);
        if(user == null){
            throw new ResourceNotFoundException("there is no such user in database,wrong userName");
        }else{
            String picName = user.getPic();
            File pic = FileUtils.getFile(picDir,picName);
            response.setHeader("Content-Disposition", "picture; picture=" + picName);
            response.setContentType("application/octet-stream");
            // TODO: 2016/8/2
            System.out.println("获取"+userName+"头像："+picName);
            try {
                ServletOutputStream servletOutputStream = response.getOutputStream();
                servletOutputStream.write(FileUtils.readFileToByteArray(pic));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @RequestMapping(value ="/deleteUser/{userName}",method = RequestMethod.DELETE)
    @ApiOperation("删除用户")
    public String deleteUser(@PathVariable("userName")String userName){
        User usertemp = userRepo.findByName(userName);
        if(usertemp == null){
            throw new ResourceNotFoundException("there is no such user");
        }else{
            userRepo.delete(usertemp);
            return "successful to delete user "+userName;
        }
    }

}
