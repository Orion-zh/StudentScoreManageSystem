package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.*;
import Indi.ZYXOrion.SSMS.Entity.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//老师主类函数
public class TeacherMainFrame extends JFrame {
    //组件
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
    private DeleteScoreAction deleteScoreAction;
    //构造函数
    public TeacherMainFrame(User user){
        this.setTitle("学生成绩管理系统-教师");
        this.setIconImage(new ImageIcon("/Img/Icon1.png").getImage());
        this.setBounds(475,240,930,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setButtonPanel();
        setEdge();
        setQueryArea();
        setButtons(user);
        setCourseList();
        this.setVisible(true);
    }
    //设置Panel
    private void setButtonPanel(){
        buttonPanel = new JPanel(new FlowLayout());
        this.add(buttonPanel,BorderLayout.NORTH);
    }
    //设置按钮
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
        //添加操作
        addScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddScoreFrame addUserFrame = new AddScoreFrame(jf);
            }
        });
        //删除操作
        deleteScoreAction = new DeleteScoreAction(this,studentScore.getQueryResult());
        //编辑操作
        editScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //EditScoreFrame的构造函数
                EditScoreFrame editScoreFrame = new EditScoreFrame(jf,
                        queryResult.getValueAt(queryResult.getSelectedRow(),4).toString(),
                        queryResult.getValueAt(queryResult.getSelectedRow(),0).toString(),
                        queryResult.getValueAt(queryResult.getSelectedRow(),1).toString(),
                        queryResult.getValueAt(queryResult.getSelectedRow(),2).toString());
            }
        });
        cancelScore.addActionListener(deleteScoreAction);
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //如果是全部则置特定查询字段为空
                String course = coursename.getSelectedItem().toString();
                if(course.equals("全部")) course=null;
                studentScore.Refresh(stuID.getText(),course,0);
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

        JButton AscButton = new JButton("按成绩升序排列");
        JButton DescButton = new JButton("按成绩降序排列");
        AscButton.setPreferredSize(new Dimension(150,30));
        DescButton.setPreferredSize(new Dimension(150,30));
        AscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentScore.Refresh(stuID.getText(), coursename.getSelectedItem().toString(),1);
            }
        });
        DescButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                studentScore.Refresh(stuID.getText(), coursename.getSelectedItem().toString(),2);
            }
        });
        panelButton.add(AscButton);
        panelButton.add(DescButton);
        panelButton.add(new JLabel("欢迎您!"));
        panelButton.add(new JLabel(user.getUsername()));
    }
    //设置数据表
    private void setQueryArea(){
        queryArea = new JScrollPane();
        studentScore = new QueryStudentScore(null);
        queryResult = studentScore.getQueryResult();
        queryArea.getViewport().add(queryResult);
        queryArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(queryArea,BorderLayout.CENTER);
    }
    //获取课程列表，添加到下拉框中
    private void setCourseList(){
        coursename.addItem("全部");
        Object[][] courseList = studentScore.getCourseList();
        for(int i=0;i<courseList.length;i++){
            coursename.addItem(courseList[i][1]);
        }
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
    public void refresh(){
        studentScore.Refresh(null, null,0);
    }
}
