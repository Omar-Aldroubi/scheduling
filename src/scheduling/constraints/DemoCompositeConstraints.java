package scheduling.constraints;

import scheduling.activities.Activity;

import java.util.HashMap;
import java.util.Map;

/**
 * Petite démo des contraintes composites :
 * - négation d'une contrainte
 * - disjonction de deux contraintes
 */
public class DemoCompositeConstraints {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("   DEMO CONTRAINTES COMPOSÉES");
        System.out.println("====================================\n");

        // Deux activités toutes simples
        Activity a = new Activity("A", 10);
        Activity b = new Activity("B", 5);

        // Contrainte : A avant B
        Constraint cPrec = new PrecedenceConstraint(a, b);

        // Négation de cette contrainte
        Constraint cNotPrec = new NegationConstraint(cPrec);

        // Contrainte unaire : A doit commencer entre 100 et 200
        Constraint cUnaryA = new UnaryConstraint(a, 100, 200);

        // Disjonction : (A avant B) OU (A entre 100 et 200)
        Constraint cDisj = new DisjunctionConstraint(cPrec, cUnaryA);

        // Premier emploi du temps : A=0, B=20
        Map<Activity, Integer> schedule1 = new HashMap<>();
        schedule1.put(a, 0);
        schedule1.put(b, 20);

        System.out.println("Emploi du temps 1 : A=0, B=20");
        System.out.println("  cPrec (A avant B) : " + cPrec.isSatisfied(schedule1));
        System.out.println("  cNotPrec (non cPrec) : " + cNotPrec.isSatisfied(schedule1));
        System.out.println("  cDisj (cPrec ou A in [100,200]) : " + cDisj.isSatisfied(schedule1));
        System.out.println();

        System.out.println("\n------------------------------------\n");
        // Deuxième emploi du temps : A=300, B=0
        Map<Activity, Integer> schedule2 = new HashMap<>();
        schedule2.put(a, 300);
        schedule2.put(b, 0);

        System.out.println("Emploi du temps 2 : A=300, B=0");
        System.out.println("  cPrec (A avant B) : " + cPrec.isSatisfied(schedule2));
        System.out.println("  cNotPrec (non cPrec) : " + cNotPrec.isSatisfied(schedule2));
        System.out.println("  cDisj (cPrec ou A in [100,200]) : " + cDisj.isSatisfied(schedule2));
        System.out.println("\n------------------------------------\n");
    }
}