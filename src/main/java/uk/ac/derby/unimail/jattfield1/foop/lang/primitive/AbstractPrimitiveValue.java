package uk.ac.derby.unimail.jattfield1.foop.lang.primitive;

import uk.ac.derby.unimail.jattfield1.foop.lang.identity.Function;

import java.util.HashMap;
import java.util.Set;

public abstract class AbstractPrimitiveValue implements PrimitiveValue {
    private final HashMap<String, Function> methods = new HashMap<>();

    public PrimitiveValue callMethod(String methodName, Set<PrimitiveValue> arguments) throws Exception {
        if (methods.containsKey(methodName))
            return methods.get(methodName).getResult(arguments);
        throw new Exception("Method does not exist");
    }

    protected void putMethod(String methodName, Function function){
        this.methods.put(methodName, function);
    }
}
