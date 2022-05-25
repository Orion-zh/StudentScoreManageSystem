package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

//加载用户信息类
public class LoadAdminInfo {
    //数据表
    Object[][] dataTable;
    //表头
    public String[] TableTitleData = {"用户名","真实姓名","密码","权限"};
    //JTable类的数据表
    JTable QueryResult;
    JTableHeader tableHeader;
    //数据库操作
    DBProcessor processor;
    //构造函数
    public LoadAdminInfo(){
        //数据库操作类
        processor = new DBProcessor();
        //从数据库获取数据表
        dataTable = processor.getAdminQuery();
        //生成JTable类型表
        QueryResult = new JTable(dataTable,TableTitleData){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        //设置选择模式
        QueryResult.setRowSelectionAllowed(true);
        QueryResult.setColumnSelectionAllowed(false);
        ListSelectionModel selectModel = QueryResult.getSelectionModel();
        selectModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHeader = QueryResult.getTableHeader();
    }
    //获取查询数据
    public JTable getQueryResult(){
        return QueryResult;
    }
    //刷新数据表
    public void freshAdminInfo() {
        dataTable = processor.getAdminQuery();
        TableModel tableModel = new DefaultTableModel(dataTable, TableTitleData);
        QueryResult.setModel(tableModel);
    }
}
