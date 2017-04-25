package com.deepinfar.FileData;

import com.deepinfar.ProtocolData.RovInitData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RovReadFileData
{
  private List<Map<String, String>> listData = new ArrayList();
  
  public RovReadFileData()
  {
    updataFileVersion(this.listData);
  }
  
  public static void updataFileVersion(List<Map<String, String>> paramList)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("ROV_VERSION", RovInitData.ROV_VERSION);
    localHashMap.put("RovIP地址", RovInitData.ROV_IP);
    localHashMap.put("Rov端口", RovInitData.ROV_PORT);
    localHashMap.put("视频地址", RovInitData.VIDEO_SITE);
    localHashMap.put("界面透明度", RovInitData.UI_ALPHA);
    localHashMap.put("窗口透明度", RovInitData.DIALOG_ALPHA);
    localHashMap.put("上", "19");
    localHashMap.put("下", "20");
    localHashMap.put("左", "21");
    localHashMap.put("右", "22");
    localHashMap.put("左摇杆", "106");
    localHashMap.put("右摇杆", "107");
    localHashMap.put("Y", "100");
    localHashMap.put("A", "96");
    localHashMap.put("X", "99");
    localHashMap.put("B", "97");
    localHashMap.put("L1", "102");
    localHashMap.put("L2", "104");
    localHashMap.put("R1", "103");
    localHashMap.put("R2", "105");
    paramList.add(localHashMap);
  }
  
  public List<Map<String, String>> compareVersion()
  {
    Iterator localIterator = this.listData.iterator();
    do
    {
      if (!localIterator.hasNext())
      {
        updataFileVersion(this.listData);
        return this.listData;
      }
    } while (!((String)((Map)localIterator.next()).get("ROV_VERSION")).equals(RovInitData.ROV_VERSION));
    return this.listData;
  }
  
  public List<Map<String, String>> getListData()
  {
    return this.listData;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/FileData/RovReadFileData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */