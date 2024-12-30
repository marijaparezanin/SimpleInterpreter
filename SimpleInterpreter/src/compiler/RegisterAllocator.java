package compiler;

import java.util.HashMap;
import java.util.Map;

public class RegisterAllocator {
    private final String[] registers = {"eax", "ebx", "ecx", "edx"}; // Available registers
    private final Map<String, String> variableToRegister = new HashMap<>(); // Variable <-> Register mapping
    private final Map<String, String> registerToVariable = new HashMap<>();
    private int lastUsedRegister = -1;
    private boolean firstPassFinished = false;
    private String[] freedRegisterValue = null;

    public Map<String, String> getVariableToRegister() {
        return variableToRegister;
    }

    public String allocateRegister(String variable) {
        if (variableToRegister.containsKey(variable)) {
            return variableToRegister.get(variable);
        }


        if(lastUsedRegister == registers.length - 1) {
            firstPassFinished = true;
        }
        lastUsedRegister = (++lastUsedRegister) % registers.length;
        if(firstPassFinished) {
            spillRegister();
        }

        variableToRegister.put(variable, registers[lastUsedRegister]);
        registerToVariable.put(registers[lastUsedRegister], variable);

        return registers[lastUsedRegister];
    }

    private void spillRegister() {
        String variableToSpill = registerToVariable.get(registers[lastUsedRegister]);
        String registerToFree = registers[lastUsedRegister];
        freedRegisterValue = new String[]{variableToSpill, registerToFree};
        variableToRegister.remove(variableToSpill);
    }

    public void FreedRegister(){
        this.freedRegisterValue = null;
    }

    public boolean isRegisterAllocated(String variable) {
        return variableToRegister.containsKey(variable);
    }

    public String getRegister(String variable) {
        return variableToRegister.get(variable);
    }

    public String[] getFreedRegisterValue() {
        return this.freedRegisterValue;
    }


    public String getNextRegister(){
        return registers[(lastUsedRegister + 1) % registers.length];
    }
}
