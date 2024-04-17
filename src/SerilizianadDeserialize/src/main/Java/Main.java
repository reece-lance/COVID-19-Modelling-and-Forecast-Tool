import main.Java.CovidCaseData.CovidCaseByDate2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
       /* Path tmpdir = Files.createTempDirectory("cashDirectory");
        var filePath =tmpdir.toAbsolutePath() +File.pathSeparator+"JsonData.json";*/
        String aBCD = "JsonDataaa.json";
        File jsonFile = new File(aBCD);


        if (!jsonFile.exists()) {
            try {

                jsonFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }




            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            CovidCaseByDate2 data = new CovidCaseByDate2("Overview",
                    "United Kingdom",
                    "K02000001",
                    LocalDate.of(2020, 11, 4),
                    1434,
                    1123197);



            String serialize = gson.toJson(data);//serialize toJson


            // Put it inside of the jsonFile
            try {
                FileWriter fw = new FileWriter(aBCD);
                fw.write(serialize);
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            // Get the contents of the file and serialize that, not the original string

            //Deserialize

        BufferedReader reader = new BufferedReader(new FileReader(aBCD));

        CovidCaseByDate2 deserializeObject = gson.fromJson(reader, CovidCaseByDate2.class);



            System.out.println(serialize); //serialized
            System.out.println(deserializeObject);//deserialized
        }
    }


