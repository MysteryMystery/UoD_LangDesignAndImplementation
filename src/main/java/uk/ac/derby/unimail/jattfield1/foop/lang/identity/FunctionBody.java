package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;

import java.util.Set;

public interface FunctionBody {
    PrimitiveValue execute(Object arguments);
}
