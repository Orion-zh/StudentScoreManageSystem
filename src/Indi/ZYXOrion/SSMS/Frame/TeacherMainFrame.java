package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.*;
import Indi.ZYXOrion.SSMS.Entity.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//��ʦ���ຯ��
public class TeacherMainFrame extends JFrame {
    //���
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
    //���캯��
    public TeacherMainFrame(User user){
        this.setTitle("ѧ���ɼ�����ϵͳ-��ʦ");
        this.setIconImage(new ImageIcon("Img/Icon1.png").getImage());
        this.setBounds(475,240,930,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setButtonPanel();
        setEdge();
        setQueryArea();
        setButtons(user);
        setCourseList();
        this.setVisible(true);
    }
    //����Panel
    private void setButtonPanel(){
        buttonPanel = new JPanel(new FlowLayout());
        this.add(buttonPanel,BorderLayout.NORTH);
    }
    //���ð�ť
    private void setButtons(User user){
        JButton addScore = new JButton("�����ɼ���Ϣ");
        JButton editScore = new JButton("�޸ĳɼ���Ϣ");
        JButton cancelScore = new JButton("ɾ���ɼ���Ϣ");
        JLabel courseQuery = new JLabel("�γ�����");
        JButton confirm = new JButton("��ѯ");
        coursename = new JComboBox();
        coursename.setBackground(Color.white);
        JLabel stuQuery = new JLabel("ѧ�ţ�");
        stuID = new JTextField();
        addScore.setPreferredSize(new Dimension(120,40));
        editScore.setPreferredSize(new Dimension(120,40));
        cancelScore.setPreferredSize(new Dimension(120,40));
        confirm.setPreferredSize(new Dimension(120,30));
        coursename.setPreferredSize(new Dimension(120,30));
        courseQuery.setPreferredSize(new Dimension(60,40));
        stuQuery.setPreferredSize(new Dimension(40,40));
        stuID.setPreferredSize(new Dimension(90,30));
        //��Ӳ���
        addScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddScoreFrame addUserFrame = new AddScoreFrame(jf);
            }
        });
        //ɾ������
        deleteScoreAction = new DeleteScoreAction(this,studentScore.getQueryResult());
        //�༭����
        editScore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //EditScoreFrame�Ĺ��캯��
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
                //�����ȫ�������ض���ѯ�ֶ�Ϊ��
                String course = coursename.getSelectedItem().toString();
                if(course.equals("ȫ��")) course=null;
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

        JButton AscButton = new JButton("���ɼ���������");
        JButton DescButton = new JButton("���ɼ���������");
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
        panelButton.add(new JLabel("��ӭ��!"));
        panelButton.add(new JLabel(user.getUsername()));
    }
    //�������ݱ�
    private void setQueryArea(){
        queryArea = new JScrollPane();
        studentScore = new QueryStudentScore(null);
        queryResult = studentScore.getQueryResult();
        queryArea.getViewport().add(queryResult);
        queryArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(queryArea,BorderLayout.CENTER);
    }
    //��ȡ�γ��б���ӵ���������
    private void setCourseList(){
        coursename.addItem("ȫ��");
        Object[][] courseList = studentScore.getCourseList();
        for(int i=0;i<courseList.length;i++){
            coursename.addItem(courseList[i][1]);
        }
    }
    //���ñ߿�
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
