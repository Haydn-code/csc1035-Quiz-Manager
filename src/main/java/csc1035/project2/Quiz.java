package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "quizes")
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int quizID;

    @Column
    private String title;

    @OneToMany(mappedBy = "QuizID")
    private List<QuizQuestions> qQuestions;

    @OneToMany(mappedBy = "quizID")
    private List<Response> responses;

    public Quiz(String title, int quizID, List<QuizQuestions> qQuestions, List<Response> responses){
        this.quizID = quizID;
        this.title = title;
        this.qQuestions = qQuestions;
        this.responses = responses;
    }

    public List<Response> getResponses() {
        return responses;
    }

    public void setResponses(List<Response> responses) {
        this.responses = responses;
    }

    public List<QuizQuestions> getqQuestions() {
        return qQuestions;
    }

    public void setqQuestions(List<QuizQuestions> qQuestions) {
        this.qQuestions = qQuestions;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
