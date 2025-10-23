package pdfwordscan;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


//ODU CS350 library for training data access
import edu.odu.cs.cs350.categorization.trainingData.TrainingData;

/**
 * PDFScanRun.java - tests the PDF word extractor
 *
 */
public class PDFScanRun
{
    public static void main( String[] args )
    {
       List<String> catFiles = new ArrayList<>();
        
        // this try block reads the odu training directory listing and places each entry into a ArrayList
        try
        {
            InputStream inData    = TrainingData.class.getResourceAsStream(TrainingData.resourcePath+TrainingData.directory);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inData));
            String entryLine;
            int idx = 0;

            //read the training data directory and populate an array list            
            while ((entryLine = reader.readLine()) != null) 
            {
                catFiles.add(entryLine);
                System.out.println("\n"+catFiles.get(idx++) );
            }
            reader.close();
        }     
        catch( IOException e )
        {
                System.out.println("\nFailed to read training data: " + e.getMessage());
        }    
        
   
        //This section gets an entry in the ArrayList of PDF files and extracts the data
        String dataLine;
        String resourceFile;
        dataLine     = catFiles.get(20);
        resourceFile = TrainingData.resourcePath+dataLine;
        InputStream pdfIn = TrainingData.class.getResourceAsStream(resourceFile);
        PdfTextExtractor extractor = new PdfTextExtractor(pdfIn);  
             
        //use the adobe library functions to extract text from the pdf
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
        // refactor to a separate function WordCounter();
        int count = 0;
        int index = 0;
        String target = "algo"; //try using a word stem
        
        while ((index = extractor.pdfWords.indexOf(target, index)) != -1) 
        {
            count++;
            index += target.length(); // Move past the last match
        }//end while

        System.out.println("\nWord count with \'algo\' stem: "+count);

    }//end main

    /**
     * Consume and discard the contents of an InputStream. Keeps the compiler happy when we
     * just need to ensure the stream is read/closed during a resource listing.
     */
    private static void consumeQuietly(InputStream in) throws IOException {
        byte[] buf = new byte[1024];
        while (in.read(buf) != -1) {
            // discard
        }
    }

}//end class
/** end PDFScanRun */  