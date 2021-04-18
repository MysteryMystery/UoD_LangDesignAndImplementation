package uk.ac.derby.unimail.jattfield1.foop.lang.primitive;


import uk.ac.derby.unimail.jattfield1.foop.lang.identity.Class;
import uk.ac.derby.unimail.jattfield1.foop.lang.identity.Function;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public abstract class AbstractPrimitiveValue extends Class implements PrimitiveValue {

    private final HashMap<String, Function> methods = new HashMap<>();

    public AbstractPrimitiveValue(String name) {
        super(name);
    }

    protected void putMethod(String methodName, Function function){
        this.methods.put(methodName, function);
    }

    public boolean equals(Object o){
        if (o instanceof PrimitiveValue)
            return this.equalTo((PrimitiveValue) o).toBool();
        return false;
    }

    @Override
    public PrimitiveValue nthElement(int n) {
        throw new RuntimeException("Cannot index " + getType());
    }
}
