package edu.aku.ehs.libraries.fileloader.listener;

import edu.aku.ehs.libraries.fileloader.pojo.FileResponse;
import edu.aku.ehs.libraries.fileloader.request.FileLoadRequest;

/**
 * Created by krishna on 12/10/17.
 */

public interface FileRequestListener<T> {
    void onLoad(FileLoadRequest request, FileResponse<T> response);

    void onError(FileLoadRequest request, Throwable t);
}
