package com.maks.ddpmeteortest;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by maks on 5/12/17.
 */

public class MemoryInfo {

    private static final String TAG = "memory";
    private long totalStorageBytes;
    private long availableStorageBytes;
    private long usedStorageBytes;

    public long getTotalStorageBytes() {
        return totalStorageBytes;
    }

    public long getAvailableStorageBytes() {
        return availableStorageBytes;
    }

    public long getUsedStorageBytes() {
        return usedStorageBytes;
    }

    public void checkDeviceMemory() {
        totalStorageBytes = getTotalMemory();
        availableStorageBytes = getFreeExternalMemory();
        usedStorageBytes = getUsedMemory();
    }

    public double convertToGb(long bytesAvailable) {
        double gigsAvailable = (double)
                bytesAvailable / (double) (1024 * 1024 * 1024);
        return gigsAvailable;
    }

    public long getFreeInternalMemory() {
        long internalMemory = getFreeMemory(Environment.getDataDirectory());
        return internalMemory /*convertToGb(internalMemory)*/;
    }

    // Get external (SDCARD) free space
    private long getFreeExternalMemory() {
        long extMem =  getFreeMemory(Environment.getExternalStorageDirectory());
        return extMem /*convertToGb(extMem)*/;
    }

    // Get Android OS (system partition) free space
    public long getFreeSystemMemory() {
        long sysMem =  getFreeMemory(Environment.getRootDirectory());
        return sysMem;
    }

    // Get free space for provided path
    // Note that this will throw IllegalArgumentException for invalid paths
    private long getFreeMemory(File path)  throws IllegalArgumentException {
        StatFs stats = new StatFs(path.getAbsolutePath());
        return (long) stats.getAvailableBlocks() * stats.getBlockSize();
    }

    private long getTotalMemory() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long totalSizeSD = (long)stat.getBlockSize() * (long)stat.getBlockCount();

        // only counting the sd card, thats where all the mp3s are stored
        return totalSizeSD /*convertToGb(totalSizeSD)*/;
    }

    private long getUsedMemory() {
        StatFs stats = new StatFs(Environment.getExternalStorageDirectory().getAbsolutePath());
        long totalSize = (long)stats.getBlockSize() * (long)stats.getBlockCount();
        long freeMem = getFreeMemory(Environment.getExternalStorageDirectory());
        long usedMem = totalSize - freeMem;
        return usedMem /*convertToGb(usedMem)*/;
    }
}

