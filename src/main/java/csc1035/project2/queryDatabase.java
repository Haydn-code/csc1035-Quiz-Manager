package csc1035.project2;

import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Created a class which will be used to query the database
 */
public class queryDatabase {
    /**
     * Defined a constructor
     */
    public queryDatabase(){
    }

    /**
     * A method that returns all questions entities from the database
     * @return - all the questions entities from the database
     */
    public static Query returnAllQuestions(){
        Session s = HibernateUtil.getSessionFactory().openSession();//opens a session and searches for all entities of
        // the questions class
        s.beginTransaction();
        Query returnAllQuestions = s.createQuery("from questions");
        s.getTransaction().commit();
        return returnAllQuestions;
    }

    /**
     * A method that allows for the searching of questions entities from the database by questionID, type or topic
     * @return - the results of the search inputs
     */
    public static Query searchQuestions(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number between 1-3");
        System.out.println("1. Search by question ID");
        System.out.println("2. Search by type");
        System.out.println("3. Search by topic");
        while (true) { //if the user has input an integer outside 1 to 3 will loop back to start
            while (!sc.hasNextInt()) { //checks the input is an integer
                System.out.println("Please enter an int 1-3");
                sc.nextLine();
            }
            int selection = sc.nextInt();
            sc.nextLine();
            switch (selection) { //selects the corresponding option to the user input
                case 1:
                    s.beginTransaction();
                    System.out.println("Please enter the question ID you would like to search for?");
                    while (!sc.hasNextInt()) { //ensures the questionID is an integer
                        System.out.println("Please enter and integer");
                        sc.nextLine();
                    }
                    int questionID = sc.nextInt();
                    sc.nextLine();
                    //creates a query based of the user input
                    Query searchQuestionID = s.createQuery("from questions q where q.questionID = " + questionID);
                    s.getTransaction().commit();
                    return searchQuestionID;
                case 2:
                    s.beginTransaction();
                    boolean valid = false;
                    String type = null;
                    while (!valid) { //ensures that the user input is one of the two enum types in the database
                        System.out.println("Please enter the type of question you would like to search for (mcq or saq");
                        type = sc.nextLine();
                        if (type == "mcq" || type == "saq") {
                            valid = true;
                        }
                    }
                    Query searchType = s.createQuery("from questions q where q.type = " + type); //creates query
                    //based of user inputs
                    s.getTransaction().commit();
                    return searchType;
                case 3:
                    s.beginTransaction();
                    System.out.println("Please enter the topic of question you wish to search for");
                    String topic = sc.nextLine(); //uses the user input to create a query for the specified topic
                    Query searchTopic = s.createQuery("from questions q where q.topic = " + topic);
                    s.getTransaction().commit();
                    return searchTopic;
            }
        }
    }

    /**
     * returns all questions previously answered incorrectly by the user
     * @return - list of incorrectly answered questions
     */
    public static Query viewIncorrectQuestions(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        s.beginTransaction(); //creates a query that returns a list of question with incorrect responses
        Query incorrectQuestions = s.createQuery("from questions q where " +
                "q.questionID = responseAnswers.questionID.questionID and responseAnswers.correct = false");
        s.getTransaction().commit();
        return incorrectQuestions;
    }

}
