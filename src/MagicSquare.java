import java.util.ArrayList;
import java.util.Arrays;

public class MagicSquare {

    public static int numCombinations = 0;
    public static int tempCombinations = 0;
    public static int largestNumCombinations = 0;
    public static int sumAssociated = 0;

    public static void main(String[] args){
        Integer[] square = {1, 14, 14, 4, 11, 7, 6, 9, 8, 10, 10, 5, 13, 2, 3, 15};
        int n = square.length;


        //find four element combinations that add up to magic number 33
        System.out.println("All 4 number combinations that add up to the magic number (33):");
        fourElementCombinations(square, n, 33);


        //find all combinations that add up to magic number 33 of any length
        System.out.println("All combinations that add up to the magic number (33) regardless of length:");
        sum(new ArrayList<Integer>(Arrays.asList(square)), 33, true);
        System.out.println("Number of combinations = " + numCombinations);


        //find how many combinations there are to make every sum on the square
        numCombinations = 0;
        int sum = 0;
        for(int i = 0; i < n; ++i){
            sum += square[i];
        }
        for(int i = 0; i < sum; ++i){
            tempCombinations = 0;
            sum(new ArrayList<Integer>(Arrays.asList(square)), i, false);
        }
        System.out.println("Num ways every possible sum can be found: " + numCombinations);


        //sum with the most number of combinations
        System.out.println("The sum with the largest number of combinations is " + sumAssociated + " with " + largestNumCombinations + " combinations");

        /*
        The interesting thing about the sum with the most combinations is that it is exactly twice the magic number. Weird.
         */
    }



    //this solution is O(n^3)
    public static void fourElementCombinations(Integer A[], int n, int X){
        int l, r;

        Arrays.sort(A);

        for (int i = 0; i < n - 3; i++){
            for (int j = i + 1; j < n - 2; j++){
                l = j + 1;
                r = n - 1;

                while (l < r){
                    if (A[i] + A[j] + A[l] + A[r] == X){
                        System.out.println(A[i]+" "+A[j]+" "+A[l]+" "+A[r]);
                        l++;
                        r--;
                    }
                    else if (A[i] + A[j] + A[l] + A[r] < X)
                        l++;
                    else
                        r--;
                }
            }
        }
    }


    public static void sum(ArrayList<Integer> nums, int target, boolean print){
        sumRecursive(nums, target, new ArrayList<Integer>(), print);
    }

    public static void sumRecursive(ArrayList<Integer> nums, int target, ArrayList<Integer> partial, boolean print){
        int sum = 0;
        for(int x : partial) sum += x;

        if(sum == target){
            if(print) System.out.println(Arrays.toString(partial.toArray()));
            ++numCombinations;
            ++tempCombinations;
            if(tempCombinations > largestNumCombinations){
                largestNumCombinations = tempCombinations;
                sumAssociated = sum;
            }
        }

        if(sum >= target){
            return;
        }

        for(int i = 0; i < nums.size(); ++i){
            ArrayList<Integer> remainders = new ArrayList<Integer>();
            int n = nums.get(i);

            for(int j = i + 1; j < nums.size(); ++j){
                remainders.add(nums.get(j));
            }

            ArrayList<Integer> partialRec = new ArrayList<Integer>(partial);
            partialRec.add(n);

            sumRecursive(remainders, target, partialRec, print);
        }
    }

}
