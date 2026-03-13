package scheduling.constraints;

import scheduling.activities.Activity;

import java.util.Map;
import java.util.Set;

/**
 * Interface générale pour une contrainte portant sur
 * une ou plusieurs activités.
 */
public interface Constraint {

    /**
     * Retourne l'ensemble des activités concernées par la contrainte.
     *
     * @return ensemble des activités de la contrainte
     */
    Set<Activity> getActivities();

    /**
     * Indique si la contrainte est satisfaite par l'emploi du temps donné.
     * La map associe à chaque activité une date de début.
     *
     * @param schedule emploi du temps (activité -> date de début)
     * @return true si la contrainte est satisfaite, false sinon
     */
    boolean isSatisfied(Map<Activity, Integer> schedule);
}