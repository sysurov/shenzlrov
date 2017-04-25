package com.tools.StringTools;

public class StringTools
{
  public static String bytesToHexString(byte... paramVarArgs)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramVarArgs == null) || (paramVarArgs.length <= 0)) {
      return null;
    }
    int i = 0;
    for (;;)
    {
      if (i >= paramVarArgs.length) {
        return localStringBuilder.toString();
      }
      String str = Integer.toHexString(paramVarArgs[i] & 0xFF);
      if (str.length() < 2) {
        localStringBuilder.append(0);
      }
      localStringBuilder.append(str);
      i += 1;
    }
  }
  
  private static byte charToByte(char paramChar)
  {
    return (byte)"0123456789ABCDEF".indexOf(paramChar);
  }
  
  public static byte[] hexStringToBytes(String paramString)
  {
    if ((paramString == null) || (paramString.equals("")))
    {
      paramString = null;
      return paramString;
    }
    paramString = paramString.toUpperCase();
    int j = paramString.length() / 2;
    char[] arrayOfChar = paramString.toCharArray();
    byte[] arrayOfByte = new byte[j];
    int i = 0;
    for (;;)
    {
      paramString = arrayOfByte;
      if (i >= j) {
        break;
      }
      int k = i * 2;
      arrayOfByte[i] = ((byte)(charToByte(arrayOfChar[k]) << 4 | charToByte(arrayOfChar[(k + 1)])));
      i += 1;
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/StringTools/StringTools.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */