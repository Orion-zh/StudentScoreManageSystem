package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.LoginAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private LoginAction loginAction;
    private int level = 1;
    public LoginFrame(){
        //���
        con = getContentPane();
        con.setBackground(Color.white);
        //���ø�����
        setImg();
        setInput();
        setChooser();
        setButton();
        //��ʼ������
        Init();
    }
    private void Init(){
        this.setTitle("ѧ���ɼ�����ϵͳ");
        this.setIconImage(new ImageIcon("Img/Icon1.png").getImage());
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
        //�û���
        usernameLabel = new JLabel("�û�����");
        usernameLabel.setBounds(295, 230, 60, 30);
        this.con.add(usernameLabel);
        usernameText = new JTextField();
        usernameText.setBounds(355, 230, 150, 30);
        this.con.add(usernameText);
        //����
        passwordLabel = new JLabel("����:");
        passwordLabel.setBounds(295, 280, 60, 30);
        this.con.add(passwordLabel);
        passwordText = new JPasswordField();
        passwordText.setBounds(355, 280, 150, 30);
        this.con.add(passwordText);
    }
    private void setChooser(){
        privilege = new ButtonGroup();
        studentButton = new JRadioButton("ѧ��");
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

        teacherButton = new JRadioButton("��ʦ");
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

        adminButton = new JRadioButton("����Ա");
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
    private void setButton(){
        //����
        loginAction = new LoginAction();
        loginAction.setUsernameText(usernameText);
        loginAction.setPasswordText(passwordText);
        loginButton = new JButton("��¼");
        loginButton.setBounds(370, 380, 60, 30);
        loginButton.addActionListener(loginAction);
        this.con.add(loginButton);

    }
}
