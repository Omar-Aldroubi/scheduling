package scheduling.constraints;

import scheduling.activities.Activity;

/**
 * Contrainte de type "meet" :
 * la seconde activité commence exactement à la fin de la première.
 */
public class MeetConstraint extends BinaryConstraint {

    public MeetConstraint(Activity first, Activity second) {
        super(first, second);
    }

    @Override
    public boolean isSatisfied(int date1, int date2) {
        return date2 == date1 + first.getDuration();
    }
}