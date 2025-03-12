import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles all methods that manipulate the list of tasks
 */
public class TaskList {
    /**
     * marks the specific task in the list as done or not
     * @param list Task list
     * @param text Task number in the list
     * @param done True if task is to be marked as done
     */
    static void markTask(ArrayList<Task> list, String text, boolean done) {
        String number = text.replaceAll("\\D+", ""); // Remove all non-digits
        int i = Integer.parseInt(number);
        if (done) {
            list.get(i - 1).markAsDone();
            Ui.printHoriLine();
            Ui.indentMessage("Marked task " + i + " as done");
        } else {
            list.get(i - 1).markAsNotDone();
            Ui.printHoriLine();
            Ui.indentMessage("Marked task " + i + " as not done");
        }
        Ui.indentMessage(list.get(i - 1).toString());
        Ui.printHoriLine();
    }

    /**
     * Creates a new task and adds it to the list
     * @param list Task list
     * @param text Task description, deadline and/or dates
     * @param type Determines what type of task to be created
     * @throws IllegalDeadlineException if user enters wrong argument for deadline task
     * @throws IllegalEventException if user enters wrong argument for event task
     */
    static void createAndAddTask(ArrayList<Task> list, String text, String type) throws IllegalDeadlineException, IllegalEventException {
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

    /**
     * Deletes a task and removes it from the list
     * @param list Task list
     * @param text task number in the list
     */
    public static void deleteTask(ArrayList<Task> list, String text) {
        String number = text.replaceAll("\\D+", "");
        int i = Integer.parseInt(number);
        Ui.printHoriLine();
        Ui.indentMessage("Noted, deleted task " + i + ": ");
        Ui.indentMessage(list.get(i - 1).toString());
        Ui.indentMessage("Now you have " + (list.size() - 1) + " task(s)");
        Ui.printHoriLine();
        list.remove(i - 1);
    }

    /**
     * prints the list out
     * @param list Task list
     */
    public static void printList(ArrayList<Task> list) {
        Ui.printHoriLine();
        if (list.isEmpty()) {
            Ui.indentMessage("No tasks found!");
        } else {
            for (int i = 0; i < list.size(); i++) {
                Ui.indentMessage((i + 1) + ": " + list.get(i).toString());
            }
        }
        Ui.printHoriLine();
    }

    /**
     * Informs user that task has been created and how many tasks there are in the list.
     * @param list Task list
     * @param key Task to be printed out
     */
    public static void printNewEntry(ArrayList<Task> list, Task key) {
        Ui.printHoriLine();
        Ui.indentMessage("Added task to list:");
        Ui.indentMessage(key.toString());
        Ui.indentMessage("Now you have " + list.size() + " task(s)");
        Ui.printHoriLine();
    }

    public static void findTask(ArrayList<Task> list, String text) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        String description = text.substring(5).trim();
        for (Task task : list) {
            if (task.toString().contains(description)) {
                matchingTasks.add(task);
            }
        }
        if (matchingTasks.isEmpty()) {
            Ui.printHoriLine();
            Ui.indentMessage("No tasks found!");
            Ui.printHoriLine();
            return;
        }
        Ui.printHoriLine();
        Ui.indentMessage("Found " + matchingTasks.size() + " task(s)");
        printList(matchingTasks);
    }
}
