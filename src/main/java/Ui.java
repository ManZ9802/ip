import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static void exitProcess(ArrayList<Task> list) throws IOException {
        printHoriLine();
        indentMessage("Bye have a great time!");
        printHoriLine();
        Storage.saveList(list);
    }

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
