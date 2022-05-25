package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Frame.TeacherMainFrame;
import javax.swing.*;

//��ӳɼ�������
public class AddScoreAction{
    //ˢ��Ŀ��
    private TeacherMainFrame refreshTarget;
    //������
    JDialog fatherFrame;
    //�ɼ���Ϣ
    private double scores;
    private String stuID;
    private String courseID;
    private String year;
    //���ݿ⴦��
    private DBProcessor dbProcessor = new DBProcessor();

    public void setRefreshTarget(TeacherMainFrame target){
        refreshTarget = target;
    }

    public void setFatherFrame(JDialog frame){
        fatherFrame = frame;
    }
    //���뺯��
    public void setInput(String stuID, String courseID, String year, String score){
        this.stuID = stuID;
        this.courseID = courseID;
        this.year = year;
        try{
            this.scores=Double.parseDouble(score);
        }catch (NumberFormatException e){
            JOptionPane.showMessageDialog(fatherFrame, "������ɼ���","",JOptionPane.WARNING_MESSAGE);
        }
    }
    //ִ����Ӳ���
    public void action(){
        //�жϿ�ֵ
        if(stuID.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "ѧ�Ų���Ϊ�գ�","",JOptionPane.WARNING_MESSAGE);
            return ;
        }
        if(courseID.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "�γ̲���Ϊ�գ�","",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(year.equals("")){
            JOptionPane.showMessageDialog(fatherFrame, "����ѧ�ڲ���Ϊ�գ�","",JOptionPane.WARNING_MESSAGE);
            return;
        }
        //�������ݿ����
        dbProcessor.AddScore(stuID,courseID,year,scores);
        //ˢ�½���
        refreshTarget.refresh();
        //ִ����ɺ�رմ���
        fatherFrame.dispose();
    }
    //��ȡ�γ��б�
    public Object[][] getCourseList(){
        return dbProcessor.getCourseList();
    }
    //��ȡ�γ̱�ţ����ڽ��γ�����ת���ɶ�Ӧ�Ŀγ̱��
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
