package uk.ac.derby.unimail.jattfield1.foop.lang;

import uk.ac.derby.unimail.jattfield1.foop.lang.identity.Variable;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveBool;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveInt;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.*;

public class FoopParser implements FoopVisitor {

    private <T> T getChild(Node node, int index){
        return (T) node.jjtGetChild(index).jjtAccept(this, null);
    }

    @Override
    public Object visit(SimpleNode node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTProgram node, Object data) {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTExpression node, Object data) {
        Object value = node.jjtGetChild(0).jjtAccept(this, data);
        System.out.println(">> " + value.toString());
        return data;
    }

    @Override
    public Object visit(ASTAdd node, Object data) {
        PrimitiveValue child1 = getChild(node, 0);
        PrimitiveValue child2 = getChild(node, 1);
        return child1.add(child2);
    }

    @Override
    public Object visit(ASTSub node, Object data) {
        PrimitiveValue child1 = getChild(node, 0);
        PrimitiveValue child2 = getChild(node, 1);
        return child1.subtract(child2);
    }

    @Override
    public Object visit(ASTMultiply node, Object data) {
        PrimitiveValue child1 = getChild(node, 0);
        PrimitiveValue child2 = getChild(node, 1);
        return child1.multiply(child2);
    }

    @Override
    public Object visit(ASTDivide node, Object data) {
        PrimitiveValue child1 = getChild(node, 0);
        PrimitiveValue child2 = getChild(node, 1);
        return child1.divide(child2);
    }

    @Override
    public Object visit(ASTNot node, Object data) {
        PrimitiveValue child1 = getChild(node, 0);
        return child1.not();
    }

    @Override
    public Object visit(ASTUnaryPlus node, Object data) {
        PrimitiveValue child1 = getChild(node, 0);
        return child1.unaryPlus();
    }

    @Override
    public Object visit(ASTUnarySub node, Object data) {
        return this.<PrimitiveValue>getChild(node, 0).unarySubtract();
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        System.out.println("Num children: " + node.jjtGetNumChildren());
        System.out.println(this.<PrimitiveValue>getChild(node, 1));

        Variable variable = getChild(node, 0);
        variable.setData(getChild(node, 1));
        return variable;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        return new Variable(node.tokenValue);
    }

    @Override
    public Object visit(ASTInt node, Object data) {
        return new PrimitiveInt(Integer.parseInt(node.tokenValue));
    }

    @Override
    public Object visit(ASTBool node, Object data) {
        return new PrimitiveBool(Boolean.parseBoolean(node.tokenValue));
    }
}
