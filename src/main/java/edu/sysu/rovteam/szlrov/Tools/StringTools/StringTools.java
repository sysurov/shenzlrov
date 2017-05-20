package edu.sysu.rovteam.szlrov.Tools.StringTools;

/**
 * Created by sunny on 17-5-20.
 */
public class StringTools {
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
        byte[] outputByte;
        if ((paramString == null) || (paramString.equals("")))
        {
            outputByte = null;
            return outputByte;
        }
        paramString = paramString.toUpperCase();
        int j = paramString.length() / 2;
        char[] arrayOfChar = paramString.toCharArray();
        byte[] arrayOfByte = new byte[j];
        int i = 0;
        for (;;)
        {
            outputByte = arrayOfByte;
            if (i >= j) {
                break;
            }
            int k = i * 2;
            arrayOfByte[i] = ((byte)(charToByte(arrayOfChar[k]) << 4 | charToByte(arrayOfChar[(k + 1)])));
            i += 1;
        }
        return outputByte;
    }
}
