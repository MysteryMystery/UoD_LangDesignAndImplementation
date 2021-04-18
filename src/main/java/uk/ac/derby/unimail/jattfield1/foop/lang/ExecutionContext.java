package uk.ac.derby.unimail.jattfield1.foop.lang;

import uk.ac.derby.unimail.jattfield1.foop.lang.identity.Function;
import uk.ac.derby.unimail.jattfield1.foop.lang.identity.NamedIdentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExecutionContext {
    private HashMap<String, NamedIdentity> variables = new HashMap<>();
    private HashMap<String, Function> functions = new HashMap<>();

    private List<ExecutionContext> children = new ArrayList<>();
    private ExecutionContext parent = null;

    public ExecutionContext(){

    }

    public ExecutionContext(ExecutionContext parent){
        this.parent = parent;
    }

    public ExecutionContext getParent() {
        return parent;
    }

    public void putNamedIdentity(NamedIdentity namedIdentity){
        if (variables.containsKey(namedIdentity.getName())){
            // set rather than put new so that constant validation can be done over in that class and not here
            NamedIdentity v = variables.get(namedIdentity.getName());
            v.setData(namedIdentity.getResult());
        } else
            variables.put(namedIdentity.getName(), namedIdentity);
    }

    public void putFunction(Function function){
        if (functions.containsKey(function.getName()))
            throw new RuntimeException("Function " + function.getName() + " already exists.");
        functions.put(function.getName(), function);
    }

    public boolean hasNamedIdentity(String name){
        return this.variables.containsKey(name) || (this.parent != null && this.parent.hasNamedIdentity(name));
    }

    public NamedIdentity getNamedIdentity(String name){
        if (!this.variables.containsKey(name)){
            if (parent == null)
                throw new RuntimeException("Variable does not exist");
            return parent.getNamedIdentity(name);
        }
        return variables.get(name);
    }

    public HashMap<String, NamedIdentity> getVariables() {
        return variables;
    }

    public Function getFunction(String name){
        if (!this.functions.containsKey(name)){
            if (parent == null)
                throw new RuntimeException("Function does not exist");
            return parent.getFunction(name);
        }
        return functions.get(name);
    }
}
