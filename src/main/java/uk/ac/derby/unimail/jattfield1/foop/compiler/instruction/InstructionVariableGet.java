package uk.ac.derby.unimail.jattfield1.foop.compiler.instruction;

import uk.ac.derby.unimail.jattfield1.foop.compiler.Instruction;
import uk.ac.derby.unimail.jattfield1.foop.compiler.Scope;

import java.util.stream.Collectors;

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
