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