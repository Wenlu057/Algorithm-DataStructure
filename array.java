/*Array*/
//Best Time to Buy and Sell Stock
/* Two pointers
 * Use a variable to keep track of the lowest point.
 * Keep updating the max profit through iterating the whole array.
 * If we find a number lower than the lowest point, we need to update,
 * since we can get max profit by substracting lowest point val.
 * Compare with longest increasing sequence, we only need two points:
 * the lowest and the highest.
 */

public class Solution {
    /*
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        // write your code here
        if (prices.length == 0) return 0;
        int start = 0;
        int max = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[start] > max) {
                max = prices[i] - prices[start];
            }
            if (prices[i] < prices[start]) {
                start = i;
            }
            
        }
        return max;
    }
}

//Remove Duplicates from Sorted Array
/*Two Pointers, Multiple duplicates may exist, IN-PLACE*/
/*Use extra variable to keep track of the tail of new array.
 *Override the array and move element forward if it is unique.
 *All duplicates will be overrided.
 */
public class Solution {
    /*
     * @param nums: An ineger array
     * @return: An integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int tail = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[tail]) {
                nums[++tail] = nums[i];
            }
        }
        return tail + 1;
        
    }
}

//Remove Duplicates from Sorted Array II 
/* Allow duplicates at most twice, need to count the times of duplicates*/
public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
        if (nums.length == 0 || nums == null) return 0;
        int tail = 0;
        int flag = 1; // count duplicates
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[tail] ) {
                nums[++tail] = nums[i];
                flag = 1;
            }else {
                if (flag < 2) { // the second time
                    nums[++tail] = nums[i]; // BE CAREFUL, ERROR IF ONLY INCREMENT TAIL
                    flag ++;
                }
            }
            
        }
        return tail + 1;
    }
}

// Longest Consecutive Sequence
// union find
// Find the start point of one consecutive sequence, keep updating the gloabal longest streak
// Solution 1 : O(n) time complexity + O(n) space complexity
// HashMap + boolean array
/*
 use hashmap to reduce lookup time to O(1)
 use extra boolean array to mark all visited elements
 compare the maxLen with current max to get gloabal maximum.
 */

public class Solution {
    /*
     * @param num: A list of integers
     * @return: An integer
     */
     
    public int longestConsecutive(int[] num) {
        // write your code here
        if (num.length == 0 || num.length == 1) return num.length;
        int start = num[0];
        int end = num[0];
        int maxLen = 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < num.length; i++) {
                map.put(num[i], i);
        }
        boolean[] marked = new boolean[num.length];
        for (int i = 0; i < num.length; i++) {
            marked[i] = true;
            int it = num[i] + 1;
            while (map.containsKey(it) && !marked[map.get(it)] ) {
                marked[map.get(it)] = true;
                it = it + 1;
            }
            end = it -1;
            it = num[i] - 1;
            while (map.containsKey(it) && !marked[map.get(it)]) {
                marked[map.get(it)] = true;
                it = it - 1;
            }
            start = it + 1;
            maxLen = Math.max(maxLen, end - start + 1);
        }
        return maxLen;
    }
    
}
// Hashset
/* the numbers are stored in a HashSet (or Set, in Python) to allow O(1) lookups
 * we only attempt to build sequences from numbers that are not already part of a longer sequence. 
 */
public class Solution {
    /*
     * @param num: A list of integers
     * @return: An integer
     */    
    public int longestConsecutive(int[] num) {
        if(num.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < num.length; i++) {
            set.add(num[i]);
        }
        int maxLen = 1;
        for (int x : set) {
            if (!set.contains(x - 1)) {
                int currNum = x;
                int currMax = 1;
                while (set.contains(currNum + 1)) {
                    currMax++;
                    currNum++;
                }
                maxLen = Math.max(maxLen, currMax);
            } 
        }
        return maxLen;
    }
}
// Solution 2
/*
 * O(nlgn) time complexity, O(1) space complexity
 * First sort the array
 */
public class Solution {
    public int longestConsecutive(int[] num) {
        if (num.length == 0) return 0;
        Arrays.sort(num);
        int maxLen = 1;
        int currMax = 1;
        for (int i = 1; i < num.length; i++) {
            if (num[i] != num[i - 1]){ // to avoid edge case [0,1,1,2,3]
                if (num[i] == num[i - 1] + 1) {
                    currMax++;
                } else {
                    maxLen = Math.max(maxLen, currMax);
                    currMax = 1;
                }
            }            
        }
        // DON'T FORGET TO COMPARE WITH LAST CONSECUTIVE SEQUENCE!!
        return Math.max(maxLen, currMax);
    }
}

// Solution 3
/*Brute Force*/
public class Solution {
    public int longestConsecutive(int[] num) {
        if (num.length == 0) return 0;
        int maxLen = 1;
        for (int i = 0; i < num.length; i++) {
            int currMax = 1;
            int curr = num[i];
            while (containsNum(num, curr + 1)) {
                curr++;
                currMax++;
            }
            maxLen = Math.max(maxLen, currMax);
        }
        return maxLen;

    }
    public boolean containsNum(int[] num, int x) {
        for (int i = 0; i < num.length; i++) {
            if (num[i] == x) {
                return true;
            }
        }
        return false;
    }
}

// Number of islands
// matrix
// if we find one, count it and set all elements of this islands to 0 using dfs.
class Solution {
    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        int count = 0;
        for (int m = 0; m < grid.length; m++) {
            for (int n = 0; n < grid[0].length; n++) {
                if (grid[m][n] == '1') {
                    count++;
                    dfs(grid, m, n);
                }
            }
        }
        return count;

    }
    public void dfs(char[][] grid, int i, int j) {
        if (i >=0 && i < grid.length && j >= 0 
            && j < grid[0].length && grid[i][j] == '1') {
            grid[i][j] = '0';
            dfs(grid, i - 1, j);
            dfs(grid, i + 1, j);
            dfs(grid, i, j - 1);
            dfs(grid, i, j + 1);
        }
    }
}
// Number of islandsII
/*Uniton find , quick-union*/
/*Referene http://www.cnblogs.com/SeaSky0606/p/4752941.html */
/*The tricky part is the union operation, if you want to connect p and q
 *append p to q? or append q to p?
 *Time complexity will decrease if you append to a larger tree.
 *Ex: one tree with root 4, (1, 4, 3, 5, 6)
 *another tree with single node 10
 *if you connect tree with root 4 to a single node tree, the tree height might increase. 
 */
class Solution {
    private static int[][] neighbours = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int[] id;
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> res = new ArrayList<>();
        if (positions.length == 0)  return res;
        if(m <= 0 || n <= 0) return res;
        id = new int[m * n];
        Arrays.fill(id, -1);
        int count = 0;
        for (int[] position : positions) {
            int i = position[0];
            int j = position[1];
            int pID = n * i + j;
            id[pID] = pID;
            count++;
            for (int[] neighbour : neighbours){
                int r = i + neighbour[0];
                int c = j + neighbour[1];
                if (r < 0 || r >= m || c < 0 || c  >= n || id[n * r + c] == -1)  continue;
                int qID = findRoot(n * r + c);
                if (qID != -1 && qID != pID) {
                    count--;
                    id[pID] = qID; // current node connects to q node
                    pID = qID; // don't forget to update the current value of pID                             
                }
            }
            res.add(count);
        }
        return res;
        
    }
    public int findRoot(int x) {
        while (x != -1 && id[x] != x) x= id[x]; 
        return x;
    }
}

// K Empty Slots
/* Solution 1 Brute Force*/
public int kEmptySlots(int[] flowers, int k) { 
        int minDay = flowers.length; 
        for (int i = 1; i <= flowers.length - k; i++) { 
            int slot1 = flowers[i - 1]; 
            int slot2 = slot1 + k + 1;
            if (slot2 > flowers.length) continue; 
            int index2 = getIndex(slot2, flowers);  // get the index(bloom day) given slot
            int lastIndex = Math.max(i, index2);
            if (kNotBlooming(slot1, lastIndex, k, flowers)) { 
                minDay = Math.min(minDay, Math.max(i, index2)); 
            } 
        } 
        if (minDay == flowers.length) 
        return -1; 
        else return minDay; 
    } 
    public boolean kNotBlooming(int slot1, int index2, int k, int[] flowers) { 
        for (int i = 1; i <= k; i++) { 
             System.out.println(getIndex(slot1 + i, flowers));
            if (getIndex(slot1 + i, flowers) <= index2) { 
                return false; 
            } // want to get the index and see if it is greater than index2 
        } 
        return true; 
    } 
    public int getIndex(int flower, int[] flowers) { 
        for (int i = 0; i < flowers.length; i++) { 
            if (flowers[i] == flower) { 
                return i + 1; 
            } 
        } 
        return -1; 
    } 
        
}
/* Solution 2 Use Hash Map*/
/* If it is position sensitive, consider hashmap to store pos info,
 * so you can get pos in constant time.
 */
public int kEmptySlots(int[] flowers, int k) { 
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < flowers.length; i++) {
            map.put(flowers[i], i + 1);
        } // key: slot value: index(bloom day)
        int minDay = flowers.length; 
        for (int i = 1; i <= flowers.length - k; i++) { 
            int slot1 = flowers[i - 1]; 
            int slot2 = slot1 + k + 1;
            if (slot2 > flowers.length) continue; 
            // int index2 = getIndex(slot2, flowers);  // get the index(bloom day) given slot
            int index2 = map.get(slot2);
            int lastIndex = Math.max(i, index2);
            if (kNotBlooming(slot1, lastIndex, k, map)) { 
                minDay = Math.min(minDay, Math.max(i, index2)); 
            } 
        } 
        if (minDay == flowers.length) 
        return -1; 
        else return minDay; 
    } 
    public boolean kNotBlooming(int slot1, int index2, int k, HashMap<Integer, Integer> map) { 
        for (int i = 1; i <= k; i++) { 
            if (map.get(slot1 + i ) <= index2) { 
                return false; 
            } // want to get the index and see if it is greater than index2 
        } 
        return true; 
    }      
// The Skyline Problem
/*List-like matrix*/
/* Priority Queue to maintain a max-heap*/
/* Array customized sort, comparator using Lambda expression*/
/* Interval Problem with start point(x left postion) and end point(x right position)
 * construct array in a format of [[x pos, height, start? end?]], sort the array
 * Need to retrieve the max value in the heap and determine when is the critical point.
 * If it is start point, add to the heap. If the max value changed, it is critial point.
 * If it is end point, remove from the heap. If the max value changed, the curren max in the heap
 * along with the current x pos is critial.
 * Be careful about the edge cases! 
 */
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        if (buildings == null ||buildings.length == 0) return res;
        int[][] points = new int[buildings.length * 2][3];
        for (int i = 0; i < buildings.length; i++) {
            points[i * 2][0] = buildings[i][0];
            points[i * 2][1] = buildings[i][2];
            points[i * 2][2] = 1;
            points[i * 2 + 1][0] = buildings[i][1];
            points[i * 2 + 1][1] = buildings[i][2];
            points[i * 2 + 1][2] = -1;
        }
        //lambda expression
        Arrays.sort(points, (p1, p2) -> {
                if (p1[0] == p2[0]) {
                    // p1 and p2 are start points, point with heiger height first
                    if (p1[2] == 1 && p2[2] == 1) return p2[1] - p1[1]; 
                    // p1 and p2 are end points, point with lower height first
                    else if (p1[2] == -1 && p2[2] == -1) return p1[1] -p2[1];
                    // one is start point, one is end point, end point comes first
                    else {
                        if (p1[2] == -1) return 1;
                        else return -1;
                    }               
                }
                // smaller left x comes first
                return p1[0] - p2[0];
        });  
        Queue<Integer> heap = new PriorityQueue<>((h1, h2) -> {
            return h2 - h1; // max-heap
        });
        int max = 0;
        for (int i = 0; i < points.length; i++) {
            int x = points[i][0];
            int h = points[i][1];
            int flag = points[i][2];
            System.out.println(h);
            //start point
            if (flag == 1) {
                heap.offer(h);
                int currMax = heap.peek();
                if (currMax != max) {
                    max = currMax;
                    res.add(new int[]{x, h});
                }
            } else {
                heap.remove(h);
                if (heap.isEmpty()) {
                    res.add(new int[] {x, 0});
                } else {
                    int currMax = heap.peek();
                    if (currMax != max) {
                        max = currMax;
                        res.add(new int[]{x, currMax});
                    }
                }
      
            }
        }
        return res;
    }
}  
// Sliding Window Maximum
/* Maitain a Max-heap, initialize the heap, remove first one from heap 
 * and add next one into heap.
 * Spetial Case Handling: sliding window size = string length
 * After the sliding is complete, don't forget to add the last one into result.
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return new int[]{};
        int[] res = new int[nums.length + 1 - k];
        Queue<Integer> heap = new PriorityQueue<>(k, (n1, n2) -> {
            return n2 - n1;
        });
        int start = 0;
        for (int i = 0; i < k; i++) {
            heap.add(nums[i]);
        }
        // if k is equal to the nums length, the slidingwendow will not move
        // it only returns the global max value.
        if (nums.length == k) {
            res[start] = heap.peek();
            return res;
        }
        for (int i = k; i < nums.length; i++) {
            res[start] = heap.peek();
            heap.remove(nums[start]);
            heap.add(nums[i]);
            start++;
        }
        res[start] = heap.peek(); // don't forget the last element!
        return res;
    }
}
// Moving Average from Data Stream 
/*Sliding window, Maintain average value, O(1) complexity*/
class MovingAverage {

    /** Initialize your data structure here. */
    int[] window;
    int count;
    double sum;
    public MovingAverage(int size) {
        window = new int[size];
        count = 0;
        sum = 0;
    }
    
    public double next(int val) {
        if (count < window.length) {
            window[count] = val;
            sum += val;
            count++;
            return sum / count;
        } else {
            int tmp = window[count % window.length];
            window[count % window.length] = val;
            sum += val - tmp;
            count++;
            return sum / window.length;
        }
    }
}
// Range Sum Query 2D - Mutable
// Solution 1 Brute force
class NumMatrix {
    int[][] matrix;
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
    }
    
    public void update(int row, int col, int val) {
        matrix[row][col] = val;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                sum += matrix[i][j];
            }
        }
        return sum;
    }
} 

// Solution 2 Binary Indexed Tree
/* How to build BIT for a matrix*/
Class NumMatrix {
    int[][] nums;
    int[][] tree;
    int m;
    int n;
    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        m = matrix.length;
        n = matrix[0].length;
        nums = new int[m][n];
        tree = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }
    public void update(int row, int col, int val) {
        if (m == 0 || n == 0) return;
        int delta = val - nums[row][col];
        nums[row][col] = val;
        // Find the next in the BIT
        for (int i = row + 1; i <= m; i += i & (-i)) {
            for (int j = col + 1; j <= n; j += j & (-j)) {
                tree[i][j] += delta;
            }
        }

    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (m == 0 || n == 0) return;
        return sum(row2 + 1, col2 + 1) + sum(row1, col1) 
            - sum(row2 + 1, col1) - sum(row1, col2 + 1);
    }
    public int sum(int row, int col) {
        int sum = 0;
        // Find the parent until reaching the root in the BIT
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}

// Random Pick Index
/*Reservoir Sampling*/
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
/* 
For the ith item in the stream, where i is index of current item in stream,
the possibility that we pick the ith item is 1/i, we continue consider all elements 
after i one by one until k. For the next item after i, the possibility that the i+1 th
element is not selected is i/i+1. Similarly for the next one,  it's i+1/ i+2.
So when we finish run through all k items, the possibility that the result is ith element is:
1/i * i/i+1 * i+1/i+2 *....* k-1/k = 1/k. We successfully implement equal opportunity for each
item from 0 to k.
*/
class Solution {
    int[] nums;
    public Solution(int[] nums) {
        this.nums = nums;
    }
    
    public int pick(int target) {
        int res = -1;
        Random random = new Random();
        int count = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                // First time only 1 elment, we return the index of that one,
                // count all targets found until now, randomly pick one, possibility is 1/count, do the replacement.
                if (random.nextInt(count++) == 0) {
                    res = i;
                }
            }
        }
        return res;
    }
}

// Linked List Random Node
/*Reservoir Sampling*/
class Solution {

    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    ListNode head;
    public Solution(ListNode head) {
        this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        Random random = new Random();
        int count = 1; 
        ListNode nodeVal = head;
        ListNode it = head;
        while (it != null) {
            // increment count in each time.
            // randomly pick one 
            if (random.nextInt(count++) == 0) {
                nodeVal = it;
            }
            it = it.next;
        }

        return nodeVal.val;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */

//------------------------------------------------------------------//
/*Basic Array Questions Simple - Medium Level*/
//Moves Zeroes
/*Move all nozeroes forward*/
public class Solution {
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int index = 0;
        for (int num : nums) {
            if (num != 0)
                nums[index++] = num;
        }
        for (int i = index; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
//Intersections of Two Arrays II
/*Solution 1 HashMap*/
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0)
            return new int[]{};
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            if (!map.containsKey(num))
                map.put(num, 1);
            else 
                map.put(num, map.get(num) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int num : nums2) {
            if (map.containsKey(num) && map.get(num) > 0) {
                res.add(num);
                map.put(num, map.get(nums) - 1);
            }
        }
        int[] arr = new int[res.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = res.get(i);
        }
        return arr;
    }
}
/*Solution 2 Sort, Two Pointers*/
public class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length == 0 || nums2.length == 0)
        return new int[]{};
        List<Integer> res = new ArrayList<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i1 = 0;
        int i2 = 0;
        while (i1 < nums1.length && i2 < nums2.length) {
            if (nums1[i1] == nums2[i2]) {
                res.add(nums1[i1]);
                i1++;
                i2++;
            } else if (nums1[i1] < nums2[i2]) {
                i1++;
            } else {
                i2++;
            }
        }
        int[] arr = new int[res.sie()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = res.get(i);
        return arr;
    } 
}
//3 Sum
/*Two pointers*/
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        if (nums.length < 3) return res;
        if (nums[0] > 0 || nums[nums.length - 1] < 0) return res;
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int target = -nums[i];
                int start = i + 1;
                int end = nums.length - 1;
                while (start < end) {
                    if (nums[start] + nums[end] == target) {
                        res.add(Arrays.asList(nums[i], nums[start++], nums[end--]));
                        while (start < end && nums[start] == nums[start - 1]) start++;
                        while (start < end && nums[end] == nums[end + 1]) end--;
                    } else if (nums[start] + nums[end] < target) {
                        start++;
                    } else {
                        end--;
                    }
                }
            }
        }
        return res;
    }
}

//Minimum Size Subarray Sum
/*Solution 1 two pointers*/
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        int start = 0;
        int i = 0;
        while (i < nums.length) {
            while (i < nums.length && sum < s) sum += nums[i++];
            if (sum < s) break;
            while (start < i && sum >= s) sum -= nums[start++];
            min = Math.min(min, i - start + 1);
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
/*Solution 2, maintain sum array, binary search*/
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int min = Integer.MAX_VALUE;
        int[] sum = new int[nums.length + 1];
        for (int i = 1; i < sum.length; i++)
            sum[i] = sum[i - 1] + nums[i - 1];
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] + s <= sum[nums.length]) {
                int end = binarySearch(sum, i + 1, nums.length, sum[i] + s);
                min = Math.min(min, end - i);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    private int binarySearch(int[] sum, int i, int j, int key) {
        while (i + 1 < j) {
            int mid = i + (j - i) / 2;
            if (sum[mid] == key) return mid;
            else if (sum[mid] < key) i = mid;
            else j = mid;
        }
        if (sum[i] > key) return i;
        else return j;
    }
}
// Maximum Size Subarray Sum Equals K
/* HashMap key: sum value : index*/
public class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        if (nums == null || nums.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int max = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (!map.containsKey(sum)) map.put(sum, i);
            if (sum == k) {
                max = i + 1;
            } else if (map.containsKey(sum - k)) {
                max = Math.max(max, i - map.get(sum - k));
            }
        }
        return max;
    }
}