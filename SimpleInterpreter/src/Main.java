import compiler.Compiler;
import runtime.Interpreter;

public class Main {
    public static void main(String[] args) {
        String taskExamplePath = "src/task_example.txt";
        String compilerPath = "src/compiler_example.txt";
        String compilerOutputPath = "src/compiler_output.asm";

        Compiler compiler = new Compiler();
        compiler.compile(compilerPath, compilerOutputPath);

        Interpreter interpreter = new Interpreter();
        interpreter.interpret(taskExamplePath);
    }
}
