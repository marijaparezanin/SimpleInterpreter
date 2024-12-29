package lexer;

public class Token {
    public enum Type { ASSIGNMENT, PRINT, SCOPE_START, SCOPE_END, VARIABLE, INTEGER }

    private final Type type;
    private final String value;

    public Token(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public String getValue() {
        return value;
    }
}
