
public class LinePosition implements Comparable{

    private int position;
    private int lineNumber;

    public LinePosition(){
        position = 0;
        lineNumber = 0;
    }

    /**
     * LinePosition constructor
     *
     * @param linePosition int telling us where the word is on the line
     * @param lineNumber int telling us the line number
     */
    public LinePosition(int linePosition, int lineNumber){
        this.position = linePosition;
        this.lineNumber = lineNumber;
    }

    /**
     * Sets the position of where the word is on the line
     *
     * @param linePosition int telling us where the word is on the line
     */
    public void setPosition (int linePosition){
        this.position = linePosition;
    }

    /**
     * Returns where on the line the word is positioned
     *
     * @return this.linePosition an int telling us where the word is in the line
     */
    public int getPosition(){
        return this.position;
    }

    /**
     * Sets the lineNumber of the word
     *
     * @param lineNumber int representing the line number
     */
    public void setLineNumber (int lineNumber){
        this.lineNumber = lineNumber;
    }

    /**
     * Returns the line number of the word
     *
     * @return this.lineNumber an int telling us where the word is on the line
     */
    public int getLineNumber(){
        return this.lineNumber;
    }

    /**
     * Returns the formatting for the printed version of the line number and line position of a word
     *
     * @return String this.lineNumber and this.position separated by a -
     */
    public String toString(){
        return this.lineNumber + "-" + this.position;
    }

    /**
     * Compares Objects
     *
     * @param o an Object for comparing
     * @return int -1 or 1 when comparing
     */
    public int compareTo(Object o){
        LinePosition lp = (LinePosition)o;
        if(this.lineNumber == lp.lineNumber && this.position == lp.position) return 0;
        if(this.lineNumber > lp.lineNumber) return -1;
        return -1;
    }
}
