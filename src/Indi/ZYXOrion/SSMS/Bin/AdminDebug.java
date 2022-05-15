package Indi.ZYXOrion.SSMS.Bin;

import Indi.ZYXOrion.SSMS.Entity.User;
import Indi.ZYXOrion.SSMS.Frame.AdminMainFrame;

public class AdminDebug {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("test");
        AdminMainFrame adminMainFrame = new AdminMainFrame(user);
    }
}
