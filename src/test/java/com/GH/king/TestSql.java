package com.GH.king;

import org.junit.Test;

import java.sql.*;
import java.util.*;

/**
 * Created by gy on 2016/7/19.
 */
public class TestSql {
    @Test
    public void ConnectSqlServer(){
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL="jdbc:sqlserver://120.27.30.105:1433;databaseName=ShESDB";
        String userName="GaH";
        String userPsd="123456789";
        Connection dbCon;

        Statement st;
        ResultSet rs;
        try {
            Class.forName(driverName);
            dbCon= DriverManager.getConnection(dbURL,userName,userPsd);
            System.out.println("connect to the DataBase successfully!");
            String sql="select * from Users";
            st=dbCon.createStatement();
            rs=st.executeQuery(sql);
            while(rs.next()){
                System.out.println("Data:"+rs.getString(2));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void parseAnswer(){
        String answer="1,3|2,1|2,2|4,3|4,2|1,0|3,1|3,2";
        String trueAnswer="0,3|1,2|1|2,3";
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
        List<String> databaseresult=new ArrayList<>();
        String strs[]=trueAnswer.split("\\|");
//将用户答案解析排序，组成单个答案
        List<String> userresult=new ArrayList<>();
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
        userresult.add(userAnswer);

    }
        System.out.println(userresult.toString());
        System.out.println(userresult.size()+"|"+strs.length);
        int count=0;
        for(int i=0;i<strs.length;i++){
            if(strs[i].equals(userresult.get(i))){
                count=count+25;
            }
        }
        System.out.println(count);
    }
}
