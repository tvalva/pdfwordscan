package pdfwordscan;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * A service class to extract text from PDF files using Apache PDFBox.
 */
public class PdfTextExtractor 
{
    // private variables
    private final File inputFile;
    private PDFTextStripper stripper;
    
    // public variables
    public String pdfWords;

    // Constructor
    public PdfTextExtractor(File inFile) 
    {
        this.inputFile = inFile;
    }

    // Public method to extract text
    public boolean extractText() throws IOException
     {
        try (PDDocument document = PDDocument.load(inputFile)) 
        {
            stripper = new PDFTextStripper();
            pdfWords = stripper.getText(document);
            return(true);
        } 
     }
 }//end PdfTestExtractor