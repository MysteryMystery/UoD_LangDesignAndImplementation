package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;

import java.util.ArrayList;

public abstract class NamedIdentity implements PrimitiveValue {
    private final String name;

    public NamedIdentity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract PrimitiveValue getResult();

    @Override
    public String toString() {
        return getName();
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
    public <T> T to(Class<T> type) {
        return getResult().to(type);
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        return getResult().toCollection();
    }
}
