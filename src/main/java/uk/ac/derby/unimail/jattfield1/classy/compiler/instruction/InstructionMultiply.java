package uk.ac.derby.unimail.jattfield1.classy.compiler.instruction;

import uk.ac.derby.unimail.jattfield1.classy.compiler.Instruction;
import uk.ac.derby.unimail.jattfield1.classy.compiler.Scope;

public class InstructionMultiply extends Instruction {
    @Override
    public void evaluate(Scope scope) {
        this.popPopPush(scope, "multiply");
    }
}
