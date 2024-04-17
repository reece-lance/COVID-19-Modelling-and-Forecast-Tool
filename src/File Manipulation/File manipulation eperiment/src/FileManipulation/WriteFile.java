package FileManipulation;

import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    public static void main(String[] args) {
        try {
            FileWriter myWriter = new FileWriter("C:\\Users\\sansk\\OneDrive\\Desktop\\Exp\\Read.csv");
            myWriter.write("Team meeting going on");

            myWriter.close();
            System.out.println("Successsssss");
        } catch (IOException e) {
            System.out.println("ERRORRRRRRR");
            e.printStackTrace();
        }
    }
}
