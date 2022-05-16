package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.AddUserAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserFrame extends JDialog {
    AddUserAction addUserAction = new AddUserAction();
    AdminMainFrame fatherFrame;
    public AddUserFrame(AdminMainFrame mainFrame){
        this.setTitle("创建用户");
        this.setIconImage(new ImageIcon("Img/Icon1.png").getImage());
        this.setBounds(800,270,320,540);
        this.setLayout(null);
        fatherFrame = mainFrame;
        addUserAction.setRefreshTarget(fatherFrame);
        addUserAction.setFatherFrame(this);
        addUserAction.setLevel(1);
        setInput();
        setChooser();
        setConfirm();
        this.setResizable(false);
        this.setVisible(true);
    }
    private void setInput(){
        JLabel usernameLabel = new JLabel("用户名：");
        JTextField usernameText = new JTextField();
        JLabel nameLabel = new JLabel("姓名：");
        JTextField nameText = new JTextField();
        JLabel passwordLabel = new JLabel("密码：");
        JPasswordField passwordText = new JPasswordField();
        JLabel rePasswordLabel = new JLabel("确认密码：");
        JPasswordField rePasswordText = new JPasswordField();
        usernameLabel.setBounds(45,50,60,30);
        usernameText.setBounds(115,50,150,30);
        this.add(usernameLabel);
        this.add(usernameText);
        nameLabel.setBounds(45,100,60,30);
        nameText.setBounds(115,100,150,30);
        this.add(nameLabel);
        this.add(nameText);
        passwordLabel.setBounds(45,150,60,30);
        passwordText.setBounds(115,150,150,30);
        this.add(passwordLabel);
        this.add(passwordText);
        rePasswordLabel.setBounds(30,200,75,30);
        rePasswordText.setBounds(115,200,150,30);
        this.add(rePasswordLabel);
        this.add(rePasswordText);
        addUserAction.setInput(usernameText,nameText,passwordText,rePasswordText);
    }
    private void setChooser(){
        JLabel levelLabel = new JLabel("权限：");
        levelLabel.setBounds(40,250,40,30);
        this.add(levelLabel);
        ButtonGroup levelGroup = new ButtonGroup();
        JRadioButton studentButton = new JRadioButton("学生");
        JRadioButton teacherButton = new JRadioButton("教师");
        JRadioButton adminButton = new JRadioButton("管理员");
        levelGroup.add(studentButton);
        levelGroup.add(teacherButton);
        levelGroup.add(adminButton);
        studentButton.setBounds(80,250,60,30);
        teacherButton.setBounds(140,250,60,30);
        adminButton.setBounds(200,250,80,30);
        studentButton.setSelected(true);
        studentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserAction.setLevel(1);
            }
        });
        teacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserAction.setLevel(2);
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addUserAction.setLevel(3);
            }
        });
        this.add(studentButton);
        this.add(teacherButton);
        this.add(adminButton);
    }
    private void setConfirm(){
        JButton confirm = new JButton("确认");
        JButton cancel = new JButton("取消");
        confirm.setBounds(60,300,70,50);
        cancel.setBounds(190,300,70,50);
        this.add(confirm);
        this.add(cancel);
        confirm.addActionListener(addUserAction);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
