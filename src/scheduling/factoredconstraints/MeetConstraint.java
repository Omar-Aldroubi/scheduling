package scheduling.factoredconstraints;

import scheduling.activities.Activity;

/**
 * Contrainte de type "meet" :
 * la seconde activité commence exactement à la fin de la première.
 */
public class MeetConstraint extends BinaryConstraint {

    /**
     * Construit une contrainte "meet" entre deux activités.
     * @param first première activité
     * @param second seconde activité
     */
    public MeetConstraint(Activity first, Activity second) {
        super(first, second);
    }

    /**
     * La contrainte est satisfaite si date2 = date1 + durée(first).
     */
    @Override
    public boolean isSatisfied(int date1, int date2) {
        return date2 == date1 + first.getDuration();
    }
}