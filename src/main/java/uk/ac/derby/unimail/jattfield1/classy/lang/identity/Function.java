package uk.ac.derby.unimail.jattfield1.classy.lang.identity;

import uk.ac.derby.unimail.jattfield1.classy.lang.ExecutionContext;
import uk.ac.derby.unimail.jattfield1.classy.lang.ClassyParser;
import uk.ac.derby.unimail.jattfield1.classy.lang.primitive.PrimitiveValue;
import uk.ac.derby.unimail.jattfield1.classy.parser.ast.SimpleNode;

import java.util.*;

public class Function {
    private LinkedHashMap<String, Constant> params; // to maintain insertion order
    private List<String> paramIndexes;
    private int populatedParameterCount = 0;
    private SimpleNode functionBody;
    private String name;
    private ExecutionContext parent;

    public Function(String name, List<Constant> params, SimpleNode functionBody, ExecutionContext parent) {
        this.name = name;
        this.params = new LinkedHashMap<>();
        params.forEach(i -> this.params.put(i.getName(), i));
        paramIndexes = new ArrayList<>(this.params.keySet());
        this.functionBody = functionBody;
        this.parent = parent;
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
        Constant c = params.get(k);
        c.setData(NamedIdentity.valueOf(value));
        params.put(k, c);
        return this;
    }

    public Function setPositionalArg(PrimitiveValue value){
        setPositionalArg(value, populatedParameterCount);
        populatedParameterCount++;
        return this;
    }

    public void clearArgs(){
        //params = (LinkedHashMap<String, Constant>) params.keySet().stream().map(Constant::new).collect(Collectors.toMap(NamedIdentity::getName, e -> e));

        for(Map.Entry<String, Constant> e : params.entrySet()){
            params.replace(e.getKey(), new Constant(e.getValue().getName()));
        }
        populatedParameterCount = 0;
    }

    private void injectArgsToScope(ExecutionContext context){
        params.forEach((key, value) -> context.putNamedIdentity(value));
    }

    public Object execute(ClassyParser parser){
        // set scope of parser to this scope, then reset at end then return value - by Parse.setScope etc.
        parser.newScope(parent);
        injectArgsToScope(parser.getCurrentScope());
        //System.out.println("vars: " + parser.getCurrentScope().getVariables().values().stream().map(ni -> ni.getName() + ": " + ni.getResult()).collect(Collectors.joining(", ")));

        // Just the code block rule. In Codeblock processing, the final is returned anyway.
        Object ret = functionBody.jjtAccept(parser, null);
        parser.parentScope();
        clearArgs();
        return ret;
    }
}
