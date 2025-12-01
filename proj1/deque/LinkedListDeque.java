package deque;

public class LinkedListDeque<Item> {
    private class ItemNode {
        private Item item;
        private ItemNode prev;
        private ItemNode next;

        public ItemNode(Item i, ItemNode p, ItemNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private int size;
    private ItemNode circleSentinel;

    public LinkedListDeque() {
        size = 0;
        circleSentinel = new ItemNode(null, null, null);
        circleSentinel.next = circleSentinel;
        circleSentinel.prev = circleSentinel;
    }
    public LinkedListDeque(Item i) {
        size = 1;
        circleSentinel = new ItemNode(null, null, null);
        circleSentinel.next = new ItemNode(i, circleSentinel, circleSentinel);
        circleSentinel.prev = circleSentinel.next;
    }

    public void addFirst(Item i) {
        circleSentinel.next = new ItemNode(i, circleSentinel, circleSentinel.next);
        circleSentinel.next.next.prev = circleSentinel.next;
        size += 1;
    }

    public void addLast(Item i) {
        circleSentinel.prev = new ItemNode(i, circleSentinel.prev, circleSentinel);
        circleSentinel.prev.prev.next = circleSentinel.prev;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        ItemNode lookup = circleSentinel.next;
        for(int i = 0; i < size; i += 1) {
           String s = String.valueOf(lookup.item);
           System.out.print(s + ' ');
           lookup = lookup.next;
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        Item first = circleSentinel.next.item;
        circleSentinel.next = circleSentinel.next.next;
        circleSentinel.next.prev = circleSentinel;
        return first;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        Item last = circleSentinel.prev.item;
        circleSentinel.prev = circleSentinel.prev.prev;
        circleSentinel.prev.next = circleSentinel;
        return last;
    }

    public Item get(int index) {
        if (index > size) return null;
        ItemNode p = circleSentinel.next;
        for (int i = 0; i < index; i += 1) {
            p = p.next;
        }
        return p.item;
    }

    public Item getRecursive(int index) {
        if (index > size) {
            return null;
        }
        ItemNode p = circleSentinel;
        return getByRecursive(index, p);
    }

    /** Helper function to better recursive get method */
    public Item getByRecursive(int i, ItemNode p) {
        if (i == 0) {
            return p.next.item;
        } else {
            return getByRecursive(i-1, p.next);
        }
    }
}