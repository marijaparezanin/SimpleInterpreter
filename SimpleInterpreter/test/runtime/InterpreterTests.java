package runtime;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InterpreterTests {
    @Test
    public void testAssignment() {
        String filePath = "test/resources/positive/test_assignment.txt";

        Interpreter interpreter = new Interpreter();
        interpreter.interpret(filePath);

        ScopeManager scopeManager = interpreter.getScopeManager();
        assertEquals(10, scopeManager.getVariable("x"), "X should equal 10");
        assertEquals(10, scopeManager.getVariable("y"), "Y should equal x (10)");
        assertEquals(15, scopeManager.getVariable("z"), "Z should equal 15");
    }

    @Test
    public void testScope() {
        String filePath = "test/resources/positive/test_scope.txt";

        Interpreter interpreter = new Interpreter();
        interpreter.interpret(filePath);

        ScopeManager scopeManager = interpreter.getScopeManager();
        assertEquals(12, scopeManager.getVariable("x"), "X should equal 10");
        assertNull(scopeManager.getVariable("y"), "Y should be null");
    }


    @Test
    public void testUndefinedVariable() {
        String filePath = "test/resources/negative/test_undefined_variable.txt";

        Interpreter interpreter = new Interpreter();
        assertThrows(RuntimeException.class, () -> interpreter.interpret(filePath));
    }

    @Test
    public void testGlobalScopeEmpty(){
        String filePath = "test/resources/negative/test_empty_global_scope.txt";
        Interpreter interpreter = new Interpreter();
        interpreter.interpret(filePath);
        assertNull(interpreter.getScopeManager().getVariable("x"));
        assertNull(interpreter.getScopeManager().getVariable("y"));
    }

}