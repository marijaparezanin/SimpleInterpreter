package compiler;

import lexer.Lexer;
import lexer.Token;
import parser.BlockNode;
import parser.Parser;
import runtime.ScopeManager;
import utils.FileReader;

import java.util.List;

public class Compiler {
    private final ScopeManager scopeManager;

    public Compiler() {
        this.scopeManager = new ScopeManager();
    }

    public ScopeManager getScopeManager() {
        return scopeManager;
    }

    public void compile(String filePath, String outputFilePath) {
        String code = FileReader.readCode(filePath);
        Lexer lexer = new Lexer();
        List<Token> tokens = lexer.tokenize(code);
        Parser parser = new Parser(tokens);
        BlockNode root = parser.parse(); // Parse the source code into an AST
        CodeGenerator codeGen = new CodeGenerator();
        root.generateCode(scopeManager, codeGen); // Generate machine code

        codeGen.writeToFile(outputFilePath); // Output to an assembly file
        System.out.println("Compilation complete, assembly code generated.");
    }

}
