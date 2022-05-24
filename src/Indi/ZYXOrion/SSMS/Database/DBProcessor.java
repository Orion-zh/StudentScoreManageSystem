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
    public Object[][] getAdminQuery(){
        Object[][] objects;
        DBConnector connector = new DBConnector();
        objects = connector.QueryAdmin();
        return objects;
    }
    public boolean deleteUser(String userLoginName){
        connect = new DBConnector();
        boolean state = connect.DeleteUser(userLoginName);
        return state;
    }
    public boolean EditUser(User user){
        connect = new DBConnector();
        boolean state = connect.EditUser(user);
        connect.close();
        return state;
    }
    public Object[][] getStudentQuery(String stuID){
        Object[][] objects;
        DBConnector connector = new DBConnector();
        objects = connector.QueryStudent(stuID);
        return objects;
    }

}