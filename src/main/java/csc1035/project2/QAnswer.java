package csc1035.project2;

import javax.persistence.*;

/**
 * QAnswer class for entity "questionAnswers" in the databse.
 */

@Entity(name = "questionAnswers")
public class QAnswer {

    /**
     * Primary key, answerID.
     * It is in number format and cannot be changed or empty.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(updatable = false, nullable = false)
    private int answerID;

    /**
     * Foreign key, questionID refers to the primary key in Question class (entity "questions").
     * It is many-to-one because there can be more than one different answers submitted for one question.
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Question questionID;

    /**
     * value column. It holds the submitted answer as a text value (varchar(60) in the database).
     * It should be no longer than 60 characters.
     */
    @Column
    private String value;

    /**
     * correct column. It is an enum with values 'true' and 'false' in the database.
     * For this reason a boolean is used.
     */
    @Column
    private boolean correct;

    /**
     * Defined a constructor for creating a new question answer from scratch
     * @param correct - if the answer is correct
     * @param value - the answer
     * @param questionID - the question that the answer is linked too
     */
    public QAnswer (boolean correct, String value, Question questionID){
        this.correct = correct;
        this.value = value;
        this.questionID = questionID;
    }

    public QAnswer (boolean correct, String value, int answerID, Question questionID)
    {
        this.answerID = answerID;
        this.questionID = questionID;
        this.value = value;
        this.correct = correct;
    }

    /**
     * No args constructor.
     */
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
