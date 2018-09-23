import java.io.PrintWriter;
import java.util.Scanner;

public class Query{

    private String userInput;
    private PrintWriter pw;

    public Query (PrintWriter pw){
        this.pw = pw;
    }

    public char userInput() {

        // Header
        System.out.println();
        pw.println();
        String enterString = "Please enter a word for the run-time query (to quit program enter \"1\"): ";

        // Import scanner for user input
        Scanner scan = new Scanner(System.in);
        boolean isAlpha;

        // Loop while input is not valid
        do {

            isAlpha = true;

            // Ask user to enter a word
            System.out.print(enterString);
            pw.print(enterString);

            userInput = scan.nextLine();
            pw.print(userInput);

            // For input validation, remove all white space from userInput
            userInput = userInput.replaceAll("\\s+", "");

            // If user enters 1 close program
            if (userInput.equals("1") || userInput.equals("1")) {
                return 'e';
            }

            // Check that input is a valid word
            char[] chars = userInput.toCharArray();

            for (char c : chars) {
                if (!Character.isLetter(c)) {
                    System.out.println();
                    pw.println();
                    System.out.println("Invalid input.");
                    pw.println("Invalid input.");
                    isAlpha = false;
                }
            }

        } while (!isAlpha);

        return 'c';
    }

    /**
     * Checks if a word is in the tree
     *
     * @param getty the ObjectBinaryTree with the words inserted
     */
    public void wordCheck(ObjectBinaryTree getty){
        Word word = new Word(userInput, pw);
        ObjectTreeNode node = getty.searchBST(word);

        // Output header
        Headers header = new Headers(pw);
        header.printTreeHeader();

        // If node is null print word and zeroes
        if (node == null){

            // Format spaces
            String spaces = word.formatSpaces(userInput);
            System.out.print(userInput + spaces + "0       0" );
            pw.print(userInput + spaces + "0" + "\t" + "0");
        }

        // If node is not null call visit so we can print its info
        else {
            Word found = (Word)node.getInfo();
            found.visit();
        }
        System.out.println();
        pw.println();
    }
}
