package uk.ac.derby.unimail.jattfield1.foop.lang.identity;

public abstract class NamedIdentity {
    private final String name;

    public NamedIdentity(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
