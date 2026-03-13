package scheduling.solvers;

import scheduling.activities.Activity;
import scheduling.constraints.Constraint;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Vérifie quelles contraintes sont satisfaites ou non
 * pour un emploi du temps donné.
 */
public class Verifier {

    private Set<Constraint> constraints;

    /**
     * Construit un vérificateur pour un ensemble de contraintes.
     *
     * @param constraints ensemble de contraintes à vérifier
     */
    public Verifier(Set<Constraint> constraints) {
        this.constraints = new HashSet<>(constraints);
    }

    /**
     * Retourne l'ensemble des contraintes vérifiées par cet objet.
     *
     * @return copie de l'ensemble des contraintes
     */
    public Set<Constraint> getConstraints() {
        return new HashSet<>(constraints);
    }

    /**
     * Retourne l'ensemble des contraintes qui ne sont pas satisfaites
     * par l'emploi du temps donné.
     *
     * @param schedule emploi du temps (activité -> date de début)
     * @return ensemble des contraintes non satisfaites
     */
    public Set<Constraint> unsatisfied(Map<Activity, Integer> schedule) {
        Set<Constraint> res = new HashSet<>();
        for (Constraint c : constraints) {
            if (!c.isSatisfied(schedule)) {
                res.add(c);
            }
        }
        return res;
    }
}