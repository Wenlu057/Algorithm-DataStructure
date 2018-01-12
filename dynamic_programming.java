// Longest Increasing Subsequence

/*
 * 2 ways to implement.
 * Traditional DP O(n*n)
 * Binary Search O(nlgn)	
 */

/*First approach*/
/*
 * step 1st: initialize an array to store intermediate result. Purpose is to avoid duplicate computation.
 * step 2nd: write the for loop logic, how to utilize the previous result to update the array.
 * use extra space to store the max 
 * be careful whether the updated value is greater than the existed val, otherwise the correct one
 * will be overrided. 
 */

public class Solution {
	public int longestIncreasingSubsequence (int[] nums){
		int[] result = new int[nums.length]; //store the count, update the max in the count array.
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < nums.length; i++) {
			result[i] = 1;
			for(int j = 0; j < i; j++){
				result[i] = result[i] > result[j] + 1 ? result[i]: result[j] + 1;
				//result[i] = result[j] + 1 will fail on use case: 9, 3, 6, 2, 7
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
 * The intermediate array is designed to store the actual element in the input array instead of the count.
 * The saved elements is in sorted order and will alrways derive the length of longest increasing subsequence.
 * Key point is how to insert the element into this array and how to initialize the array.
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


//Russian Doll Envelopes
/*Binary Search, Dynamic Programming*/
/*
 * Follow-up and advanced version of LIS. 
 * Key point is how to SORT the envelopes, how to insert into the correct position.
 * The width is sorted in ascending order, if the width is the same, orted in "descending" order.
 * Iterate and do the same thing as in LIS on the height of envelopes.
 * Use Java Built-in binary search method.
 * Use Comparator to sort
 */

public class Solution {
	public int maxEnvelopes(int[][] envelopes) {
		if(envelopes.length == 0 || envelopes == null
			|| envelopes[0] == null || envelopes[0].length != 2) {
			return 0;
		}
		Arrays.sort(envelopes, new Comparator<int[]>(){
			public int compare(int[] arr1, int[] arr2){
				if (arr1[0] == arr2[0]) {
					return arr2[1] - arr1[1];
				} else {
					return arr1[0] - arr2[0];
				}
			}
		});
		int[] dp = new int[envelopes.length];
		length = 0;
		for (int[] envelop : envelopes) {
			int index = Arrays.binarySearch(dp, 0, len, envelop[1]); // we search in range [0, len)
			if (index < 0){
				index = -index - 1;
			} // we didn't find the envelop[1]
			dp[index] = envelop[1]; 
			// if envelop is the largest, we add it at the tail(index = len)
			// otherwise, determine the insert position, update the dp array 
			if (length == index) {
				length ++; 
			//update the length only if the length is equal to the index, mean it is the largest.
			}
		}

		return length;
	}
}// Maximum Vacation Days
/*Solution 1 Recursion, DFS using ArrayList as one argument of recursion call*/
class Solution {
    int MaxVacation = 0;
    public int maxVacationDays(int[][] flights, int[][] days) {
        if (flights == null || flights.length == 0 || days == null || days.length == 0) return 0;
        int max = 0;
        int lastCity = 0;
        List<Integer> vacations = new ArrayList<>();
        helper(0, 0, vacations, flights, days);
        return MaxVacation;
        
    }
    public void helper(int currWeek, int lastCity, List<Integer> vacations, int[][] flights, int[][] days) {
        if (currWeek == days[0].length) {
            int sum = 0;
            for(int vacation : vacations) {
                sum += vacation;
            }
            MaxVacation = Math.max(MaxVacation, sum);
            return;
        }
        for (int i = 0; i < days.length; i++) {
            if (flights[lastCity][i] != 0 || lastCity == i) {
                vacations.add(days[i][currWeek]);
                helper(currWeek + 1, i, vacations, flights, days);
                vacations.remove(vacations.size() - 1);
            }
        }
    }
}
/*Solution 1-1 Recursion, DFS using integer as one argument for the recursion*/
class Solution {
    int MaxVacation = 0;
    public int maxVacationDays(int[][] flights, int[][] days) {
        if (flights == null || flights.length == 0 || days == null || days.length == 0) return 0;
        helper(0, 0, 0, flights, days);
        return MaxVacation;
        
    }
    public void helper(int week, int curr, int sum, int[][] flights, int[][] days) {
        //sum = 0 - loop1, sum = 1 - loop2, sum = 4 - loop3, sum = 5 -loop4
        if (week == days[0].length) {
            MaxVacation = Math.max(MaxVacation, sum);
            return;
        }
        // for recursion, we go deeper each time, and only execute once for the for loop.
        // when you reach the end and return to the upper level to execute the second time of the for loop,
        // we hope the result keeps the same as if the recursion call doesn't change anything!
        // this is the key point of backtracking!!!!!!
        for (int dest = 0; dest < days.length; dest++) {
            if (flights[curr][dest] != 0 || curr == dest) {
                // sum += days[dest][week]; // sum = 1 - loop1, sum = 4 -loop2, sum = 5 - loop3
                helper(week + 1, dest, sum + days[dest][week], flights, days);
                // loop4 returns to loop3, sum is 5!! it will cause error. we want it to be 4.
            }
        }
    }
}

/*Solution 2 Dynamic Programming, dp[k] represents the max vacation days
 *we can get until week i, for i = 0 ... total weeks. Update the dp array
 *and overide the val in each loop.
 */
class Solution {
	public int maxVacationDays(int[][] flights, int[][] days) {
		int[] dp = new int[flights.length];
		Arrays.fill(dp, Integer.MIN_VALUE);
		dp[0] = 0; // entry point
		for (int week = 0; week < days[0].length; week++) {
			int[] tmp = new tmp[flights.length];
			Array.fill(tmp, Integer.MIN_VALUE);
			for (int currCity = 0; currCity < flights.length; currCity++) {
				// update tmp[currCity]
				for (int preCity = 0; preCity < flights.length; preCity++) {
					if(currCity == preCity || flights[preCity][currCity] == 1){
						tmp[currCity] = Math.max(tmp[currCity], 
							days[currCity][week] + dp[preCity])
					}	
				}
				// get the max vacation days if stays in currCity at week 0 .. N
				dp = tmp; // update dp
			}
		}
		int max = 0;
		for (int v : dp) {
			max = Math.max(max, v);
		}
		return max;
	}
}