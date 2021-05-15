package uk.ac.derby.unimail.jattfield1.classy.lang.primitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class PrimitiveCollection extends AbstractPrimitiveValue {
    private final ArrayList<PrimitiveValue> boxed;

    public PrimitiveCollection(ArrayList<PrimitiveValue> toBox){
        super("Collection");
        boxed = toBox;
    }

    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        if (!boxed.add(other))
            return null;
        return new PrimitiveCollection(boxed);
    }

    @Override
    public PrimitiveValue subtract(PrimitiveValue other) {
        boxed.remove(other);
        return new PrimitiveCollection(boxed);
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
    public PrimitiveValue getNthElement(int n) {
        return boxed.get(n);
    }

    @Override
    public PrimitiveValue setNthElement(int n, PrimitiveValue element) {
        boxed.set(n, element);
        return new PrimitiveCollection(boxed);
    }

    @Override
    public PrimitiveValue getElement(PrimitiveValue index) {
        index = unpackVariable(index);
        if (! (index instanceof PrimitiveInt))
            throw new RuntimeException("Indexing for " + getType() + " is integer only.");
        return boxed.get(index.toInt());
    }

    @Override
    public PrimitiveValue setElement(PrimitiveValue index, PrimitiveValue element) {
        if (! (index instanceof PrimitiveInt))
            throw new RuntimeException("Indexing for " + getType() + " is integer only.");
        if (boxed.size() < index.toInt()){
            for (int i = 0; i < index.toInt() - boxed.size(); i++)
                boxed.add(new PrimitiveNull());
        }
        boxed.set(index.toInt(), element);
        return this;
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
    public HashMap<PrimitiveValue, PrimitiveValue> toMap(){
        if (boxed.size() % 2 == 1)
            throw new RuntimeException("Collection must have even number of elements for casting to dict: " + getType());

        HashMap<PrimitiveValue, PrimitiveValue> toReturn = new HashMap<>();
        for(int i = 0; i < boxed.size(); i+=2)
            toReturn.put(boxed.get(i), boxed.get(i+1));
        return toReturn;
    }

    @Override
    public int hashCode() {
        return boxed.hashCode();
    }

    @Override
    protected boolean equalsCheck(PrimitiveValue other) {
        return boxed.equals(other.toCollection());
    }
}
