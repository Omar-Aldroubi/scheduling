package scheduling.constraints;

import scheduling.activities.Activity;

import java.util.Map;
import java.util.Set;

/**
 * Contrainte représentant la négation d'une autre contrainte :
 * elle est satisfaite si et seulement si la contrainte interne
 * ne l'est pas.
 */
public class NegationConstraint implements Constraint {

    private Constraint inner;

    /**
     * Construit la négation d'une contrainte.
     *
     * @param inner contrainte à nier
     */
    public NegationConstraint(Constraint inner) {
        this.inner = inner;
    }

    @Override
    public Set<Activity> getActivities() {
        // On a besoin des mêmes activités que la contrainte interne
        return inner.getActivities();
    }

    @Override
    public boolean isSatisfied(Map<Activity, Integer> schedule) {
        // Négation logique
        return !inner.isSatisfied(schedule);
    }
}