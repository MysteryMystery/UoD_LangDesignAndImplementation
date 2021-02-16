package uk.ac.derby.unimail.jattfield1.foop.lang;

import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveBool;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveInt;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.*;

public class FoopParser implements FoopVisitor {



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
        PrimitiveValue value = (PrimitiveValue) node.jjtGetChild(0).jjtAccept(this, data);
        System.out.println(">> " + value.toString());
        System.out.print("> ");
        return data;
    }

    @Override
    public Object visit(ASTAdd node, Object data) {
        Node child = node.jjtGetChild(0).jjtAccept(this, null);
        return null;
    }

    @Override
    public Object visit(ASTSub node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTMultiply node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTDivide node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTNot node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTUnaryPlus node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTUnarySub node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        String variableName = node.tokenValue;
        System.out.println(variableName + "=");
        return data;
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        return node.tokenValue;
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
