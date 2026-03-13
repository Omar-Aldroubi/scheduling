package scheduling.factoredconstraints;

import scheduling.activities.Activity;

/**
 * Contrainte de précédence avec un délai minimal et maximal
 * entre la fin de la première activité et le début de la seconde.
 */
public class PrecedenceConstraintWithGap extends PrecedenceConstraint {

    private int delayMin;
    private int delayMax;

    /**
     * @param first première activité
     * @param second seconde activité
     * @param delayMin délai minimal (>= 0)
     * @param delayMax délai maximal
     */
    public PrecedenceConstraintWithGap(Activity first, Activity second,
                                       int delayMin, int delayMax) {
        super(first, second);
        this.delayMin = delayMin;
        this.delayMax = delayMax;
    }

    /**
     * La contrainte est satisfaite si start2 est entre
     * end1 + delayMin et end1 + delayMax (inclus).
     */
    @Override
    public boolean isSatisfied(int start1, int start2) {
        int end1 = start1 + getFirst().getDuration();
        return start2 >= end1 + delayMin && start2 <= end1 + delayMax;
    }
}