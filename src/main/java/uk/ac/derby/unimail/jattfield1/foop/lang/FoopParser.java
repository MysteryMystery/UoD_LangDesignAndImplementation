package uk.ac.derby.unimail.jattfield1.foop.lang;

import uk.ac.derby.unimail.jattfield1.foop.compiler.FoopCompiler;
import uk.ac.derby.unimail.jattfield1.foop.lang.exception.VariableDoesNotExistException;
import uk.ac.derby.unimail.jattfield1.foop.lang.identity.NamedIdentity;
import uk.ac.derby.unimail.jattfield1.foop.lang.identity.Variable;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveBool;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveCollection;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveInt;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FoopParser implements FoopVisitor {
    private final FoopCompiler compiler;
    private static FoopParser instance;

    FoopParser() {
        compiler = new FoopCompiler();
        instance = this;
    }

    public static FoopParser getInstance() {
        if (instance == null)
            new FoopParser();
        return instance;
    }

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
    public Object visit(ASTParameterList node, Object data) {
        ArrayList<Variable> params = new ArrayList<>();
        for (int i=0; i < node.jjtGetNumChildren(); i++){
            params.add(getChild(node, i));
        }
        return params.toArray();
    }

    @Override
    public Object visit(ASTFunctionDefinition node, Object data) {
        System.out.println(node.jjtGetNumChildren());
        return null;
    }

    @Override
    public Object visit(ASTCodeBlock node, Object data) {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTStatement node, Object data) {
        Object value = node.jjtGetChild(0).jjtAccept(this, data);
        return data;
    }

    @Override
    public Object visit(ASTPrint node, Object data) {
        PrimitiveValue c = getChild(node, 0);
        System.out.println(c.toString());
        return c;
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
    public Object visit(ASTCollection node, Object data) {
        ArrayList<PrimitiveValue> collection = new ArrayList<>();

        for(int i = 0; i < node.jjtGetNumChildren(); i++)
            collection.add(getChild(node, i));

        return new PrimitiveCollection(collection);
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        Variable variable = getChild(node, 0);
        variable.setData(getChild(node, 1));

        compiler.getGlobalScope().registerIdentity(variable);

        return variable;
    }

    @Override
    public Object visit(ASTReassignment node, Object data) {
        Variable variable = getChild(node, 0);
        if (compiler.getGlobalScope().hasIdentity(variable.getName())){
            variable.setData(getChild(node, 1));
            return variable;
        }
        return null;
    }

    @Override
    public Object visit(ASTOr node, Object data) {
        return this.<PrimitiveValue>getChild(node, 0).or(getChild(node, 1));
    }

    @Override
    public Object visit(ASTAnd node, Object data) {
        return this.<PrimitiveValue>getChild(node, 0).and(getChild(node, 1));
    }

    @Override
    public Object visit(ASTEqualTo node, Object data) {
        return this.<PrimitiveValue>getChild(node, 0).equalTo(getChild(node, 1));
    }

    @Override
    public Object visit(ASTNotEqualTo node, Object data) {
        return this.<PrimitiveValue>getChild(node, 0).notEqualTo(getChild(node, 1));
    }

    @Override
    public Object visit(ASTGreaterThanOrEqualTo node, Object data) {
        return this.<PrimitiveValue>getChild(node, 0).greaterThanEqualTo(getChild(node, 1));
    }

    @Override
    public Object visit(ASTLessThanOrEqualTo node, Object data) {
        return this.<PrimitiveValue>getChild(node, 0).lessThanEqualTo(getChild(node, 1));
    }

    @Override
    public Object visit(ASTGreaterThan node, Object data) {
        return this.<PrimitiveValue>getChild(node, 0).greaterThan(getChild(node, 1));
    }

    @Override
    public Object visit(ASTLessThan node, Object data) {
        return this.<PrimitiveValue>getChild(node, 0).lessThan(getChild(node, 1));
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        if (compiler.getGlobalScope().hasIdentity(node.tokenValue))
            return compiler.getGlobalScope().getIdentity(node.tokenValue);
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
