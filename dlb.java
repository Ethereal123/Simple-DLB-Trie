
 
public class dlb{
 


    private Node root;      // root of trie
    private int n;          // number of keys in trie

    // DLB node
    private static class Node {
		private char c;
        private Node right;
		private Node down;
		private boolean isEnd;
		
		private Node(char n,Node r,Node d){
			c = n;
			right = r;
			down = d;
			isEnd = false;
		}
    }

   /**
     * Initializes an empty string symbol table.
     */
    public dlb() {
    }


    /**
     * Returns the value associated with the given key.
     * @param key the key
     * @return the value associated with the given key if the key is in the symbol table
     *     and {@code null} if the key is not in the symbol table
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public Boolean contains(String key) {
		Node curr = root;
		//make a loop that first finds the char in the right going list, then if it finds it it moves down. if we reach the end of the right going list, or the down going list is null at the char
		//and we arent at the last char reurn false, if we travese all the way o the last char, we find it and the isEnd boolean is true, return true
		for(int i = 0;i<key.length;i++){
			char c = key.charAt(i);
			while(curr.right != NULL && c != curr.c){
				curr = curr.right
			}
			if(curr.c == c){
				curr = curr.down;
			}
			else{
				return(false);
			}
		}
		return(true); 
	}		

    /**
     * Does this symbol table contain the given key?
     * @param key the key
     * @param key the key
     * @return {@code true} if this symbol table contains {@code key} and
     *     {@code false} otherwise
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public boolean gets(String key) {
        return(contains(key));
    }

    /**
     * Inserts the key-value pair into the symbol table, overwriting the old value
     * with the new value if the key is already in the symbol table.
     * If the value is {@code null}, this effectively deletes the key from the symbol table.
     * @param key the key
     * @param val the value
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public void put(String key) {
       
	    Node curr = root;
	    for(int i = 0;i<key.length;i++){
	  	    char c = key.charAt(i);
		    while(curr.right != NULL && curr.c != c){
				curr = curr.right;
		    }
		    if(i != key.length-1){
			    if(curr.c == c){
				    if(curr.down != NULL){
						curr = curr.down;
				    }
				    else{
						curr.down = new Node(key.charAt(i+1),NULL,NULL);
						curr = curr.down;
					   
				    }
			    }
			    else{
				    curr.right = new Node(c,NULL,NULL);
				    curr = curr.right;
				    curr.down = new Node(key.charAt(i+1);
				    curr = curr.down;
			    }
			
			}
			else{
				//if its the last character then we have to make sure that the boolean is set to true, constructor for the NOde class automatically sets the boolean to false
				if(curr.c == c){
					curr.isEnd = true;
				}
				else{
					curr.right = new Node(c,NULL,NULL);
					curr = curr.right;
					curr.isEnd = true;
				}
			}
		}

  

    /**
     * Returns the number of key-value pairs in this symbol table.
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }

    /**
     * Is this symbol table empty?
     * @return {@code true} if this symbol table is empty and {@code false} otherwise
     */
    public boolean isEmpty() {
        return size() == 0;
    }

 
    /**
     * Removes the key from the set if the key is present.
     * @param key the key
     * @throws NullPointerException if {@code key} is {@code null}
     */
    public void delete(String key) {
		//dont need to implement
    }


    /**
     * Unit tests the {@code TrieST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
		
    }
}