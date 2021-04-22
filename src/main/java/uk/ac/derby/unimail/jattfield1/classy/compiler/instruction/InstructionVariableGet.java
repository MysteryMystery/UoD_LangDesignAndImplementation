package uk.ac.derby.unimail.jattfield1.classy.compiler.instruction;

import uk.ac.derby.unimail.jattfield1.classy.compiler.Instruction;
import uk.ac.derby.unimail.jattfield1.classy.compiler.Scope;

public class InstructionVariableGet extends Instruction {
    private final String name;

    public InstructionVariableGet(String name){
        this.name = name;
    }

    @Override
    public void evaluate(Scope scope) {
        scope.valueStack.push(scope.getIdentity(name));
    }
}
