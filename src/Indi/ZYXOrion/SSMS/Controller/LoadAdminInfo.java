package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

public class LoadAdminInfo {
    Object[][] dataTable;
    public String[] TableTitleData = {"用户名","真实姓名","密码","权限"};
    JTable QueryResult;
    JTableHeader tableHeader;
    DBProcessor processor;
    public LoadAdminInfo(){
        processor = new DBProcessor();
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
    public void freshAdminInfo() {
        dataTable = processor.getAdminQuery();
        TableModel tableModel = new DefaultTableModel(dataTable, TableTitleData);
        QueryResult.setModel(tableModel);
    }
}
