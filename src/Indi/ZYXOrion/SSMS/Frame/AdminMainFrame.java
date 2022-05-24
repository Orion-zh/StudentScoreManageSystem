package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.*;
import Indi.ZYXOrion.SSMS.Entity.User;

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
    private JTable queryResult;
    private AdminMainFrame jf = this;
    private DeleteUserAction deleteUserAction;
    public AdminMainFrame(User user){
        this.setTitle("学生成绩管理系统-管理员");
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
        JButton addUser = new JButton("新增用户信息");
        JButton editUser = new JButton("修改用户信息");
        JButton cancelUser = new JButton("删除用户信息");
        JButton exportUser = new JButton("导出用户信息");
        addUser.setPreferredSize(new Dimension(120,40));
        editUser.setPreferredSize(new Dimension(120,40));
        cancelUser.setPreferredSize(new Dimension(120,40));
        exportUser.setPreferredSize(new Dimension(120,40));

        EditUserAction editUserAction = new EditUserAction();
        ExportUserAction exportUserAction = new ExportUserAction(user);
        deleteUserAction = new DeleteUserAction(this,adminInfo.getQueryResult());

        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserFrame addUserFrame = new AddUserFrame(jf);
            }
        });

        editUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditUserFrame editUserFrame = new EditUserFrame(jf,queryResult.getValueAt(queryResult.getSelectedRow(),0).toString());
            }
        });

        cancelUser.addActionListener(deleteUserAction);

        exportUser.addActionListener(exportUserAction);

        buttonPanel.add(addUser);
        buttonPanel.add(editUser);
        buttonPanel.add(cancelUser);
        buttonPanel.add(exportUser);
        buttonPanel.add(new JLabel("欢迎您!"));
        buttonPanel.add(new JLabel(user.getUsername()));
    }
    private void setQueryArea(){
        queryArea = new JScrollPane();
        adminInfo = new LoadAdminInfo();
        queryResult = adminInfo.getQueryResult();
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
        adminInfo.freshAdminInfo();
    }
}
