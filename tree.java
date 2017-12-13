/*
 Binary Tree 
 Try to use divide and conquer to solv tree problems,
 since it can divide into two subproblems. 	
 */

/*Binary Tree Data Structure*/
public class TreeNode {
	int val;
	TreeNode left; 
	TreeNode right;
	TreeNode(int x) { val = x; }
}


//Binary Tree Traversal

//Method 1: traversal
/*
 * Deep First Search
 * First traverse as deep as possible, then trace back.
 * Usually if the return resule is a list, you should put it in argument list.
 * In each iteration, the list is updated to record the trace.
 * This method needs to check every node and relative logic operation is done accordingly.
 */

class Solution {
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		helper(root, res);
		return res;
	}
	public void helper(TreeNode root, List<Integer> ls) {
		if (root == null) return;
		ls.add(root.val);
		helper(root.left, ls);
		helper(root.right, ls);
	}
}

//Method 2: Divide and Conquer

/*
 * Treat the whole problem as several sub-problems.
 * Each sub-problem can use same way to solve, 
 * and the final anwser can be computed by merging 
 * all the sub-answers.
 * It's like the queen and two workers. The queen only
 * gets the feedbacks from two workers and summarize them.
 */

class Solution {
	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) return res;
		List<Integer> left = preorderTraversal(root.left);
		List<Integer> right = preorderTraversal(root.right);
		res.add(root.val);
		res.addAll(left);
		res.addAll(right);
		return res;
	}
}

//Method 3: No recursion

/*
 * Continuously push and poll elements from stack.
 * Write while loop and the exit condition is the stack is not empty.
 * First push the right node, then left one, since stack is FILO.
 */

class Solution {
	public List<Integer> preorderTraversal (TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> st = new Stack<>();
		if(root == null) return res;
		st.push(root);
		while (!st.isEmpty()) {
			TreeNode temp = st.pop();
			res.add(temp.val);
			if (temp.right != null) {
				st.push(temp.right);
			}
			if (temp.left != null) {
				st.push(temp.left);
			}
		}
		return res;
	}
}

// Maximum Depth of Binary Tree
/*
 * Divide and Conquer
 * Check maximum depth of left subtree and right subtree
 * return total
 */

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: An integer.
     */
    public int maxDepth(TreeNode root) {
        // write your code here
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return left > right? left + 1 : right + 1;
    }
}

// Balanced Binary Tree
/*
 * Build ResultType with depth and isBalanced.
 * Divide and Conquer
 */
class Solution {
	public boolean isBalanced(TreeNode root) {
		if(root == null) return true;
		ResultType rs = helper(root);
		return rs.balanced;
	}
	public ResultType helper(TreeNode root) {
		if (root == null) return new ResultType(0, true);
		ResultType left = helper(root.left);
		ResultType right = helper(root.right);
		int depth = Math.max(left.depth, right.depth) + 1;
		boolean balanced = left.balanced && right.balanced && (Math.abs(left.depth - right.depth) <= 1);
		return new ResultType(depth, balanced);
	}
}

class ResultType {
	int depth;
	boolean balanced;
	ResultType(int d, boolean b) {
		depth = d;
		balanced = b;
	}
}

/*
 * Use -1 to represent it's not balanced
 */

class Solution {
	public boolean isBalanced(TreeNode root) {
		if(root == null) return true;
		if (isBalanced(root) == -1 ) return false;
		else return true;

	}
	public int helper(TreeNode root) {
		if (root == null) return 0;
		int left = helper(root.left);
		int right = helper(root.right);
		if(left == -1 || right == -1 || Math.abs(left - right) > 1) {
			return -1;
		}else {
			return Math.max(left, right) + 1;
		}
	}
}

//Maximum path sum (root -> leaf)
/*
 *Divide and conquer. Divide into two subproblems.
 *How to use the two sub-answers to combine into the final result.
 *Be careful about the exit point for recursion(special case).
 */
class Solution {
	public int maxPathSum(TreeNode root) {
		if (root == null) return 0;
		int left = maxPathSum(root.left);
		int right = maxPathSum(root.right);
		return Math.max(left, right) + root.val;
	}
}

//Path sum(root -> leaf)

/*
 determine if the tree has a root-to-leaf path 
 such that adding up all the values along the 
 path equals the given sum.
 */

 /*
  * The return type is boolean.  How to construct
  * two sub-problems?  Substract the root value from
  * target value each time down one level.
  * Need to consider two special cases :
  * 1, no node. 2, leaf node
  */

 class Solution {
 	public boolean hasPathSum(TreeNode root, int sum) {
 		if (root == null) return false;
 		if (root.left == null && root.right == null) return root.val == sum;
 		return hasPathSum(root.left, sum -root.val) 
 			|| hasPathSum(root.right, sum - root.val); 
 	}
 }

 //Maximum path sum ii (root -> any)

 /*
  * One more step: determine whether we need to add sub-tree value.
  * Divide and conquer, first get the two sub-results and then do relative logic check.
  */

 class Solution {
 	public int maxPathSum2(TreeNode root) {
 		if (root == null) return 0;
 		int left = maxPathSum2(root.left);
 		int right = maxPathSum2(root.right);
 		return Math.max(0, Math.max(left,right)) + root.val;
 	}
 }

 //Maximum path sum iii (any -> any)

 /*
  * Treat each node as an independent node and calculate 
  * which node has the maxPathSum.
  * In the recursion, we need to track the maximum single path(root -> any)
  * We need use another variable to track the maximum path (any -> any)
  */

 class Solution {
 	public int maxPathSum3(TreeNode root) {
 		ResultType res = helper(root);
 		return res.maxPath;
 	}
 	public ResultType helper(TreeNode root) {
 		// Use case: (-2, null, null)
 		if (root == null) return new ResultType(0, Integer.MIN_VALUE);
 		ResultType left = helper(root.left);
 		ResultType right = helper(root.right);
 		// For the maxPath, need to determine whether we need to add left or right.
 		// Use case: (2, -1, null,null,null)
 		int path  = Math.max(0, left.singlePath) + Math.max(0, right.singlePath) + root.val;
 		int single = Math.max(0, Math.max(left.singlePath, right.singlePath)) + root.val;
 		return new ResultType(single, Math.max(path, Math.max(left.maxPath, right.maxPath)));
 	}
 }

 class ResultType {
 	int singlePath;
 	int maxPath;
 	ResultType (int singlePath, int maxPath) {
 		this.singlePath = singlePath;
 		this.maxPath = maxPath;
 	}
 }

 // Solution 2

 class Solution {
 	int max = Integer.MIN_VALUE; //gloable variable for updating maxPathSum
 	public int maxPathSum3(TreeNode root) {
 		helper(root);
 		return max;
 	}

 	public int helper(TreeNode root) {
 		if(root == null) return 0;
 		int left = maxPathSum3(root.left);
 		int right = maxPathSum3(root.right);
 		int maxPath = root.val;
 		if (left > 0) {
 			maxPath += left;
 		}
 		if (right > 0) {
 			maxPath += right;
 		}
 		max = Math.max(maxPath, max);
 		return Math.max(0, Math.max(left, right)) + root.val;
 	}
 }

 //Binary Search Tree
 /*
  * The left subtree of a node contains only nodes with keys less than the node's key.
  * The right subtree of a node contains only nodes with keys greater than the node's key.
  * Both the left and right subtrees must also be binary search trees.
  * A Single node tree is a BST.
  */

 //Validate Binary Search Tree
 /*
  * Solution 1:
  * Divide and Conquer
  * Check whether left subtree is BST, right subtree is BST.
  * Check the node key matches the condition.
  * Maintain Min and Max, for the left subtree Max is parent key, 
  * for the right subtree Min is parent key.
  */
 public class Solution {
    /*
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
        return helper(root, Long.MIN_VALUE, Long.MAX_VALUE);
        
    }
    public boolean helper(TreeNode root, long min, long max) {
        if (root == null) return true;
        return (root.val < max && root.val > min) &&  helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}

/*
 * Solution 2:
 * Check whether the maximum value in the left subtree is less than the root value.
 * Check whether the minimum value in the right subtree is greater than the root value.
 * Traverse the whole tree.
 */

public class Solution {
    /*
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
        if (root == null) return true;
        if (root.left == null && root.right == null) return true;
        TreeNode left = root.left;
        TreeNode right = root.right;
        while (left != null && left.right != null) {
            left = left.right;
        }
        while (right != null && right.left != null) {
            right = right.left;
        }
        boolean res = true;
        if (left != null) {
            res = res &&  root.val > left.val;
        }
        if (right != null) {
            res = res && root.val < right.val;
        }
        return res && isValidBST(root.left) && isValidBST(root.right);
        
    }
}

/*
 * Solution 3
 * Example: (4,2,6,1,3,5,7)
 * The inoder traversal can print treeNode in ascending order if it is BST.
 * Use a gloable variable to save the previous Node, and contiously compare with current node.
 * Note: when to return? you can return immediately right after you know the result. 
 */

public class Solution {
    /*
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
     TreeNode preNode = null;
     public boolean isValidBST(TreeNode root) {
         if (root == null) return true;
         if (!isValidBST(root.left)) return false;
         if (preNode != null && preNode.val >= root.val) return false;
         preNode = root;
         return isValidBST(root.right);
         
     }
} 

//Binary Search Tree Iterator
//Solution 1 
/*
 * Use stack, keep pushing left child into stack,
 * Pop one node, then push the right child into stack,
 * keep pushing left child into stack.
 */
public class BSTIterator {
    private Stack<TreeNode> st;
    /*
    * @param root: The root of binary tree.
    */public BSTIterator(TreeNode root) {
        // do intialization if necessary
        st = new Stack<TreeNode>();
        if (root != null) {
            st.push(root);
            TreeNode iter = root.left;
            while (iter != null) {
                st.push(iter);
                iter = iter.left;
            }
        }
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        // write your code here
        return !st.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        // write your code here
        TreeNode temp = null;
        if (!st.isEmpty()) {
            temp = st.pop();
            TreeNode iter = temp.right;
            while (iter != null) {
                st. push(iter);
                iter = iter.left;
            }
        }
        return temp;
    }
}

// Solution 2
/* Use the queue to store node. Do the in-order traversal*/
public class BSTIterator {
    private Queue<TreeNode> res;
    private void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        res.add(root);
        helper(root.right);
    }
    /*
    * @param root: The root of binary tree.
    */public BSTIterator(TreeNode root) {
        // do intialization if necessary
        res = new LinkedList<TreeNode>();
        helper(root);
    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        // write your code here
        return !res.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        return res.poll();
    }
}

//Binary Tree Level Order Traversal
/*
 * Be careful to choose the right data structure. 
 * Queue? Stack?
 * How to record the size of each level?
 */
public class Solution {
    /*
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here
    	List<List<Integer> res = new ArrayList<>();
    	Queue<TreeNode> q = new LinkedList<>();
    	if (root == null) return res;
    	q.add(root);
    	int size = 1;
    	while (!q.isEmpty()) {
    		List<Integer> ls = new ArrayList<>();
    		for (int i = 0; i < size; i++) {
    			TreeNode temp = q.poll();
    			ls.add(temp.val);
    			if (temp.left != null) {
    				q.add(temp.left);
    			}
    			if (temp.right != null) {
    				q.add(temp.right);
    			}
    		}
    		res.add(ls);
    		size = q.size();
    	}
    	return res;
    }

}

/*-------------Extra Binary Tree Problems----------------------------*/

// Identical Binary Tree
/*
 * Divide and conquer
 * Check if left-subtrees are identical, right subtrees are identical
 * Check if root values are identical
 */
public class Solution {
    /*
     * @param a: the root of binary tree a.
     * @param b: the root of binary tree b.
     * @return: true if they are identical, or false.
     */
    public boolean isIdentical(TreeNode a, TreeNode b) {
        // write your code here
        if (a == null && b == null) return true;
        else if (a == null || b == null) return false;
        if (a.val != b.val) return false;
        return isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
    }
}

// Minimum Depth of Binary Tree
/*
 * If the one subtree is null, it doen't count to the minimum depth!
 * Use case (1, null, 2, 3)
 */
public class Solution {
    /*
     * @param root: The root of binary tree
     * @return: An integer
     */
    public int minDepth(TreeNode root) {
        // write your code here
        if (root == null) return 0;
        if (root.left == null && root.right != null) return minDepth(root.right) + 1;
        if (root.right == null && root.left != null) return minDepth(root.left) + 1;
        else return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}