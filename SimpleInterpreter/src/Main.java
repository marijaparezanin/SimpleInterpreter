
import runtime.*;

public class Main {
    public static void main(String[] args) {
        String code = """
x = 1
print x
scope {
 x = 2
 print x
 scope {
   x = 3
   y = x
   print x
   print y
 }
 print x
 print y
}
print x
        """;


        Interpreter interpreter = new Interpreter();
        interpreter.interpret(code);


    }
}
