package edu.sysu.rovteam.szlrov.Tools.Bytetools;

/**
 * Created by sunny on 17-5-20.
 */
public class ByteToBinary {
    public static String getBinaryStrFromByte(byte b)
    {
        String result = "";
        byte a = b;
        ;
        for (int i = 0; i < 8; i++) {
            byte c = a;
            a = (byte) (a >> 1);
            a = (byte) (a << 1);
            if (a == c) {
                result = "0" + result;
            } else {
                result = "1" + result;
            }
            a = (byte) (a >> 1);
        }
        return result;
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
