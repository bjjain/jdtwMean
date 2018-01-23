package tools;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

/**
 * Created by jain on 12/07/16.
 */
public class Rand extends Random {
    private static final Rand rd = new Rand();

    private Rand() {
    }

    public static Integer[] shuffle(int n) {
        Integer[] x = new Integer[n];
        for (int i = 0; i < n; i++) {
            x[i] = i;
        }
        Collections.shuffle(Arrays.asList(x));
        return x;
    }

    public static int[] choose(int k, int n) {
        if (k < 0) {
            return null;
        }
        if (k == 0) {
            return new int[0];
        }
        if (n == 1) {
            return new int[1];
        }
        if (k == n) {
            return arr(n);
        }

        int[] set = arr(n);

        if (k >= n) {
            return set;
        }

        int[] result = new int[k];
        int index;
        for (int i = 0; i < k; i++) {
            index = rd.nextInt(n - i);
            result[i] = set[index];
            set[index] = set[n - i - 1];
        }
        return result;
    }

    private static int[] arr(int n) {
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = i;
        }
        return x;
    }
}
