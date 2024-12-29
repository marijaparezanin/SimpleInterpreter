package runtime;

import parser.Parser;
import parser.BlockNode;
import lexer.Lexer;
import lexer.Token;

import java.util.List;

public class Interpreter {
    private final ScopeManager scopeManager;

    public Interpreter() {
        this.scopeManager = new ScopeManager();
    }

    public void interpret(String code) {
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.tokenize(code);
        Parser parser = new Parser(tokens);
        BlockNode root = parser.parse();
        root.execute(scopeManager);
    }
}