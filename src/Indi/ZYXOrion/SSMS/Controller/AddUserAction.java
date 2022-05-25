package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Entity.User;
import Indi.ZYXOrion.SSMS.Frame.AdminMainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//����û�������
public class AddUserAction implements ActionListener {
    //�û�
    User user;
    //�������
    JTextField LoginName;
    JTextField Name;
    JPasswordField passwordField;
    JPasswordField rePasswordField;
    //�û�����
    String userLoginName;
    String userName;
    String userPassword;
    String userRePassword;
    int userLevel;
    //������
    JDialog fatherFrame;
    //����ˢ��Ŀ��
    AdminMainFrame refreshTarget;

    //ִ����Ӳ���
    @Override
    public void actionPerformed(ActionEvent e) {
        //�½��û���
        user = new User();
        //���ݿ⴦��
        DBProcessor dbProcessor = new DBProcessor();
        //������л�ȡ����
        userLoginName = LoginName.getText().trim();
        userName = Name.getText().trim();
        userPassword = new String(passwordField.getPassword());
        userRePassword = new String(rePasswordField.getPassword());
        //�����ݴ����û�
        setUser();
        //�жϿ�ֵ
        if(userLoginName.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "�û�������Ϊ�գ�","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
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
        //���ݿ�ִ����Ӳ���
        dbProcessor.AddUser(user);
        //ˢ�½���
        refreshTarget.refresh();
        //ִ�к�رճ���
        fatherFrame.dispose();
    }
    //���ϲ�����level
    public void setLevel(int level){
        userLevel = level;
    }
    //���ϲ���������
    public void setInput(JTextField userLoginName,JTextField userName, JPasswordField userPassword, JPasswordField userRePassword) {
        LoginName = userLoginName;
        Name = userName;
        passwordField = userPassword;
        rePasswordField = userRePassword;
    }
    //�����ݴ����û�
    private void setUser(){
        user.setUsername(userLoginName);
        user.setName(userName);
        user.setPassword(userPassword);
        user.setLevel(userLevel);
    }
    //���ø�����
    public void setFatherFrame(JDialog frame){
        fatherFrame = frame;
    }
    //����ˢ�¶���
    public void setRefreshTarget(AdminMainFrame target){
        refreshTarget = target;
    }
}
