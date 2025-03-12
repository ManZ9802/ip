/**
 * <h1> Jerry Chatbot</h1>
 * <p>
 * The Jerry chatbot implements a task tracker that tracks three types of
 * tasks: Todo, Event, Deadline. </p>
 * <p>
 *     It takes in different commands as argument and returns an output
 *     based on those commands.
 * </p>
 * <p>
 *     When loading in, Jerry will read from ./data/Jerry.txt file for any pre-existing list
 *     and will save to the same file when exiting to preserve the list when the program is closed.
 * </p>
 * @author Man Zhong
 * @version 1.0
 * @since AY24/25 Semester 2
 */

public class Jerry {
    public static void main(String[] args) {
        Ui.run();
    }
}