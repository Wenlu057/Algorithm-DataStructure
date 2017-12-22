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