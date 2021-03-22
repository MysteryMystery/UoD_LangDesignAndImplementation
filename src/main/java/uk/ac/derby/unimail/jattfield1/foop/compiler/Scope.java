package uk.ac.derby.unimail.jattfield1.foop.compiler;

import uk.ac.derby.unimail.jattfield1.foop.lang.identity.NamedIdentity;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;

import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

public class Scope {
    private final HashMap<String, NamedIdentity> identities = new HashMap<>();
    public final Stack<Instruction> instructionStack = new Stack<>();
    public final Stack<PrimitiveValue> valueStack = new Stack<>();
    public Scope parent = null;

    public Scope(){

    }

    public Scope(Scope parent){
        this.parent = parent;
    }

    public void registerIdentity(NamedIdentity identity){
        identities.put(identity.getName(), identity);
    }

    public void registerIdentity(Set<NamedIdentity> identities){
        identities.forEach(this::registerIdentity);
    }

    public NamedIdentity getIdentity(NamedIdentity identity){
        return getIdentity(identity.getName());
    }

    public NamedIdentity getIdentity(String key){
        Scope s = this;

        //bubble up from local to wider to wider to global
        while(s != null){
            if (s.getIdentities().containsKey(key))
                return s.getIdentities().get(key);
            s = s.getParent();
        }
        return null;
    }

    public boolean hasIdentity(String key){
        return identities.containsKey(key);
    }

    public HashMap<String, NamedIdentity> getIdentities() {
        return identities;
    }

    public Scope compileInstruction(Instruction instruction){
        this.instructionStack.push(instruction);
        return this;
    }

    // Results will be stored on the stack!
    public void executeInstructions(){
        //instructionStack.forEach(i -> i.evaluate(this));
        while(instructionStack.size() > 0){
            instructionStack.pop().evaluate(this);
        }
    }

    public Scope getParent() {
        return parent;
    }

}
