import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Jerry {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        CommandManager.enterText(list);
        String text;
        Scanner input = new Scanner(System.in);
        do {
            text = input.nextLine();
        } while (CommandManager.processCommand(list, text));
    }
}