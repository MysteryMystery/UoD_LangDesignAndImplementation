package uk.ac.derby.unimail.jattfield1.classy.compiler.instruction;

import uk.ac.derby.unimail.jattfield1.classy.compiler.Instruction;
import uk.ac.derby.unimail.jattfield1.classy.compiler.Scope;

public class InstructionPrint extends Instruction {
    @Override
    public void evaluate(Scope scope) {
        System.out.println(scope.valueStack.pop());
    }
}
