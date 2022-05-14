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
        connect.close();
        return state;
    }
    //检查是否已经登陆的方法
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
    //更新登陆状态
    public boolean UpdateIsLogin(User user) {
        connect = new DBConnector();
        boolean state = connect.update_IsLogin(user);
        connect.close();//关闭资源
        return state;
    }

}