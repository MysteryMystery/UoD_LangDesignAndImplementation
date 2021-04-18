import org.junit.jupiter.api.Test;
import uk.ac.derby.unimail.jattfield1.foop.lang.Foop;
import uk.ac.derby.unimail.jattfield1.foop.lang.FoopParser;

public class InterpreterTests {

    @Test
    public void function(){
        String code = "Int x = 20; meth x expects Int y { print y; } x(x);";
        Foop.fromString(code);
    }

    @Test
    public void assignFunctionResult(){
        StringBuilder sb = new StringBuilder();
        sb.append("Int x = 20;")
                .append("meth x expects Int y { y; }")
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

    @Test
    public void ifStatement(){
        String code = "? (true) { print \"true\"; } ";
        Foop.fromString(code);
    }

    @Test
    public void ifElifStatement(){
        String code = "? (false) { print \"true\"; } ?: (false) { print \"false\"; } : { print \"else\"; } ";
        Foop.fromString(code);
    }

    @Test
    public void assignIfElse() {
        String code = "Int x = ? (5 > 6) { 5; } : { 0; }; print x;";
        Foop.fromString(code);
    }

    @Test
    public void whileTest() {
        String code = "Int x = 0; while x < 1 { x = x + 1; }";
        Foop.fromString(code);
    }
}
