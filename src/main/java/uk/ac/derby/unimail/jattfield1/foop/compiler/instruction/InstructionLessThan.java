package uk.ac.derby.unimail.jattfield1.foop.compiler.instruction;

import uk.ac.derby.unimail.jattfield1.foop.compiler.Instruction;
import uk.ac.derby.unimail.jattfield1.foop.compiler.Scope;

public class InstructionLessThan extends Instruction {
    @Override
    public void evaluate(Scope scope) {
        popPopPush(scope, "lessThan");
    }
}
