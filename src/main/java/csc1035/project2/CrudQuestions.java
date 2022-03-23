package csc1035.project2;

import com.sun.security.jgss.GSSUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
    public static void createQuestion(Session s){
        Scanner sc = new Scanner(System.in);
        //takes an input from the user and initialises a new Question object based of that data
        System.out.println("Please enter the integer amount of points the question will be worth");
        while (!sc.hasNextInt()){ //ensures score is an int
            System.out.println("Please enter an integer for the amount of points the question will be worth");
            sc.nextLine();
        }
        int score = sc.nextInt();
        boolean type = determineType();
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
                    correct = determineCorrect();
                    answers.add(new QAnswer(correct, value, q1));
                case 2: //allows the user to exit the loop once they have added all the possible answers
                    newAnswer = false;
            }

        }
        q1.setQAnswers(answers);
        s.beginTransaction(); //saves the created Question object and respective QAnswer objects to the database
        s.save(q1);
        for (QAnswer q : answers){
            s.save(q);
        }
        s.getTransaction().commit();
    }

    /**
     * Implemented a method that allows the user to read all questions using this class
     * @param s - the hibernate session that is open for the duration of the program
     */
    public static void readQuestions(Session s){
        Query q = QueryDatabase.returnAllQuestions(s);
        for (Object i: q.getResultList()) { //iterates through the results of the query and prints to the console
            Question q2 = (Question) i;
            System.out.println(q2);
        }
    }

    /**
     * This method allows the user to find and update an existing question and it's answers
     * @param s - the hibernate session that is open for the duration of the program
     */
    public static void updateQuestion(Session s) {
        Scanner sc = new Scanner(System.in);
        Question q2 = null;
        Query q;
        s.beginTransaction();
        do { //ensures that user has selected a question
            System.out.println("Please enter the question ID of the question you would like to update?");
            while (!sc.hasNextInt()) { //ensures the questionID is an integer
                System.out.println("Please enter and integer");
                sc.nextLine();
            }
            int questionID = sc.nextInt();
            sc.nextLine();
            //creates a query based of the user input
            q = s.createQuery("from questions q where q.questionID = " + questionID);
        } while (q.getResultList().size() == 0);
        for (Object i : q.getResultList()) {
            q2 = (Question) i;
        }
        boolean loop = true;
        while (loop) { //loops until the user is done updating the question
            //provides a list of options for the user
            System.out.println("Please enter an integer 1-6");
            System.out.println("1. Change the amount of points the question is worth");
            System.out.println("2. Change the title of the question");
            System.out.println("3. Change the question type");
            System.out.println("4. Change the question topic");
            System.out.println("5. Change the question answers");
            System.out.println("6. Finish and apply changes");
            System.out.println("Current question is " + q2);
            while (!sc.hasNextInt()) {
                System.out.println("Please enter an integer 1-6");
                sc.nextLine();
            }
            int choice = sc.nextInt();
            sc.nextLine();
            //runs code corresponding to the users choice based of their input
            switch (choice) {
                case 1:
                    System.out.println("Please enter the new score");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter an integer");
                        sc.nextLine();
                    }
                    int newScore = sc.nextInt();
                    q2.setScore(newScore);
                    break;
                case 2:
                    System.out.println("Please enter the title of the new question");
                    String title = sc.nextLine();
                    q2.setTitle(title);
                    break;
                case 3:
                    boolean type = determineType();
                    q2.setType(type);
                    break;
                case 4:
                    System.out.println("Please enter the new question topic");
                    String topic = sc.nextLine();
                    q2.setTopic(topic);
                    break;
                case 5:
                    boolean loop2;
                    for (QAnswer qA : q2.getQAnswers()) {
                        System.out.println(qA);
                        loop2 = true;
                        while (loop2) { //loops until the user decides they are finished altering the answer
                            //provides a list of options for the user
                            System.out.println("Please enter an integer 1-4");
                            System.out.println("1. Change the boolean correct of the answer");
                            System.out.println("2. Change the answer stored in QAnswer");
                            System.out.println("3. Finish and delete answer");
                            System.out.println("4. Finish and apply changes to this QAnswer");
                            while (!sc.hasNextInt()) {
                                System.out.println("Please enter an integer 1-4");
                                sc.nextLine();
                            }
                            //runs code corresponding to the users choice based of their input
                            int choice2 = sc.nextInt();
                            switch (choice2) {
                                case 1:
                                    qA.setCorrect(determineCorrect());
                                case 2:
                                    System.out.println("Please enter the new answer stored here");
                                    String value = sc.nextLine();
                                case 3:
                                    s.delete(qA);
                                    loop2 = false;
                                case 4:
                                    s.save(qA);
                                    loop2 = false;
                            }
                        }
                    }
                    break;
                case 6://Save changes and causes the method to close
                    s.save(q2);
                    loop = false;
                    break;
            }
        }
        //Commit changes to the database
        s.getTransaction().commit();
    }

    /**
     * allows the user to delete a question from the database
     */
    public static void deleteQuestion(Session s){
        Scanner sc = new Scanner (System.in);
        Query q;
        Question q2 = null;
        s.beginTransaction();
        do { //ensures that user has selected a question
            System.out.println("Please enter the question ID of the question you would like to delete?");
            while (!sc.hasNextInt()) { //ensures the questionID is an integer
                System.out.println("Please enter and integer");
                sc.nextLine();
            }
            int questionID = sc.nextInt();
            sc.nextLine();
            //creates a query based of the user input
            q = s.createQuery("from questions q where q.questionID = " + questionID);
        } while(q.getResultList().size() == 0);
        for (Object i : q.getResultList()){
            q2 = (Question) i;
        }
        //deletes the question and other associated entities from the database
        for (QAnswer qA : q2.getQAnswers()){
            s.delete(qA);
        }
        for (RAnswer rA : q2.getRAnswers()){
            s.delete(rA);
        }
        s.delete(q2);
        s.getTransaction().commit();
    }
    /**
     * This method prevents code duplication across the create and update methods by checking what the user would like
     * to set the question type as
     * @return - the boolean that corresponds to the type selected by the user
     */
    public static boolean determineType(){
        Scanner sc = new Scanner(System.in);
        while(true){ //ensures that it is one of the two possible question types
            System.out.println("Please enter mcq or saq for the type of question");
            String temp = sc.nextLine();
            if (Objects.equals(temp, "mcq")){
                return false;
            }
            if (Objects.equals(temp, "saq")){
                return true;
            }
        }
    }

    /**
     * This method prevents code duplication by checking whether the user would like to set the answer to be true or
     * false
     * @return - whether the answer is correct or not
     */
    public static boolean determineCorrect(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("Please enter the a f if this answer is incorrect and t if its correct");
            String temp = sc.nextLine();
            if (Objects.equals(temp, "f")){
                return false;
            }
            if (Objects.equals(temp, "t")){
                return true;
            }
        }
    }
}
