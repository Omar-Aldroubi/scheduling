package scheduling.constraints;

import scheduling.activities.Activity;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Classe abstraite factorisant les contraintes binaires
 * entre deux activités.
 */
public abstract class BinaryConstraint implements Constraint {

    protected Activity first;
    protected Activity second;

    /**
     * Construit une contrainte binaire entre deux activités.
     *
     * @param first  première activité
     * @param second seconde activité
     */
    public BinaryConstraint(Activity first, Activity second) {
        this.first = first;
        this.second = second;
    }

    /**
     * Retourne la première activité.
     */
    public Activity getFirst() {
        return first;
    }

    /**
     * Retourne la seconde activité.
     */
    public Activity getSecond() {
        return second;
    }

    /**
     * Vérifie la contrainte en fonction des dates de début
     * des deux activités seulement.
     *
     * @param date1 date de début de la première activité
     * @param date2 date de début de la seconde activité
     * @return true si la contrainte est satisfaite
     */
    public abstract boolean isSatisfied(int date1, int date2);

    /**
     * Retourne l'ensemble des activités concernées par la contrainte.
     */
    @Override
    public Set<Activity> getActivities() {
        Set<Activity> set = new HashSet<>();
        set.add(first);
        set.add(second);
        return set;
    }

    /**
     * Vérifie la contrainte à partir d'un emploi du temps complet.
     * On récupère les dates de début des deux activités, puis
     * on appelle la version à deux entiers.
     */
    @Override
    public boolean isSatisfied(Map<Activity, Integer> schedule) {
        Integer d1 = schedule.get(first);
        Integer d2 = schedule.get(second);
        if (d1 == null || d2 == null) {
            // Si l'une des dates manque, on considère que ce n'est pas satisfait.
            return false;
        }
        return isSatisfied(d1, d2);
    }
}