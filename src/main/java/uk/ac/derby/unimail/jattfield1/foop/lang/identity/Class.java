package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

import uk.ac.derby.unimail.jattfield1.foop.lang.ExecutionContext;
import uk.ac.derby.unimail.jattfield1.foop.parser.ast.ASTAdd;

public class Class {
    private final ExecutionContext scope = new ExecutionContext();

    private final String type;

    public Class(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public ExecutionContext getScope() {
        return scope;
    }


}
