import java.io.PrintWriter;
import java.lang.String;

/**
 * Word Class
 * @author Danielle Muhlenberg
 * @version Version 2.3, November 7, 2017
 */

public class Word implements TreeComparable{
    private String word;
    private int count;
    private int line;
    private ObjectList p;
    private PrintWriter pw;

    /**
     * Word constructor
     *
     * @param word String representing a word from the text file
     */
    public Word (String word, PrintWriter pw){
        this.word = word;
        this.pw = pw;
        this.count = 1;
        p = new ObjectList();
    }

    public Word() {
        this.word = "";
        this.count = 0;
    }

    /**
     * Sets the word count
     *
     * @param count int representing amount of time word appears
     */
    public void setCount(int count){
        this.count = count;
    }

    /**
     * Returns the word count
     *
     * @return this.count int representing amount of time word appears
     */
    public int getCount(){
        return this.count;
    }

    /**
     * Returns the word
     * @return String this.word representing the word
     */
    public String getWord(){
        return this.word;
    }

    /**
     * Inserts the line position
     *
     * @param lp LinePosition to insert into the ObjectList
     */
    public void insertLinePosition(LinePosition lp){
        p.insert(lp);
    }

    /**
     * Returns the ObjectList p
     *
     * @return ObjectList this.p
     */
    public ObjectList getLinePositionList(){
        return this.p;
    }

    /**
     * Compares the String stored in word against another word object's string
     * and returns 0 if they are the same, otherwise returns -1 or 1 if they are
     * different. Note this function compares words ignoring case.
     *
     * @param o Object to compare against
     * @return 0 if equal, otherwise returns 1 or -1
     */
    public int compareTo(Object o){
        Word w = (Word)o;
        String compareWord = w.getWord();
        return this.word.compareToIgnoreCase(compareWord);
    }

    // Allows the user to define what should be output when an
    // Object is visited in a traversal of a binary search tree
    public void visit(){

        int firstRun = 0;

        // Format spacing
        String spaces = formatSpaces(word);
        String pwSpaces = formatPWSpaces(word);
        String pwAfterCount = "   ";

        String afterCount = "   ";
        if (count > 9){
            afterCount = "  ";
            pwAfterCount = "  ";
        }

        System.out.print(this.word + spaces + this.count + afterCount);
        pw.print(this.word + pwSpaces + this.count + pwAfterCount);
        ObjectListNode node = this.p.getFirstNode();

        // Loop through nodes
        while(node != null){
            LinePosition lp = (LinePosition)node.getInfo();
            String lastSpace = " ";
            String lpString = lp.toString();
            String firstSpace = "  ";

            // Format last space of numbers
            if (lpString.charAt(1) == '-'){
                lastSpace = "  ";
            }
            if (lp.getPosition() > 9 || lp.getLineNumber() > 9){
                lastSpace = " ";
            }
            if (lp.getPosition() > 9 && lp.getLineNumber() > 9){
                lastSpace = "";
            }

            System.out.print(firstSpace + lpString + lastSpace);
            pw.print(firstSpace + " " + lpString + " " + lastSpace);
            node = node.getNext();
            firstRun = 1;
        }
        System.out.println();
        pw.println();
    }

    /**
     *Determines what actions to take when a duplicate word has been found in the
     insertBSTDup() method. The counter for the word will be incremented and the
     line number and word position will be added to the appropriate linear linked list
     *
     * @param o an Object
     */
    public void operate(Object o){
        Word w = (Word) o;

        // increment word count
        this.count++;

        // Get the first Object from the Word and insert it into the current Word object
        ObjectList lineList = w.getLinePositionList();
        LinePosition lp = (LinePosition)lineList.getLast();
        this.p.addLast(lp);
    }

    /**
     * Determines the space formatting for the word and count output
     *
     * @param word String representing the word that affects the space formatting
     * @return String spaces for proper formatting
     */
    public String formatSpaces (String word){

        switch (word.length()){
            case 1: return "\t          ";
            case 2: return "\t          ";
            case 3: return "\t          ";
            case 4:
            case 5: return "\t      ";
            case 6:
            case 7: return "\t      ";
            case 8:
            case 9:
            case 10: return "\t  ";
            case 11: return "\t  ";
            default:
                break;
        }
        return  "        ";
    }

    /**
     * Determines the space formatting for the text file
     *
     * @param word String representing the word that affects the space formatting
     * @return String spaces for proper formatting
     */
    public String formatPWSpaces (String word){

        switch (word.length()){
            case 1: return "\t          ";
            case 2: return "\t          ";
            case 3: return "\t          ";
            case 4:
            case 5: return "\t          ";
            case 6: return "\t          ";
            case 7: return "\t          ";
            case 8: return "          ";
            case 9: return "         ";
            case 10: return "        ";
            case 11: return "       ";
            default:
                break;
        }
        return  "        ";
    }
}

