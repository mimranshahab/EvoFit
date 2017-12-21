package com.structure.managers;

import android.content.Context;
import android.content.ContextWrapper;
import android.webkit.URLUtil;


import com.structure.BaseApplication;
import com.structure.constatnts.AppConstants;
import com.structure.utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.Random;

import static com.structure.constatnts.AppConstants.ROOT_MEDIA_PATH;
import static com.structure.constatnts.AppConstants.USER_PROFILE_PICTURE_FOLDER_DIRECTORY;
import static com.structure.constatnts.AppConstants.USER_PROFILE_PICTURE_NAME;
import static com.structure.constatnts.AppConstants.USER_PROFILE_THUMBNAIL_NAME;


/**
 * Created by muhammadmuzammil on 4/26/2017.
 */

public class FileManager {
    private static final String AUDIO_DIRECTORY_PATH = ROOT_MEDIA_PATH + "/Aaloo Audio";
    private static final String VIDEO_DIRECTORY_PATH = ROOT_MEDIA_PATH + "/Aaloo Video";
    private static final String IMAGE_DIRECTORY_PATH = ROOT_MEDIA_PATH + "/Aaloo Image";

    private static void createDirectory(String directory) {
        /*First check if root directory not created then create*/
        File rootDirectory = new File(ROOT_MEDIA_PATH);
        if (!rootDirectory.exists())
            rootDirectory.mkdirs();

        File innerDirectory = new File(directory);
        if (!innerDirectory.exists())
            innerDirectory.mkdir();

    }

    /**
     * Avatar is the location on which it saved picture
     *
     * @param avatar
     * @param thumbnail
     * @return
     */
    public static File createProfileImage(String avatar, boolean thumbnail, Context context) {
        if (avatar == null || avatar.equals(""))
            return null;

        try {
            avatar = avatar.substring(0, avatar.lastIndexOf(".")) + ".j";
        } catch (Exception e) {
//            e.printStackTrace();
            return null;
        }
        ContextWrapper cw = new ContextWrapper(context);
        File directory;

        if (thumbnail)
            directory = cw.getDir("userProfile", Context.MODE_PRIVATE);
        else
            directory = cw.getCacheDir();

        if (!directory.exists()) {
            directory.mkdir();
        }

        String filename = URLUtil.guessFileName(avatar, null, null);
        File imageFile = new File(directory, filename);
        if (!imageFile.exists())
            try {
                imageFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        return imageFile;
    }

    public static File getUserImage(String avatar, boolean thumbnail, Context context) {
        if (avatar == null || avatar.equals(""))
            return null;

        // FIXME: 8/18/2017  :  REMOVE NULL POINTER EXCEPTION // CONVERT TO JPG
        try {
            avatar = avatar.substring(0, avatar.lastIndexOf(".")) + ".j";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        ContextWrapper cw = new ContextWrapper(context);
        File directory;

        if (thumbnail)
            directory = cw.getDir(USER_PROFILE_PICTURE_FOLDER_DIRECTORY, Context.MODE_PRIVATE);
        else {
            directory = cw.getCacheDir();
            avatar = avatar.replace(AppConstants.SUFFIX_THUMB_IMAGE, "");
        }

        if (!directory.exists()) {
            return null;
        }
        String filename = URLUtil.guessFileName(avatar, null, null);
        File imageFile = new File(directory, filename);
        if (!imageFile.exists())
            return null;
        return imageFile;
    }

    public static File getMyImage(boolean isThumbnail, Context context) {
        ContextWrapper cw = new ContextWrapper(context);
        if (isThumbnail)
            return new File(cw.getDir(USER_PROFILE_PICTURE_FOLDER_DIRECTORY, Context.MODE_PRIVATE), USER_PROFILE_THUMBNAIL_NAME);
        else
            return new File(cw.getDir(USER_PROFILE_PICTURE_FOLDER_DIRECTORY, Context.MODE_PRIVATE), USER_PROFILE_PICTURE_NAME);
    }

    public static boolean isFileExits(String path) {
        return new File(path).exists();
    }

    public static void copyFile(String sourcePath, File destFile) {
        try {
            File sourceFile = new File(sourcePath);
//        File destFile = new File(destPath);

            if (!sourceFile.exists()) {
                return;
            }

            FileChannel source = null;
            FileChannel destination = null;

            source = new FileInputStream(sourceFile).getChannel();

            destination = new FileOutputStream(destFile).getChannel();
            if (source != null) {
                destination.transferFrom(source, 0, source.size());
            }
            if (source != null) {
                source.close();
            }
            destination.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static File copyImage(String sourcePath, String targetPath){
        try {
            InputStream in = new FileInputStream(sourcePath);
            OutputStream out = new FileOutputStream(targetPath);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            return new File(targetPath);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFileNameFromPath(String path) {
        int index = path.lastIndexOf("/");
        if (index > path.length())
            return "Unknown File";

        return path.substring(index + 1);
    }

    public static void copyFile(String inputPath, String outputPath) {
        InputStream in = null;
        OutputStream out = null;
        try {

            //create output directory if it doesn't exist
            File dir = new File(outputPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }


            in = new FileInputStream(inputPath);
            out = new FileOutputStream(outputPath);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;

            // write the output file (You have now copied the file)
            out.flush();
            out.close();
            out = null;

        } catch (FileNotFoundException fnfe1) {
            LogUtil.e("tag", fnfe1.getMessage());
        } catch (Exception e) {
            LogUtil.e("tag", e.getMessage());
        }

    }


    public static String getFileSize(String path) {
        File file = new File(path);
        if (!file.exists())
            return "0 KB";

        return formatFileSize(file.length());
    }


    public static String formatFileSize(long size) {
        String hrSize = null;

        double b = size;
        double k = size / 1024.0;
        double m = ((size / 1024.0) / 1024.0);
        double g = (((size / 1024.0) / 1024.0) / 1024.0);
        double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

        DecimalFormat dec = new DecimalFormat("0.0");

        if (t > 1) {
            hrSize = dec.format(t).concat(" TB");
        } else if (g > 1) {
            hrSize = dec.format(g).concat(" GB");
        } else if (m > 1) {
            hrSize = dec.format(m).concat(" MB");
        } else if (k > 1) {
            hrSize = dec.format(k).concat(" KB");
        } else {
            hrSize = dec.format(b).concat(" Bytes");
        }

        return hrSize;
    }

    public static File createFileInAppDirectory(Context context, String folderName, String fileName) {
        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir(folderName, Context.MODE_PRIVATE);
        return new File(directory, fileName);
    }
}
