package Indi.ZYXOrion.SSMS.Database;


import Indi.ZYXOrion.SSMS.Entity.*;
import java.util.*;
import java.sql.*;

//���ݿ�ͨ����
public class DBConnector implements JDBCConfig{
    //���������jdbc����
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection connection = null;
    //��������
    private void Init(){
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("���ӵ����ݿ�"+URL);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //���캯��
    public DBConnector(){
        Init();
    }
    //��ȡ�û�����
    public User getUser(User user){
        User newUser = new User();
        try {
            preparedStatement = connection.prepareStatement("select * from Users where userLoginName=?");
            preparedStatement.setString(1, user.getUsername());
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                newUser.setUsername(resultSet.getString(1));
                newUser.setPassword(resultSet.getString(3));
                newUser.setLevel(resultSet.getInt(4));
                newUser.setName(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newUser;
    }

    //�ر����ݿ�����
    public void close()	{
        try {
            if(resultSet !=null) resultSet.close();
            if(preparedStatement !=null) preparedStatement.close();
            if(connection !=null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //�����û�
    public boolean AddUser(User user){
        boolean state = true;
        try{
            preparedStatement = connection.prepareStatement("insert into Users(userLoginName,userName,userPassword,userLevel) values (?,?,?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4,user.getLevel());
            if(preparedStatement.executeUpdate()!=1){
                state = false;
            }
        } catch (SQLException e){
            state = false;
            e.printStackTrace();
        }
        return state;
    }


}