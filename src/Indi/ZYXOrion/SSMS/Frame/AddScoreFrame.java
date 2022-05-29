package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.AddScoreAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//添加成绩界面
public class AddScoreFrame extends JDialog {
    //对应的操作对象
    AddScoreAction addScoreAction = new AddScoreAction();
    //界面组件
    TeacherMainFrame fatherFrame;
    JTextField stuIDText;
    JComboBox courseText;
    JTextField scoreText;
    JTextField openYear;
    //构造函数
    public AddScoreFrame(TeacherMainFrame mainFrame){
        //设置界面基础信息
        this.setTitle("创建成绩信息");
        this.setIconImage(new ImageIcon("/Img/Icon1.png").getImage());
        this.setBounds(800,270,320,540);
        this.setLayout(null);
        //设置界面的父界面和刷新界面
        fatherFrame = mainFrame;
        addScoreAction.setRefreshTarget(fatherFrame);
        addScoreAction.setFatherFrame(this);
        setInput();
        setConboBox();
        setConfirm();
        //可改大小，显示
        this.setResizable(false);
        this.setVisible(true);
    }
    //设置课程选择下拉框
    private void setConboBox(){
        Object[][] courseList = addScoreAction.getCourseList();
        for(int i=0;i<courseList.length;i++){
            courseText.addItem(courseList[i][1]);
        }
    }
    //设置输入组件
    private void setInput(){
        JLabel stuIDLabel = new JLabel("学号：");
        stuIDText = new JTextField();
        JLabel courseLabel = new JLabel("课程：");
        courseText = new JComboBox();
        JLabel yearLable = new JLabel("开课学期：");
        openYear = new JTextField();
        JLabel scoreLabel = new JLabel("成绩：");
        scoreText = new JTextField();
        stuIDLabel.setBounds(45,50,60,30);
        stuIDText.setBounds(115,50,150,30);
        this.add(stuIDLabel);
        this.add(stuIDText);
        courseLabel.setBounds(45,100,60,30);
        courseText.setBounds(115,100,150,30);
        courseText.setBackground(Color.white);
        this.add(courseLabel);
        this.add(courseText);
        yearLable.setBounds(40,150,65,30);
        openYear.setBounds(115,150,150,30);
        this.add(yearLable);
        this.add(openYear);
        scoreLabel.setBounds(45,200,60,30);
        scoreText.setBounds(115,200,150,30);
        this.add(scoreLabel);
        this.add(scoreText);

    }
    //设置确认按钮
    private void setConfirm(){
        JButton confirm = new JButton("确认");
        JButton cancel = new JButton("取消");
        confirm.setBounds(60,300,70,50);
        cancel.setBounds(190,300,70,50);
        this.add(confirm);
        this.add(cancel);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addScoreAction.setInput(stuIDText.getText(),addScoreAction.getCourseID(courseText.getSelectedItem().toString()),openYear.getText(),scoreText.getText());
                addScoreAction.action();
            }
        });
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
