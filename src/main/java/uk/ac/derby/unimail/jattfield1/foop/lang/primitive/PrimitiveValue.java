package uk.ac.derby.unimail.jattfield1.foop.lang.primitive;

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
    PrimitiveValue greaterThan (PrimitiveValue other);
    PrimitiveValue lessThan (PrimitiveValue other);
    PrimitiveValue greaterThanEqualTo (PrimitiveValue other);
    PrimitiveValue lessThanEqualTo (PrimitiveValue other);
    PrimitiveValue or (PrimitiveValue other);
    PrimitiveValue and (PrimitiveValue other);
    PrimitiveValue not ();

    // Casts
    String toString();
    int toInt();
    double toDouble();
    float toFloat();
    boolean toBool();
    <T> T to(Class<T> type);
}
