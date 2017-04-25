package com.tools.ip;

public class IPTools
  implements IPUtils
{
  public String isIpLegal(String paramString)
  {
    if ((paramString == null) || (paramString.equals(""))) {
      return "IP地址输入错误";
    }
    paramString = paramString.split("\\.");
    int i;
    if (paramString.length == 4) {
      i = 0;
    }
    for (;;)
    {
      if (i >= paramString.length) {
        return null;
      }
      try
      {
        int j = Integer.parseInt(paramString[i]);
        if ((j < 0) || (j > 255)) {
          return "IP地址输入错误";
        }
      }
      catch (NumberFormatException paramString)
      {
        return "IP地址输入错误";
      }
      i += 1;
    }
  }
  
  public String isPortLegal(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      if ((i < 0) || (i > 65536)) {
        return "端口地址输入错误";
      }
    }
    catch (NumberFormatException paramString)
    {
      return "端口地址输入错误";
    }
    return null;
  }
  
  public String isUriLegal(String paramString)
  {
    paramString = paramString.split("//");
    if (paramString.length != 2) {
      paramString = "地址不合法。。";
    }
    String str;
    do
    {
      return paramString;
      if ((!paramString[0].equals("rtsp:")) && (!paramString[0].equals("https:")) && (!paramString[0].equals("http:"))) {
        break;
      }
      str = isIpLegal(paramString[1].split("/")[0]);
      paramString = str;
    } while (str != null);
    return null;
    return "地址不合法。。";
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/ip/IPTools.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */