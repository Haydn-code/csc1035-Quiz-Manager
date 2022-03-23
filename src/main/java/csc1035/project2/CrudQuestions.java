package csc1035.project2;

import org.hibernate.Session;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Defined a class that will be used to create, read, update and delete questions
 */
public class CrudQuestions {
    /**
     * Defined a default constructor
     */
    public CrudQuestions(){}

    /**
     * allows the user to create questions which are then saved to the database
     * @param s - the hibernate session that is open the full duration of the program
     */
    public void createQuestion(Session s){
        Scanner sc = new Scanner(System.in);
        //takes an input from the user and initialises a new Question object based of that data
        System.out.println("Please enter the integer amount of points the question will be worth");
        while (!sc.hasNextInt()){ //ensures score is an int
            System.out.println("Please enter an integer for the amount of points the question will be worth");
            sc.nextLine();
        }
        int score = sc.nextInt();
        boolean loop = true;
        boolean type = false;
        while(loop){ //ensures that it is one of the two possible question types
            System.out.println("Please enter mcq or saq for the type of question");
            String temp = sc.nextLine();
            if (Objects.equals(temp, "mcq")){
                loop = false;
            }
            if (Objects.equals(temp, "saq")){
                type = true;
                loop = false;
            }
        }
        System.out.println("Please enter the topic of the question");
        String topic = sc.nextLine();
        System.out.println("Please enter the question");
        String title = sc.nextLine();
        Question q1 = new Question(score, title, type, topic);
        List<QAnswer> answers = new ArrayList<QAnswer>();
        boolean newAnswer = true;
        boolean correct = false;
        while (newAnswer){ //Will repeat until the user has added all the possible answers they wish to add to the
            // question
            System.out.println("Please enter 1 if you wish to add a new question and 2 if you wish to exit");
            while(!sc.hasNextInt()){
                System.out.println("Please enter an integer 1 or 2");
            }
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    //creates a new QAnswer object based of inputs from the user and adds it to the list of answers
                    //to the question
                    System.out.println("Please enter the possible answer that you wish to add to the question");
                    String value = sc.nextLine();
                    loop = true;
                    while(loop){
                        System.out.println("Please enter the a f if this answer is incorrect and t if its correct");
                        String temp = sc.nextLine();
                        if (temp == "f"){
                            loop = false;
                        }
                        if (temp == "t"){
                            correct = true;
                            loop = false;
                        }
                    }
                    answers.add(new QAnswer(correct, value, q1));
                case 2: //allows the user to exit the loop once they have added all the possible answers
                    newAnswer = false;
            }

        }
        q1.setqAnswers(answers);
        s.beginTransaction(); //saves the created Question object and respective QAnswer objects to the database
        s.save(q1);
        for (QAnswer q : answers){
            s.save(q);
        }
        s.getTransaction().commit();
    }

}
