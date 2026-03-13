package scheduling.factoredconstraints;

import scheduling.activities.Activity;

/**
 * Classe abstraite factorisant les contraintes binaires
 * entre deux activités.
 */
public abstract class BinaryConstraint {

    protected Activity first;
    protected Activity second;

    /**
     * Construit une contrainte binaire entre deux activités.
     * @param first première activité
     * @param second seconde activité
     */
    public BinaryConstraint(Activity first, Activity second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Retourne la première activité.
     * @return première activité
     */
    public Activity getFirst() {
        return first;
    }

    /**
     * Retourne la seconde activité.
     * @return seconde activité
     */
    public Activity getSecond() {
        return second;
    }

    /**
     * Vérifie si la contrainte est satisfaite pour deux dates données.
     * Méthode laissée abstraite pour que chaque sous-classe définisse sa condition.
     * @param date1 date de début de la première activité
     * @param date2 date de début de la seconde activité
     * @return vrai si la contrainte est satisfaite
     */
    public abstract boolean isSatisfied(int date1, int date2);
}