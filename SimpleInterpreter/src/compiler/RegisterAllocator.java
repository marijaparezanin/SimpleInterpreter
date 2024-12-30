package compiler;

import java.util.HashMap;
import java.util.Map;

public class RegisterAllocator {
    private final String[] registers = {"rax", "rbx", "rcx", "rdx"}; // Available registers
    private final Map<String, String> variableToRegister = new HashMap<>(); // Variable <-> Register mapping
    private int lastUsedRegister = -1;
    private String[] freedRegisterValue = null;

    public Map<String, String> getVariableToRegister() {
        return variableToRegister;
    }

    public String allocateRegister(String variable) {
        if (variableToRegister.containsKey(variable)) {
            return variableToRegister.get(variable);
        }

        if(lastUsedRegister == registers.length - 1) {
            spillRegister();
        }
        lastUsedRegister = (++lastUsedRegister) % registers.length;
        variableToRegister.put(variable, registers[lastUsedRegister]);

        return registers[lastUsedRegister];
    }

    private void spillRegister() {
        String variableToSpill = variableToRegister.keySet().iterator().next();
        String registerToFree = variableToRegister.get(variableToSpill);
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


}
