package uk.ac.derby.unimail.jattfield1.foop.lang.primitive;

import java.util.ArrayList;

public class PrimitiveString extends AbstractPrimitiveValue{
    private String boxed;

    public PrimitiveString(String toBox){
        boxed = toBox;
    }

    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        return new PrimitiveString(boxed + other.toString());
    }

    @Override
    public PrimitiveValue multiply(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue divide(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue subtract(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue unaryPlus() {
        return null;
    }

    @Override
    public PrimitiveValue unarySubtract() {
        return null;
    }

    @Override
    public PrimitiveValue equalTo(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue notEqualTo(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue greaterThan(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue lessThan(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue greaterThanEqualTo(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue lessThanEqualTo(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue or(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue and(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue not() {
        return null;
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
    public String toString() {
        return boxed;
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        return null;
    }
}
