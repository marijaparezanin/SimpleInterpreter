package runtime;

import parser.Parser;
import parser.BlockNode;
import lexer.Lexer;
import lexer.Token;
import utils.FileReader;

import java.util.List;

public class Interpreter {
    private final ScopeManager scopeManager;

    public Interpreter() {
        this.scopeManager = new ScopeManager();
    }

    public void interpret(String filePath) {
        String code = FileReader.readCode(filePath);
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.tokenize(code);
        Parser parser = new Parser(tokens);
        BlockNode root = parser.parse();
        root.execute(scopeManager);
        Integer val = scopeManager.getVariable("x");
        System.out.println(val);
    }
}