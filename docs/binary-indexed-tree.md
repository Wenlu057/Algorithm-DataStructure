## Data Structure : Binary Indexed Tree

**Get prefix sum of an array.**

Other approach:

* prefix array : if too many updates, it takes extra time 
* segment tree : takes extra space, more complicated

Space Complexity : O\(n\)  
Search Complexity: O\(logN\)  
Initialization Complexity: nO\(logN\)  
Update Complexity: O\(logN\)

**Key Idea:**

* Each integer can be represented as sum of powers of two.
* Flip right-most bit to get its parent.
* Calculate parent of a node
  1. compute 2's complement
  2. AND original node val
  3. subtract from original val
* Calculate next node 
  1. compute 2's complement
  2. AND original node val
  3. add to original val

![](/assets/BIT.png)

![](/assets/BITMemo.png)

