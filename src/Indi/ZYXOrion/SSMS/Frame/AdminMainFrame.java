package Indi.ZYXOrion.SSMS.Frame;

import Indi.ZYXOrion.SSMS.Controller.AddUserAction;
import Indi.ZYXOrion.SSMS.Controller.ChangeUserInfoAction;
import Indi.ZYXOrion.SSMS.Controller.DeleteUserAction;
import Indi.ZYXOrion.SSMS.Controller.ExportUserAction;
import Indi.ZYXOrion.SSMS.Entity.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminMainFrame extends JFrame {
    private JButton addUser;
    private JButton editUser;
    private JButton cancelUser;
    private JButton exportUser;
    private JPanel buttonPanel;
    public AdminMainFrame(User user){
        this.setBackground(Color.white);
        this.setTitle("ѧ���ɼ�����ϵͳ-����Ա");
        this.setIconImage(new ImageIcon("Img/Icon1.png").getImage());
        this.setBounds(540,240,800,600);
        setButtonPanel();
        setButtons(user);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    private void setButtonPanel(){
        buttonPanel = new JPanel(new FlowLayout());
        this.add(buttonPanel,BorderLayout.NORTH);
    }
    private void setButtons(User user){
        addUser = new JButton("�����û���Ϣ");
        editUser = new JButton("�޸��û���Ϣ");
        cancelUser = new JButton("ɾ���û���Ϣ");
        exportUser = new JButton("�����û���Ϣ");
        addUser.setPreferredSize(new Dimension(120,40));
        editUser.setPreferredSize(new Dimension(120,40));
        cancelUser.setPreferredSize(new Dimension(120,40));
        exportUser.setPreferredSize(new Dimension(120,40));

        ChangeUserInfoAction changeUserInfoAction = new ChangeUserInfoAction(user);
        ExportUserAction exportUserAction = new ExportUserAction(user);
        DeleteUserAction deleteUserAction = new DeleteUserAction(user);
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUserFrame addUserFrame = new AddUserFrame();
            }
        });
        editUser.addActionListener(changeUserInfoAction);
        cancelUser.addActionListener(deleteUserAction);
        exportUser.addActionListener(exportUserAction);
        buttonPanel.add(addUser);
        buttonPanel.add(editUser);
        buttonPanel.add(cancelUser);
        buttonPanel.add(exportUser);
        buttonPanel.add(new JLabel("��ӭ��!"));
        buttonPanel.add(new JLabel(user.getUsername()));
    }
}
