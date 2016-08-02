package com.GH.king.service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gy on 2016/8/2.
 */

public interface AnswerService {
     int parseAnswer(String userAnswer,String trueAnswer);
}
