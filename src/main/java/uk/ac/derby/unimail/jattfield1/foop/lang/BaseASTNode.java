package uk.ac.derby.unimail.jattfield1.foop.lang;

public class BaseASTNode {
    public String tokenValue = null;

    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
