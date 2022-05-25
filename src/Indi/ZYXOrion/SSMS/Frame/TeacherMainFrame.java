package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.*;
import Indi.ZYXOrion.SSMS.Entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherMainFrame extends JFrame {
    private JPanel buttonPanel;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelButton;
    private JScrollPane queryArea;
    private QueryStudentScore studentScore;
    private JTable queryResult;
    private TeacherMainFrame jf = this;
    private JTextField stuID;
    private JComboBox coursename;
    public TeacherMainFrame(User user){
        this.setTitle("学生成绩管理系统-教师");
        this.setIconImage(new ImageIcon("Img/Icon1.png").getImage());
        this.setBounds(475,240,930,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setButtonPanel();
        setEdge();
        setButtons(user);
        setQueryArea();
        this.setVisible(true);
    }
    private void setButtonPanel(){
        buttonPanel = new JPanel(new FlowLayout());
        this.add(buttonPanel,BorderLayout.NORTH);
    }
    private void setButtons(User user){
        JButton addScore = new JButton("新增成绩信息");
        JButton editScore = new JButton("修改成绩信息");
        JButton cancelScore = new JButton("删除成绩信息");
        JLabel courseQuery = new JLabel("课程名：");
        JButton confirm = new JButton("查询");
        coursename = new JComboBox();
        coursename.setBackground(Color.white);
        JLabel stuQuery = new JLabel("学号：");
        stuID = new JTextField();
        addScore.setPreferredSize(new Dimension(120,40));
        editScore.setPreferredSize(new Dimension(120,40));
        cancelScore.setPreferredSize(new Dimension(120,40));
        confirm.setPreferredSize(new Dimension(120,30));
        coursename.setPreferredSize(new Dimension(120,30));
        courseQuery.setPreferredSize(new Dimension(60,40));
        stuQuery.setPreferredSize(new Dimension(40,40));
        stuID.setPreferredSize(new Dimension(90,30));
/*
        EditScoreAction editScoreAction = new EditScoreAction();
        deleteScoreAction = new DeleteScoreAction(this,adminInfo.getQueryResult());
*/
        addScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddScoreFrame addUserFrame = new AddScoreFrame(jf);
            }
        });
/*
        editScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditScoreFrame editScoreFrame = new EditScoreFrame(jf,queryResult.getValueAt(queryResult.getSelectedRow(),0).toString());
            }
        });

        cancelScore.addActionListener(deleteScoreAction);

 */
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String course = coursename.getSelectedItem().toString();
                if(course.equals("全部")) course=null;
                studentScore.Refresh(stuID.getText(),course);
            }
        });
        buttonPanel.add(addScore);
        buttonPanel.add(editScore);
        buttonPanel.add(cancelScore);
        buttonPanel.add(courseQuery);
        buttonPanel.add(coursename);
        buttonPanel.add(stuQuery);
        buttonPanel.add(stuID);
        buttonPanel.add(confirm);
        panelButton.add(new JLabel("欢迎您!"));
        panelButton.add(new JLabel(user.getUsername()));
    }
    private void setQueryArea(){
        queryArea = new JScrollPane();
        studentScore = new QueryStudentScore(null);
        queryResult = studentScore.getQueryResult();
        coursename.addItem("全部");
        Object[][] courseList = studentScore.getCourseList();
        for(int i=0;i<courseList.length;i++){
            coursename.addItem(courseList[i][1]);
        }
        queryArea.getViewport().add(queryResult);
        queryArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(queryArea,BorderLayout.CENTER);
    }
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
    public void refresh(){
        studentScore.Refresh(null, null);
    }
}
