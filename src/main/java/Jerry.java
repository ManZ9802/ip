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

    public static void printList(Task[] list) {
        printHoriLine();
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                System.out.print("\t" + (i + 1) + ". ");
                list[i].printTask();
            }
        }
        printHoriLine();
    }

    public static void newEntry(Task[] list, String key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = new Task(key);
                return;
            }
        }
    }


    public static void main(String[] args) {
        enterText();
        String text;
        Scanner input = new Scanner(System.in);
        text = input.nextLine();
        Task[] list = new Task[100];
        while (!text.equals("bye")) {
            String command = text.split("\\s+")[0]; // Extract first word (command)

            switch (command) {
            case "list":
                printList(list);
                break;

            case "mark":
                try {
                    int i = Integer.parseInt(text.substring(5).trim());
                    list[i - 1].markAsDone();
                    printHoriLine();
                    printTab("Marked task " + i + " as done");
                    System.out.print("\t");
                    list[i - 1].printTask();
                    printHoriLine();
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    printTab("Invalid mark format! Expected: mark <number>");
                }
                break;

            case "unmark":
                try {
                    int i = Integer.parseInt(text.substring(7).trim());
                    list[i - 1].markAsNotDone();
                    printHoriLine();
                    printTab("Marked task " + i + " as not done");
                    System.out.print("\t");
                    list[i - 1].printTask();
                    printHoriLine();
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    printTab("Invalid unmark format! Expected: unmark <number>");
                }
                break;

            default:
                newEntry(list, text);
                printHoriLine();
                printTab("added: " + text);
                printHoriLine();
                break;
            }

            text = input.nextLine();
        }
        exitText();
    }
}