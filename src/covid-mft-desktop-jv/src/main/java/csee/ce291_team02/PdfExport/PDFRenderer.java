package csee.ce291_team02.PdfExport;

import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PDFRenderer {
    /**
     * Set up PDF creation at the destination
     * @param file The file as a string
     * @param dest The destination as a string
     */
    public static void renderToPDF(String file, Path dest) {

        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocument(file);
        doRenderToPDF(renderer, dest);
    }

    /**
     * Creates PDF file at the destination
     * @param renderer Renderer which renders the PDF
     * @param dest The destination as a string
     */
    private static void doRenderToPDF(ITextRenderer renderer, Path dest) {
        System.out.println(dest);
        File f = dest.toFile();

        if(!Files.exists(dest)){
            try {
                Files.createFile(dest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OutputStream os;
        try {
            os = new FileOutputStream(dest.toString());
            renderer.layout();
            try {
                renderer.createPDF(os);
            } catch (com.lowagie.text.DocumentException e) {
                e.printStackTrace();
            }
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

