package deque;

public class ArrayDeque<Item> implements Deque<Item> {
    Item[] items;
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

    @Override
    public int size() {
        return size;
    }

    @Override
    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item first = items[lastNext(nextFirst)];
        nextFirst = lastNext(nextFirst);
        size -= 1;
        return first;
    }

    @Override
    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item last = items[firstNext(nextLast)];
        nextLast = firstNext(nextLast);
        size -= 1;
        return last;
    }

    public void resize(int cap) {
        Item[] a = (Item[]) new Object[cap];
        for (int i = 0; i < size; i += 1) {
            a[i] = items[lastNext(nextFirst)];
            nextFirst = lastNext(nextFirst);
        }
        items = a;
        nextFirst = cap - 1;
        nextLast = size;
        capacity = cap;
    }

    @Override
    public void addLast(Item i) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = i;
        nextLast = lastNext(nextLast);
        size += 1;
    }

    @Override
    public void addFirst(Item i) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = i;
        nextFirst = firstNext(nextFirst);
        size += 1;
    }

    @Override
    public Item get(int index) {
        if (index > size) {
            return null;
        }
        int current = (nextFirst + 1 + index) % capacity;
        return items[current];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i += 1) {
            String s = String.valueOf(this.get(i));
            System.out.print(s + ' ');
        }
    }
}