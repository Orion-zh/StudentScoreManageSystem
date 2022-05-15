package Indi.ZYXOrion.SSMS.Controller;

import Indi.ZYXOrion.SSMS.Entity.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangeUserInfoAction implements ActionListener {
    User user;
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    public ChangeUserInfoAction(User user){
        this.user=user;
    }
}
