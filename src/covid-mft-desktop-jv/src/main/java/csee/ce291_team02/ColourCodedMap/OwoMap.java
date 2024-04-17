package csee.ce291_team02.ColourCodedMap;

import csee.ce291_team02.AppConstants;
import csee.ce291_team02.Data.DataUtils;
import csee.ce291_team02.MugLogger;

import java.io.*;
import java.util.HashMap;

public class OwoMap {
    private String comment;
    private int catLabNumber;
    private int catNumber;
    private int xSize;
    private int ySize;

    // categories representing pixel values
    public short[][] data;
    private String[] metadata;
    // code is the index, cat is the value
    private String[] catLabels;
    private HashMap<String, Short> codeCatMap;

    public String getComment() {
        return comment;
    }

    public int getCatLabNumber() {
        return catLabNumber;
    }

    public int getCatNumber() {
        return catNumber;
    }

    public int getxSize() {
        return xSize;
    }

    public int getySize() {
        return ySize;
    }

    public String[] getCatLabels() { return catLabels; }

    public HashMap<String, Short> getCodeCatMap(){ return this.codeCatMap; }

    public String[] getMetadata() {
        return metadata;
    }

    public boolean isDataLoaded() {
        return dataLoaded;
    }

    private boolean dataLoaded;

    /**
     * Expects a name of a .owo map that is located in the maps zip file in resources.
     * @param filename Path to file.
     * @throws FileNotFoundException If the file is not found.
     */
    public void readOwoMap(String filename) throws IOException {

        InputStream is = DataUtils.unzipFile(AppConstants.Paths.resolvePath(AppConstants.Paths.TempResources.TEMP_MAP_ZIP_FILE), filename);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        try {
            this.comment = reader.readLine();
            this.catLabNumber = Integer.parseInt(reader.readLine());
            this.catNumber = Integer.parseInt(reader.readLine());
            this.catLabels = new String[catLabNumber];
            this.codeCatMap = new HashMap<>(catLabNumber);
            this.xSize = Integer.parseInt(reader.readLine());
            this.ySize = Integer.parseInt(reader.readLine());
            this.data = new short[this.xSize][this.ySize];
            for (short i = 0; i < catLabNumber; i++) {
                this.catLabels[i] = reader.readLine();
                this.codeCatMap.put(catLabels[i], i);
            }
            for (int i = 0; i < this.xSize; i++) {
                for (int j = 0; j < ySize; j++) {
                    this.data[i][j] = Short.parseShort(reader.readLine());
                }
            }
            final String firstMetadataLine = reader.readLine();
            if (firstMetadataLine != null) {
                this.metadata = new String[catNumber];
                this.metadata[0] = firstMetadataLine;

                for (int i = 1; i < catNumber; i++) {
                    this.metadata[i] = reader.readLine();
                }
            }

            reader.close();
            this.Celebrate();
            this.dataLoaded = true;
        } catch (IOException e) {
            MugLogger.log("Error while reading owo map.");
            MugLogger.log(e.getStackTrace());
            this.dataLoaded = false;
        }
    }

    private void Celebrate() {
        MugLogger.log("Wubba Lubba DUB DUB");
    }
}
