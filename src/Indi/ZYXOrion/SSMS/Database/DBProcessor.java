package Indi.ZYXOrion.SSMS.Database;

import Indi.ZYXOrion.SSMS.Entity.*;

//���ݿ�ҵ������
public class DBProcessor {
    private DBConnector connect;
    //��¼
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
    //��ȡ�û���Ϣ
    public User getUser(User user){
        connect = new DBConnector();
        User DBuser =  connect.getUser(user);
        return DBuser;
    }
    //�����û�
    public boolean AddUser(User user){
        connect = new DBConnector();
        boolean state = connect.AddUser(user);
        connect.close();
        return state;
    }
    //��ѯ�û���Ϣ
    public Object[][] getAdminQuery(){
        Object[][] objects;
        DBConnector connector = new DBConnector();
        objects = connector.QueryAdmin();
        return objects;
    }
    //ɾ���û���Ϣ
    public boolean deleteUser(String userLoginName){
        connect = new DBConnector();
        boolean state = connect.DeleteUser(userLoginName);
        return state;
    }
    //�༭�û���Ϣ
    public boolean EditUser(User user){
        connect = new DBConnector();
        boolean state = connect.EditUser(user);
        connect.close();
        return state;
    }
    //��ѯ�ɼ�
    public Object[][] getScoreQuery(String stuID, String courseID){
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
        objects = connector.QueryStudent(stu, course);
        return objects;
    }
    //��ӳɼ�
    public boolean AddScore(String stuID, String courseName,String year, double scores){
        connect = new DBConnector();
        boolean state = connect.AddScore(stuID,courseName,year,scores);
        connect.close();
        return state;
    }
    //��ȡ�γ��б�
    public Object[][] getCourseList(){
        connect = new DBConnector();
        Object[][] result = connect.getCourseList();
        connect.close();
        return result;
    }
    //ɾ���ɼ�
    public boolean deleteScore(String stuID,String courseID){
        connect = new DBConnector();
        boolean state = connect.DeleteScore(stuID,courseID);
        return state;
    }
    //�༭�ɼ�
    public boolean EditScore(String stuID,String courseID,String score){
        connect = new DBConnector();
        boolean state = connect.EditScore(stuID,courseID,score);
        connect.close();
        return state;
    }
}