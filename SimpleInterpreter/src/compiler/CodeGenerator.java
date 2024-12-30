package compiler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class CodeGenerator {
    private RegisterAllocator registerAllocator;
    private StringBuilder code;
    private HashMap<String, Integer> variables = new HashMap<>();

    public CodeGenerator() {
        this.code = new StringBuilder();
        this.registerAllocator = new RegisterAllocator();
    }

    public boolean declareVariable(String variableName, Integer value) {
        if(!variables.containsKey(variableName)){
            variables.put(variableName, value);
            return true;
        }
        return false; //Variable already declared
    }

    private String getDeclarations() {
        StringBuilder declarations = new StringBuilder();
        String dataDeclarationHeader = "section .data\n";
        declarations.append(dataDeclarationHeader);
        for (String variable : variables.keySet()) {
            declarations.append(variable).append(" dd ").append(variables.get(variable)).append("\n");
        }
        return declarations.toString();
    }

    // Emit instructions into the generated code
    public void emit(String instruction) {
        code.append(instruction).append("\n");
    }

    // Get the generated assembly code as a string
    public String getGeneratedCode() {
        String programStart = "section .text\n" +
                "global _start\n" +
                "_start:\n";
        String programExit = "mov rax, 1\nxor rbx, rbx\nint 0x80\n";
        saveRegister();
        return getDeclarations() + programStart + code.toString() + programExit;
    }


    public void saveRegister(){
        for(String variable : registerAllocator.getVariableToRegister().keySet()){
            String register = registerAllocator.getVariableToRegister().get(variable);
            emit("mov dword [" + variable + "], " + register);
        }
    }

    // Allocate a new register (simplified example)
    public String allocateRegister(String variable) {
        String register = registerAllocator.allocateRegister(variable);
        FreeOverbookedRegister();   //in case allocating a register pushes out another variable
        return register;
    }

    public String[] getFreedRegisterValue(){
        return registerAllocator.getFreedRegisterValue();
    }

    //in case allocating a register pushes out another variable
    public void FreeOverbookedRegister(){
        if(registerAllocator.getFreedRegisterValue() != null){
            String variableToSpill = getFreedRegisterValue()[0];
            String registerToFree = getFreedRegisterValue()[1];
            emit(";" + registerToFree + " is being taken up, so the value is saved in memory");
            emit("mov dword [" + variableToSpill + "], " + registerToFree);
            registerAllocator.FreedRegister();
        }
        registerAllocator.FreedRegister();
    }



    public void generateCodeForAssignment(String variable, String value) {
        try {
            Integer literalValue = Integer.parseInt(value);
            declareVariable(variable, literalValue);

            //if the variable has already been given a register
            if(registerAllocator.isRegisterAllocated(variable)){
                String register = registerAllocator.getRegister(variable);
                emit("; Updating the new value of " + variable + " for their register");
                emit("mov " + register + ", " + literalValue);
            } else {
                String register = allocateRegister(variable);
                emit("; Allocating a "+ register + " for  " + variable);
                emit("mov " + register + ", " + literalValue);
            }
            //the value is placed in memory when it looses a register or in the end
        } catch (NumberFormatException e) {
            //do I need to fetch the value from memory
            if(registerAllocator.isRegisterAllocated(value)){
                String valueRegister = registerAllocator.getRegister(value);

                if(registerAllocator.isRegisterAllocated(variable)){
                    String variableRegister = registerAllocator.getRegister(variable);
                    emit("mov " + variableRegister + ", " + valueRegister);
                }else{
                    emit("mov dword [" + variable + "], " + valueRegister);
                }
            } else {
                //fetch variable holding the value from memory, it doesn't have their designated register
                String valueRegister = allocateRegister(value);
                emit("mov " + valueRegister + ", [" + value + "]"); // Move value of the variable to register

                if(registerAllocator.isRegisterAllocated(variable)) {
                    String variableRegister = registerAllocator.getRegister(variable);
                    emit("mov " + variableRegister + ", " + valueRegister);
                }else{
                    String variableRegister = allocateRegister(variable); // Allocate a register for the variable
                    emit("; Allocating " + variableRegister +"  register for  " + variable);
                    emit("mov " + variableRegister + ", " + valueRegister);
                }

                //the value is placed in memory when it looses a register or in the end
            }

        }
    }


    // Write the generated code to a file
    public void writeToFile(String filePath) {
        try {
            Files.write(Paths.get(filePath), getGeneratedCode().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
