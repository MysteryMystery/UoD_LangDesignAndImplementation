package uk.ac.derby.unimail.jattfield1.foop.lang.controlflow;


import uk.ac.derby.unimail.jattfield1.foop.lang.FoopParser;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.ASTCodeBlock;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.FoopVisitor;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.SimpleNode;

import java.util.stream.Collectors;

public class WhileStatement {
    private SimpleNode booleanCondition;
    private SimpleNode codeBlock;

    public WhileStatement(){

    }

    public WhileStatement(SimpleNode booleanCondition){
        this.booleanCondition = booleanCondition;
    }

    public void setBooleanCondition(SimpleNode booleanCondition) {
        this.booleanCondition = booleanCondition;
    }

    public void setCodeBlock(SimpleNode codeBlock) {
        this.codeBlock = codeBlock;
    }

    public Object execute(FoopParser visitor){
        PrimitiveValue loopCond = (PrimitiveValue) booleanCondition.jjtAccept(visitor, null);
        Object result = null;
        System.out.println(visitor.getCurrentScope().getVariables().entrySet().stream().map(x -> x.getKey() + ": " + x.getValue()).collect(Collectors.joining(", ")));
        System.out.println(codeBlock.jjtAccept(visitor, null));
        System.out.println(codeBlock.jjtAccept(visitor, null));
        System.out.println(codeBlock.jjtAccept(visitor, null));
        System.out.println(visitor.getCurrentScope().getVariables().entrySet().stream().map(x -> x.getKey() + ": " + x.getValue()).collect(Collectors.joining(", ")));
        System.out.println(loopCond.toBool());
        return null;

        /*
        while (loopCond.toBool()){
            result = codeBlock.jjtAccept(visitor, null);
        }
        return result;
         */

    }
}
