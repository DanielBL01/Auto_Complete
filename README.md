```
//Trie Node class
private class Node {
    Map<Character, Node> children;
    String prefix;
    boolean isWord;

    private Node(String prefix) {
        this.prefix = prefix;
        this.children = new HashMap<Character, Node>();
    }
}
//The trie
private static Node trie;
```





