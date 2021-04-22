package uk.ac.derby.unimail.jattfield1.classy.lang;

import uk.ac.derby.unimail.jattfield1.classy.lang.controlflow.IfStatement;
import uk.ac.derby.unimail.jattfield1.classy.lang.controlflow.WhileStatement;
import uk.ac.derby.unimail.jattfield1.classy.lang.identity.Constant;
import uk.ac.derby.unimail.jattfield1.classy.lang.identity.Function;
import uk.ac.derby.unimail.jattfield1.classy.lang.identity.NamedIdentity;
import uk.ac.derby.unimail.jattfield1.classy.lang.identity.Variable;
import uk.ac.derby.unimail.jattfield1.classy.lang.primitive.*;
import uk.ac.derby.unimail.jattfield1.classy.parser.ast.*;

import java.util.ArrayList;
import java.util.List;

public class ClassyParser implements ClassyVisitor {
    private static ClassyParser instance;
    private final ExecutionContext globalScope;
    private ExecutionContext currentScope;

    ClassyParser() {
        globalScope = new ExecutionContext();
        currentScope = globalScope;
        instance = this;
    }

    public static ClassyParser getInstance() {
        if (instance == null)
            new ClassyParser();
        return instance;
    }

    public void newScope() {
        this.currentScope = new ExecutionContext(currentScope);
    }

    public void parentScope(){
        if (this.currentScope.getParent() == null)
            return;
        this.currentScope = this.currentScope.getParent();
    }

    public ExecutionContext getCurrentScope() {
        return currentScope;
    }

    private <T> T getChild(Node node, int index){
        return (T) node.jjtGetChild(index).jjtAccept(this, null);
    }

    private void eachChild(Node node, java.util.function.Function<Node, Object> f){
        for (int i = 0; i < node.jjtGetNumChildren(); i++){
            f.apply(node.jjtGetChild(i));
        }
    };

    @Override
    public Object visit(SimpleNode node, Object data) {
         throw new RuntimeException("No matching rule found.");
    }

    @Override
    public Object visit(ASTProgram node, Object data) {
        return node.childrenAccept(this, data);
    }

    @Override
    public Object visit(ASTParameterList node, Object data) {
        ArrayList<Constant> params = new ArrayList<>();
        for (int i=0; i < node.jjtGetNumChildren(); i=i+2){
            Constant c = new Constant(getChild(node, i+1));
            c.setType(getChild(node, i));
            params.add(c);
        }
        return params;
    }

    @Override
    public Object visit(ASTAttributeDefinition node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTFunctionDefinition node, Object data) {
        Function function = new Function(getChild(node, 0), getChild(node, 1), (SimpleNode) node.jjtGetChild(2));
        currentScope.putFunction(function);
        return function;
    }

    /**
     *
     * @param node
     * @param data
     * @return result of last instruction of the block - useful for the functions
     */
    @Override
    public Object visit(ASTCodeBlock node, Object data) {
        //return node.childrenAccept(this, data);
        Object o = null;
        for(int i = 0; i < node.jjtGetNumChildren(); i++)
            o = node.jjtGetChild(i).jjtAccept(this, null);
        return o;
    }

    @Override
    public Object visit(ASTStatement node, Object data) {
        Object value = node.jjtGetChild(0).jjtAccept(this, data);
        return value;
    }

    public Object visit(ASTNthElement node, Object data) {
        System.out.println("seen");
        return null;
    }

    @Override
    public Object visit(ASTClassDefinition node, Object data) {
        return null;
    }

    @Override
    public Object visit(ASTFunctionCall node, Object data) {
        Function function = currentScope.getFunction(getChild(node, 0));
        List<PrimitiveValue> args = getChild(node, 1);
        args.forEach(function::setPositionalArg);
        return function.execute(this);
    }

    @Override
    public Object visit(ASTFunctionCallArgs node, Object data) {
        ArrayList<PrimitiveValue> args = new ArrayList<>();
        for (int i=0; i < node.jjtGetNumChildren(); i++){
            args.add(getChild(node, i));
        }
        return args;
    }

    @Override
    public Object visit(ASTPrint node, Object data) {
        PrimitiveValue c = getChild(node, 0);
        System.out.println(c.toString());
        return c;
    }

    @Override
    public Object visit(ASTDeference node, Object data) {
        NamedIdentity namedIdentity = currentScope.getNamedIdentity(getChild(node, 0));
        if (node.jjtGetNumChildren() == 1)
            return namedIdentity;

        PrimitiveValue selected = null;
        for(int i = 1; i < node.jjtGetNumChildren(); i++){
            int index =  this.<PrimitiveValue>getChild(node, i).toInt();
            selected = namedIdentity.nthElement(index);
        }
        return selected;
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
    public Object visit(ASTFunctionIdentifier node, Object data) {
        return node.tokenValue;
    }

    @Override
    public Object visit(ASTAssignment node, Object data) {
        Variable variable = new Variable(getChild(node, 1));
        variable.setType(getChild(node, 0));
        variable.setData(getChild(node, 2));
        currentScope.putNamedIdentity(variable);
        return variable;
    }

    @Override
    public Object visit(ASTWhileStatement node, Object data) {
        WhileStatement statement = new WhileStatement();
        statement.setBooleanCondition((SimpleNode) node.jjtGetChild(0));
        statement.setCodeBlock((ASTCodeBlock) node.jjtGetChild(1));
        return statement.execute(this);
    }

    @Override
    public Object visit(ASTIfStatement node, Object data) {
        IfStatement ifStatement = new IfStatement();
        for (int i = 0; i < node.jjtGetNumChildren(); i++){
            node.jjtGetChild(i).jjtAccept(this, ifStatement);
        }
        return ifStatement.execute(this);
    }

    @Override
    public Object visit(ASTIfIf node, Object data) {
        return ((IfStatement) data).putCase((SimpleNode) node.jjtGetChild(0), (SimpleNode) node.jjtGetChild(1));
    }

    @Override
    public Object visit(ASTIfElif node, Object data) {
        return ((IfStatement) data).putCase((SimpleNode) node.jjtGetChild(0), (SimpleNode) node.jjtGetChild(1));
    }

    @Override
    public Object visit(ASTIfElse node, Object data) {
        return ((IfStatement) data).putCase(null, (SimpleNode) node.jjtGetChild(0));
    }

    @Override
    public Object visit(ASTReassignment node, Object data) {
        NamedIdentity variable = currentScope.getNamedIdentity(getChild(node, 0));
        variable.setData(getChild(node, 1));
        currentScope.putNamedIdentity(variable);
        return variable;
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
        PrimitiveValue o = getChild(node, 0);
        PrimitiveValue i = getChild(node, 1);
        return o.lessThan(i);
    }

    @Override
    public Object visit(ASTIdentifier node, Object data) {
        return node.tokenValue;
    }

    @Override
    public Object visit(ASTClassIdentifier node, Object data) {
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

    @Override
    public Object visit(ASTString node, Object data) {
        return new PrimitiveString(node.tokenValue.substring(1, node.tokenValue.length() - 1));
    }
}