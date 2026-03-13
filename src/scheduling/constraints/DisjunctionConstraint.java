package scheduling.constraints;

import scheduling.activities.Activity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Contrainte représentant la disjonction (OU logique) de deux contraintes :
 * elle est satisfaite si au moins une des deux contraintes est satisfaite.
 */
public class DisjunctionConstraint implements Constraint {

    private Constraint c1;
    private Constraint c2;

    /**
     * Construit une disjonction de deux contraintes.
     *
     * @param c1 première contrainte
     * @param c2 seconde contrainte
     */
    public DisjunctionConstraint(Constraint c1, Constraint c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public Set<Activity> getActivities() {
        // On a besoin de toutes les activités impliquées par les deux contraintes
        Set<Activity> res = new HashSet<>(c1.getActivities());
        res.addAll(c2.getActivities());
        return res;
    }

    @Override
    public boolean isSatisfied(Map<Activity, Integer> schedule) {
        // OU logique
        return c1.isSatisfied(schedule) || c2.isSatisfied(schedule);
    }
}