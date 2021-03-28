package uk.ac.derby.unimail.jattfield1.foop.lang;

import uk.ac.derby.unimail.jattfield1.foop.parser.ParseException;

public class FoopRepl {
    public static void main(String[] args) {
        uk.ac.derby.unimail.jattfield1.foop.parser.Foop foop = new uk.ac.derby.unimail.jattfield1.foop.parser.Foop(System.in);
        try {
            while (true)
                foop.program().jjtAccept(FoopParser.getInstance(), null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
