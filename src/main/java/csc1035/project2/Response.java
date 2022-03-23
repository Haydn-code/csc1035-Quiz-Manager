package csc1035.project2;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Response class made for responses entity
 */

@Entity(name = "responses")
public class Response {

    /**
     * Primary key - responseID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private int responseID;

    /**
     * quizID is a foreign key connected to the quizes entity.
     * It is a many-to-one relation, because one quiz can have more than one response
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Quiz quizID;// foreign key

    /**
     * rAnswers is mapped to responsesID, will be used in responseAnswers entity.
     */
    @OneToMany(mappedBy = "responseID")
    private List<RAnswer> rAnswers;

    /**
     * studentNo column, as name implies will store student number
     */
    @Column
    private int studentNo;

    /**
     * result column, as name implies will store result.
     */
    @Column
    private int result;

    public Response (int responseID, Quiz quizID, List<RAnswer> rAnswers, int studentNo, int result){
        this.responseID = responseID;
        this.quizID = quizID;
        this.rAnswers = rAnswers;
        this.studentNo = studentNo;
        this.result = result;
    }

    /**
     * no args constructor.
     */
    public Response() {

    }

    public int getResponseID() {
        return responseID;
    }

    public void setResponseID(int responseID) {
        this.responseID = responseID;
    }

    public Quiz getQuizID() {
        return quizID;
    }

    public void setQuizID(Quiz quizID) {
        this.quizID = quizID;
    }

    public List<RAnswer> getrAnswers() {
        return rAnswers;
    }

    public void setrAnswers(List<RAnswer> rAnswers) {
        this.rAnswers = rAnswers;
    }

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }
}
