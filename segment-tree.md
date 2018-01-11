## Data Structure: Segment Tree

Segment Tree is used in cases where there are multiple **range queries on array** and modifications of elements of the same array. For example, finding the sum of all the elements in an array from indices L to R, or finding the minimum \(famously known as Range Minumum Query problem\) of all the elements in an array from indices L to R. These problems can be easily solved with one of the most versatile data structures, **Segment Tree.**

#### Definition:
Segment Tree is a basically a binary tree used for storing the intervals or segments. Each node in the Segment Tree represents an interval.The root of the Segment Tree represents the whole array A\[0:N−1\].Then it is broken down into two half intervals or segments and the two children of the root in turn represent the A\[0: \(N-1\)/2\], A\[\(N-1\)/2 + 1: N -1\].So the height of the segment tree will be Log\(n\).

Two operations:

1. **Update: **To update the element of the array A and reflect the corresponding change in the Segment tree. O\(logN\)
2. **Query: **In this operation we can query on an interval or segment and return the answer to the problem \(say minimum/maximum/summation in the particular segment\). O\(logN\)![](/assets/import.png)


```

class SegmentTreeNode {
    public int start, end, count;
    public SegmentTreeNode left, right;
    public SegmentTreeNode(int start, int end, int count) {
        this.start = start;
        this.end = end;
        this.count = count;
        this.left = this.right = null;
    }
}

```








