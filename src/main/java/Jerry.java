import java.io.FileNotFoundException;
import java.util.Scanner;

public class Jerry {

    public static void main(String[] args) throws FileNotFoundException {
        Task[] list = new Task[100];
        CommandManager.enterText(list);
        String text;
        Scanner input = new Scanner(System.in);
        do {
            text = input.nextLine();
        } while (CommandManager.processCommand(list, text));
    }
}