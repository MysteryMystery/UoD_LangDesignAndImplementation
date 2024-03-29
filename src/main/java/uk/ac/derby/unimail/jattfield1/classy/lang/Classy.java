package uk.ac.derby.unimail.jattfield1.classy.lang;

import uk.ac.derby.unimail.jattfield1.classy.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Classy {
    public static void main(String[] args) {
        try {
            FileInputStream input = new FileInputStream(args[0]);
            //System.out.println((new BufferedReader(new InputStreamReader(input))).lines().collect(Collectors.joining("\n")));
            run(input);
        } catch (Throwable e){
            System.out.println(e.getMessage());
        }
    }

    public static void fromResource(String resourceName){
        try{
            ClassLoader classLoader = Classy.class.getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource(resourceName)).getFile());
            run(new FileInputStream(file));
        } catch (Exception e){
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
        uk.ac.derby.unimail.jattfield1.classy.parser.Classy classy = new uk.ac.derby.unimail.jattfield1.classy.parser.Classy(input);

        System.out.println("Welcome to Classy.");
        classy.program().jjtAccept(ClassyParser.getInstance(), null);
    }
}
