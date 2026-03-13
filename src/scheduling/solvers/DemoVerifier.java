package scheduling.solvers;

import scheduling.activities.Activity;
import scheduling.constraints.Constraint;
import scheduling.constraints.PrecedenceConstraint;
import scheduling.constraints.MeetConstraint;
import scheduling.constraints.MaxSpanConstraint;
import scheduling.constraints.UnaryConstraint;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Classe exécutable illustrant l'utilisation de Verifier.
 * Elle construit des activités, des contraintes et deux emplois du temps :
 * - un qui satisfait toutes les contraintes ;
 * - un qui viole au moins une contrainte.
 */
public class DemoVerifier {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("          DEMO VERIFIER");
        System.out.println("====================================\n");

        //1. Création des activités
        Activity cours = new Activity("Cours Java", 120);     // 2h
        Activity td    = new Activity("TD Java", 120);        // 2h
        Activity sport = new Activity("Sport", 60);           // 1h

        Set<Activity> activities = new HashSet<>();
        activities.add(cours);
        activities.add(td);
        activities.add(sport);

        //2. Création des contraintes
        Set<Constraint> constraints = new HashSet<>();

        // TD après le cours
        constraints.add(new PrecedenceConstraint(cours, td));

        // Sport commence quand le TD se termine
        constraints.add(new MeetConstraint(td, sport));

        // Les trois tiennent dans une plage de 8h (480 min)
        constraints.add(new MaxSpanConstraint(activities, 480));

        // Cours doit commencer entre 8h et 10h (480 à 600)
        constraints.add(new UnaryConstraint(cours, 480, 600));

        Verifier verifier = new Verifier(constraints);

        //3. Emploi du temps satisfaisant toutes les contraintes
        Map<Activity, Integer> goodSchedule = new HashMap<>();

        // Cours : 9h00 -> 540
        goodSchedule.put(cours, 540);
        // TD juste après : 11h00 -> 660
        goodSchedule.put(td, 660);
        // Sport juste après le TD : 13h00 -> 780
        goodSchedule.put(sport, 780);

        System.out.println("Emploi du temps 1 (bon) :");
        printSchedule(goodSchedule);
        System.out.println("Contraintes non satisfaites : "
                + verifier.unsatisfied(goodSchedule).size());
        System.out.println();

        //4. Emploi du temps qui viole au moins une contraint
        Map<Activity, Integer> badSchedule = new HashMap<>();

        // Cours plus tard que permis (ex: 12h00 -> 720)
        badSchedule.put(cours, 720);
        // TD avant le cours pour violer la précédence
        badSchedule.put(td, 500);
        // Sport n'importe où
        badSchedule.put(sport, 800);

        System.out.println("Emploi du temps 2 (mauvais) :");
        printSchedule(badSchedule);
        System.out.println("Contraintes non satisfaites : "
                + verifier.unsatisfied(badSchedule).size());
        System.out.println();
    }

    /**
     * Affiche un emploi du temps (activité → date).
     */
    private static void printSchedule(Map<Activity, Integer> schedule) {
        for (Map.Entry<Activity, Integer> entry : schedule.entrySet()) {
            Activity a = entry.getKey();
            Integer date = entry.getValue();
            System.out.println("  " + a.getDescription() + " → " + date);

            System.out.println("------------------------------------\n");
        }
    }
}