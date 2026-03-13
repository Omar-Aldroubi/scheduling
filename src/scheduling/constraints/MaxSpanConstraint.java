package scheduling.constraints;

import scheduling.activities.Activity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contrainte imposant que toutes les activités données
 * aient lieu dans une plage de temps d'une durée au plus
 * égale à maxSpan.
 */
public class MaxSpanConstraint implements Constraint {

    private Set<Activity> activities;
    private int maxSpan;

    /**
     * Construit une contrainte de type "max span".
     *
     * @param activities ensemble d'activités concernées
     * @param maxSpan    durée maximale de la plage de temps (>= 0)
     */
    public MaxSpanConstraint(Set<Activity> activities, int maxSpan) {
        this.activities = new HashSet<>(activities);
        this.maxSpan = maxSpan;
    }

    @Override
    public Set<Activity> getActivities() {
        return new HashSet<>(activities);
    }

    @Override
    public boolean isSatisfied(Map<Activity, Integer> schedule) {
        if (activities.isEmpty()) {
            // Par convention : plage de durée 0 -> toujours satisfaite.
            return true;
        }

        Integer minStart = null;
        Integer maxEnd = null;

        for (Activity a : activities) {
            Integer start = schedule.get(a);
            if (start == null) {
                return false;
            }
            int end = start + a.getDuration();

            if (minStart == null || start < minStart) {
                minStart = start;
            }
            if (maxEnd == null || end > maxEnd) {
                maxEnd = end;
            }
        }

        int span = maxEnd - minStart;
        return span <= maxSpan;
    }
}