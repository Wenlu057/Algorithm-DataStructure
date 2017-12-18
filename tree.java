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

// Binary Tree Inorder Traversal

/*Traversal in recursion*/
public class Solution {
    /*
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        help(root, res);
        return res;
    }
    public void help (TreeNode root, List<Integer> res) {
        if (root == null) return;
        help(root.left, res);
        res.add(root.val);
        help(root.right, res);
    }
}

/*Divide and Conquer*/
public class Solution {
    /*
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */    
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> left = inorderTraversal(root.left);
        List<Integer> right = inorderTraversal(root.right);
        res.addAll(left);
        res.add(root.val);
        res.addAll(right);
        return res;
    }
}

/*Non recursion using stack*/
public class Solution {
    /*
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        if (root == null) return res;
        st.push(root);
        TreeNode left = root.left;
        while (left != null) {
            st.push(left);
            left = left.left;
        }
        while (!st.isEmpty()) {
            TreeNode temp = st.pop();
            res.add(temp.val);
            if (temp.right != null) {
                TreeNode right = temp.right;
                while (right != null) {
                    st.push(right);
                    right = right.left;
                }
            }
        }
        return
    }
}

//Lowest Common Ancestor
/*
 * Divide and Conquer
 * If we find node A or node B, return it
 * If A, B are found in sepearte subtree, the root is the LCA
 * else one node is the ancestor of the other.
 */
public class Solution {
    /*
     * @param root: The root of the binary search tree.
     * @param A: A TreeNode in a Binary.
     * @param B: A TreeNode in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        if (root == null) return null;
        if (root == A || root == B) return root;
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);
        return left != null ? (right != null? root : left) : right;
        
    }
}

// Flatten Binary Tree to Linked List
/*
 * Solution 1
 * Binary Tree Traversal
 * Construct the linked list from tail to head.
 * Recursion in right, left, root order, the reversed order of preorder
 * Set global variable pre to record the previous node before the current node.
 * Update the pre, construct the linked list accordingly.	
 */

public class Solution {
	 /*
     * @param root: a TreeNode, the root of the binary tree
     * @return: 
     */
     private TreeNode pre = null;
     public void flatten(TreeNode root) {
     	if (root == null) {
     		return;
     	}
     	flatten(root.right);
     	flatten(root.left);
     	root.right = pre;
     	root.left = null;
     	pre = root;
     }
}

/*
 * Solution 2
 * Construct the linked list form head to tail.
 * Divide and Conquer
 * Suppose we already get result of left subtree and right subtree.
 * We connect the root to left subtree, connect the last node in the left subtree to right subtree.
 * Be careful to check whether the left is null
 * Don't forget to set left child to null.
 */

public class Solution {
	public void flatten(TreeNode root) {
		if (root == null) return;
		helper(root);
	}
	public TreeNode helper(TreeNode root) {
		if (root == null) return null;
		// if (root.left == null && root.right == null) return root;
		TreeNode left = helper(root.left);
		TreeNode right = helper(root.right);
		root.left = null;
		if (left != null) {
			TreeNode temp = left;
			while (temp.right != null) {
				temp = temp.right;
			}
			root.right = left;
			temp.right = right;
		} else {
			root.right = right;
		}
		return root;
	}
}

/*
 * Solution 3
 * Store all nodes into list in preorder.
 * Construct the flattened tree based on the list.
 */

public class Solution {
	public void flatten(TreeNode root) {
		List<TreeNode> ls = new ArrayList<TreeNode>();
		if (root == null) return;
		helper(root, ls);
		TreeNode temp = root;
		for (int i = 1; i < ls.size(); i++) {
			temp.left = null;
			temp.right = ls.get(i);
			temp = temp.right;
		}
	}
	public void helper(TreeNode root, List<TreeNode> ls) {
		if (root == null) return;
		ls.add(root);
		helper(root.left, ls);
		helper(root.right, ls);
	}
}

// Binary Tree Postorder Traversal

/*Solution1 Traversal*/
public class Solution {
    /*
     * @param root: A Tree
     * @return: Postorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        helper(root, res);
        return res;
    }
    public void helper(TreeNode root, List<Integer> ls) {
        if (root == null) return;
        helper(root.left, ls);
        helper(root.right, ls);
        ls.add(root.val);
    }
}
/*Solution 2 Divide and Conquer*/
public class Solution {
	public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        List<Integer> left = postorderTraversal(root.left);
        List<Integer> right = postorderTraversal(root.right);
        res.addAll(left);
        res.addAll(right);
        res.add(root.val);
        return res;
    }
}

/* Soultion 3
 * Non-recursive method 
 * Here's the trick! Use a pointer to save the previous node, 
 * Check the relationship between the previous and current,
 * If the previous is parent node, it's going down, if it's leaf, pop it
 * If the current is parent node, it's going up,
 * if the previous is left node, try push the right node into stack
 * if the previous is right node, we try both children, just pop it
 */
public class Solution {
    /*
     * @param root: A Tree
     * @return: Postorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> S = new Stack<>();
        if (root == null)
            return res;
        S.push(root);
        TreeNode prev = null;
        while (!S.isEmpty()) 
        {
            TreeNode current = S.peek();
  
            /* go down the tree in search of a leaf an if so process it 
            and pop stack otherwise move down */
            if (prev == null || prev.left == current || 
                                        prev.right == current) 
            {
                if (current.left != null)
                    S.push(current.left);
                else if (current.right != null)
                    S.push(current.right);
                else
                {
                    S.pop();
                    res.add(current.val);
                }
  
                /* go up the tree from left node, if the child is right 
                   push it onto stack otherwise process parent and pop 
                   stack */
            } 
            else if (current.left == prev) 
            {
                if (current.right != null)
                    S.push(current.right);
                else
                {
                    S.pop();
                    res.add(current.val);
                }
                  
                /* go up the tree from right node and after coming back
                 from right node process parent and pop stack */
            } 
            else if (current.right == prev) 
            {
                S.pop();
                res.add(current.val);
            }
  
            prev = current;
        }
        return res;
 
    }
}

// Construct Binary Tree from Preorder and Inorder Traversal
/*
 * The head of preorder array is always root node, use the value of the root value 
 * to find the position in inorder array, lock the left subtree and right subtree accordingly,
 * Use 4 indexes to record the begining and end in preorder and inorder.
 */
public class Solution {
    /**
     *@param preorder : A list of integers that preorder traversal of a tree
     *@param inorder : A list of integers that inorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
        // don't forget the case where length is 0
        if (preorder == null || inorder == null || preorder.length == 0
            ||inorder.length == 0) return null;
        
        return helper(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
        
    }
    public TreeNode helper(int[] preorder, int[] inorder, int prei, int prej, int ini, int inj){
    	// remember to check the start and end value
        if (preorder == null || inorder == null || prej - prei < 0 || inj - ini < 0) return null;
        else{
            TreeNode root = new TreeNode(preorder[prei]);
            int index = 0;
            for (int i = ini; i <= inj; i++) {
                if (inorder[i] == preorder[prei]) {
                    index = i;
                    break;
                }
            }
            root.left = helper(preorder, inorder, prei + 1, prei + index - ini, ini, index - 1 );
            root.right = helper(preorder, inorder, prei + index - ini + 1, prej, index + 1, inj);
            return root;
        }
 
    }
}

// Binary Tree Path
/* Solution 1
 * Divide and Conquer
 * Suppose we have the left sub-tree path, and right sub-tree path
 * Merge the two sub paths into final binary tree path	
 */
public class Solution {
    /*
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        if (root.left == null && root.right == null) {
            res.add("" + root.val);
        }
        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        for (int i = 0; i < left.size(); i++) {
            res.add(root.val + "->" + left.get(i));
        }
        for (int i = 0; i < right.size(); i++ ) {
            res.add(root.val + "->" + right.get(i));
        }
        return res;
    }
}
/*
 * Solution 2
 * Deep first search
 * the number of leaf nodes is equal to the number of tree paths.
 * Each time in the recursion, we append one portion of the path into path,
 * until we reach the leaf node, we add the path into the result list, otherwise
 * continue the traversal.
 */

public class Solution {
    /*
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        helper(root, res, "");
        return res;
        
    }
    public void helper(TreeNode root, List<String> res, String path) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            path += "" + root.val;
            res.add(path);
        }else {
            helper(root.left, res, path +root.val + "->");
            helper(root.right, res, path + root.val + "->");
        }

    }
}

// Binary Tree Level Order Traversal II
/*
 * how to reverse the order of lever order traversal
 * 1, use Collections.reverse(ls) to return the elements in the list.
 * 2, use stack to save each level, then pop it to add into the list. 
 */
public class Solution {
    /*
     * @param root: A tree
     * @return: buttom-up level order a list of lists of integer
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // write your code here
        List<List<Integer>> ls = new ArrayList<>();
        if (root == null) return ls;
        Queue<TreeNode> q = new LinkedList<>();
        Stack<List<Integer>> st = new Stack<>();
        int size = 1;
        q.add(root);
        while (!q.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();
                temp.add(node.val);
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
            }
            st.push(temp);
            size = q.size();
        }
        while (!st.isEmpty()) {
            ls.add(st.pop());
        }
        return ls;
    }
}

//Binary Tree Serialization
/*
 * Use DFS to store Tree Nodes. Each time you go deeper, you append something to StringBuilder.
 * The core of DFS is recursion, and in method arguments, you probably want to pass somehing like
 * StringBuilder, List, Queue, Stack, Array which it could be carried and updated all the time. Or
 * you could consider using a global variable and update it in each recursion call.
 */
/*
 * Be familiar with standard JAVA API
 * Collections like Queue, Stack, List: boolean addAll(Collection<? extends E> c)
 * Convert Array to List: public static <T> List<T> asList(T... a)
 * Convert String to int: Integer.valueOf(str)
 */
/*
 * Design a data structure which we can retreive one node from it each time.
 * The first one popped out is the root of the tree.
 * Queue!
 */
public class Solution {
	private static final String splitter = ",";
	private static final String NN = "#";
	public String serialize(TreeNode root) {
		StringBuilder sb = new StringBuilder();
		buildString(root, sb);
		return sb.toString();
	}
	public void buildString(TreeNode root, StringBuilder sb) {
		if (root == null) {
			sb.append(NN).append(splitter);
			return;
		}
		sb.append(root.val).append(splitter);
		buildString(root.left, sb);
		buildString(root.right, sb);
	}
	public TreeNode deserialize(String data) {
		Queue<String> nodes = new LinkedList<>();
		nodes.addAll(Arrays.asList(data.split(splitter)));
		return buildTree(nodes);
	}
	public TreeNode buildTree(Queue<String> nodes) {
		if (!nodes.isEmpty()) {
			String val = nodes.poll();
			if (val.equals(NN)) {
				return null;
			} else {
				TreeNode node = new TreeNode(Integer.valueOf(val));
				node.left = buildTree(nodes);
				node.right = buildTree(nodes);
				return node;
			}
		}
		return null;
	}
}