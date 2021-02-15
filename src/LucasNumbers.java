import java.time.Clock;
import java.time.Instant;
import java.util.ArrayList;

public class LucasNumbers {

    public static void main(String args[]){
        Clock clock = Clock.systemDefaultZone();
        Instant instant = clock.instant();

        int numLucasNumbers = 45;

        ArrayList<Long> times = new ArrayList<Long>();



        System.out.println("The first " + numLucasNumbers + " Lucas numbers are:");
        for (int i=0; i < numLucasNumbers; i++){
            long startTime = clock.millis();
            System.out.print(lucas(i));
            long totalTime = clock.millis() - startTime;
            times.add(totalTime);
            System.out.println(" Time: " + totalTime + "ms");
        }

        //print the ratios of successive calculations
        System.out.println("The ratios of successive calculations are:");
        for(int i = 0; i < times.size() - 1; ++i){
            System.out.println(times.get(i) == 0 ? 0 : (float)times.get(i+1)/(float)times.get(i));
        }

        /*
        Edouard Lucas was a professor of maths, and is also know for Lucas pseudoprimes, Lucas–Carmichael numbers,
        and Pell-Lucas numbers.

        The ratios of successive calculations seem to approach the golden ratio.
        The order of growth for this algorithm is O(n^2)
         */
    }



    //calculate lucas numbers recursively, with the initial conditions hardcoded
    public static int lucas(int n) {
        if(n == 0) return 2;
        if(n == 1) return 1;
        return lucas(n - 1) + lucas(n - 2);
    }
}
