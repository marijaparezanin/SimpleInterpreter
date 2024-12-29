package parser;
import lexer.Token;

import java.util.List;

public class Parser {
    private final List<Token> tokens;
    private int position;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
    }

    public BlockNode parse() {
        BlockNode root = new BlockNode();

        while (hasNext()) {
            Token token = next();

            switch (token.getType()) {
                case ASSIGNMENT -> {
                    String variable = token.getValue();
                    Token valueToken = next();
                    root.addStatement(new AssignmentNode(variable, valueToken.getValue()));
                }
                case PRINT -> {
                    String variable = token.getValue();
                    root.addStatement(new PrintNode(variable));
                }
                case SCOPE_START -> {
                    BlockNode block = parse();
                    root.addStatement(block);
                }
                case SCOPE_END -> {
                    return root;
                }
                default -> throw new RuntimeException("Unexpected token: " + token);
            }
        }

        return root;
    }

    private boolean hasNext() {
        return position < tokens.size();
    }

    private Token next() {
        return tokens.get(position++);
    }
}
