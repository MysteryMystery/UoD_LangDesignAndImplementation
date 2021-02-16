package uk.ac.derby.unimail.jattfield1.foop.lang.primitive;

public class PrimitiveFloat extends AbstractPrimitiveValue{
    private final float boxed;

    public PrimitiveFloat(float toBox){
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
    public <T> T to(Class<T> type) {
        return null;
    }
}
