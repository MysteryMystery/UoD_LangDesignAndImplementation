package uk.ac.derby.unimail.jattfield1.classy.lang.identity;

import uk.ac.derby.unimail.jattfield1.classy.lang.ExecutionContext;

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
