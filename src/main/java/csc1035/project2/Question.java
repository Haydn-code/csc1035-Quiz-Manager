package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Question class for entity 'questions' in the databse.
 */

@Entity(name = "questions")
public class Question {

    /**
     * Primary key, questionID.
     * It is a number format and cannot be changed or empty.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int questionID;

    /**
     * Score collumn. Keeps the ammount of points given for this question as a number format.
     */
    @Column
    private int score;

    /**
     * Title column. Actual question, kept as a string format in this class and varchar(60) in the database.
     * Should be no longer than 60 characters.
     */
    @Column
    private String title;

    /**
     * Type column. Represents enum in the database which can have either 'mcq' or 'saq'.
     * mcq - multiple choices question.
     * saq - short answer question.
     * True equals mcq and false equals saq.
     */
    @Column
    private boolean type;

    /**
     * topic column. holds topic as a string in the class and as varchar(50) in the database.
     * topic should be no longer than 50 characters.
     */
    @Column
    private String topic;

    /**
     * QAnswers class (entity "questionAnswers") is mapped to the questionID in Question class (entity "questions").
     */
    @OneToMany(mappedBy = "questionID")
    private List<QAnswer> qAnswers;

    /**
     * QuizQuestions class (entity "qQuestions") is mapped to the questionID in Question class (entity "questions").
     */
    @OneToMany(mappedBy = "QuestionID")
    private List<QuizQuestions> qQuestions;

    /**
     * RAnswer class (entity "rAnswers") is mapped to the questionID in Question class (entity "questions").
     */
    @OneToMany(mappedBy = "questionID")
    private List<RAnswer> rAnswers;

    public Question(int questionID, int score, String title, boolean type, String topic, List<QAnswer> qAnswers,
                    List<QuizQuestions> qQuestions, List<RAnswer> rAnswers){
        this.questionID = questionID;
        this.score = score;
        this.title = title;
        this.type = type;
        this.topic = topic;
        this.qAnswers = qAnswers;
        this.qQuestions = qQuestions;
        this.rAnswers = rAnswers;
    }

    /**
     * No args constructor.
     */
    public Question() {

    }

    public List<RAnswer> getrAnswers() {
        return rAnswers;
    }

    public void setrAnswers(List<RAnswer> rAnswers) {
        this.rAnswers = rAnswers;
    }

    public List<QuizQuestions> getqQuestions() {
        return qQuestions;
    }

    public void setqQuestions(List<QuizQuestions> qQuestions) {
        this.qQuestions = qQuestions;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<QAnswer> getqAnswers() {
        return qAnswers;
    }

    public void setqAnswers(List<QAnswer> qAnswers) {
        this.qAnswers = qAnswers;
    }

    @Override
    public String toString(){
        StringBuilder toString = new StringBuilder("Question{ID: " + this.questionID + ",Type: " + this.type +
                ",Score: " + this.score + ",Topic: " + this.topic + ",Title: " + this.title + ",Answers: ");
        for (QAnswer a : this.qAnswers){
            toString.append(a);
        }
        toString.append("}");
        return toString.toString();
    }
}

// done