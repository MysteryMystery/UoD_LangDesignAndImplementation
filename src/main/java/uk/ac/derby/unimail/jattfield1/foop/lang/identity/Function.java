package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

import uk.ac.derby.unimail.jattfield1.foop.compiler.Scope;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Function extends NamedIdentity{
    private int numberOfParams;
    private Scope scope;

    public Function(String name, int numberOfParams, Scope scope) {
        super(name);
        this.numberOfParams = numberOfParams;
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Function: " + getName();
    }

    @Override
    public PrimitiveValue getResult() {
        scope.executeInstructions();
        return scope.valueStack.pop();
    }

    @Override
    public int toInt() {
        return 0;
    }

    @Override
    public double toDouble() {
        return 0;
    }

    @Override
    public float toFloat() {
        return 0;
    }

    @Override
    public boolean toBool() {
        return false;
    }

    @Override
    public <T> T to(Class<T> type) {
        return null;
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        return null;
    }
}
