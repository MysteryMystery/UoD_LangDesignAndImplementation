package uk.ac.derby.unimail.jattfield1.foop.compiler;

import uk.ac.derby.unimail.jattfield1.foop.lang.identity.NamedIdentity;

import java.util.HashMap;
import java.util.stream.Collectors;

public class FoopCompiler {
    private final HashMap<String, NamedIdentity> identities = new HashMap<>();

    public void registerIdentity(NamedIdentity identity){
        identities.put(identity.getName(), identity);
    }

    public <T extends NamedIdentity> T getIdentity(NamedIdentity identity){
        return getIdentity(identity.getName());
    }

    public <T extends NamedIdentity> T getIdentity(String key){
        if (identities.containsKey(key))
            return (T) identities.get(key);
        return null;
    }

    public boolean hasIdentity(String key){
        return identities.containsKey(key);
    }

    public HashMap<String, NamedIdentity> getIdentities() {
        return identities;
    }

    public String getIdentitiesAsString(){
        return identities.keySet().stream().map(k -> identities.get(k).toString()).collect(Collectors.joining(", ", "{", "}"));
    }
}
