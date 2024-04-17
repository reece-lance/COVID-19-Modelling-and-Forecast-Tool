package FileManipulation;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Export {

        public static void main(String[] args) {
            try {
                File myObj = new File("C:\\Users\\sansk\\OneDrive\\Desktop\\Assignment.pdf");
                Scanner myReader = new Scanner(myObj);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    System.out.println(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("ERRRORRR");
                e.printStackTrace();
            }
            if (Desktop.isDesktopSupported()) {
                try {
                    Scanner user = new Scanner( System.in );
                    String  inputFileName;

                    System.out.print("Please enter the filename: ");
                    inputFileName = user.nextLine().trim();
                    File input = new File( inputFileName );
                    Scanner scan = new Scanner( input );
                    Desktop.getDesktop().open(input);
                } catch (IOException ex) {
                }
            }
        }
    }
