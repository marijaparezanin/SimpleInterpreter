package runtime;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ScopeManager {
    private final Stack<Map<String, Integer>> scopes;

    public ScopeManager() {
        scopes = new Stack<>();
        scopes.push(new HashMap<>()); // Global scope
    }

    public void enterScope() {
        scopes.push(new HashMap<>());
    }

    public void exitScope() {
        scopes.pop();
    }

    public void assignVariable(String name, Integer value) {
        if (!scopes.isEmpty()) {
            scopes.peek().put(name, value);
        }
    }

    public Integer getVariable(String name) {
        for (int i = scopes.size() - 1; i >= 0; i--) {
            Map<String, Integer> scope = scopes.get(i);
            if (scope.containsKey(name)) {
                return scope.get(name);
            }
        }
        return null; // Variable not found
    }
}