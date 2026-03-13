package scheduling.solvers;

import java.util.Random;
import schedulingtests.solvers.RandomSchedulerTests;

public class TestRandomScheduler {

    public static void main(String[] args) {

        Random random = new Random(); // graine aléatoire simple

        boolean ok = true;

        RandomSchedulerTests randomSchedulerTester = new RandomSchedulerTests(random);
        ok = ok && randomSchedulerTester.testGenerateOneSchedule();
        ok = ok && randomSchedulerTester.testGenerateSchedule();

        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }
}