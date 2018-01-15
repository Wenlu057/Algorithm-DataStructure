//Valid Word Square
/* The ith character in jth word is equal to the jth character in the ith word
 * Be careful about the string index out of bound probelm cause by different string length.
 */

class Solution {
    public boolean validWordSquare(List<String> words) {
        if(words == null || words.size() == 0){
            return true;
        }
        for(int i = 0; i < words.size(); i++){
            for(int j=0; j < words.get(i).length(); j++){
                if(j >= words.size() || i >= words.get(j).length() || words.get(j).charAt(i) != words.get(i).charAt(j))
                    return false;
            }
        }
        return true;
    }
}
//Word Squares
/* Solution 1*/
/* DFS, Backtracking, brute-force*/
class Solution {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if (words.length == 0) return res;
        for (int i = 0; i < words.length; i++) {
            List<String> ls = new ArrayList<String>();
            ls.add(words[i]);
            dfs(words, 1, ls, res);
        }
        return res;
    
    }
    public void dfs (String[] words, int row,List<String> square, List<List<String>> res){
        if (square.size() == words[0].length()) {
            res.add(new ArrayList<>(square));
            return;
        }      
        for (int i = 0; i < words.length; i++) {
            boolean flag = true;
            for (int j = 0; j < row; j++) {
                if (words[i].charAt(j) != square.get(j).charAt(row)) {
                    flag = false;
                    break;
                }
            }
            // if we find one word to add into the list, go deeper
            if (flag) {
                square.add(words[i]);
                dfs(words, row + 1, square, res);               
                square.remove(square.size() - 1);
            } 
        }            
    }
   
}
/* Solution 2*/
/* Trie, fast retrieve all the words with a given prefix using a prefix list*/
class Solution {
    //define trie structure, each note maintain a list of all the words 
    //which have the prefix
    class TrieNode {
        List<String> prefix;
        TrieNode[] children;
        TrieNode() {
            prefix = new ArrayList<>();
            children = new TrieNode[26]; 
            // each node have 26 children from a to z
        }
        
    }
    class Trie {
        TrieNode root; 
        // build the trie tree given the words array.
        Trie(String[] words) {
            root = new TrieNode();
            for (String word : words) {
                TrieNode curr = root;
                for (char ch : word.toCharArray()) {
                    int idx = ch - 'a';
                    if (curr.children[idx] == null) {
                        curr.children[idx] = new TrieNode();
                    }
                    curr.children[idx].prefix.add(word);
                    curr = curr.children[idx];
                }
            }
        }
        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode curr = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (curr.children[idx] == null)
                    return ans;
                curr =curr.children[idx];
            }
            ans.addAll(curr.prefix);
            return ans;
        }
    }
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> ans = new ArrayList<>();
        if (words == null || words.length == 0) {
            return ans;
        }
        int len = words[0].length();
        Trie trie = new Trie(words);
        for (String word : words) {
            List<String> square = new ArrayList<>();
            square.add(word);
            search(len, trie, ans, square);
        }
        return ans;
    }
    private void search(int len, Trie tr, List<List<String>> ans,
                        List<String> square) {
        if (square.size() == len) {
            ans.add(new ArrayList<>(square));
            return;
        }
        int idx = square.size();
        StringBuilder prefixBuilder = new StringBuilder();
        for (String s : square)
            prefixBuilder.append(s.charAt(idx));
        // find the the list of strings which contains all the words with given prefix
        List<String> prefix = tr.findByPrefix(prefixBuilder.toString());
        // if we find available words, go deeper
        for (String word : prefix) {
            square.add(word);
            search(len, tr, ans, square);
            square.remove(square.size() - 1);
        }
    }
}

// Next Closest Time
/* Character -> Integer, HashSet, ArrayList, Collections.sort*/
class Solution {
	public String nextClosestTime(String time) {
		//extract digits from time
		char[] digits = new char[4];
		// we know the position of each digit!!! Don't use substring, or 
		// try to check if it is digist by iterating the whole string.
		digits[0] = time.charAt(0);
		digits[1] = time.charAt(1);
		digits[2] = time.charAt(3);
		digits[3] = time.charAt(4);
		// at this point, we get all the digits included in the time
		// what we can do using these digist?
		// We can iterate all the possibilities by 4 loops.
		// Store all the valid time into HashSet. Why hashset?
		// It can remove the duplicates instead of using arraylist, but no order sequence is guaranteed.
		Set<String> set = new HashSet<>();
		for (int i = 0; i < 4; i++ ) {
			for (int j = 0; j < 4; j++) {
				for (int k = 0; k < 4; k++) {
					for (int l = 0; l < 4; l++) {
						//for each possibility we do the following
						String candidate = "" + digits[i] + "" + digits[j] 
							+ ":" +digists[k] + "" + digists[l];
						if (isValidTime(candidate)) {
							//Adds the specified element to this set if it is not already present.
							set.add(candidate);
						}

					}
				}
			}
		}
		//Restore all into arraylist, sort it to get order info.
		List<String> ls = new ArrayList<>();
		ls.addAll(set);
		Collections.sort(ls);
		// in a arraylist, how to get the index of a specified target?
		int index = timList.indexOf(time);
		return index == ls.size() - 1 ? ls.get(0) : list.get(index + 1);

	}
	public boolean isValidTime(String candidate) {
		// get the hour and minute
		int hour = Integer.parseInt(candidate.substring(0, 2));
		int minute = Integer.parseInt(candidate.substring(3, 5));
		// validate the time.
		return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59
	}
}

// Decode String
/*Solution 1, use stack to check the outer ']', divide and conquer*/
/*If we found 1 decode string, divide it as subproblem*/
class Solution {
    public String decodeString(String s) {
        if (s.length() == 0) return "";
        return helper(s, 0, s.length() - 1);
       
    }
    public String helper(String s, int start, int end) {
         String res = "";
         for (int i = start; i <= end; i++) {
            char ch = s.charAt(i);
            if (Character.isLetter(ch)) {
                res += ch;
            } else if (Character.isDigit(ch) && (i==0 || !Character.isDigit(s.charAt(i - 1))) ){
            	//tricky part, need to check if the previous one is digit!! the number could have more than 1 digit.
                int j = i;
                int st = i;
                Stack<Character> stack = new Stack<>();
                for (; j <= end; j++) {
                    if (s.charAt(j) == '[') {
                        if (stack.isEmpty()) {
                            st = j;
                        }
                        stack.push('[');
                    } else if (s.charAt(j) == ']') {
                        stack.pop();
                        if (stack.isEmpty()) {
                            break;
                        }
                        
                    }
                }
                String tmp =helper(s, st + 1, j - 1);
                String num = s.substring(i, st);
                for (int k = 1; k < Integer.parseInt(num); k++) {
                    res += tmp;
                }
            }
        }
        return res;
    }
}

/*Solution 2*/
/*
 *using 2 stack, one stores the count, one stores the substring before the '[',
 *use a variable to save current substring within the inner brackets.
 *Example: 3[a2[c]]
 *Start with [c], current substring within the inner brackets : 'c',
 *Decode the inner brackets, find the prefix by poping the string stack,
 *find the k by poping the count stack, replace it by acc.
 *use the same way to decode the outer brackets and replace it by accaccacc.
 */
public class Solution {
    public String decodeString(String s) {
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = ""; // don't forget to reset the res
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                System.out.println(res);
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}
// Add Bold Tag in String
/*Interval Overlapping, First Position and Last position of overlapping chunks matters*/
/*Use a boolean array to mark all char in a substring to true if this substring exists in the dict*/
/*Solution 1*/
/*
 *Use recursion to update the boolean array
 *Determine the position of <br> and </br> and insert it into string.
 */
class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] marked = new boolean[s.length()];
        for (String word : dict) {
            helper(s, word, 0, marked);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (marked[i] && (i == 0 || !marked[i - 1])) {
                sb.append("<b>");
            }
            if (!marked[i] && (i != 0 && marked[i - 1])) {
                sb.append("</b>");
            }
            sb.append(s.charAt(i));
        }
        if (marked[s.length() - 1]) {
            sb.append("</b>");
        }
        return sb.toString();
    }
    private void helper(String s, String word, int start, boolean[] marked) {
        int index = s.indexOf(word, start);
        if (start + word.length() > s.length() || index == -1) return;
        else {
            for (int i = index; i < index + word.length(); i++) {
                marked[i] = true;
            }
            helper(s, word, start + 1, marked);
        }
    }
    
}
/*Solution 2*/
/*
 *Use while loop, use idx as both an argument and return value.
 *Determine the substring wrapper by bold tag, insert them together into string.
 */
class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for (String word : dict) {
            int idx = s.indexOf(word);
            while (idx != -1) {
                for (int i = idx; i < idx + word.length(); i++)
                    bold[i] = true;
                idx = s.indexOf(word, idx + 1);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length();) {
            if (!bold[i]) {
                sb.append(s.charAt(i++));
            } else {
                sb.append("<b>");
                while (i < s.length() && bold[i])
                    sb.append(s.charAt(i++));
                sb.append("</b>");
            }
        }
        return sb.toString();
    }
}
// Encode and Decode Strings
/*StringBuilder, store the length and content for each string with a splitter*/
public class Codec {
	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for (String s : strs) {
			sb.append(s.length()).append('/').append(s);
		}
		return sb.toString();
	}
	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		List<String> res = new ArrayList<>();
		int i = 0;
		while (i < s.length()) {
			// the first occurence of '/' is splitter, it couldn't be '/' in original strs.
			// every time the i jumps to next word to avoid getting the '/' in original strs.
			int slash = s.indexOf('/', i);
			int size = Integer.valueOf(s.substring(i, slash));
			res.add(s.substring(slash + 1, slash + size + 1));
			i = slash + size + 1;
		}
		retu  res;
	}
}