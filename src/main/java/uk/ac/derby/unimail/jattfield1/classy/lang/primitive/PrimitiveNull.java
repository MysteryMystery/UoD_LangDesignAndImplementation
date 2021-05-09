package uk.ac.derby.unimail.jattfield1.classy.lang.primitive;

public class PrimitiveNull extends AbstractPrimitiveValue{
    public PrimitiveNull() {
        super("Null");
    }

    @Override
    public int hashCode() {
        return 0; // as google says
    }

    @Override
    protected boolean equalsCheck(PrimitiveValue other) {
        return true;
    }
}
