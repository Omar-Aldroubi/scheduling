package scheduling.solvers;

import scheduling.activities.Activity;
import scheduling.constraints.Constraint;

import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.Random;
import java.util.HashSet;

public class RandomScheduler {

    private Random random;

    /**
     * Constructeur.
     * @param random générateur aléatoire utilisé par la classe
     */
    public RandomScheduler(Random random) {
        this.random = random;
    }

    /**
     * Génère un emploi du temps aléatoire.
     *
     * @param activities ensemble des activités
     * @param minDate date minimale (incluse)
     * @param maxDate date maximale (incluse)
     * @return une map activité → date de début
     */
    public Map<Activity, Integer> generateOneSchedule(Set<Activity> activities,
                                                      int minDate,
                                                      int maxDate) {
        Map<Activity, Integer> schedule = new HashMap<>();

        int span = maxDate - minDate;
        if (span < 0) {
            // au cas où, on swap (normalement les tests ne donnent pas ce cas)
            int tmp = minDate;
            minDate = maxDate;
            maxDate = tmp;
            span = maxDate - minDate;
        }

        for (Activity a : activities) {
            int date = minDate + random.nextInt(span + 1); // [minDate, maxDate]
            schedule.put(a, date);
        }

        return schedule;
    }

    /**
     * Génère plusieurs emplois du temps et garde celui qui
     * satisfait le plus de contraintes.
     *
     * @param activities ensemble des activités
     * @param constraints ensemble des contraintes
     * @param minDate date minimale
     * @param maxDate date maximale
     * @param tries nombre de tirages aléatoires
     * @return un emploi du temps "meilleur trouvé" (jamais null si tries > 0)
     */
    public Map<Activity, Integer> generateSchedule(Set<Activity> activities,
                                                   Set<Constraint> constraints,
                                                   int minDate,
                                                   int maxDate,
                                                   int tries) {
        if (tries <= 0) {
            return null;
        }

        Verifier verifier = new Verifier(constraints);

        Map<Activity, Integer> bestSchedule = null;
        int bestSatisfied = -1;

        for (int i = 0; i < tries; i++) {
            Map<Activity, Integer> schedule = generateOneSchedule(activities, minDate, maxDate);

            Set<Constraint> unsatisfied = verifier.unsatisfied(schedule);
            int satisfied = constraints.size() - unsatisfied.size();

            if (satisfied > bestSatisfied) {
                bestSatisfied = satisfied;
                bestSchedule = schedule;

                if (bestSatisfied == constraints.size()) {
                }
            }
        }

        return bestSchedule;
    }
}