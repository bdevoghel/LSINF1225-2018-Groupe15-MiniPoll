package p.poll.model.struc;

/**
 * Created by User on 21-04-18.
 */

public class LimitedNumber {
    private static int MAX;
    private int number;

    public LimitedNumber(int max){
        MAX=max;
    }

    public void increment(){
        if(number<MAX){
            number++;
        }
        else {
            number=0;
        }
    }
    public int get() {
        return number;
    }
}
