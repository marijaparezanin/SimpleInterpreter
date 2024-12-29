package lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        String[] lines = input.split("\\n");

        for (String line : lines) {
            line = line.trim();

            if (line.startsWith("scope {")) {
                tokens.add(new Token(Token.Type.SCOPE_START, "scope"));
            } else if (line.equals("}")) {
                tokens.add(new Token(Token.Type.SCOPE_END, "}"));
            } else if (line.startsWith("print ")) {
                tokens.add(new Token(Token.Type.PRINT, line.substring(6).trim()));
            } else if (line.contains("=")) {
                String[] parts = line.split("=");
                tokens.add(new Token(Token.Type.ASSIGNMENT, parts[0].trim()));
                String value = parts[1].trim();
                if (value.matches("\\d+")) {
                    tokens.add(new Token(Token.Type.INTEGER, value));
                } else {
                    tokens.add(new Token(Token.Type.VARIABLE, value));
                }
            }
        }

        return tokens;
    }
}
