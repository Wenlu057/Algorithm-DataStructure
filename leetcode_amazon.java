import  java.util.*;
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

/*LRU Cache*/
// refer to Algorithm-DataStructure/LinkedList.java
/* doubly linkedlist + hashmap,
 1)remove least recently used cache and add new one to head if capacity is full,
 2)remove the existing cache first for updating cache, add the newly updates to the head */



/*Two Sum*/

public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2]
        if (nums == null || nums.length < 2) {
            return res;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                res[0] = map.get(target - nums[i]);
                res[1] = i;
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}


/*200. Number of Islands*/

// refer to Algorithm-DataStructure/array.java


/*819. Most Common Word*/

/*
  default V getOrDefault​(Object key, V defaultValue)
  Returns the value to which the specified key is mapped,
  or defaultValue if this map contains no mapping for the key.
*/
/*
* regular expression
* [^abc] Any character except a, b, or c (negation)
* */

//Notes: how to remove redundent character? using regular expression!
//Notes: find the key of max value in the hashmap?

//Keywords: Hashset, hashmap
public class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph.isEmpty()) {
            return "";
        }
        HashSet<String> set = new HashSet<>();
        for (String w : banned) {
            set.add(w);
        }
        HashMap<String, Integer> map = new HashMap<>();
        paragraph = paragraph.toLowerCase();
        paragraph = paragraph.replaceAll("[^a-z]", " ");
        String[] words = paragraph.split(" ");
        for (String word : words) {
            if (!word.trim().isEmpty() && !set.contains(word)) {
                map.put(word, map.getOrDefault(work, 0) + 1);
            }
        }
        String res = "";
        for (String key : map.keySet()) {
            if (res.equals("") || map.get(res) < map.get(key)) {
                res = key;
            }
        }
        return res;
    }
}

/*5. Longest Palindromic Substring*/

/*2 methods
* 1) use dp, time complexity O(n * n), space complexity O(n)
* how to define dp, design a matrix dp[i][j] represents if the substring(i, j + 1) is palindrome.
* how to iterate and utilize previously calculated value.
*
* 2) use expand center, time complexity O(n * n), space O(1)
* divide into 2 cases, the center has 1 num, the center has 2 nums.
* helper function to check the left bound and right bound.
* */

//first solution

class Solution {
    String longestPalindrome(String s) {
        if (s.isEmpty() || s.length() == 0) {
            return "";
        }
        int max = 0;
        String res = "";
        int[][] dp = new int[s.length()][s.length()];
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i <= 2 || dp[i + 1][j - 1]);
                if (dp[i][j]) {
                    if (i - j + 1 > max) {
                        max = i - j + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }
}

//second solution

class Solution {
    String res = "";
    public String longestPalindrome(String s) {
        if (s.isEmpty() || s.length() == 0) {
            return "";
        }
        for (int i = 0; i < s.length(); i++) {
            expandAroundCenter(s, i, i);
            expandAroundCenter(s, i, i + 1);
        }
        return res;
    }
    private void expandAroundCenter(String s, int left, int right) {
        if (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right))  {
            left--;
            right++;
        }
        String curr = s.substring(left, right + 1);
        if (curr.length() > res.length()) {
            res = curr;
        }
    }
}

/*Cut Off Trees for Golf Event*/

// the requirement is cut from the lowest height first starting from the origh,
// what you really need to do is sort the height of trees in ascending order.
// After that, you just need to find the path from origh to 1st shortest tree, to 2nd shorted,..etc
// you could use bfs to get the steps from ith tree to the next.


//Notes, bfs use queue to store elements in each level, each time in the loop you increment the level,
//You really don't want to go back to the elments you already visited, so you need to record all those
//visited elements.  Key idea is BFS is expanding from the center, for each element in each level, it goes
//to 4 directions.


class Solution {
    public int cutOfTree(List<List<Integer>> forest) {
        if (forest == null || forest.size() == 0) {
            return -1;
        }
        int m = forest.size(); // how many rows
        int n = forest.get(0).size(); // how many columns

        // store the trees in a heap, each tree element is an array with x coordinate, y coordinate, height.
        PriorityQueue<int[]> trees = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (forest.get(i).get(j) > 1) {
                    trees.add(new int[]{i, j, forest.get(i).get(j)});
                }
            }
        }
        int[] start = new int[];
        int totalSteps = 0;
        //retrive trees from the heap
        while (!trees.isEmpty()) {
            int[] tree = trees.poll();
            int[] next = new int[]{tree[0], tree[1]};
            int step = BFS(forest, start, next, m, n);
            if (step == -1) {
                return -1;
            }
            totalSteps += steps;
            start = next;
        }
        return totalSteps;
    }
    static int[][] dir = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private int BFS(List<List<Integer>> forest, int[] start, int[] next, int m, int n) {
        boolean[][] visited = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(start);
        visited[start[0]][start[1]] = true;
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                if (curr[0] == next[0] && curr[1] == next[1]) {
                    return step;
                }
                for (int[] d : dir) {
                    int nextX = curr[0] + d[0];
                    int nextY = curr[1] + d[1]
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY] || forest.get(nextX).get(nextY) == 0) {
                        continue;
                    }
                    queue.offer(new int[]{nextX, nextY});
                    visited[nextX][nextY] = true;
                }
            }
            step++;
        }
        return -1;
    }
}

/*Trapping Rain Water*/

//hard to understand
//how much water current unit is able to trap depends on the smaller one of the max vals on both sides.
//update left max val and right max val by one pass.

class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length;
        int leftMax = 0;
        int rightMax = 0;
        int total = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                total += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                total += rightMax - height[right];
                right--;
            }
        }
        return  total;
    }
}