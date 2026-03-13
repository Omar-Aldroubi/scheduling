package scheduling.basicconstraints;

import scheduling.activities.Activity;

public class PrecedenceConstraint{
    private Activity first;
    private Activity second;

    public PrecedenceConstraint(Activity first, Activity second){
        this.first = first;
        this.second = second;
    }

    public Activity getFirst(){
        return first;
    }

    public Activity getSecond(){
        return second;
    }

    
    public boolean isSatisfied(int date1, int date2){
        return date2 >= date1 + first.getDuration();
    }
}
