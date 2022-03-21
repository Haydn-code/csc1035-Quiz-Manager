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
    public static Query returnAllQuestions(){
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query returnAllQuestions = s.createQuery("from questions");
        return returnAllQuestions;
    }
    public static Query searchQuestions(){
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
                s.beginTransaction();
                System.out.println("Please enter the question ID you would like to search for?");
                while (!sc.hasNextInt()) {
                    System.out.println("Please enter and integer");
                    sc.nextLine();
                }
                int questionID = sc.nextInt();
                sc.nextLine();
                Query searchQuestionID = s.createQuery("from questions q where q.questionID = " + questionID);
                s.getTransaction().commit();
                return searchQuestionID;
            case 2:
                s.beginTransaction();
                boolean valid = false;
                String type = null;
                while (!valid){
                    System.out.println("Please enter the type of question you would like to search for (mcq or saq");
                    type = sc.nextLine();
                    if (type == "mcq" || type == "saq"){
                        valid = true;
                    }
                }
                Query searchType = s.createQuery("from questions q where q.type = " + type);
                s.getTransaction().commit();
                return searchType;
            case 3:
                s.beginTransaction();
                System.out.println("Please enter the topic of question you wish to search for");
                String topic = sc.nextLine();
                Query searchTopic = s.createQuery("from questions q where q.topic = " + topic);
                s.getTransaction().commit();
                return searchTopic;
        }
    }
    public static Query viewIncorrectQuestions(){
    }

}
