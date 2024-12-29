package compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CodeGenerator {
    private StringBuilder code;
    private int registerCounter;

    public CodeGenerator() {
        this.code = new StringBuilder();
        this.registerCounter = 0;
    }

    // Emit instructions into the generated code
    public void emit(String instruction) {
        code.append(instruction).append("\n");
    }

    // Get the generated assembly code as a string
    public String getGeneratedCode() {
        return code.toString();
    }

    // Allocate a new register (simplified example)
    public String allocateRegister() {
        return "r" + registerCounter++; // Allocate a new register (simplified example)
    }

    // Write the generated code to a file
    public void writeToFile(String filePath) {
        try {
            Files.write(Paths.get(filePath), code.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to generate assembly for printing
    public void generatePrintCode(String message) {
        // Print the value using WriteConsoleA
        // This requires setting up the console output handle, the message, and the length of the string

        // Declare the string in the .data section
        emit("section .data");
        emit("msg db '" + message + "', 0"); // Hardcoded message for simplicity

        // Declare external functions in the .text section
        emit("section .text");
        emit("extern GetStdHandle");
        emit("extern WriteConsoleA");

        // Start of the program
        emit("global _start");
        emit("_start:");

        // Get STD_OUTPUT_HANDLE (console handle)
        emit("mov rdi, -11"); // STD_OUTPUT_HANDLE constant
        emit("call GetStdHandle");

        // Save handle to rdi (console handle)
        emit("mov rdi, rax"); // Save handle to rdi

        // Move the message pointer into rsi (parameter for WriteConsoleA)
        emit("lea rsi, [msg]");

        // Set number of characters to write
        emit("mov rdx, " + message.length()); // Length of the message

        // Call WriteConsoleA to print the message
        emit("call WriteConsoleA");

        // Exit the program
        emit("mov rax, 0x60"); // Exit syscall number
        emit("xor rdi, rdi");   // Return code 0
        emit("syscall");        // Trigger syscall to exit
    }
}
