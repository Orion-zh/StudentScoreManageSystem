package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUserAction implements ActionListener{
    String userLoginName;
    JFrame fatherFrame;
    JTable queryTable;
    int state;
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = queryTable.getSelectedRow();
        userLoginName = queryTable.getValueAt(row,1).toString();
        JOptionPane optionPane = new JOptionPane();
        state = optionPane.showConfirmDialog(fatherFrame,"你确定要删除吗？","删除用户",JOptionPane.OK_CANCEL_OPTION);
        if(state==JOptionPane.OK_OPTION){
            DBProcessor processor = new DBProcessor();
            processor.deleteUser(userLoginName);
        }
    }
    public void setFatherFrame(JFrame fr){
        fatherFrame = fr;
    }
    public void setQueryResult(JTable table){
        queryTable = table;
    }
}
