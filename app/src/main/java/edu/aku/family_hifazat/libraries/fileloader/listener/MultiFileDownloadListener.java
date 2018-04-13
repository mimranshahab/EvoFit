package edu.aku.family_hifazat.libraries.fileloader.listener;

import java.io.File;

/**
 * Created by krishna on 17/10/17.
 */

public abstract class MultiFileDownloadListener {
    abstract public void onProgress(File downloadedFile, int progress, int totalFiles);

    public void onError(Exception e, int progress) {

    }
}