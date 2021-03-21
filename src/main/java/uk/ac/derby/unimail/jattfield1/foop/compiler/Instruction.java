package uk.ac.derby.unimail.jattfield1.foop.compiler;

public abstract class Instruction {

    public final String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return getName();
    }

    public abstract boolean validate(Scope scope);

    public abstract void evaluate(Scope scope);
}
