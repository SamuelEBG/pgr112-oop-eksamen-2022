package expg2022.topics;

import java.util.ArrayList;

public class MultiChoiceQuiz extends AbstractQuiz{

    private ArrayList<String> answers;

    public MultiChoiceQuiz(String question, String correctAnswer, ArrayList<String> answers){
        super(question, correctAnswer);
        this.answers = answers;
    }

    @Override
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(getCorrectAnswer());
    }
    public void setCorrectAnswer(String answer){
        super.setCorrectAnswer(answer);
    }
    public ArrayList<String> getAnswers() {
        return answers;
    }
    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }
}
