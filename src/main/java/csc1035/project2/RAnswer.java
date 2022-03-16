package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "responseAnswers")
public class RAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int ARID;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Response responseID;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Question questionID;

    @Column
    private String answer;

    @Column
    private boolean correct;

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
