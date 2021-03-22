package uk.ac.derby.unimail.jattfield1.foop.lang.primitive;

import uk.ac.derby.unimail.jattfield1.foop.lang.identity.Function;
import uk.ac.derby.unimail.jattfield1.foop.lang.identity.FunctionBody;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PrimitiveCollection extends AbstractPrimitiveValue implements PrimitiveValue{
    private final ArrayList<PrimitiveValue> boxed;

    public PrimitiveCollection(ArrayList<PrimitiveValue> toBox){
        boxed = toBox;
    }

    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        if (!boxed.add(other))
            return null;
        return new PrimitiveCollection(boxed);
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
        boxed.remove(other);
        return new PrimitiveCollection(boxed);
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
        if (other instanceof PrimitiveCollection)
            return new PrimitiveBool(other.toCollection().equals(boxed));
        return new PrimitiveBool(false);
    }

    @Override
    public PrimitiveValue notEqualTo(PrimitiveValue other) {
        return new PrimitiveBool(!equalTo(other).toBool());
    }

    @Override
    public PrimitiveValue greaterThan(PrimitiveValue other) {
        return new PrimitiveBool(other.toCollection().size() > boxed.size());
    }

    @Override
    public PrimitiveValue lessThan(PrimitiveValue other) {
        return new PrimitiveBool(other.toCollection().size() < boxed.size());
    }

    @Override
    public PrimitiveValue greaterThanEqualTo(PrimitiveValue other) {
        return new PrimitiveBool(other.toCollection().size() >= boxed.size());
    }

    @Override
    public PrimitiveValue lessThanEqualTo(PrimitiveValue other) {
        return new PrimitiveBool(other.toCollection().size() <= boxed.size());
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
    public String toString() {
        return "[" + boxed.stream().map(PrimitiveValue::toString).collect(Collectors.joining(", ")) + "]";
    }

    @Override
    public int toInt() {
        return boxed.size();
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
        return false;
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        return boxed;
    }

    @Override
    public <T> T to(Class<T> type) {
        return null;
    }

    public PrimitiveCollection each(FunctionBody functionBody){
        boxed.forEach(functionBody::execute);
        return this;
    }
}
