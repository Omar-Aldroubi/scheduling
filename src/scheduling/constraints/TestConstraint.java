package scheduling.constraints;

import schedulingtests.constraints.MaxSpanConstraintTests;
import schedulingtests.constraints.MeetConstraintTests;
import schedulingtests.constraints.PrecedenceConstraintTests;
import schedulingtests.constraints.PrecedenceConstraintWithGapTests;
import schedulingtests.constraints.UnaryConstraintTests;

/**
 * Classe exécutable pour lancer les tests des contraintes
 * du package scheduling.constraints.
 */
public class TestConstraint {
    public static void main(String[] args) {
        boolean ok = true;

        // Precedence constraints
        PrecedenceConstraintTests precedenceConstraintTester = new PrecedenceConstraintTests();
        ok = ok && precedenceConstraintTester.testExtends();
        ok = ok && precedenceConstraintTester.testImplements();
        ok = ok && precedenceConstraintTester.testGetActivities();
        ok = ok && precedenceConstraintTester.testGetFirst();
        ok = ok && precedenceConstraintTester.testGetSecond();
        ok = ok && precedenceConstraintTester.testIsSatisfied();
        ok = ok && precedenceConstraintTester.testIsSatisfiedMap();

        // Meet constraints
        MeetConstraintTests meetConstraintTester = new MeetConstraintTests();
        ok = ok && meetConstraintTester.testExtends();
        ok = ok && meetConstraintTester.testImplements();
        ok = ok && meetConstraintTester.testGetActivities();
        ok = ok && meetConstraintTester.testGetFirst();
        ok = ok && meetConstraintTester.testGetSecond();
        ok = ok && meetConstraintTester.testIsSatisfied();
        ok = ok && meetConstraintTester.testIsSatisfiedMap();

        // Precedence constraints with gap
        PrecedenceConstraintWithGapTests precedenceConstraintWithGapTester =
                new PrecedenceConstraintWithGapTests();
        ok = ok && precedenceConstraintWithGapTester.testExtends();
        ok = ok && precedenceConstraintWithGapTester.testImplements();
        ok = ok && precedenceConstraintWithGapTester.testGetActivities();
        ok = ok && precedenceConstraintWithGapTester.testGetFirst();
        ok = ok && precedenceConstraintWithGapTester.testGetSecond();
        ok = ok && precedenceConstraintWithGapTester.testIsSatisfied();
        ok = ok && precedenceConstraintWithGapTester.testIsSatisfiedMap();

        // Unary constraints
        UnaryConstraintTests unaryConstraintTester = new UnaryConstraintTests();
        ok = ok && unaryConstraintTester.testImplements();
        ok = ok && unaryConstraintTester.testGetActivities();
        ok = ok && unaryConstraintTester.testIsSatisfiedMap();

        // Max span constraints
        MaxSpanConstraintTests maxSpanConstraintTester = new MaxSpanConstraintTests();
        ok = ok && maxSpanConstraintTester.testImplements();
        ok = ok && maxSpanConstraintTester.testGetActivities();
        ok = ok && maxSpanConstraintTester.testIsSatisfiedMap();

        System.out.println(ok ? "All tests passed" : "At least one test failed");
    }
}