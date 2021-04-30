package uk.ac.derby.unimail.jattfield1.classy.lang.primitive;


import uk.ac.derby.unimail.jattfield1.classy.lang.identity.Class;
import uk.ac.derby.unimail.jattfield1.classy.lang.identity.Function;

import java.util.HashMap;

public abstract class AbstractPrimitiveValue extends Class {

    public AbstractPrimitiveValue(String name) {
        super(name);
    }

    public boolean equals(Object o){
        if (o instanceof PrimitiveValue)
            return this.equalTo((PrimitiveValue) o).toBool();
        return false;
    }
}
