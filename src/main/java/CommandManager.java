import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles processing of user input.
 */
public class CommandManager {

    /**
     * Handles all possible commands and returns a specific output based on the command.
     * @param list Task list to be processed.
     * @param text String that command is extracted from by checking the first word of the string
     * @return True unless user enters "bye"
     * @throws NumberFormatException if user marks a non-numeric task in the list
     * @throws NullPointerException if user specifies a task that does not exist
     * @throws ArrayIndexOutOfBoundsException if user refers to a task that exceeds the number of tasks in the list
     * @throws IllegalArgumentException if user enters a wrong argument for the task
     * @throws IllegalDeadlineException if user enters wrong argument for deadline task
     * @throws IllegalEventException if user enters wrong argument for event task
     *
     */
    public static boolean processCommand(ArrayList<Task> list, String text) {
        String command = text.split("\\s+")[0];// Extract first word (command)
        try {
            switch (command) {
            case "list":
                TaskList.printList(list);
                break;

            case "mark":
                TaskList.markTask(list, text, true);
                break;

            case "unmark":
                TaskList.markTask(list, text, false);
                break;

            case "todo":
            case "deadline":
            case "event":
                TaskList.createAndAddTask(list, text, command);
                break;

            case "delete":
                TaskList.deleteTask(list, text);
                break;

            case "bye":
                Ui.exitProcess(list);
                return false;

            default:
                Ui.printHoriLine();
                Ui.indentMessage("please define task: " + text);
                Ui.printHoriLine();
                break;
            }
        } catch (NumberFormatException e) {
            Ui.printHoriLine();
            Ui.indentMessage("can't mark a non-numeric task");
            Ui.printHoriLine();
        } catch (NullPointerException e) {
            Ui.printHoriLine();
            Ui.indentMessage("task does not exist");
            Ui.printHoriLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printHoriLine();
            Ui.indentMessage("list is not that long");
            Ui.printHoriLine();
        } catch (IllegalArgumentException e) {
            Ui.printHoriLine();
            Ui.indentMessage("Task Name cannot be empty");
            Ui.printHoriLine();
        } catch (IllegalDeadlineException e) {
            Ui.printHoriLine();
            Ui.indentMessage("Task entered incorrectly, please enter <Task Name> /by <deadline>");
            Ui.printHoriLine();
        } catch (IllegalEventException e) {
            Ui.printHoriLine();
            Ui.indentMessage("Task entered incorrectly, please enter <Task Name> /from <start> /to <end>");
            Ui.printHoriLine();
        } catch (IOException e) {
            Ui.printHoriLine();
            e.printStackTrace();
            Ui.printHoriLine();
        }
        return true;
    }
}