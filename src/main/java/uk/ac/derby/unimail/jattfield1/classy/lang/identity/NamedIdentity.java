package uk.ac.derby.unimail.jattfield1.classy.lang.identity;

import uk.ac.derby.unimail.jattfield1.classy.lang.ExecutionContext;
import uk.ac.derby.unimail.jattfield1.classy.lang.primitive.PrimitiveValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public abstract class NamedIdentity implements PrimitiveValue {
    private final String name;
    private String type;

    public NamedIdentity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void setData(PrimitiveValue value);

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract PrimitiveValue getResult();

    @Override
    public String toString() {
        return getResult().toString();
    }

    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        return getResult().add(other);
    }

    @Override
    public PrimitiveValue multiply(PrimitiveValue other) {
        return getResult().multiply(other);
    }

    @Override
    public PrimitiveValue divide(PrimitiveValue other) {
        return getResult().divide(other);
    }

    @Override
    public PrimitiveValue subtract(PrimitiveValue other) {
        return getResult().subtract(other);
    }

    @Override
    public PrimitiveValue unaryPlus() {
        return getResult().unaryPlus();
    }

    @Override
    public PrimitiveValue unarySubtract() {
        return getResult().unarySubtract();
    }

    @Override
    public PrimitiveValue equalTo(PrimitiveValue other) {
        return getResult().equalTo(other);
    }

    @Override
    public PrimitiveValue greaterThan(PrimitiveValue other) {
        return getResult().greaterThan(other);
    }

    @Override
    public PrimitiveValue lessThan(PrimitiveValue other) {
        return getResult().lessThan(other);
    }

    @Override
    public PrimitiveValue greaterThanEqualTo(PrimitiveValue other) {
        return getResult().greaterThanEqualTo(other);
    }

    @Override
    public PrimitiveValue lessThanEqualTo(PrimitiveValue other) {
        return getResult().lessThanEqualTo(other);
    }

    @Override
    public PrimitiveValue or(PrimitiveValue other) {
        return getResult().or(other);
    }

    @Override
    public PrimitiveValue and(PrimitiveValue other) {
        return getResult().and(other);
    }

    @Override
    public PrimitiveValue not() {
        return getResult().not();
    }

    @Override
    public PrimitiveValue notEqualTo(PrimitiveValue other) {
        return getResult().notEqualTo(other);
    }

    @Override
    public int toInt() {
        return getResult().toInt();
    }

    @Override
    public double toDouble() {
        return getResult().toDouble();
    }

    @Override
    public float toFloat() {
        return getResult().toFloat();
    }

    @Override
    public boolean toBool() {
        return getResult().toBool();
    }

    @Override
    public Vector<Vector<PrimitiveValue>> toMatrix() {
        return getResult().toMatrix();
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        return getResult().toCollection();
    }

    @Override
    public HashMap<PrimitiveValue, PrimitiveValue> toMap() {
        return getResult().toMap();
    }

    public static PrimitiveValue valueOf(PrimitiveValue primitiveValue){
        // cannot overload this due to inheritance
        if (primitiveValue instanceof Constant)
            return ((Constant) primitiveValue).getResult();
        if (primitiveValue instanceof Variable)
            return ((Variable) primitiveValue).getResult();
        return primitiveValue;
    }

    protected void validateSet(PrimitiveValue data){
        if (!this.getType().equals(data.getType()))
            throw new RuntimeException("Incorrect datatype for " + getName() + ", expecting " + getType() + " but got " + data.getType());
    }

    public void saveToScope(ExecutionContext scope){
        scope.putNamedIdentity(this);
    }

    @Override
    public PrimitiveValue getNthElement(int n) {
        return getResult().getNthElement(n);
    }

    @Override
    public PrimitiveValue setNthElement(int n, PrimitiveValue element) {
        return getResult().setNthElement(n, element);
    }

    @Override
    public PrimitiveValue getElement(PrimitiveValue index) {
        return getResult().getElement(index);
    }

    @Override
    public PrimitiveValue setElement(PrimitiveValue index, PrimitiveValue element) {
        return getResult().setElement(index, element);
    }
}
