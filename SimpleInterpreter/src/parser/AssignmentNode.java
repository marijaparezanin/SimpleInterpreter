package parser;

import compiler.CodeGenerator;
import runtime.ScopeManager;

public class AssignmentNode extends ASTNode {
    private final String variable;
    private final String value;

    public AssignmentNode(String variable, String value) {
        try{
            Integer.parseInt(variable);
            throw new RuntimeException("Can't reassign value to a literal");
        } catch (NumberFormatException e) {
            this.variable = variable;
            this.value = value;
        }
    }

    @Override
    void execute(ScopeManager scopeManager) {
        Integer resolvedValue = resolveValue(scopeManager, value);
        scopeManager.assignVariable(variable, resolvedValue);
    }

    @Override
    public void generateCode(ScopeManager scopeManager, CodeGenerator codeGen) {
        try {
            //literal
            Integer literalValue = Integer.parseInt(value);
            scopeManager.assignVariable(variable, literalValue); // save the variable to scope
            if(codeGen.declareVariable(variable, literalValue)){ //first time declared, so it is initialized
                return;                                          //skip unnecessary register assignment
            }
            codeGen.generateCodeForAssignment(variable, value);
        } catch (NumberFormatException e) {
            //variable
            Integer assigningVariableValue = scopeManager.getVariable(value);
            if (assigningVariableValue == null) {
                throw new RuntimeException("Undefined variable: " + value);
            }
            //Add to scope
            scopeManager.assignVariable(variable, assigningVariableValue);
            if(codeGen.declareVariable(variable, assigningVariableValue)){
                return;
            }
            codeGen.generateCodeForAssignment(variable, value);
        }
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
