package uk.ac.derby.unimail.jattfield1.foop.lang;

import uk.ac.derby.unimail.jattfield1.foop.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Foop {
    public static void main(String[] args) {
        try {
            FileInputStream input = new FileInputStream(args[0]);
            //System.out.println((new BufferedReader(new InputStreamReader(input))).lines().collect(Collectors.joining("\n")));

            uk.ac.derby.unimail.jattfield1.foop.parser.Foop foop = new uk.ac.derby.unimail.jattfield1.foop.parser.Foop(input);

            System.out.println("Foop (Functional Object Orientated Programming)");
           /*
            while(true)
                foop.program().jjtAccept(FoopParser.getInstance(), null);
            */

            foop.program().jjtAccept(FoopParser.getInstance(), null);
        } catch (Throwable e){
            System.out.println(e.getMessage());
        }
    }
}
