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

// Topologial Sort
/**
 * Definition for Directed graph.
 * class DirectedGraphNode {
 *     int label;
 *     ArrayList<DirectedGraphNode> neighbors;
 *     DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
 * };
 */
public class Solution {
    /**
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */    
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> result = new ArrayList<DirectedGraphNode>();
        HashMap<DirectedGraphNode, Integer> map = new HashMap();
        for (DirectedGraphNode node : graph) {
            for (DirectedGraphNode neighbor : node.neighbors) {
                if (map.containsKey(neighbor)) {
                    map.put(neighbor, map.get(neighbor) + 1);
                } else {
                    map.put(neighbor, 1); 
                }
            }
        }
        Queue<DirectedGraphNode> q = new LinkedList<DirectedGraphNode>();
        for (DirectedGraphNode node : graph) {
            if (!map.containsKey(node)) {
                q.offer(node);
                result.add(node);
            }
        }
        while (!q.isEmpty()) {
            DirectedGraphNode node = q.poll();
            for (DirectedGraphNode n : node.neighbors) {
                map.put(n, map.get(n) - 1);
                if (map.get(n) == 0) {
                    result.add(n);
                    q.offer(n);
                }
            }
        }
        return result;
    }
}

//Alien Dictionary
class Solution {
public String alienOrder(String[] words) {
    Map<Character, Set<Character>> map =new HashMap<Character, Set<Character>>();
    Map<Character, Integer> degree=new HashMap<Character, Integer>();
    String result="";
    if(words==null || words.length==0) return result;
    for(String s: words){
        for(char c: s.toCharArray()){
            degree.put(c,0); //initialize degree : w-0, r -0, t-0, e-0, f-0
        }
    }
    for(int i=0; i<words.length-1; i++){
        String cur=words[i];
        String next=words[i+1];
        int length=Math.min(cur.length(), next.length());
        for(int j=0; j<length; j++){
            char c1=cur.charAt(j);
            char c2=next.charAt(j);
            if(c1!=c2){
                Set<Character> set=new HashSet<Character>();
                if(map.containsKey(c1)) set=map.get(c1);
                if(!set.contains(c2)){
                    set.add(c2);
                    map.put(c1, set);
                    degree.put(c2, degree.get(c2)+1);
                }
                break;
            }
        }
    }
    Queue<Character> q=new LinkedList<Character>();
    for(char c: degree.keySet()){
        if(degree.get(c)==0) q.add(c);
    }
    while(!q.isEmpty()){
        char c=q.remove();
        result+=c;
        if(map.containsKey(c)){
            for(char c2: map.get(c)){
                degree.put(c2,degree.get(c2)-1);
                if(degree.get(c2)==0) q.add(c2);
            }
        }
    }
    if(result.length()!=degree.size()) return "";
    return result;
}
}