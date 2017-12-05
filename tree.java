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