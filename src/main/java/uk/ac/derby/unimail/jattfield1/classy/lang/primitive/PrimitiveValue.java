package uk.ac.derby.unimail.jattfield1.classy.lang.primitive;

import java.util.ArrayList;
import java.util.HashMap;

public interface PrimitiveValue {
    // Arithmatic
    PrimitiveValue add (PrimitiveValue other);
    PrimitiveValue multiply (PrimitiveValue other);
    PrimitiveValue divide (PrimitiveValue other);
    PrimitiveValue subtract (PrimitiveValue other);

    PrimitiveValue unaryPlus ();
    PrimitiveValue unarySubtract ();

    // Bool logic
    PrimitiveValue equalTo (PrimitiveValue other);
    PrimitiveValue notEqualTo (PrimitiveValue other);
    PrimitiveValue greaterThan (PrimitiveValue other);
    PrimitiveValue lessThan (PrimitiveValue other);
    PrimitiveValue greaterThanEqualTo (PrimitiveValue other);
    PrimitiveValue lessThanEqualTo (PrimitiveValue other);
    PrimitiveValue or (PrimitiveValue other);
    PrimitiveValue and (PrimitiveValue other);
    PrimitiveValue not ();

    @Deprecated
    PrimitiveValue getNthElement(int n);
    @Deprecated
    PrimitiveValue setNthElement(int n, PrimitiveValue element);

    PrimitiveValue getElement(PrimitiveValue index);
    PrimitiveValue setElement(PrimitiveValue index, PrimitiveValue element);

    String getType();

    // Casts
    String toString();
    int toInt();
    double toDouble();
    float toFloat();
    boolean toBool();
    ArrayList<PrimitiveValue> toCollection();
    HashMap<PrimitiveValue, PrimitiveValue> toMap();
}
