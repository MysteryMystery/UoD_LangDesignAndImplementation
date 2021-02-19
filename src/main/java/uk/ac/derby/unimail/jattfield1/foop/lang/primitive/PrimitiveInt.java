package uk.ac.derby.unimail.jattfield1.foop.lang.primitive;


public class PrimitiveInt extends AbstractPrimitiveValue {
    private final int boxed;

    public PrimitiveInt(int toBox){
        this.boxed = toBox;
    }

    /**
     * If is int, return int - if is other dt, fall back on java and allow java to convert the datatype
     *
     * @param other
     * @return PrimitiveValue
     */
    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        if (other instanceof PrimitiveInt)
            return new PrimitiveInt(boxed + other.toInt());
        return other.add(this);
    }

    @Override
    public PrimitiveValue multiply(PrimitiveValue other) {
        if (other instanceof PrimitiveInt)
            return new PrimitiveInt(boxed * other.toInt());
        return other.multiply(this);
    }

    @Override
    public PrimitiveValue divide(PrimitiveValue other) {
        if (other instanceof PrimitiveInt)
            return new PrimitiveInt(boxed / other.toInt());
        return other.divide(this);
    }

    @Override
    public PrimitiveValue subtract(PrimitiveValue other) {
        if (other instanceof PrimitiveInt)
            return new PrimitiveInt(boxed - other.toInt());
        return other.subtract(this);
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
        return new PrimitiveBool(other.toInt() == boxed);
    }

    @Override
    public PrimitiveValue greaterThan(PrimitiveValue other) {
        return new PrimitiveBool(other.toInt() < boxed);
    }

    @Override
    public PrimitiveValue lessThan(PrimitiveValue other) {
        return new PrimitiveBool(other.toInt() > boxed);
    }

    @Override
    public PrimitiveValue greaterThanEqualTo(PrimitiveValue other) {
        return new PrimitiveBool(other.toInt() <= boxed);
    }

    @Override
    public PrimitiveValue lessThanEqualTo(PrimitiveValue other) {
        return new PrimitiveBool(other.toInt() >= boxed);
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
        return new PrimitiveInt(boxed * -1);
    }

    @Override
    public int toInt() {
        return boxed;
    }

    @Override
    public double toDouble() {
        return (double) boxed;
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
