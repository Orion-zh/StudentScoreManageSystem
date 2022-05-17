package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Frame.AdminMainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteUserAction implements ActionListener{
    String userLoginName;
    AdminMainFrame fatherFrame;
    JTable queryResult;
    int state;
    public DeleteUserAction(AdminMainFrame fr, JTable table){
        fatherFrame = fr;
        queryResult = table;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = queryResult.getSelectedRow();
        userLoginName = queryResult.getValueAt(row,1).toString();
        JOptionPane optionPane = new JOptionPane();
        state = optionPane.showConfirmDialog(fatherFrame,"你确定要删除吗？","删除用户",JOptionPane.OK_CANCEL_OPTION);
        if(state==JOptionPane.OK_OPTION){
            DBProcessor processor = new DBProcessor();
            if(processor.deleteUser(userLoginName)==true){
                fatherFrame.refresh();
            }
        }
    }
}
