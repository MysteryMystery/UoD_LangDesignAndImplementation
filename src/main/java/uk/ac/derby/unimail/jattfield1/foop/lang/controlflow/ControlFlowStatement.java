package uk.ac.derby.unimail.jattfield1.foop.lang.controlflow;

import uk.ac.derby.unimail.jattfield1.foop.parser.ast.FoopVisitor;

public interface ControlFlowStatement {
    Object execute(FoopVisitor visitor);
}
