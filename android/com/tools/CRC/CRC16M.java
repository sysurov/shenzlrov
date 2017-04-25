package com.tools.CRC;

import java.io.PrintStream;

public class CRC16M
{
  static final String HEXES = "0123456789ABCDEF";
  private static byte[] auchCRCHi;
  private static byte[] auchCRCLo;
  byte uchCRCHi = -1;
  byte uchCRCLo = -1;
  public int value = 0;
  
  static
  {
    byte[] arrayOfByte = new byte['Ā'];
    arrayOfByte[1] = -63;
    arrayOfByte[2] = -127;
    arrayOfByte[3] = 64;
    arrayOfByte[4] = 1;
    arrayOfByte[5] = -64;
    arrayOfByte[6] = Byte.MIN_VALUE;
    arrayOfByte[7] = 65;
    arrayOfByte[8] = 1;
    arrayOfByte[9] = -64;
    arrayOfByte[10] = Byte.MIN_VALUE;
    arrayOfByte[11] = 65;
    arrayOfByte[13] = -63;
    arrayOfByte[14] = -127;
    arrayOfByte[15] = 64;
    arrayOfByte[16] = 1;
    arrayOfByte[17] = -64;
    arrayOfByte[18] = Byte.MIN_VALUE;
    arrayOfByte[19] = 65;
    arrayOfByte[21] = -63;
    arrayOfByte[22] = -127;
    arrayOfByte[23] = 64;
    arrayOfByte[25] = -63;
    arrayOfByte[26] = -127;
    arrayOfByte[27] = 64;
    arrayOfByte[28] = 1;
    arrayOfByte[29] = -64;
    arrayOfByte[30] = Byte.MIN_VALUE;
    arrayOfByte[31] = 65;
    arrayOfByte[32] = 1;
    arrayOfByte[33] = -64;
    arrayOfByte[34] = Byte.MIN_VALUE;
    arrayOfByte[35] = 65;
    arrayOfByte[37] = -63;
    arrayOfByte[38] = -127;
    arrayOfByte[39] = 64;
    arrayOfByte[41] = -63;
    arrayOfByte[42] = -127;
    arrayOfByte[43] = 64;
    arrayOfByte[44] = 1;
    arrayOfByte[45] = -64;
    arrayOfByte[46] = Byte.MIN_VALUE;
    arrayOfByte[47] = 65;
    arrayOfByte[49] = -63;
    arrayOfByte[50] = -127;
    arrayOfByte[51] = 64;
    arrayOfByte[52] = 1;
    arrayOfByte[53] = -64;
    arrayOfByte[54] = Byte.MIN_VALUE;
    arrayOfByte[55] = 65;
    arrayOfByte[56] = 1;
    arrayOfByte[57] = -64;
    arrayOfByte[58] = Byte.MIN_VALUE;
    arrayOfByte[59] = 65;
    arrayOfByte[61] = -63;
    arrayOfByte[62] = -127;
    arrayOfByte[63] = 64;
    arrayOfByte[64] = 1;
    arrayOfByte[65] = -64;
    arrayOfByte[66] = Byte.MIN_VALUE;
    arrayOfByte[67] = 65;
    arrayOfByte[69] = -63;
    arrayOfByte[70] = -127;
    arrayOfByte[71] = 64;
    arrayOfByte[73] = -63;
    arrayOfByte[74] = -127;
    arrayOfByte[75] = 64;
    arrayOfByte[76] = 1;
    arrayOfByte[77] = -64;
    arrayOfByte[78] = Byte.MIN_VALUE;
    arrayOfByte[79] = 65;
    arrayOfByte[81] = -63;
    arrayOfByte[82] = -127;
    arrayOfByte[83] = 64;
    arrayOfByte[84] = 1;
    arrayOfByte[85] = -64;
    arrayOfByte[86] = Byte.MIN_VALUE;
    arrayOfByte[87] = 65;
    arrayOfByte[88] = 1;
    arrayOfByte[89] = -64;
    arrayOfByte[90] = Byte.MIN_VALUE;
    arrayOfByte[91] = 65;
    arrayOfByte[93] = -63;
    arrayOfByte[94] = -127;
    arrayOfByte[95] = 64;
    arrayOfByte[97] = -63;
    arrayOfByte[98] = -127;
    arrayOfByte[99] = 64;
    arrayOfByte[100] = 1;
    arrayOfByte[101] = -64;
    arrayOfByte[102] = Byte.MIN_VALUE;
    arrayOfByte[103] = 65;
    arrayOfByte[104] = 1;
    arrayOfByte[105] = -64;
    arrayOfByte[106] = Byte.MIN_VALUE;
    arrayOfByte[107] = 65;
    arrayOfByte[109] = -63;
    arrayOfByte[110] = -127;
    arrayOfByte[111] = 64;
    arrayOfByte[112] = 1;
    arrayOfByte[113] = -64;
    arrayOfByte[114] = Byte.MIN_VALUE;
    arrayOfByte[115] = 65;
    arrayOfByte[117] = -63;
    arrayOfByte[118] = -127;
    arrayOfByte[119] = 64;
    arrayOfByte[121] = -63;
    arrayOfByte[122] = -127;
    arrayOfByte[123] = 64;
    arrayOfByte[124] = 1;
    arrayOfByte[125] = -64;
    arrayOfByte[126] = Byte.MIN_VALUE;
    arrayOfByte[127] = 65;
    arrayOfByte[''] = 1;
    arrayOfByte[''] = -64;
    arrayOfByte[''] = Byte.MIN_VALUE;
    arrayOfByte[''] = 65;
    arrayOfByte[''] = -63;
    arrayOfByte[''] = -127;
    arrayOfByte[''] = 64;
    arrayOfByte[''] = -63;
    arrayOfByte[''] = -127;
    arrayOfByte[''] = 64;
    arrayOfByte[''] = 1;
    arrayOfByte[''] = -64;
    arrayOfByte[''] = Byte.MIN_VALUE;
    arrayOfByte[''] = 65;
    arrayOfByte[''] = -63;
    arrayOfByte[''] = -127;
    arrayOfByte[''] = 64;
    arrayOfByte[''] = 1;
    arrayOfByte[''] = -64;
    arrayOfByte[''] = Byte.MIN_VALUE;
    arrayOfByte[''] = 65;
    arrayOfByte[''] = 1;
    arrayOfByte[''] = -64;
    arrayOfByte[''] = Byte.MIN_VALUE;
    arrayOfByte[''] = 65;
    arrayOfByte[''] = -63;
    arrayOfByte[''] = -127;
    arrayOfByte[''] = 64;
    arrayOfByte['¡'] = -63;
    arrayOfByte['¢'] = -127;
    arrayOfByte['£'] = 64;
    arrayOfByte['¤'] = 1;
    arrayOfByte['¥'] = -64;
    arrayOfByte['¦'] = Byte.MIN_VALUE;
    arrayOfByte['§'] = 65;
    arrayOfByte['¨'] = 1;
    arrayOfByte['©'] = -64;
    arrayOfByte['ª'] = Byte.MIN_VALUE;
    arrayOfByte['«'] = 65;
    arrayOfByte['­'] = -63;
    arrayOfByte['®'] = -127;
    arrayOfByte['¯'] = 64;
    arrayOfByte['°'] = 1;
    arrayOfByte['±'] = -64;
    arrayOfByte['²'] = Byte.MIN_VALUE;
    arrayOfByte['³'] = 65;
    arrayOfByte['µ'] = -63;
    arrayOfByte['¶'] = -127;
    arrayOfByte['·'] = 64;
    arrayOfByte['¹'] = -63;
    arrayOfByte['º'] = -127;
    arrayOfByte['»'] = 64;
    arrayOfByte['¼'] = 1;
    arrayOfByte['½'] = -64;
    arrayOfByte['¾'] = Byte.MIN_VALUE;
    arrayOfByte['¿'] = 65;
    arrayOfByte['Á'] = -63;
    arrayOfByte['Â'] = -127;
    arrayOfByte['Ã'] = 64;
    arrayOfByte['Ä'] = 1;
    arrayOfByte['Å'] = -64;
    arrayOfByte['Æ'] = Byte.MIN_VALUE;
    arrayOfByte['Ç'] = 65;
    arrayOfByte['È'] = 1;
    arrayOfByte['É'] = -64;
    arrayOfByte['Ê'] = Byte.MIN_VALUE;
    arrayOfByte['Ë'] = 65;
    arrayOfByte['Í'] = -63;
    arrayOfByte['Î'] = -127;
    arrayOfByte['Ï'] = 64;
    arrayOfByte['Ð'] = 1;
    arrayOfByte['Ñ'] = -64;
    arrayOfByte['Ò'] = Byte.MIN_VALUE;
    arrayOfByte['Ó'] = 65;
    arrayOfByte['Õ'] = -63;
    arrayOfByte['Ö'] = -127;
    arrayOfByte['×'] = 64;
    arrayOfByte['Ù'] = -63;
    arrayOfByte['Ú'] = -127;
    arrayOfByte['Û'] = 64;
    arrayOfByte['Ü'] = 1;
    arrayOfByte['Ý'] = -64;
    arrayOfByte['Þ'] = Byte.MIN_VALUE;
    arrayOfByte['ß'] = 65;
    arrayOfByte['à'] = 1;
    arrayOfByte['á'] = -64;
    arrayOfByte['â'] = Byte.MIN_VALUE;
    arrayOfByte['ã'] = 65;
    arrayOfByte['å'] = -63;
    arrayOfByte['æ'] = -127;
    arrayOfByte['ç'] = 64;
    arrayOfByte['é'] = -63;
    arrayOfByte['ê'] = -127;
    arrayOfByte['ë'] = 64;
    arrayOfByte['ì'] = 1;
    arrayOfByte['í'] = -64;
    arrayOfByte['î'] = Byte.MIN_VALUE;
    arrayOfByte['ï'] = 65;
    arrayOfByte['ñ'] = -63;
    arrayOfByte['ò'] = -127;
    arrayOfByte['ó'] = 64;
    arrayOfByte['ô'] = 1;
    arrayOfByte['õ'] = -64;
    arrayOfByte['ö'] = Byte.MIN_VALUE;
    arrayOfByte['÷'] = 65;
    arrayOfByte['ø'] = 1;
    arrayOfByte['ù'] = -64;
    arrayOfByte['ú'] = Byte.MIN_VALUE;
    arrayOfByte['û'] = 65;
    arrayOfByte['ý'] = -63;
    arrayOfByte['þ'] = -127;
    arrayOfByte['ÿ'] = 64;
    auchCRCHi = arrayOfByte;
    arrayOfByte = new byte['Ā'];
    arrayOfByte[1] = -64;
    arrayOfByte[2] = -63;
    arrayOfByte[3] = 1;
    arrayOfByte[4] = -61;
    arrayOfByte[5] = 3;
    arrayOfByte[6] = 2;
    arrayOfByte[7] = -62;
    arrayOfByte[8] = -58;
    arrayOfByte[9] = 6;
    arrayOfByte[10] = 7;
    arrayOfByte[11] = -57;
    arrayOfByte[12] = 5;
    arrayOfByte[13] = -59;
    arrayOfByte[14] = -60;
    arrayOfByte[15] = 4;
    arrayOfByte[16] = -52;
    arrayOfByte[17] = 12;
    arrayOfByte[18] = 13;
    arrayOfByte[19] = -51;
    arrayOfByte[20] = 15;
    arrayOfByte[21] = -49;
    arrayOfByte[22] = -50;
    arrayOfByte[23] = 14;
    arrayOfByte[24] = 10;
    arrayOfByte[25] = -54;
    arrayOfByte[26] = -53;
    arrayOfByte[27] = 11;
    arrayOfByte[28] = -55;
    arrayOfByte[29] = 9;
    arrayOfByte[30] = 8;
    arrayOfByte[31] = -56;
    arrayOfByte[32] = -40;
    arrayOfByte[33] = 24;
    arrayOfByte[34] = 25;
    arrayOfByte[35] = -39;
    arrayOfByte[36] = 27;
    arrayOfByte[37] = -37;
    arrayOfByte[38] = -38;
    arrayOfByte[39] = 26;
    arrayOfByte[40] = 30;
    arrayOfByte[41] = -34;
    arrayOfByte[42] = -33;
    arrayOfByte[43] = 31;
    arrayOfByte[44] = -35;
    arrayOfByte[45] = 29;
    arrayOfByte[46] = 28;
    arrayOfByte[47] = -36;
    arrayOfByte[48] = 20;
    arrayOfByte[49] = -44;
    arrayOfByte[50] = -43;
    arrayOfByte[51] = 21;
    arrayOfByte[52] = -41;
    arrayOfByte[53] = 23;
    arrayOfByte[54] = 22;
    arrayOfByte[55] = -42;
    arrayOfByte[56] = -46;
    arrayOfByte[57] = 18;
    arrayOfByte[58] = 19;
    arrayOfByte[59] = -45;
    arrayOfByte[60] = 17;
    arrayOfByte[61] = -47;
    arrayOfByte[62] = -48;
    arrayOfByte[63] = 16;
    arrayOfByte[64] = -16;
    arrayOfByte[65] = 48;
    arrayOfByte[66] = 49;
    arrayOfByte[67] = -15;
    arrayOfByte[68] = 51;
    arrayOfByte[69] = -13;
    arrayOfByte[70] = -14;
    arrayOfByte[71] = 50;
    arrayOfByte[72] = 54;
    arrayOfByte[73] = -10;
    arrayOfByte[74] = -9;
    arrayOfByte[75] = 55;
    arrayOfByte[76] = -11;
    arrayOfByte[77] = 53;
    arrayOfByte[78] = 52;
    arrayOfByte[79] = -12;
    arrayOfByte[80] = 60;
    arrayOfByte[81] = -4;
    arrayOfByte[82] = -3;
    arrayOfByte[83] = 61;
    arrayOfByte[84] = -1;
    arrayOfByte[85] = 63;
    arrayOfByte[86] = 62;
    arrayOfByte[87] = -2;
    arrayOfByte[88] = -6;
    arrayOfByte[89] = 58;
    arrayOfByte[90] = 59;
    arrayOfByte[91] = -5;
    arrayOfByte[92] = 57;
    arrayOfByte[93] = -7;
    arrayOfByte[94] = -8;
    arrayOfByte[95] = 56;
    arrayOfByte[96] = 40;
    arrayOfByte[97] = -24;
    arrayOfByte[98] = -23;
    arrayOfByte[99] = 41;
    arrayOfByte[100] = -21;
    arrayOfByte[101] = 43;
    arrayOfByte[102] = 42;
    arrayOfByte[103] = -22;
    arrayOfByte[104] = -18;
    arrayOfByte[105] = 46;
    arrayOfByte[106] = 47;
    arrayOfByte[107] = -17;
    arrayOfByte[108] = 45;
    arrayOfByte[109] = -19;
    arrayOfByte[110] = -20;
    arrayOfByte[111] = 44;
    arrayOfByte[112] = -28;
    arrayOfByte[113] = 36;
    arrayOfByte[114] = 37;
    arrayOfByte[115] = -27;
    arrayOfByte[116] = 39;
    arrayOfByte[117] = -25;
    arrayOfByte[118] = -26;
    arrayOfByte[119] = 38;
    arrayOfByte[120] = 34;
    arrayOfByte[121] = -30;
    arrayOfByte[122] = -29;
    arrayOfByte[123] = 35;
    arrayOfByte[124] = -31;
    arrayOfByte[125] = 33;
    arrayOfByte[126] = 32;
    arrayOfByte[127] = -32;
    arrayOfByte[''] = -96;
    arrayOfByte[''] = 96;
    arrayOfByte[''] = 97;
    arrayOfByte[''] = -95;
    arrayOfByte[''] = 99;
    arrayOfByte[''] = -93;
    arrayOfByte[''] = -94;
    arrayOfByte[''] = 98;
    arrayOfByte[''] = 102;
    arrayOfByte[''] = -90;
    arrayOfByte[''] = -89;
    arrayOfByte[''] = 103;
    arrayOfByte[''] = -91;
    arrayOfByte[''] = 101;
    arrayOfByte[''] = 100;
    arrayOfByte[''] = -92;
    arrayOfByte[''] = 108;
    arrayOfByte[''] = -84;
    arrayOfByte[''] = -83;
    arrayOfByte[''] = 109;
    arrayOfByte[''] = -81;
    arrayOfByte[''] = 111;
    arrayOfByte[''] = 110;
    arrayOfByte[''] = -82;
    arrayOfByte[''] = -86;
    arrayOfByte[''] = 106;
    arrayOfByte[''] = 107;
    arrayOfByte[''] = -85;
    arrayOfByte[''] = 105;
    arrayOfByte[''] = -87;
    arrayOfByte[''] = -88;
    arrayOfByte[''] = 104;
    arrayOfByte[' '] = 120;
    arrayOfByte['¡'] = -72;
    arrayOfByte['¢'] = -71;
    arrayOfByte['£'] = 121;
    arrayOfByte['¤'] = -69;
    arrayOfByte['¥'] = 123;
    arrayOfByte['¦'] = 122;
    arrayOfByte['§'] = -70;
    arrayOfByte['¨'] = -66;
    arrayOfByte['©'] = 126;
    arrayOfByte['ª'] = Byte.MAX_VALUE;
    arrayOfByte['«'] = -65;
    arrayOfByte['¬'] = 125;
    arrayOfByte['­'] = -67;
    arrayOfByte['®'] = -68;
    arrayOfByte['¯'] = 124;
    arrayOfByte['°'] = -76;
    arrayOfByte['±'] = 116;
    arrayOfByte['²'] = 117;
    arrayOfByte['³'] = -75;
    arrayOfByte['´'] = 119;
    arrayOfByte['µ'] = -73;
    arrayOfByte['¶'] = -74;
    arrayOfByte['·'] = 118;
    arrayOfByte['¸'] = 114;
    arrayOfByte['¹'] = -78;
    arrayOfByte['º'] = -77;
    arrayOfByte['»'] = 115;
    arrayOfByte['¼'] = -79;
    arrayOfByte['½'] = 113;
    arrayOfByte['¾'] = 112;
    arrayOfByte['¿'] = -80;
    arrayOfByte['À'] = 80;
    arrayOfByte['Á'] = -112;
    arrayOfByte['Â'] = -111;
    arrayOfByte['Ã'] = 81;
    arrayOfByte['Ä'] = -109;
    arrayOfByte['Å'] = 83;
    arrayOfByte['Æ'] = 82;
    arrayOfByte['Ç'] = -110;
    arrayOfByte['È'] = -106;
    arrayOfByte['É'] = 86;
    arrayOfByte['Ê'] = 87;
    arrayOfByte['Ë'] = -105;
    arrayOfByte['Ì'] = 85;
    arrayOfByte['Í'] = -107;
    arrayOfByte['Î'] = -108;
    arrayOfByte['Ï'] = 84;
    arrayOfByte['Ð'] = -100;
    arrayOfByte['Ñ'] = 92;
    arrayOfByte['Ò'] = 93;
    arrayOfByte['Ó'] = -99;
    arrayOfByte['Ô'] = 95;
    arrayOfByte['Õ'] = -97;
    arrayOfByte['Ö'] = -98;
    arrayOfByte['×'] = 94;
    arrayOfByte['Ø'] = 90;
    arrayOfByte['Ù'] = -102;
    arrayOfByte['Ú'] = -101;
    arrayOfByte['Û'] = 91;
    arrayOfByte['Ü'] = -103;
    arrayOfByte['Ý'] = 89;
    arrayOfByte['Þ'] = 88;
    arrayOfByte['ß'] = -104;
    arrayOfByte['à'] = -120;
    arrayOfByte['á'] = 72;
    arrayOfByte['â'] = 73;
    arrayOfByte['ã'] = -119;
    arrayOfByte['ä'] = 75;
    arrayOfByte['å'] = -117;
    arrayOfByte['æ'] = -118;
    arrayOfByte['ç'] = 74;
    arrayOfByte['è'] = 78;
    arrayOfByte['é'] = -114;
    arrayOfByte['ê'] = -113;
    arrayOfByte['ë'] = 79;
    arrayOfByte['ì'] = -115;
    arrayOfByte['í'] = 77;
    arrayOfByte['î'] = 76;
    arrayOfByte['ï'] = -116;
    arrayOfByte['ð'] = 68;
    arrayOfByte['ñ'] = -124;
    arrayOfByte['ò'] = -123;
    arrayOfByte['ó'] = 69;
    arrayOfByte['ô'] = -121;
    arrayOfByte['õ'] = 71;
    arrayOfByte['ö'] = 70;
    arrayOfByte['÷'] = -122;
    arrayOfByte['ø'] = -126;
    arrayOfByte['ù'] = 66;
    arrayOfByte['ú'] = 67;
    arrayOfByte['û'] = -125;
    arrayOfByte['ü'] = 65;
    arrayOfByte['ý'] = -127;
    arrayOfByte['þ'] = Byte.MIN_VALUE;
    arrayOfByte['ÿ'] = 64;
    auchCRCLo = arrayOfByte;
  }
  
  private static byte[] HexString2Buf(String paramString)
  {
    int j = paramString.length();
    byte[] arrayOfByte = new byte[j / 2 + 2];
    paramString = paramString.getBytes();
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return arrayOfByte;
      }
      arrayOfByte[(i / 2)] = uniteBytes(paramString[i], paramString[(i + 1)]);
      i += 2;
    }
  }
  
  public static boolean checkBuf(byte[] paramArrayOfByte)
  {
    CRC16M localCRC16M = new CRC16M();
    localCRC16M.update(paramArrayOfByte, paramArrayOfByte.length - 2);
    int i = localCRC16M.getValue();
    return (paramArrayOfByte[(paramArrayOfByte.length - 1)] == (byte)(i & 0xFF)) && (paramArrayOfByte[(paramArrayOfByte.length - 2)] == (byte)((0xFF00 & i) >> 8));
  }
  
  public static String getBufHexStr(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return null;
    }
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
    int j = paramArrayOfByte.length;
    int i = 0;
    for (;;)
    {
      if (i >= j) {
        return localStringBuilder.toString();
      }
      int k = paramArrayOfByte[i];
      localStringBuilder.append("0123456789ABCDEF".charAt((k & 0xF0) >> 4)).append("0123456789ABCDEF".charAt(k & 0xF));
      i += 1;
    }
  }
  
  public static byte[] getSendBuf(String paramString)
  {
    paramString = HexString2Buf(paramString);
    CRC16M localCRC16M = new CRC16M();
    localCRC16M.update(paramString, paramString.length - 2);
    int i = localCRC16M.getValue();
    paramString[(paramString.length - 1)] = ((byte)(i & 0xFF));
    paramString[(paramString.length - 2)] = ((byte)((0xFF00 & i) >> 8));
    return new byte[] { paramString[(paramString.length - 1)], paramString[(paramString.length - 2)] };
  }
  
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = getSendBuf("010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101010101");
    System.out.println(getBufHexStr(paramArrayOfString));
  }
  
  private static byte uniteBytes(byte paramByte1, byte paramByte2)
  {
    return (byte)((byte)(Byte.decode("0x" + new String(new byte[] { paramByte1 })).byteValue() << 4) ^ Byte.decode("0x" + new String(new byte[] { paramByte2 })).byteValue());
  }
  
  public int getValue()
  {
    return this.value;
  }
  
  public void reset()
  {
    this.value = 0;
    this.uchCRCHi = -1;
    this.uchCRCLo = -1;
  }
  
  public void update(byte[] paramArrayOfByte, int paramInt)
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramInt)
      {
        this.value = ((this.uchCRCHi << 8 | this.uchCRCLo & 0xFF) & 0xFFFF);
        return;
      }
      int j = (this.uchCRCHi ^ paramArrayOfByte[i]) & 0xFF;
      this.uchCRCHi = ((byte)(this.uchCRCLo ^ auchCRCHi[j]));
      this.uchCRCLo = auchCRCLo[j];
      i += 1;
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/CRC/CRC16M.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */