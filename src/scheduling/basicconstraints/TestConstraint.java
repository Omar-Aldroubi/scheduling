package scheduling.basicconstraints;

import schedulingtests.basicconstraints.MeetConstraintTests;
import schedulingtests.basicconstraints.PrecedenceConstraintTests;
import schedulingtests.basicconstraints.PrecedenceConstraintWithGapTests;
public class TestConstraint {
    public static void main(String[] args) {
        boolean ok = true;

        PrecedenceConstraintTests precedenceTester = new PrecedenceConstraintTests();
        ok = ok && precedenceTester.testGetFirst();
        ok = ok && precedenceTester.testGetSecond();
        ok = ok && precedenceTester.testIsSatisfied();

        MeetConstraintTests meetTester = new MeetConstraintTests();
        ok = ok && meetTester.testGetFirst();
        ok = ok && meetTester.testGetSecond();
        ok = ok && meetTester.testIsSatisfied();

        PrecedenceConstraintWithGapTests precedenceConstraintWithGapTester = new PrecedenceConstraintWithGapTests();
        ok = ok && precedenceConstraintWithGapTester.testExtends();
        ok = ok && precedenceConstraintWithGapTester.testGetFirst();
        ok = ok && precedenceConstraintWithGapTester.testGetSecond();
        ok = ok && precedenceConstraintWithGapTester.testIsSatisfied();

        System.out.println(ok ? "\nAll tests passed" : "\nAt least one test failed");
    }
}
