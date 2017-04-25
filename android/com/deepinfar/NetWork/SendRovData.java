package com.deepinfar.NetWork;

import android.content.Context;
import android.content.res.Resources;
import com.tools.Bytetools.ByteUtils;
import com.tools.CRC.CRC16M;
import com.tools.StringTools.StringTools;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class SendRovData
{
  private static Map<String, SendRovData> sendRovDatas;
  private int AXIS_RZ = 0;
  private int AXIS_X = 0;
  private int AXIS_Y = 0;
  private int AXIS_Z = 0;
  private int Send_No = 0;
  private String[] menu;
  private NetWork netWork;
  private Map<String, Byte> netWorkByte;
  public Resources res;
  private RovData rovData;
  public byte[] sendBytes;
  private Timer timer = new Timer();
  TimerTask timerTask = new TimerTask()
  {
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: invokestatic 23	com/deepinfar/Task/FunctionAdapter:getFunctionAdapter	()Lcom/deepinfar/Task/FunctionAdapter;
      //   5: invokevirtual 27	com/deepinfar/Task/FunctionAdapter:getRockerTask	()Lcom/deepinfar/Task/FunctionAdapter$OnDeployTask;
      //   8: astore_3
      //   9: aload_3
      //   10: ifnonnull +6 -> 16
      //   13: aload_0
      //   14: monitorexit
      //   15: return
      //   16: aload_0
      //   17: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   20: invokestatic 31	com/deepinfar/NetWork/SendRovData:access$0	(Lcom/deepinfar/NetWork/SendRovData;)Lcom/deepinfar/NetWork/SendRovData$RovData;
      //   23: ldc 33
      //   25: invokevirtual 39	com/deepinfar/NetWork/SendRovData$RovData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   28: invokevirtual 45	java/lang/Integer:intValue	()I
      //   31: istore_2
      //   32: aload_3
      //   33: invokeinterface 50 1 0
      //   38: aload_0
      //   39: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   42: ldc 52
      //   44: invokevirtual 56	com/deepinfar/NetWork/SendRovData:getVideoData	(Ljava/lang/String;)Lcom/deepinfar/NetWork/SendRovData$VideoData;
      //   47: astore_3
      //   48: aload_0
      //   49: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   52: invokestatic 31	com/deepinfar/NetWork/SendRovData:access$0	(Lcom/deepinfar/NetWork/SendRovData;)Lcom/deepinfar/NetWork/SendRovData$RovData;
      //   55: ldc 58
      //   57: invokevirtual 39	com/deepinfar/NetWork/SendRovData$RovData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   60: invokevirtual 45	java/lang/Integer:intValue	()I
      //   63: invokestatic 64	com/tools/Bytetools/ByteUtils:ToIntIsBytes	(I)[B
      //   66: astore 4
      //   68: aload_0
      //   69: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   72: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   75: ldc 70
      //   77: aload 4
      //   79: iconst_1
      //   80: baload
      //   81: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   84: invokeinterface 82 3 0
      //   89: pop
      //   90: aload_0
      //   91: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   94: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   97: ldc 84
      //   99: aload 4
      //   101: iconst_0
      //   102: baload
      //   103: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   106: invokeinterface 82 3 0
      //   111: pop
      //   112: aload_0
      //   113: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   116: invokestatic 31	com/deepinfar/NetWork/SendRovData:access$0	(Lcom/deepinfar/NetWork/SendRovData;)Lcom/deepinfar/NetWork/SendRovData$RovData;
      //   119: ldc 86
      //   121: invokevirtual 39	com/deepinfar/NetWork/SendRovData$RovData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   124: invokevirtual 45	java/lang/Integer:intValue	()I
      //   127: invokestatic 64	com/tools/Bytetools/ByteUtils:ToIntIsBytes	(I)[B
      //   130: astore 4
      //   132: aload_0
      //   133: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   136: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   139: ldc 88
      //   141: aload 4
      //   143: iconst_1
      //   144: baload
      //   145: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   148: invokeinterface 82 3 0
      //   153: pop
      //   154: aload_0
      //   155: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   158: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   161: ldc 90
      //   163: aload 4
      //   165: iconst_0
      //   166: baload
      //   167: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   170: invokeinterface 82 3 0
      //   175: pop
      //   176: aload_0
      //   177: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   180: invokestatic 31	com/deepinfar/NetWork/SendRovData:access$0	(Lcom/deepinfar/NetWork/SendRovData;)Lcom/deepinfar/NetWork/SendRovData$RovData;
      //   183: ldc 92
      //   185: invokevirtual 39	com/deepinfar/NetWork/SendRovData$RovData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   188: invokevirtual 45	java/lang/Integer:intValue	()I
      //   191: invokestatic 64	com/tools/Bytetools/ByteUtils:ToIntIsBytes	(I)[B
      //   194: astore 4
      //   196: aload_0
      //   197: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   200: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   203: ldc 94
      //   205: aload 4
      //   207: iconst_1
      //   208: baload
      //   209: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   212: invokeinterface 82 3 0
      //   217: pop
      //   218: aload_0
      //   219: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   222: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   225: ldc 96
      //   227: aload 4
      //   229: iconst_0
      //   230: baload
      //   231: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   234: invokeinterface 82 3 0
      //   239: pop
      //   240: aload_0
      //   241: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   244: invokestatic 31	com/deepinfar/NetWork/SendRovData:access$0	(Lcom/deepinfar/NetWork/SendRovData;)Lcom/deepinfar/NetWork/SendRovData$RovData;
      //   247: ldc 98
      //   249: invokevirtual 39	com/deepinfar/NetWork/SendRovData$RovData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   252: invokevirtual 45	java/lang/Integer:intValue	()I
      //   255: invokestatic 64	com/tools/Bytetools/ByteUtils:ToIntIsBytes	(I)[B
      //   258: astore 4
      //   260: aload_0
      //   261: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   264: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   267: ldc 100
      //   269: aload 4
      //   271: iconst_1
      //   272: baload
      //   273: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   276: invokeinterface 82 3 0
      //   281: pop
      //   282: aload_0
      //   283: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   286: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   289: ldc 102
      //   291: aload 4
      //   293: iconst_0
      //   294: baload
      //   295: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   298: invokeinterface 82 3 0
      //   303: pop
      //   304: aload_0
      //   305: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   308: invokestatic 31	com/deepinfar/NetWork/SendRovData:access$0	(Lcom/deepinfar/NetWork/SendRovData;)Lcom/deepinfar/NetWork/SendRovData$RovData;
      //   311: ldc 104
      //   313: invokevirtual 39	com/deepinfar/NetWork/SendRovData$RovData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   316: invokevirtual 45	java/lang/Integer:intValue	()I
      //   319: invokestatic 64	com/tools/Bytetools/ByteUtils:ToIntIsBytes	(I)[B
      //   322: astore 4
      //   324: aload_0
      //   325: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   328: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   331: ldc 106
      //   333: aload 4
      //   335: iconst_1
      //   336: baload
      //   337: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   340: invokeinterface 82 3 0
      //   345: pop
      //   346: aload_0
      //   347: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   350: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   353: ldc 108
      //   355: aload 4
      //   357: iconst_0
      //   358: baload
      //   359: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   362: invokeinterface 82 3 0
      //   367: pop
      //   368: iload_2
      //   369: invokestatic 112	com/tools/Bytetools/ByteUtils:ToIntIsByte	(I)B
      //   372: istore_1
      //   373: aload_0
      //   374: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   377: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   380: ldc 114
      //   382: iload_1
      //   383: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   386: invokeinterface 82 3 0
      //   391: pop
      //   392: aload_0
      //   393: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   396: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   399: ldc 116
      //   401: aload_0
      //   402: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   405: invokestatic 31	com/deepinfar/NetWork/SendRovData:access$0	(Lcom/deepinfar/NetWork/SendRovData;)Lcom/deepinfar/NetWork/SendRovData$RovData;
      //   408: ldc 118
      //   410: invokevirtual 39	com/deepinfar/NetWork/SendRovData$RovData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   413: invokevirtual 45	java/lang/Integer:intValue	()I
      //   416: invokestatic 112	com/tools/Bytetools/ByteUtils:ToIntIsByte	(I)B
      //   419: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   422: invokeinterface 82 3 0
      //   427: pop
      //   428: aload_0
      //   429: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   432: invokestatic 31	com/deepinfar/NetWork/SendRovData:access$0	(Lcom/deepinfar/NetWork/SendRovData;)Lcom/deepinfar/NetWork/SendRovData$RovData;
      //   435: ldc 120
      //   437: invokevirtual 39	com/deepinfar/NetWork/SendRovData$RovData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   440: invokevirtual 45	java/lang/Integer:intValue	()I
      //   443: invokestatic 64	com/tools/Bytetools/ByteUtils:ToIntIsBytes	(I)[B
      //   446: astore 4
      //   448: aload_0
      //   449: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   452: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   455: ldc 122
      //   457: aload 4
      //   459: iconst_1
      //   460: baload
      //   461: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   464: invokeinterface 82 3 0
      //   469: pop
      //   470: aload_0
      //   471: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   474: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   477: ldc 124
      //   479: aload 4
      //   481: iconst_0
      //   482: baload
      //   483: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   486: invokeinterface 82 3 0
      //   491: pop
      //   492: aload_0
      //   493: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   496: invokestatic 31	com/deepinfar/NetWork/SendRovData:access$0	(Lcom/deepinfar/NetWork/SendRovData;)Lcom/deepinfar/NetWork/SendRovData$RovData;
      //   499: ldc 126
      //   501: invokevirtual 39	com/deepinfar/NetWork/SendRovData$RovData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   504: invokevirtual 45	java/lang/Integer:intValue	()I
      //   507: invokestatic 112	com/tools/Bytetools/ByteUtils:ToIntIsByte	(I)B
      //   510: istore_1
      //   511: aload_0
      //   512: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   515: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   518: ldc -128
      //   520: iload_1
      //   521: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   524: invokeinterface 82 3 0
      //   529: pop
      //   530: aload_0
      //   531: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   534: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   537: ldc -126
      //   539: iconst_0
      //   540: invokestatic 112	com/tools/Bytetools/ByteUtils:ToIntIsByte	(I)B
      //   543: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   546: invokeinterface 82 3 0
      //   551: pop
      //   552: aload_0
      //   553: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   556: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   559: ldc -124
      //   561: iconst_0
      //   562: invokestatic 112	com/tools/Bytetools/ByteUtils:ToIntIsByte	(I)B
      //   565: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   568: invokeinterface 82 3 0
      //   573: pop
      //   574: aload_3
      //   575: ldc -122
      //   577: invokevirtual 137	com/deepinfar/NetWork/SendRovData$VideoData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   580: invokevirtual 45	java/lang/Integer:intValue	()I
      //   583: invokestatic 64	com/tools/Bytetools/ByteUtils:ToIntIsBytes	(I)[B
      //   586: astore 4
      //   588: aload_0
      //   589: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   592: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   595: ldc -117
      //   597: aload 4
      //   599: iconst_1
      //   600: baload
      //   601: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   604: invokeinterface 82 3 0
      //   609: pop
      //   610: aload_0
      //   611: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   614: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   617: ldc -115
      //   619: aload 4
      //   621: iconst_0
      //   622: baload
      //   623: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   626: invokeinterface 82 3 0
      //   631: pop
      //   632: aload_3
      //   633: ldc -113
      //   635: invokevirtual 137	com/deepinfar/NetWork/SendRovData$VideoData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   638: invokevirtual 45	java/lang/Integer:intValue	()I
      //   641: invokestatic 64	com/tools/Bytetools/ByteUtils:ToIntIsBytes	(I)[B
      //   644: astore 4
      //   646: aload_0
      //   647: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   650: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   653: ldc -111
      //   655: aload 4
      //   657: iconst_1
      //   658: baload
      //   659: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   662: invokeinterface 82 3 0
      //   667: pop
      //   668: aload_0
      //   669: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   672: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   675: ldc -109
      //   677: aload 4
      //   679: iconst_0
      //   680: baload
      //   681: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   684: invokeinterface 82 3 0
      //   689: pop
      //   690: aload_3
      //   691: ldc -107
      //   693: invokevirtual 137	com/deepinfar/NetWork/SendRovData$VideoData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   696: invokevirtual 45	java/lang/Integer:intValue	()I
      //   699: invokestatic 64	com/tools/Bytetools/ByteUtils:ToIntIsBytes	(I)[B
      //   702: astore 4
      //   704: aload_0
      //   705: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   708: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   711: ldc -105
      //   713: aload 4
      //   715: iconst_1
      //   716: baload
      //   717: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   720: invokeinterface 82 3 0
      //   725: pop
      //   726: aload_0
      //   727: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   730: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   733: ldc -103
      //   735: aload 4
      //   737: iconst_0
      //   738: baload
      //   739: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   742: invokeinterface 82 3 0
      //   747: pop
      //   748: aload_3
      //   749: ldc -101
      //   751: invokevirtual 137	com/deepinfar/NetWork/SendRovData$VideoData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   754: invokevirtual 45	java/lang/Integer:intValue	()I
      //   757: invokestatic 64	com/tools/Bytetools/ByteUtils:ToIntIsBytes	(I)[B
      //   760: astore 4
      //   762: aload_0
      //   763: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   766: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   769: ldc -99
      //   771: aload 4
      //   773: iconst_1
      //   774: baload
      //   775: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   778: invokeinterface 82 3 0
      //   783: pop
      //   784: aload_0
      //   785: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   788: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   791: ldc -97
      //   793: aload 4
      //   795: iconst_0
      //   796: baload
      //   797: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   800: invokeinterface 82 3 0
      //   805: pop
      //   806: aload_0
      //   807: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   810: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   813: ldc -95
      //   815: iconst_0
      //   816: invokestatic 112	com/tools/Bytetools/ByteUtils:ToIntIsByte	(I)B
      //   819: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   822: invokeinterface 82 3 0
      //   827: pop
      //   828: aload_3
      //   829: ldc -93
      //   831: invokevirtual 137	com/deepinfar/NetWork/SendRovData$VideoData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   834: invokevirtual 45	java/lang/Integer:intValue	()I
      //   837: invokestatic 112	com/tools/Bytetools/ByteUtils:ToIntIsByte	(I)B
      //   840: istore_1
      //   841: aload_0
      //   842: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   845: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   848: ldc -91
      //   850: iload_1
      //   851: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   854: invokeinterface 82 3 0
      //   859: pop
      //   860: aload_3
      //   861: ldc -89
      //   863: invokevirtual 137	com/deepinfar/NetWork/SendRovData$VideoData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   866: invokevirtual 45	java/lang/Integer:intValue	()I
      //   869: invokestatic 112	com/tools/Bytetools/ByteUtils:ToIntIsByte	(I)B
      //   872: istore_1
      //   873: aload_0
      //   874: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   877: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   880: ldc -87
      //   882: iload_1
      //   883: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   886: invokeinterface 82 3 0
      //   891: pop
      //   892: aload_3
      //   893: ldc -85
      //   895: invokevirtual 137	com/deepinfar/NetWork/SendRovData$VideoData:getDataInt	(Ljava/lang/String;)Ljava/lang/Integer;
      //   898: invokevirtual 45	java/lang/Integer:intValue	()I
      //   901: invokestatic 112	com/tools/Bytetools/ByteUtils:ToIntIsByte	(I)B
      //   904: istore_1
      //   905: aload_0
      //   906: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   909: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   912: ldc -83
      //   914: iload_1
      //   915: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   918: invokeinterface 82 3 0
      //   923: pop
      //   924: aload_0
      //   925: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   928: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   931: ldc -81
      //   933: iconst_0
      //   934: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   937: invokeinterface 82 3 0
      //   942: pop
      //   943: aload_0
      //   944: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   947: invokestatic 68	com/deepinfar/NetWork/SendRovData:access$1	(Lcom/deepinfar/NetWork/SendRovData;)Ljava/util/Map;
      //   950: ldc -79
      //   952: iconst_0
      //   953: invokestatic 76	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
      //   956: invokeinterface 82 3 0
      //   961: pop
      //   962: aload_0
      //   963: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   966: invokevirtual 180	com/deepinfar/NetWork/SendRovData:updataByteArray	()V
      //   969: aload_0
      //   970: getfield 12	com/deepinfar/NetWork/SendRovData$1:this$0	Lcom/deepinfar/NetWork/SendRovData;
      //   973: invokevirtual 183	com/deepinfar/NetWork/SendRovData:sendData	()V
      //   976: goto -963 -> 13
      //   979: astore_3
      //   980: aload_0
      //   981: monitorexit
      //   982: aload_3
      //   983: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	984	0	this	1
      //   372	543	1	b	byte
      //   31	338	2	i	int
      //   8	885	3	localObject1	Object
      //   979	4	3	localObject2	Object
      //   66	728	4	arrayOfByte	byte[]
      // Exception table:
      //   from	to	target	type
      //   2	9	979	finally
      //   16	976	979	finally
    }
  };
  private Map<String, VideoData> videoData;
  
  public SendRovData(NetWork paramNetWork)
  {
    this.netWork = paramNetWork;
    this.videoData = new HashMap();
    this.videoData.put("MAIN", new VideoData("MAIN"));
    this.rovData = new RovData();
    this.netWorkByte = new HashMap();
    this.res = paramNetWork.getContext().getResources();
    this.menu = this.res.getStringArray(2130968582);
    initSendData();
    this.timer.schedule(this.timerTask, 50L, 50L);
  }
  
  public static SendRovData getSendRovData(String paramString, NetWork paramNetWork)
  {
    if ((sendRovDatas == null) && (paramString != null))
    {
      sendRovDatas = new HashMap();
      sendRovDatas.put(paramString, new SendRovData(paramNetWork));
    }
    if ((paramString != null) && (!paramString.equals("")))
    {
      SendRovData localSendRovData2 = (SendRovData)sendRovDatas.get(paramString);
      SendRovData localSendRovData1 = localSendRovData2;
      if (localSendRovData2 == null)
      {
        localSendRovData1 = new SendRovData(paramNetWork);
        sendRovDatas.put(paramString, localSendRovData1);
      }
      return localSendRovData1;
    }
    return new SendRovData(paramNetWork);
  }
  
  private void initSendData()
  {
    this.netWorkByte.put("开始标志1", Byte.valueOf((byte)83));
    this.netWorkByte.put("开始标志2", Byte.valueOf((byte)84));
    this.netWorkByte.put("开始标志3", Byte.valueOf((byte)82));
    this.netWorkByte.put("开始标志4", Byte.valueOf((byte)67));
    this.netWorkByte.put("开始标志5", Byte.valueOf((byte)1));
    this.netWorkByte.put("开始标志6", Byte.valueOf((byte)0));
    this.netWorkByte.put("开始标志7", Byte.valueOf((byte)0));
    this.netWorkByte.put("开始标志8", Byte.valueOf((byte)0));
    this.netWorkByte.put("横滚角1", Byte.valueOf((byte)0));
    this.netWorkByte.put("横滚角2", Byte.valueOf((byte)0));
    this.netWorkByte.put("俯仰角1", Byte.valueOf((byte)0));
    this.netWorkByte.put("俯仰角2", Byte.valueOf((byte)0));
    this.netWorkByte.put("航向角1", Byte.valueOf((byte)0));
    this.netWorkByte.put("航向角2", Byte.valueOf((byte)0));
    this.netWorkByte.put("前后速度1", Byte.valueOf((byte)0));
    this.netWorkByte.put("前后速度2", Byte.valueOf((byte)0));
    this.netWorkByte.put("横移速度1", Byte.valueOf((byte)0));
    this.netWorkByte.put("横移速度2", Byte.valueOf((byte)0));
    this.netWorkByte.put("升降速度1", Byte.valueOf((byte)0));
    this.netWorkByte.put("升降速度2", Byte.valueOf((byte)0));
    this.netWorkByte.put("工作模式", Byte.valueOf((byte)0));
    this.netWorkByte.put("航向启停", Byte.valueOf((byte)0));
    this.netWorkByte.put("定深/定高1", Byte.valueOf((byte)0));
    this.netWorkByte.put("定深/定高2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(姿态控制)保留1", Byte.valueOf((byte)0));
    this.netWorkByte.put("(姿态控制)保留2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(姿态控制)保留3", Byte.valueOf((byte)0));
    this.netWorkByte.put("(姿态控制)保留4", Byte.valueOf((byte)0));
    this.netWorkByte.put("板载设备供电控制1", Byte.valueOf((byte)0));
    this.netWorkByte.put("板载设备供电控制2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)俯仰1", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)俯仰2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)旋转1", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)旋转2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)焦点1", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)焦点2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)缩放1", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)缩放2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1) 调焦模式", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)W亮度左", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)W亮度右", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头1)激光", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)俯仰1", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)俯仰2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)旋转1", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)旋转2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)焦点1", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)焦点2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)缩放1", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)缩放2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2) 调焦模式", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)W亮度左", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)W亮度右", Byte.valueOf((byte)0));
    this.netWorkByte.put("(摄像头2)激光", Byte.valueOf((byte)0));
    this.netWorkByte.put("(机械手)开合", Byte.valueOf((byte)0));
    this.netWorkByte.put("(机械手)旋转", Byte.valueOf((byte)0));
    this.netWorkByte.put("(机械手)伸缩", Byte.valueOf((byte)0));
    this.netWorkByte.put("(机械手)保留1", Byte.valueOf((byte)0));
    this.netWorkByte.put("(机械手)保留2", Byte.valueOf((byte)0));
    this.netWorkByte.put("(机械手)保留3", Byte.valueOf((byte)0));
    this.netWorkByte.put("(机械手)保留4", Byte.valueOf((byte)0));
    this.netWorkByte.put("(机械手)保留5", Byte.valueOf((byte)0));
    this.netWorkByte.put("系统保留1", Byte.valueOf((byte)0));
    this.netWorkByte.put("系统保留2", Byte.valueOf((byte)0));
    this.netWorkByte.put("系统保留3", Byte.valueOf((byte)0));
    this.netWorkByte.put("OSD开关", Byte.valueOf((byte)0));
    this.netWorkByte.put("发送序号1", Byte.valueOf((byte)0));
    this.netWorkByte.put("发送序号2", Byte.valueOf((byte)0));
    this.netWorkByte.put("CRC校验1", Byte.valueOf((byte)0));
    this.netWorkByte.put("CRC校验2", Byte.valueOf((byte)0));
    this.netWorkByte.put("结束标志1", Byte.valueOf((byte)69));
    this.netWorkByte.put("结束标志2", Byte.valueOf((byte)68));
    this.netWorkByte.put("结束标志3", Byte.valueOf((byte)82));
    this.netWorkByte.put("结束标志4", Byte.valueOf((byte)67));
    this.netWorkByte.put("结束标志5", Byte.valueOf((byte)1));
    this.netWorkByte.put("结束标志6", Byte.valueOf((byte)0));
    this.netWorkByte.put("结束标志7", Byte.valueOf((byte)0));
    this.netWorkByte.put("结束标志8", Byte.valueOf((byte)0));
    this.sendBytes = new byte[this.menu.length];
  }
  
  public void addNoOdinal()
  {
    this.Send_No += 1;
    try
    {
      ByteUtils.ToIntIsBytes(this.Send_No);
      byte[] arrayOfByte = ByteUtils.ToIntIsBytes(this.Send_No);
      this.netWorkByte.put("发送序号1", Byte.valueOf(arrayOfByte[1]));
      this.netWorkByte.put("发送序号2", Byte.valueOf(arrayOfByte[0]));
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        this.Send_No = 0;
      }
    }
  }
  
  public int getAXIS_RZ()
  {
    return this.AXIS_RZ;
  }
  
  public int getAXIS_RovRZ()
  {
    return -this.AXIS_RZ;
  }
  
  public int getAXIS_RovY()
  {
    return -this.AXIS_Y;
  }
  
  public int getAXIS_X()
  {
    return this.AXIS_X;
  }
  
  public int getAXIS_Y()
  {
    return this.AXIS_Y;
  }
  
  public int getAXIS_Z()
  {
    return this.AXIS_Z;
  }
  
  public RovData getRovData()
  {
    return this.rovData;
  }
  
  public byte getSendBytes(int paramInt)
  {
    return this.sendBytes[paramInt];
  }
  
  public byte[] getSendBytes()
  {
    return this.sendBytes;
  }
  
  public VideoData getVideoData(String paramString)
  {
    return (VideoData)this.videoData.get(paramString);
  }
  
  public void sendData()
  {
    this.netWork.send(this.sendBytes);
  }
  
  public void setAXIS_RZ(int paramInt)
  {
    this.AXIS_RZ = paramInt;
  }
  
  public void setAXIS_X(int paramInt)
  {
    this.AXIS_X = paramInt;
  }
  
  public void setAXIS_Y(int paramInt)
  {
    this.AXIS_Y = paramInt;
  }
  
  public void setAXIS_Z(int paramInt)
  {
    this.AXIS_Z = paramInt;
  }
  
  public void setSendBytes(int paramInt, byte paramByte)
  {
    this.sendBytes[paramInt] = paramByte;
  }
  
  public void updataByteArray()
  {
    int i = 0;
    byte[] arrayOfByte1;
    if (i >= this.sendBytes.length)
    {
      addNoOdinal();
      arrayOfByte1 = new byte[this.sendBytes.length - 18];
      i = 0;
    }
    for (;;)
    {
      if (i >= arrayOfByte1.length)
      {
        byte[] arrayOfByte2 = CRC16M.getSendBuf(StringTools.bytesToHexString(arrayOfByte1));
        this.sendBytes[(arrayOfByte1.length + 8)] = arrayOfByte2[0];
        this.sendBytes[(arrayOfByte1.length + 9)] = arrayOfByte2[1];
        return;
        this.sendBytes[i] = ((Byte)this.netWorkByte.get(this.menu[i])).byteValue();
        i += 1;
        break;
      }
      arrayOfByte1[i] = this.sendBytes[(i + 8)];
      i += 1;
    }
  }
  
  public class RovData
  {
    public static final String Attitude_Control = "Attitude_Control";
    public static final String Azimuth_Posture = "Azimuth_Posture";
    public static final String Course_StartStop = "Course_StartStop";
    public static final String Pitching_Posture = "Pitching_Posture";
    public static final String Roll_Posture = "Roll_Posture";
    public static final String Work_Pattern = "Work_Pattern";
    public static final String X_Translation = "X_Translation";
    public static final String Y_Translation = "Y_Translation";
    public static final String Z_Translation = "Z_Translation";
    Map<String, Integer> datas = new HashMap();
    
    public RovData()
    {
      init();
    }
    
    public void UpdataData(String paramString, Integer paramInteger)
    {
      this.datas.put(paramString, paramInteger);
    }
    
    public Integer getDataInt(String paramString)
    {
      return (Integer)this.datas.get(paramString);
    }
    
    public void init()
    {
      this.datas.put("X_Translation", Integer.valueOf(0));
      this.datas.put("Y_Translation", Integer.valueOf(0));
      this.datas.put("Z_Translation", Integer.valueOf(0));
      this.datas.put("Roll_Posture", Integer.valueOf(0));
      this.datas.put("Pitching_Posture", Integer.valueOf(0));
      this.datas.put("Azimuth_Posture", Integer.valueOf(0));
      this.datas.put("Work_Pattern", Integer.valueOf(0));
      this.datas.put("Course_StartStop", Integer.valueOf(0));
      this.datas.put("Attitude_Control", Integer.valueOf(0));
    }
  }
  
  public class VideoData
  {
    public static final String Brightness_Left = "Brightness_Left";
    public static final String Brightness_Right = "Brightness_Right";
    public static final String Focal_Length = "Focal_Length";
    public static final String Focal_Model = "Focal_Model";
    public static final String Horizontal_Turn = "Horizontal_Turn";
    public static final String OSD_SW = "OSD_SW";
    public static final String Vertical_Turn = "Vertical_Turn";
    public static final String Zoom_Video = "Zoom_Video";
    private String Tag;
    Map<String, Integer> datas = new HashMap();
    
    public VideoData(String paramString)
    {
      this.Tag = paramString;
      init();
    }
    
    private void init()
    {
      this.datas.put("Horizontal_Turn", Integer.valueOf(0));
      this.datas.put("Vertical_Turn", Integer.valueOf(0));
      this.datas.put("Zoom_Video", Integer.valueOf(0));
      this.datas.put("Focal_Length", Integer.valueOf(0));
      this.datas.put("Focal_Model", Integer.valueOf(0));
      this.datas.put("Brightness_Left", Integer.valueOf(0));
      this.datas.put("Brightness_Right", Integer.valueOf(0));
      this.datas.put("OSD_SW", Integer.valueOf(0));
    }
    
    public void UpdataData(String paramString, Integer paramInteger)
    {
      this.datas.put(paramString, paramInteger);
    }
    
    public Integer getDataInt(String paramString)
    {
      return (Integer)this.datas.get(paramString);
    }
    
    public Map<String, Integer> getDatas()
    {
      return this.datas;
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/NetWork/SendRovData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */