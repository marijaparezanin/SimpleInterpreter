package runtime;

import parser.Parser;
import parser.BlockNode;
import lexer.Lexer;
import lexer.Token;
import utils.FileReader;

import java.io.IOException;
import java.util.List;

public class Interpreter {
    private final ScopeManager scopeManager;

    public Interpreter() {
        this.scopeManager = new ScopeManager();
    }

    public void interpret(String filePath) {
        String code = null;
        try {
            code = FileReader.readProgram(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.tokenize(code);
        Parser parser = new Parser(tokens);
        BlockNode root = parser.parse();
        root.execute(scopeManager);
    }
}