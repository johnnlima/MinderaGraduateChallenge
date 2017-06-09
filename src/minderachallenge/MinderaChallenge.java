package minderachallenge;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Nuno Lima
 */
public class MinderaChallenge {

   
    public static void main(String[] args) {
        
        // Insert the array that you want to sort and how many groups:
        int[] numbers = {16, 15, 14, 13, 34, 23, 24, 25, 26, 28, 45, 34, 23, 29, 12, 23, 45, 67, 23, 12, 34, 45, 23, 67, 23, 670};
        int groups = 3;

        // Saves the array's lenght and sorts the array.
        int numbersTotal = numbers.length;
        Arrays.sort(numbers);

        // Both "centroid" are the average of each of the groups; "converge" will check if the numbers are grouped;
        int[] centroid = new int[groups];
        int[] centroid2 = new int[groups];
        boolean converge = false;

        // I will start to add random averages to the centroid; I will use this method so I can guarantee there are no
        // duplicates (bad things happen when there are duplicates);
        HashSet<Integer> used = new HashSet<Integer>();
        for (int i = 0; i < groups; i++) {
            int add = (int) (numbers[new Random().nextInt(numbersTotal)]); //It will add a number of the inserted array;
            while (used.contains(add)) { // While we have already used the number
                add = (int) (numbers[new Random().nextInt(numbersTotal)]); //Gets another number;
            }
            used.add(add);
            centroid[i] = add;
        }

        // Sorts the centroid so the result will be in order;
        Arrays.sort(centroid);

        // Creates a list with a n number of list where the numbers will be stored and grouped;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < groups; i++) {
            List<Integer> resultn = new ArrayList<>();
            result.add(resultn);
        }

        // Iterates while the averages dont converge;
        do {

            //Copys "centroid" to "centroid2";
            System.arraycopy(centroid, 0, centroid2, 0, groups);

            // Clears all the result list;
            for (int i = 0; i < groups; i++) {
                result.get(i).clear();
            }
            
            int min = 100000;
            int intMin = 0;

            //Goes through the array inserted and groups the numbers in lists with the 
            // averages closest to the number;
            for (int i = 0; i < numbersTotal; i++) {
                int dif = 100000;

                for (int j = 0; j < groups; j++) {
                    dif = abs(numbers[i] - centroid[j]);
                    if (dif < min) {
                        min = dif;
                        intMin = j;

                    }
                }
                min = 100000;
                result.get(intMin).add(numbers[i]);
            }

            // After the numbers are inserted, calculates the new average of each group;
            for (int i = 0; i < groups; i++) {
                centroid[i] = (int) (calculateAverage(result.get(i)) + 0.5);
            }

            // Checks if the averages dont change and if they dont, "converge" is true and the
            // loop can end;
            if (Arrays.equals(centroid2, centroid)) {
                converge = true;
                // Prints the result;
                System.out.println(result);
            }
            
        } while (!converge);

    }

    //Calculates average of a list<Integer>;
    private static double calculateAverage(List<Integer> group) {
        long sum = 0;
        for (Integer element : group) {
            sum += element;
        }
        return group.isEmpty() ? 0 : 1.0 * sum / group.size();
    }

}
