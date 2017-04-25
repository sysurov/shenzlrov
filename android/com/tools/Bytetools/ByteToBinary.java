package com.tools.Bytetools;

public class ByteToBinary
{
  public static String getBinaryStrFromByte(byte paramByte)
  {
    String str = "";
    int i = paramByte;
    paramByte = 0;
    if (paramByte >= 8) {
      return str;
    }
    int j = (byte)((byte)(i >> 1) << 1);
    if (j == i) {}
    for (str = "0" + str;; str = "1" + str)
    {
      i = (byte)(j >> 1);
      paramByte += 1;
      break;
    }
  }
  
  public static String getBinaryStrFromByte2(byte paramByte)
  {
    String str = "";
    int i = paramByte;
    paramByte = 0;
    for (;;)
    {
      if (paramByte >= 8) {
        return str;
      }
      str = i % 2 + str;
      i = (byte)(i >> 1);
      paramByte += 1;
    }
  }
  
  public static String getBinaryStrFromByte3(byte paramByte)
  {
    String str = "";
    int i = paramByte;
    paramByte = 0;
    for (;;)
    {
      if (paramByte >= 8) {
        return str;
      }
      str = i % 2 + str;
      i = (byte)(i / 2);
      paramByte += 1;
    }
  }
  
  public static String getBinaryStrFromByteArr(byte[] paramArrayOfByte)
  {
    String str = "";
    int j = paramArrayOfByte.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return str;
      }
      byte b = paramArrayOfByte[i];
      str = str + getBinaryStrFromByte(b);
      i += 1;
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/Bytetools/ByteToBinary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */