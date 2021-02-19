package uk.ac.derby.unimail.jattfield1.foop.lang.primitive;

public class PrimitiveBool extends AbstractPrimitiveValue{
    private final boolean boxed;

    public PrimitiveBool(boolean toBox){
        boxed = toBox;
    }

    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        return null;
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
        return boxed ? 1 : 0;
    }

    @Override
    public double toDouble() {
        return toInt();
    }

    @Override
    public float toFloat() {
        return toInt();
    }

    @Override
    public boolean toBool() {
        return boxed;
    }

    @Override
    public String toString() {
        return boxed ? "true" : "false";
    }

    @Override
    public <T> T to(Class<T> type) {
        return null;
    }
}
