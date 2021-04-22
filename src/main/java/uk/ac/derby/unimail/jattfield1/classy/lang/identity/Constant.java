package uk.ac.derby.unimail.jattfield1.classy.lang.identity;

import uk.ac.derby.unimail.jattfield1.classy.lang.primitive.PrimitiveValue;

public class Constant extends NamedIdentity{

    private PrimitiveValue data;

    public Constant(String name) {
        super(name);
    }

    public Constant(String name, PrimitiveValue toBox) {
        super(name);
        data = toBox;
    }

    public void setData(PrimitiveValue data) {
        validateSet(data);
        this.data = data;
    }

    @Override
    protected void validateSet(PrimitiveValue data) {
        if (this.data != null)
            throw new RuntimeException("Cannot change value of constant: " + getName());
        super.validateSet(data);
    }

    @Override
    public PrimitiveValue getResult() {
        return data;
    }

    @Override
    public PrimitiveValue getNthElement(int n) {
        return data.getNthElement(n);
    }

    @Override
    public PrimitiveValue setNthElement(int n, PrimitiveValue element) {
        throw new RuntimeException("Cannot change value of constant: " + getName());
    }
}
