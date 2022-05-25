package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Frame.TeacherMainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//删除成绩操作类
public class DeleteScoreAction implements ActionListener {
    //成绩数据
    String stuID;
    String courseID;
    TeacherMainFrame fatherFrame;
    //查询表格，用于获取选中的数据
    JTable queryResult;
    int state;
    //构造函数
    public DeleteScoreAction(TeacherMainFrame fr, JTable table){
        fatherFrame = fr;
        queryResult = table;
    }
    //执行删除操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //获取选中的数据
        int row = queryResult.getSelectedRow();
        stuID = queryResult.getValueAt(row,4).toString();
        courseID = queryResult.getValueAt(row,0).toString();
        JOptionPane optionPane = new JOptionPane();
        state = optionPane.showConfirmDialog(fatherFrame,"你确定要删除吗？","删除成绩",JOptionPane.OK_CANCEL_OPTION);
        if(state==JOptionPane.OK_OPTION){
            DBProcessor processor = new DBProcessor();
            if(processor.deleteScore(stuID,courseID)==true){
                fatherFrame.refresh();
            }
        }
    }
}
