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