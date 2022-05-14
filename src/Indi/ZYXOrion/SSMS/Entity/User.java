package Indi.ZYXOrion.SSMS.Entity;

public class User {
    private String username;
    private String password;
    private int isLogin = 0;
    public int getIsLogin() {
        return isLogin;
    }
    public void setIsLogin(int isLogin) {
        this.isLogin = isLogin;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
