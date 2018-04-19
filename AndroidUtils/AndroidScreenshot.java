import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class AndroidScreenshot extends AppCompatActivity {

    //  View rootView = getWindow().getDecorView().getRootView();
    private static TextureView textureView;
    Button button;
    SurfaceView mPreview;
    private SurfaceHolder holder;

    private static File screenshotFile;

    private static float videoWidth = 1080.0f;
    private static float videoHeight = 1920.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textureView = (TextureView) findViewById(R.id.textureView);
        button = (Button) findViewById(R.id.button);

       // mPreview = (SurfaceView) findViewById(R.id.surface);
//        holder = mPreview.getHolder();
       // holder.setFormat(PixelFormat.RGBX_8888);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // takeScreenShot();
                takeScreenshotVideoPlaying();
            }
        });
    }



    public static Bitmap takeScreenshotVideoPlaying() {
        Log.d("", "takeScreenshotVideoPlaying() in VideoPlayerActivity running!");

        Bitmap bitmap = null;
        int tries = 100;
        for (int i = 1; i < tries; i++) {
            if (textureView.isAvailable()) {

                float bitmapScale = 4.0f;
                float bitmapWidth = videoWidth / bitmapScale;
                float bitmapHeight = videoHeight / bitmapScale;
                bitmap = textureView.getBitmap((int) bitmapWidth, (int) bitmapHeight);
                if (bitmap != null) {
                    Log.d("", "screenshot taken");
                    break;
                }
            }
        }
        saveScreenshot(bitmap);
        return bitmap;
    }


    private static void saveScreenshot(Bitmap bitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();

        Date now = new Date();
        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);


        String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
        // see if this helps the uploading to GCS

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bytes);

        screenshotFile = new File(mPath);


        try {
            FileOutputStream fo = new FileOutputStream(screenshotFile);
            if (screenshotFile.exists()) {
                screenshotFile.delete();
            }
            if (screenshotFile.createNewFile()) {

                fo.write(bytes.toByteArray());
                fo.flush();
                fo.close();
                Log.d("Saved", "saveScreenshot: ");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


//
//    private void openScreenshot(File imageFile) {
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_VIEW);
//        Uri uri = Uri.fromFile(imageFile);
//        intent.setDataAndType(uri, "image/*");
//        startActivity(intent);
//    }

//    private void takeScreenShot(){
//        Date now = new Date();
//        android.text.format.DateFormat.format("yyyy-MM-dd_hh:mm:ss", now);
//
//        try {
//            // image naming and path  to include sd card  appending name you choose for file
//            String mPath = Environment.getExternalStorageDirectory().toString() + "/" + now + ".jpg";
//
//            // create bitmap screen capture
//            View v1 = getWindow().getDecorView().getRootView();
//            v1.setDrawingCacheEnabled(true);
//            Bitmap bitmap = Bitmap.createBitmap(v1.getDrawingCache());
//            v1.setDrawingCacheEnabled(false);
//
//            File imageFile = new File(mPath);
//
//            FileOutputStream outputStream = new FileOutputStream(imageFile);
//            int quality = 100;
//            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
//            outputStream.flush();
//            outputStream.close();
//
//            openScreenshot(imageFile);
//        } catch (Throwable e) {
//            // Several error may come out with file handling or OOM
//            e.printStackTrace();
//        }
//    }
