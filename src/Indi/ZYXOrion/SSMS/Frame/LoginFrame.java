package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.LoginAction;
import Indi.ZYXOrion.SSMS.Controller.RegisterAction;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame{
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JButton registerButton;
    private JLabel labelImage;
    private ButtonGroup privilege;
    private JRadioButton studentButton;
    private JRadioButton teacherButton;
    private JRadioButton adminButton;
    private Container con;
    public LoginFrame(){
        //组件
        con = getContentPane();
        con.setBackground(Color.white);
        //设置各部分
        setImg();
        setInput();
        setChooser();
        setButton();
        //初始化窗口
        Init();
    }
    private void Init(){
        this.setTitle("学生成绩管理系统");
        this.setLayout(null);
        this.setBounds(540,240,800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    private void setImg(){
        labelImage = new JLabel(new ImageIcon("Img/Icon2.png"));
        labelImage.setBounds(200,50,400,130);
        this.con.add(labelImage);
    }
    private void setInput(){
        //用户名
        usernameLabel = new JLabel("用户名：");
        usernameLabel.setBounds(295, 230, 60, 30);
        this.con.add(usernameLabel);
        usernameText = new JTextField();
        usernameText.setBounds(355, 230, 150, 30);
        this.con.add(usernameText);
        //密码
        passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(295, 280, 60, 30);
        this.con.add(passwordLabel);
        passwordText = new JPasswordField();
        passwordText.setBounds(355, 280, 150, 30);
        this.con.add(passwordText);
    }
    private void setChooser(){
        privilege = new ButtonGroup();

        studentButton = new JRadioButton("学生");
        privilege.add(studentButton);
        studentButton.setBounds(295,330,80,30);
        studentButton.setBackground(Color.white);
        studentButton.setSelected(true);
        this.con.add(studentButton);

        teacherButton = new JRadioButton("老师");
        privilege.add(teacherButton);
        teacherButton.setBounds(370,330,60,30);
        teacherButton.setBackground(Color.white);
        this.con.add(teacherButton);

        adminButton = new JRadioButton("管理员");
        privilege.add(adminButton);
        adminButton.setBounds(445,330,80,30);
        adminButton.setBackground(Color.white);
        this.con.add(adminButton);
    }
    private void setButton(){
        //按键
        LoginAction loginAction = new LoginAction();
        loginAction.setUsernameText(usernameText);
        loginAction.setPasswordText(passwordText);
        loginButton = new JButton("登录");
        loginButton.setBounds(295, 380, 60, 30);
        loginButton.addActionListener(loginAction);
        this.con.add(loginButton);
        registerButton = new JButton("注册");
        registerButton.setBounds(465, 380, 60, 30);
        registerButton.addActionListener(new RegisterAction());
        this.con.add(registerButton);
    }
}
