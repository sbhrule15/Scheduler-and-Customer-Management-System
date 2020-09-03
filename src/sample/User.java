package sample;

public class User {

    private Integer userID;
    private String userName;
    private String password;

    public User(Integer userID, String userName) {
        this.userID = userID;
        this.userName = userName;
        this.password = null;
    }

    public Integer getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }
}
