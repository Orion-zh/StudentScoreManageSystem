package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class QueryStudentScore {
    Object[][] dataTable;
    public String[] TableTitleData = {"课程号","课程名","开设学期","学分","学号","成绩"};
    JTable QueryResult;
    JTableHeader tableHeader;
    DBProcessor processor;
    String stuNo;
    public QueryStudentScore(String stuID){
        this.stuNo = stuID;
        processor = new DBProcessor();
        dataTable = processor.getStudentQuery(stuNo);
        QueryResult = new JTable(dataTable,TableTitleData){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        QueryResult.setRowSelectionAllowed(false);
        QueryResult.setColumnSelectionAllowed(false);
        tableHeader = QueryResult.getTableHeader();
    }
    public JTable getQueryResult(){
        return QueryResult;
    }
    public void Refresh() {
        dataTable = processor.getStudentQuery(stuNo);
        TableModel tableModel = new DefaultTableModel(dataTable, TableTitleData);
        QueryResult.setModel(tableModel);
    }
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
}
