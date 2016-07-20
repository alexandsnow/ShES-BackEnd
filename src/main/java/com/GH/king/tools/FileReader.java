package com.GH.king.tools;

import com.GH.king.expection.ResourceNotFoundException;

import java.io.*;

/**
 * Created by gy on 2016/7/19.
 */

/**
 * description:
 * this class is just for read the file in local disk and return String
 */
public class FileReader {
    FileInputStream fs;
    InputStreamReader isr;
    BufferedReader br;
    public String read(String filePath){
        String result="";
        File questionFile= new File(filePath);
        if(questionFile==null){
            throw new ResourceNotFoundException("There is NO Such File! Please, Check Your File Path");
        }else{
            try {
                fs=new FileInputStream(questionFile);
                isr=new InputStreamReader(fs,"utf-8");
                br=new BufferedReader(isr);
                String temp=br.readLine();
                while(temp!=null){
                    result=result+temp+"\n";
                    temp=br.readLine();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    fs.close();
                    isr.close();
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }
    }

}
