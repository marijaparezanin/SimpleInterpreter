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
        Integer resolvedValue = resolveValue(scopeManager, value);
        scopeManager.assignVariable(variable, resolvedValue);
    }

    private Integer resolveValue(ScopeManager scopeManager, String value) {
        // The value is saved as a string, as we have the case of it being another variable or a literal
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
