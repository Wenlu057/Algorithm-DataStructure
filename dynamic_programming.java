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

// Bomb Enemy
/* Matrix*/
/*Solution 1, Brute force*/
/*Need to check the boundry, the wall position otherwise the edge
 *First check the boundry, then count the killed enemies.	
 */

class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') {
                    int[] boundry = checkBoundry(grid, i, j);
                    max = Math.max(max, helper(grid, i, j, boundry));
                }
            }
        }
        return max;
    }
    public int[] checkBoundry(char[][] grid, int i, int j) {
        int[] res = new int[4];
        res[0] = 0;
        res[1] = grid.length;
        res[2] = 0;
        res[3] = grid[0].length;
        for (int row = 0; row < grid.length; row++) {
            if (grid[row][j] == 'W') {
                if (row < i)
                    res[0] = row;
                if (row > i) {
                    res[1] = row;
                    break;
                }
            }
        }
        for (int col = 0; col  < grid[0].length; col ++) {
            if(grid[i][col] == 'W') {
                if (col < j)
                    res[2] = col;
                if (col > j) {
                    res[3] = col;
                    break;
                }
            }
        }
        return res;
    }
    public int helper(char[][] grid, int i, int j, int[] boundry) { 
        int count = 0;
        for (int row = boundry[0]; row < boundry[1]; row++) {
            if (grid[row][j] == 'E' ) {
             count++;   
            }
        }
        for (int col = boundry[2]; col < boundry[3]; col++) {
            if (grid[i][col] == 'E') {
             count++;   
            }
        }
        return count;
    }
}
// Solution 2, do the boundry check and enemies count at the same time.
class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if(grid==null || grid.length==0|| grid[0].length==0) return 0;
        int res=0;
        for(int i=0; i<grid.length; i++ ){
            for(int j=0; j<grid[0].length; j++){
                if(grid[i][j]=='0')
                    res = Math.max(res,helper(grid,i,j));
            }
        }
        return res;
    }
    int helper(char[][]grid, int i, int j){
        int res=0;
        int ti=i, tj=j;
        while(i>=0 && grid[i][j]!='W'){
            if(grid[i--][j]=='E') res++;
        }
        i=ti;
        while(j>=0 && grid[i][j]!='W'){
            if(grid[i][j--]=='E') res++;
        }
        j=tj;
        while(i<grid.length && grid[i][j]!='W'){
            if(grid[i++][j]=='E') res++;
        }
        i=ti;
        while(j<grid[0].length && grid[i][j]!='W'){
            if(grid[i][j++]=='E') res++;
        }
        return res;
    }
  
}

// Solution 3, use DP to save column hits.
class Solution {
	public int maxKilledEnemies(char[][] grid) {
		if (grid == null || grid.length == 0) return 0;
		int M = grid.length;
		int N = grid[0].length;
		int[] colhits = new int[N];
		int rowhits = 0;
		int max = 0;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (j == 0 || grid[i][j - 1] == 'W') {
					rowhits = 0;
					for (int k = j; k < N && grid[i][k] != 'W'; k++) {
						rowhits += grid[i][k] == 'E'? 1: 0;
					}
				}
				colhits[j] = 0;
				if (i == 0 || grid[i -1][j] == 'W') {
					for (int k = i; k < M && grid[k][j] != 'W'; k++) {
						colhits[j] += grid[k][j] == 'E' ? 1 : 0;
					}
				}
				if (grid[i][j] == '0') {
					max = Math.max(max, rowhits + colhits[j]);
				}
			}
		}
		return max;
	}
}

// Perfect Squares
/* Solution 1, DP*/
/*
 * create an array with length of n + 1, we update the array until
 * we get the nth element in the array which is the result.
 * Each element in the array stores the least number of perfect squares.
 * Initilize the array by update  all the perfect square within the range of n.
 */
class Solution {
    public int numSquares(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int i = n/2;
        for ( ; i > 0; i--) {
            if (i * i <= n) {
                break;
            }
        } 
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for(int j = 1; j <= i;j++) {
            dp[j * j] = 1;
        }

        for (int k = 2; k < n + 1; k++) {
            if (dp[k] == 0) {
                for (int m = 1; m < k; m++) {
                    if (dp[m] != 0 && dp[k - m] != 0) {
                        if (dp[k] == 0) dp[k] = dp[m] + dp[k - m];
                        else dp[k] = Math.min(dp[k], dp[m] + dp[k - m]);
                    }
                }
            }
        }
        return dp[n];
    }
}

/*Solution 2, DP*/
/*Similar with solution 1.
 *Iterate ALL the possibilities of the LAST perfect square number we add into current number.
 * ---------(j = 1, 4, 9, .... until j * j <=currNum)-------------
 *Whatever the last perfect suqare number is, we know the its result and the one which
 *subtract that number from currNum.
 */
class Solution {
    public int numSquares(int n) {
        // Write your code here
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // Remember this for loop format: i * i <= n!!!
        for(int i = 0; i * i <= n; ++i) {
            dp[i * i] = 1;
        }

        for (int i = 2; i <= n; ++i) {
            if (dp[i] == Integer.MAX_VALUE){
            	//Try to think in another way, try to find the last perfect square num.
                for (int j = 1; j * j <= i; ++j) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
                } 
            }
           
        }

        return dp[n];
    }
}
