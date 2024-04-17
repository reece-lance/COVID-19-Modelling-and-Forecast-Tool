package csee.ce291_team02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemHelper {

    public static void ifFolderDoesNotExistCreate(Path path) {
        if (!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteDirectoryExtract(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.err.printf("Unable to delete this path : %s%n%s", path, e);
        }
    }
}
