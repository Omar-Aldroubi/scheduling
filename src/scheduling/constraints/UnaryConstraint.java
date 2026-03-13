package scheduling.constraints;

import scheduling.activities.Activity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contrainte unaire sur la date de début d'une activité :
 * l'activité doit commencer dans un intervalle [minDate, maxDate].
 */
public class UnaryConstraint implements Constraint {

    private Activity activity;
    private int minDate;
    private int maxDate;

    /**
     * Construit une contrainte unaire.
     *
     * @param activity activité concernée
     * @param minDate  date minimale (incluse)
     * @param maxDate  date maximale (incluse)
     */
    public UnaryConstraint(Activity activity, int minDate, int maxDate) {
        this.activity = activity;
        this.minDate = minDate;
        this.maxDate = maxDate;
    }

    @Override
    public Set<Activity> getActivities() {
        Set<Activity> set = new HashSet<>();
        set.add(activity);
        return set;
    }

    @Override
    public boolean isSatisfied(Map<Activity, Integer> schedule) {
        Integer d = schedule.get(activity);
        if (d == null) {
            return false;
        }
        return d >= minDate && d <= maxDate;
    }
}