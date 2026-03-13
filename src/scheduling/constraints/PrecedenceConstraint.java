package scheduling.constraints;

import scheduling.activities.Activity;

/**
 * Contrainte de précédence simple :
 * la seconde activité doit commencer après la fin de la première.
 */
public class PrecedenceConstraint extends BinaryConstraint {

    public PrecedenceConstraint(Activity first, Activity second) {
        super(first, second);
    }

    @Override
    public boolean isSatisfied(int date1, int date2) {
        return date2 >= date1 + first.getDuration();
    }
}