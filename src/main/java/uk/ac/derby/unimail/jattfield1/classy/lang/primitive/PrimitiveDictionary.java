package uk.ac.derby.unimail.jattfield1.classy.lang.primitive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class PrimitiveDictionary extends AbstractPrimitiveValue{
    private final HashMap<PrimitiveValue, PrimitiveValue> boxed;

    public PrimitiveDictionary(HashMap<PrimitiveValue, PrimitiveValue> toBox) {
        super("Dict");
        boxed = toBox;
    }

    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        other = unpackVariable(other);
        if (other instanceof PrimitiveDictionary){
            boxed.putAll(other.toMap());
            return new PrimitiveDictionary(boxed);
        } else if (other instanceof PrimitiveCollection){
            ArrayList<PrimitiveValue> values = other.toCollection();
            if (values.size() % 2 == 1)
                values.add(new PrimitiveNull());

            for(int i = 0; i < values.size(); i += 2){
                boxed.put(values.get(i), values.get(i+1));
            }
            return new PrimitiveDictionary(boxed);
        }
        throw new RuntimeException("Invalid concatenation: " + getType() + " + " + other.getType());
    }

    @Override
    public PrimitiveValue subtract(PrimitiveValue other) {
        other = unpackVariable(other);
        if (other instanceof PrimitiveCollection){
            for(PrimitiveValue v : other.toCollection())
                boxed.remove(v);
        } else
            boxed.remove(other);
        return new PrimitiveDictionary(boxed);
    }

    @Override
    public PrimitiveValue getElement(PrimitiveValue index) {
        return boxed.get(unpackVariable(index));
    }

    @Override
    public PrimitiveValue setElement(PrimitiveValue index, PrimitiveValue element) {
        boxed.put(unpackVariable(index), unpackVariable(element));
        return this;
    }

    @Override
    public String toString() {
        return "{" + boxed.entrySet().stream().map(es -> es.getKey().toString() + ": " + es.getValue().toString()).collect(Collectors.joining(", ")) + "}";
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
       return (ArrayList<PrimitiveValue>) boxed.values();
    }

    @Override
    public HashMap<PrimitiveValue, PrimitiveValue> toMap(){
        return boxed;
    }

    @Override
    public int hashCode() {
        return boxed.hashCode();
    }

    @Override
    protected boolean equalsCheck(PrimitiveValue other) {
        return boxed.equals(other.toMap());
    }
}
