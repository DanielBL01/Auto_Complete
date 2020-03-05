This project taught me a lot about the **Trie (Prefix Tree)** data structure and it's implementation

Trie Implementation (JAVA):

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

> Bad programmers worry about the code. Good programmers worry about data structures and their relationships.
> - Linus Torvalds




