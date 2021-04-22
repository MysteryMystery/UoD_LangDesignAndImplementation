package uk.ac.derby.unimail.jattfield1.classy.lang.controlflow;

import uk.ac.derby.unimail.jattfield1.classy.parser.ast.ClassyVisitor;

public interface ControlFlowStatement {
    Object execute(ClassyVisitor visitor);
}
