package uk.ac.derby.unimail.jattfield1.classy.compiler;

import uk.ac.derby.unimail.jattfield1.classy.lang.primitive.PrimitiveValue;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Instruction implements Serializable {
    private static final long serialVersionUID = 1L;

    public final String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public String toString() {
        return getName();
    }

    public abstract void evaluate(Scope scope);

    protected void popPopPush(Scope scope, String methodName) {
        try {
            Method method = PrimitiveValue.class.getMethod(methodName, PrimitiveValue.class);
            scope.valueStack.push((PrimitiveValue) method.invoke(scope.valueStack.pop(), scope.valueStack.pop()));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new Error("Invalid operation: " + methodName);
        }
    }
}
