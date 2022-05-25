package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

//查询成绩类
public class QueryStudentScore {
    //数据表
    Object[][] dataTable;
    //所有课程
    Object[][] courseList;
    //表相关对象
    public String[] TableTitleData = {"课程号","课程名","开设学期","学分","学号","成绩"};
    JTable QueryResult;
    JTableHeader tableHeader;
    //数据库操作
    DBProcessor processor;
    String stuNo;

    //构造函数，设置组件
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
    //获取查询结果
    public JTable getQueryResult(){
        return QueryResult;
    }
    //刷新数据表
    public void Refresh(String stuID, String courseID) {
        dataTable = processor.getScoreQuery(stuID,getCourseID(courseID));
        TableModel tableModel = new DefaultTableModel(dataTable, TableTitleData);
        QueryResult.setModel(tableModel);
    }
    //计算GPA
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
    //获取课程列表
    public Object[][] getCourseList(){
        return courseList;
    }
    //将课程名称转换成课程编号
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
