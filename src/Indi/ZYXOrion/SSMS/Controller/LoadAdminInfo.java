package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

//�����û���Ϣ��
public class LoadAdminInfo {
    //���ݱ�
    Object[][] dataTable;
    //��ͷ
    public String[] TableTitleData = {"�û���","��ʵ����","����","Ȩ��"};
    //JTable������ݱ�
    JTable QueryResult;
    JTableHeader tableHeader;
    //���ݿ����
    DBProcessor processor;
    //���캯��
    public LoadAdminInfo(){
        //���ݿ������
        processor = new DBProcessor();
        //�����ݿ��ȡ���ݱ�
        dataTable = processor.getAdminQuery();
        //����JTable���ͱ�
        QueryResult = new JTable(dataTable,TableTitleData){
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        //����ѡ��ģʽ
        QueryResult.setRowSelectionAllowed(true);
        QueryResult.setColumnSelectionAllowed(false);
        ListSelectionModel selectModel = QueryResult.getSelectionModel();
        selectModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableHeader = QueryResult.getTableHeader();
    }
    //��ȡ��ѯ����
    public JTable getQueryResult(){
        return QueryResult;
    }
    //ˢ�����ݱ�
    public void freshAdminInfo() {
        dataTable = processor.getAdminQuery();
        TableModel tableModel = new DefaultTableModel(dataTable, TableTitleData);
        QueryResult.setModel(tableModel);
    }
}
