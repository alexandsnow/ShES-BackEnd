package com.GH.king.controller;

import com.GH.king.domain.AppStatus;
import com.GH.king.domain.User;
import com.GH.king.repository.UserRepository;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by alex on 2016/1/23.
 */
@RestController
@RequestMapping(value = "v1")
@Api(description = "Return the Result of Login",value = "Login API")
public class Login {

    @Inject
    UserRepository userRepo;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ApiOperation(value = "check User's Login Info")
    public AppStatus Login(@RequestParam(value = "email",required = true) String email,
                           @RequestParam(value = "password",required = true)String password
           ){
        User user=userRepo.findByEmail(email);

        if(user==null)
            return AppStatus.UserNotFound;
        else if(!password.equals(user.getUserPsd()))
            return AppStatus.WrongPasswod;
        else {
            return AppStatus.Success;
        }
    }
}
