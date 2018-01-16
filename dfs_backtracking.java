//robert cleaner
/*
Given a robot cleaner in a room modeled as a grid. 
Each cell in the grid can be empty or blocked. 
The robot cleaner can move forward, turn left or turn right. 
When it tries to move into a blocked cell, its bumper sensor 
detects the obstacle and it stays on the current cell.

The control API:

interface Robot {
  // returns true if next cell is open and robot moves into the cell.
  // returns false if next cell is obstacle and robot stays on the current cell.
  boolean Move();

  // Robot will stay on the same cell after calling Turn*. k indicates how
  // many turns to perform.
  void TurnLeft(int k);
  void TurnRight(int k);
  // Clean the current cell.
  void Clean();

  boolean Move(Direction d);
}
 */
//  Template
/*
<!-- for the current one, do something --!>
<!-- for loop, loop through all possible cases for current one --!>
<!-- for each case in the for loop
		if (it matches something)
			do something
			go deeper, recursive call of itself, update the parameter value
			when backtracking, the program runs here, you possibly need remain current value as the recursion dosen't happen
	  end of for loop --!>
*/
// Dfs + backtracking features: graph traversal, path permutation
public void dfsClean(int x, int y, int d, Set<String> memo) {
	//clean current tile, add current tile to hashset.
	this.Clean();
	String hcode = x + "," + y;
	memo.add(hcode);
	// for each tile, it can go to 4 directions.(up, down, left, right)
	for (int i = 0; i < 4; i++) {
		this.TurnRight(i > 0 ?  1 : 0);
		// choose one direction, compute new pos and direction.
		int nd = (d + i) % 4; // new d
		int nx = x + dir[nd][0]; // new x
		int ny = y + dir[nd][1]; // new y
		String ncode = nx + "," + ny;
		// if it's not visited.
		if (!memo.contains(ncode)) {
			// if it's not obstacle, go deeper!
			if (this.move()) {
				dfsClean(nx, ny, nd, memo);
				// reset to original when backtracking.
				this.move();
				this.TurnRight(2);

			} else {
				// it's not obstacle, add it to visited list.
				memo.add(ncode);
			}
		}
	}
	// go backword one step.
	this.TurnRight(3);
}

// Clone Graph
/* HashMap*/
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    // use hashmap to save visited node.
    private HashMap<Integer, UndirectedGraphNode> map = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return clone(node);
    }

    private UndirectedGraphNode clone(UndirectedGraphNode node) {
        if (node == null) return null;
        
        if (map.containsKey(node.label)) {
            // the hardest point, the value of each key is a undirectedGraphNode,
            // the undirectedGraphNode can update its neighbors as we run the dfs.
            return map.get(node.label); 
        }
        // clone current node
        UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
        map.put(clone.label, clone); // put clone to hashmap with the key of its label
        for (UndirectedGraphNode neighbor : node.neighbors) {
            UndirectedGraphNode tmp = clone(neighbor);
            clone.neighbors.add(tmp); // here, we add neighbor to clone, the value saved in hashmap hold its address
            // clone.neighbors.add(clone(neighbor));
        }
        return clone;
    }
}