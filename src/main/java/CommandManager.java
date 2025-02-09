public class CommandManager {
    public static void indentMessage(String message) {
        System.out.println("\t" + message);
    }

    public static void exitText() {
        printHoriLine();
        indentMessage("Bye have a great time!");
        printHoriLine();
    }

    public static void enterText() {
        printHoriLine();
        indentMessage("Hello! I'm Jerry");
        indentMessage("What can I do for you?");
        printHoriLine();
    }

    public static void printHoriLine() {
        System.out.println("__________________________________________________");
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
        indentMessage("Added task to list:");
        System.out.println("\t" + key);
        indentMessage("Now you have " + getListSize(list) + " task(s)");
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

    public static void markTask(Task[] list, String text, boolean done) {
        try {
            String number = text.replaceAll("\\D+", ""); // Remove all non-digits
            int i = Integer.parseInt(number);
            if (done) {
                list[i - 1].markAsDone();
                printHoriLine();
                indentMessage("Marked task " + i + " as done");
            } else {
                list[i - 1].markAsNotDone();
                printHoriLine();
                indentMessage("Marked task " + i + " as not done");
            }
            System.out.println("\t" + list[i - 1]);
            printHoriLine();
        } catch (NumberFormatException e) {
            printHoriLine();
            indentMessage("can't mark a non-numeric task");
            printHoriLine();
        } catch (NullPointerException e) {
            printHoriLine();
            indentMessage("task does not exist");
            printHoriLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            printHoriLine();
            indentMessage("list is not that long");
            printHoriLine();
        }
    }


    public static boolean processCommand(Task[] list, String text) {
        String command = text.split("\\s+")[0]; // Extract first word (command)
        switch (command) {
        case "list":
            printList(list);
            break;

        case "mark":
            markTask(list, text, true);
            break;

        case "unmark":
            markTask(list, text, false);
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

        case "bye":
            exitText();
            return false;

        default:
            printHoriLine();
            indentMessage("please define task: " + text);
            printHoriLine();
            break;
        }
        return true;
    }
}