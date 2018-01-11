# Algorithm-DataStructure

---

### JAVA SE 8 API

* **java.util.Array**

```java
static int binarySearch(int[] a, int fromIndex,int toIndex, int key)
```

Searches a range of the specified array of ints for the specified value using the binary search algorithm. The range must be sorted prior to making this call. If it is not sorted, the results are undefined. If the range contains multiple elements with the specified value, there is no guarantee which one will be found.

Parameters:

a - the array to be searched

fromIndex - the index of the first element \(inclusive\) to be searched

toIndex - the index of the last element \(exclusive\) to be searched

key - the value to be searched for

Return the index of the search key,, if it is contained in the array within the specified range; otherwise, \(-\(insertion point\) - 1\)

* [ ] _Example: Russian Doll Envelopes_

_**Use Binary Search to find insert position.**_

```java
static void sort(T[] a, Comparator<? super T> c
```

Sorts the specified array of objects according to the order induced by the specified comparator. All elements in the array must be

mutually comparable by the specified comparator \(that is,`c.compare(e1, e2)).`

* java.util **Interface Comparator&lt;T&gt;**

Type Parameters:

T - the type of objects that maybe compared by the comparator.

```java
int compare(T o1, T o2)
```

Compares its two arguments for order. Returns a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.

**Lambda Expression:**[**http://www.java67.com/2014/11/java-8-comparator-example-using-lambda-expression.html**](http://www.java67.com/2014/11/java-8-comparator-example-using-lambda-expression.html)

[![](https://2.bp.blogspot.com/-Vs6FZVdzIkQ/VtMMdfADCqI/AAAAAAAAE9s/TlcaZrJoC0g/s640/Java%2B8%2BComparator%2Bexample.png "How to implement Comparator in Java 8 using lambdas")](https://click.linksynergy.com/deeplink?id=JVFxdTr9V80&mid=39197&murl=https%3A%2F%2Fwww.udemy.com%2Fjava-basics-for-j2ee-and-android%2F)

* [ ] _Example: Russian Doll Envelopes, Merge K Sorted List, Skyline Problem_

* **java.util.PriorityQueue&lt;E&gt;**

Type Parameters:

E - the type of elements held in this collection

```java
PriorityQueue(int initialCapacity, Comparator<? super E> comparator)
```

Creates a PriorityQueue with the specified initial capacity that orders its elements according to the specified comparator.

* [ ] _Example: Merge K Sorted Lists, Skyline Problem_

* java.util **Interface List&lt;E&gt;  ** ------SuperInterface Collection&lt;E&gt;

```java
boolean addAll(Collection<? extends E> c)
```

Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator \(optional operation\).

---

### Average-Related Preblem Analysis

array: \[a1, a2, a3, a4, a5, a6\]

**average &gt; minimum value &&  average &lt; maximum value**

Maintain a sum array, each time add the difference between current element and average.

\[0, a1 - average, a1 - average + a2 - average, a1 - average + a2 - average + a3 - average, .....

sum\[0, m\] - sum\[0, n\] = sum\[n, m\] -- consider it as setAlgorithm-DataStructure

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

