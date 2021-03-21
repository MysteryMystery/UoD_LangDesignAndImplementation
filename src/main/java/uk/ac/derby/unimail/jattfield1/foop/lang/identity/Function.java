package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Function extends NamedIdentity{
    private FunctionBody functionBody;
    private int numberOfParams;

    public Function(String name, int numberOfParams, FunctionBody functionBody) {
        super(name);
        this.functionBody = functionBody;
        this.numberOfParams = numberOfParams;
    }

    public static class FunctionBuilder {
        private String name;
        private int numberOfParams;
        private FunctionBody behaviour;

        FunctionBuilder(){

        }

        public static FunctionBuilder of(){
            return new FunctionBuilder();
        }

        public FunctionBuilder setName(String name){
            this.name = name;
            return this;
        }

        public FunctionBuilder setBehaviour(FunctionBody behaviour) {
            this.behaviour = behaviour;
            return this;
        }

        public FunctionBuilder setNumberOfParams(int numberOfParams) {
            this.numberOfParams = numberOfParams;
            return this;
        }

        public Function build(){
            return new Function(name, numberOfParams, behaviour);
        }
    }

    @Override
    public String toString() {
        return "Function: " + getName();
    }

    @Override
    public PrimitiveValue getResult() {
        return functionBody.execute(new HashSet<>());
    }

    public PrimitiveValue getResult(Set<PrimitiveValue> arguments){
        return functionBody.execute(arguments);
    }

    @Override
    public int toInt() {
        return 0;
    }

    @Override
    public double toDouble() {
        return 0;
    }

    @Override
    public float toFloat() {
        return 0;
    }

    @Override
    public boolean toBool() {
        return false;
    }

    @Override
    public <T> T to(Class<T> type) {
        return null;
    }

    @Override
    public ArrayList<PrimitiveValue> toCollection() {
        return null;
    }
}
