package com.GH.king;

import com.GH.king.domain.Question;
import com.GH.king.tools.FileReader;
import org.junit.Test;

/**
 * Created by gy on 2016/7/19.
 */
public class testUploadQuestionBank {
    @Test
    public void testFileReader(){
        FileReader reader=new FileReader();
        String result=reader.read("D:/question.txt");
        System.out.println(result);
        String questions[]=result.split("#");
        System.out.println("包含："+questions.length+" 个问题");
        Question domain=new Question();
        for(int i=1;i<questions.length;i++){
            String str[]=questions[i].split("\n");
            domain.setTitle(str[0]);
            domain.setGroup(str[1]);
            domain.setItemA(str[2]);
            domain.setItemB(str[3]);
            domain.setItemC(str[4]);
            domain.setItemD(str[5]);
            domain.setAnswer(str[6]);
            domain.show();
        }
    }
}
