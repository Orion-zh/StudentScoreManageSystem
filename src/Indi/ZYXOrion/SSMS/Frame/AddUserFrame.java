package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.AddUserAction;
import Indi.ZYXOrion.SSMS.Entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserFrame extends JDialog {
    User user;
    public AddUserFrame(){
        this.setTitle("创建用户");
        this.setBounds(800,270,320,540);
        this.setLayout(null);
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
        JTextField passwordText = new JTextField();
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
    }
    private void setChooser(){
        JLabel levelLabel = new JLabel("权限：");
        levelLabel.setBounds(40,200,40,30);
        this.add(levelLabel);
        ButtonGroup levelGroup = new ButtonGroup();
        JRadioButton studentButton = new JRadioButton("学生");
        JRadioButton teacherButton = new JRadioButton("教师");
        JRadioButton adminButton = new JRadioButton("管理员");
        levelGroup.add(studentButton);
        levelGroup.add(teacherButton);
        levelGroup.add(adminButton);
        studentButton.setBounds(80,200,60,30);
        teacherButton.setBounds(140,200,60,30);
        adminButton.setBounds(200,200,80,30);
        this.add(studentButton);
        this.add(teacherButton);
        this.add(adminButton);
    }
    private void setConfirm(){
        JButton confirm = new JButton("确认");
        JButton cancel = new JButton("取消");
        confirm.setBounds(60,250,70,50);
        cancel.setBounds(190,250,70,50);
        this.add(confirm);
        this.add(cancel);
        AddUserAction addUserAction = new AddUserAction();
        confirm.addActionListener(addUserAction);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
