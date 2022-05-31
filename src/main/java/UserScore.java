package main.java;

public class UserScore {

    private int score;
    private String topic;

    public UserScore(int score, String topic) {
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

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
