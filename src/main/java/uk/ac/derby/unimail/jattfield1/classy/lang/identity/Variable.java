package uk.ac.derby.unimail.jattfield1.classy.lang.identity;

import uk.ac.derby.unimail.jattfield1.classy.lang.primitive.PrimitiveValue;

import java.util.ArrayList;

public class Variable extends NamedIdentity {
    /**
     * PrimitiveValue, Function etc
     */
    private PrimitiveValue data;

    public Variable(String name){
        super(name);
    }

    public Variable(String name, PrimitiveValue toBox) {
        super(name);
        data = toBox;
    }

    public void setData(PrimitiveValue data) {
        validateSet(data);
        this.data = data;
    }

    public PrimitiveValue getResult() {
        return data;
    }

    @Override
    public PrimitiveValue getNthElement(int n) {
        return data.getNthElement(n);
    }

    @Override
    public PrimitiveValue setNthElement(int n, PrimitiveValue element) {
        return data.setNthElement(n, element);
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        ArrayList<PrimitiveValue> x = new ArrayList<>();
        x.add(this);
        return x;
    }
}
