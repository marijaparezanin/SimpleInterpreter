
import runtime.*;

public class Main {
    public static void main(String[] args) {
        String taskExamplePath = "src/taskExample.txt";
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(taskExamplePath);


    }
}
