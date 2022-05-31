Use quizDb;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS binaryQuiz;
DROP TABLE IF EXISTS multiChoiceQuiz;
DROP TABLE IF EXISTS highscore;

CREATE TABLE highscore(
    id INT NOT NULL AUTO_INCREMENT,
    user varChar(50),
    score smallint,
    topic char(30)
);

CREATE TABLE multiChoiceQuiz (
    id INT NOT NULL AUTO_INCREMENT,
    question varChar(400),
    answerA varChar(100),
    answerB varChar(100),
    answerC varChar(100),
    answerD varChar(100),
    correctAnswer char(10)
);

CREATE TABLE binaryQuiz (
    id INT NOT NULL AUTO_INCREMENT,
    question varChar(400),
    correctAnswer char(5)
);