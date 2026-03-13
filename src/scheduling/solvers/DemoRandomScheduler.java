package scheduling.solvers;

import scheduling.activities.Activity;
import scheduling.constraints.Constraint;
import scheduling.constraints.PrecedenceConstraint;
import scheduling.constraints.MeetConstraint;
import scheduling.constraints.MaxSpanConstraint;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;

public class DemoRandomScheduler {

    private static void printSchedule(Map<Activity, Integer> schedule) {
        if (schedule == null) {
            System.out.println("  Aucun emploi du temps.");
            return;
        }
        for (Map.Entry<Activity, Integer> entry : schedule.entrySet()) {
            Activity a = entry.getKey();
            Integer date = entry.getValue();
            System.out.println("  " + a.getDescription() + " → " + date);
        }
    }

    public static void main(String[] args) {


        System.out.println("====================================");
        System.out.println("     DEMO SCHEDULER ALÉATOIRE");
        System.out.println("====================================\n");
        Random random = new Random();
        RandomScheduler scheduler = new RandomScheduler(random);

        // Quelques activités
        Activity cours = new Activity("Cours Java", 120);
        Activity td = new Activity("TD Java", 120);
        Activity sport = new Activity("Sport", 60);

        Set<Activity> activities = new HashSet<>();
        activities.add(cours);
        activities.add(td);
        activities.add(sport);

        // Quelques contraintes
        Set<Constraint> constraints = new HashSet<>();

        // TD après le cours
        constraints.add(new PrecedenceConstraint(cours, td));
        // Sport doit commencer quand le TD se termine
        constraints.add(new MeetConstraint(td, sport));
        // Les trois doivent tenir dans un intervalle de 8h (480 min)
        constraints.add(new MaxSpanConstraint(activities, 480));

        Verifier verifier = new Verifier(constraints);

        int minDate = 8 * 60;   // 8h00 = 480
        int maxDate = 20 * 60;  // 20h00 = 1200

        System.out.println("\nUn emploi du temps aléatoire :");
        Map<Activity, Integer> randomSchedule =
                scheduler.generateOneSchedule(activities, minDate, maxDate);
        printSchedule(randomSchedule);
        System.out.println("Contraintes non satisfaites : "
                + verifier.unsatisfied(randomSchedule).size());

        System.out.println("\nMeilleur emploi du temps trouvé :");
        Map<Activity, Integer> bestSchedule =
                scheduler.generateSchedule(activities, constraints, minDate, maxDate, 1000);
        printSchedule(bestSchedule);
        System.out.println("Contraintes non satisfaites (meilleur) : "
                + verifier.unsatisfied(bestSchedule).size());

        System.out.println("\n------------------------------------\n");
    }
}