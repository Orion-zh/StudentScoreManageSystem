package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.*;
import Indi.ZYXOrion.SSMS.Entity.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ѧ��������
public class StudentMainFrame extends JFrame {
    //���
    private JPanel buttonPanel;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelButton;
    private JScrollPane queryArea;
    private QueryStudentScore scores;
    private JTable queryResult;
    private String stuID;
    //���캯��
    public StudentMainFrame(User user){
        stuID=user.getUsername();
        this.setTitle("ѧ���ɼ�����ϵͳ-ѧ��");
        this.setIconImage(new ImageIcon("Img/Icon1.png").getImage());
        this.setBounds(540,240,800,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setButtonPanel();
        setQueryArea();
        setButtons(user);
        setEdge();
        this.setVisible(true);
    }
    //����Panel
    private void setButtonPanel(){
        buttonPanel = new JPanel(new FlowLayout());
        this.add(buttonPanel,BorderLayout.NORTH);
    }
    //���ð�ť
    private void setButtons(User user){
        JButton freshInfo = new JButton("ˢ��");
        freshInfo.setPreferredSize(new Dimension(120,40));
        freshInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refresh();
            }
        });
        buttonPanel.add(freshInfo);
        buttonPanel.add(new JLabel("��ӭ��!"));
        buttonPanel.add(new JLabel(user.getUsername()));
        buttonPanel.add(new JLabel(String.format("          ����GPA�ǣ�%.2f",scores.countGPA())));
    }
    //���ò�ѯ���ݱ�
    private void setQueryArea(){
        queryArea = new JScrollPane();
        scores = new QueryStudentScore(stuID);
        queryResult = scores.getQueryResult();
        queryArea.getViewport().add(queryResult);
        queryArea.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(queryArea,BorderLayout.CENTER);
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
    //ˢ�º���
    public void refresh(){
        scores.Refresh(stuID,null,0);
    }
}
