package uk.ac.derby.unimail.jattfield1.foop.lang;

public class Foop {
    public static void main(String[] args) {
        uk.ac.derby.unimail.jattfield1.foop.parser.Foop foop = new uk.ac.derby.unimail.jattfield1.foop.parser.Foop(System.in);
        try {
            System.out.println("Foop (Functional Object Orientated Programming)");
            while(true)
                foop.program().jjtAccept(new FoopParser(), null);
        } catch (Throwable e){
            e.printStackTrace();
        }
    }
}
