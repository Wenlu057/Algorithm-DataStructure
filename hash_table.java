/* Hash Table */
/*
 * Hash Map
 * java.util.HashMap<K,V>
 * Type Parameters:
 * K - the type of keys maintained by this map
 * V - the type of mapped values
 * Hash table based implementation of the Map inerface.
 * It is unsynchronized and permits the null values and the null key.
 * This class makes no guarantees as to the order of the map.
 * Constant-time O(1) for the basic operations(get and put)
 * Method:
 * boolean containsKey(Object key)
 * boolean containsValue(Object value)
 * V get(Object key)
 * boolean isEmpty()
 * Set<K> keySet()
 * void put(K key, V value)
 * V remove(Object key)
 */

/*
 * Linked Hash Map
 * java.util.LinkedHashMap<K,V>
 * Hash table and linked list implementation of the Map interface.
 * It maintains a doubly-linked list, the linked list defines the iteration
 * ordering(insertion-order)
 */

/*
 * Hash Set
 * java.util.HashSet<E>
 * This class implements the Set interface, backed by a hash table.
 * Method:
 * boolean add(E e)
 * boolean contains(Object o)
 * boolean remove(Object o)
 */

// Longest Substring with At most K Distinct Character
/* contains at most k distinct characters --> maitain a data structure
 * to check number of distinct characters in constant time -- HashMap!
 * Spetial case handling on last substring satifying the condition.
 */
class Solution {
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (s.length() < k) return s.length();
		int maxLen = 1;
		int start = 0;
		HashMap<Character, Integer> map = new HashMap<>(); // key -- distinct character, value -- count
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			// add current char into hashmap
			if (map.containsKey(i)) {
				map.put(ch, map.get(ch) + 1);
			} else {
				map.put(ch, 1);
			}
			if (map.size() > k) {  //substring(start, i) satisfies the condition
				maxLen = Math.max(maxLen, i - start);
				char firstCh = s.charAt(start);
				if (map.get(firstCh) == 1) {
					map.remove(firstCh);
				} else {
					map.put(firstCh, map.get(firstCh) - 1);
				}
				start++; // Caution!!! increment start by 1 each time!!! start = i will cause error
			}
		}
		return Math.max(maxLen, s.length() - start);
	}
}

//Longest Substring with At Most Two Distinct Characters

class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() <= 2) return s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        int start = 0;
        int maxLen = 1;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
            if (map.size() > 2) {
                maxLen = Math.max(maxLen, i - start);
                if (map.get(s.charAt(start)) == 1) {
                    map.remove(s.charAt(start));
                }else {
                    map.put(s.charAt(start), map.get(s.charAt(start)) - 1);
                }
                start++;
            }
        }
        return Math.max(maxLen, s.length() - start);
    }
}