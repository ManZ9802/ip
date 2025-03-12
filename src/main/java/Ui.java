import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents Ui class
 * Handles all the Ui related methods
 */
public class Ui {
    /**
     * Prints exit message and saves the task list to the Jerry.txt file
     * @param list task list to be saved
     * @throws IOException if issues occur when saving the list to the Jerry.txt file
     */
    public static void exitProcess(ArrayList<Task> list) throws IOException {
        printHoriLine();
        indentMessage("Bye have a great time!");
        printHoriLine();
        Storage.saveList(list);
    }

    /**
     * Prints entry message when the program is started up and retrieves task list from the Jerry.txt filr
     * @param list task list retrieved from the file
     */
    public static void enterText(ArrayList<Task> list) {
        printHoriLine();
        indentMessage("Hello! I'm Jerry");
        indentMessage("What can I do for you?");
        printHoriLine();
        Storage.loadTask(list);
    }

    public static void indentMessage(String message) {
        System.out.println("\t" + message);
    }

    public static void printHoriLine() {
        System.out.println("__________________________________________________");
    }

    /**
     * Main method that runs while program is active. User will input a string as argument and
     * processCommand will return true to loop back in order to get the next command, until user inputs "bye"
     * to exit the loop.
     */
    public static void run() {
        ArrayList<Task> list = new ArrayList<>();
        enterText(list);
        String text;
        Scanner input = new Scanner(System.in);
        do {
            text = input.nextLine();
        } while (CommandManager.processCommand(list, text));
    }
}
