import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by GuessWh0o on 10/26/2017.
 */

public class CreateDirectoriesInAppFolder {

    private Context context;
    private static String pathToFirstFolder;
    private static String pathToSecondFolder;

    public CreateDirectoriesInAppFolder(Context context) {
        this.context = context;
    }


    public static String getPathToFirstFolder() {
        return pathToFirstFolder;
    }


    public static String getPathToSecondFolder() {
        return pathToSecondFolder;
    }


    public static boolean createFileDirectories(Context context) {
        boolean firstFolderExists;
        boolean secondFolderExists;

        File firstFolder = new File(Environment.getExternalStorageDirectory(), "firstFolder");
        firstFolderExists = firstFolder.mkdirs() || firstFolder.isDirectory();

        File secondFolder = new File(Environment.getExternalStorageDirectory(), "secondFolder");
        secondFolderExists = secondFolder.mkdirs() || secondFolder.isDirectory();

        if (firstFolderExists) {
            pathToFirstFolder = firstFolder.getAbsolutePath();
        }
        if (secondFolderExists) {
            pathToSecondFolder = secondFolder.getAbsolutePath();
        }
        return firstFolderExists && secondFolderExists;
    }
}
