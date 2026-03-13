package scheduling.constraints;

import tests.scheduling.NegationConstraintTests;
import tests.scheduling.DisjunctionConstraintTests;

/**
 * Classe exécutable lançant les tests sur les contraintes composites.
 */
public class TestCompositeConstraints {

    public static void main(String[] args) {
        boolean ok = true;

        NegationConstraintTests negTests = new NegationConstraintTests();
        ok = ok && negTests.testIsSatisfied();

        DisjunctionConstraintTests disjTests = new DisjunctionConstraintTests();
        ok = ok && disjTests.testIsSatisfied();

        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }
}