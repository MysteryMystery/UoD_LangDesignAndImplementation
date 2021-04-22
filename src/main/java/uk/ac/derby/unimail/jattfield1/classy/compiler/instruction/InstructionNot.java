package uk.ac.derby.unimail.jattfield1.classy.compiler.instruction;

import uk.ac.derby.unimail.jattfield1.classy.compiler.Instruction;
import uk.ac.derby.unimail.jattfield1.classy.compiler.Scope;

public class InstructionNot extends Instruction {
    @Override
    public void evaluate(Scope scope) {
        scope.valueStack.push(scope.valueStack.pop().not());
    }
}
