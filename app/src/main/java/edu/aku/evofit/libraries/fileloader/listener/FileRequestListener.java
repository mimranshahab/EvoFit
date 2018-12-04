package edu.aku.evofit.libraries.fileloader.listener;

import edu.aku.evofit.libraries.fileloader.pojo.FileResponse;
import edu.aku.evofit.libraries.fileloader.request.FileLoadRequest;

/**
 * Created by krishna on 12/10/17.
 */

public interface FileRequestListener<T> {
    void onLoad(FileLoadRequest request, FileResponse<T> response);

    void onError(FileLoadRequest request, Throwable t);
}
