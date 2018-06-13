import com.spbsu.a1arick.homework2.BubbleSort;
import com.spbsu.a1arick.homework2.QuickSort;
import com.spbsu.a1arick.homework2.SortAlgorithm;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;

public class SortAlgorithmTest {
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

    private static void testSorting(SortAlgorithm sort) {
        for (int i = 0; i < 10; i++) {
            assertTrue(test(sort));
        }
    }

    @Test
    public void bubble() {
        testSorting(new BubbleSort());
    }

    @Test
    public void qSort() {
        testSorting(new QuickSort());
    }
}