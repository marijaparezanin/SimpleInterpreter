package parser;

import compiler.CodeGenerator;
import runtime.ScopeManager;

public class PrintNode extends ASTNode {
    private final String variable;

    public PrintNode(String variable) {
        this.variable = variable;
    }

    @Override
    void execute(ScopeManager scopeManager) {
        Integer value = scopeManager.getVariable(variable);
        /**if (value == null) {
            throw new RuntimeException("Undefined variable: " + variable);
        }**/
        //Per task request printing null instead of raising an exception
        System.out.println(value);
    }

    @Override
    public void generateCode(ScopeManager scopeManager, CodeGenerator codeGen) {
        Integer value = scopeManager.getVariable(variable);
        if (value == null) {
            throw new RuntimeException("Undefined variable: " + variable);
        }
        codeGen.generatePrintCode(String.valueOf(value));
    }
}
