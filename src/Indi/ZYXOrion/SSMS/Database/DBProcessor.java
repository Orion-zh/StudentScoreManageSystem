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
    public User getUser(User user){
        return connect.getUser(user);
    }
    //创建用户
    public boolean AddUser(User user){
        connect = new DBConnector();
        connect.AddUser(user);
        return true;
    }
}