package uk.ac.derby.unimail.jattfield1.foop.compiler.instruction;

import uk.ac.derby.unimail.jattfield1.foop.compiler.Instruction;
import uk.ac.derby.unimail.jattfield1.foop.compiler.Scope;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveValue;

public class InstructionPushValue extends Instruction {
    private PrimitiveValue v;

    public InstructionPushValue(PrimitiveValue value){
        this.v = value;
    }

    @Override
    public void evaluate(Scope scope) {
        scope.valueStack.push(v);
    }
}
