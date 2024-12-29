import runtime.*;

public class Main {
    public static void main(String[] args) {
        String taskExamplePath = "src/task_example.txt";
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(taskExamplePath);
    }
}
