package com.liu.测试数据库连接池;

import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

public class JDBCStu {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        HashMap<String, Statement> map = new HashMap<>();
        long star = System.currentTimeMillis();
        for (int i = 0; i<10; i++){
            Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.0.100:3306/user", "root", "123456");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from sys_user_table;");
            map.put(i+"",statement);
        }
        System.out.println(System.currentTimeMillis() - star+"s");

        star = System.currentTimeMillis();
        for (int i = 0; i<10; i++){
            Statement statement = map.get(i + "");
            ResultSet resultSet = statement.executeQuery("select * from sys_user_table;");
        }
        System.out.println(System.currentTimeMillis() - star+"s");

        System.out.println(map);


    }
}
