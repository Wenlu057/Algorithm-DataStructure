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