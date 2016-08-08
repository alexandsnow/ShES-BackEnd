package com.GH.king.controller;

import com.GH.king.domain.Question;
import com.GH.king.domain.User;
import com.GH.king.expection.ResourceNotFoundException;
import com.GH.king.repository.QuestionRepository;
import com.GH.king.repository.UserRepository;
import com.GH.king.tools.FileReader;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Inject
    UserRepository userRepo;

    @Value("${Page.size}")
    private String pageSize;

    @Value("${QuestionBank.filePath}")
    private String filePath;


    @RequestMapping(value="/QuestionBank/addAllByFile",method = RequestMethod.GET)
    @ApiOperation("add all of the questions in the file")
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

    @RequestMapping(value = "/getQuestions/{userName}/{group}",method = RequestMethod.GET)
    @ApiOperation(value = "return  random question of your Group",response = Question.class,responseContainer = "List")
    public ResponseEntity<Page<Iterable<Question>>> getQuestions(@PathVariable("userName")String userName,
                                                                 @PathVariable("group")String group,
                                                                 @RequestParam(value="size" ,required=false) Integer size){
        if(size==null)
            size=Integer.parseInt(pageSize);

        Pageable p=new PageRequest(0,size);

        Page<Iterable<Question>> result = questionRepo.getRandQuestions(group,p);

        String questionID = "";
        String answers="";
        int questionSize = result.getContent().size();
        for(int i=0;i<questionSize;i++) {
            Question question = (Question) result.getContent().get(i);
            if (i < questionSize - 1) {
                questionID = questionID + question.getId() + "|";
                answers = answers + question.getAnswer() + "|";
            } else {
                questionID = questionID + question.getId();
                answers = answers + question.getAnswer();
            }
        }
        User user = userRepo.findByName(userName);
        if(user==null){
            throw new ResourceNotFoundException("there is no such user in database");
        }else{
            System.out.println(questionID);
            System.out.println(answers);
            user.setQuestions(questionID);
            user.setAnswers(answers);
            userRepo.save(user);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/getAllQuestions",method = RequestMethod.GET)
    @ApiOperation("获取所有的题目")
    public ResponseEntity<Iterable<Question>> getAllQuestions(){
        return new ResponseEntity<>(questionRepo.findAll(),HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteQuestion/{Id}",method = RequestMethod.DELETE)
    @ApiOperation("删除题目")
    public String deleteQuestion(@PathVariable("Id")String id){
        Question question = questionRepo.findOne(id);
        if(question == null){
            throw new ResourceNotFoundException("there is no such question");
        }else{
            questionRepo.delete(question);
            return "Successful to delete the question by id equals "+id;
        }

    }
}