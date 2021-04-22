package uk.ac.derby.unimail.jattfield1.classy.compiler.instruction;

import uk.ac.derby.unimail.jattfield1.classy.compiler.Instruction;
import uk.ac.derby.unimail.jattfield1.classy.compiler.Scope;
import uk.ac.derby.unimail.jattfield1.classy.lang.identity.Variable;

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
