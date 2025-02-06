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
                System.out.println("\t" + (i + 1) + ". " + list[i]);
            }
        }
        printHoriLine();
    }

    public static void newEntry(Task[] list, Task key) {
        for (int i = 0; i < list.length; i++) {
            if (list[i] == null) {
                list[i] = key;
                return;
            }
        }
    }

    public static void printNewEntry(Task[] list, Task key) {
        printHoriLine();
        printTab("Added task to list:");
        System.out.println("\t" + key);
        printTab("Now you have " + getListSize(list) + " task(s)");
        printHoriLine();
    }

    public static int getListSize(Task[] list) {
        int size = 0;
        for (Task task : list) {
            if (task != null) {
                size++;
            }
        }
        return size;
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
                    System.out.println("\t" + list[i - 1]);
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
                    System.out.println("\t" + list[i - 1]);
                    printHoriLine();
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    printTab("Invalid unmark format! Expected: unmark <number>");
                }
                break;

            case "todo":
                Todo todo = new Todo(text.substring(4).trim());
                newEntry(list, todo);
                printNewEntry(list, todo);
                break;

            case "deadline":
                String description = text.substring(8, text.indexOf('/')).trim();
                String by = text.substring(text.indexOf('/') + 4).trim();
                Deadline deadline = new Deadline(description, by);
                newEntry(list, deadline);
                printNewEntry(list, deadline);
                break;

            case "event":
                String eventDescription = text.substring(6, text.indexOf('/')).trim();
                int firstIndex = text.indexOf('/');
                int secondIndex = text.indexOf('/', firstIndex + 1);
                String start = text.substring(firstIndex + 6, secondIndex).trim();
                String end = text.substring(secondIndex + 4).trim();
                Event event = new Event(eventDescription, start, end);
                newEntry(list, event);
                printNewEntry(list, event);
                break;

            default:
                printHoriLine();
                printTab("please define task: " + text);
                printHoriLine();
                break;
            }

            text = input.nextLine();
        }
        exitText();
    }
}