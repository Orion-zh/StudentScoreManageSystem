package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.EditUserAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//�༭�û�����
public class EditUserFrame extends JDialog {
    //�༭�û�������
    EditUserAction editUserAction = new EditUserAction();
    AdminMainFrame fatherFrame;
    String userLoginName;
    //���캯��
    public EditUserFrame(AdminMainFrame mainFrame,String userLoginname){
        this.setTitle("�޸��û���Ϣ");
        this.setIconImage(new ImageIcon("Img/Icon1.png").getImage());
        this.setBounds(800,270,320,540);
        this.setLayout(null);
        this.userLoginName = userLoginname;
        fatherFrame = mainFrame;
        editUserAction.setRefreshTarget(fatherFrame);
        editUserAction.setFatherFrame(this);
        editUserAction.setLevel(1);
        setInput();
        setChooser();
        setConfirm();
        this.setResizable(false);
        this.setVisible(true);
    }
    //�������
    private void setInput(){
        JLabel usernameLabel = new JLabel("�û�����");
        JLabel usernameText = new JLabel(userLoginName);
        JLabel nameLabel = new JLabel("������");
        JTextField nameText = new JTextField();
        JLabel passwordLabel = new JLabel("���룺");
        JPasswordField passwordText = new JPasswordField();
        JLabel rePasswordLabel = new JLabel("ȷ�����룺");
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
        editUserAction.setInput(usernameText,nameText,passwordText,rePasswordText);
    }
    //����Ȩ�����
    private void setChooser(){
        JLabel levelLabel = new JLabel("Ȩ�ޣ�");
        levelLabel.setBounds(40,250,40,30);
        this.add(levelLabel);
        ButtonGroup levelGroup = new ButtonGroup();
        JRadioButton studentButton = new JRadioButton("ѧ��");
        JRadioButton teacherButton = new JRadioButton("��ʦ");
        JRadioButton adminButton = new JRadioButton("����Ա");
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
                editUserAction.setLevel(1);
            }
        });
        teacherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUserAction.setLevel(2);
            }
        });
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editUserAction.setLevel(3);
            }
        });
        this.add(studentButton);
        this.add(teacherButton);
        this.add(adminButton);
    }
    //����ȷ�ϰ���
    private void setConfirm(){
        JButton confirm = new JButton("ȷ��");
        JButton cancel = new JButton("ȡ��");
        confirm.setBounds(60,300,70,50);
        cancel.setBounds(190,300,70,50);
        this.add(confirm);
        this.add(cancel);
        confirm.addActionListener(editUserAction);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
