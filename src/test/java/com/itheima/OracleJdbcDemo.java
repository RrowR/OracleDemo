package com.itheima;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OracleJdbcDemo {
    @Test
    public void jdbcTest() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //得到connection链接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.124.4:15210:orcl", "system", "****");
        //得到预编译的statement对象
        PreparedStatement preparedStatement = connection.prepareStatement("select * from emp where empno = ?");
        //给第一个占位符设置参数
        preparedStatement.setObject(1,7788);
        //执行查询返回结果集
        ResultSet resultSet = preparedStatement.executeQuery();
        //输出结果
        while(resultSet.next()){
            System.out.println(resultSet.getString("ename"));
        }
//        释放资源
        resultSet.close();
        preparedStatement.close();
        connection.close();
    }
}
