package com.tools.file;

import java.io.File;

public class VideoFiles
{
  private File VideoFile;
  private boolean collect;
  
  public VideoFiles(File paramFile, boolean paramBoolean)
  {
    if (paramFile != null)
    {
      this.VideoFile = paramFile;
      this.collect = paramBoolean;
    }
  }
  
  public boolean getCollect()
  {
    return this.collect;
  }
  
  public File getVideoFile()
  {
    return this.VideoFile;
  }
  
  public void setCollect(boolean paramBoolean)
  {
    this.collect = paramBoolean;
  }
  
  public void setVideoFile(File paramFile)
  {
    this.VideoFile = paramFile;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/file/VideoFiles.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */