package runtime;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTest {
    @Test
    public void testAssignmentPositive() {
        String filePath = "test/resources/test_assignment.txt";

        Interpreter interpreter = new Interpreter();
        interpreter.interpret(filePath);

        ScopeManager scopeManager = interpreter.getScopeManager();
        assertEquals(10, scopeManager.getVariable("x"), "X should equal 10");
        assertEquals(10, scopeManager.getVariable("y"), "Y should equal x (10)");
        assertEquals(15, scopeManager.getVariable("z"), "Z should equal 15");
    }

}