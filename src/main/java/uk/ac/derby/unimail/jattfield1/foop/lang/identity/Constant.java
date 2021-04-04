package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;

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
        if (this.data != null)
            throw new RuntimeException("Cannot change value of constant: " + getName());
        this.data = data;
    }

    @Override
    public PrimitiveValue getResult() {
        return data;
    }
}
