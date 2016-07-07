package com.GH.king.controller;

import com.GH.king.GahEmailConfig;
import com.GH.king.domain.AppStatus;
import com.GH.king.domain.GaHEmail;
import com.GH.king.service.GaHEmailMessageService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;


/**
 * Created by alex on 2016/3/10.
 */
@RestController
@Api(value = "Email Api",description = "use for register and notice user")
@RequestMapping(value = "/v1")
public class EmailController {

    @Inject
    private GahEmailConfig gahEmailConfig;

    private JavaMailSenderImpl javaMailSender;

    @RequestMapping(value = "/sendEmail",method= RequestMethod.GET)
    @ApiOperation(value = "send message to your customer")
    public String sendMail(@RequestParam(value = "sendTo") String sendTo,
                           @RequestParam(value = "Content")String content,
                           @RequestParam(value = "Subject")String subject){
        GaHEmail email = new GaHEmail(subject,sendTo,content);
        javaMailSender = gahEmailConfig.getSender();
        GaHEmailMessageService gaHEmailService = new GaHEmailMessageService(javaMailSender);
        gaHEmailService.sendMimeMessage(email);
        AppStatus.Success.setDescription("email send successful");
        return AppStatus.Success.getDescription();
    }
}
