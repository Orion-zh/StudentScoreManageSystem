package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Entity.User;
import Indi.ZYXOrion.SSMS.Frame.AdminMainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//编辑用户类
public class EditUserAction implements ActionListener {
    //用户
    User user;
    //界面组件
    JTextField Name;
    JPasswordField passwordField;
    JPasswordField rePasswordField;
    //界面数据
    String userLoginName;
    String userName;
    String userPassword;
    String userRePassword;
    int userLevel;
    //父界面
    JDialog fatherFrame;
    //刷新目标
    AdminMainFrame refreshTarget;

    //执行编辑用户操作
    @Override
    public void actionPerformed(ActionEvent e) {
        user = new User();
        //数据库联机
        DBProcessor dbProcessor = new DBProcessor();
        //设置用户数据
        userName = Name.getText().trim();
        userPassword = new String(passwordField.getPassword());
        userRePassword = new String(rePasswordField.getPassword());
        //把数据生成用户类
        setUser();
        //判断空值
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
        //数据库执行编辑操作
        dbProcessor.EditUser(user);
        //刷新界面
        refreshTarget.refresh();
        fatherFrame.dispose();
    }
    //上层设置level
    public void setLevel(int level){
        userLevel = level;
    }
    //上层设置输入
    public void setInput(JLabel userLoginName,JTextField userName, JPasswordField userPassword, JPasswordField userRePassword) {
        this.userLoginName = userLoginName.getText().trim();
        Name = userName;
        passwordField = userPassword;
        rePasswordField = userRePassword;
    }
    //数据生成用户类
    private void setUser(){
        user.setUsername(userLoginName);
        user.setName(userName);
        user.setPassword(userPassword);
        user.setLevel(userLevel);
    }
    public void setFatherFrame(JDialog frame){
        fatherFrame = frame;
    }
    public void setRefreshTarget(AdminMainFrame target){
        refreshTarget = target;
    }
}
