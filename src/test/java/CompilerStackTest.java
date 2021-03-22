import org.junit.jupiter.api.Test;
import uk.ac.derby.unimail.jattfield1.foop.compiler.Scope;
import uk.ac.derby.unimail.jattfield1.foop.compiler.instruction.InstructionAdd;
import uk.ac.derby.unimail.jattfield1.foop.compiler.instruction.InstructionVariableAssign;
import uk.ac.derby.unimail.jattfield1.foop.compiler.instruction.InstructionVariableGet;
import uk.ac.derby.unimail.jattfield1.foop.lang.identity.NamedIdentity;
import uk.ac.derby.unimail.jattfield1.foop.lang.identity.Variable;
import uk.ac.derby.unimail.jattfield1.foop.lang.primitive.PrimitiveInt;

import java.util.HashMap;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CompilerStackTest {
    private final Scope globalScope = new Scope();

    @Test
    public void addition(){
        globalScope.valueStack.push(new PrimitiveInt(5));
        globalScope.valueStack.push(new PrimitiveInt(10));
        globalScope.compileInstruction(new InstructionAdd());
        globalScope.executeInstructions();
        assertEquals(new PrimitiveInt(15), globalScope.valueStack.pop());
    }

    @Test
    public void assignVariable(){
        globalScope.compileInstruction(new InstructionVariableAssign("x"));
        globalScope.valueStack.push(new PrimitiveInt(5));
        globalScope.executeInstructions();
        System.out.println(globalScope.getIdentities().entrySet().stream().map(entry -> entry.getKey() + ": " + entry.getValue()).collect(Collectors.joining(", ")));
    }

    @Test
    public void assignAndGetVariable(){
        globalScope.compileInstruction(new InstructionVariableGet("x"));
        globalScope.compileInstruction(new InstructionVariableAssign("x"));
        globalScope.valueStack.push(new PrimitiveInt(5));
        globalScope.executeInstructions();
        //assertEquals(5, globalScope.valueStack.pop().toInt());
        System.out.println(globalScope.valueStack.peek());
    }

    @Test
    public void addVariableToScalar(){
        globalScope.compileInstruction(new InstructionVariableGet("x"));
        globalScope.compileInstruction(new InstructionVariableAssign("x"));

        globalScope.compileInstruction(new InstructionAdd());
        globalScope.valueStack.push(new PrimitiveInt(10));

        globalScope.compileInstruction(new InstructionVariableGet("x"));
        globalScope.compileInstruction(new InstructionVariableAssign("x"));
        globalScope.valueStack.push(new PrimitiveInt(5));

        globalScope.executeInstructions();
        //assertEquals(5, globalScope.valueStack.pop().toInt());
        System.out.println(globalScope.valueStack.peek());
    }
}
