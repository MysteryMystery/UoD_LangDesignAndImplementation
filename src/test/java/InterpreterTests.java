import org.junit.jupiter.api.Test;
import uk.ac.derby.unimail.jattfield1.foop.lang.Foop;
import uk.ac.derby.unimail.jattfield1.foop.lang.FoopParser;

public class InterpreterTests {

    @Test
    public void function(){
        String code = "Int x = 20; meth x expects Int y does { print y; } x(x);";
        Foop.fromString(code);
    }

    @Test
    public void assignFunctionResult(){
        StringBuilder sb = new StringBuilder();
        sb.append("Int x = 20;")
                .append("meth x expects Int y does { y; }")
                .append("print x(x);");
        Foop.fromString(sb.toString());
    }

    @Test
    public void var(){
        String code = "Int x = 20; x;";
        Foop.fromString(code);
    }

    @Test
    public void strictTypes(){
        String code = "Int x = 20; Int y = \"hi\";";
        Foop.fromString(code);
    }

}
