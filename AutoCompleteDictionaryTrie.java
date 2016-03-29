package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Praveen Sankaranarayanan
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		String wordLower = word.toLowerCase();
		TrieNode curr = root;
		for(char c: wordLower.toCharArray()){
			if(curr.getChild(c) == null){
				curr = curr.insert(c);
			} else {
				curr = curr.getChild(c);
			}
		}
		if(curr != null && curr.endsWord() == false){
			curr.setEndsWord(true);
			size += 1;
			return true;
		}
	    return false;
	}
	
	/** 
	 * Return the number of words in the dictionary. 
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		String wordLower = s.toLowerCase();
		TrieNode curr = root;
		for(char c: wordLower.toCharArray()){
			if(curr.getChild(c) == null){
				return false;
			} else {
				curr = curr.getChild(c);
			}
		}
		if(curr.endsWord()){
			return true;
		}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // This method implements the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and we don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 LinkedList<String> completionsList = new LinkedList<String>();
    	 int compGen = 0;
 		 String wordLower = prefix.toLowerCase();
 		 TrieNode curr = root;
 		 // Finding the prefix in the trie
 		 for(char c: wordLower.toCharArray()){
 			 if(curr.getChild(c) == null){ // prefix is not in the trie
 			 	return completionsList;
 			 } else {
 			 	curr = curr.getChild(c);
 			 }
 		 }
 		 LinkedList<TrieNode> queueBFS = new LinkedList<TrieNode>();
 		 queueBFS.add(curr);
 		 // do BFS
    	 while(!queueBFS.isEmpty() && compGen < numCompletions){
    		 TrieNode head = queueBFS.removeFirst();
    		 if(head.endsWord()){
    			 completionsList.add(head.getText());
    			 compGen += 1;
    		 }
    		 for(char c: head.getValidNextCharacters()){
    			 queueBFS.add(head.getChild(c));
    		 }
    	 }
         return completionsList;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}