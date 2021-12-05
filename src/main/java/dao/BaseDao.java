package dao;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class BaseDao {
    private static String driver = null;
    private static String url = null;
    private static String userName = null;
    private static String passWord = null;
    static{
        try {
            InputStream in = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            userName = properties.getProperty("username");
            passWord = properties.getProperty("password");

            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,userName,passWord);
    }
    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet){
        boolean flag = true;
        try{
            if (resultSet != null) {
                resultSet.close();

            }
        }catch (Exception e){
            e.printStackTrace();
            flag = false;
        }
        if (preparedStatement!=null) {
            try {
                preparedStatement.close();
                //GC回收
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (connection!=null) {
            try {
                connection.close();
                //GC回收
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        return flag;
    }
    //编写查询公共类
    public static ResultSet execute(Connection connection, String sql,Object[] params,ResultSet resultSet,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            //setObject占位符从1开始，数组从0开始
            preparedStatement.setObject(i+1,params[i]);
        }
        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    //编写增、删、改公共方法
    public static int execute(Connection connection, String sql,Object[] params,PreparedStatement preparedStatement) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            //setObject占位符从1开始，数组从0开始
            preparedStatement.setObject(i+1,params[i]);
        }
        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }

}
