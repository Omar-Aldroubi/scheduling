package scheduling.activities;


public class DemoActivity {

    /**
     * lance le demo du classe Activity
     */
    public static void main(String[] args){
        System.out.println("\n");
        System.out.println("====================================");
        System.out.println("          DEMO ACTIVITÉS");
        System.out.println("====================================\n");

        Activity activity1 = new Activity("Description de l'activité 1", 2);
        Activity activity2 = new Activity("Description de l'activité 2", 2);

        System.out.println(activity1);
        System.out.println(activity2);
        System.out.println("\n------------------------------------\n");
    }
}


