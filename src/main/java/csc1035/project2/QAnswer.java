package csc1035.project2;

import javax.persistence.*;


@Entity(name = "questionAnswers")
public class QAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int answerID;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Question questionID;

    @Column
    private String value;

    @Column
    private boolean correct;


    public QAnswer (boolean correct, String value, int answerID, Question questionID)
    {
        this.answerID = answerID;
        this.questionID = questionID;
        this.value = value;
        this.correct = correct;
    }

    public QAnswer() {

    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public Question getQuestionID() {
        return questionID;
    }

    public void setQuestionID(Question questionID) {
        this.questionID = questionID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public String toString(){
        return "QAnswer{ID: " + this.answerID + ",Value: " + this.value + ",Correct: " + this.correct + "}";
    }
}
// done