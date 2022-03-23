package csc1035.project2;

import javax.persistence.*;
import java.security.PublicKey;

/**
 * Class for quizQuestions entity in the database.
 */

@Entity(name = "quizQuestions")
public class QuizQuestions {

    /**
     * Primary key is QQJD. It is in number format and cannot be changed or empty.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    private int QQJD;

    /**
     * Foreign key regarding to the primary key in quizes entity.
     * It is a many-to-one relation because there can be more than one question in a quiz.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Quiz QuizID;

    /**
     * Foreign key to the primary key in quiz questions.
     * It is a many-to-one relation because there can be the same question in different quizes.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Question QuestionID;

    /**
     * Defined a new constructor to be used in the creation of quizes
     * @param QuizID - the quiz
     * @param QuestionID - the question
     */
    public QuizQuestions(Quiz QuizID, Question QuestionID){
        this.QuestionID = QuestionID;
        this.QuizID = QuizID;
    }
    public QuizQuestions(int QQJD, Quiz QuizID, Question QuestionID){
        this.QQJD = QQJD;
        this.QuestionID = QuestionID;
        this.QuizID = QuizID;
    }

    /**
     * No args constructor.
     */
    public QuizQuestions() {

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

    @Override
    public String toString(){
        return this.QuestionID.toString() + "QQJD: " + this.QQJD;
    }
}
