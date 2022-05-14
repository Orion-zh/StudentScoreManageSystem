package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Entity.User;
import Indi.ZYXOrion.SSMS.Frame.StudentMainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAction implements ActionListener {
    private JTextField usernameText;
    private JPasswordField passwordText;
    @Override
    public void actionPerformed(ActionEvent e) {
        //��ȡ
        /*
        String username = usernameText.getText().trim();
        String password = new String(passwordText.getPassword());
        */
        String username = "test";
        String password = "test";
        JFrame jf = new JFrame("����");
        if(username.equals("")){
            JOptionPane.showMessageDialog(jf, "�û�������Ϊ��","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if(password.equals("")){
            JOptionPane.showMessageDialog(jf, "���벻��Ϊ��","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        //��¼ҵ����
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        DBProcessor processor = new DBProcessor();
        if(processor.Login(user)){	//��½ҵ����
            if(processor.CheckIsLogin(user)){
                //JOptionPane.showMessageDialog(jf, "�ظ���½��","",JOptionPane.WARNING_MESSAGE);
                //return ;
            }else{
                JOptionPane.showMessageDialog(jf, "��½�ɹ���");
                jf.dispose();
                user.setIsLogin(1);
                processor.UpdateIsLogin(user);
                user.setPassword("");
                System.out.println("�½�һ������");
                //StudentMainFrame frame = new StudentMainFrame(user);
                return ;
            }
        }else{
            JOptionPane.showMessageDialog(jf, "�û������������");
            Reset();
            return ;
        }
    }
    public void setText(JTextField username,JPasswordField password){
        this.usernameText = username;
        this.passwordText = password;
    }
    public void Reset(){
        //usernameText.setText("");
        //passwordText.setText("");
    }
}
