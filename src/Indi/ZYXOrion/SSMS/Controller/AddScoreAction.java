package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Frame.TeacherMainFrame;
import javax.swing.*;

//添加成绩操作类
public class AddScoreAction{
    //刷新目标
    private TeacherMainFrame refreshTarget;
    //父界面
    JDialog fatherFrame;
    //成绩信息
    private double scores;
    private String stuID;
    private String courseID;
    private String year;
    //数据库处理
    private DBProcessor dbProcessor = new DBProcessor();

    public void setRefreshTarget(TeacherMainFrame target){
        refreshTarget = target;
    }

    public void setFatherFrame(JDialog frame){
        fatherFrame = frame;
    }
    //输入函数
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
    //执行添加操作
    public void action(){
        //判断空值
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
        //调用数据库添加
        dbProcessor.AddScore(stuID,courseID,year,scores);
        //刷新界面
        refreshTarget.refresh();
        //执行完成后关闭窗口
        fatherFrame.dispose();
    }
    //获取课程列表
    public Object[][] getCourseList(){
        return dbProcessor.getCourseList();
    }
    //获取课程编号，用于将课程名称转换成对应的课程编号
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
