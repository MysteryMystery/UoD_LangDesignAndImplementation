package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

import uk.ac.derby.unimail.jattfield1.foop.compiler.Scope;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Function {
    private ArrayList<NamedIdentity> params;
    public final Scope scope;
    private String name;

    public Function(String name, ArrayList<NamedIdentity> params, Scope scope) {
        this.name = name;
        this.params = params;
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Function: " + getName();
    }


    public String getName(){
        return name;
    }

}
