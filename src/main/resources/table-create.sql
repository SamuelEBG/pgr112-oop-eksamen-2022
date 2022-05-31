Use quizDb;

DROP TABLE IF EXISTS binaryQuiz;
DROP TABLE IF EXISTS multiChoiceQuiz;
DROP TABLE IF EXISTS userScore;

CREATE TABLE userScore(
    id INT NOT NULL AUTO_INCREMENT,
    user varChar(50),
    score smallint,
    topic char(30),
    PRIMARY KEY (id)
);

CREATE TABLE multiChoiceQuiz (
    id INT NOT NULL AUTO_INCREMENT,
    question varChar(400),
    answerA varChar(100),
    answerB varChar(100),
    answerC varChar(100),
    answerD varChar(100),
    correctAnswer varChar(100),
    PRIMARY KEY (id)
);

CREATE TABLE binaryQuiz (
    id INT NOT NULL AUTO_INCREMENT,
    question varChar(400),
    correctAnswer char(5),
    PRIMARY KEY (id)
);