package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.EditScoreAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//�༭�ɼ�����
public class EditScoreFrame extends JDialog {
    //�༭�ɼ�������
    EditScoreAction editScoreAction = new EditScoreAction();
    //��������
    TeacherMainFrame fatherFrame;
    String stuID;
    String courseID;
    String courseName;
    String courseYear;
    //���캯�������뺯��
    public EditScoreFrame(TeacherMainFrame mainFrame,String stuID, String courseID,String courseName, String courseYear){
        this.setTitle("�޸ĳɼ���Ϣ");
        this.setIconImage(new ImageIcon(this.getClass().getResource("/Img/Icon1.png")).getImage());
        this.setBounds(800,270,320,540);
        this.setLayout(null);
        this.stuID=stuID;
        this.courseID=courseID;
        this.courseName=courseName;
        this.courseYear=courseYear;
        fatherFrame = mainFrame;
        editScoreAction.setRefreshTarget(fatherFrame);
        editScoreAction.setFatherFrame(this);
        setInput();
        setConfirm();
        this.setResizable(false);
        this.setVisible(true);
    }
    //�������
    private void setInput(){
        JLabel stuLabel = new JLabel("ѧ�ţ�");
        JLabel stuText = new JLabel(stuID);
        JLabel courseLabel = new JLabel("�γ̣�");
        JLabel courseText = new JLabel(courseName);
        JLabel yearLabel = new JLabel("����ѧ�ڣ�");
        JLabel yearText = new JLabel(courseYear);
        JLabel scoreLabel = new JLabel("�ɼ���");
        JTextField scoreText = new JTextField();
        stuLabel.setBounds(45,50,60,30);
        stuText.setBounds(115,50,150,30);
        this.add(stuLabel);
        this.add(stuText);
        courseLabel.setBounds(45,100,60,30);
        courseText.setBounds(115,100,150,30);
        this.add(courseLabel);
        this.add(courseText);
        yearLabel.setBounds(40,150,65,30);
        yearText.setBounds(115,150,150,30);
        this.add(yearLabel);
        this.add(yearText);
        scoreLabel.setBounds(45,200,75,30);
        scoreText.setBounds(115,200,150,30);
        this.add(scoreLabel);
        this.add(scoreText);
        editScoreAction.setInput(stuID,courseID,scoreText);
    }
    //����ȷ�ϰ�ť
    private void setConfirm(){
        JButton confirm = new JButton("ȷ��");
        JButton cancel = new JButton("ȡ��");
        confirm.setBounds(60,300,70,50);
        cancel.setBounds(190,300,70,50);
        this.add(confirm);
        this.add(cancel);
        confirm.addActionListener(editScoreAction);
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
