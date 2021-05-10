package uk.ac.derby.unimail.jattfield1.classy.lang.primitive;

import java.util.ArrayList;
import java.util.Vector;
import java.util.stream.Collectors;

public class PrimitiveMatrix extends AbstractPrimitiveValue{
    private final Vector<Vector<PrimitiveValue>> boxed;

    public PrimitiveMatrix(Vector<Vector<PrimitiveValue>> v) {
        super("Matrix");
        boxed = v;
        validateConstruction();
    }

    private void validateConstruction(){
        if (boxed.size() == 0)
            return;

        int firstLen = boxed.get(0).size();
        // check all rows the same length
        for (Vector<PrimitiveValue> row : boxed){
            if (row.size() != firstLen)
                throw new RuntimeException("Matrix must consist of equal length rows");
        }
    }

    @Override
    public PrimitiveValue add(PrimitiveValue other) {
        other = unpackVariable(other);
        if (! (other instanceof PrimitiveMatrix))
            throw new RuntimeException("Can only add 2 Matrices.");
        Vector<Vector<PrimitiveValue>> otherMatrix = other.toMatrix();

        if (otherMatrix.size() == 0 && boxed.size() == 0)
            return new PrimitiveMatrix(new Vector<>());
        validateAdditionSubtraction(otherMatrix);

        Vector<Vector<PrimitiveValue>> newMatrix = new Vector<>();

        for(int i = 0; i < otherMatrix.size(); i++){
            Vector<PrimitiveValue> nested = new Vector<>();
            for(int j = 0; j < otherMatrix.get(i).size(); j++){
                nested.add(boxed.get(i).get(j).add(otherMatrix.get(i).get(j)));
            }
            newMatrix.add(nested);
        }
        return new PrimitiveMatrix(newMatrix);
    }

    @Override
    public PrimitiveValue subtract(PrimitiveValue other) {
        other = unpackVariable(other);
        if (! (other instanceof PrimitiveMatrix))
            throw new RuntimeException("Can only add 2 Matrices.");
        Vector<Vector<PrimitiveValue>> otherMatrix = other.toMatrix();

        if (otherMatrix.size() == 0 && boxed.size() == 0)
            return new PrimitiveMatrix(new Vector<>());
        validateAdditionSubtraction(otherMatrix);

        Vector<Vector<PrimitiveValue>> newMatrix = new Vector<>();

        for(int i = 0; i < otherMatrix.size(); i++){
            Vector<PrimitiveValue> nested = new Vector<>();
            for(int j = 0; j < otherMatrix.get(i).size(); j++){
                nested.add(boxed.get(i).get(j).subtract(otherMatrix.get(i).get(j)));
            }
            newMatrix.add(nested);
        }
        return new PrimitiveMatrix(newMatrix);
    }

    @Override
    public PrimitiveValue multiply(PrimitiveValue other) {
        other = unpackVariable(other);
        if (other instanceof PrimitiveInt || other instanceof PrimitiveFloat) {
            Vector<Vector<PrimitiveValue>> newVector = new Vector<>();
            for (int i = 0; i < boxed.size(); i++){
                Vector<PrimitiveValue> x = new Vector<>();
                for (int j = 0; j < boxed.get(0).size(); j++){
                    x.add(boxed.get(i).get(j).multiply(other));
                }
                newVector.add(x);
            }
            return new PrimitiveMatrix(newVector);
        }

        throw new RuntimeException("Matrix x Matrix currently unsupported");
    }

    private void validateAdditionSubtraction(Vector<Vector<PrimitiveValue>> otherMatrix){
        if (
                otherMatrix.size() != boxed.size() //Equal rows
                        || otherMatrix.get(0).size() != boxed.get(0).size() // equal cols
                        || boxed.size() != boxed.get(0).size()  // check n by n size for both
                        || otherMatrix.size() != otherMatrix.get(0).size()
        )
            throw new RuntimeException("Cannot add 2 matrices of unequal dimensions");
    }

    @Override
    public int toInt() {
        int s = 0;
        for(Vector<PrimitiveValue> v : boxed)
            s += v.size();
        return s;
    }

    @Override
    public double toDouble() {
        return toInt();
    }

    @Override
    public float toFloat() {
        return toInt();
    }

    @Override
    public String toString() {
        return boxed.stream().map(
                e -> e.stream().map(PrimitiveValue::toString).collect(Collectors.joining(" "))
        ).collect(Collectors.joining("\n"));
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        ArrayList<PrimitiveValue> toReturn = new ArrayList<>();
        for (Vector<PrimitiveValue> row : boxed){
            toReturn.add(new PrimitiveCollection(new ArrayList<>(row)));
        }
        return toReturn;
    }

    @Override
    public Vector<Vector<PrimitiveValue>> toMatrix() {
        return boxed;
    }

    @Override
    protected boolean equalsCheck(PrimitiveValue other) {
        return toCollection().equals(other.toCollection());
    }
}
