# Jerry Chatbot

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Using the chatbot

Prerequisites: JDK 17 installed, jar file downloaded

1. Run "java -jar Jerry.jar" in your terminal, ensure you are in the directory where you stored Jerry.jar
2. Enter a command from the list below

## Command list
1. **list** - print the list of tasks
2. **mark x** - mark task number 'x' as done
3. **unmark x** - mark task number 'x' as not done
4. **todo string** - create a todo with the description "string"
5. **deadline string /by date** -  create a deadline with description "string" and deadline "date"
6. **event string /from start /to end** - create an event with description "string", start time "start" and end time "end"
7. **delete x** - remove task number 'x' from the list
8. **find string** - display tasks which description contains "string"
9. **bye** - exits the program


**Warning:** Keep the `src\main\java` folder as the root folder for Java files (i.e., don't rename those folders or move Java files to another folder outside of this folder path), as this is the default location some tools (e.g., Gradle) expect to find Java files.
