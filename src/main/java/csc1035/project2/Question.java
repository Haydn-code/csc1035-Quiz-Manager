package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int questionID;

    @Column
    private int score;

    @Column
    private String title;

    @Column
    private boolean type; // 0 - mcq  1 - saq

    @Column
    private String topic;

    @OneToMany(mappedBy = "questionID")
    private List<QAnswer> qAnswers;

    @OneToMany(mappedBy = "QuestionID")
    private List<QuizQuestions> qQuestions;

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
        String toString =  "Question{ID: " + this.questionID + ",Type: " + this.type + ",Score: " + this.score + ",Topic: "
                + this.topic + ",Title: " + this.title + ",Answers: ";
        for (QAnswer a : this.qAnswers){
            toString = toString + a;
        }
        return toString;
    }
}

// done