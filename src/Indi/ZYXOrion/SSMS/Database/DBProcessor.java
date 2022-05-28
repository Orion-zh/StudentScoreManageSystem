package Indi.ZYXOrion.SSMS.Database;

import Indi.ZYXOrion.SSMS.Entity.*;

//数据库业务处理类
public class DBProcessor {
    private DBConnector connect;
    //登录
    public boolean Login(User user) {
        boolean state = true;
        connect = new DBConnector();
        User newUser = connect.getUser(user);
        if (!user.getPassword().equals(newUser.getPassword())) {
            state = false;
        }
        if(user.getLevel()!=newUser.getLevel()){
            state = false;
        }
        connect.close();
        return state;
    }
    //获取用户信息
    public User getUser(User user){
        connect = new DBConnector();
        User DBuser =  connect.getUser(user);
        return DBuser;
    }
    //创建用户
    public boolean AddUser(User user){
        connect = new DBConnector();
        boolean state = connect.AddUser(user);
        connect.close();
        return state;
    }
    //查询用户信息
    public Object[][] getAdminQuery(){
        Object[][] objects;
        DBConnector connector = new DBConnector();
        objects = connector.QueryAdmin();
        return objects;
    }
    //删除用户信息
    public boolean deleteUser(String userLoginName){
        connect = new DBConnector();
        boolean state = connect.DeleteUser(userLoginName);
        return state;
    }
    //编辑用户信息
    public boolean EditUser(User user){
        connect = new DBConnector();
        boolean state = connect.EditUser(user);
        connect.close();
        return state;
    }
    //查询成绩
    public Object[][] getScoreQuery(String stuID, String courseID,int c){
        Object[][] objects;
        String stu,course;
        if(stuID==null||stuID.equals("")){
            stu = null;
        } else {
            stu = stuID;
        }
        if(courseID==null||courseID.equals("")){
            course = null;
        } else {
            course = courseID;
        }
        DBConnector connector = new DBConnector();
        objects = connector.QueryStudent(stu, course,c);
        return objects;
    }
    //添加成绩
    public boolean AddScore(String stuID, String courseName,String year, double scores){
        connect = new DBConnector();
        boolean state = connect.AddScore(stuID,courseName,year,scores);
        connect.close();
        return state;
    }
    //获取课程列表
    public Object[][] getCourseList(){
        connect = new DBConnector();
        Object[][] result = connect.getCourseList();
        connect.close();
        return result;
    }
    //删除成绩
    public boolean deleteScore(String stuID,String courseID){
        connect = new DBConnector();
        boolean state = connect.DeleteScore(stuID,courseID);
        return state;
    }
    //编辑成绩
    public boolean EditScore(String stuID,String courseID,String score){
        connect = new DBConnector();
        boolean state = connect.EditScore(stuID,courseID,score);
        connect.close();
        return state;
    }
}