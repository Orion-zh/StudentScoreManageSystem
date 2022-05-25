package Indi.ZYXOrion.SSMS.Database;

import Indi.ZYXOrion.SSMS.Entity.*;
import javax.swing.*;
import java.sql.*;

//数据库通信类
public class DBConnector implements JDBCConfig{
    //连接所需的jdbc对象
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private Connection connection = null;
    //构造函数建立连接
    public DBConnector(){
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
    //获取用户对象
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
    //创建用户
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
            JOptionPane.showMessageDialog(null, "用户已存在", "错误", JOptionPane.ERROR_MESSAGE);
        }catch (SQLException e){
            state = false;
            e.printStackTrace();
        }
        return state;
    }
    //查询管理员信息
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
                    objects[count][3] = "学生";
                }
                else if(level == 2){
                    objects[count][3] = "老师";
                }
                else if(level == 3){
                    objects[count][3] = "管理员";
                }
                count++;
            }
        } catch (SQLException e){
            objects = new Object[1][1];
            e.printStackTrace();
        }
        return objects;
    }
    //删除用户
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
    //编辑用户
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
    //查询成绩信息
    public Object[][] QueryStudent(String stuID, String courseID){
        int count = 0;
        Object[][] objects;
        String queryCondition;
        if(stuID==null&&courseID==null){
            queryCondition = "select * from Score_View";
        }
        else if(stuID!=null&&courseID==null){
            queryCondition = "select * from Score_View where stuID="+stuID;
        }
        else if(stuID==null&&courseID!=null) {
            queryCondition = "select * from Score_View where courseID=\'"+courseID+"\'";
        }
        else {
            queryCondition = "select * from Score_View where stuID="+stuID+" and courseID=\'"+courseID+"\'";
        }
        try{
            preparedStatement = connection.prepareStatement(queryCondition);
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
    //添加成绩信息
    public boolean AddScore(String stuID, String courseName, String year, double score){
        boolean state = true;
        try {
            preparedStatement = connection.prepareStatement("insert into Score(stuID,courseID,studyYear,score) values (?,?,?,?)");
            preparedStatement.setString(1, stuID);
            preparedStatement.setString(2, courseName);
            preparedStatement.setString(3, year);
            preparedStatement.setDouble(4, score);
            if (preparedStatement.executeUpdate() != 1) {
                state = false;
            }
        } catch (SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "该生已有该课程成绩，请先删除后操作！", "错误", JOptionPane.ERROR_MESSAGE);
        }catch (SQLException e){
            state = false;
            e.printStackTrace();
        }
        return state;
    }
    //获取课程列表
    public Object[][] getCourseList(){
        int count = 0;
        Object[][] objects;
        try{
            preparedStatement = connection.prepareStatement("select * from Course");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                count++;
            }
            objects = new Object[count][2];
            resultSet = preparedStatement.executeQuery();
            count = 0;
            while(resultSet.next()){
                objects[count][0] = resultSet.getString("courseID");
                objects[count][1] = resultSet.getString("courseName");
                count++;
            }
        } catch (SQLException e){
            objects = new Object[1][1];
            e.printStackTrace();
        }
        return objects;
    }
    //删除成绩信息
    public boolean DeleteScore(String stuID,String courseID){
        boolean state = true;
        try{
            preparedStatement = connection.prepareStatement("delete from Score where stuID=? and courseID=?");
            preparedStatement.setString(1,stuID);
            preparedStatement.setString(2,courseID);
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
    //编辑成绩信息
    public boolean EditScore(String stuID,String courseID,String score){
        boolean state = true;
        try {
            preparedStatement = connection.prepareStatement("update Score set score = ? where stuID = ? and courseID=?;");
            preparedStatement.setInt(1, Integer.parseInt(score));
            preparedStatement.setString(2, stuID);
            preparedStatement.setString(3,courseID);
            if (preparedStatement.executeUpdate() != 1) {
                state = false;
            }
        } catch (SQLException e){
            state = false;
            e.printStackTrace();
        }
        return state;
    }

}