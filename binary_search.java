/*
Binary Search: Search a sorted array by repeatedly dividing the Search
in half. Begin with an interval covering the whole array. If the value
of the search key is less than the item in the middle of the interval,
narrow the interval to the lower half. Ohterwise narrow it to the upper half.
Repeatedly check until the value is found or the interval is empty.
*/


/*
The time complexity of Binary Search can be written as 
T(n) = T(n/2) + c
O(logn)
*/

// Binary Search Template

// Java implementation of iterative Binary Search
class BinarySearch {
	//Returns index of x if it is present in arr[], else return -1
	int binarySearch(int arr[], int x) {
		int l = 0, r = arr.length - 1;
		while (l <= r) {
			int m = l + (r - l) / 2;
			//Check if x is present at mid
			if (arr[m] == x)
				return m;
			//If x is greater, ignore left half
			if (arr[m] < x)
				l = m + 1;

			//If x is smaller, ignore right half
			else
				r = m - 1;
	    }
	    // if we reach here, the element was not present
	    return -1;
	}
} 


// First Position of Target
/*
For a given sorted array and a target number, find the first index of this number in O(logn)
*/

class Solution {
	public int binarySearch(int[] nums, int target) {
		int l = 0, r = nums.length - 1; // be careful about the index of last element
		while( l < r) { // be careful about dead loop
			int m = l + (r - l) / 2; // be careful about how to assign middle index
			if(nums[m] < target) {
				l = m + 1;
			} else if (nums[m] > target) {
				r = m - 1;
			} else {
				r = m;
			}
		}
		if (nums[l] == target) return l; // check return status
		return -1;
	}

}

//Last Position of Target

/*
Keep an eye on boundry check to avoid dead loop.
*/

class Solution {
	public int binarySearch(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		while (l + 1 < r) { //to avoid dead loop
			int m = l + (r - l) / 2;
			if(nums[m] > target) {
				r = m;
			}else {
				l = m;
			}
		}
		if(nums[l] == target) return l;
		else if(nums[r] == target) return r;
		return -1;
	}
}

// Don't put the return statement within the while loop

// Search a 2D Matrix
/* 
Method 1 : find the correct row using binary search, then find
the correct element using binary search. 
Method 2: treat the 2D matrix as 1d array, use the binary search
to find the target element.
 */

class Solution {
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix.length == 0 || matrix[0].length == 0) return false;
		int start = 0, end = matrix.length * matrix[0].length - 1;
		int index = matrix[0].length;
		while(start + 1 < end){
			int middle = start + (end - start) / 2;
			if(matrix[middle/index][middle%index] < target) {
				start = middle;
			}else {
				end = middle;
			}
		}
		if(matrix[start/index][start%index] == target || matrix[end/index][end%index] == target) {
			return true;
		}
		return false;
	}
}

class Solution {
	public boolean searchMatrix(int[][] matrix, int target) {
		if(matrix.length == 0 || matrix[0].length == 0) return false;
		int rStart = 0, rEnd = matrix.length -1;
		int cStart = 0, cEnd = matrix[0].length - 1;
		//first binary search 
		while(rStart + 1 < rEnd) {
			int rMid = rStart + (rEnd - rStart) / 2;
			if(matrix[rMid][0] < target) {
				rStart = rMid;
			}else{
				rEnd = rMid;
			}
		}
		if(matrix[rStart][0] == target ||
			matrix[rEnd][0] == target)
			return true;
		int rIndex = matrix[rEnd][0] < target? rEnd : rStart;
		// second binary search
		while(cStart + 1 < cEnd) {
			int cMid = cStart + (cEnd - cStart) / 2;
			if(matrix[rIndex][cMid] < target){
				cStart = cMid;
			}else{
				cEnd = cMid;
			}
		}
		if(matrix[rIndex][cStart] == target || matrix[rIndex][cEnd] == target){
			return true;
		}
		return false;

	}
}

//find minimum in rotated sorted array
/*
first observe the rotated array, the minimux divide the array into half half.
The first half is ascending order, the second half is ascending order.
All the values in the second half are less than the values in the first half.
Use binary search, need to decide the interval. 
*/

// if we use the first element as the target value

class Solution {
	public int findMin(int[] nums) {
		int l = 0, r = nums.length - 1;
		while(l + 1 < r) {
			int mid = l + (r - l) / 2;
			ind first = nums[l];
			int last = nums[r];
			// we need check whether the whole array is in ascending order
			if(first <= nums[mid] && nums[mid] <= nums[last]){
				//the minimum is the first element
				r = l;
			}else{
				if(nums[mid] < first) {
					r = mid;
				}else {
					l = mid;
				}
			}

		}
		return nums[l] > nums[r] ? nums[r] : nums[l];
	}
}

//if we use the last elemnt as the target value
public class Solution {
    /*
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        int l = 0, r = nums.length - 1;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            int last = nums[r];
            if(nums[mid] < last) {
                r = mid;
            }else{
                l = mid;
            }
        }
        return nums[l] < nums[r] ? nums[l]:nums[r];
    }
}