package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;

public abstract class NamedIdentity {
    private final String name;

    public NamedIdentity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract PrimitiveValue getResult();

    @Override
    public String toString() {
        return getName();
    }
}
