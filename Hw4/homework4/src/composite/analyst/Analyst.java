package composite.analyst;
import composite.Member;
/**
 * Created by Ugur Belge and Halil Duruakan.
 */

public abstract class Analyst extends Member {

    public Analyst(double dailyCost, float taskCompletionTime){
        super(dailyCost,taskCompletionTime);
    }

}
