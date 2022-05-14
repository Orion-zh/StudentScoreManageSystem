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
        //读取
        /*
        String username = usernameText.getText().trim();
        String password = new String(passwordText.getPassword());
        */
        String username = "test";
        String password = "test";
        JFrame jf = new JFrame("错误");
        if(username.equals("")){
            JOptionPane.showMessageDialog(jf, "用户名不能为空","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if(password.equals("")){
            JOptionPane.showMessageDialog(jf, "密码不能为空","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        //登录业务处理。
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        DBProcessor processor = new DBProcessor();
        if(processor.Login(user)){	//登陆业务处理
            if(processor.CheckIsLogin(user)){
                //JOptionPane.showMessageDialog(jf, "重复登陆！","",JOptionPane.WARNING_MESSAGE);
                //return ;
            }else{
                JOptionPane.showMessageDialog(jf, "登陆成功！");
                jf.dispose();
                user.setIsLogin(1);
                processor.UpdateIsLogin(user);
                user.setPassword("");
                System.out.println("新建一个窗口");
                //StudentMainFrame frame = new StudentMainFrame(user);
                return ;
            }
        }else{
            JOptionPane.showMessageDialog(jf, "用户名或密码错误！");
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
