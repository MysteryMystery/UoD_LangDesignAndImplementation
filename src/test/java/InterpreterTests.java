import org.junit.jupiter.api.Test;
import uk.ac.derby.unimail.jattfield1.classy.lang.Classy;

public class InterpreterTests {

    @Test
    public void function(){
        String code = "Int x = 20; meth x expects Int y { print y; } x(x);";
        Classy.fromString(code);
    }

    @Test
    public void assignFunctionResult(){
        StringBuilder sb = new StringBuilder();
        sb.append("Int x = 20;")
                .append("meth x expects Int y { y; }")
                .append("print x(x);");
        Classy.fromString(sb.toString());
    }

    @Test
    public void var(){
        String code = "Int x = 20; print x; x = x + 1; print x;";
        Classy.fromString(code);
    }

    @Test
    public void strictTypes(){
        String code = "Int x = 20; Int y = \"hi\";";
        Classy.fromString(code);
    }

    @Test
    public void ifStatement(){
        String code = "? (true) { print \"true\"; } ";
        Classy.fromString(code);
    }

    @Test
    public void ifElifStatement(){
        String code = "? (false) { print \"true\"; } ?: (false) { print \"false\"; } : { print \"else\"; } ";
        Classy.fromString(code);
    }

    @Test
    public void assignIfElse() {
        String code = "Int x = ? (5 > 6) { 5; } : { 0; }; print x;";
        Classy.fromString(code);
    }

    @Test
    public void whileTest() {
        String code = "Int x = 0; while x < 10 { x = x + 1; print x;}";
        Classy.fromString(code);
    }

    @Test
    public void twoDArray(){
        String code = "Collection x = [[1,2],[2,3],2]; print x;";
        Classy.fromString(code);
    }

    @Test
    public void indexTest(){
        String code = "Collection x = [1,2,3]; print x:0; print x:1;";
        code += "String str = \"Hello World\"; print str:1;";
        Classy.fromString(code);
    }

    @Test
    public void indexReassignment() {
        String code = "Collection x = [1,2,3]; x:0 = 2; print x;";
        code += "String str = \"Hello World!\"; str:0 = \"J\"; print str;";
        Classy.fromString(code);
    }

    @Test
    public void arrayAppending(){
        String code = "Collection x = [1,2,3,4]; x + 5; print x;";
        Classy.fromString(code);
    }

    @Test
    public void classDeclaration(){
        Classy.fromResource("class_declaration.classy");
    }

    @Test
    public void exampleSorting(){
        Classy.fromResource("example_sorting.classy");
    }
}
