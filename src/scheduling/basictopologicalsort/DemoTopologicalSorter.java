package scheduling.basictopologicalsort;

import scheduling.activities.Activity;
import scheduling.basicconstraints.PrecedenceConstraint;

import java.util.*;

/**
 * Classe exécutable illustrant le tri topologique (brute force)
 * et la planification d'activités avec contraintes de précédence.
 *
 * Exemples :
 * - Un scénario possible (lever, petit déjeuner, travail, etc.)
 * - Un scénario impossible (cycle entre activités)
 */
public class DemoTopologicalSorter {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("      DEMO TRI TOPOLOGIQUE");
        System.out.println("====================================\n");

        TopologicalSorter sorter = new TopologicalSorter();

        //EXEMPLE 1 :
        System.out.println("Exemple 1 : \n");

        // Création des activités
        Activity seLever = new Activity("Se lever", 1);
        Activity petitDej = new Activity("Prendre un petit déjeuner", 15);
        Activity douche = new Activity("Prendre une douche", 10);
        Activity dents = new Activity("Se brosser les dents", 3);
        Activity habiller = new Activity("S'habiller", 2);
        Activity travail = new Activity("Aller au travail", 15);

        HashSet<Activity> activities = new HashSet<>(
                Arrays.asList(seLever, petitDej, douche, dents, habiller, travail)
        );

        HashSet<PrecedenceConstraint> constraints = new HashSet<>(Arrays.asList(
                new PrecedenceConstraint(seLever, petitDej),
                new PrecedenceConstraint(seLever, habiller),
                new PrecedenceConstraint(petitDej, dents),
                new PrecedenceConstraint(douche, habiller),
                new PrecedenceConstraint(dents, travail),
                new PrecedenceConstraint(habiller, travail),
                new PrecedenceConstraint(seLever, douche),
                new PrecedenceConstraint(petitDej, travail)
        ));

        ArrayList<Activity> order = sorter.bruteForceSort(activities, constraints);
        System.out.println("Ordre obtenu :");
        if (order != null) {
            int i = 1;
            for (Activity a : order) {
                System.out.println("  " + (i++) + ". " + a.getDescription() + " (" + a.getDuration() + " min)");
            }
        } else {
            System.out.println("  Aucun ordre possible (cycle détecté).");
        }

        // Planification
        HashMap<Activity, Integer> schedule = sorter.schedule(activities, constraints, 500);
        System.out.println("\nPlanning :");
        if (schedule != null) {
            for (Activity a : order) {
                System.out.printf("  %-30s → %d%n", a.getDescription(), schedule.get(a));
            }
        } else {
            System.out.println("  Impossible de planifier (cycle détecté).");
        }

        //EXEMPLE 2 : scénario impossible
        System.out.println("\nExemple 2 :\n");

        Activity sujet = new Activity("Prendre connaissance du sujet d'examen", 30);
        Activity reviser = new Activity("Réviser", 300);
        Activity entrer = new Activity("Entrer dans la salle d'examen", 8);

        HashSet<Activity> badActs = new HashSet<>(Arrays.asList(sujet, reviser, entrer));

        HashSet<PrecedenceConstraint> badConstraints = new HashSet<>(Arrays.asList(
                new PrecedenceConstraint(reviser, entrer),
                new PrecedenceConstraint(entrer, sujet),
                new PrecedenceConstraint(sujet, reviser)
        ));

        ArrayList<Activity> badOrder = sorter.bruteForceSort(badActs, badConstraints);
        System.out.println("Ordre obtenu : " + badOrder);

        HashMap<Activity, Integer> badSchedule = sorter.schedule(badActs, badConstraints, 500);
        System.out.println("Planning : " + badSchedule);

        System.out.println("\n------------------------------------\n");
    }
}
