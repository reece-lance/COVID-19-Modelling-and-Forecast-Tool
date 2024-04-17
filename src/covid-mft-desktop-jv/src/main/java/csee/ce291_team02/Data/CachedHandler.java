package csee.ce291_team02.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import csee.ce291_team02.AppConstants;
import csee.ce291_team02.MugExceptions.MugInvalidCacheException;
import csee.ce291_team02.MugLogger;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public abstract class CachedHandler<T> {

    private final Path cachePath;
    private Type cacheType;
    private int cashDuration = AppConstants.Properties.DEFAULT_CASH_DURATION;

    protected CachedHandler(Path cachePath, Type cacheType){
        this.cachePath = cachePath;
        this.cacheType = cacheType;
    }

    protected CachedHandler(Path cachePath, Type cacheType, int cashDuration){
        this.cachePath = cachePath;
        this.cacheType = cacheType;
        this.cashDuration = cashDuration;
    }

    public boolean hasData() throws Exception {
        return hasData(cachePath);
    }

    /**
     * This method will check if it has Data and if the fileLastModified was retrieved in less than 4 hours.
     * @param dataPath Path type
     * @return
     * @throws MugInvalidCacheException
     */

    private boolean hasData(Path dataPath) throws MugInvalidCacheException {
        File f = new File(cachePath.toString());
        if (!f.exists()) {
            return false;
        }

        //Getting the last modified time
        long fileLastModified = f.lastModified();

        boolean cashHasExpired = cashHasExpired(fileLastModified, cashDuration);

        return !cashHasExpired;
    }


    public void deleteCache(){
        File f = new File(cachePath.toString());

        if(f.exists()){
            f.delete();
        }
    }


    /**
     * Ths method will check when is the file retrieved.
     * @param fileLastModified Long type
     * @param cashDuration int type.
     * @return
     * @throws MugInvalidCacheException
     */

    public static boolean cashHasExpired(long fileLastModified, int cashDuration) throws MugInvalidCacheException {
        long now = Instant.now().toEpochMilli();
        long nHoursAgo = now - (3600000 * cashDuration);

        if (fileLastModified > now) {
            throw new MugInvalidCacheException("The file last modified cannot be in the future.");
        } else if (fileLastModified < nHoursAgo){
            return true;
        }
        return false;
    }


    /**
     * This method will serialize the data and store it on the filesystem.
     * @param data List type.
     */

    public void setCache(List<T> data) {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String path = cachePath.toString();
        File jsonFile = new File(path);


        //Process of serialization
        String serialize = gson.toJson(data);

        try {
            if (jsonFile.exists()) {
                jsonFile.delete();
            }
            jsonFile.createNewFile();
            FileWriter fw = new FileWriter(jsonFile);
            fw.write(serialize);
            fw.close();
        } catch (IOException e) {
            MugLogger.log(e.getStackTrace());
        }
    }

    /**
     * This method will retrieve the cashed file on the filSystem
     * deserialize it into it's response type and return it.
     */
    public ArrayList<T> getCacheData() {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //Reads the file
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(cachePath.toString()));
        } catch (FileNotFoundException e) {
            MugLogger.log(e.getStackTrace());
        }

        StringBuilder serializedData = new StringBuilder();

        reader.lines().forEach(serializedData::append);

        //SOURCE https://stackoverflow.com/a/5554296
        //Process of deserialization
        ArrayList<T> deserializeObject = (ArrayList<T>) gson.fromJson(serializedData.toString(), cacheType);

        return deserializeObject;
    }
}





