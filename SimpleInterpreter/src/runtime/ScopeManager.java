package runtime;

public class ScopeManager {
    private Scope currentScope;

    public ScopeManager() {
        //not going to create a new scope since treating the entire program as a BlockAST
    }

    public void enterScope() {
        currentScope = new Scope(currentScope);
    }

    public void exitScope() {
        if (currentScope.getParent() != null) { // Retain global scope
            currentScope = currentScope.getParent();
        }
    }

    public void assignVariable(String name, Integer value) {
        currentScope.assign(name, value);
    }

    public Integer getVariable(String name) {
        return currentScope.lookup(name);
    }

}