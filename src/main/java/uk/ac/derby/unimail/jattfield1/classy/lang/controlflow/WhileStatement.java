package uk.ac.derby.unimail.jattfield1.classy.lang.controlflow;


import uk.ac.derby.unimail.jattfield1.classy.lang.ClassyParser;
import uk.ac.derby.unimail.jattfield1.classy.lang.primitive.PrimitiveValue;
import uk.ac.derby.unimail.jattfield1.classy.parser.ast.SimpleNode;

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

    public Object execute(ClassyParser visitor){
        Object result = null;

        while (getLoopCondition(visitor)){
            result = codeBlock.jjtAccept(visitor, null);
        }
        return result;
    }

    private boolean getLoopCondition(ClassyParser visitor){
        return ((PrimitiveValue) booleanCondition.jjtAccept(visitor, null)).toBool();
    }
}
