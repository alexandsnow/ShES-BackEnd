package com.GH.king.service;

import java.util.*;

/**
 * Created by gy on 2016/8/2.
 */
public class AnswerServiceImpl implements AnswerService {
    @Override
    public int parseAnswer(String answer,String trueAnswer) {
        String items[] = answer.split("\\|");
        Map<Integer,List<String>> answerMap = new HashMap<>();
        String answerString="";
        Integer questionId=Integer.parseInt(items[0].split(",")[0]);
        List<String> list= new ArrayList<>();
        for(int i=0;i<items.length;i++){
            String item[]=items[i].split(",");
            if(questionId.equals(item[0])){
                list.add(item[1]);
                answerString=answerString+item[1];
            }
            else{
                answerMap.put(questionId,list);
                questionId=Integer.parseInt(item[0]);
                list=new ArrayList<>();
                list.add(item[1]);
                if(answerMap.containsKey(questionId)){
                    list = answerMap.get(questionId);
                    list.add(item[1]);
                    answerMap.put(questionId,list);
                }else{
                    answerMap.put(questionId,list);
                }
                answerString=answerString+"|"+item[1];
            }
        }
        //sort
        List<Map.Entry<Integer,List<String>>> answerList = new ArrayList<>(answerMap.entrySet());
        Collections.sort(answerList, new Comparator<Map.Entry<Integer, List<String>>>() {
                    @Override
                    public int compare(Map.Entry<Integer, List<String>> o1, Map.Entry<Integer, List<String>> o2) {
                        return o1.getKey().compareTo(o2.getKey());
                    }
                }
        );

        //获取value
        List<List<String>> result = new ArrayList<>();
        for(int i=0;i<answerList.size();i++){
            result.add(answerList.get(i).getValue());
        }
        //解析正确答案排序，组成单个答案
        String trueAnswers[]=trueAnswer.split("\\|");
        //将用户答案解析排序，组成单个答案
        List<String> userResult=new ArrayList<>();
        for(int j=0;j<result.size();j++ ){
            List<String> listTemp = result.get(j);
            Collections.sort(listTemp,String::compareTo);
            String userAnswer="";
            for(int i=0;i<listTemp.size();i++) {
                if (i < listTemp.size() - 1)
                    userAnswer = userAnswer + listTemp.get(i) + ",";
                else
                    userAnswer = userAnswer + listTemp.get(i);
            }
            userResult.add(userAnswer);
        }
        System.out.println("用户答案："+userResult.toString());
        System.out.println("正确答案："+trueAnswer);
        int count=0;
        for(int i=0;i<trueAnswers.length;i++){
            if(trueAnswers[i].equals(userResult.get(i))){
                count = count + 10;
            }
        }
        return count;
    }
}


