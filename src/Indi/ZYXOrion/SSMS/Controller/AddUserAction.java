package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Entity.User;
import Indi.ZYXOrion.SSMS.Frame.AdminMainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//添加用户操作类
public class AddUserAction implements ActionListener {
    //用户
    User user;
    //界面组件
    JTextField LoginName;
    JTextField Name;
    JPasswordField passwordField;
    JPasswordField rePasswordField;
    //用户数据
    String userLoginName;
    String userName;
    String userPassword;
    String userRePassword;
    int userLevel;
    //父界面
    JDialog fatherFrame;
    //设置刷新目标
    AdminMainFrame refreshTarget;

    //执行添加操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //新建用户类
        user = new User();
        //数据库处理
        DBProcessor dbProcessor = new DBProcessor();
        //从组件中获取数据
        userLoginName = LoginName.getText().trim();
        userName = Name.getText().trim();
        userPassword = new String(passwordField.getPassword());
        userRePassword = new String(rePasswordField.getPassword());
        //将数据存入用户
        setUser();
        //判断空值
        if(userLoginName.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "用户名不能为空！","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if(userName.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "姓名不能为空！","",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(userPassword.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "密码不能为空！","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if(userRePassword.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "确认密码不能为空！","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if(!userPassword.equals(userRePassword)){
            JOptionPane.showMessageDialog(fatherFrame, "两次密码不一致！","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        //数据库执行添加操作
        dbProcessor.AddUser(user);
        //刷新界面
        refreshTarget.refresh();
        //执行后关闭程序
        fatherFrame.dispose();
    }
    //由上层设置level
    public void setLevel(int level){
        userLevel = level;
    }
    //由上层设置输入
    public void setInput(JTextField userLoginName,JTextField userName, JPasswordField userPassword, JPasswordField userRePassword) {
        LoginName = userLoginName;
        Name = userName;
        passwordField = userPassword;
        rePasswordField = userRePassword;
    }
    //将数据存入用户
    private void setUser(){
        user.setUsername(userLoginName);
        user.setName(userName);
        user.setPassword(userPassword);
        user.setLevel(userLevel);
    }
    //设置父界面
    public void setFatherFrame(JDialog frame){
        fatherFrame = frame;
    }
    //设置刷新对象
    public void setRefreshTarget(AdminMainFrame target){
        refreshTarget = target;
    }
}
