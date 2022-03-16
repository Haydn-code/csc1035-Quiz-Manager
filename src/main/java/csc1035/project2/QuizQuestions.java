package csc1035.project2;

import javax.persistence.*;
import java.security.PublicKey;

@Entity(name = "quizQuestions")
public class QuizQuestions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int QQJD;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Quiz QuizID;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Question QuestionID;

    public QuizQuestions(int QQJD, Quiz QuizID, Question QuestionID){
        this.QQJD = QQJD;
        this.QuestionID = QuestionID;
        this.QuizID = QuizID;
    }

    public int getQQJD() {
        return QQJD;
    }

    public void setQQJD(int QQJD) {
        this.QQJD = QQJD;
    }

    public Quiz getQuizID() {
        return QuizID;
    }

    public void setQuizID(Quiz quizID) {
        QuizID = quizID;
    }

    public Question getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(Question questionID) {
        QuestionID = questionID;
    }
}
