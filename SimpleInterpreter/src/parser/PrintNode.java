package parser;

import runtime.ScopeManager;

public class PrintNode extends ASTNode {
    private final String variable;

    public PrintNode(String variable) {
        this.variable = variable;
    }

    @Override
    void execute(ScopeManager scopeManager) {
        // Print the resolved value of the variable
        Integer value = scopeManager.getVariable(variable);
        if (value == null) {
            throw new RuntimeException("Undefined variable: " + variable);
        }
        System.out.println(value);
    }
}
