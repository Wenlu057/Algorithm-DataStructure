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

//找第一个大于target的数
public int binarySearch(int[] arr, int target) {
        int low = 0;
        int high = psum.size() - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (target >= target.get(mid)) { //若当前数等于target，我们要找第一个大于它的，所以当前肯定不是
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
}

//找第一个大于或等于target的数 (模糊查找）， 或找target出现的首位置
public int binarySearch(int[] arr, int target) {
    int l = 0, r = arr.length;
    while (l < r){ //退出条件是l和r相等
        int mid = l + (r - l) / 2;
        if (arr[mid] < target) {
            l = mid + 1; //若当前数比target小，我们要找第一个相等或大于的数，我们知道当前数一定不是我们想要的
        } else {
            r = mid; //若当前数比target大或相等，我们不确定是否是第一个，所以不能排除; 因为是找第一个当碰到相等的应该扔掉右边继续往左找
        }
    }
    //退出时l和r指向的是一个数， 只要保证能指向一个就不会死循环
    return l;
}

//找最后一个等于target的数
public int binarySearch(int[] arr, int target) {
        int l = 0;
        int r = A.length - 1;
        while (l  < r) {
            int mid = l + (r - l) / 2 + 1; //确保当只有两个时，mid = r，强制其更新避免死循环
            if (A[mid] <= target) {
                l = mid; // 若当前等于target，我们不确定是否是最后一个，因为是找最后一个所以把左边排除继续向右找
            } else {
                r = mid - 1;
            }
        }
        return A[l] > A[r] ? l : r;
}

//在有序数组中找是否存在一个数（exact search）
public int binarySearch(int[] arr, int target) {
    int l = 0, r = arr.length;
    while (l < r){ //退出条件是l和r相等
        int mid = l + (r - l) / 2;
        if (arr[mid] == target) {
            return true;
        } else if (arr[mid] < target) {
            l = mid + 1; //若当前数比target小，我们要找相等的数，我们知道当前数一定不是我们想要的
        } else {
            r = mid + 1; //若当前数比target大，我们要找相等的数，我们知道当前数一定不是我们想要的
        }
    }
    //退出时l和r指向的是一个，需要在判断一下
    return arr[l] == target;
}

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
			int  first = nums[l];
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
//first determine the position of the middle point, then determine the position of target
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
  /*
   * Example : dividend 30 divisor 3
   * 1st loop : 30 < 3 * 2 * 2 * 2 * 2 numshift = 4, add 2 * 2 * 2 into res, 30 - 3 * 2 * 2 * 2 = 6
   * 2nd loop : 6 < 3 * 2 numshift = 2, add 2 into res, 6 - 3 * 2 = 0
   * exit
   * result : 101 in binary
   */
  class Solution {
      public int divide(int dividend, int divisor) {
          // Handle abnormal cases caused by overflow
          if (divisor == 0) return Integer.MAX_VALUE;
          if (dividend == Integer.MIN_VALUE && divisor == -1) return Integer.MAX_VALUE;
          // change to positive
          Long pDividend = Math.abs((long)dividend);
          Long pDivisor = Math.abs((long)divisor);
          // lock ALL 1's position in binary format
          int res = 0;
          // think of exit condition
          while (pDividend >= pDivisor){
              // count left shift
              int numShift = 0;
              while (pDividend >= (pDivisor << numShift)){ // pDivisor * 2 * 2 * 2 ... * 2
                  numShift++;
              }
              // find most significant bit,...., least significant bit
              res += 1 << (numShift - 1)
              // Substract the value in current loop from dividend
              pDividend -= pDivisor << (numShift - 1);
          }
          if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0))
              return result;
          else
              return - result;
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
        // when exiting the loop, in most cases the target is between the A[l] and A[r]
        //if (r == A.length - 1 && target > A[r]) return A.length;
        //else if (l == 0 && target <= A[l]) return 0;
        //else return r;
        
    }
  }

  //Search for a Range
  /*
   * Combination problem of first target postion and last target position.
   * no need to run binary search twice, first we use binary search to find 
   * the first position, and then iterate until we find the index right after 
   * the last element. 
   */
  public class Solution {
    /*
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        // write your code here
        if(A.length == 0) return new int[]{-1,-1};
        int l = 0, r = A.length - 1;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(A[mid] < target) {
                l = mid;
            }else{
                r = mid;
            }
        }
        if(A[l] != target && A[r] != target) return new int[]{-1, -1}; // don't forget the case when the target is not found in the array.
        int end = r;
        while(end < A.length && A[end] == target) {
                end++;
        }
        int start = A[l] == target? l:r;
        return new int[]{start,end-1};
    }
  }
  //Search in Rotated Sorted Array II
  /*
   *Compare with the basic question. This follow up question needs to 
   *consider the following case and how to deal with it.
   *nums[start] == nums[mid] == nums[end]
   *We can remove duplicate from one side once at a time, or remove
   *all the duplicates from both left and right.
   */
  // Solution 1 Remove duplicates once a time
  // Trick: try to compare with the last element instead of the first.
  public class Solution {
    /*
     * @param A: an integer ratated sorted array and duplicates are allowed
     * @param target: An integer
     * @return: a boolean 
     */
    public boolean search(int[] A, int target) {
        // write your code here
        if(A.length == 0) return false;
        int l = 0, r = A.length - 1;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            if (A[mid] > A[r]) {
                if(A[l] <= target && target < A[mid] ){ //Don't forget the case which the target is the A[l]
                    r = mid;
                }else{
                    l = mid;
                }
            }else if(A[mid] < A[r]){
                if(A[mid] < target && target <= A[r]) { // Don't forget the case which the target is A[r]
                    l = mid;
                }else{
                    r = mid;
                }
            }else{
                //Whether it's r-- or l ++ depends A[mid] compare with A[r] or A[l]
                // if it compares with A[l], we can use l++, the goal is to avoid the case that we remove unique element from array.
                r--; // remove one duplicate from left, l++ also works
            }
        }
        if(A[l] == target || A[r] == target) return true;
        else return false;
    }
  }
  // Solution 2 Remove all duplicates at a time
       public class Solution {
           public boolean search(int[] A, int target) {
               if (A.length == 0) return false;
               int l = 0, r = A.length - 1;
               while (l + 1 < r) {
                   int mid = l + (r - l) / 2;
                   if (A[l] == A[r]) {
                       if (target == A[l]) return true;
                       int val = A[l];
                       while (A[l] == val && l + 1 < r) {
                           l ++; // remove all duplicates from left;
                       }
                       while (A[r] == val && l + 1 < r) {
                           r --; // remove all duplicates from right;
                       }
                       mid = l + (r - l) / 2;
                   }
                   // find the target as we don't have any duplicates.
                   if (A[r] < A[mid]) {
                       if (target >= A[l] && A[mid] > target) {
                           r = mid;
                       } else {
                           l = mid;
                       }
                   } else {
                       if (target <= A[r] && A[mid] < target) {
                           l = mid;
                       } else {
                           r = mid;
                       }
                   }
               }
               if (A[l] == target || A[r] == target) return true;
               return false;
           }
       }
    

  //Find Minimum in Rotated Sorted Array II
  /*
   * Same as above, be careful about nums[start] == nums[mid] == nums[end]
   * For this question, we cannot remove from start, it will elliminate the
   * minimum value from candidacy. 
   */

  public class Solution {
    /*
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        if(nums.length == 1) return nums[0];
        int l = 0, r = nums.length - 1;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] > nums[r]){
                l = mid;
            }else if (nums[mid] < nums[r]){
                r = mid;
            }else{
                r--;
            }
        }
        return nums[l] < nums[r] ? nums[l] : nums[r];
    }
  }

  // Wood Cut
  /*
   * If the length of one piece of wook is less than the maximum length, we can discard it.
   * We only need to guarantee  that we have equal or more than k pieces of wood with maximum length.
   * We can use O(n) time to find the maximum in these n pieces of wood. We know that the overall maximum is
   * greater than the desired maximum length. Then we use the binary search to find the correct answer.
   * Be caueful about the boundry value.
   * You need to calculate the total pieces which satisfy the requirment in every check. 
   */

  public class Solution {
    /*
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        int result = 0;
        int maxNum = Integer.MAX_VALUE;
        // find the maxNum 
        for(int i = 0; i < L.length; i++) {
            if(L[i] > maxNum) maxNum = L[i];
        }
        result = binarySearch(L, k, maxNum );
        return result;

    }
    public int binarySearch(int[] L, int k, int maxNum){
        int maxLen = 0;
        int l = 0, r = maxNum;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            int num = checkValidity(mid, L);
            if(num >= k){
                l = mid;
                if(mid > maxLen) maxLen = mid;
            }else{
                r = mid;
            }
            
        }
        if(checkValidity(r,L) >= k && r > maxLen) return r;
        // be careful if l is equal to 0
        if(l!=0){
            if(checkValidity(l,L) >= k && l > maxLen) return l;
        }
        return maxLen;
    }
    // calculate the total pieces of wood which length is equal to the first passing argument 
    public int checkValidity(int length, int[] L) {
        int num = 0;
        for(int i = 0; i < L.length; i++) {
           num += L[i] / length;
        }
        return num;
    }
  }


  // Classical Binary Search
  /*
   * Binary Search template
   */
  public class Solution {
    /*
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int findPosition(int[] nums, int target) {
        // write your code here
        if(nums.length == 0) return -1;
        int l = 0, r = nums.length - 1;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            if(nums[mid] == target) return mid;
            if(nums[mid] < target) {
                l = mid;
            }else{
                r = mid;
            }
        }
        if(nums[l] == target) return l;
        if(nums[r] == target) return r;
        return -1;
    }
  }

  //Intersection of Two Arrays
  /*
   * two ways to implement. 
   * approach 1 : use hashmap to remove duplicate, use another hashmap to record the result.
   * approach 2 : sort the array, then use binary search in array 1,  use ArrayList to record the result.
   * How to iterate the list/hashmap
   */

    /*First approach*/
    public class Solution {
    
    /*
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2){
        HashMap<Integer, Boolean> map1 = new HashMap<>();
        HashMap<Integer, Boolean> intersectMap = new HashMap<>();
        for(int i = 0; i < nums1.length; i++) {
            if(!map1.containsKey(nums1[i])){
                map1.put(nums1[i], true);
            }
        }
        for(int j = 0; j < nums2.length; j++) {
            if(map1.containsKey(nums2[j]) && !intersectMap.containsKey(nums2[j])){
                intersectMap.put(nums2[j], true);
            }
        }
        int[] res = new int[intersectMap.size()];
        int i = 0;
        for(int e : intersectMap.keySet()){
            res[i] = e;
            i++;
        }
        return res;
    }
  };
  /*Second approach*/
  public class Solution {
    
    /*
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // write your code here
        Arrays.sort(nums1);
        if(nums1.length == 0 || nums2.length == 0) return new int[]{};
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums2.length; i++) {
            if(binarySearch(nums1, nums2[i])){
                if(!res.contains(nums2[i])){
                    res.add(nums2[i]);
                }
            }
        }
        int[] arr = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            arr[i] = res.get(i);
        }
        return arr;
    }
    public boolean binarySearch(int[] nums, int num){
        int l = 0, r = nums.length - 1;
        while(l + 1 < r){
            int mid = l + (r - l) / 2;
            if(nums[mid] == num) return true;
            if(nums[mid] < num) l = mid;
            else r = mid;
        }
        if(nums[l] == num || nums[r] == num) return true;
        return false;
    }
};

// Intersection of Two Arrays II
/*
 * Use hashmap to save nums1 and result, record the times it appears in nums1
 */
public class Solution {
    
    /*
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // write your code here
        if(nums1.length == 0 || nums2.length == 0) return new int[]{};
        HashMap<Integer, Integer> map1 = new HashMap<>();
        HashMap<Integer, Integer> intersectMap = new HashMap<>();
        List<Integer> ls = new ArrayList<>();
        for(int i = 0; i < nums1.length; i++) {
            if(!map1.containsKey(nums1[i])){
                map1.put(nums1[i], 1);
            }else{
                map1.put(nums1[i], map1.get(nums1[i]) + 1);
            }
        }
        for(int j = 0; j < nums2.length; j++) {
            if(map1.containsKey(nums2[j]) && map1.get(nums2[j]) > 0){
                map1.put(nums2[j], map1.get(nums2[j]) - 1);
                if (!intersectMap.containsKey(nums2[j])) {
                    intersectMap.put(nums2[j], 1);
                } else {
                    intersectMap.put(nums2[j], intersectMap.get(nums2[j]) + 1);
                }
            }
        }
        int sum = 0;
        for(int e : intersectMap.keySet()){
            int count = intersectMap.get(e);
            sum += count;
            for(int i = 0; i < count; i++){
                ls.add(e);
            }
        }
        int[] res = new int[sum];
        for(int i = 0; i < ls.size(); i++){
            res[i] = ls.get(i);
        }
        return res;
    }
};

//Maximum Average Subarray
/*
 *key points:
 *1, use a double array to save the sum of difference with expected average value for each element
 *2, the difference of i th sum of difference and j th sum of difference
 *3, the length should be greater or equal to given length k
 *4, the average value is between the lowest and highest value in the array
 *5, binary search to find the maximum average
 */

public class Solution {
    /*
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        // write your code here
        double high = Integer.MIN_VALUE;
        double low = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > high) {
                high = nums[i];
            }
            if(nums[i] < low){
                low = nums[i];
            }
        }
        while(high - low >= 1e-6) {
            double mid = low + (high - low) / 2;
            if(search(nums, k, mid)){
                low = mid;
            }else{
                high = mid;
            }
        }
        return high;
    }
    
    public boolean search(int[] nums, int k, double mid) {
        double min = 0;
        double[] sum = new double[nums.length + 1];
        sum[0] = 0;
        for(int i = 1; i <= nums.length; i++){
            sum[i] = sum[i - 1] + nums[i - 1] - mid; // add the diffrence with mid for each element
            //guarantee the length of the subarray is no less than k 
            if(i >= k && sum[i] >= min) {
                return true;
            }
            // decide whether to add elements before previous k elements                                                        
            if(i >= k){
                min = Math.min(min, sum[i - k + 1]);
            }
        }
        return false;
    }
}
// Guess Number Game
/*
 * easy binary search
 */

/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    /**
     * @param n an integer
     * @return the number you guess
     */
    public int guessNumber(int n) {
        // Write your code here
        int l = 1, r = n;
        while(l + 1 < r) {
            int mid = l + (r - l) / 2;
            int res = guess(mid);
            if(res == 0) return mid;
            if(res < 0) {
                r = mid;
            }
            if(res > 0) {
                l = mid;
            }
        }
        if(guess(l) == 0) return l;
        if(guess(r) == 0) return r;
        return -1;
    }
}

// Find the Duplicate Number
/*
 * Count how many times the number is less than the middle.
 * We cannot modify the array, so sort is not allowed.
 * We cannot use extra space, so hash table is not allowed.
 * The time complexily must be less than O(n*n), so we shall consider binary search
 * We search in range [1, n], count and judge which part to be discarded.
 */

public class Solution {
    /*
     * @param nums: an array containing n + 1 integers which is between 1 and n
     * @return: the duplicate one
     */
    public int findDuplicate(int[] nums) {
        // write your code here

        int l = 1, r = nums.length - 1;
        while(l < r) {
            int mid = l +(r - l) / 2;
            int count = 0;
            for(int i = 0; i < nums.length; i++) {
                if(nums[i] <= mid)
                    count ++;
            }
            if(count <= mid) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}

//Solution 2 : use l + 1 < r
       public class Solution {
           /*
            * @param nums: an array containing n + 1 integers which is between 1 and n
            * @return: the duplicate one
            */
           public int findDuplicate(int[] nums) {
               // write your code here
               
               int l = 1, r = nums.length - 1;
               while(l + 1< r) {
                   int mid = l +(r - l) / 2;
                   int count = 0;
                   for(int i = 0; i < nums.length; i++) {
                       if(nums[i] <= mid)
                           count ++;
                   }
                   if(count <= mid) l = mid;
                   else r = mid;
               }
               int count = 0;
               for(int num : nums) {
                   
                   if (num == l) {
                       count ++;
                   }
               }
               return count > 1 ? l : r;
           }
       }
