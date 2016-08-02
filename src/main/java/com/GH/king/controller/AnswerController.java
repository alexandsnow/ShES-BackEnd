package com.GH.king.controller;

import com.GH.king.domain.User;
import com.GH.king.expection.ResourceNotFoundException;
import com.GH.king.repository.UserRepository;
import com.GH.king.service.AnswerService;
import com.GH.king.service.AnswerServiceImpl;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;


/**
 * Created by gy on 2016/8/2.
 */
@RestController
@RequestMapping("/v1")
@Api("上传答案获取成绩")
public class AnswerController {
    @Inject
    UserRepository userRepo;

    @RequestMapping(value = "uploadAnswer/{userName}",method = RequestMethod.POST)
    @ApiOperation("根据用户名上传用户答案，并返回成绩")
    public ResponseEntity<String> uploadAnswer(@PathVariable("userName")String userName, @RequestParam("answer") String userAnswer){
        User user = validateUser(userName);
        String trueAnswer = user.getAnswers();
        AnswerService answerService = new AnswerServiceImpl();
        int count=answerService.parseAnswer(userAnswer,trueAnswer);
        return new ResponseEntity<>(userName+" 的成绩为："+count, HttpStatus.OK);
    }

    public User validateUser(String userName){
        User user = userRepo.findByName(userName);
            if(user != null){
                return user;
            }
            else{
                throw new ResourceNotFoundException("these is no such user");
            }
    }
}