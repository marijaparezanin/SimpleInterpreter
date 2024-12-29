package parser;

import runtime.ScopeManager;

public class AssignmentNode extends ASTNode {
    private final String variable;
    private final String value;

    public AssignmentNode(String variable, String value) {
        this.variable = variable;
        this.value = value;
    }

    @Override
    void execute(ScopeManager scopeManager) {
        // Assign the resolved value to the variable
        Integer resolvedValue = resolveValue(scopeManager, value);
        scopeManager.assignVariable(variable, resolvedValue);
    }

    private Integer resolveValue(ScopeManager scopeManager, String value) {
        // If the value is a number, parse it; otherwise, retrieve it as a variable
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            Integer variableValue = scopeManager.getVariable(value);
            if (variableValue == null) {
                throw new RuntimeException("Undefined variable: " + value);
            }
            return variableValue;
        }
    }
}
