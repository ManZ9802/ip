import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents storage class.
 * Handles all methods related to storing and retrieving from Jerry.txt file.
 */

public class Storage {

    /**
     * Saves the list to path ./data/Jerry.txt.
     * Creates this path if it does not exist
     * @param list list to be saved
     * @throws IOException if there is issues saving the file
     */
    static void saveList(ArrayList<Task> list) throws IOException {
        File f = new File("./data/Jerry.txt");

        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }

        FileWriter fw = new FileWriter("./data/Jerry.txt");
        for (Task task : list) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }

    /**
     * Retrieves the list from path ./data/Jerry.txt.
     * List is empty if path does not exist
     * @param list list that Jerry.txt will copy to
     */
    static void loadTask(ArrayList<Task> list) {
        File file = new File("./data/Jerry.txt");
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs(); // Ensure the directory exists
                file.createNewFile(); // Create an empty file
                return; // No tasks to load
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
            return;
        }
        Scanner s;
        try {
            s = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }
        while (s.hasNextLine()) {
            String line = s.nextLine();
            Task task = Parser.parseTask(line);
            list.add(task);
        }
        s.close();
    }
}
