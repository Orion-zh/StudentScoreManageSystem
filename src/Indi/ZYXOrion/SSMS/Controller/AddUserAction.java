package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserAction implements ActionListener {
    User user;
    JTextField LoginName;
    JTextField Name;
    JPasswordField passwordField;
    JPasswordField rePasswordField;
    String userLoginName;
    String userName;
    String userPassword;
    String userRePassword;
    int userLevel;
    JDialog fatherFrame;
    @Override
    public void actionPerformed(ActionEvent e) {
        user = new User();
        DBProcessor dbProcessor = new DBProcessor();
        userLoginName = LoginName.getText().trim();
        userName = Name.getText().trim();
        userPassword = new String(passwordField.getPassword());
        userRePassword = new String(rePasswordField.getPassword());
        setUser();
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
        dbProcessor.AddUser(user);
        fatherFrame.dispose();
    }
    public void setLevel(int level){
        userLevel = level;
    }
    public void setInput(JTextField userLoginName,JTextField userName, JPasswordField userPassword, JPasswordField userRePassword) {
        LoginName = userLoginName;
        Name = userName;
        passwordField = userPassword;
        rePasswordField = userRePassword;
    }
    private void setUser(){
        user.setUsername(userLoginName);
        user.setName(userName);
        user.setPassword(userPassword);
        user.setLevel(userLevel);
    }
    public void setFatherFrame(JDialog frame){
        fatherFrame = frame;
    }
}
