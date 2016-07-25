package com.GH.king;

import org.junit.Test;

import java.sql.*;

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
}
