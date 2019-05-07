package HeapSort;

import java.util.*;

/**
 * MaxHeap class that contains HeapSort and priority queues
 */
public class MaxHeap {
    private ArrayList<PageRank> heap;
    private int heapSize;
    private int max;

    /**
     * Initializing the constructor for the class
     *
     * @param heap
     * @param heapSize
     * @param max
     */
    public MaxHeap(ArrayList<PageRank> heap, int heapSize, int max) {
        this.heap = heap;
        this.heapSize = heapSize;
        this.max = max;
    }

    //Setter method that sets the heap size
    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

    //Setter method that sets the heap
    public void setHeap(ArrayList<PageRank> heap) {
        this.heap = heap;
    }

    //Parent method
    private int Parent(int i) {
        return i / 2;
    }

    //Left side of the Parent node
    private static int Left(int i) {
        return 2 * i;
    }

    //Right side of the Parent node
    private static int Right(int i) {
        return (2 * i) + 1;
    }

    /**
     * This method sorts the list
     */
    public void HeapSort() {
        BuildMaxHeap();         //Builds a MaxHeap
        int size = heapSize;
        for (int i = heap.size() - 1; i >= 1; i--) {
            Collections.swap(heap, i, 0);
            heapSize--;
            MaxHeapify(0);
        }
        heapSize = size;
    }

    /**
     * MaxHeapify compares the parent to the left and right children
     * Swaps the parent with the left and right children, if the parent is not the largest.
     * This is done to satisfy the MaxHeap property
     *
     * @param i
     */
    public void MaxHeapify(int i) {
        int largest;
        if (heap == null && i >= heapSize) {
            return;
        }
        int l = Left(i);
        int r = Right(i);
        if (l < heapSize && heap.get(l).getScore() > heap.get(i).getScore()) {
            largest = l;
        } else {
            largest = i;
        }
        if (r < heapSize && heap.get(r).getScore() > heap.get(largest).getScore()) {
            largest = r;
        }
        if (largest != i) {
            Collections.swap(heap, i, largest);
            MaxHeapify(largest);
        }
    }

    /**
     * Goes through the parent nodes and calls MaxHeapify
     * MaxHeapify is not called on the leaf nodes
     */
    public void BuildMaxHeap() {
        heapSize = heap.size();
        for (int i = heap.size() / 2 - 1; i >= 0; i--) {
            MaxHeapify(i);
        }
    }

    /**
     * Implements the insertion operation
     *
     * @param key
     */
    public void MaxHeapInsert(PageRank key) {
        if (heapSize == max) {
            System.out.println("Max Reached");
        }
        heap.add(key);                  //Adds a new leaf
        HeapIncreaseKey(heapSize, key); //HeapIncreaseKey is called to maintain the MaxHeap property
        heapSize = heapSize + 1;

    }

    /**
     * This method extracts the element with the largest value
     *
     * @return
     */
    public PageRank HeapExtractMax() {
        if (heapSize < 0) {
            System.out.println("Heap Underflow");
        }
        PageRank maxPageRank = heap.get(0);
        heap.get(0).equals(heap.get(heapSize - 1));
        heapSize = heapSize - 1;
        MaxHeapify(1);
        return maxPageRank;
    }

    /**
     * This method increases the value of an element.
     *
     * @param i
     * @param key
     */
    public void HeapIncreaseKey(int i, PageRank key) {
        if (key.getScore() < heap.get(i).getScore()) {
            System.out.println("New Key is smaller than current key");
        }
        heap.get(i).equals(key);
        while (i > 0 && heap.get(Parent(i)).getScore() < heap.get(i).getScore()) {
            Collections.swap(heap, i, Parent(i));
            i = Parent(i);
        }
    }

    /**
     * This method returns the element with the largest value.
     *
     * @return
     */
    public int HeapMaximum() {

        return heap.get(0).getScore();
    }

}
