package NonIterativeMethod;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Nuno Lima
 */
public class NonIterativeMethod {

    public static void main(String[] args) {
        
        // The real submition for the challenge is "MinderaChallenge.java", not this one!
        
        // I just added this because when Jose asked me how I would achive the solution to this problem
        // this was my answer. 
 
        Integer[] numbers = {16, 15, 14, 13, 34, 23, 24, 25, 26, 28, 45, 34, 23, 29, 12, 23, 45, 67, 23, 12, 34, 45, 23, 67, 23, 670};
        int groups = 3;
        int numbersTotal = numbers.length;

        List<Integer> numbersList = new ArrayList<>(Arrays.asList(numbers));
        Collections.sort(numbersList);

        List<Integer> list = new ArrayList<>();

        int dif;

        for (int i = 0; i < numbersTotal - 1; i++) {
            dif = abs(numbersList.get(i) - numbersList.get(i + 1));
            list.add(dif);

        }

        List<Integer> breaks = new ArrayList<>();

        for (int i = 0; i < groups - 1; i++) {
            int j = list.indexOf(Collections.max(list));
            list.set(j, 0);
            breaks.add(j);

        }

        Collections.sort(breaks);

        List<List<Integer>> result = new ArrayList<>();

        List<Integer> resulti = numbersList.subList(0, breaks.get(0) + 1);
        List<Integer> resultf = numbersList.subList(breaks.get((breaks.size() - 1)) + 1, numbersList.size());

        result.add(resulti);

        for (int i = 0; i < groups - 2; i++) {
            List<Integer> resultx = numbersList.subList(breaks.get(i) + 1, breaks.get(i + 1) + 1);

            result.add(resultx);
        }
        result.add(resultf);
        System.out.println(result);

    }

}
