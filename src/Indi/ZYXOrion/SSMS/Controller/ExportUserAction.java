package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Entity.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExportUserAction implements ActionListener {
    User user;
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public ExportUserAction(User user){
        this.user=user;
    }
}
