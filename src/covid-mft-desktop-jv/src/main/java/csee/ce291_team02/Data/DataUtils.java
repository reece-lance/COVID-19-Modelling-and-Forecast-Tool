package csee.ce291_team02.Data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.nio.file.Path;
import java.util.zip.ZipFile;

/**
 * Tries to fetch a file within a zip archive and serve it as an Input stream
 */
public class DataUtils {
    public static InputStream unzipFile(Path zipPath, String entryName) throws IOException {
        if(zipPath == null || entryName == null){
            return null;
        }

        File zipFile = zipPath.toFile();
        ZipFile zf = new ZipFile(zipFile);
        InputStream in = zf.getInputStream(zf.getEntry(entryName));

        return in;
    }
}
