package uk.ac.derby.unimail.jattfield1.classy.lang.primitive;


import java.util.ArrayList;

public class PrimitiveString extends AbstractPrimitiveValue{
    private String boxed;

    public PrimitiveString(String toBox){
        super("String");
        boxed = toBox;
    }

    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        return new PrimitiveString(boxed + other.toString());
    }

    @Override
    public PrimitiveValue multiply(PrimitiveValue other) {
        if ((other = unpackVariable(other)) instanceof PrimitiveInt){
            String s = boxed;
            for(int i = 0; i < other.toInt(); i++){
                boxed += s;
            }
            return new PrimitiveString(boxed) ;
        }
        throw new RuntimeException("Cannot multiply string by " + other.getType());
    }

    @Override
    public PrimitiveValue getNthElement(int n) {
        return new PrimitiveString(String.valueOf(boxed.charAt(n)));
    }

    @Override
    public PrimitiveValue setNthElement(int n, PrimitiveValue element) {
        StringBuilder sb = new StringBuilder(boxed);
        sb.setCharAt(n, element.toString().charAt(0));
        boxed = sb.toString();
        return this;
    }

    @Override
    public PrimitiveValue getElement(PrimitiveValue index) {
        if (! (index instanceof PrimitiveInt))
            throw new RuntimeException("Indexing for " + getType() + " is integer only.");
        return new PrimitiveString(String.valueOf(boxed.charAt(index.toInt())));
    }

    @Override
    public PrimitiveValue setElement(PrimitiveValue index, PrimitiveValue element) {
        if (! (index instanceof PrimitiveInt))
            throw new RuntimeException("Indexing for " + getType() + " is integer only.");
        StringBuilder sb = new StringBuilder(boxed);
        sb.setCharAt(index.toInt(), element.toString().charAt(0));
        boxed = sb.toString();
        return this;
    }

    @Override
    public int toInt() {
        return Integer.parseInt(boxed);
    }

    @Override
    public double toDouble() {
        return Double.parseDouble(boxed);
    }

    @Override
    public float toFloat() {
        return Float.parseFloat(boxed);
    }

    @Override
    public String toString() {
        return boxed;
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        return null;
    }

    @Override
    public int hashCode() {
        return boxed.hashCode();
    }

    @Override
    protected boolean equalsCheck(PrimitiveValue other) {
        return boxed.equals(other.toString());
    }
}
