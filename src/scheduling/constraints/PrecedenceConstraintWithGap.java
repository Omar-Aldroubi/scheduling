package scheduling.constraints;

import scheduling.activities.Activity;

/**
 * Représente une contrainte de précédence assortie d’un délai minimal et maximal.
 * Exemple : si l’activité A dure 10 minutes et que le délai minimal est 5
 * minutes, alors la seconde activité ne peut commencer qu’au plus tôt 15
 * minutes après le début de A.
 */
public class PrecedenceConstraintWithGap extends PrecedenceConstraint {

    private int delayMin;
    private int delayMax;

    /**
     * Construit une contrainte de précédence avec délai.
     *
     * @param first     la première activité (celle qui doit précéder)
     * @param second    la seconde activité (celle qui doit suivre)
     * @param delayMin  délai minimal entre la fin de la première activité et le début de la seconde
     * @param delayMax  délai maximal entre la fin de la première activité et le début de la seconde
     */
    public PrecedenceConstraintWithGap(Activity first, Activity second, int delayMin, int delayMax) {
        super(first, second);
        this.delayMin = delayMin;
        this.delayMax = delayMax;
    }

    /**
     * Vérifie si la contrainte est satisfaite pour les dates de début données.
     *
     * @param start1 date de début de la première activité
     * @param start2 date de début de la seconde activité
     * @return {@code true} si la seconde activité commence entre
     *         (start1 + durée1 + delayMin) et (start1 + durée1 + delayMax),
     *         {@code false} sinon
     */
    @Override
    public boolean isSatisfied(int start1, int start2) {
        int end1 = start1 + getFirst().getDuration();
        return start2 >= end1 + delayMin && start2 <= end1 + delayMax;
    }
}