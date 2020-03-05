import java.util.*;
import java.io.*;

class auto_complete_word { 
    public static void main(String[] args) throws FileNotFoundException {
        auto_complete_word program = new auto_complete_word();
        program.run();
    }

    public void run() throws FileNotFoundException {
        //Download and import a dictionary as a text file
        File file = new File("/home/daniel/Documents/JavaProjects/dictionary.txt");
        try(Scanner scan = new Scanner(file)) {
            List<String> dictionary = new ArrayList<String>();
        
            while(scan.hasNext()) {
                dictionary.add(scan.nextLine());
            }
            System.out.println("Your Dictionary of Words:");
            for(String item : dictionary) {
                System.out.print(item + " ");
            }
            System.out.println();
            System.out.println("--------------------------------------------------");

            ConstructPrefixTree(dictionary);

            Scanner input_from_user = new Scanner(System.in);
            System.out.print("Enter in a prefix: ");
            List<String> matching_words = getWordsForPrefix(input_from_user.nextLine());

            for(String wd : matching_words) {
                System.out.print(wd);
             }
        }

        catch(FileNotFoundException error) {
            System.out.println("Failed! Please check the address of your text file on your device");
        }
    }
    
    private class Node {
        String prefix;
        Map<Character, Node> children;
        boolean isWord;

        //constructor
        private Node(String prefix) {
            this.prefix = prefix;
            this.children = new HashMap<Character, Node>();
            //Character vs char -> Character warps a value of the primitive type char in an object
        }
    }
    //pointer for the head of the trie
    private static Node trie;    

    public void ConstructPrefixTree(List<String> dictionary) {
        trie = new Node(""); //empty string right now
        for(String words : dictionary) {
            insertWord(words); //now we need to take each character of the string into tree
        }
    }

    private void insertWord(String words) {
        Node current_node = trie; //starting at the top...
        for(int i = 0; i < words.length(); i++) {
            //Look through each character in the string
            if(!current_node.children.containsKey(words.charAt(i))) {
                //then add a new node into the prefix tree
                current_node.children.put(words.charAt(i), new Node(words.substring(0, i+1)));
            }
            current_node = current_node.children.get(words.charAt(i));
            //Check is this a complete word?
            if(i == words.length() - 1) {
                current_node.isWord = true;
            }
        }
    }

    public List<String> getWordsForPrefix(String pre) {
        List<String> results = new LinkedList<String>();
        Node current_node = trie;
        for(char c : pre.toCharArray()) {
            if(current_node.children.containsKey(c)) {
                current_node = current_node.children.get(c);
            } else {
                return results;
            }
        }

        findAllChildWords(current_node, results);
        return results;
    }

    private static void findAllChildWords(Node node, List<String> results) {
        if(node.isWord) {
            results.add(node.prefix);
        }
        
        for(Character c : node.children.keySet()) {
            findAllChildWords(node.children.get(c), results);
        }
    }
}
