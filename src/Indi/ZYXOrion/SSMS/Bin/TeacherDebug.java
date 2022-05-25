package Indi.ZYXOrion.SSMS.Bin;

import Indi.ZYXOrion.SSMS.Entity.User;
import Indi.ZYXOrion.SSMS.Frame.TeacherMainFrame;

public class TeacherDebug {
    public static void main(String[] args) {
        User user = new User();
        user.setUsername("teacher");
        TeacherMainFrame teacherMainFrame = new TeacherMainFrame(user);
    }
}
