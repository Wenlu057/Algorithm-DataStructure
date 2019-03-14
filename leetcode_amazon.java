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