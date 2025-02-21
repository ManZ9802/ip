import java.util.ArrayList;
import java.util.Objects;

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

    public static void printList(ArrayList<Task> list) {
        printHoriLine();
        for (int i = 0; i < list.size(); i++) {
                indentMessage((i + 1) + ": " + list.get(i).toString());
        }
        printHoriLine();
    }

    public static void printNewEntry(ArrayList<Task> list, Task key) {
        printHoriLine();
        indentMessage("Added task to list:");
        indentMessage(key.toString());
        indentMessage("Now you have " + list.size() + " task(s)");
        printHoriLine();
    }

    private static void markTask(ArrayList<Task> list, String text, boolean done) {
            String number = text.replaceAll("\\D+", ""); // Remove all non-digits
            int i = Integer.parseInt(number);
            if (done) {
                list.get(i - 1).markAsDone();
                printHoriLine();
                indentMessage("Marked task " + i + " as done");
            } else {
                list.get(i - 1).markAsNotDone();
                printHoriLine();
                indentMessage("Marked task " + i + " as not done");
            }
            indentMessage(list.get(i - 1).toString());
            printHoriLine();
    }

    private static void createAndAddTask(ArrayList<Task> list, String text, String type) throws IllegalDeadlineException, IllegalEventException {
        String taskName = text.substring(type.length()).trim();
        if (taskName.isEmpty()) {
            throw new IllegalArgumentException();
        }
        Task newTask;
        switch (type) {
        case "todo":
            newTask = new Todo(text.substring(4).trim());
            break;

        case "deadline":
            int byIndex = text.indexOf("/by");
            if (byIndex == -1) {
                throw new IllegalDeadlineException();
            }
            String description = text.substring(9, text.indexOf('/')).trim();
            String by = text.substring(text.indexOf('/') + 4).trim();
            if (by.isEmpty()) {
                throw new IllegalDeadlineException();
            }
            newTask = new Deadline(description, by);
            break;

        case "event":
            int fromIndex = text.indexOf("/from");
            int toIndex = text.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex) {
                throw new IllegalEventException();
            }
            String eventDescription = text.substring(6, text.indexOf('/')).trim();
            String start = text.substring(text.indexOf('/') + 6, text.lastIndexOf('/')).trim();
            String end = text.substring(text.lastIndexOf('/') + 4).trim();
            if (start.isEmpty() || end.isEmpty()) {
                throw new IllegalEventException();
            }
            newTask = new Event(eventDescription, start, end);
            break;

        default:
            newTask = null;
            break;
        }
        list.add(newTask);
        printNewEntry(list, Objects.requireNonNull(newTask));
    }

    public static void deleteTask(ArrayList<Task> list, String text) {
        String number = text.replaceAll("\\D+", "");
        int i = Integer.parseInt(number);
        printHoriLine();
        indentMessage("Noted, deleted task " + i + ": ");
        indentMessage(list.get(i - 1).toString());
        indentMessage("Now you have " + (list.size() - 1)+ " task(s)");
        printHoriLine();
        list.remove(i - 1);
    }

    public static boolean processCommand(ArrayList<Task> list, String text) {
        String command = text.split("\\s+")[0];// Extract first word (command)
        try {
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
            case "deadline":
            case "event":
                createAndAddTask(list, text, command);
                break;

            case "delete":
                deleteTask(list, text);
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
        } catch (IllegalArgumentException e) {
            printHoriLine();
            indentMessage("Task Name cannot be empty");
            printHoriLine();
        } catch (IllegalDeadlineException e) {
            printHoriLine();
            indentMessage("Task entered incorrectly, please enter <Task Name> /by <deadline>");
            printHoriLine();
        } catch (IllegalEventException e) {
            printHoriLine();
            indentMessage("Task entered incorrectly, please enter <Task Name> /from <start> /to <end>");
            printHoriLine();
        }
        return true;
    }
}