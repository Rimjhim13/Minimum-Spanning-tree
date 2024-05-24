import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;       // maximum number of elements in PQ
    private int n;          // number of elements in PQ
    private int[] pq;       // binary heap using 1-based indexing
    private int[] qp;       // inverse of pq: qp[pq[i]] = pq[qp[i]] = i
    private Key[] keys;     // keys[i] = priority of i

    public IndexMinPQ(int maxN) {
        if (maxN <= 0) throw new IllegalArgumentException("Invalid maxN");
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }

    public int size() {
        return n;
    }

    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException("Invalid index");
        if (contains(i)) throw new IllegalArgumentException("Index is already in the priority queue");
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public int delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        int minIndex = pq[1];
        swap(1, n--);
        sink(1);
        qp[minIndex] = -1; // delete
        keys[pq[n + 1]] = null; // to help with garbage collection
        pq[n + 1] = -1; // not needed
        return minIndex;
    }

    public void change(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException("Invalid index");
        if (!contains(i)) throw new NoSuchElementException("Index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            swap(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    private void swap(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
}

