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