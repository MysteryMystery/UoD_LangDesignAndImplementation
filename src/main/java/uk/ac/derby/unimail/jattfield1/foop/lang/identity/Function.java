package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

import uk.ac.derby.unimail.jattfield1.foop.compiler.Scope;
import uk.ac.derby.unimail.jattfield1.foop.lang.BaseASTNode;
import uk.ac.derby.unimail.jattfield1.foop.lang.FoopParser;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.Node;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.SimpleNode;

import java.util.*;

public class Function {
    private LinkedHashMap<String, Constant> params; // to maintain insertion order
    private List<String> paramIndexes;
    private int populatedParameterCount = 0;
    private SimpleNode functionBody;
    private String name;

    public Function(String name, List<Constant> params, SimpleNode functionBody) {
        this.name = name;
        this.params = new LinkedHashMap<>();
        params.forEach(i -> this.params.put(i.getName(), i));
        paramIndexes = new ArrayList<>(this.params.keySet());
        this.functionBody = functionBody;
    }

    @Override
    public String toString() {
        return "Function: " + getName();
    }

    public String getName(){
        return name;
    }

    public Function setPositionalArg(PrimitiveValue value, int position){
        if (position > paramIndexes.size() - 1)
            throw new RuntimeException("Function was expecting " + paramIndexes.size() + " parameters but parameter " + position + " was added.");

        String k = paramIndexes.get(position);
        Constant c = (Constant) params.get(k);
        c.setData(value);
        params.put(k, c);
        return this;
    }

    public Function setPositionalArg(PrimitiveValue value){
        setPositionalArg(value, populatedParameterCount);
        populatedParameterCount++;
        return this;
    }

    public Object execute(FoopParser parser){
        // set scope of parser to this scope, then reset at end then return value - by Parse.setScope etc.
        return functionBody.childrenAccept(parser, null);
    }
}
