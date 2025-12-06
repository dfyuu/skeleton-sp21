package deque;

import java.util.Comparator;

public class MaxArrayDeque<Item> extends ArrayDeque<Item> {
    private Comparator<Item> comparator;

    public MaxArrayDeque(Comparator<Item> c) {
        super();
        comparator = c;
    }

    public Item max() {
        return max(comparator);
    }

    public Item max(Comparator<Item> c) {
        if (this.isEmpty()) {
            return null;
        }
        Item maxN = this.get(0);
        int i = 1;
        while (i < this.size()) {
            if (c.compare(maxN, this.get(i)) < 0) {
                maxN = this.get(i);
            }
        }
        return maxN;
    }
}
