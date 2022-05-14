package Indi.ZYXOrion.SSMS.Database;


import Indi.ZYXOrion.SSMS.Entity.*;
import java.util.*;
import java.sql.*;

//数据库通信类
public class DBConnector implements JDBCConfig{
    //连接所需的jdbc对象
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection connection = null;
    //建立连接
    private void Init(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("连接到数据库"+URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //构造函数
    public DBConnector(){
        Init();
    }
    //获取用户对象
    public User getUser(User user){
        User newUser = new User();
        try {
            preparedStatement = connection.prepareStatement("select * from tb_User where User_name=?");
            preparedStatement.setString(1, user.getUsername());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                newUser.setUsername(resultSet.getString(1));
                newUser.setPassword(resultSet.getString(2));
                newUser.setIsLogin(resultSet.getInt(3));
                newUser.setLevel(resultSet.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUser;
    }

    //修改登录状态
    public boolean update_IsLogin(User user){
        boolean state = true;
        try {
            preparedStatement = connection.prepareStatement("update tb_User set IsLogin=? where User_name=?");
            preparedStatement.setInt(1, user.getIsLogin());
            preparedStatement.setString(2, user.getUsername());
            if(preparedStatement.executeUpdate()!=1){
                state = false;
            }
        } catch (SQLException e) {
            state = false;
            e.printStackTrace();
        }
        return state;
    }

    //关闭数据库连接
    public void close()	{
        try {
            if(resultSet !=null) resultSet.close();
            if(preparedStatement !=null) preparedStatement.close();
            if(connection !=null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}