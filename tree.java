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
 		// ResultType res = new ResultType(Integer.MIN_VALUE, Integer.MIN_VALUE);
 		if (root == null) return new ResultType(0, Integer.MIN_VALUE);
 		ResultType left = helper(root.left);
 		ResultType right = helper(root.right);
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
 	int max = Integer.MIN_VALUE;
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