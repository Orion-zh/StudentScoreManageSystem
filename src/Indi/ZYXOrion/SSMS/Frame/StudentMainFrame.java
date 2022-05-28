package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.*;
import Indi.ZYXOrion.SSMS.Entity.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//学生主界面
public class StudentMainFrame extends JFrame {
    //组件
    private JPanel buttonPanel;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelButton;
    private JScrollPane queryArea;
    private QueryStudentScore scores;
    private JTable queryResult;
    private String stuID;
    //构造函数
    public StudentMainFrame(User user){
        stuID=user.getUsername();
        this.setTitle("学生成绩管理系统-学生");
        this.setIconImage(new ImageIcon("Img/Icon1.png").getImage());
        this.setBounds(540,240,800,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setButtonPanel();
        setQueryArea();
        setButtons(user);
        setEdge();
        this.setVisible(true);
    }
    //设置Panel
    private void setButtonPanel(){
        buttonPanel = new JPanel(new FlowLayout());
        this.add(buttonPanel,BorderLayout.NORTH);
    }
    //设置按钮
    private void setButtons(User user){
        JButton freshInfo = new JButton("刷新");
        freshInfo.setPreferredSize(new Dimension(120,40));
        freshInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        buttonPanel.add(freshInfo);
        buttonPanel.add(new JLabel("欢迎您!"));
        buttonPanel.add(new JLabel(user.getUsername()));
        buttonPanel.add(new JLabel(String.format("          您的GPA是：%.2f",scores.countGPA())));
    }
    //设置查询数据表
    private void setQueryArea(){
        queryArea = new JScrollPane();
        scores = new QueryStudentScore(stuID);
        queryResult = scores.getQueryResult();
        queryArea.getViewport().add(queryResult);
        queryArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(queryArea,BorderLayout.CENTER);
    }
    //设置边框
    private void setEdge(){
        panelLeft = new JPanel();
        panelRight = new JPanel();
        panelButton = new JPanel();
        panelLeft.setPreferredSize(new Dimension(50,600));
        panelRight.setPreferredSize(new Dimension(50,600));
        panelButton.setPreferredSize(new Dimension(800,50));
        this.add(panelLeft,BorderLayout.WEST);
        this.add(panelRight,BorderLayout.EAST);
        this.add(panelButton,BorderLayout.SOUTH);
    }
    //刷新函数
    public void refresh(){
        scores.Refresh(stuID,null,0);
    }
}
