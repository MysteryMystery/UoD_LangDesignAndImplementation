package uk.ac.derby.unimail.jattfield1.classy.lang.identity;

import uk.ac.derby.unimail.jattfield1.classy.lang.ClassyParser;
import uk.ac.derby.unimail.jattfield1.classy.lang.ExecutionContext;
import uk.ac.derby.unimail.jattfield1.classy.lang.primitive.PrimitiveValue;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class Class implements PrimitiveValue, Cloneable {
    private ExecutionContext scope = new ExecutionContext();

    private final String type;

    public Class(String type){
        this.type = type;
    }

    public Class(Class cls){
        this(cls.getType());
        this.scope = cls.getScope();
    }

    private void throwNotDefinedMethodException(String method){
        throw new RuntimeException("Cannot " + method + " method not defined in " + getType());
    }

    private PrimitiveValue forward(String method, PrimitiveValue[] params){
        Function f = scope.getFunction(method);
        if (f != null){
            for (PrimitiveValue primitiveValue : params)
                f.setPositionalArg(primitiveValue);
            return (PrimitiveValue) f.execute(ClassyParser.getInstance());
        }
        throwNotDefinedMethodException(method);
        return null;
    }

    private PrimitiveValue forward(String method){
        return forward(method, new PrimitiveValue[0]);
    }

    public ExecutionContext getScope() {
        return scope;
    }

    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        return forward("add", new PrimitiveValue[] { other });
    }

    @Override
    public PrimitiveValue multiply(PrimitiveValue other) {
        return forward("multiply", new PrimitiveValue[] { other });
    }

    @Override
    public PrimitiveValue divide(PrimitiveValue other) {
        return forward("divide", new PrimitiveValue[] { other });
    }

    @Override
    public PrimitiveValue subtract(PrimitiveValue other) {
        return forward("subtract", new PrimitiveValue[] { other });
    }

    @Override
    public PrimitiveValue unaryPlus() {
        return forward("unaryPlus");
    }

    @Override
    public PrimitiveValue unarySubtract() {
        return forward("unarySubtract");
    }

    @Override
    public PrimitiveValue equalTo(PrimitiveValue other) {
        return forward("equalTo");
    }

    @Override
    public PrimitiveValue notEqualTo(PrimitiveValue other) {
        return forward("unaryPlus");
    }

    @Override
    public PrimitiveValue greaterThan(PrimitiveValue other) {
        return forward("greaterThan", new PrimitiveValue[] { other });
    }

    @Override
    public PrimitiveValue lessThan(PrimitiveValue other) {
        return forward("lessThan", new PrimitiveValue[] { other });
    }

    @Override
    public PrimitiveValue greaterThanEqualTo(PrimitiveValue other) {
        return forward("greaterThanEqualTo", new PrimitiveValue[] { other });
    }

    @Override
    public PrimitiveValue lessThanEqualTo(PrimitiveValue other) {
        return forward("lessThanEqualTo", new PrimitiveValue[] { other });
    }

    @Override
    public PrimitiveValue or(PrimitiveValue other) {
        return forward("or", new PrimitiveValue[] { other });
    }

    @Override
    public PrimitiveValue and(PrimitiveValue other) {
        return forward("and", new PrimitiveValue[] { other });
    }

    @Override
    public PrimitiveValue not() {
        return forward("not");
    }

    @Override
    public PrimitiveValue getNthElement(int n) {
        throw new RuntimeException("Cannot index " + getType());
    }

    @Override
    public PrimitiveValue setNthElement(int n, PrimitiveValue element) {
        throw new RuntimeException("Cannot index " + getType());
    }

    @Override
    public PrimitiveValue getElement(PrimitiveValue index) {
        throw new RuntimeException("Cannot index " + getType());
    }

    @Override
    public PrimitiveValue setElement(PrimitiveValue index, PrimitiveValue element) {
        throw new RuntimeException("Cannot index " + getType());
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int toInt() {
        throw new RuntimeException("Cannot cast to int on " + getType());
    }

    @Override
    public double toDouble() {
        throw new RuntimeException("Cannot cast to float on " + getType());
    }

    @Override
    public float toFloat() {
        throw new RuntimeException("Cannot cast to float on " + getType());
    }

    @Override
    public boolean toBool() {
        throw new RuntimeException("Cannot cast to bool on " + getType());
    }

    @Override
    public Vector<Vector<PrimitiveValue>> toMatrix() {
        throw new RuntimeException("Cannot cast to matrix on " + getType());
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        throw new RuntimeException("Cannot cast to collection on " + getType());
    }

    @Override
    public HashMap<PrimitiveValue, PrimitiveValue> toMap() {
        throw new RuntimeException("Cannot cast to dict on " + getType());
    }

    @Override
    public Class clone() {
        try {
            return (Class) super.clone();
        } catch (CloneNotSupportedException e){
            throw new RuntimeException("Underlying error: cloning not possible.");
        }
    }

    @Override
    public String toString() {
        return getType();
    }
}
