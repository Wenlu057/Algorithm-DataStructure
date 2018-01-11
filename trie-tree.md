## Data Structure: Trie

Tries are an extremely special and useful data-structure that are based on the _prefix of a string_. They are used to represent the “Re**trie**val” of data and thus the name Trie.

The **prefix of a string** is nothing but any n letters n≤\|S\| that can be considered beginning strictly from the starting of a string. For example , the word “abacaba” has the following prefixes:

a  
ab  
aba  
abac  
abaca  
abacab

A Trie is a special data structure used to store strings that can be visualized like a graph. It consists of nodes and edges. Each node consists of at max 26 children and edges connect each parent node to its children. These 26 pointers are nothing but pointers for each of the 26 letters of the English alphabet A separate edge is maintained for every edge.

![](/assets/trie.png)

Tries are generally used on **groups of strings**, rather than a single string. When given multiple strings , we can solve a variety of problems based on them. For example, consider an English dictionary and a single stringss, find the** prefix of maximum length **from the dictionary strings matching the stringss. Solving this problem using a naive approach would require us to match the prefix of the given string with the prefix of every other word in the dictionary and note the maximum. The is an expensive process considering the amount of time it would take. Tries can solve this problem in much more efficient way.


    class TrieNode {
        List<String> prefix;
        TrieNode[] children;
        TrieNode() {
            prefix = new ArrayList<>();
            children = new TrieNode[26]; 
            // each node have 26 children from a to z
        }
        
    }

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
  


