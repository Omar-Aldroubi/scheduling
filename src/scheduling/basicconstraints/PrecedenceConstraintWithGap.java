package scheduling.basicconstraints;
import scheduling.activities.Activity;

public class PrecedenceConstraintWithGap extends PrecedenceConstraint{
    
    private int delayMin;
    private int delayMax;

    public PrecedenceConstraintWithGap(Activity first, Activity second, int delayMin, int delayMax){
        super(first, second);
        this.delayMin = delayMin;
        this.delayMax = delayMax;
    }

    @Override
    public boolean isSatisfied(int start1, int start2){
        int end1 = start1 + getFirst().getDuration();
        //int delay = start2 - end1;
        return start2 >= end1 + delayMin && start2 <= end1 + delayMax;
        //return delay >= delayMin && delay <= delayMax;
    }




}