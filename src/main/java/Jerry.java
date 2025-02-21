import java.util.Scanner;
import java.util.ArrayList;

public class Jerry {

    public static void main(String[] args) {
        CommandManager.enterText();
        String text;
        Scanner input = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        do {
            text = input.nextLine();
        } while (CommandManager.processCommand(list, text));
    }
}