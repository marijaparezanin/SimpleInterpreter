import compiler.Compiler;
import runtime.*;

public class Main {
    public static void main(String[] args) {
        String taskExamplePath = "src/task_example.txt";
        String helper = "src/helper.txt";

        Compiler compiler = new Compiler();
        compiler.compile(helper);

        Interpreter interpreter = new Interpreter();
        interpreter.interpret(helper);
    }
}
