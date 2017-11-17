// Longest Increasing Subsequence

/*
 * 2 ways to implement.
 * Traditional DP O(n*n)
 * Binary Search O(nlgn)	
 */

/*First approach*/
/*
 * step 1st: initialize an array to store intermediate result. Purpose it to avoid duplicate computation.
 * step 2nd: write the for loop logic, how to utilize the previous result to update the array.
 * use extra space to store the max 
 * be careful whether the updated value is greater than the existed val, otherwise the correct one
 * will be overrided. 
 */

public class Solution {
	public int longestIncreasingSubsequence (int[] nums){
		int[] result = new int[nums.length];
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < nums.length; i++) {
			result[i] = 1;
			for(int j = 0; j < i; j++){
				result[i] = result[i] > result[j] + 1 ? result[i]: result[j] + 1;
			}
			if(result[i] > max) {
				max = result[i];
			}
		} 
		return max;
	}

}

/*Second approach*/
/*
 * Use binary search to update the intermediate result.
 * The intermediate array is designed to store the length of last longest increasing subsequence.
 * Try to find the position in the lastMin array to insert the current element in nums.
 * The index of the last element which value is not Integer.MAX_VALUE is the result.
 */

public class Solution {
	public int longestIncreasingSubsequence(int[] nums){
		int[] lastMin = new int[nums.length + 1];
		lastMin[0] = Integer.MIN_VALUE;
		for(int i = 1; i <= nums.length; i++){
			lastMin[i] = Integer.MAX_VALUE;
		}
		for(int i = 0; i < nums.length; i++){
			int index = binarySearch(lastMin, nums[i]);
			lastMin[index] = nums[i];
		}
		for(int i = nums.length; i >= 1; i--){
			if(lastMin[i] != Integer.MAX_VALUE){
				return i;
			}
		}
		return -1;
	}
	public int binarySearch(int[] lastMin, int num) {
		int start = 0, end = lastMin.length - 1;
		while(start + 1 < end) {
			int mid = start + (end - start) / 2;
			if(lastMin[mid] < num) {
				start = mid;
			}else{
				end = mid;
			}
		}
		return end;
	}
}