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