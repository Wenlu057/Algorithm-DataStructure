# Algorithm-DataStructure

### JAVA SE 8 API

* **java.util.Array**

`static int binarySearch(int[] a, int fromIndex, int toIndex, int key)`

Searches a range of the specified array of ints for the specified value using the binary search algorithm. The range must be sorted prior to making this call. If it is not sorted, the results are undefined. If the range contains multiple elements with the specified value, there is no guarantee which one will be found.

Parameters:

a - the array to be searched

fromIndex - the index of the first element \(inclusive\) to be searched

toIndex - the index of the last element \(exclusive\) to be searched

key - the value to be searched for

Return the index of the search key,, if it is contained in the array within the specified range; otherwise, \(-\(insertion point\) - 1\)

_Example: Russian Doll Envelopes_

`static void sort(T[] a, Comparator<? super T> c`

Sorts the specified array of objects according to the order induced by the specified comparator. All elements in the array must be

mutually comparable by the specified comparator \(that is,`c.compare(e1, e2)).`

* java.util **Interface Comparator&lt;T&gt;**

Type Parameters:

T - the type of objects that maybe compared by the comparator.

`int compare(T o1, T o2)`

Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.

* **java.util.PriorityQueue&lt;E&gt;**

Type Parameters:

E - the type of elements held in this collection

`PriorityQueue(int initialCapacity, Comparator<? super E> comparator)`

Creates a PriorityQueue with the specified initial capacity that orders its elements according to the specified comparator.

_Example: Merge K Sorted Lists_

