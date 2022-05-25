package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

//��ѯ�ɼ���
public class QueryStudentScore {
    //���ݱ�
    Object[][] dataTable;
    //���пγ�
    Object[][] courseList;
    //����ض���
    public String[] TableTitleData = {"�γ̺�","�γ���","����ѧ��","ѧ��","ѧ��","�ɼ�"};
    JTable QueryResult;
    JTableHeader tableHeader;
    //���ݿ����
    DBProcessor processor;
    String stuNo;

    //���캯�����������
    public QueryStudentScore(String stuID){
        this.stuNo = stuID;
        processor = new DBProcessor();
        dataTable = processor.getScoreQuery(stuNo,null);
        courseList = processor.getCourseList();
        QueryResult = new JTable(dataTable,TableTitleData){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        QueryResult.setRowSelectionAllowed(true);
        QueryResult.setColumnSelectionAllowed(false);
        tableHeader = QueryResult.getTableHeader();
    }
    //��ȡ��ѯ���
    public JTable getQueryResult(){
        return QueryResult;
    }
    //ˢ�����ݱ�
    public void Refresh(String stuID, String courseID) {
        dataTable = processor.getScoreQuery(stuID,getCourseID(courseID));
        TableModel tableModel = new DefaultTableModel(dataTable, TableTitleData);
        QueryResult.setModel(tableModel);
    }
    //����GPA
    public double countGPA(){
        double GPA;
        double credit = 0.0;
        double sum = 0.0;
        for(int i=0;i<dataTable.length;i++){
            credit += Double.parseDouble(dataTable[i][3].toString());
            double changetograde = (Integer.parseInt(dataTable[i][5].toString())-50)/10.0;
            if(changetograde>4) changetograde=4.0;
            if(changetograde<1) changetograde=0.0;
            sum += Double.parseDouble(dataTable[i][3].toString())*changetograde;
        }
        if(credit==0) return 0;
        else GPA=sum/credit;
        return GPA;
    }
    //��ȡ�γ��б�
    public Object[][] getCourseList(){
        return courseList;
    }
    //���γ�����ת���ɿγ̱��
    public String getCourseID(String courseName){
        Object[][] dataList = processor.getCourseList();
        String result = new String();
        if(courseName==null){
            return "";
        }
        for(int i=0;i<dataList.length;i++){
            if(courseName.equals(dataList[i][1].toString())){
                result=dataList[i][0].toString();
            }
        }
        return result;
    }
}
