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
