package scheduling.solvers;

import schedulingtests.solvers.TopologicalSorterTests;
import schedulingtests.solvers.VerifierTests;

/**
 * Classe exécutable pour lancer les tests du package scheduling.solvers.
 */
public class TestSolvers {

    public static void main(String[] args) {
        boolean ok = true;

        TopologicalSorterTests topologicalSortTester = new TopologicalSorterTests();
        ok = ok && topologicalSortTester.testBruteForceSort();
        ok = ok && topologicalSortTester.testSchedule();
        ok = ok && topologicalSortTester.testLinearTimeSort();

        VerifierTests verifierTester = new VerifierTests();
        ok = ok && verifierTester.testUnsatisfied();

        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }
}