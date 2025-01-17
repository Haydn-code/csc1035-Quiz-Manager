package csc1035.project2;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Defined a class that will allow, for the creation, reading, updating and deleting of quizes
 */
public class CrudQuiz {
    /**
     * Defined a default constructor
     */
    public CrudQuiz(){}

    /**
     * Defined a method which allows the user to select what type of quiz they wish to create
     * @param s - the hibernate session that is open for the duration of the program
     */
    public static void createQuiz(Session s){
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter the title of the quiz you wish to create");
        String title = sc.nextLine();
        System.out.println("Please enter an integer 1-5 ");
        System.out.println("1. Randomly generate a 5 question quiz");
        System.out.println("2. Randomly generate a 10 question quiz");
        System.out.println("3. Randomly generate a 15 question quiz");
        System.out.println("4. Randomly generate a 20 question quiz");
        System.out.println("5. Create your own custom quiz");
        while(!sc.hasNextInt()){
            System.out.println("Please enter an integer 1-5");
            sc.nextLine();
        }
        //chooses the selected quiz based of the users input
        int choice = sc.nextInt();
        switch(choice){
            case 1:
                randomQuiz(s, 5, title);
                break;
            case 2:
                randomQuiz(s, 10, title);
                break;
            case 3:
                randomQuiz(s, 15, title);
                break;
            case 4:
                randomQuiz(s, 20, title);
                break;
            case 5:
                customQuiz(s, title);
                break;
        }
    }

    /**
     * Creates a quiz of random questions with the title and amount given by the user and stores it in the database
     * @param s - the hibernate session that runs until the program closes
     * @param amount - the number of questions that the user wishes to see in the quiz
     * @param title - the user chosen title of the quiz
     */
    public static void randomQuiz(Session s, int amount, String title){
        List possibleQuestions = QueryDatabase.returnAllQuestions(s).getResultList(); //fetches the list of all
        // questions to randomly select questions from
        List<Question> chosenQuestions = new ArrayList<Question>();
        Random random = new Random();
        s.beginTransaction();
        for (int i = 0; i<amount; i++){ //creates a list of random questions from the database of size defined by the
            //user
            int index = random.nextInt(possibleQuestions.size());
            Question q = (Question) possibleQuestions.get(index);
            chosenQuestions.add(q);
        }
        Quiz quiz = new Quiz(title);
        List<QuizQuestions> quizQuestions = new ArrayList<QuizQuestions>();
        for (Question q : chosenQuestions){ //creates a list of QuizQuestions from the selected questions
            // and saves them individually in the database
            QuizQuestions qQ = new QuizQuestions(quiz, q);
            s.save(qQ);
            quizQuestions.add(qQ);
        }
        //adds the list of QuizQuestions to the quiz object and saves it to the database
        quiz.setqQuestions(quizQuestions);
        s.save(quiz);
        s.getTransaction().commit();
    }

    /**
     * Allows the user to select which questions they wish to add to the quiz and how long they wish the quiz to be
     * @param s - the hibernate session that runs until the program closes
     * @param title - the user chosen title of the quiz
     */
    public static void customQuiz(Session s, String title){
        Scanner sc = new Scanner(System.in);
        boolean loop = true;
        Quiz quiz = new Quiz(title);
        List<QuizQuestions> quizQuestions = new ArrayList<QuizQuestions>();
        while (loop){
            System.out.println("Please enter a number 1-3");
            System.out.println("1. Add another question to the quiz");
            System.out.println("2. View the list of all questions");
            System.out.println("3. Finish and save the quiz");
            while(!sc.hasNextInt()){
                System.out.println("Please enter an integer 1-3");
                sc.nextLine();
            }
            int choice = sc.nextInt();
            sc.nextLine();
            switch(choice) {
                case 1:
                    Question q = CrudQuestions.selectQuestion(s, sc);
                    QuizQuestions qQ = new QuizQuestions(quiz, q);
                    s.beginTransaction();
                    s.save(qQ);
                    s.getTransaction().commit();
                    quizQuestions.add(qQ);
                    break;
                case 2:
                    Query query1 = QueryDatabase.returnAllQuestions(s);
                    for (Object i: query1.getResultList()){ //iterates through the results of the query and prints to the console
                        Question q2 = (Question) i;
                        System.out.println(q2);
                    }
                    break;
                case 3:
                    loop = false;
                    break;
            }
        }
        quiz.setqQuestions(quizQuestions);
        s.beginTransaction();
        s.save(quiz);
        s.getTransaction().commit();
    }

    /**
     * Defined a method that displays all the quiz's stored in the database to the user
     * @param s - the hibernate session that is open for the duration of the program
     */
    public static void readQuiz(Session s){
        s.beginTransaction();
        //iterates through every quizes entity and outputs it to the console
        Query q = s.createQuery("from quizes");
        for (Object i : q.getResultList()){
            Quiz quiz = (Quiz) i;
            System.out.println(quiz);
            s.getTransaction().commit();
        }
    }

    /**
     * Defined a method which allows the user to update an existing quiz
     * @param s - the hibernate session that is open for the duration of the program
     */
    public static void updateQuiz(Session s){
        Scanner sc = new Scanner(System.in);
        Quiz quiz = selectQuiz(s, sc); //allows the user to select which quiz they wish to update
        boolean loop = true;
        while (loop) { //code will loop until the user decides they have finished updating the quiz
            System.out.println(quiz);
            System.out.println("Please enter a integer 1-3");
            System.out.println("1. Add a question to the quiz");
            System.out.println("2. Remove a question from the quiz");
            System.out.println("3. Finish and save quiz to the database");
            while (!sc.hasNextInt()) {//ensures that the user enters an integer
                System.out.println("Please enter an integer 1-3");
                sc.nextLine();
            }
            int choice = sc.nextInt();
            //runs the corresponding code to the user input
            switch (choice) {
                case 1:
                    Question q = CrudQuestions.selectQuestion(s, sc);//allows the user to select which question they
                    //wish to add to the quiz
                    QuizQuestions qQ = new QuizQuestions(quiz, q);
                    s.beginTransaction();
                    s.save(qQ);
                    s.getTransaction().commit();
                    List<QuizQuestions> qQuestions = quiz.getqQuestions();
                    qQuestions.add(qQ);
                    break;
                case 2:
                    //allows the user to select the relationship between Quiz and QuizQuestion that they wish to remove
                    System.out.println("Please enter the QQJD of the QuizQuestion you wish to remove");
                    while (!sc.hasNextInt()) {
                        System.out.println("Please enter an integer");
                        sc.nextLine();
                    }
                    int QQJD = sc.nextInt();
                    sc.nextLine();
                    s.beginTransaction();
                    QuizQuestions qQ1 = s.get(QuizQuestions.class, QQJD);
                    s.delete(qQ1);
                    s.getTransaction().commit();
                    List<QuizQuestions> qQL = quiz.getqQuestions();
                    qQL.remove(qQ1);
                    quiz.setqQuestions(qQL);
                case 3:
                    //Apply changes to the database and ends the loop
                    s.beginTransaction();
                    s.update(quiz);
                    s.getTransaction().commit();
                    loop = false;
            }
        }
    }

    /**
     * Added a method for deleting a quiz
     * @param s - this hibernate session that is open for the duration of the program
     */
    public static void deleteQuiz(Session s){
        Scanner sc = new Scanner(System.in);
        Quiz quiz = selectQuiz(s, sc);
        s.beginTransaction();
        for (QuizQuestions qQ : quiz.getqQuestions()){//iterates through all the relationships between this
            // quiz and questions and deletes them from the database accordingly
            s.delete(qQ);
        }
        s.delete(quiz);
        s.getTransaction().commit();
    }
    public static Quiz selectQuiz(Session s, Scanner sc){
        System.out.println("Please enter the questionID of the quiz you wish to select");
        while (!sc.hasNextInt()) { //ensures the questionID is an integer
            System.out.println("Please enter an integer");
            sc.nextLine();
        }
        int quizID = sc.nextInt();
        sc.nextLine();
        s.beginTransaction();
        Quiz quiz = s.get(Quiz.class, quizID);
        s.getTransaction().commit();
        return quiz;
    }
}
