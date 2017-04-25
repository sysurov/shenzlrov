package com.tools.network;

import android.os.Handler;
import android.os.Message;
import com.tools.Toast.ToastClass;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPSend
{
  protected static final int MSG_SHOW_WARNING = 1000;
  protected static final int MSG_UPDATA_ERROR = 1000;
  protected static final int MSG_UPDATA_FINISH = 0;
  private static Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      ToastClass localToastClass = ToastClass.getToastClass();
      if (paramAnonymousMessage.what == 1000) {
        localToastClass.ToastshowString("IP 地址异常,亲查看视频连接地址是否正确.");
      }
      do
      {
        return;
        if (paramAnonymousMessage.what == 0)
        {
          localToastClass.ToastshowString("时间更新完成。");
          return;
        }
      } while (paramAnonymousMessage.what != 1000);
      localToastClass.ToastshowString("时间自动更新失败,请打开设置手动更新ROV的时间。");
    }
  };
  
  /* Error */
  public static void sendbyUDP(String paramString1, int paramInt, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 6
    //   3: aconst_null
    //   4: astore 7
    //   6: aconst_null
    //   7: astore_3
    //   8: aconst_null
    //   9: astore 5
    //   11: new 35	java/net/DatagramSocket
    //   14: dup
    //   15: invokespecial 36	java/net/DatagramSocket:<init>	()V
    //   18: astore 4
    //   20: aload_0
    //   21: invokestatic 42	java/net/InetAddress:getByName	(Ljava/lang/String;)Ljava/net/InetAddress;
    //   24: astore_0
    //   25: aload_2
    //   26: invokevirtual 48	java/lang/String:getBytes	()[B
    //   29: astore_2
    //   30: aload 4
    //   32: new 50	java/net/DatagramPacket
    //   35: dup
    //   36: aload_2
    //   37: aload_2
    //   38: arraylength
    //   39: aload_0
    //   40: iload_1
    //   41: invokespecial 53	java/net/DatagramPacket:<init>	([BILjava/net/InetAddress;I)V
    //   44: invokevirtual 57	java/net/DatagramSocket:send	(Ljava/net/DatagramPacket;)V
    //   47: aload 4
    //   49: ifnull +8 -> 57
    //   52: aload 4
    //   54: invokevirtual 60	java/net/DatagramSocket:close	()V
    //   57: return
    //   58: astore_2
    //   59: aload 5
    //   61: astore_0
    //   62: aload_0
    //   63: astore_3
    //   64: aload_2
    //   65: invokevirtual 63	java/net/SocketException:printStackTrace	()V
    //   68: aload_0
    //   69: ifnull -12 -> 57
    //   72: aload_0
    //   73: invokevirtual 60	java/net/DatagramSocket:close	()V
    //   76: return
    //   77: astore_2
    //   78: aload 6
    //   80: astore_0
    //   81: aload_0
    //   82: astore_3
    //   83: aload_2
    //   84: invokevirtual 64	java/net/UnknownHostException:printStackTrace	()V
    //   87: aload_0
    //   88: ifnull -31 -> 57
    //   91: aload_0
    //   92: invokevirtual 60	java/net/DatagramSocket:close	()V
    //   95: return
    //   96: astore_2
    //   97: aload 7
    //   99: astore_0
    //   100: aload_0
    //   101: astore_3
    //   102: aload_2
    //   103: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   106: aload_0
    //   107: ifnull -50 -> 57
    //   110: aload_0
    //   111: invokevirtual 60	java/net/DatagramSocket:close	()V
    //   114: return
    //   115: astore_0
    //   116: aload_3
    //   117: ifnull +7 -> 124
    //   120: aload_3
    //   121: invokevirtual 60	java/net/DatagramSocket:close	()V
    //   124: aload_0
    //   125: athrow
    //   126: astore_0
    //   127: aload 4
    //   129: astore_3
    //   130: goto -14 -> 116
    //   133: astore_2
    //   134: aload 4
    //   136: astore_0
    //   137: goto -37 -> 100
    //   140: astore_2
    //   141: aload 4
    //   143: astore_0
    //   144: goto -63 -> 81
    //   147: astore_2
    //   148: aload 4
    //   150: astore_0
    //   151: goto -89 -> 62
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	154	0	paramString1	String
    //   0	154	1	paramInt	int
    //   0	154	2	paramString2	String
    //   7	123	3	localObject1	Object
    //   18	131	4	localDatagramSocket	java.net.DatagramSocket
    //   9	51	5	localObject2	Object
    //   1	78	6	localObject3	Object
    //   4	94	7	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   11	20	58	java/net/SocketException
    //   11	20	77	java/net/UnknownHostException
    //   11	20	96	java/io/IOException
    //   11	20	115	finally
    //   64	68	115	finally
    //   83	87	115	finally
    //   102	106	115	finally
    //   20	47	126	finally
    //   20	47	133	java/io/IOException
    //   20	47	140	java/net/UnknownHostException
    //   20	47	147	java/net/SocketException
  }
  
  /* Error */
  protected static void sendbyUDP(String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aconst_null
    //   4: astore 5
    //   6: aconst_null
    //   7: astore_3
    //   8: aconst_null
    //   9: astore 6
    //   11: invokestatic 72	com/tools/file/FileDataUtils:getFileDataUtils	()Lcom/tools/file/FileDataUtils;
    //   14: astore 7
    //   16: aload_3
    //   17: astore_2
    //   18: aload 7
    //   20: ldc 74
    //   22: invokevirtual 78	com/tools/file/FileDataUtils:getTagData	(Ljava/lang/String;)Ljava/lang/String;
    //   25: ldc 80
    //   27: invokevirtual 84	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   30: astore 7
    //   32: aload 7
    //   34: ifnull +12 -> 46
    //   37: aload_3
    //   38: astore_2
    //   39: aload 7
    //   41: arraylength
    //   42: iconst_2
    //   43: if_icmpge +52 -> 95
    //   46: aload_3
    //   47: astore_2
    //   48: getstatic 23	com/tools/network/UDPSend:mHandler	Landroid/os/Handler;
    //   51: sipush 1000
    //   54: invokevirtual 90	android/os/Handler:obtainMessage	(I)Landroid/os/Message;
    //   57: invokevirtual 95	android/os/Message:sendToTarget	()V
    //   60: iconst_0
    //   61: ifeq +11 -> 72
    //   64: new 97	java/lang/NullPointerException
    //   67: dup
    //   68: invokespecial 98	java/lang/NullPointerException:<init>	()V
    //   71: athrow
    //   72: return
    //   73: astore_0
    //   74: ldc 100
    //   76: ldc 102
    //   78: invokestatic 108	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   81: pop
    //   82: getstatic 23	com/tools/network/UDPSend:mHandler	Landroid/os/Handler;
    //   85: sipush 1000
    //   88: invokevirtual 90	android/os/Handler:obtainMessage	(I)Landroid/os/Message;
    //   91: invokevirtual 95	android/os/Message:sendToTarget	()V
    //   94: return
    //   95: aload_3
    //   96: astore_2
    //   97: aload 7
    //   99: iconst_1
    //   100: aaload
    //   101: ldc 110
    //   103: invokevirtual 84	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   106: astore 7
    //   108: aload 7
    //   110: ifnonnull +29 -> 139
    //   113: aload_3
    //   114: astore_2
    //   115: getstatic 23	com/tools/network/UDPSend:mHandler	Landroid/os/Handler;
    //   118: sipush 1000
    //   121: invokevirtual 90	android/os/Handler:obtainMessage	(I)Landroid/os/Message;
    //   124: invokevirtual 95	android/os/Message:sendToTarget	()V
    //   127: iconst_0
    //   128: ifeq -56 -> 72
    //   131: new 97	java/lang/NullPointerException
    //   134: dup
    //   135: invokespecial 98	java/lang/NullPointerException:<init>	()V
    //   138: athrow
    //   139: aload_3
    //   140: astore_2
    //   141: aload 7
    //   143: iconst_0
    //   144: aaload
    //   145: ldc 112
    //   147: invokevirtual 84	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   150: astore 8
    //   152: aload 8
    //   154: ifnull +12 -> 166
    //   157: aload_3
    //   158: astore_2
    //   159: aload 8
    //   161: arraylength
    //   162: iconst_4
    //   163: if_icmpeq +29 -> 192
    //   166: aload_3
    //   167: astore_2
    //   168: getstatic 23	com/tools/network/UDPSend:mHandler	Landroid/os/Handler;
    //   171: sipush 1000
    //   174: invokevirtual 90	android/os/Handler:obtainMessage	(I)Landroid/os/Message;
    //   177: invokevirtual 95	android/os/Message:sendToTarget	()V
    //   180: iconst_0
    //   181: ifeq -109 -> 72
    //   184: new 97	java/lang/NullPointerException
    //   187: dup
    //   188: invokespecial 98	java/lang/NullPointerException:<init>	()V
    //   191: athrow
    //   192: aload 7
    //   194: iconst_0
    //   195: aaload
    //   196: astore 7
    //   198: aload_3
    //   199: astore_2
    //   200: new 35	java/net/DatagramSocket
    //   203: dup
    //   204: invokespecial 36	java/net/DatagramSocket:<init>	()V
    //   207: astore_3
    //   208: aload 7
    //   210: invokestatic 42	java/net/InetAddress:getByName	(Ljava/lang/String;)Ljava/net/InetAddress;
    //   213: astore_2
    //   214: aload_0
    //   215: invokevirtual 48	java/lang/String:getBytes	()[B
    //   218: astore_0
    //   219: aload_3
    //   220: new 50	java/net/DatagramPacket
    //   223: dup
    //   224: aload_0
    //   225: aload_0
    //   226: arraylength
    //   227: aload_2
    //   228: sipush 4005
    //   231: invokespecial 53	java/net/DatagramPacket:<init>	([BILjava/net/InetAddress;I)V
    //   234: invokevirtual 57	java/net/DatagramSocket:send	(Ljava/net/DatagramPacket;)V
    //   237: aload_3
    //   238: ifnull +7 -> 245
    //   241: aload_3
    //   242: invokevirtual 60	java/net/DatagramSocket:close	()V
    //   245: iload_1
    //   246: ifne -174 -> 72
    //   249: getstatic 23	com/tools/network/UDPSend:mHandler	Landroid/os/Handler;
    //   252: iconst_0
    //   253: invokevirtual 90	android/os/Handler:obtainMessage	(I)Landroid/os/Message;
    //   256: invokevirtual 95	android/os/Message:sendToTarget	()V
    //   259: return
    //   260: astore_3
    //   261: aload 6
    //   263: astore_0
    //   264: aload_0
    //   265: astore_2
    //   266: aload_3
    //   267: invokevirtual 63	java/net/SocketException:printStackTrace	()V
    //   270: aload_0
    //   271: ifnull -26 -> 245
    //   274: aload_0
    //   275: invokevirtual 60	java/net/DatagramSocket:close	()V
    //   278: goto -33 -> 245
    //   281: astore_3
    //   282: aload 4
    //   284: astore_0
    //   285: aload_0
    //   286: astore_2
    //   287: aload_3
    //   288: invokevirtual 64	java/net/UnknownHostException:printStackTrace	()V
    //   291: aload_0
    //   292: astore_2
    //   293: getstatic 23	com/tools/network/UDPSend:mHandler	Landroid/os/Handler;
    //   296: sipush 1000
    //   299: invokevirtual 90	android/os/Handler:obtainMessage	(I)Landroid/os/Message;
    //   302: invokevirtual 95	android/os/Message:sendToTarget	()V
    //   305: aload_0
    //   306: ifnull -61 -> 245
    //   309: aload_0
    //   310: invokevirtual 60	java/net/DatagramSocket:close	()V
    //   313: goto -68 -> 245
    //   316: astore_3
    //   317: aload 5
    //   319: astore_0
    //   320: aload_0
    //   321: astore_2
    //   322: aload_3
    //   323: invokevirtual 65	java/io/IOException:printStackTrace	()V
    //   326: aload_0
    //   327: ifnull -82 -> 245
    //   330: aload_0
    //   331: invokevirtual 60	java/net/DatagramSocket:close	()V
    //   334: goto -89 -> 245
    //   337: astore_0
    //   338: aload_2
    //   339: ifnull +7 -> 346
    //   342: aload_2
    //   343: invokevirtual 60	java/net/DatagramSocket:close	()V
    //   346: aload_0
    //   347: athrow
    //   348: astore_0
    //   349: aload_3
    //   350: astore_2
    //   351: goto -13 -> 338
    //   354: astore_2
    //   355: aload_3
    //   356: astore_0
    //   357: aload_2
    //   358: astore_3
    //   359: goto -39 -> 320
    //   362: astore_2
    //   363: aload_3
    //   364: astore_0
    //   365: aload_2
    //   366: astore_3
    //   367: goto -82 -> 285
    //   370: astore_2
    //   371: aload_3
    //   372: astore_0
    //   373: aload_2
    //   374: astore_3
    //   375: goto -111 -> 264
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	378	0	paramString	String
    //   0	378	1	paramBoolean	boolean
    //   17	334	2	localObject1	Object
    //   354	4	2	localIOException1	java.io.IOException
    //   362	4	2	localUnknownHostException1	java.net.UnknownHostException
    //   370	4	2	localSocketException1	java.net.SocketException
    //   7	235	3	localDatagramSocket	java.net.DatagramSocket
    //   260	7	3	localSocketException2	java.net.SocketException
    //   281	7	3	localUnknownHostException2	java.net.UnknownHostException
    //   316	40	3	localIOException2	java.io.IOException
    //   358	17	3	localObject2	Object
    //   1	282	4	localObject3	Object
    //   4	314	5	localObject4	Object
    //   9	253	6	localObject5	Object
    //   14	195	7	localObject6	Object
    //   150	10	8	arrayOfString	String[]
    // Exception table:
    //   from	to	target	type
    //   11	16	73	java/io/IOException
    //   18	32	260	java/net/SocketException
    //   39	46	260	java/net/SocketException
    //   48	60	260	java/net/SocketException
    //   97	108	260	java/net/SocketException
    //   115	127	260	java/net/SocketException
    //   141	152	260	java/net/SocketException
    //   159	166	260	java/net/SocketException
    //   168	180	260	java/net/SocketException
    //   200	208	260	java/net/SocketException
    //   18	32	281	java/net/UnknownHostException
    //   39	46	281	java/net/UnknownHostException
    //   48	60	281	java/net/UnknownHostException
    //   97	108	281	java/net/UnknownHostException
    //   115	127	281	java/net/UnknownHostException
    //   141	152	281	java/net/UnknownHostException
    //   159	166	281	java/net/UnknownHostException
    //   168	180	281	java/net/UnknownHostException
    //   200	208	281	java/net/UnknownHostException
    //   18	32	316	java/io/IOException
    //   39	46	316	java/io/IOException
    //   48	60	316	java/io/IOException
    //   97	108	316	java/io/IOException
    //   115	127	316	java/io/IOException
    //   141	152	316	java/io/IOException
    //   159	166	316	java/io/IOException
    //   168	180	316	java/io/IOException
    //   200	208	316	java/io/IOException
    //   18	32	337	finally
    //   39	46	337	finally
    //   48	60	337	finally
    //   97	108	337	finally
    //   115	127	337	finally
    //   141	152	337	finally
    //   159	166	337	finally
    //   168	180	337	finally
    //   200	208	337	finally
    //   266	270	337	finally
    //   287	291	337	finally
    //   293	305	337	finally
    //   322	326	337	finally
    //   208	237	348	finally
    //   208	237	354	java/io/IOException
    //   208	237	362	java/net/UnknownHostException
    //   208	237	370	java/net/SocketException
  }
  
  public static void updataTime(boolean paramBoolean)
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        String str = new SimpleDateFormat("yyyy\tMM\tdd\tHH\tmm\tss\n\n\n").format(new Date(System.currentTimeMillis()));
        UDPSend.sendbyUDP("CMD\tTIMESYNC\t" + str, this.val$mode);
      }
    }).start();
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/network/UDPSend.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */