import java.util.Scanner;

public class Jerry {
    public static void printHoriLine() {
        System.out.println("\t");
        for (int i = 50; i > 0; i--) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void readBack(String line) {
        printHoriLine();
        System.out.println("\t" + line);
        printHoriLine();
    }

    public static void exitText() {
        printHoriLine();
        System.out.println("\tBye have a great time!");
        printHoriLine();
    }

    public static void enterText() {
        printHoriLine();
        System.out.println("\tHello! I'm Jerry\n"
                + "\tWhat can i do for you?");
        printHoriLine();
    }


    public static void main(String[] args) {
        enterText();
        String text;
        Scanner input = new Scanner(System.in);
        text = input.nextLine();
        while (!text.equals("bye")) {
            readBack(text);
            text = input.nextLine();
        }
        exitText();
    }

}