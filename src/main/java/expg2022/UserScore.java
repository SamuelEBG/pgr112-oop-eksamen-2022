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

    @Override
    public String toString(){
        return String.format(
                "%s %s %s",
                this.user.getUserName(),
                this.score,
                this.topic
        );
    }

    public int getScore() {
        return this.score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public String getTopic() {
        return this.topic;
    }
    public void setTopic(String topic) {this.topic = topic;}
    public User getUser() {return this.user;}
    public void setUser(User user) {
        this.user = user;
    }
}
