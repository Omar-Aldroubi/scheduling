package scheduling.factoredconstraints;

import scheduling.activities.Activity;

/**
 * Contrainte de précédence simple :
 * la seconde activité doit commencer après la fin de la première.
 */
public class PrecedenceConstraint extends BinaryConstraint {

    /**
     * Construit une contrainte de précédence.
     * @param first première activité
     * @param second seconde activité
     */
    public PrecedenceConstraint(Activity first, Activity second) {
        super(first, second);
    }

    /**
     * La contrainte est satisfaite si date2 >= date1 + durée(first).
     */
    @Override
    public boolean isSatisfied(int date1, int date2) {
        return date2 >= date1 + first.getDuration();
    }
}