package com.example.function;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 根据bitmap保存图片,图片的相关操作工具类
 */
public class SaveImageUtil {

    /**
     * 根据链接获取Bitmap图片
     *
     * @param url http 地址
     * @return Bitmap 可能为 null
     */
    public static Bitmap url2Bitmap(String url) {
        Bitmap bitmap = null;
        try {
            URL imageUrl = new URL(url);
            URLConnection conn = imageUrl.openConnection();
            HttpURLConnection httpURLConnection = (HttpURLConnection) conn;
            int length = httpURLConnection.getContentLength();
            conn.connect();
            //获取图像的字符流
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is, length);
            bitmap = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     * java.io.File.mkdir()：只能创建一级目录，且父目录必须存在，否则无法成功创建一个目录。
     * <p>
     * java.io.File.mkdirs()：可以创建多级目录，父目录不一定存在。
     *
     * @param bitmap 位图
     */
    public static File saveBitmapToLoacl(Bitmap bitmap) {
        File imageDir = new File(Environment.getExternalStorageDirectory(), "zyApp");
        //不存在目录就创建
        if (!imageDir.exists()) {
            imageDir.mkdirs();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(imageDir, fileName);
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream out = new FileOutputStream(file);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 图片插入到相册
     *
     * @param context
     * @param file
     */
    public static void insertImage(Context context, File file) {
        // 插入图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(),
                    file.getName(), null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 发送广播，通知刷新图库的显示
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(file)));
    }

    /**
     * 图片插入到相册
     *
     * @param context
     * @param bitmap
     */
    public static void insertImage(Context context, Bitmap bitmap) {
        // 插入图库
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap,
                "title", null);
        // 发送广播，通知刷新图库的显示
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse(path)));
    }

    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),
                inImage, "Title", null);
        return Uri.parse(path);
    }


    public static void savePhotoToMedia(Context context,File file,String fileName) throws FileNotFoundException {
        String uriString = MediaStore.Images.Media.insertImage(context.getContentResolver(),
                file.getAbsolutePath(), fileName, null);
        File file1 = new File(getRealPathFromURI(Uri.parse(uriString),context));
        updatePhotoMedia(file1,context);
    }

    //更新图库  oppo 有些机型，不设置日志，会默认1970年1月1日
    private static void updatePhotoMedia(File file ,Context context){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(file));
        context.sendBroadcast(intent);
    }
    //得到绝对地址
    private static String getRealPathFromURI(Uri contentUri,Context context) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String fileStr = cursor.getString(column_index);
        cursor.close();
        return fileStr;
    }

}
