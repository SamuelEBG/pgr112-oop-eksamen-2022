package main.java;

public class User {

    public User(String userName) {
        this.userName = userName;
    }

    private String userName;
    private UserScore userScore;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserScore getUserScore() {
        return userScore;
    }

    public void setUserScore(UserScore userScore) {
        this.userScore = userScore;
    }
}
