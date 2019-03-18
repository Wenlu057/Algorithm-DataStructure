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

/*Find Median from Data Stream*/

/*use max heap to store left half, use min heap to store right half
* keep balanced, which means the diffence of two heaps is less than 2.
* if the data stream is odd, return the max val of the left half, else get one from left
* and get one from right, calculate the average.
* */

// time complexity, each operation O(lgn) * n data = nlg(n)
class MedianFinder {
    PriorityQueue<Integer> left;
    PriorityQueue<Integer> right;
    public MedianFinder() {
        left = new PriorityQueue<Integer>((a, b) -> (b - a));
        right = new PriorityQueue<Integer>((a, b) -> (a - b));

    }
    public void addNum(int num) {
        if (left.isEmpty() || num <= left.peek()) {
            left.add(num);
        } else {
            right.add(num);
        }

        if (left.size() < right.size()) {
            left.add(right.remove());
        } else if (left.size() - right.size() == 2) {
            right.add(left.remove());
        }
    }

    public double findMedian() {
        if (left.size() > right.size()) {
            return left.peek();
        } else {
            return (double)(left.peek() + right.peek()) / 2;
        }
    }
}

/*2. Add Two Numbers*/

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pt1 = l1;
        ListNode pt2 = l2;
        ListNode dummy = new ListNode(-1);
        ListNode pt = dummy;
        int carry = 0;
        while (pt1 != null || pt2 != null || carry != 0) {
            int num1 = pt1 == null ? 0 : pt1.val;
            int num2 = pt2 == null ? 0 : pt2.val;
            int digit = (num1 + num2 + carry) % 10;
            carry = (num1 + num2 + carry) / 10;
            ListNode node = new ListNode(digit);
            pt.next = node;
            pt = pt.next;
            pt1 = pt1 == null ? null : pt1.next;
            pt2 = pt2 == null ? null : pt2.next;
        }
        return dummy.next;
    }
}

/*23. Merge k Sorted Lists*/

// refer to Algorithm-DataStructure/LinkedList.java


/*103. Binary Tree Zigzag Level Order Traversal*/

// refer to Algorithm-DataStructure/tree.java


/*212. Word Search II*/

//trie tree + dfs
//dfs is backtracking, explore all possibilities, use dfs in graph needs to record all visiting cell
//here mark # as visited, when backtracking, setback as original character

class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        TrieNode root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, root, res);
            }
        }
        return res;

    }
    private void dfs(char[][] board, int i, int j, TrieNode node, List<String> res) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || node.next[board[i][j] - 'a'] == null || board[i][j] == '#') {
            return;
        }
        char c = board[i][j];
        node = node.next[c - 'a'];
        if (node.word != null) {
            res.add(node.word);
            node.word == null;
        }
        board[i][j] = '#';
        dfs(board, i - 1, j, node, res);
        dfs(board, i, j - 1, node, res);
        dfs(board, i + 1, j, node, res);
        dfs(board, i, j + 1, node, res);
        board[i][j] = c;

    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode pt = root;
            for (char c : word.toCharArray()) {
                if (pt.next[c - 'a'] == null) {
                    pt.next[c - 'a'] = new TrieNode();
                }
                pt = pt.next[c - 'a'];
            }
            pt.word = word;
        }
    }

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }
}

/*642. Design Search Autocomplete System*/


//trie + dfs
//use dfs to find all the paths with same prefix.

class AutocompleteSystem {
    TrieNode root;
    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        }
    }
    String curr_prefix = "";
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            insert(root, curr_prefix, 1);
            curr_prefix = "";
        } else {
            curr_prefix += c;
            List<Node> list = lookup(root, curr_prefix);
            Collections.sort(list, (a, b) -> (a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times));
            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).sentence);
            }
        }
        return res;

    }
    //take the curr_prefix, retrieve all the available sentences and its times.
    private List<Node> lookup(TrieNode t, String prefix) {
        List<Node> res = new ArrayList<>();
        for (char c : prefix.toCharArray()) {
            if (t.next[int_(c)] == null) {
                return new ArrayList<>();
            }
            t = t.next[int_(c)];
        }
        // update the trie pointer to the last char in the prefix, dfs!
        dfs(t, prefix, res);
        return res;
    }

    private void dfs(TrieNode t, String sentence, List<Node> res) {
        if (t.times > 0) {
            res.add(new Node(sentence, t.times));
//!!!!            return;
        }
        for (char i = 'a'; i <= 'z'; i++) {
            if (t.next[i - 'a'] != null) {
                dfs(t.next[i - 'a'], sentence + i, res);
            }
        }
        if (t.next[26] != null) {
            dfs(t.next[26], sentence + ' ', res);
        }
    }
    //helper function to get the position in the trie
    private int int_(char c) {
        return c == ' ' ? 26 : c - 'a';
    }
    // add sentencess to the trie tree
    private void insert(TrieNode t, String sentence, int times) {
        for (char c : sentence.toCharArray()) {
            if (t.next[int_(c)] == null) {
                t.next[int_(c)] = new TrieNode();
            }
            t = root.next[int_(c)];
        }
        t.times += times;
    }
    //create a trie structure
    class TrieNode {
        TrieNode[] next = new TrieNode[27]; // incluedes blank space
        int times = times;
    }

    //create a class to store sentences and its times
    class Node {
        String sentence;
        int times;
        Node(String s, int t) {
            this.sentence = s;
            this.times = t;
        }
    }
}


/*208. Implement Trie (Prefix Tree)*/

//trie
class Trie {

    class TrieNode {
        TrieNode[] next = new TrieNode[26];
        String word;
    }

    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode t = root;
        for (char c : word.toCharArray()) {
            if (t.next[c - 'a'] == null) {
                t.next[c - 'a'] = new TrieNode();
            }
            t = t.next[c - 'a'];
        }
        t.word = word;
    }

    public boolean search(String word) {
        TrieNode t = root;
        for (char c : word.toCharArray()) {
            if (t.next[c - 'a'] == null) {
                return false;
            }
            t = t.next[c - 'a'];
        }
        if (t.word != null && t.word.equals(word)) {
            return true;
        }
        return false;
    }

    public boolean startsWith(String prefix) {
        TrieNode t = root;
        for (char c : word.toCharArray()) {
            if (t.next[c - 'a'] == null) {
                return false;
            }
            t = t.next[c - 'a'];
        }
        return true;
    }
}


/*211. Add and Search Word - Data structure design*/

// how to handle '.' ?
// recursively traverse all paths, if it has one path, return true.

class WordDictionary {
    class TrieNode{
        TrieNode[] next = new TrieNode[26];
        String word = "";
    }
    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }
    public void addWord(String word) {
        TrieNode t = root;
        for (char c : word.toCharArray()) {
            if (t.next[c - 'a'] == null) {
                t.next[c - 'a'] = new TrieNode();
            }
            t = t.next[c - 'a'];
        }
        t.word = word;
    }
    public boolean search(String word) {

    }

    private boolean match(char[] chars, int index, TrieNode t) {
        //index为word长度时， trie指针刚好指到最后一个字母的地方，也就是存word string的叶子节点。
        if (index == chars.length) {
            return !t.word.equals("");
        }
        if (chars[index] == '.') {
            for (int i = 0; i < t.next.length; i++) {
                if (t.next[i] != null) {
                    if (match(chars, index  + 1, t.next[i])) {
                        return true;
                    }
                }
            }
        } else {
            return t.next[chars[index] - 'a'] != null && match(chars, index + 1, t.next[chars[index] - 'a']);
        }
        return false;
    }
}

/*336. Palindrome Pairs*/

//Palindrom property, given some existed palindrom, expand to make a bigger palindrom
//use hashmap for constant search.

class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        if (words == null || words.length == 0) {
            return res;
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++ ) {
            map.put(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                String str1 = words[i].substring(0, j);
                String str2 = words[i].substring(j);
                if (isPalindrome(str1)) {
                    String str2rvs = new StringBuilder(str2).reverse().toString();
                    if (map.containsKey(str2rvs) && map.get(str2rvs) != i) {
                        res.add(Arrays.asList(map.get(str2rvs), i));
                    }
                }
                if (!str2.isEmpty() && isPalindrome(str2)) {
                    String str1rvs = new StringBuilder(str1).reverse().toString();
                    if (map.containsKey(str1rvs) && map.get(str1rvs) != i) {
                        res.add(Arrays.asList(i, map.get(str1rvs)));
                    }
                }
            }
        }
        return res;

    }

    private boolean isPalindrome(String word) {
        char[] ch = word.toCharArray();
        int start = 0;
        int end = word.length() - 1;
        while (start < end) {
            if (ch[start] != ch[end]) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
}
/*127. Word Ladder*/
//bfs
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return 0;
        }
        Queue<String> q = new LinkedList<>();
        q.add(beginWord);
        boolean[] visited = new boolean[wordList.size()];
        int level = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String word = q.poll();
                if (word.equals(endWord)) {
                    return level;
                }
                for (int j = 0; j < wordList.size(); j++) {
                    if (visited[j] || !isTransferable(word, wordList.get(j))) {
                        continue;;
                    }
                    q.add(wordList.get(j));
                    visited[j] = true;
                }
            }
            level++;
        }
        return 0;
    }
    private boolean isTransferable(String w1, String w2) {
        int difference = w1.length();
        for (int i = 0; i < w1.length(); i++) {
            if (w1.charAt(i) == w2.charAt(i)) {
                difference--;
            }
        }
        if (difference == 1) {
            return true;
        }
        return false;
    }
}

/*909. Snakes and Ladders*/

//bfs, how to map number to matrix index

class Solution {
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> q = new LinkedList<>();
        boolean[] visited = new boolean[n * n + 1];
        q.offer(1);
        visited[1] = true;
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int num = q.poll();
                if (num == n * n) {
                    return level;
                }
                for (int j = 1; j <= 6; j++) {
                    int next = num + j;
                    if (next > n * n) {
                        continue;
                    }
                    int val = getBoardValue(board, next);
                    if (value != -1) {
                        next = val;
                    }
                    if (!visited[next]){
                        visited[next] = true;
                        q.offer(next);
                    }
                }
            }
            level++;
        }
        return -1;

    }

    private int getBoardValue(int[][] board, int num) {
        int n = board.length;
        int r = num - 1 / n;
        int x =  n - r - 1;
        int y = r % 2 == 0 ? num - r * n - 1 : n + r * n - num;
        return board[x][y];
    }
}


/*Coin Change*/

//dp, time: O(n * amount)
//ask the min amount, dp can process this kinda question.
//define transformation function: dp[i] = Math.min(dp[i], dp[i - coin] + 1);
/*假设当前求的是dp[11]，也就是想要凑齐11块钱需要最少的硬币数量，
* 当币种有一元，二元和五元的时候，外循环从一元遍历到五元， 五元这一行都是使用5元一次，
* 想要凑齐11元，使用5元一次，还剩6元， 因为我们已经计算过想要凑齐6元所需要的最少
* 的硬币数量（即使用一个5元，一个1元）， 我们将已知的子结果加上一个（5元）即3，我们
* 还要将当前这个必须使用一个5元的结果与之前不需要使用五元的结果比较一下，将较小值保存。*/


class Solution {
    public int cointChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }
}


/*937. Reorder Log Files*/

//custom sort

class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (a, b) -> {
            int idx1 = a.indexOf(' ') + 1;
            int idx2 = b.indexOf(' ') + 1;
            int ch1 = a.charAt(idx1);
            int ch2 = b.charAt(idx2);
            if (Characters.isLetter(ch1)) {
                if (Characters.isLetter(ch2)) {
                    return a.substring(idx1).compareTo(b.substring(idx2));
                } else {
                    return -1;
                }

            } else {
                if (Characters.isLetter(ch2)) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        return logs;
    }
}


/*347. Top K Frequent Elements*/

// heap + hashmap

class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a, b) -> (map.get(a) - map.get(b)));
        for (int key : map.keySet()) {
            minHeap.add(key);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }
        List<Integer> res = new ArrayList<>();
        while (k-- > 0) {
            res.add(minHeap.remove());
        }
        Collections.reverse(res);
        return res;
    }
}


/*373. Find K Pairs with Smallest Sums*/
//heap, need observation, what is the next candidate
/*维持一个小根堆，并使其永远保持size k
* 刚开始的时候把（i， 0）pair都加进去， i<=k, 我们已知最小
* pair一定是（0， 0），先将其poll出来把下一个candidte加进去
* 不断从堆中poll出一个放入output并添加下个candidate，下一个
* candidate就是第一个元素不变，第二个元素往上挪一个。
* */

class Solution {
    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> (a[0] + a[1] - b[0] - b[1]));
        List<int[]> res = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }
        // fisrt initialize the minHeap
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.add(nums1[i], nums2[0], 0);
        }

        //remove one to output, add next candidate to heap
        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] curr = minHeap.remove();
            res.add(new int[]{curr[0], curr[1]});
            if (curr[2] + 1 < nums2.length) {
                minHeap.add(new int[]{curr[0], nums2[curr[2] + 1], curr[2] + 1});
            }
        }
        return res;
    }
}


/*703. Kth Largest Element in a Stream*/
//priority q, kick out and maintain size k

class Solution {
    int k;
    PriorityQueue<Integer> minHeap;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        minHeap = new PriorityQueue<>((a, b) -> (a - b));
        for (int num : nums) {
            minHeap.add(num);
            if (minHeap.size() > k) {
                minHeap.remove();
            }
        }

    }
    public int add(int val) {
        minHeap.add(val);
        if (minHeap.size() > k) {
            minHeap.remove();
        }
        return minHeap.peek();
    }
}

/*252. Meeting Rooms*/

/*sort, comparator*/
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length < 2) {
            return true;
        }
        Arrays.sort(intervals, (a, b) -> (a.start - b.start));
        for (int i = 0; i < intervals.length - 1; i++) {
            if (intervals[i].end > intervals[i + 1].start) {
                return false;
            }
        }
        return true;
    }
}