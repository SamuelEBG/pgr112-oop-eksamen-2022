package expg2022.topics;

public class BinaryQuiz extends AbstractQuiz{

    public BinaryQuiz(String question, String correctAnswer){
        super(question, correctAnswer);
    }

    @Override
    public boolean isCorrectAnswer(String answer) {
        return answer.equals(getCorrectAnswer());
    }
}
