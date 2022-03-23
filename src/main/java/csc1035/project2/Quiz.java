package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for quizes entity in the database.
 */

@Entity(name = "quizes")
public class Quiz {

    /**
     * Primary key quizID is in number format. It cannot be empty or changed.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int quizID;

    /**
     * Title column. As name implies it is in a text format (String in code and varchar(40) in the database).
     * Should be no more than 40 characters.
     */
    @Column
    private String title;

    /**
     * QuizQuestions class (entity "quizQuestions") is mapped to the quizID in Quiz class (entity "quizes").
     */
    @OneToMany(mappedBy = "QuizID")
    private List<QuizQuestions> qQuestions;

    /**
     * Response class (entity "responses") is mapped to the quizID in Quiz class (entity "quizes").
     */
    @OneToMany(mappedBy = "quizID")
    private List<Response> responses;

    public Quiz(String title, int quizID, List<QuizQuestions> qQuestions, List<Response> responses){
        this.quizID = quizID;
        this.title = title;
        this.qQuestions = qQuestions;
        this.responses = responses;
    }

    /**
     * No args constructor
     */
    public Quiz() {

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
