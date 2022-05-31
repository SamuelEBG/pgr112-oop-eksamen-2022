package expg2022;

public class UserScore {

    private User user;
    private int score;
    private String topic;

    public UserScore(int score, String topic, User user) {
        this.user = user;
        this.score = score;
        this.topic = topic;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {this.topic = topic;}

    public User getUser() {return user;}

    public void setUser(User user) {
        this.user = user;
    }
}
