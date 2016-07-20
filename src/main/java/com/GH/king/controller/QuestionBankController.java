package com.GH.king.controller;

import com.GH.king.domain.Question;
import com.GH.king.repository.QuestionRepository;
import com.GH.king.tools.FileReader;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * Created by gy on 2016/7/19.
 */
@RestController
@RequestMapping("v1")
@Api(value="QuestionBank-API",description="add/modify/delete the questions")
public class QuestionBankController {

    @Inject
    QuestionRepository questionRepo;

    @Value("${QuestionBank.filePath}")
    private String filePath;


    @RequestMapping(value="/QuestionBank/addAll",method = RequestMethod.GET)
    @ApiOperation("add all of the questions into the DataBase")
    public String addQuestionBankByFile(){
        FileReader fr=new FileReader();
        String questions[]=fr.read(filePath).split("#");
        for(int i=1;i<questions.length;i++){
            Question domain=new Question();
            String str[]=questions[i].split("\n");
            domain.setTitle(str[0]);
            domain.setGroup(str[1]);
            domain.setItemA(str[2]);
            domain.setItemB(str[3]);
            domain.setItemC(str[4]);
            domain.setItemD(str[5]);
            domain.setAnswer(str[6]);
            questionRepo.save(domain);
        }
        return "successful to upload "+ questions.length+" records into Server";
    }
    @RequestMapping(value="/QuestionBank/addOne",method=RequestMethod.PUT)
    @ApiOperation("add one question for QuestionBank")
    public ResponseEntity<Question> addQuestionBankByInput(@RequestParam("GroupType")String group,
                                                           @RequestParam("Title")String title,
                                                           @RequestParam("ItemA")String itemA,
                                                           @RequestParam("ItemB")String itemB,
                                                           @RequestParam("ItemC")String itemC,
                                                           @RequestParam("ItemD")String itemD,
                                                           @RequestParam("Answer")String answer){
        Question question = new Question();
        question.setGroup(group);
        question.setTitle(title);
        question.setItemA(itemA);
        question.setItemB(itemB);
        question.setItemC(itemC);
        question.setItemD(itemD);
        question.setAnswer(answer);
        questionRepo.save(question);
        return new ResponseEntity<>(question, HttpStatus.OK);
    }
}
