package pdfwordscan;

import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * A service class to extract text from PDF files using Apache PDFBox.
 * InputStream pdfStream = new FileInputStream("document.pdf"); // or from a resource
PDDocument document = PDDocument.load(pdfStream);
*/
public class PdfTextExtractor 
{
    // private variables
    private final InputStream inputStream;
    private PDFTextStripper stripper;
    
    // public variables
    public String pdfWords;

    // Constructor
    public PdfTextExtractor(InputStream pdfStream) 
    {
        this.inputStream = pdfStream;
    }

    // Public method to extract text
    public boolean extractText() throws IOException
     {
        try (PDDocument document = PDDocument.load(this.inputStream)) 
        {
            stripper = new PDFTextStripper();
            pdfWords = stripper.getText(document);
            return(true);
        } 
     }
 }//end PdfTestExtractor