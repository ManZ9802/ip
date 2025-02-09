import java.util.Scanner;

public class Jerry {

    public static void main(String[] args) {
        CommandManager.enterText();
        String text;
        Scanner input = new Scanner(System.in);
        Task[] list = new Task[100];
        do {
            text = input.nextLine();
        } while (CommandManager.processCommand(list, text));
    }
}