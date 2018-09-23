import java.io.*;
import java.util.Scanner;

public class Xref {

    public static void main(String[] args) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("csis.txt"));
        Headers header = new Headers(pw);

        // Open a file for input and create a Scanner object
        Scanner fileScan = new Scanner(new File("ignored.txt"));

        Hash ignoredWords = new Hash(pw);

        // Read a line from the file using the Scanner object
        while (fileScan.hasNext()) {
            String s = fileScan.nextLine();
            ignoredWords.insert(s);
        }

        // Close the Scanner object
        fileScan.close();

        // Set file to getty.txt
        Scanner nextScan = new Scanner(new File("getty.txt"));
        header.gettyHeader();

        ObjectBinaryTree getty = new ObjectBinaryTree();
        int lineCount = 0;

        // Read in a line
        while (nextScan.hasNext()) {

            String line = nextScan.nextLine();
            lineCount++;
            String space = "     ";
            String pwSpace = "      ";

            // Output line
            if (lineCount > 9){
                space = "    ";
                pwSpace = "     ";
            }
            System.out.println(lineCount + space + line);
            pw.println(lineCount + pwSpace + line);

            String delims = "\\W+";
            String[] tokens = line.split(delims);
            int position = 0;

            // Find line position and insert
            for(int i = 0; i < tokens.length; i++){
                LinePosition linePosition = new LinePosition();
                linePosition.setLineNumber(lineCount);
                position++;
                linePosition.setPosition(position);

                // If word is supposed to be ignored we go back up to for loop
                if (ignoredWords.contains(tokens[i].toLowerCase())){
                    continue;
                }

                Word word = new Word(tokens[i].toLowerCase(), pw);
                word.insertLinePosition(linePosition);
                getty.insertBSTDup(word);
            }
        }

        // Print out the hash description, stats, and table
        header.hashDescript();
        ignoredWords.printHashFunctionDescription();
        header.hashStats();
        ignoredWords.printStatistics();
        header.printHashTable();
        ignoredWords.printTable();

        // Print tree
        header.printTreeHeader();
        getty.inTrav(getty.getRoot());

        // Query
        Query query = new Query(pw);
        char exit = 'e';

        // Repeat query until user quits
        do {
             exit = query.userInput();
            if (exit != 'e'){
                query.wordCheck(getty);
            }
        } while (exit != 'e');

        pw.close();
        System.out.println();
        pw.println();
        System.out.println("Program exiting, goodbye!");
        pw.println("Program exiting, goodbye!");
        System.exit(0);
    }
}
