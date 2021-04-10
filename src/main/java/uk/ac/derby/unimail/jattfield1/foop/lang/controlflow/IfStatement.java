package uk.ac.derby.unimail.jattfield1.foop.lang.controlflow;

/*

?  (boolean condition) { codeblock }
?: (boolean condition) { codeblock }
:  {codeblock}
 */

import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveBool;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.FoopVisitor;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.SimpleNode;

import java.util.LinkedHashMap;
import java.util.Map;

public class IfStatement {
    //Many unique keys, null is else case
    private LinkedHashMap<SimpleNode, SimpleNode> cases;

    public IfStatement (LinkedHashMap<SimpleNode, SimpleNode> cases){
        this.cases = cases;
    }

    public IfStatement(){
        this.cases = new LinkedHashMap<>();
    }

    public IfStatement putCase(SimpleNode condition, SimpleNode codeblock) {
        if (cases.containsKey(condition))
            throw new RuntimeException("If statement cannot have multiple cases of the same condition");
        cases.put(condition, codeblock);
        return this;
    }

    public Object execute(FoopVisitor visitor){
        for (Map.Entry<SimpleNode, SimpleNode> entry : cases.entrySet()){
            // want to execute this last, regardless of insertion order, although grammar will ensure correct insertion order
            if (entry.getKey() == null) 
                continue;

            PrimitiveValue conditionReturn = (PrimitiveValue) entry.getKey().jjtAccept(visitor, null);
            if (conditionReturn.toBool()){
                return entry.getValue().jjtAccept(visitor, null);
            }
        }
        if (cases.containsKey(null)){
            return cases.get(null).jjtAccept(visitor, null);
        }
        return null;
    }
}
