package tests.scheduling;

import scheduling.activities.Activity;
import scheduling.constraints.Constraint;
import scheduling.constraints.NegationConstraint;
import scheduling.constraints.UnaryConstraint;

import java.util.HashMap;
import java.util.Map;

/**
 * Tests simples pour NegationConstraint.
 */
public class NegationConstraintTests {

    public boolean testIsSatisfied() {
        Activity a = new Activity("A", 10);
        Constraint base = new UnaryConstraint(a, 100, 200);
        Constraint neg = new NegationConstraint(base);

        Map<Activity, Integer> schedule = new HashMap<>();
        schedule.put(a, 150); // satisfait la contrainte de base

        // base : true, negation : false
        if (!base.isSatisfied(schedule)) return false;
        if (neg.isSatisfied(schedule)) return false;

        schedule.put(a, 50); // viole la contrainte de base

        // base : false, negation : true
        if (base.isSatisfied(schedule)) return false;
        if (!neg.isSatisfied(schedule)) return false;

        return true;
    }
}