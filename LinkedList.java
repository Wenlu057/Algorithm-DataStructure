/* LinkedList*/
/*
 * It's basic data structure.
 * Our node data structure will have two fields. 
 * We also keep a variable firstNode which always points to 
 * the first node in the list, or is null for an empty list.
 */
/* 
 * Time Complexity Analysis
 * Access: O(n)	Search: O(n) Insertion: O(1) Deletion: O(1)
 * Space Complexity: O(n)
 * Insertion after an existing node in a singly linked list takes constant time.
 * Insertion before an existing node must keep track of the previous node.
 * So is deletion.
 */
/* Definition of LinkedList */
public class ListNode {
	int val;
	ListNode next;
	ListNode (int val) {
		this.val = val;
		next = null;
	}
}
// Remove Duplicates from Sorted List
/* deletion after an existing node*/
public class Solution {
    /*
     * @param head: head is the head of the linked list
     * @return: head of linked list
     */
    public ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if (head == null || head.next == null) return head;
        ListNode it = head;
        while (it.next != null) {
            if (it.next.val == it.val) {
                it.next = it.next.next;
            } else {
                it = it.next;
            }
        }
        return head;
    }
}
// Remove Duplicates from Sorted List II
/* Solution 1
 * Because we don't know if the head is distinct, a dummy node is needed.
 * The dummy node points to the head. Return the dummy.next at last.
 * Use a variable to count the number. Need a pointer to get index before 
 * the duplicates. 
 * 2 cases: 
 * 1,the current val is equal to the first different number we saved earlier.
 * 2 it's not equal
 * If it's equal, we keep counting.
 * If it's not equal, we need to check if removal of duplicates is needed.
 */

public class Solution {
    /*
     * @param head: head is the head of the linked list
     * @return: head of the linked list
     */
    public ListNode deleteDuplicates(ListNode head) {
        // write your code here
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode it = head;
        int count = 0;
        int curr = it.val;
        while (it != null) {
            if (it.val == curr) {
                count ++;
            } else {
                curr = it.val;
                if (count == 1) {
                    pre = pre.next;
                } else {
                    count = 1;
                    pre.next = it;
                }
            }
            it = it.next;
        }
        if (count > 1) {
            pre.next = null;
        }
        return dummy.next;
    }
}

/*
 * Soultion 2
 * Continuously comparing adjacent nodes. 
 * 2 cases:
 * 1, the two nodes are equal
 * 2, not equal
 * If they are equal, loop until we find the last node with same value, 
 * perform the deletion.
 * Otherwise, update the pointer to the node before the duplicates.
 */
public class Solution {
    /*
     * @param head: head is the head of the linked list
     * @return: head of the linked list
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode it = head;
        while (it != null && it.next != null) {
            if (it.next.val == it.val) {
                while (it.next != null && it.next.val == it.val) {
                    it = it.next;
                }
                pre.next = it.next;
            } else {
                pre = pre.next;
            }
            it = it.next;
        }
        return dummy.next;
    }
}

// Reverse Linked List
/* Solution 1 - Non Recursion
 * Because if you want to reverse the linked list, the current node
 * will point to the previous node, we use a pointer to keep track of 
 * the previous node.
 * If the current points to the previous, we will lose the reference
 * of the next, we need also save the next before you change the reference.
 * Iterate the whole linked list until we finish the reverse.
 */
public class Solution {
    /*
     * @param head: n
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        // write your code here
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }
}

/* Solution 2 - Recursion 
 * Suppose all nodes after the head is in reversed order,
 * We only need do the last step which the head is added into the list.
 * The linked list before the last step is something like this:
 * 1 -> 2 <- 3 <- 4 <- 5
 * Change the reference direction between head node and second node.
 * Let the head points to null at last.
 */
public class Solution {
    /*
     * @param head: n
     * @return: The new head of reversed linked list.
     */
    // public ListNode reverse(ListNode head) {
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode node = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }
}
// Reverse Linked List II
/*
 * 4 important position:
 * A pointer before the reversed segment,
 * a pointer after the reversed segment,
 * a pointer of the head of reversed segment,
 * a pointer of the tail of reversed segment.
 */

public class Solution {
    /*
     * @param head: ListNode head is the head of the linked list 
     * @param m: An integer
     * @param n: An integer
     * @return: The head of the reversed ListNode
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // write your code here
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preM = dummy;
        for (int i = 1; i < m; i++) {
            preM = preM.next;
        }
        ListNode M = preM;
        ListNode postN = preM.next;
        ListNode N = preM.next;
        for (int i = m; i <= n; i++) {
            ListNode tmp = postN.next;
            postN.next = M;
            M = postN;
            postN = tmp;
        }
        preM.next = M;
        N.next = postN;
        return dummy.next;
        
    }
}

// Partition List
/*
 *  Maintain the references of two lists, one connects all the nodes less than x,
 *  the other one connects all the nodes no less than x.
 *  Use two dummy nodes, because we don't know the head node of the two lists.
 *  Iterate the whole list, dependent on the value comparison, connects the previous
 *  to current, update the previous to current accordingly.
 *  Don't forget to let the last node of list with greater nodes points to null.
 *  Connects the tail of list with smaller nodes to the head of the list with greater nodes.
 */

public class Solution {
    /*
     * @param head: The first node of linked list
     * @param x: An integer
     * @return: A ListNode
     */
    public ListNode partition(ListNode head, int x) {
        // write your code here
        if (head == null || head.next == null) return head;
        ListNode ls1 = new ListNode(0);
        ListNode ls2 = new ListNode(0);
        ListNode it1 = ls1;
        ListNode it2 = ls2;
        ListNode it = head;
        while (it != null) {
            if (it.val < x) {
                it1.next = it;
                it1 = it;
            } else {
                it2.next = it;
                it2 = it;
            }
            it = it.next;
        }
        it1.next = ls2.next;
        it2.next = null;
        return ls1.next;
    }
}

// Sort List
/*
 * Merge Sort
 * slow pointer, fast pointer to find the middle point.
 * Merge two sorted linked list to one sorted list using dummy node
 * Don't forget to let the last node of first half  points to null!
 * Don't forget to update the pointer to next in the while loop.
 */
public class Solution {
    /*
     * @param head: The head of linked list.
     * @return: You should return the head of the sorted linked list, using constant space complexity.
     */
    public ListNode sortList(ListNode head) {
        // write your code here
        if (head == null || head.next == null) return head;
        ListNode slow = head; // the head of second list
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next; 
        slow.next = null; // caution!!! set the next of the tail to null.
        ListNode left = sortList(head);
        ListNode right = sortList(head2);
        return merge(left, right);
    }
    public ListNode merge (ListNode left, ListNode right) {
        if (left == null || right == null) return left == null ? right : left;
        ListNode dummy = new ListNode(0);
        ListNode it = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                it.next = left;
                left = left.next;
            } else {
                it.next = right;
                right = right.next;
            }
            it = it.next; // caution!!! need to update!
        }
        it.next = left == null? right : left;
        return dummy.next;
    }
}

// Reorder List
/*
 * Divide the list into half half.
 * Reverse the second half.
 * Merge two lists into one list.
 * How to merge two list into one? we take one node in turn from two lists.
 * 1, use one additional pointer, in each time we add one node until the merge is complete.
 *.   use a flag to determine the node in which list is needed.
 * 2, use two pointers to save the next node of the iterator in two lists. 
 *    if we change the reference of a pointer which may cause the pointer loses the original refrerence
 *    try to think of using a pointer to save the original reference.
 */
public class Solution {
    /*
     * @param head: The head of linked list.
     * @return: nothing
     */
    public void reorderList(ListNode head) {
        // write your code here
        if (head == null || head.next == null) return;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode ls2 = slow.next;
        slow.next = null;
        ListNode rLs2 = reverse(ls2);
        merge(head, rLs2);
        
    }
    
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = pre;
            pre = curr;
            curr = tmp;
        }
        return pre;
    }
    public void merge(ListNode ls1, ListNode ls2) {
        // if (ls1 == null) {
            
        // }
        // ListNode it = ls1;
        // ListNode it1 = ls1.next;
        // ListNode it2 = ls2;
        // int flag = 1;
        // while (it1 != null && it2 != null) {
        //     if (flag == 1) {
        //         it.next = it2;
        //         it2 = it2.next;
        //     } else {
        //         it.next = it1;
        //         it1 = it1.next;
        //     }
        //     flag = -flag;
        //     it = it.next;
        // }
        // if (it1 != null || it2 != null) {
        //     it.next = it1 != null ? it1 :it2;
        // }
        while (ls1 != null && ls2 != null) {
            ListNode node1 = ls1.next;
            ListNode node2 = ls2.next;
            ls1.next = ls2;
            ls2.next = node1;
            ls1 = node1;
            ls2 = node2;
        }
   
    }
    
}
// Merge K Sorted Lists
/* Priority Queue */
/* Use a priority queue to save all head nodes in the lists.
 * Priority queue needs a Comparator as one argument.
 * Implement inner anonymous class of comparator.
 * Poll the node from the priority queue, add the next node into queue
 * until we have polled all nodes.
 * Key points:
 * How to find next node to be added into queue after one node is polled? 
 * It can be tracked based on the polled node.
 */
public class Solution {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {  
        // write your code here
        if (lists == null || lists.size() == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode it = dummy;
        
        if (lists.size() == 1 ) return lists.get(0);
        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), ListNodeComparator);
        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i) != null) {
                heap.add(lists.get(i));
            }
        }
        while (!heap.isEmpty()) {
            ListNode node = heap.poll();
            if (node.next != null) {
                heap.add(node.next);
            }
            it.next = node;
            it = it.next;
        }
        
        return dummy.next;
    }
    // inner anonymous class 
    Comparator<ListNode> ListNodeComparator = new Comparator<ListNode>() {
        public int compare(ListNode l1, ListNode l2) {
            if (l1 == null || l2 == null) {
                return l1 == null? 1 : -1;
            }
                return l1.val - l2.val;
            }
    };
}

// LRU CACHE
/* Data Structure Design*/
/*
 * Double linked list  + Hashmap<Integer, Node>
 * Deletion, insertion from double linked list
 * Move recently used one to front, move least used one to back
 * Delete least used one to make room for the new one if the memory is full.
 */
public class LRUCache {
    // double linked list
    private class Node {
        Node pre;
        Node next;
        int key;
        int value;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.pre = null;
            this.next = null;
        }
    }
    // we need create a head node and a tail node for searching the first node and last node in O(1)
    // The hashmap contains all the nodes in the memory.
    // The hashmap maps the double linkedlist.
    private int capacity;
    private HashMap<Integer, Node> hs  = new HashMap<Integer, Node>();
    private Node dummy = new Node(0,0);
    private Node tail = new Node(0,0);
    /*
    * @param capacity: An integer
    */public LRUCache(int capacity) {
        // do intialization if necessary
       this.capacity = capacity;
       tail.pre = dummy;
       dummy.next = tail;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        // if the node exists in the memory, it exists in both linkedlist and hashmap.
        // we can retrieve it from hashmap in O(1)
        if (hs.containsKey(key)) {
            // hashmap remains the same, adjust the position of the node in double linkedlist
            Node node = hs.get(key);
            remove_from_currPos(node); //delete a node from double linkedlist, given the position of the node.
            move_to_head(node); // insert a node into the head of double linkedlist.
            return hs.get(key).value; 
        } else
            return -1;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        
        Node data = new Node(key, value);
        // if the we can find the key from hashmap, it means one of the node in the memory has the same key with the new node. We are not sure if they have same value.
        if (hs.containsKey(key)) {
            Node node = hs.get(key); 
            remove_from_currPos(node); // delete the node from double linkedlist before insertion.
        } else if (hs.size() == capacity) { // if we reach here, it means the memory is full
            // remove the last node in the linkedlist
            hs.remove(tail.pre.key);
            tail.pre = tail.pre.pre;
            tail.pre.next = tail;
        }
        hs.put(key, data); // update the hashmap
        move_to_head(data); // insert a node into the head of double linkedlist
    }
    private void move_to_head(Node node) {
        Node tmp = dummy.next;
        dummy.next = node;
        node.pre = dummy;
        node.next = tmp;
        tmp.pre = node;
    }
    private void remove_from_currPos(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }
}