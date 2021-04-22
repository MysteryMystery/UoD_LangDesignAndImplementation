package uk.ac.derby.unimail.jattfield1.classy.lang.primitive;

import java.util.ArrayList;

public class PrimitiveFloat extends AbstractPrimitiveValue{
    private final float boxed;

    public PrimitiveFloat(float toBox){
        super("Float");
        boxed = toBox;
    }

    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        return new PrimitiveFloat(other.toFloat() + boxed);
    }

    @Override
    public PrimitiveValue multiply(PrimitiveValue other) {
        return new PrimitiveFloat(other.toFloat() * boxed);
    }

    @Override
    public PrimitiveValue divide(PrimitiveValue other) {
        return new PrimitiveFloat(other.toFloat() / boxed);
    }

    @Override
    public PrimitiveValue subtract(PrimitiveValue other) {
        return new PrimitiveFloat(other.toFloat() - boxed);
    }

    @Override
    public PrimitiveValue unaryPlus() {
        throw new UnsupportedOperationException("Unary Plus not implemented yet.");
    }

    @Override
    public PrimitiveValue unarySubtract() {
        throw new UnsupportedOperationException("Unary Subtract not implemented yet.");
    }

    @Override
    public PrimitiveValue equalTo(PrimitiveValue other) {
        return null;
    }

    @Override
    public PrimitiveValue notEqualTo(PrimitiveValue other) {
        return new PrimitiveBool(boxed != other.toFloat());
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
        return new PrimitiveFloat(boxed * -1);
    }

    @Override
    public int toInt() {
        return (int) boxed;
    }

    @Override
    public double toDouble() {
        return boxed;
    }

    @Override
    public float toFloat() {
        return boxed;
    }

    @Override
    public boolean toBool() {
        return boxed != 0;
    }

    @Override
    public String toString() {
        return String.valueOf(boxed);
    }


    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        ArrayList<PrimitiveValue> x = new ArrayList<>();
        x.add(this);
        return x;
    }
}
