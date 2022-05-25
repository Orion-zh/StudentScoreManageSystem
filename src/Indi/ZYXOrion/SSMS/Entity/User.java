package Indi.ZYXOrion.SSMS.Entity;

//用户实体类
public class User {
    private String username;
    private String password;
    private int level;
    private String name;
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
    public void setLevel(int level){
        this.level=level;
    }
    public int getLevel(){
        return level;
    }
    public void setName(String name){this.name = name;}
    public String getName(){return this.name;}
}
