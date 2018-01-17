package com.activaire.video.player.util;

import android.util.Log;

import java.io.File;

/**
 * Created by GuessWh0o on 10/26/2017.
 */

public class DeleteAllFilesExceptTheBiggestFromDir {

    public static void deleteAllSmallFiles() {
        File yourDir = new File("pathToYourFolder");
        boolean exists = (yourDir.mkdir() || yourDir.isDirectory());
        if (exists) {
            boolean moreThan1 = false;
            File[] files = yourDir.listFiles();
            if (files.length > 1) {
                moreThan1 = true;
                File theBiggestFile = null;
                float theBiggestFileSize = 0.1f;
                for (final File fileEntry : yourDir.listFiles()) {
                    float newFileLength = fileEntry.length();
                    if (newFileLength > theBiggestFileSize) {
                        theBiggestFile = fileEntry;
                        theBiggestFileSize = newFileLength;
                    }
                }
                if (theBiggestFile != null && moreThan1) {
                    for (final File fileEntry : yourDir.listFiles()) {
                        if (fileEntry.getName().equals(theBiggestFile.getName())) {
                            continue;
                        } else {
                            if (fileEntry.delete()) {
                                Log.d("DELETED", ": " + fileEntry.getName());
                            } else {
                                Log.d("NOT DELETED", ": " + fileEntry.getName());
                            }
                        }
                    }
                    Log.d("BIGGEST FILE", ": " + theBiggestFile.getName());
                }
            }
        }
    }
}
