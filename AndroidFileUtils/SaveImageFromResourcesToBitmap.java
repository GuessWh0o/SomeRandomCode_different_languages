import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Maks on 10/26/2017.
 */

public class SaveImageFromResourcesToBitmap {
    private static void saveImageFromResToDevice(Context context){
        FileOutputStream outputStream;
        try{
            Bitmap bm= BitmapFactory.decodeResource(context.getResources(), R.drawable.omniasig);
            File file=new File(FileUtils.getPathToPhotosFolder(),"IMAGE_NAME.png");
            outputStream=new FileOutputStream(file);
            bm.compress(Bitmap.CompressFormat.PNG,100,outputStream);
            outputStream.flush();
            outputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
