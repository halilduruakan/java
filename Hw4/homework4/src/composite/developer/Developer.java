package composite.developer;
import composite.Member;
/**
 * Created by Ugur Belge and Halil Duruakan.
 */

public abstract class Developer extends Member {
    public Developer(double dailyCost, float taskCompletionTime) {
        super(dailyCost, taskCompletionTime);
    }
}
