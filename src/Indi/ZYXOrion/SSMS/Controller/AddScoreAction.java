package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Frame.TeacherMainFrame;

import javax.swing.*;

public class AddScoreAction{
    private TeacherMainFrame refreshTarget;
    JDialog fatherFrame;
    private double scores;
    private String stuID;
    private String courseID;
    private String year;
    private DBProcessor dbProcessor = new DBProcessor();
    public void setRefreshTarget(TeacherMainFrame target){
        refreshTarget = target;
    }
    public void setFatherFrame(JDialog frame){
        fatherFrame = frame;
    }
    public void setInput(String stuID, String courseID, String year, String score){
        this.stuID = stuID;
        this.courseID = courseID;
        this.year = year;
        try{
            this.scores=Double.parseDouble(score);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(fatherFrame, "请输入成绩！","",JOptionPane.WARNING_MESSAGE);
        }
    }
    public void action(){
        if(stuID.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "学号不能为空！","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if(courseID.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "课程不能为空！","",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(year.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "开课学期不能为空！","",JOptionPane.WARNING_MESSAGE);
            return;
        }
        dbProcessor.AddScore(stuID,courseID,year,scores);
        refreshTarget.refresh();
        fatherFrame.dispose();
    }
    public Object[][] getCourseList(){
        return dbProcessor.getCourseList();
    }
    public String getCourseID(String courseName){
        Object[][] dataList = dbProcessor.getCourseList();
        String result = new String();
        for(int i=0;i<dataList.length;i++){
            if(courseName.equals(dataList[i][1].toString())){
                result=dataList[i][0].toString();
            }
        }
        return result;
    }
}
