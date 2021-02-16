package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

public class Variable extends NamedIdentity{
    /**
     * PrimitiveValue, Function etc
     */
    private Object data;

    public Variable(String name) {
        super(name);
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
