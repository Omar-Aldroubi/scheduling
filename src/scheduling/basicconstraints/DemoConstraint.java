package scheduling.basicconstraints;

import scheduling.activities.Activity;


public class DemoConstraint {
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("        DEMO CONTRAINTES");
        System.out.println("====================================\n");

        Activity a1 = new Activity("Aller à l'université", 15);
        Activity a2 = new Activity("Attacher le vélo", 1);

        PrecedenceConstraint precedenceConstraint = new PrecedenceConstraint(a1, a2);
        System.out.println("Test PrecedenceConstraint :");
        System.out.println("500 , 515 :" + precedenceConstraint.isSatisfied(500, 515));
        System.out.println("500 , 513 :" + precedenceConstraint.isSatisfied(500, 513));
        System.out.println("500 , 518 :" + precedenceConstraint.isSatisfied(500, 518));


        MeetConstraint meetConstraint = new MeetConstraint(a1, a2);
        System.out.println("\nTest MeetConstraint :");
        System.out.println("500 , 515 :" + meetConstraint.isSatisfied(500, 515));
        System.out.println("500 , 513 :" + meetConstraint.isSatisfied(500, 513));
        System.out.println("500 , 518 :" + meetConstraint.isSatisfied(500, 518));
        System.out.println();

        System.out.println("\n------------------------------------\n");
    }
}
