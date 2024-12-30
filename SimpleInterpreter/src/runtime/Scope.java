package runtime;

import java.util.HashMap;
import java.util.Map;

public class Scope {
    private final Map<String, Integer> variables = new HashMap<>();
    private final Scope parent;

    public Scope(Scope parent) {
        this.parent = parent;
    }

    public Scope getParent() {
        return parent;
    }

    public void assign(String name, Integer value) {
        variables.put(name, value);
    }

    public Integer lookup(String name) {
        if (variables.containsKey(name)) {
            return variables.get(name);
        }
        if (parent != null) {
            return parent.lookup(name);
        }
        return null; // Not found
    }
}
