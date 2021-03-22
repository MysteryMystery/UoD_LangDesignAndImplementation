package uk.ac.derby.unimail.jattfield1.foop.lang.primitive;

import uk.ac.derby.unimail.jattfield1.foop.lang.identity.Function;

import java.util.HashMap;
import java.util.Set;

public abstract class AbstractPrimitiveValue implements PrimitiveValue {
    private final HashMap<String, Function> methods = new HashMap<>();

    protected void putMethod(String methodName, Function function){
        this.methods.put(methodName, function);
    }

    public boolean equals(Object o){
        if (o instanceof PrimitiveValue)
            return this.equalTo((PrimitiveValue) o).toBool();
        return false;
    }
}
