import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

/**
 * Created by Maks on 10/26/2017.
 */

public class CheckAndWriteToExternalStorage {

    private static String pathToExternalAppFolder = "";

    public static void checkExternalMedia(Context context){
        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // Can read and write the media
            listExternalStoragePath(context);
        } else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            // Can only read the media
            Toast.makeText(context, "SDcard is READ-ONLY", Toast.LENGTH_LONG).show();
        } else {
            // Can't read or write
            Toast.makeText(context, "CAN'T WRITE TO SDcard", Toast.LENGTH_LONG).show();
        }
        if(Environment.MEDIA_UNMOUNTED.equals(state)) {
            Toast.makeText(context, "COULD NOT FIND SDcard", Toast.LENGTH_LONG).show();
        }
    }

    private static void listExternalStoragePath(Context context) {
        File[] fs = context.getExternalFilesDirs(null);
        String extPath = "";
        // at index 0 you have the internal storage and at index 1 the real external...
        if (fs != null && fs.length >= 2) {
            extPath = fs[1].getAbsolutePath();
            Log.e("SD Path", extPath);
            pathToExternalAppFolder = extPath;
        }
    }
}
