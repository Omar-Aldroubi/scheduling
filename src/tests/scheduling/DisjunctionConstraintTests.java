package tests.scheduling;

import scheduling.activities.Activity;
import scheduling.constraints.Constraint;
import scheduling.constraints.DisjunctionConstraint;
import scheduling.constraints.PrecedenceConstraint;
import scheduling.constraints.UnaryConstraint;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * Tests simples pour DisjunctionConstraint.
 */
public class DisjunctionConstraintTests {

    public boolean testIsSatisfied() {
        Activity a = new Activity("A", 10);
        Activity b = new Activity("B", 5);

        Constraint prec = new PrecedenceConstraint(a, b);
        Constraint unaryA = new UnaryConstraint(a, 100, 200);
        Constraint disj = new DisjunctionConstraint(prec, unaryA);

        Map<Activity, Integer> schedule = new HashMap<>();

        // Cas 1 : A=0, B=20 -> precedence vraie, unary fausse -> disj vraie
        schedule.put(a, 0);
        schedule.put(b, 20);
        if (!prec.isSatisfied(schedule)) return false;
        if (unaryA.isSatisfied(schedule)) return false;
        if (!disj.isSatisfied(schedule)) return false;

        // Cas 2 : A=150, B=0 -> precedence fausse, unary vraie -> disj vraie
        schedule.put(a, 150);
        schedule.put(b, 0);
        if (prec.isSatisfied(schedule)) return false;
        if (!unaryA.isSatisfied(schedule)) return false;
        if (!disj.isSatisfied(schedule)) return false;

        // Cas 3 : A=300, B=0 -> precedence fausse, unary fausse -> disj fausse
        schedule.put(a, 300);
        schedule.put(b, 0);
        if (prec.isSatisfied(schedule)) return false;
        if (unaryA.isSatisfied(schedule)) return false;
        if (disj.isSatisfied(schedule)) return false;

        return true;
    }
}