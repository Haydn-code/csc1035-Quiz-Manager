package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * class for responseAnswers entity in the databse.
 */

@Entity(name = "responseAnswers")
public class RAnswer {

    /**
     * Primary key is called ARID, it is a basic ID. cannot be empty and cannot be changed.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int ARID;

    /**
     * responseID is a foreign key connected to the responses entity.
     * It is a many-to-one relation because there can be more than one response answers in a response.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Response responseID;

    /**
     * questionID is a foreign key connected to the questions entity.
     * It is a many-to-one relation beacuse there can be more than one response answers for a question.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Question questionID;

    /**
     * Answer column, asnwers will be stored as text(VARCHAR 50).
     * Should be no more than 50 characters
     */
    @Column
    private String answer;

    /**
     * Correct column (holds whether it was answered correctly).
     * In database it is an enum with values 'true' and 'false'. For this reason boolean is used here in the code.
     */
    @Column
    private boolean correct;

    /**
     * score column. Answer score is kept in a number format.
     */
    @Column
    private int score;


    public RAnswer(int ARID, Response responseID, Question questionID, String answer, boolean correct, int score){
        this.ARID = ARID;
        this.responseID = responseID;
        this.questionID = questionID;
        this.answer = answer;
        this.correct = correct;
        this.score = score;
    }

    /**
     * No args constructor.
     */
    public RAnswer() {

    }

    public int getARID() {
        return ARID;
    }

    public void setARID(int ARID) {
        this.ARID = ARID;
    }

    public Response getResponseID() {
        return responseID;
    }

    public void setResponseID(Response responseID) {
        this.responseID = responseID;
    }

    public Question getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Question questionID) {
        this.questionID = questionID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
