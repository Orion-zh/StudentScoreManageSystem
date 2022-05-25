package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Frame.TeacherMainFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//编辑成绩操作
public class EditScoreAction implements ActionListener {
    //成绩信息
    String stuID;
    String courseID;
    JTextField score;
    //父界面
    JDialog fatherFrame;
    //设置刷新目标
    TeacherMainFrame refreshTarget;

    //执行编辑成绩
    @Override
    public void actionPerformed(ActionEvent e) {
        DBProcessor dbProcessor = new DBProcessor();
        if(score.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "成绩不能为空！","",JOptionPane.WARNING_MESSAGE);
            return;
        }
        dbProcessor.EditScore(stuID,courseID,score.getText());
        refreshTarget.refresh();
        fatherFrame.dispose();
    }
    //上层设置输入
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
