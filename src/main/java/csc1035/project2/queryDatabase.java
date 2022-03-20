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
    public static ArrayList<Question> returnAllQuestions(){
        Session
    }
    public static ArrayList<Question> searchQuestions(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number between 1-3");
        System.out.println("1. Search by question ID");
        System.out.println("2. Search by type");
        System.out.println("3. Search by topic");
        while (!sc.hasNextInt()) { //checks the input is an integer
            System.out.println("Please enter an int 1-13");
            sc.nextLine();
        }
        int selection = sc.nextInt();
        sc.nextLine();
        switch(selection){
            case 1:
                while (true) {
                    s.beginTransaction();
                    System.out.println("Please enter the question ID you would like to search for?");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter and integer");
                        sc.nextLine();
                    }
                    int questionID = sc.nextInt();
                    String query = "from Questions q where q.questionID = " + questionID;
                    Query searchQuestionID = s.createQuery(query);
            }
        }
    }
    public static ArrayList<Question> viewIncorrectQuestions(){
    }
    public static ArrayList<Question> randomQuestions(int size){
    }
}
