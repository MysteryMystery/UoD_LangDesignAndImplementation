package uk.ac.derby.unimail.jattfield1.foop.compiler;

import java.util.Stack;

public class FoopCompiler {
    private final Scope globalScope = new Scope();

    public Scope getGlobalScope() {
        return globalScope;
    }

}
