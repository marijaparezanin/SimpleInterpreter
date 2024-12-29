
import runtime.*;

public class Main {
    public static void main(String[] args) {
        String code = """
            x = 5
            print x
            {
                y = x
                print y
            }
            print x
        """;
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(code);


    }
}
