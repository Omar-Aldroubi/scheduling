package scheduling.factoredtopologicalsort;

import schedulingtests.factoredtopologicalsort.TopologicalSorterTests;

public class TestTopologicalSorter {
    public static void main(String[] args) {
        boolean ok = true;
		TopologicalSorterTests tester = new TopologicalSorterTests();
		ok = ok && tester.testBruteForceSort();
		ok = ok && tester.testSchedule();
		ok = ok && tester.testLinearTimeSort();
		System.out.println(ok ? "All tests passed" : "At least one test failed");
    }
}
