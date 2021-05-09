package uk.ac.derby.unimail.jattfield1.classy.lang.identity;


import uk.ac.derby.unimail.jattfield1.classy.lang.ExecutionContext;
import uk.ac.derby.unimail.jattfield1.classy.lang.primitive.PrimitiveValue;

public class IdentityElement extends NamedIdentity {
    private final NamedIdentity theCollection;
    private final PrimitiveValue index;

    public IdentityElement(NamedIdentity possessor, PrimitiveValue index) {
        super("");
        this.theCollection = possessor;
        this.index = index;
    }

    @Override
    public void setData(PrimitiveValue value) {
        setElement(index, value);
    }

    @Override
    public PrimitiveValue getResult() {
        return theCollection.getElement(index);
    }

    @Override
    public PrimitiveValue getNthElement(int n) {
        return theCollection.getNthElement(n);
    }

    @Override
    public PrimitiveValue setNthElement(int n, PrimitiveValue element) {
        return theCollection.setNthElement(n, element);
    }

    @Override
    public PrimitiveValue getElement(PrimitiveValue index) {
        return getResult().getElement(index);
    }

    @Override
    public PrimitiveValue setElement(PrimitiveValue index, PrimitiveValue element) {
        return getResult().setElement(index, element);
    }

    @Override
    public void saveToScope(ExecutionContext scope){
        scope.putNamedIdentity(theCollection);
    }
}
