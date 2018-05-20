import com.spbsu.a1arick.homework2.BubleSort;
import com.spbsu.a1arick.homework2.Qsort;
import com.spbsu.a1arick.homework2.SortAlgorithm;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;



public class SortAlgorithmTest {
    @Test
    public void bubble() {
        for (int i = 0; i < 10; i++) {
            assertTrue(test(new BubleSort()));
        }
    }

    @Test
    public void qSort() {
        for (int i = 0; i < 10; i++) {
            assertTrue(test(new Qsort()));
        }
    }

    private static boolean test(SortAlgorithm sortAlgorithm) {
        int n = 1000;
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            a[i] = ThreadLocalRandom.current().nextInt();
        }

        sortAlgorithm.sort(a);

        for (int i = 1; i < n; i++) {
            if (a[i - 1] > a[i])
                return false;
        }
        return true;
    }
}