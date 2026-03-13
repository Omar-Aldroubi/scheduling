package scheduling.activities;

public class Activity {
    private String description;
    private int duree ;

    public Activity(String description, int duree) {
        this.description = description;
        this.duree = duree;
    }

    public String getDescription(){
        return description;
    }


    public int getDuration(){
        return duree;
    }

    @Override
    public String toString() {
        return "Activité : " + description + " (" + duree + " min)";
    }



}