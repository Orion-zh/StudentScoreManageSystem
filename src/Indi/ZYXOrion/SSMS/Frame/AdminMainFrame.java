package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.*;
import Indi.ZYXOrion.SSMS.Entity.User;
import javafx.scene.control.ScrollPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainFrame extends JFrame {
    private JPanel buttonPanel;
    private JPanel panelLeft;
    private JPanel panelRight;
    private JPanel panelButton;
    private JScrollPane queryArea;
    private LoadAdminInfo adminInfo;
    public AdminMainFrame(User user){
        this.setTitle("ѧ���ɼ�����ϵͳ-����Ա");
        this.setIconImage(new ImageIcon("Img/Icon1.png").getImage());
        this.setBounds(540,240,800,600);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setButtonPanel();
        setQueryArea();
        setButtons(user);
        setEdge();
        this.setVisible(true);
    }
    private void setButtonPanel(){
        buttonPanel = new JPanel(new FlowLayout());
        this.add(buttonPanel,BorderLayout.NORTH);
    }
    private void setButtons(User user){
        JButton addUser = new JButton("�����û���Ϣ");
        JButton editUser = new JButton("�޸��û���Ϣ");
        JButton cancelUser = new JButton("ɾ���û���Ϣ");
        JButton exportUser = new JButton("�����û���Ϣ");
        addUser.setPreferredSize(new Dimension(120,40));
        editUser.setPreferredSize(new Dimension(120,40));
        cancelUser.setPreferredSize(new Dimension(120,40));
        exportUser.setPreferredSize(new Dimension(120,40));

        ChangeUserInfoAction changeUserInfoAction = new ChangeUserInfoAction();
        ExportUserAction exportUserAction = new ExportUserAction(user);
        DeleteUserAction deleteUserAction = new DeleteUserAction();

        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserFrame addUserFrame = new AddUserFrame();
            }
        });

        editUser.addActionListener(changeUserInfoAction);

        deleteUserAction.setFatherFrame(this);
        deleteUserAction.setQueryResult(adminInfo.getQueryResult());
        cancelUser.addActionListener(deleteUserAction);

        exportUser.addActionListener(exportUserAction);

        buttonPanel.add(addUser);
        buttonPanel.add(editUser);
        buttonPanel.add(cancelUser);
        buttonPanel.add(exportUser);
        buttonPanel.add(new JLabel("��ӭ��!"));
        buttonPanel.add(new JLabel(user.getUsername()));
    }
    private void setQueryArea(){
        queryArea = new JScrollPane();
        adminInfo = new LoadAdminInfo();
        queryArea.getViewport().add(adminInfo.getQueryResult());
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
}
