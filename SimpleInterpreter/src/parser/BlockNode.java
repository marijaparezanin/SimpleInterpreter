package parser;

import compiler.CodeGenerator;
import runtime.ScopeManager;

import java.util.ArrayList;
import java.util.List;

public class BlockNode extends ASTNode {
    private final List<ASTNode> statements = new ArrayList<>();

    public void addStatement(ASTNode statement) {
        statements.add(statement);
    }

    @Override
    public void execute(ScopeManager scopeManager) {
        scopeManager.enterScope();
        try {
            for (ASTNode statement : statements) {
                statement.execute(scopeManager);
            }
        } finally {
            scopeManager.exitScope();
        }
    }

    @Override
    public void generateCode(ScopeManager scopeManager, CodeGenerator codeGen) {
        scopeManager.enterScope();
        for (ASTNode statement : statements) {
            statement.generateCode(scopeManager, codeGen);
        }
        scopeManager.exitScope();
    }

}
