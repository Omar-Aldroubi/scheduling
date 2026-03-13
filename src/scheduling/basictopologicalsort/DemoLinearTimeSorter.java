package scheduling.basictopologicalsort;

import scheduling.activities.Activity;
import scheduling.basicconstraints.PrecedenceConstraint;

import java.util.*;

public class DemoLinearTimeSorter {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println(" DEMO : COMPARAISON DES ALGORITHMES");
        System.out.println("====================================\n");

        int n = 2000;
        HashSet<Activity> acts = new HashSet<>();
        HashSet<PrecedenceConstraint> cons = new HashSet<>();

        // créer 2000 activités
        Activity[] tab = new Activity[n];
        for (int i = 0; i < n; i++) {
            tab[i] = new Activity("Activité " + i, 1);
            acts.add(tab[i]);
        }

        // contraintes i -> i+1
        for (int i = 0; i < n-1; i++) {
            cons.add(new PrecedenceConstraint(tab[i], tab[i+1]));
        }

        TopologicalSorter sorter = new TopologicalSorter();

        long t1 = System.currentTimeMillis();
        sorter.bruteForceSort(acts, cons);
        long t2 = System.currentTimeMillis();

        long t3 = System.currentTimeMillis();
        sorter.linearTimeSort(acts, cons);
        long t4 = System.currentTimeMillis();

        System.out.println("Brute force : " + (t2 - t1) + " ms");
        System.out.println("Temps linéaire : " + (t4 - t3) + " ms");


        System.out.println("\n------------------------------------\n");
    }
}