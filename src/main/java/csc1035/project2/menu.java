package csc1035.project2;

import org.hibernate.query.Query;
import java.util.Scanner;

/**
 * A class which will provide an interface giving the user options for what they want to do
 */
public class menu {
    /**
     * defined an empty constructor
     */
    public menu(){
    }

    /**
     * Will run the main program
     */
    public static void main(String[] args) {
        menu m = new menu();
        m.selector();
    }

    /**
     * Determines what the program will do based of the users inputs
     */
    private void selector() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu please enter an int 1-13:"); //displays menu options to user
        System.out.println("1. Run Quiz");
        System.out.println("2. Create Quiz");
        System.out.println("3. Read Quiz");
        System.out.println("4. Update Quiz");
        System.out.println("5. Delete Quiz");
        System.out.println("6. Create Question");
        System.out.println("7. Read Question");
        System.out.println("8. Update Question");
        System.out.println("9. Delete Question");
        System.out.println("10. Import Quiz");
        System.out.println("11. Export Quiz");
        System.out.println("12. View previously incorrect questions");
        System.out.println("13. Return All questions");
        System.out.println("14. Search Questions");
        System.out.println("15. Exit");
        boolean loop = true;
        while (loop) { //will return to the menu after every process has finished unless exit is selected
            while (!sc.hasNextInt()) { //checks the input is an integer
                System.out.println("Please enter an int 1-13");
                sc.nextLine();
            }
            int selection = sc.nextInt();
            sc.nextLine();
            switch (selection) { //runs the corresponding process to the user input
                case 1:
                    runningQuiz();
                    break;
                case 2:
                    createQuiz();
                    break;
                case 3:
                    readQuiz();
                    break;
                case 4:
                    updateQuiz();
                    break;
                case 5:
                    deleteQuiz();
                    break;
                case 6:
                    createQuestion();
                    break;
                case 7:
                    readQuestion();
                    break;
                case 8:
                    updateQuestion();
                    break;
                case 9:
                    deleteQuestion();
                    break;
                case 10:
                    importQuiz();
                    break;
                case 11:
                    exportQuiz();
                    break;
                case 12:
                    searchQuestions(queryDatabase.viewIncorrectQuestions());
                    break;
                case 13:
                    searchQuestions(queryDatabase.returnAllQuestions());
                    break;
                case 14:
                    searchQuestions(queryDatabase.searchQuestions());
                    break;
                case 15:
                    loop = false;
                    break;
            }
        }
    }
    private void runningQuiz(){
        //will implement function from other class
    }
    private void createQuiz(){
        //will implement function from other class
    }
    private void readQuiz(){
        //will implement function from other class
    }
    private void updateQuiz(){
        //will implement function from other class
    }
    private void deleteQuiz(){
        //will implement function from other class
    }
    private void createQuestion(){
        //will implement function from other class
    }
    private void readQuestion(){
        //will implement function from other class
    }
    private void updateQuestion(){
        //will implement function from other class
    }
    private void deleteQuestion(){
        //will implement function from other class
    }
    private void importQuiz(){
        //will implement function from other class
    }
    private void exportQuiz(){
        //will implement function from other class
    }

    /**
     * prints the results of a questions query to the console
     * @param q - the type of query the user wishes to run
     */
    private void searchQuestions(Query q){
        for (Object i: q.getResultList()){
            Question q2 = (Question) i;
            System.out.println(q2);
        }
    }
}
