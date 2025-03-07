public class Parser {
    static Task parseTask(String line) {
        char taskType = line.charAt(1);
        boolean isDone = line.charAt(4) == 'X';
        switch (taskType) {
        case 'T':
            String tDescription = line.substring(7).trim();
            Task todo = new Todo(tDescription);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case 'D':
            int byIndex = line.indexOf("(by:");
            String dDescription = line.substring(7, byIndex).trim();
            String deadline = line.substring(byIndex + 4, line.length() - 1).trim();
            Task deadlineTask = new Deadline(dDescription, deadline);
            if (isDone) {
                deadlineTask.markAsDone();
            }
            return deadlineTask;
        case 'E':
            int fromIndex = line.indexOf("(from:");
            int toIndex = line.indexOf(", to:");
            String eDescription = line.substring(7, fromIndex).trim();
            String startTime = line.substring(fromIndex + 6, toIndex).trim();
            String endTime = line.substring(toIndex + 5, line.length() - 1).trim();
            Task eventTask = new Event(eDescription, startTime, endTime);
            if (isDone) {
                eventTask.markAsDone();
            }
            return eventTask;
        default:
            return null;
        }
    }
}
