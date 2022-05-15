package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Database.DBProcessor;
import Indi.ZYXOrion.SSMS.Entity.User;
import Indi.ZYXOrion.SSMS.Frame.AddUserFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddUserAction implements ActionListener {
    User user;
    @Override
    public void actionPerformed(ActionEvent e) {
        DBProcessor dbProcessor = new DBProcessor();
        dbProcessor.AddUser(user);
    }
}
