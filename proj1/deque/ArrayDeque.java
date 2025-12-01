package deque;

public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int capacity = 8;

    public ArrayDeque() {
        items = (Item[]) new Object[capacity];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int firstNext(int first) {
        if (first == 0) {
            return capacity - 1;
        }
        return first - 1;
    }

    public int lastNext(int last) {
        if (last == capacity-1) {
            return 0;
        }
        return last + 1;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item first = items[lastNext(nextFirst)];
        nextFirst = firstNext(nextFirst);
        size -= 1;
        return first;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item last = items[firstNext(nextLast)];
        nextLast = lastNext(nextLast);
        size -= 1;
        return last;
    }

    public void resize(int cap) {
        Item[] a = (Item[]) new Object[cap];
        for (int i = 0; i < size; i += 1) {
            a[i] = items[nextFirst-1];
            nextFirst = lastNext(nextFirst);
        }
        items = a;
        nextFirst = cap - 1;
        nextLast = size;
        capacity = cap;
    }

    public void addLast(Item i) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = i;
        size += 1;
    }

    public void addFirst(Item i) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = i;
        size += 1;
    }

    public Item get(int index) {
        if (index > size) {
            return null;
        }
        int current = (nextFirst + 1 + index) % capacity;
        return items[current];
    }
}