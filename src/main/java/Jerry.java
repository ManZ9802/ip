import java.util.Scanner;

public class Jerry {
        public static void printTab(String message) {
            System.out.println("\t" + message);
        }

    public static void printHoriLine() {
        for (int i = 50; i > 0; i--) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void readBack(String line) {
        printHoriLine();
        printTab(line);
        printHoriLine();
    }

    public static void exitText() {
        printHoriLine();
        printTab("Bye have a great time!");
        printHoriLine();
    }

    public static void enterText() {
        printHoriLine();
        printTab("Hello! I'm Jerry");
        printTab("What can I do for you?");
        printHoriLine();
    }

    public static void printList(String[] list) {
        printHoriLine();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                printTab(i+1 + ". " + list[i]);
            }
        }
        printHoriLine();
    }

    public static void newEntry(String[] list, String key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = key;
                return;
            }
        }
    }


    public static void main(String[] args) {
        enterText();
        String text;
        Scanner input = new Scanner(System.in);
        text = input.nextLine();
        String[] list = new String[100];
        while (!text.equals("bye")) {
            //if text == list, printList, text=input, skip the rest of the loop
            if (text.equals("list")) {
                printList(list);
                text = input.nextLine();
                continue;
            }
            //1. add text to list, print added: readback, text = input
            newEntry(list, text);
            printHoriLine();
            printTab("added: " + text);
            printHoriLine();
            text = input.nextLine();
        }
        exitText();
    }
}