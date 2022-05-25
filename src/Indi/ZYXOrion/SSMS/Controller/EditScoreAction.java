package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Frame.TeacherMainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//�༭�ɼ�����
public class EditScoreAction implements ActionListener {
    //�ɼ���Ϣ
    String stuID;
    String courseID;
    JTextField score;
    //������
    JDialog fatherFrame;
    //����ˢ��Ŀ��
    TeacherMainFrame refreshTarget;

    //ִ�б༭�ɼ�
    @Override
    public void actionPerformed(ActionEvent e) {
        DBProcessor dbProcessor = new DBProcessor();
        if(score.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "�ɼ�����Ϊ�գ�","",JOptionPane.WARNING_MESSAGE);
            return;
        }
        dbProcessor.EditScore(stuID,courseID,score.getText());
        refreshTarget.refresh();
        fatherFrame.dispose();
    }
    //�ϲ���������
    public void setInput(String stuID,String courseID,JTextField scoreText) {
        this.stuID = stuID;
        this.courseID = courseID;
        this.score=scoreText;
    }
    public void setFatherFrame(JDialog frame){
        fatherFrame = frame;
    }
    public void setRefreshTarget(TeacherMainFrame target){
        refreshTarget = target;
    }
}
