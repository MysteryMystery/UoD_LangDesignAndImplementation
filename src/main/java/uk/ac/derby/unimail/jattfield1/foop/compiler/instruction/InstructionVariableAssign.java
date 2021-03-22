package uk.ac.derby.unimail.jattfield1.foop.compiler.instruction;

import uk.ac.derby.unimail.jattfield1.foop.compiler.Instruction;
import uk.ac.derby.unimail.jattfield1.foop.compiler.Scope;
import uk.ac.derby.unimail.jattfield1.foop.lang.identity.Variable;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;

public class InstructionVariableAssign extends Instruction {
    private String variableName;

    public InstructionVariableAssign(String a){
        variableName = a;
    }

    @Override
    public void evaluate(Scope scope) {
        scope.registerIdentity(new Variable(variableName, scope.valueStack.pop()));
    }
}
