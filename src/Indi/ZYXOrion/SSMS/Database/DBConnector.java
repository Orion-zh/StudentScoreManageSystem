package Indi.ZYXOrion.SSMS.Database;


import Indi.ZYXOrion.SSMS.Entity.*;

import javax.swing.*;
import java.util.*;
import java.sql.*;

//���ݿ�ͨ����
public class DBConnector implements JDBCConfig{
    //���������jdbc����
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection connection = null;
    //���캯����������
    public DBConnector(){
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
    public boolean AddUser(User user) {
        boolean state = true;
        try {
            preparedStatement = connection.prepareStatement("insert into Users(userLoginName,userName,userPassword,userLevel) values (?,?,?,?)");
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getLevel());
            if (preparedStatement.executeUpdate() != 1) {
                state = false;
            }
        } catch (SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "�û��Ѵ���", "����", JOptionPane.ERROR_MESSAGE);
        }catch (SQLException e){
            state = false;
            e.printStackTrace();
        }
        return state;
    }
    public Object[][] QueryAdmin(){
        int count = 0;
        Object[][] objects;
        try{
            preparedStatement = connection.prepareStatement("select * from Users");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                count++;
            }
            objects = new Object[count][4];
            resultSet = preparedStatement.executeQuery();
            count = 0;
            while(resultSet.next()){
                objects[count][0] = resultSet.getString("userLoginName");
                objects[count][1] = resultSet.getString("userName");
                objects[count][2] = resultSet.getString("userPassword");
                int level = Integer.valueOf(resultSet.getInt("userLevel"));
                if(level == 1){
                    objects[count][3] = "ѧ��";
                }
                else if(level == 2){
                    objects[count][3] = "��ʦ";
                }
                else if(level == 3){
                    objects[count][3] = "����Ա";
                }
                count++;
            }
        } catch (SQLException e){
            objects = new Object[1][1];
            e.printStackTrace();
        }
        return objects;
    }
    public boolean DeleteUser(String userLoginName){
        boolean state = true;
        try{
            preparedStatement = connection.prepareStatement("delete from Users where userLoginName=?");
            preparedStatement.setString(1,userLoginName);
            int s =preparedStatement.executeUpdate();
            if(s!=1){
                System.out.println("Error");
                state = false;
            }
        } catch(SQLException e){
            state = false;
            e.printStackTrace();
        }
        return state;
    }
    public boolean EditUser(User user){
        boolean state = true;
        try {
            preparedStatement = connection.prepareStatement("update Users set userName = ? where userLoginName = ? ;");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            if (preparedStatement.executeUpdate() != 1) {
                state = false;
            }
        } catch (SQLException e){
            state = false;
            e.printStackTrace();
        }
        try {
            preparedStatement = connection.prepareStatement("update Users set userPassword = ? where userLoginName = ? ;");
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUsername());
            if (preparedStatement.executeUpdate() != 1) {
                state = false;
            }
        } catch (SQLException e){
            state = false;
            e.printStackTrace();
        }
        try {
            preparedStatement = connection.prepareStatement("update Users set userLevel = ? where userLoginName = ? ;");
            preparedStatement.setInt(1, user.getLevel());
            preparedStatement.setString(2, user.getUsername());
            if (preparedStatement.executeUpdate() != 1) {
                state = false;
            }
        } catch (SQLException e){
            state = false;
            e.printStackTrace();
        }
        return state;
    }
    public Object[][] QueryStudent(String stuID){
        int count = 0;
        Object[][] objects;
        try{
            preparedStatement = connection.prepareStatement("select * from Score_View where stuID=?");
            preparedStatement.setString(1,stuID);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                count++;
            }
            objects = new Object[count][6];
            resultSet = preparedStatement.executeQuery();
            count = 0;
            while(resultSet.next()){
                objects[count][0] = resultSet.getString("courseID");
                objects[count][1] = resultSet.getString("courseName");
                objects[count][2] = resultSet.getString("studyYear");
                objects[count][3] = resultSet.getString("courseCredit");
                objects[count][4] = resultSet.getString("stuID");
                objects[count][5] = resultSet.getString("score");
                count++;
            }
        } catch (SQLException e){
            objects = new Object[1][1];
            e.printStackTrace();
        }
        return objects;
    }
}