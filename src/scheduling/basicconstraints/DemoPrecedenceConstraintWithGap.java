package scheduling.basicconstraints;

import scheduling.activities.Activity;

public class DemoPrecedenceConstraintWithGap {

    public static void main(String[] args) {

        Activity cuisiner = new Activity("Cuisiner le plat", 30);
        Activity manger = new Activity("Manger le plat", 20);

        PrecedenceConstraintWithGap contrainte = new PrecedenceConstraintWithGap(cuisiner, manger, 0, 5);

        int startCuisiner = 100;

        System.out.println("Cas 1 (exactement après la fin de la cuisine) : " + contrainte.isSatisfied(startCuisiner, 130));  // vrai

        System.out.println("Cas 2 (3 min après la fin de la cuisine) : " + contrainte.isSatisfied(startCuisiner, 133));  // vrai

        System.out.println("Cas 3 (10 min après la fin de la cuisine) : " + contrainte.isSatisfied(startCuisiner, 140));  // faux

        System.out.println("Cas 4 (pendant la cuisine) : " + contrainte.isSatisfied(startCuisiner, 120));  // faux
    }
}