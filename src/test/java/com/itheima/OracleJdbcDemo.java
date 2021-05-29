package com.itheima;

import oracle.jdbc.OracleTypes;
import org.junit.Test;

import java.sql.*;

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

    /**
     * java调用存储过程
     *  {?= call <procedure-name>[(<arg1>,<arg2>, ...)]}        调用存储函数使用（有返回值）
     *    {call <procedure-name>[(<arg1>,<arg2>, ...)]}         调用存储过程使用（没有返回值）
     * @throws Exception
     */
    @Test
    public void jdbcTest2() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //得到connection链接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.124.4:15210:orcl", "system", "****");
        //得到预编译的statement对象
        CallableStatement callableStatement = connection.prepareCall("{call p_yearsal(?,?)}");
        //给参数赋值
        callableStatement.setObject(1,7788);
        callableStatement.registerOutParameter(2, OracleTypes.NUMBER);
        //执行数据库查询操作
        callableStatement.execute();
        //输出结果
        System.out.println(callableStatement.getObject(2));

        callableStatement.close();
        connection.close();
    }
    @Test
    public void jdbcTest3() throws Exception {
        //加载数据库驱动
        Class.forName("oracle.jdbc.driver.OracleDriver");
        //得到connection链接
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.124.4:15210:orcl", "system", "****");
        //得到预编译的statement对象
        CallableStatement callableStatement = connection.prepareCall("{?= call f_yearsal(?)}");
        //给参数赋值
        callableStatement.setObject(2,7788);
        callableStatement.registerOutParameter(1, OracleTypes.NUMBER);
        //执行数据库查询操作
        callableStatement.execute();
        //输出结果
        System.out.println(callableStatement.getObject(1));

        callableStatement.close();
        connection.close();
    }
}
