package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;

import javax.swing.*;
import javax.swing.table.JTableHeader;

public class LoadAdminInfo {
    Object[][] dataTable;
    String[] TableTitleData = {"�û���","��ʵ����","����","Ȩ��"};
    JTable QueryResult;
    JTableHeader tableHeader;
    public LoadAdminInfo(){
        DBProcessor processor = new DBProcessor();
        dataTable = processor.getAdminQuery();
        QueryResult = new JTable(dataTable,TableTitleData){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        QueryResult.setRowSelectionAllowed(true);
        QueryResult.setColumnSelectionAllowed(false);
        ListSelectionModel selectModel = QueryResult.getSelectionModel();
        selectModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHeader = QueryResult.getTableHeader();
    }
    public JTable getQueryResult(){
        return QueryResult;
    }
}
