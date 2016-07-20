package com.GH.king.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by alex on 2016/1/21.
 */
@RestController
@RequestMapping(value="v1")
@Api(value = "Server-API" ,description = "return the status of Server")
public class Health {

    @RequestMapping(value="/health",method= RequestMethod.GET)
    @ApiOperation(value="return the status of Server")
    public String returnStatus(){
        String status="Tht ShES Server is running.....";
        return status;
    }

}
