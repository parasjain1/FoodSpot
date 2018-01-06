package com.starks.foodspots.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;


/**
 * Created by sharda on 10/06/17.
 */

public class ImageCompressor {
    Context context;

    public ImageCompressor(Context context)
    {
        this.context = context;
    }

    public Uri compress(Uri uri)
    {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/req_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        Log.i("ImageCompressor", "" + file);
        if (file.exists())
            file.delete();
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            return Uri.fromFile(file);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
//    public Uri compress(Bitmap inImage) {
//        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
//        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
//        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
//        return Uri.parse(path);
//    }

    public Uri compress(Bitmap bitmap)
    {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root + "/req_images");
        myDir.mkdirs();
        Random generator = new Random();
        int n = 10000;
        n = generator.nextInt(n);
        String fname = "Image-" + n + ".jpg";
        File file = new File(myDir, fname);
        Log.i("ImageCompressor", "" + file);
        if (file.exists())
            file.delete();
        try {
            //Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            Uri savedImageURI = Uri.parse(file.getAbsolutePath());
            return savedImageURI;
        } catch (Exception e) {
            Log.d("fileuri",e+"");
            e.printStackTrace();
            return null;
        }

    }
}

