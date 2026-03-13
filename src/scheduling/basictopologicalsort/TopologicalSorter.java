package scheduling.basictopologicalsort;

import scheduling.activities.Activity;
import scheduling.basicconstraints.PrecedenceConstraint;

import java.util.*;

public class TopologicalSorter {

    public TopologicalSorter() {}

    /**
     * Tri topologique brute force.
     *
     * @param activities   ensemble d'activités
     * @param constraints  ensemble de contraintes de précédence
     * @return une liste d'activités dans un ordre compatible avec les contraintes,
     *         ou null s'il n'existe pas d'ordonnancement.
     */
    public ArrayList<Activity> bruteForceSort(HashSet<Activity> activities,
                                              HashSet<PrecedenceConstraint> constraints) {
        HashSet<Activity> Oc = new HashSet<>(activities);
        ArrayList<Activity> res = new ArrayList<>();

        while (!Oc.isEmpty()) {
            Activity available = findAvailable(Oc, res, constraints);
            if (available == null) {
                // aucune activité disponible -> cycle
                return null;
            }
            res.add(available);
            Oc.remove(available);
        }

        return res;
    }

    /**
     * Méthode auxiliaire : cherche une activité "disponible"
     * dans Oc, sachant que les activités déjà placées sont dans res.
     */
    private Activity findAvailable(HashSet<Activity> Oc, ArrayList<Activity> res,
                                   HashSet<PrecedenceConstraint> constraints) {

        for (Activity candidate : Oc) {
            boolean ok = true;
            for (PrecedenceConstraint c : constraints) {
                Activity before = c.getFirst();
                Activity after = c.getSecond();

                // si candidate doit venir après "before" et que "before" n'est pas encore dans res
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
     * Construction d'un emploi du temps en planifiant chaque activité "au plus tôt"
     * selon l'ordre retourné par bruteForceSort.
     *
     * @param activities   ensemble d'activités
     * @param constraints  ensemble de contraintes de précédence
     * @param startDate    date de départ minimale
     * @return une map activité -> date de début, ou null si impossible.
     */
    public HashMap<Activity, Integer> schedule(HashSet<Activity> activities,
                                               HashSet<PrecedenceConstraint> constraints,
                                               int startDate) {
        ArrayList<Activity> order = bruteForceSort(activities, constraints);
        if (order == null) return null;

        HashMap<Activity, Integer> schedule = new HashMap<>();

        for (Activity a : order) {
            int earliest = startDate;
            for (PrecedenceConstraint c : constraints) {
                if (c.getSecond().equals(a)) {
                    Activity pred = c.getFirst();
                    Integer predStart = schedule.get(pred);
                    if (predStart == null) return null;
                    int predEnd = predStart + pred.getDuration();
                    if (predEnd > earliest) earliest = predEnd;
                }
            }
            schedule.put(a, earliest);
        }

        return schedule;
    }

    /**
     * Tri topologique en temps linéaire (algorithme de Kahn).
     *
     * @param activities   ensemble d'activités
     * @param constraints  ensemble de contraintes de précédence
     * @return une liste d'activités dans un ordre compatible,
     *         ou null s'il n'existe pas d'ordonnancement.
     */
    public ArrayList<Activity> linearTimeSort(HashSet<Activity> activities,
                                              HashSet<PrecedenceConstraint> constraints) {

        // nombre de prédécesseurs pour chaque activité
        HashMap<Activity, Integer> nbPredecessors = new HashMap<>();
        // liste des successeurs pour chaque activité
        HashMap<Activity, ArrayList<Activity>> successeurs = new HashMap<>();

        // initialisation des dictionnaires
        for (Activity a : activities) {
            nbPredecessors.put(a, 0);
            successeurs.put(a, new ArrayList<Activity>());
        }

        // remplir nbPredecessors et successeurs à partir des contraintes
        for (PrecedenceConstraint c : constraints) {
            Activity before = c.getFirst();
            Activity after = c.getSecond();

            // on ne considère que les activités présentes dans "activities"
            if (activities.contains(before) && activities.contains(after)) {
                nbPredecessors.put(after, nbPredecessors.get(after) + 1);
                successeurs.get(before).add(after);
            }
        }

        // L = liste des activités sans prédécesseur
        ArrayList<Activity> L = new ArrayList<>();
        for (Activity a : activities) {
            if (nbPredecessors.get(a) == 0) {
                L.add(a);
            }
        }

        ArrayList<Activity> res = new ArrayList<>();

        // boucle principale
        while (!L.isEmpty()) {
            // on peut prendre n'importe quel élément de L
            Activity o = L.remove(L.size() - 1); // par exemple le dernier
            res.add(o);

            for (Activity o2 : successeurs.get(o)) {
                nbPredecessors.put(o2, nbPredecessors.get(o2) - 1);
                if (nbPredecessors.get(o2) == 0) {
                    L.add(o2);
                }
            }
        }

        // si on n'a pas placé toutes les activités, il y a un cycle
        if (res.size() != activities.size()) {
            return null;
        }

        return res;
    }
}