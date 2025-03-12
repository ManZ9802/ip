/**
 * Todo is part of the Task class with only a description and boolean on whether it is done.
 * @see Task
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}