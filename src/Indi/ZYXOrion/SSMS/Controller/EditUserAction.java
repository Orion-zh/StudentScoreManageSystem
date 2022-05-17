package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Entity.User;
import Indi.ZYXOrion.SSMS.Frame.AdminMainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EditUserAction implements ActionListener {
    User user;
    JTextField Name;
    JPasswordField passwordField;
    JPasswordField rePasswordField;
    String userLoginName;
    String userName;
    String userPassword;
    String userRePassword;
    int userLevel;
    JDialog fatherFrame;
    AdminMainFrame refreshTarget;
    @Override
    public void actionPerformed(ActionEvent e) {
        user = new User();
        DBProcessor dbProcessor = new DBProcessor();
        userName = Name.getText().trim();
        userPassword = new String(passwordField.getPassword());
        userRePassword = new String(rePasswordField.getPassword());
        setUser();
        if(userName.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "��������Ϊ�գ�","",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(userPassword.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "���벻��Ϊ�գ�","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if(userRePassword.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "ȷ�����벻��Ϊ�գ�","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if(!userPassword.equals(userRePassword)){
            JOptionPane.showMessageDialog(fatherFrame, "�������벻һ�£�","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        dbProcessor.EditUser(user);
        refreshTarget.refresh();
        fatherFrame.dispose();
    }
    public void setLevel(int level){
        userLevel = level;
    }
    public void setInput(JLabel userLoginName,JTextField userName, JPasswordField userPassword, JPasswordField userRePassword) {
        this.userLoginName = userLoginName.getText().trim();
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
    public void setRefreshTarget(AdminMainFrame target){
        refreshTarget = target;
    }
}
