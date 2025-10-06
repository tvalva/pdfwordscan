package pdfwordscan;
import java.io.File;
import java.io.IOException;

/**
 * PDFScanRun.java - tests the PDF word extractor
 *
 */
public class PDFScanRun
{
    public static void main( String[] args )
    {
        File inFile;

        /** test for argument */
        if (args.length < 1) 
        {
            System.out.println("\nUsage: java PDFScanRun <filename.pdf>\n");
            return;
        }
        
        //current path validation
        String currentPath = System.getProperty("user.dir");
        System.out.println("Current working directory: " + currentPath);

        inFile = new File(args[0]);                
        System.out.println( "\nAdobe PDF Scanner target file: "+args[0]);
        PdfTextExtractor extractor = new PdfTextExtractor(inFile);

        try 
        {
            extractor.extractText();
            System.out.println(extractor.pdfWords);
        } 
        catch (IOException e) 
        {
            System.err.println("Failed to extract text: " + e.getMessage());
            return;
        }

        //create an array of words only for classification processing
        // "[^A-Za-z]+" regex, words no digits
        // "\\p{L}" unicode version for non-english alphabets
        
        String[] wordTokens = extractor.pdfWords.split("[^A-Za-z]+");

        //do something with the pdf words
        for (String word : wordTokens) 
        {      
            if (!word.isEmpty()) 
            {
                    System.out.println(word);
                    // do a comparison here...
            }
        }//end for
       
        //or count a word by finding it's index the number of times it appears
        // refactor to a separate function WordCounter( )
        int count = 0;
        int index = 0;
        String target = "Financial";
        while ((index = extractor.pdfWords.indexOf(target, index)) != -1) 
        {
            count++;
            index += target.length(); // Move past the last match
        }//end while

        System.out.println("\nWord count \'Financial\': "+count);

    }//end main
   
}/** end PDFScanRun */

  