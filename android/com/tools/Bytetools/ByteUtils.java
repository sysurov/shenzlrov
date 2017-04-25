package com.tools.Bytetools;

public class ByteUtils
{
  public static int ToByteIsSignedInt(byte paramByte)
  {
    return paramByte;
  }
  
  public static int ToByteIsUInt(byte paramByte)
  {
    return paramByte & 0xFF;
  }
  
  public static int ToBytesIsInt(byte... paramVarArgs)
  {
    if (paramVarArgs.length == 1) {
      return ToByteIsSignedInt(paramVarArgs[0]);
    }
    if (paramVarArgs.length > 2) {
      return 0;
    }
    return 0 + (ToByteIsSignedInt(paramVarArgs[1]) << 8) + ToByteIsUInt(paramVarArgs[0]);
  }
  
  public static long ToBytesIsSignedLong(byte... paramVarArgs)
  {
    if (paramVarArgs.length == 1) {
      return paramVarArgs[0];
    }
    byte[] arrayOfByte = new byte[paramVarArgs.length - 1];
    int i = arrayOfByte.length - 1;
    for (;;)
    {
      if (i < 0)
      {
        if (paramVarArgs[(paramVarArgs.length - 1)] >= 0) {
          break;
        }
        i = ToByteIsUInt(paramVarArgs[(paramVarArgs.length - 1)]);
        l = ToBytesIsSignedLong(arrayOfByte);
        return i + (l << 8);
      }
      arrayOfByte[i] = paramVarArgs[i];
      i -= 1;
    }
    long l = ToBytesIsSignedLong(arrayOfByte);
    return paramVarArgs[(paramVarArgs.length - 1)] + (l << 8);
  }
  
  public static long ToBytesIsULong(byte... paramVarArgs)
  {
    if (paramVarArgs.length == 1)
    {
      if (paramVarArgs[0] < 0) {
        return ToByteIsUInt(paramVarArgs[0]);
      }
      return paramVarArgs[0];
    }
    byte[] arrayOfByte = new byte[paramVarArgs.length - 1];
    int i = arrayOfByte.length - 1;
    for (;;)
    {
      if (i < 0)
      {
        if (paramVarArgs[(paramVarArgs.length - 1)] >= 0) {
          break;
        }
        i = ToByteIsUInt(paramVarArgs[(paramVarArgs.length - 1)]);
        l = ToBytesIsULong(arrayOfByte);
        return i + (l << 8);
      }
      arrayOfByte[i] = paramVarArgs[i];
      i -= 1;
    }
    long l = ToBytesIsULong(arrayOfByte);
    return paramVarArgs[(paramVarArgs.length - 1)] + (l << 8);
  }
  
  public static byte ToIntIsByte(int paramInt)
    throws NumberFormatException
  {
    if (paramInt >> 8 > 0) {
      throw new NumberFormatException();
    }
    return (byte)(paramInt & 0xFF);
  }
  
  public static byte[] ToIntIsBytes(int paramInt)
    throws NumberFormatException
  {
    byte[] arrayOfByte = new byte[2];
    if ((paramInt >= -128) && (paramInt < 0))
    {
      arrayOfByte[0] = -1;
      arrayOfByte[1] = ((byte)(paramInt & 0xFF));
      return arrayOfByte;
    }
    if ((paramInt > 0) && (paramInt <= 127))
    {
      arrayOfByte[0] = 0;
      arrayOfByte[1] = ((byte)(paramInt & 0xFF));
      return arrayOfByte;
    }
    if ((paramInt > 127) && (paramInt < 256))
    {
      arrayOfByte[0] = 0;
      arrayOfByte[1] = ((byte)(paramInt & 0xFF));
      return arrayOfByte;
    }
    if ((paramInt < -128) && (paramInt > 65280))
    {
      arrayOfByte[0] = -1;
      arrayOfByte[1] = ((byte)paramInt);
      return arrayOfByte;
    }
    arrayOfByte[0] = ((byte)(paramInt >> 8));
    arrayOfByte[1] = ((byte)(paramInt & 0xFF));
    return arrayOfByte;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/Bytetools/ByteUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */