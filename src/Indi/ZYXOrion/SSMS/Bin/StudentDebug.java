package Indi.ZYXOrion.SSMS.Bin;

import Indi.ZYXOrion.SSMS.Entity.User;
import Indi.ZYXOrion.SSMS.Frame.StudentMainFrame;

public class StudentDebug {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("student");
        StudentMainFrame studentMainFrame = new StudentMainFrame(user);
    }
}
