package uk.ac.derby.unimail.jattfield1.classy.lang;

import uk.ac.derby.unimail.jattfield1.classy.parser.ParseException;

public class ClassyRepl {
    public static void main(String[] args) {
        uk.ac.derby.unimail.jattfield1.classy.parser.Classy classy = new uk.ac.derby.unimail.jattfield1.classy.parser.Classy(System.in);
        try {
            while (true)
                classy.program().jjtAccept(ClassyParser.getInstance(), null);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
