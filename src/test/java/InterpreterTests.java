import org.junit.jupiter.api.Test;
import uk.ac.derby.unimail.jattfield1.foop.lang.Foop;
import uk.ac.derby.unimail.jattfield1.foop.lang.FoopParser;

public class InterpreterTests {

    @Test
    public void function(){
        String code = "Int x = 20; meth x expects Int y does { print y; } x(x);";
        Foop.fromString(code);
    }
}
