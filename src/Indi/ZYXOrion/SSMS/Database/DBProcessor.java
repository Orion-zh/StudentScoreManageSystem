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
    //����Ƿ��Ѿ���½�ķ���
    public boolean CheckIsLogin(User user) {
        boolean state = true;
        connect = new DBConnector();
        User newUser = connect.getUser(user);
        if (newUser.getIsLogin() == 0) {
            state = false;
        }
        connect.close();
        return state;
    }
    //���µ�½״̬
    public boolean UpdateIsLogin(User user) {
        connect = new DBConnector();
        boolean state = connect.update_IsLogin(user);
        connect.close();//�ر���Դ
        return state;
    }

}