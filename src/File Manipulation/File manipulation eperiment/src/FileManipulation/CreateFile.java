package FileManipulation;

import java.io.File;
import java.io.IOException;

public class CreateFile {
        public static void main(String[] args) {
            try {
                File myObj = new File("C:\\Users\\sansk\\OneDrive\\Desktop\\Exp\\Read.csv");
                if (myObj.createNewFile()) {
                    System.out.println("File created " + myObj.getName());
                } else {
                    System.out.println("File already exists");
                }
            } catch (IOException e) {
                System.out.println("ERRORRRRRR");
                e.printStackTrace();
            }
        }
    }

