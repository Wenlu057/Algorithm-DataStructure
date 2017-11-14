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
 *first observe the rotated array, the minimum divides the array into half half.
 *The first half is ascending order, the second half is ascending order.
 *All the values in the second half are less than the values in the first half.
 *Use binary search, need to decide the interval. 
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


// find the peak element
/*
 * nums[0] < nums[1] && nums[n-2] > nums[n-1]
 * If it is the peak element, then nums[p] > nums[p-1] && nums[p] > nums[p+1]
 * We need to figure out which half is discarded. We observe that the left half of
 * the peak element is ascending order, the right half is descending order.
 */
//divide into two cases
public class Solution {
    public int findPeakElement(int[] nums) {
        int start=0,end=nums.length-1;
        int mid;
        while(start+1<end){
            mid=start+(end-start)/2;
            if(nums[mid]<nums[mid+1] && nums[mid] > nums[mid-1]) start=mid;
            else end=mid;
        }
        return nums[start]>nums[end]? start:end;

    }
}
//divide into 4 cases
public class Solution {
    /*
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
        if(A.length == 0) return 0;
        int start = 0, end = A.length - 1;
        while(start + 1 < end) {
            int mid = start + (end - start) / 2; 
            if(A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) return mid; // if it is the peak value
            else if(A[mid] > A[mid + 1]) end = mid; // if it is descending
                else if(A[mid] < A[mid + 1]) start = mid; // if it is ascending
                     else start = mid; // if it is the minimum
        }
        if(start > 0 && A[start] > A[end] ) return start;
        if(end < A.length - 2 && A[end] > A[start]) return end;
        return -1;
    }
}
/* Frequent Binary Search Problems */
////////////////////////////////////////////////////////////////////////////////////////////////

// Pow(x, n)
/*
 * use recursion, but also dynamic programming to save the intermediate result. otherwise stack overflow 
 * occurs. 
 * also be careful about negative n, and whether n is odd, or even.
 */
 public class Solution {
    /*
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        // write your code here
        if(x == 0) return 0;
        if(n == 0) return 1;
        if(n == 1) return x;
        if(n == -1) return 1/x;
        double half = myPow(x, n/2);
        if(n % 2 == 0) return half*half;
        else if(n > 0) return half * half * x;
             else return half*half/x;
    }
}

//Search in Roatated Sorted Array
/*
 * the roatated sorted array have two parts, the first half and second half is ascending.
 * all the elements in second half  are less than elements in the first half.
 * don't forget the case which the target is either the first elemnt or the last one.
 */

 public class Solution {
    /*
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] A, int target) {
        // write your code here
        if(A.length == 0) return -1;
        int l = 0, r = A.length - 1;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(A[mid] < A[r]) { //the middle point is in the second half
                if(A[mid] < target && target <= A[r]) {
                    l = mid; // in which case, we can discard all elements before middle point?
                }else{
                    r = mid;
                }
            }else{
                if(A[mid] > target && target >= A[l]) {
                    r= mid;
                }else{
                    l = mid;
                }
                
            }
        }
        if(target == A[l]) return l;
        if(target == A[r]) return r;
        return -1;
    }

  // Sqrt(x)
  /*
   * be careful if square value out of Intger maximum bound.
   * use mid > x/mid instead of mid * mid > x to avoid the above issue.
   */
   public class Solution {
    /*
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        if (x == 0) return 0;
        int l = 1, r = x / 2;
        while (l + 1 < r) {
            int mid = l + (r - l)/2;
            if (mid > x/mid) {
                r = mid;
            } else {
                l = mid ;
            }
        }
        System.out.println(l);
        if(l+1 > x/(l + 1)) return l;
        else return r;
        
    }
  // divide two integers

  /*
   * cannot use multiplication, division and mod operator.
   * then only bitwise operation is available.
   * try to find n times divisor which is the closest to dividend,
   * which n is a multiple of two. 
   * repeate the above step and accumulate n to get the answer.
   * /

  /*
   * Notes:
   * num << i means num multiply i times 2
   */

  class Solution {
  	public int divide (int dividend, int divisor) {
  		//handle special cases
  		if(divisor == 0) return Intger.MAX_VALUE;
  		if(divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
  		//get positive values
  		long pDividend = Math.abs((long)dividend);
  		long pDivisor = Math.abs((long)divisor);
  		int result = 0;
  		while(pDividend >= pDivisor){
  			int numShift = 0;
  			while(pDividend >= (pDivisor << numShift)){
  				numShift++;
  			}
  			result += 1 << (numShift - 1);
  			pDividend -= (pDivisor<<(numShift - 1));
  		}
  		if((dividend>0 && divisor>0) || (dividend<0 && divisor<0)){
            return result;
        }else{
            return -result;
        }
  	}
  }
  //First Bad Version
  /*
   *similar with first position of target.
   */
  /**
    * public class SVNRepo {
    *     public static boolean isBadVersion(int k);
    * }
    * you can use SVNRepo.isBadVersion(k) to judge whether 
    * the kth code version is bad or not.
  */

  public class Solution {
    /*
     * @param n: An integer
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        // write your code here
        int l = 1, r = n;
        while( l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(SVNRepo.isBadVersion(mid)== true){
                r = mid;
            }else{
                l = mid;
            }
        }
        return SVNRepo.isBadVersion(l)==true ? l: r;
    }
  }

  //Search Insert Position
  /*
   *If the target is not found, need to determine the insert position.
   *When we exit from the loop, there are 3 cases, in the left, in ther middle, in ther right
   *Write return statement for each case.
   */ 

  public class Solution {
    /*
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
        if(A.length == 0) return 0;
        int l = 0, r = A.length - 1;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(A[mid] == target) return mid;
            if(A[mid] < target) {
                l = mid;
            }else{
                r = mid;
            }
        }
        if(target <= A[l] ) return l;
        else if(target > A[l] && target <= A[r]) return r;
            else return r + 1;
        
    }
  }
