package com.tools.Image;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Environment;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class BitmapUtils
{
  private static final long MB = 1048576L;
  
  public static byte[] Bitmap2Bytes(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static Bitmap Bytes2Bitmap(Intent paramIntent)
  {
    paramIntent = paramIntent.getByteArrayExtra("bitmap");
    return BitmapFactory.decodeByteArray(paramIntent, 0, paramIntent.length);
  }
  
  public static Bitmap getBitmap(String paramString)
  {
    try
    {
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      File localFile = new File(paramString);
      if ((localFile.exists()) && (localFile.length() / 1048576L > 1L)) {
        localOptions.inSampleSize = 2;
      }
      paramString = BitmapFactory.decodeFile(paramString, localOptions);
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static Bitmap getBitmap(String paramString1, String paramString2)
    throws IOException
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    paramString1 = new File(paramString1, paramString2);
    if ((paramString1.exists()) && (paramString1.length() / 1048576L > 1L)) {
      localOptions.inSampleSize = 2;
    }
    return BitmapFactory.decodeFile(paramString1.getAbsolutePath(), localOptions);
  }
  
  public static String getSDPath()
  {
    if (Environment.getExternalStorageState().equals("mounted")) {
      return Environment.getExternalStorageDirectory().toString();
    }
    return Environment.getDownloadCacheDirectory().toString();
  }
  
  public static Bitmap getViewBitmap(View paramView)
  {
    paramView.clearFocus();
    paramView.setPressed(false);
    boolean bool = paramView.willNotCacheDrawing();
    paramView.setWillNotCacheDrawing(false);
    int i = paramView.getDrawingCacheBackgroundColor();
    paramView.setDrawingCacheBackgroundColor(0);
    if (i != 0) {
      paramView.destroyDrawingCache();
    }
    paramView.buildDrawingCache();
    Bitmap localBitmap = paramView.getDrawingCache();
    if (localBitmap == null) {
      return null;
    }
    localBitmap = Bitmap.createBitmap(localBitmap);
    paramView.destroyDrawingCache();
    paramView.setWillNotCacheDrawing(bool);
    paramView.setDrawingCacheBackgroundColor(i);
    return localBitmap;
  }
  
  public static void save(String paramString1, String paramString2, Bitmap paramBitmap)
    throws IOException
  {
    paramString1 = new File(paramString1, paramString2);
    if (!paramString1.getParentFile().exists()) {
      paramString1.getParentFile().mkdirs();
    }
    if (!paramString1.exists()) {
      paramString1.createNewFile();
    }
    paramString1 = new FileOutputStream(paramString1);
    paramBitmap.recycle();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, paramString1);
  }
  
  public static void saveFile(String paramString1, String paramString2, Bitmap paramBitmap)
    throws IOException
  {
    File localFile = new File(paramString1, paramString2);
    if (!localFile.getParentFile().exists()) {
      localFile.getParentFile().mkdirs();
    }
    if (!localFile.exists()) {
      localFile.createNewFile();
    }
    paramString1 = new BufferedOutputStream(new FileOutputStream(new File(paramString1 + paramString2)));
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 80, paramString1);
    paramString1.flush();
    paramString1.close();
  }
  
  public static Bitmap shot(Activity paramActivity)
  {
    View localView = paramActivity.getWindow().getDecorView();
    localView.setDrawingCacheEnabled(true);
    localView.buildDrawingCache();
    paramActivity = paramActivity.getWindowManager().getDefaultDisplay();
    localView.layout(0, 500, paramActivity.getWidth() - 200, paramActivity.getHeight() - 250);
    return Bitmap.createBitmap(localView.getDrawingCache());
  }
  
  public static Bitmap zoomImage(Bitmap paramBitmap, double paramDouble1, double paramDouble2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale((float)paramDouble1 / f1, (float)paramDouble2 / f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/Image/BitmapUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */