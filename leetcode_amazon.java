import  java.util;
/* K Closest Points to Origin */

/*
* you have an empty max heap of size K, and each time you push an element into the max heap,
* if we exceed the max size K, we pop the maximum element out from the heap.
* */

class Solution {
    public int[][] kClosest(int[][] points, int K) {
        Queue<int[]> maxHeap = new PriorityQueue<int[]> ((a, b) -> ((b[0] *  b[0] + b[1] * b[1]) - (a[0] * a[0] + a[1] * a[1])));
        for (int[] point : points) {
            maxHeap.add(point);
            if (maxHeap.size() > K) {
                maxHeap.remove();
            }
        }
        int[][] res = new int[K][2];
        while (K-- > 0) {
            res[K] = maxHeap.remove();
        }
        return res;
    }
}