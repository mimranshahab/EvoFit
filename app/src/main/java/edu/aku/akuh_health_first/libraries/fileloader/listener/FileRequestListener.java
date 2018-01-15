package edu.aku.akuh_health_first.libraries.fileloader.listener;

import edu.aku.akuh_health_first.libraries.fileloader.pojo.FileResponse;
import edu.aku.akuh_health_first.libraries.fileloader.request.FileLoadRequest;

/**
 * Created by krishna on 12/10/17.
 */

public interface FileRequestListener<T> {
    void onLoad(FileLoadRequest request, FileResponse<T> response);

    void onError(FileLoadRequest request, Throwable t);
}
