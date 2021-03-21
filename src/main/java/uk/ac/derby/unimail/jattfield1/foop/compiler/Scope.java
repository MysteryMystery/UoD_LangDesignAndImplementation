package uk.ac.derby.unimail.jattfield1.foop.compiler;

import uk.ac.derby.unimail.jattfield1.foop.lang.identity.NamedIdentity;

import java.util.HashMap;
import java.util.Stack;
import java.util.stream.Collectors;

public class Scope {
    private final HashMap<String, NamedIdentity> identities = new HashMap<>();
    private final Stack<Instruction> instructionStack = new Stack<>();


    public void registerIdentity(NamedIdentity identity){
        identities.put(identity.getName(), identity);
    }

    public <T extends NamedIdentity> T getIdentity(NamedIdentity identity){
        return getIdentity(identity.getName());
    }

    public <T extends NamedIdentity> T getIdentity(String key){
        if (identities.containsKey(key))
            return (T) identities.get(key);
        return null;
    }

    public boolean hasIdentity(String key){
        return identities.containsKey(key);
    }

    public HashMap<String, NamedIdentity> getIdentities() {
        return identities;
    }

    public Scope compileInstruction(Instruction instruction){
        return this;
    }

    public void executeInstructions(){
        instructionStack.forEach(i -> i.evaluate(this));
    }
}
