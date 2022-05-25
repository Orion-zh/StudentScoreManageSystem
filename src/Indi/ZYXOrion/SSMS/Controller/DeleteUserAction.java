package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Frame.AdminMainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//ɾ���û�������
public class DeleteUserAction implements ActionListener{
    //�û���Ϣ
    String userLoginName;
    //������
    AdminMainFrame fatherFrame;
    //��ѯ������ڻ�ȡѡ�е�����
    JTable queryResult;
    int state;
    //���캯��
    public DeleteUserAction(AdminMainFrame fr, JTable table){
        fatherFrame = fr;
        queryResult = table;
    }
    //ִ�в���
    @Override
    public void actionPerformed(ActionEvent e) {
        int row = queryResult.getSelectedRow();
        userLoginName = queryResult.getValueAt(row,1).toString();
        JOptionPane optionPane = new JOptionPane();
        state = optionPane.showConfirmDialog(fatherFrame,"��ȷ��Ҫɾ����","ɾ���û�",JOptionPane.OK_CANCEL_OPTION);
        if(state==JOptionPane.OK_OPTION){
            DBProcessor processor = new DBProcessor();
            if(processor.deleteUser(userLoginName)==true){
                fatherFrame.refresh();
            }
        }
    }
}
