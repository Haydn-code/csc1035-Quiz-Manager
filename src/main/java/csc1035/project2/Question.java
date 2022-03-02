package csc1035.project2;

import java.util.ArrayList;
import java.util.List;

// My idea on Question class. I think it would be a good starting point

public class Question {
    private int id; // just ID, nothing to explain
    private boolean type; // since there are two types of questions i decided to use bool
    private String answer; // actual correct answer of the question
    private String question; // Question text
    // possible answers - 4 if multiple / 1 if short answer question
    private List<String> possibleAnswers = new ArrayList<String>();
    // keeps a date and time log of when mistake was made for this question
    private List<String> mistakes = new ArrayList<String>();

    public Question(int id, boolean type, String answer, String question, List<String> possibleAnswers,
                    List<String> mistakes)
    {
        this.id = id;
        this.type = type;
        this.answer = answer;
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.mistakes = mistakes;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(List<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public List<String> getMistakes() {
        return mistakes;
    }

    public void setMistakes(List<String> mistakes) {
        this.mistakes = mistakes;
    }
}

