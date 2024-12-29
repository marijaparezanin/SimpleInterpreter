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
            codeGen.emit("mov " + variable + ", " + Integer.parseInt(value));      // Move value to variable directly
            scopeManager.assignVariable(variable, Integer.parseInt(value)); // save the variable to scope
        } catch (NumberFormatException e) {
            //variable
            Integer assigningVariableValue = scopeManager.getVariable(value);
            if (assigningVariableValue == null) {
                throw new RuntimeException("Undefined variable: " + value);
            }

            // Get a register to hold the value of the variable
            String register = codeGen.allocateRegister();
            //fetch from memory to register
            codeGen.emit("mov " + register + ", " + value);
            codeGen.emit("mov " + variable + ", " + register); //  register value -> target variable
            scopeManager.assignVariable(variable, assigningVariableValue);
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
