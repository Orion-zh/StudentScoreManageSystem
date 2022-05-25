package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Frame.TeacherMainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ɾ���ɼ�������
public class DeleteScoreAction implements ActionListener {
    //�ɼ�����
    String stuID;
    String courseID;
    TeacherMainFrame fatherFrame;
    //��ѯ������ڻ�ȡѡ�е�����
    JTable queryResult;
    int state;
    //���캯��
    public DeleteScoreAction(TeacherMainFrame fr, JTable table){
        fatherFrame = fr;
        queryResult = table;
    }
    //ִ��ɾ������
    @Override
    public void actionPerformed(ActionEvent e) {
        //��ȡѡ�е�����
        int row = queryResult.getSelectedRow();
        stuID = queryResult.getValueAt(row,4).toString();
        courseID = queryResult.getValueAt(row,0).toString();
        JOptionPane optionPane = new JOptionPane();
        state = optionPane.showConfirmDialog(fatherFrame,"��ȷ��Ҫɾ����","ɾ���ɼ�",JOptionPane.OK_CANCEL_OPTION);
        if(state==JOptionPane.OK_OPTION){
            DBProcessor processor = new DBProcessor();
            if(processor.deleteScore(stuID,courseID)==true){
                fatherFrame.refresh();
            }
        }
    }
}
