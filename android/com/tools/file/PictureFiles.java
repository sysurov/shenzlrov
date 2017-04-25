package com.tools.file;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import java.io.File;

public class PictureFiles
{
  private static final long MB = 1048576L;
  private File PictureFile;
  private boolean collect;
  
  public PictureFiles(File paramFile, boolean paramBoolean)
  {
    if (paramFile != null)
    {
      this.PictureFile = paramFile;
      this.collect = paramBoolean;
    }
  }
  
  public Bitmap getBitmap()
  {
    try
    {
      Object localObject = new BitmapFactory.Options();
      if ((this.PictureFile.exists()) && (this.PictureFile.length() / 1048576L > 1L)) {
        ((BitmapFactory.Options)localObject).inSampleSize = 2;
      }
      localObject = BitmapFactory.decodeFile(this.PictureFile.getPath(), (BitmapFactory.Options)localObject);
      return (Bitmap)localObject;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }
  
  public boolean getCollect()
  {
    return this.collect;
  }
  
  public File getVideoFile()
  {
    return this.PictureFile;
  }
  
  public void setCollect(boolean paramBoolean)
  {
    this.collect = paramBoolean;
  }
  
  public void setPictureFile(File paramFile)
  {
    this.PictureFile = paramFile;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/file/PictureFiles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */