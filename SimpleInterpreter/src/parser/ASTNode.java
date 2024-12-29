package parser;
import compiler.CodeGenerator;
import runtime.ScopeManager;

public abstract class ASTNode {
    abstract void execute(ScopeManager scopeManager);

    protected void generateCode(ScopeManager scopeManager, CodeGenerator codeGen) {
    }
}
