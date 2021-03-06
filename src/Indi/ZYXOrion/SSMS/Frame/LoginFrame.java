package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.LoginAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//设置登录界面
public class LoginFrame extends JFrame{
    //组件
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JTextField usernameText;
    private JPasswordField passwordText;
    private JButton loginButton;
    private JLabel labelImage;
    private ButtonGroup privilege;
    private JRadioButton studentButton;
    private JRadioButton teacherButton;
    private JRadioButton adminButton;
    private Container con;
    private LoginAction loginAction;
    private LoginFrame jf = this;
    private int level = 1;

    //构造函数
    public LoginFrame(){
        //组件
        con = getContentPane();
        con.setBackground(Color.white);
        //设置各部分
        setImg();
        setInput();
        setChooser();
        setButton();
        Init();
    }
    //设置界面信息
    private void Init(){
        this.setTitle("学生成绩管理系统");
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Img/Icon1.png")).getImage());
        this.setLayout(null);
        this.setBounds(540,240,800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    //设置标题图片
    private void setImg(){
        labelImage = new JLabel(new ImageIcon(this.getClass().getResource("/Img/Icon2.png")));
        labelImage.setBounds(200,50,400,130);
        this.con.add(labelImage);
    }
    //设置输入组件
    private void setInput(){
        usernameLabel = new JLabel("用户名：");
        usernameLabel.setBounds(295, 230, 60, 30);
        this.con.add(usernameLabel);
        usernameText = new JTextField();
        usernameText.setBounds(355, 230, 150, 30);
        this.con.add(usernameText);
        passwordLabel = new JLabel("密码:");
        passwordLabel.setBounds(295, 280, 60, 30);
        this.con.add(passwordLabel);
        passwordText = new JPasswordField();
        passwordText.setBounds(355, 280, 150, 30);
        this.con.add(passwordText);
    }
    //设置权限选择
    private void setChooser(){
        privilege = new ButtonGroup();
        studentButton = new JRadioButton("学生");
        privilege.add(studentButton);
        studentButton.setBounds(295,330,80,30);
        studentButton.setBackground(Color.white);
        studentButton.setSelected(true);
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction.setLevel(1);
            }
        });
        this.con.add(studentButton);

        teacherButton = new JRadioButton("老师");
        privilege.add(teacherButton);
        teacherButton.setBounds(370,330,60,30);
        teacherButton.setBackground(Color.white);
        teacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction.setLevel(2);
            }
        });
        this.con.add(teacherButton);

        adminButton = new JRadioButton("管理员");
        privilege.add(adminButton);
        adminButton.setBounds(445,330,80,30);
        adminButton.setBackground(Color.white);
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginAction.setLevel(3);
            }
        });
        this.con.add(adminButton);
    }
    //设置登录
    private void setButton(){
        //按键
        loginAction = new LoginAction();
        loginAction.setLevel(1);
        loginAction.fatherFrame(jf);
        loginAction.setUsernameText(usernameText);
        loginAction.setPasswordText(passwordText);
        loginButton = new JButton("登录");
        loginButton.setBounds(370, 380, 60, 30);
        loginButton.addActionListener(loginAction);
        this.con.add(loginButton);

    }
}
