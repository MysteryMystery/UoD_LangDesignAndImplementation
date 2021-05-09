package uk.ac.derby.unimail.jattfield1.classy.lang.primitive;


import uk.ac.derby.unimail.jattfield1.classy.lang.Subroutine;
import uk.ac.derby.unimail.jattfield1.classy.lang.identity.Class;
import uk.ac.derby.unimail.jattfield1.classy.lang.identity.Function;
import uk.ac.derby.unimail.jattfield1.classy.lang.identity.NamedIdentity;

import java.util.Collections;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class AbstractPrimitiveValue extends Class {

    public AbstractPrimitiveValue(String name) {
        super(name);
    }

    public boolean equals(Object o){
        if (! (o.getClass().equals(this.getClass())))
            return false;
        return equalsCheck((PrimitiveValue) o);
    }

    protected abstract boolean equalsCheck(PrimitiveValue other);

    protected PrimitiveValue unpackVariable(PrimitiveValue primitiveValue){
        if (primitiveValue instanceof NamedIdentity)
            return ((NamedIdentity) primitiveValue).getResult();
        return primitiveValue;
    }
}
