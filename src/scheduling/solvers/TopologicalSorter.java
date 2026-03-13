package scheduling.solvers;

import scheduling.activities.Activity;
import scheduling.constraints.BinaryConstraint;

import java.util.*;

/**
 * Classe permettant d'effectuer un tri topologique sur un ensemble
 * d'activités soumises à des contraintes binaires, et de construire
 * un emploi du temps en respectant ces contraintes.
 */
public class TopologicalSorter {

    /**
     * Constructeur par défaut.
     */
    public TopologicalSorter() {
    }

    /**
     * Tri topologique en mode "brute force".
     * À chaque étape, on cherche une activité disponible :
     * une activité dont tous les prédécesseurs sont déjà
     * dans le résultat.
     *
     * @param activities  ensemble des activités
     * @param constraints ensemble des contraintes binaires
     * @return une liste d'activités dans un ordre compatible
     * avec les contraintes, ou null s'il n'existe pas d'ordre.
     */
    public List<Activity> bruteForceSort(Set<Activity> activities,
                                         Set<BinaryConstraint> constraints) {
        Set<Activity> remaining = new HashSet<>(activities);
        List<Activity> res = new ArrayList<>();

        while (!remaining.isEmpty()) {
            Activity available = findAvailable(remaining, res, constraints);
            if (available == null) {
                return null; // pas d'activité disponible -> cycle
            }
            res.add(available);
            remaining.remove(available);
        }

        return res;
    }

    /**
     * Méthode auxiliaire : cherche une activité "disponible"
     * dans remaining, sachant que les activités déjà prises
     * sont dans res.
     */
    private Activity findAvailable(Set<Activity> remaining,
                                   List<Activity> res,
                                   Set<BinaryConstraint> constraints) {
        for (Activity candidate : remaining) {
            boolean ok = true;
            for (BinaryConstraint c : constraints) {
                Activity before = c.getFirst();
                Activity after = c.getSecond();

                // si candidate doit venir après "before"
                // et que "before" n'est pas encore dans res,
                // candidate n'est pas disponible
                if (after.equals(candidate) && !res.contains(before)) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                return candidate;
            }
        }
        return null;
    }

    /**
     * Tri topologique en temps linéaire (algorithme de Kahn).
     *
     * @param activities  ensemble des activités
     * @param constraints ensemble des contraintes binaires
     * @return une liste d'activités dans un ordre compatible
     * ou null s'il n'existe pas d'ordre.
     */
    public List<Activity> linearTimeSort(Set<Activity> activities,
                                         Set<BinaryConstraint> constraints) {
        // nombre de prédécesseurs pour chaque activité
        Map<Activity, Integer> indegree = new HashMap<>();
        // successeurs de chaque activité
        Map<Activity, List<Activity>> succ = new HashMap<>();

        for (Activity a : activities) {
            indegree.put(a, 0);
            succ.put(a, new ArrayList<>());
        }

        // construire le graphe
        for (BinaryConstraint c : constraints) {
            Activity before = c.getFirst();
            Activity after = c.getSecond();

            if (activities.contains(before) && activities.contains(after)) {
                indegree.put(after, indegree.get(after) + 1);
                succ.get(before).add(after);
            }
        }

        // file (ou liste) des activités sans prédécesseur
        Deque<Activity> queue = new ArrayDeque<>();
        for (Activity a : activities) {
            if (indegree.get(a) == 0) {
                queue.add(a);
            }
        }

        List<Activity> res = new ArrayList<>();

        while (!queue.isEmpty()) {
            Activity a = queue.removeFirst();
            res.add(a);

            for (Activity s : succ.get(a)) {
                indegree.put(s, indegree.get(s) - 1);
                if (indegree.get(s) == 0) {
                    queue.addLast(s);
                }
            }
        }

        if (res.size() != activities.size()) {
            return null; // il reste un cycle
        }

        return res;
    }

    /**
     * Construit un emploi du temps en respectant les contraintes.
     * Chaque activité est planifiée au plus tôt, en respectant
     * les activités qui doivent la précéder.
     *
     * @param activities  ensemble des activités
     * @param constraints ensemble des contraintes binaires
     * @param startDate   date de départ minimale
     * @return un emploi du temps (activité -> date de début),
     * ou null s'il n'existe pas de planification valide.
     */
    public Map<Activity, Integer> schedule(Set<Activity> activities,
                                           Set<BinaryConstraint> constraints,
                                           int startDate) {
        // on peut utiliser n'importe quel tri topologique correct
        List<Activity> order = linearTimeSort(activities, constraints);
        if (order == null) {
            return null;
        }

        Map<Activity, Integer> schedule = new HashMap<>();

        for (Activity a : order) {
            int earliest = startDate;

            // toutes les contraintes où "a" est la seconde activité
            for (BinaryConstraint c : constraints) {
                if (c.getSecond().equals(a)) {
                    Activity pred = c.getFirst();
                    Integer predStart = schedule.get(pred);

                    if (predStart == null) {
                        // la précédente n'est pas planifiée -> pas normal si tri ok
                        return null;
                    }

                    int predEnd = predStart + pred.getDuration();
                    if (predEnd > earliest) {
                        earliest = predEnd;
                    }
                }
            }

            schedule.put(a, earliest);
        }

        return schedule;
    }
}