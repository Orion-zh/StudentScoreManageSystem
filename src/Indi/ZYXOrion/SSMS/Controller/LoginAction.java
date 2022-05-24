package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Entity.User;
import Indi.ZYXOrion.SSMS.Frame.AdminMainFrame;
import Indi.ZYXOrion.SSMS.Frame.LoginFrame;
import Indi.ZYXOrion.SSMS.Frame.StudentMainFrame;
import Indi.ZYXOrion.SSMS.Frame.TeacherMainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginAction implements ActionListener {
    private JTextField usernameText;
    private JPasswordField passwordText;
    private int level;
    private LoginFrame jframe;
    public void setUsernameText(JTextField username){
        usernameText = username;
    }
    public void setPasswordText(JPasswordField password){
        passwordText = password;
    }
    public void setLevel(int level){
        this.level=level;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //读取
        String username = usernameText.getText().trim();
        String password = new String(passwordText.getPassword());
        JFrame jf = new JFrame("错误");
        if(username.equals("")){
            JOptionPane.showMessageDialog(jf, "用户名不能为空","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if(password.equals("")){
            JOptionPane.showMessageDialog(jf, "密码不能为空","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        //登录业务处理
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setLevel(level);
        DBProcessor processor = new DBProcessor();
        if(processor.Login(user)){
                JOptionPane.showMessageDialog(jf, "登陆成功！");
                jf.dispose();
                user.setPassword("");
                if(level==1) {
                    StudentMainFrame frame = new StudentMainFrame(processor.getUser(user));
                }
                else if(level==2){
                    TeacherMainFrame frame = new TeacherMainFrame();
                }
                else if(level==3){
                    AdminMainFrame frame = new AdminMainFrame(processor.getUser(user));
                }
                jframe.dispose();
                return ;
        }else{
            JOptionPane.showMessageDialog(jf, "用户名、密码或权限错误！");
            Reset();
            return ;
        }
    }
    public void Reset(){
        usernameText.setText("");
        passwordText.setText("");
    }
    public void fatherFrame(LoginFrame frame){
        jframe = frame;
    }
}
