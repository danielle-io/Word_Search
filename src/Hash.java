import java.io.PrintWriter;
import java.lang.Math;

/**
 * Hash Class
 * @author Danielle Muhlenberg
 * @version Version 2.3, November 7, 2017
 */
public class Hash {

    private PrintWriter pw;

    private final int SIZE = 37;
    private ObjectList[] table;

    // Table statistics to output
    private int numberOfCollisions;
    private double averageChainSize;
    private int maxChainSize;

    /**
     * Gets the index (hash code) of the array to store the String into.
     *
     * @param s The string to evaluate the hash code from
     * @return an integer representing a hash code (array index) for the table
     */
    private int getHash(String s) {
        int index;
        int hash = 0;

        for (int i = 0; i < s.length(); i++) {
            hash = hash << 7 ^ s.charAt(i);
        }

       index = Math.abs((hash % SIZE));
        return index;
    }

    // Prints a hash function description (should be output before printTable())
    public void printHashFunctionDescription(){
        String stepOne = "The hash function (getHash(String)) iterates through each letter of the String, \n"+
                "each time, computing and updating the hash value.";

        String stepTwo = "To calculate the hash value at each iteration, the current hash value has the \nbinary " +
                "representation of 7 bit shifted into it (using <<).";

        String stepThree = "The result of the bit shift operation is then put into an XOR operation against \n" +
                "the integer value of the current character in the String.";

        String stepFour = "Once the hash is computed, we apply a modulus to the hash against the size, and " +
                "\nfinally retrieve the absolute value of this result to get a valid index in the \nhash table.";

        System.out.println(stepOne);
        pw.println(stepOne);

        System.out.println(stepTwo);
        pw.println(stepTwo);

        System.out.println(stepThree);
        pw.println(stepThree);

        System.out.println(stepFour);
        pw.println(stepFour);
    }

    /**
     * Hash constructor
     *
     * @param pw PrintWriter Object
     */
    public Hash(PrintWriter pw){
        this.pw = pw;
        this.numberOfCollisions = 0;
        this.averageChainSize = 0.0;
        this.maxChainSize = 0;
        initializeTable();
    }

    public Hash(){
        this.numberOfCollisions = 0;
        this.averageChainSize = 0.0;
        this.maxChainSize = 0;
        initializeTable();
    }

    // Constructs a HashTable
    private void initializeTable(){
        this.table = new ObjectList[SIZE];
        for(int i = 0; i < SIZE; i++){

            // initialize ObjectList for each index
            this.table[i] = new ObjectList();
        }
    }

    // Prints the table statistics
    public void printStatistics(){
        System.out.println("Total number of collisions: " + this.numberOfCollisions);
        pw.println("Total number of collisions: " + this.numberOfCollisions);

        System.out.println("Average chain size: " + this.averageChainSize);
        pw.println("Average chain size: " + this.averageChainSize);

        System.out.println("Maximum chain size: " + this.maxChainSize);
        pw.println("Maximum chain size: " + this.maxChainSize);
    }

    /**
     * Inserts a String into the hash table
     *
     * @param s String to insert into the table
     */
    public void insert(String s){

        // If the value we are adding is a duplicate, return
        if(this.contains(s)){
            return;
        }

        // Otherwise, calculate the hash and insert it into the correct position
        int index = getHash(s);
        Hash.StringWrapper sw = this.new StringWrapper();
        sw.setString(s);

        // Insert into table
        this.table[index].insert(sw);

        // If current ObjectList has more than 1 entry, increment collisions
        if(this.table[index].size() > 1) this.numberOfCollisions++;

        // If current chain size is greater than max chain size, update max chain size
        if(this.table[index].size() > this.maxChainSize) this.maxChainSize = this.table[index].size();

        // Update average chain size
        int totalChain = 0;
        for(int i = 0; i < SIZE; i++){

            // Get the sum of total chains
            totalChain += this.table[i].size();
        }

        // Get the average chain size
        double avg = totalChain / (double)SIZE;

        // Round to two decimal places
        this.averageChainSize = Math.round(avg * 100.0) / 100.0;
    }

    /**
     * Removes a String s from the table.
     *
     * @param s String to remove from the hash table
     */
    public void remove(String s){

        // If the hash table does not contain the String provided then return
        if(!this.contains(s)){
            return;
        }

        // Calculate the index of String s and call the remove function from ObjectList
        int index = getHash(s);
        Hash.StringWrapper sw = this.new StringWrapper();
        sw.setString(s);
        this.table[index].remove(sw);
    }

    /**
     * Checks to see if table contains the String s
     *
     * @param s String to check against the table
     * @return boolean true if the table contains s, false otherwise
     */
    public boolean contains(String s){

        // Calculate the array index that the String will exist in
        int index = this.getHash(s);

        // Call the ObjectList method and return true if the ObjectList contains the String
        ObjectList list = table[index];
        if (!list.isEmpty()){
            ObjectListNode node = list.getFirstNode();

            while (node!= null){
                Hash.StringWrapper sw = (StringWrapper)node.getInfo();
                String t = sw.getString();
                if (s.equals(t)){
                    return true;
                }
                node = node.getNext();
            }
        }
        return false;
    }

    /**
     * Checks to see if any items exist in the hash table
     *
     * @return true if the hash table is empty, false if otherwise
     */
    public boolean isEmpty(){
        boolean emptyFlag = true;
        for(int i = 0; i < SIZE; i++){
            if(!this.table[i].isEmpty()) {
                emptyFlag = false;
            }
        }
        return emptyFlag;
    }

    // Prints the contents of the table
    public void printTable(){
        for(int i = 0; i < SIZE; i++){

            // Print the current index
            System.out.print("[ " + i + " ]");
            pw.print("[ " + i + " ]");

            // If the table is empty, print an X and continue to the next ObjectList
            if(this.table[i].isEmpty()){

                // Print end of current ObjectList and continue
                if (i > 9){
                    System.out.println("--| ");
                    pw.println("--| ");
                }
                else {
                    System.out.println(" --| ");
                    pw.println(" --| ");
                }
                continue;
            }

            ObjectList list = this.table[i];
            ObjectListNode node = list.getFirstNode();
            int space = i;

            // Iterate through each node printing out contents
            while(node != null){
                Hash.StringWrapper sw = (Hash.StringWrapper)node.getInfo();

                String info = sw.getString();
                if (space > 9){
                    System.out.print("--> [" + info + "]");
                    pw.print("--> [" + info + "]");
                }
                else {
                    System.out.print(" --> [" + info + "]");
                    pw.print(" --> [" + info + "]");
                }
                node = node.getNext();
            }
            System.out.println(" --| ");
            pw.println(" --| ");
        }
    }

    // Nested class used to insert into ObjectList so ObjectList can use (Comparable) interface on a String
    public class StringWrapper implements Comparable {

        private String str;

        // Constructors
        public StringWrapper()          { this.str = ""; }

        /**
         * Returns the instance variable string
         *
         * @return String containing the current String
         */
        public String getString()       { return this.str; }

        /**
         * Sets the instance variable str to String passed in
         *
         * @param s String containing what we want to change the str to
         */
        public void setString(String s) { this.str = s;}

        /**
         * Compares String Objects using Java's String Comparable method
         *
         * @param o Object representing String
         * @return integer value representing order of string
         */
        public int compareTo(Object o)  {
            Hash.StringWrapper sw = (Hash.StringWrapper)o;
            String s = sw.getString();
            if(this.str.equals(s)) return 0;
            else return str.compareTo(s);
        }
    }
}