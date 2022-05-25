package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Frame.TeacherMainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteScoreAction implements ActionListener {
    String stuID;
    String courseID;
    TeacherMainFrame fatherFrame;
    JTable queryResult;
    int state;
    public DeleteScoreAction(TeacherMainFrame fr, JTable table){
        fatherFrame = fr;
        queryResult = table;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = queryResult.getSelectedRow();
        stuID = queryResult.getValueAt(row,4).toString();
        courseID = queryResult.getValueAt(row,0).toString();
        JOptionPane optionPane = new JOptionPane();
        state = optionPane.showConfirmDialog(fatherFrame,"ÄãÈ·¶¨ÒªÉ¾³ýÂð£¿","É¾³ý³É¼¨",JOptionPane.OK_CANCEL_OPTION);
        if(state==JOptionPane.OK_OPTION){
            DBProcessor processor = new DBProcessor();
            if(processor.deleteScore(stuID,courseID)==true){
                fatherFrame.refresh();
            }
        }
    }
}
