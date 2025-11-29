package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> NA = new AListNoResizing<>();
        BuggyAList<Integer> BA = new BuggyAList<>();
        NA.addLast(4);
        NA.addLast(8);
        NA.addLast(12);

        BA.addLast(4);
        BA.addLast(8);
        BA.addLast(12);

        assertEquals(BA.size(), NA.size());

        assertEquals(NA.removeLast(), BA.removeLast());
        assertEquals(NA.removeLast(), BA.removeLast());
        assertEquals(NA.removeLast(), BA.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BuggyL = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                BuggyL.addLast(randVal);
                L.addLast(randVal);
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int BuggySize = BuggyL.size();
            } else if (operationNumber == 2) {
                //getLast
                if (L.size() == 0) continue;
                int last = L.getLast();
                int BuggyLast = BuggyL.getLast();
            } else if (operationNumber == 3) {
                //removeLast
                if (L.size() == 0) continue;
                L.removeLast();
                BuggyL.removeLast();
            }
        }
    }
}
