import java.io.PrintWriter;

public class Headers {
    private PrintWriter pw;

    /**
     * Headers constructor
     *
     * @param pw PrintWriter Object
     */
    public Headers(PrintWriter pw){
        this.pw = pw;
    }

    // Header for the stats
    public void hashStats(){
        System.out.println();
        pw.println();
        System.out.println("Hash Table Statistics: ");
        pw.println("Hash Table Statistics: ");
        System.out.println("- - - - - - - - - - - ");
        pw.println("- - - - - - - - - - - ");
    }

    // Hash description header
    public void hashDescript(){
        System.out.println();
        pw.println();

        System.out.println("Hash Table Function Description: ");
        pw.println("Hash Table Function Description: ");
        System.out.println("- - - - - - - - - - - - - - - -");
        pw.println("- - - - - - - - - - - - - - - -");
    }

    // Header for printing the tree
    public void printTreeHeader(){
        System.out.println();
        pw.println();
        System.out.println("     \t\t       \tLine Number");
        pw.println("     \t\t     Line Number");
        System.out.println("Word \t\tCount \t& Position");
        pw.println("Word \t     Count    & Position");
        System.out.println("_______________________________________________");
        pw.println("_______________________________________________");
    }

    // Hashtable header
    public void printHashTable(){
        System.out.println();
        pw.println();
        System.out.println("Hash Table: ");
        pw.println("Hash Table: ");
        System.out.println("- - - - - - - - - - - -");
        pw.println("- - - - - - - - - - - -");
    }

    // Header for outputting getty
    public void gettyHeader(){
        System.out.println();
        pw.println();
        System.out.println("   \t \t              The Gettysburg Address");
        pw.println("\t\t    The Gettysburg Address");
        System.out.println("Line  Text");
        pw.println("Line  Text");
    }
}
