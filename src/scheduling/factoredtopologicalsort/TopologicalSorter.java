package scheduling.factoredtopologicalsort;

import scheduling.activities.Activity;
import scheduling.factoredconstraints.PrecedenceConstraint;

import java.util.*;


public class TopologicalSorter {

    public TopologicalSorter() {}

    /**
     *
     * @param activities
     * @param constraints
     * @return
     */

    public ArrayList<Activity> bruteForceSort(HashSet<Activity> activities,
                                              HashSet<PrecedenceConstraint> constraints) {
        HashSet<Activity> Oc = new HashSet<>(activities);
        ArrayList<Activity> res = new ArrayList<>();

        while (!Oc.isEmpty()) {
            Activity available = findAvailable(Oc, res, constraints);
            if (available == null) {
                return null;
            }
            res.add(available);
            Oc.remove(available);
        }

        return res;
    }

    /**
     *
     * @param Oc
     * @param res
     * @param constraints
     * @return
     */
    private Activity findAvailable(HashSet<Activity> Oc, ArrayList<Activity> res,
                                   HashSet<PrecedenceConstraint> constraints) {

        for (Activity candidate : Oc) {
            boolean ok = true;
            for (PrecedenceConstraint c : constraints) {
                Activity before = c.getFirst();
                Activity after = c.getSecond();

                if (after.equals(candidate) && !res.contains(before)) {
                    ok = false;
                    break;
                }
            }
            if (ok) return candidate;
        }
        return null;
    }

    /**
     *
     * @param activities
     * @param constraints
     * @param startDate
     * @return
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
}
