package uk.ac.derby.unimail.jattfield1.foop.lang;

import uk.ac.derby.unimail.jattfield1.foop.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Foop {
    public static void main(String[] args) {
        try {
            FileInputStream input = new FileInputStream(args[0]);
            //System.out.println((new BufferedReader(new InputStreamReader(input))).lines().collect(Collectors.joining("\n")));
            run(input);
        } catch (Throwable e){
            System.out.println(e.getMessage());
        }
    }

    public static void fromString(String code){
        try {
            InputStream inputStream = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
            run(inputStream);
        } catch (Throwable e){
            e.printStackTrace();
        }
    }

    static void run(InputStream input) throws ParseException {
        uk.ac.derby.unimail.jattfield1.foop.parser.Foop foop = new uk.ac.derby.unimail.jattfield1.foop.parser.Foop(input);

        System.out.println("Foop (Functional Object Orientated Programming)");
        foop.program().jjtAccept(FoopParser.getInstance(), null);
    }
}
