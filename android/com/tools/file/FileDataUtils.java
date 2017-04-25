package com.tools.file;

import android.os.Environment;
import android.util.Log;
import com.deepinfar.FileData.RovReadFileData;
import com.deepinfar.ProtocolData.RovInitData;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FileDataUtils
{
  private static FileDataUtils fileDataUtils;
  private Gson Gson = new Gson();
  private BufferedReader bufferedReader;
  private File file;
  private FileInputStream fileInputStream;
  private FileWriter fileWriter;
  private RovReadFileData readFileData;
  
  private FileDataUtils()
    throws FileNotFoundException, IOException
  {
    Object localObject = RovInitData.DATAFILE_PATH;
    localObject = getSDPath() + (String)localObject + "/";
    String str = RovInitData.DATAFILE_NAME;
    makeRootDirectory((String)localObject);
    this.file = makeFile((String)localObject, str);
    if (!this.file.exists())
    {
      this.file.getParentFile().mkdirs();
      this.file.createNewFile();
    }
    this.fileInputStream = new FileInputStream(this.file);
    this.bufferedReader = new BufferedReader(new InputStreamReader(this.fileInputStream));
    localObject = new StringBuffer();
    for (;;)
    {
      str = this.bufferedReader.readLine();
      if (str == null)
      {
        this.bufferedReader.close();
        this.fileInputStream.close();
      }
      try
      {
        this.readFileData = ((RovReadFileData)this.Gson.fromJson(((StringBuffer)localObject).toString(), RovReadFileData.class));
        if (this.readFileData == null)
        {
          this.readFileData = new RovReadFileData();
          writerFile();
        }
        this.readFileData.compareVersion();
        writerFile();
        return;
        if (!((StringBuffer)localObject).toString().equals("")) {
          ((StringBuffer)localObject).append("\n");
        }
        ((StringBuffer)localObject).append(str);
      }
      catch (Exception localException)
      {
        for (;;)
        {
          this.readFileData = new RovReadFileData();
          writerFile();
        }
      }
    }
  }
  
  public static FileDataUtils getFileDataUtils()
    throws IOException, FileNotFoundException
  {
    if (fileDataUtils == null) {}
    try
    {
      fileDataUtils = new FileDataUtils();
      return fileDataUtils;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      fileDataUtils = null;
      throw new FileNotFoundException();
    }
    catch (IOException localIOException)
    {
      fileDataUtils = null;
      throw new IOException();
    }
  }
  
  public static List<File> getFileDir(List<File> paramList, String paramString, String... paramVarArgs)
  {
    Object localObject = paramList;
    if (paramList == null) {
      localObject = new ArrayList();
    }
    paramList = (List<File>)localObject;
    if (paramVarArgs != null)
    {
      if (paramVarArgs.length == 0) {
        paramList = (List<File>)localObject;
      }
    }
    else {
      return paramList;
    }
    paramList = (List<File>)localObject;
    for (;;)
    {
      int i;
      int j;
      try
      {
        File[] arrayOfFile = new File(paramString).listFiles();
        paramList = (List<File>)localObject;
        if (arrayOfFile == null) {
          break;
        }
        paramList = (List<File>)localObject;
        int k = arrayOfFile.length;
        i = 0;
        paramList = (List<File>)localObject;
        if (i >= k) {
          break;
        }
        File localFile = arrayOfFile[i];
        paramList = (List<File>)localObject;
        if (localFile.isDirectory())
        {
          paramList = (List<File>)localObject;
          paramString = getFileDir((List)localObject, localFile.getPath(), paramVarArgs);
        }
        else
        {
          paramList = (List<File>)localObject;
          String[] arrayOfString = localFile.getName().split("\\.");
          paramList = (List<File>)localObject;
          int m = arrayOfString.length;
          j = 0;
          paramList = (List<File>)localObject;
          paramString = (String)localObject;
          if (j < paramVarArgs.length)
          {
            paramList = (List<File>)localObject;
            if (arrayOfString[(m - 1)].equals(paramVarArgs[j]))
            {
              paramList = (List<File>)localObject;
              ((List)localObject).add(localFile);
              paramString = (String)localObject;
            }
          }
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return paramList;
      }
      j += 1;
      continue;
      i += 1;
      localObject = paramString;
    }
  }
  
  /* Error */
  public static File makeFile(String paramString1, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_0
    //   3: invokestatic 69	com/tools/file/FileDataUtils:makeRootDirectory	(Ljava/lang/String;)V
    //   6: aload_1
    //   7: ifnull +12 -> 19
    //   10: aload_1
    //   11: ldc -117
    //   13: invokevirtual 143	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   16: ifeq +12 -> 28
    //   19: new 77	java/io/File
    //   22: dup
    //   23: aload_0
    //   24: invokespecial 163	java/io/File:<init>	(Ljava/lang/String;)V
    //   27: areturn
    //   28: new 77	java/io/File
    //   31: dup
    //   32: new 41	java/lang/StringBuilder
    //   35: dup
    //   36: aload_0
    //   37: invokestatic 51	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   40: invokespecial 54	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   43: aload_1
    //   44: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: invokevirtual 63	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: invokespecial 163	java/io/File:<init>	(Ljava/lang/String;)V
    //   53: astore_0
    //   54: aload_0
    //   55: invokevirtual 81	java/io/File:exists	()Z
    //   58: ifne +24 -> 82
    //   61: aload_0
    //   62: invokevirtual 91	java/io/File:createNewFile	()Z
    //   65: pop
    //   66: aload_0
    //   67: areturn
    //   68: astore_1
    //   69: aload_2
    //   70: astore_0
    //   71: aload_1
    //   72: invokevirtual 192	java/lang/Exception:printStackTrace	()V
    //   75: goto -9 -> 66
    //   78: astore_1
    //   79: goto -8 -> 71
    //   82: goto -16 -> 66
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	85	0	paramString1	String
    //   0	85	1	paramString2	String
    //   1	69	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   28	54	68	java/lang/Exception
    //   54	66	78	java/lang/Exception
  }
  
  public static void makeRootDirectory(String paramString)
  {
    try
    {
      paramString = new File(paramString);
      return;
    }
    catch (Exception paramString)
    {
      try
      {
        if (paramString.exists()) {
          return;
        }
        paramString.mkdirs();
        return;
      }
      catch (Exception paramString)
      {
        for (;;) {}
      }
      paramString = paramString;
      Log.e("Error", paramString);
      return;
    }
  }
  
  public boolean delData(String paramString)
  {
    Iterator localIterator = this.readFileData.getListData().iterator();
    Map localMap;
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
      localMap = (Map)localIterator.next();
    } while ((!((String)localMap.get("ROV_VERSION")).equals(RovInitData.ROV_VERSION)) || (!localMap.containsKey(paramString)));
    localMap.remove(paramString);
    return true;
  }
  
  public boolean delFile(String paramString)
  {
    return this.file.delete();
  }
  
  public File getFile()
  {
    return this.file;
  }
  
  public RovReadFileData getReadFileData()
  {
    return this.readFileData;
  }
  
  public String getSDPath()
  {
    File localFile = null;
    if (Environment.getExternalStorageState().equals("mounted")) {
      localFile = Environment.getExternalStorageDirectory();
    }
    return localFile.toString();
  }
  
  public String getTagData(String paramString)
  {
    Iterator localIterator = this.readFileData.getListData().iterator();
    Map localMap;
    do
    {
      if (!localIterator.hasNext()) {
        return null;
      }
      localMap = (Map)localIterator.next();
    } while (!((String)localMap.get("ROV_VERSION")).equals(RovInitData.ROV_VERSION));
    return (String)localMap.get(paramString);
  }
  
  public Map<String, String> getTagDataAll()
  {
    Iterator localIterator = this.readFileData.getListData().iterator();
    Map localMap;
    do
    {
      if (!localIterator.hasNext()) {
        return null;
      }
      localMap = (Map)localIterator.next();
    } while (!((String)localMap.get("ROV_VERSION")).equals(RovInitData.ROV_VERSION));
    return localMap;
  }
  
  public boolean save(String paramString1, String paramString2)
  {
    Iterator localIterator = this.readFileData.getListData().iterator();
    Map localMap;
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
      localMap = (Map)localIterator.next();
    } while (!((String)localMap.get("ROV_VERSION")).equals(RovInitData.ROV_VERSION));
    localMap.put(paramString1, paramString2);
    return true;
  }
  
  public boolean updata(String paramString1, String paramString2)
  {
    Iterator localIterator = this.readFileData.getListData().iterator();
    Map localMap;
    do
    {
      if (!localIterator.hasNext()) {
        return false;
      }
      localMap = (Map)localIterator.next();
    } while (!((String)localMap.get("ROV_VERSION")).equals(RovInitData.ROV_VERSION));
    localMap.put(paramString1, paramString2);
    return true;
  }
  
  public boolean writerFile()
  {
    try
    {
      this.fileWriter = new FileWriter(this.file, false);
      this.fileWriter.write(this.Gson.toJson(this.readFileData));
      this.fileWriter.close();
      return true;
    }
    catch (Exception localException) {}
    return false;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/file/FileDataUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */