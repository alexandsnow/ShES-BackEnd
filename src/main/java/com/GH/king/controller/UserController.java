package com.GH.king.controller;

import com.GH.king.FreeMarkerConfig;
import com.GH.king.GahEmailConfig;
import com.GH.king.domain.GaHEmail;
import com.GH.king.domain.User;
import com.GH.king.expection.ResourceNotFoundException;
import com.GH.king.repository.UserRepository;
import com.GH.king.service.FreeMarkerService;
import com.GH.king.service.GaHEmailMessageService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 2016/1/23.
 */
@RestController
@Api(description = "user information",value = "User API")
@RequestMapping(value = "v1")
//@PreAuthorize("hasAuthority('admin')")
public class UserController {
    @Value("${GaH.userPic.dir}")
    private String picDir;

    @Inject
    private UserRepository userRepo;

    @Inject
    private FreeMarkerConfig freeMarkerConfig;

    @Inject
    private GahEmailConfig gahEmailConfig;

    private static String maxUserId="00000000";

    @RequestMapping(value = "/Users",method = RequestMethod.GET)
    @ApiOperation(value = "Return Users",response = User.class,responseContainer = "List")
    public ResponseEntity<Object> getUsers(){
        Iterable<User> results=userRepo.findAll();
        return new ResponseEntity<>(results , HttpStatus.OK);
    }
    @RequestMapping(value = "/user/{Id}",method = RequestMethod.GET)
    @ApiOperation(value = "Return a specific User by id",response=User.class)
    public User getUserById(@PathVariable(value="Id") String Id){
        return this.validateUser(Id);
    }

    @RequestMapping(value = "/user/{Id}/{password}",method = RequestMethod.PUT)
    @ApiOperation(value = "modify the password",response = User.class)
    public User updateUserPsd(@PathVariable(value="Id") String Id,@PathVariable(value = "password") String password){
        User user=this.validateUser(Id);
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //password=encoder.encode(password);
        user.setUserPsd(password);
        userRepo.save(user);
        return user;
    }

    @RequestMapping(value = "user/{email}/pic",method = RequestMethod.GET)
    @ApiOperation(value = "Return Image of User")
    public void getPic(@PathVariable(value = "email") String email,HttpServletResponse response){
        User user=userRepo.findByEmail(email);
        String picName=user.getHeadPic();
        File pic=FileUtils.getFile(picDir,picName);
        response.setHeader("Content-Disposition", "picture; pic=" + pic.getName());
        response.setContentType("application/octet-stream");
        try {
            ServletOutputStream stream = response.getOutputStream();
            stream.write(FileUtils.readFileToByteArray(pic));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "Register")
    @RequestMapping(value = "user/register/{Email}/{password}",method=RequestMethod.POST)
    public ResponseEntity<String> registerUser(@PathVariable("Email") String email,@PathVariable("password") String password){
        User user=new User();
        int current_userID=Integer.parseInt(maxUserId)+1;
        maxUserId=current_userID+"";
        user.setUserId(maxUserId);
        user.setEmail(email);
        user.setUserPsd(password);

        userRepo.save(user);

        FreeMarkerService freeMarkerService=new FreeMarkerService(freeMarkerConfig.getConfiguration());
        Map<String,String> map=new HashMap();
        map.put("user",email);
        map.put("password", password);
        String content=freeMarkerService.processTemplate(map,"emailTemplate.ftl");


        GaHEmail emailEntity=new GaHEmail("register successful",email,content);

        GaHEmailMessageService gaHEmailMessageService = new GaHEmailMessageService(gahEmailConfig.getSender());
        gaHEmailMessageService.sendMimeMessage(emailEntity);

        return new ResponseEntity<>(content,HttpStatus.OK);
    }

    public User validateUser(String Id){
        User user=userRepo.findByuserId(Id);
        if(user == null){
            throw new ResourceNotFoundException("there is no this ID for any user");
        }
        else
            return user;
    }
}
