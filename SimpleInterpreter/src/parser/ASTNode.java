package parser;
import runtime.ScopeManager;

public abstract class ASTNode {
    abstract void execute(ScopeManager scopeManager);
}
