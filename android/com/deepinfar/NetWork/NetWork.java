package com.deepinfar.NetWork;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Handler;
import com.deepinfar.rov.StartActivity;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class NetWork
{
  public static int Port;
  public static boolean ReadFlags = false;
  public static boolean SendFlags = false;
  private static NetWork network;
  private String IP;
  public Runnable ReadThread = new Runnable()
  {
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   4: invokestatic 25	com/deepinfar/NetWork/NetWork:access$0	(Lcom/deepinfar/NetWork/NetWork;)Ljava/net/Socket;
      //   7: ifnull +16 -> 23
      //   10: aload_0
      //   11: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   14: invokestatic 25	com/deepinfar/NetWork/NetWork:access$0	(Lcom/deepinfar/NetWork/NetWork;)Ljava/net/Socket;
      //   17: invokevirtual 31	java/net/Socket:isConnected	()Z
      //   20: ifne +4 -> 24
      //   23: return
      //   24: aload_0
      //   25: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   28: getfield 35	com/deepinfar/NetWork/NetWork:dataInputStream	Ljava/io/DataInputStream;
      //   31: aload_0
      //   32: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   35: invokestatic 39	com/deepinfar/NetWork/NetWork:access$1	(Lcom/deepinfar/NetWork/NetWork;)[B
      //   38: invokevirtual 45	java/io/DataInputStream:read	([B)I
      //   41: istore_3
      //   42: iconst_1
      //   43: putstatic 49	com/deepinfar/NetWork/NetWork:ReadFlags	Z
      //   46: iconst_2
      //   47: newarray <illegal type>
      //   49: dup
      //   50: iconst_0
      //   51: aload_0
      //   52: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   55: invokestatic 39	com/deepinfar/NetWork/NetWork:access$1	(Lcom/deepinfar/NetWork/NetWork;)[B
      //   58: bipush 36
      //   60: baload
      //   61: bastore
      //   62: dup
      //   63: iconst_1
      //   64: aload_0
      //   65: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   68: invokestatic 39	com/deepinfar/NetWork/NetWork:access$1	(Lcom/deepinfar/NetWork/NetWork;)[B
      //   71: bipush 37
      //   73: baload
      //   74: bastore
      //   75: invokestatic 54	com/tools/Bytetools/ByteUtils:ToBytesIsInt	([B)I
      //   78: i2d
      //   79: ldc2_w 55
      //   82: ddiv
      //   83: dstore_1
      //   84: iconst_2
      //   85: newarray <illegal type>
      //   87: dup
      //   88: iconst_0
      //   89: aload_0
      //   90: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   93: invokestatic 39	com/deepinfar/NetWork/NetWork:access$1	(Lcom/deepinfar/NetWork/NetWork;)[B
      //   96: bipush 40
      //   98: baload
      //   99: bastore
      //   100: dup
      //   101: iconst_1
      //   102: aload_0
      //   103: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   106: invokestatic 39	com/deepinfar/NetWork/NetWork:access$1	(Lcom/deepinfar/NetWork/NetWork;)[B
      //   109: bipush 41
      //   111: baload
      //   112: bastore
      //   113: invokestatic 54	com/tools/Bytetools/ByteUtils:ToBytesIsInt	([B)I
      //   116: istore 4
      //   118: iconst_2
      //   119: newarray <illegal type>
      //   121: dup
      //   122: iconst_0
      //   123: aload_0
      //   124: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   127: invokestatic 39	com/deepinfar/NetWork/NetWork:access$1	(Lcom/deepinfar/NetWork/NetWork;)[B
      //   130: bipush 38
      //   132: baload
      //   133: bastore
      //   134: dup
      //   135: iconst_1
      //   136: aload_0
      //   137: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   140: invokestatic 39	com/deepinfar/NetWork/NetWork:access$1	(Lcom/deepinfar/NetWork/NetWork;)[B
      //   143: bipush 39
      //   145: baload
      //   146: bastore
      //   147: invokestatic 54	com/tools/Bytetools/ByteUtils:ToBytesIsInt	([B)I
      //   150: istore 5
      //   152: invokestatic 62	com/deepinfar/rov/Alarm_Fragment:getAlarm_Fragment	()Lcom/deepinfar/rov/Alarm_Fragment;
      //   155: astore 6
      //   157: aload 6
      //   159: ifnull +34 -> 193
      //   162: getstatic 67	com/deepinfar/Dialog/LsattrDialog:bAlarmShow	Z
      //   165: ifeq +28 -> 193
      //   168: iload 4
      //   170: bipush 60
      //   172: if_icmpgt +11 -> 183
      //   175: iload 5
      //   177: sipush 800
      //   180: if_icmple +58 -> 238
      //   183: aload 6
      //   185: invokevirtual 71	com/deepinfar/rov/Alarm_Fragment:getRootViewUpdataUI	()Landroid/os/Handler;
      //   188: iconst_1
      //   189: invokevirtual 77	android/os/Handler:sendEmptyMessage	(I)Z
      //   192: pop
      //   193: iload_3
      //   194: iconst_m1
      //   195: if_icmpne -195 -> 0
      //   198: aload_0
      //   199: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   202: invokestatic 25	com/deepinfar/NetWork/NetWork:access$0	(Lcom/deepinfar/NetWork/NetWork;)Ljava/net/Socket;
      //   205: invokevirtual 80	java/net/Socket:close	()V
      //   208: aload_0
      //   209: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   212: aconst_null
      //   213: invokestatic 84	com/deepinfar/NetWork/NetWork:access$2	(Lcom/deepinfar/NetWork/NetWork;Ljava/net/Socket;)V
      //   216: return
      //   217: astore 6
      //   219: aload_0
      //   220: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   223: invokestatic 25	com/deepinfar/NetWork/NetWork:access$0	(Lcom/deepinfar/NetWork/NetWork;)Ljava/net/Socket;
      //   226: invokevirtual 80	java/net/Socket:close	()V
      //   229: aload_0
      //   230: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   233: aconst_null
      //   234: invokestatic 84	com/deepinfar/NetWork/NetWork:access$2	(Lcom/deepinfar/NetWork/NetWork;Ljava/net/Socket;)V
      //   237: return
      //   238: dload_1
      //   239: ldc2_w 85
      //   242: dcmpl
      //   243: ifle +16 -> 259
      //   246: aload 6
      //   248: invokevirtual 71	com/deepinfar/rov/Alarm_Fragment:getRootViewUpdataUI	()Landroid/os/Handler;
      //   251: iconst_2
      //   252: invokevirtual 77	android/os/Handler:sendEmptyMessage	(I)Z
      //   255: pop
      //   256: goto -63 -> 193
      //   259: aload 6
      //   261: invokevirtual 71	com/deepinfar/rov/Alarm_Fragment:getRootViewUpdataUI	()Landroid/os/Handler;
      //   264: iconst_0
      //   265: invokevirtual 77	android/os/Handler:sendEmptyMessage	(I)Z
      //   268: pop
      //   269: goto -76 -> 193
      //   272: astore 6
      //   274: aload_0
      //   275: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   278: aconst_null
      //   279: invokestatic 84	com/deepinfar/NetWork/NetWork:access$2	(Lcom/deepinfar/NetWork/NetWork;Ljava/net/Socket;)V
      //   282: goto -74 -> 208
      //   285: astore 6
      //   287: aload_0
      //   288: getfield 14	com/deepinfar/NetWork/NetWork$1:this$0	Lcom/deepinfar/NetWork/NetWork;
      //   291: aconst_null
      //   292: invokestatic 84	com/deepinfar/NetWork/NetWork:access$2	(Lcom/deepinfar/NetWork/NetWork;Ljava/net/Socket;)V
      //   295: goto -66 -> 229
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	298	0	this	1
      //   83	156	1	d	double
      //   41	155	3	i	int
      //   116	57	4	j	int
      //   150	31	5	k	int
      //   155	29	6	localAlarm_Fragment	com.deepinfar.rov.Alarm_Fragment
      //   217	43	6	localIOException1	java.io.IOException
      //   272	1	6	localIOException2	java.io.IOException
      //   285	1	6	localIOException3	java.io.IOException
      // Exception table:
      //   from	to	target	type
      //   24	157	217	java/io/IOException
      //   162	168	217	java/io/IOException
      //   183	193	217	java/io/IOException
      //   208	216	217	java/io/IOException
      //   246	256	217	java/io/IOException
      //   259	269	217	java/io/IOException
      //   274	282	217	java/io/IOException
      //   198	208	272	java/io/IOException
      //   219	229	285	java/io/IOException
    }
  };
  public Handler ToastHandler;
  public Context context;
  public DataInputStream dataInputStream;
  private DataOutputStream dataOutputStream;
  private byte[] mBuf = new byte[114];
  private SendRovData sendRovData;
  private Socket socket;
  
  private NetWork(Context paramContext, Handler paramHandler)
  {
    this.ToastHandler = paramHandler;
    this.context = paramContext;
    connect();
    this.sendRovData = SendRovData.getSendRovData("MAIN", this);
  }
  
  public static NetWork getNetwork()
    throws NullPointerException
  {
    if (network == null) {
      throw new NullPointerException();
    }
    return network;
  }
  
  public static NetWork getNetwork(Context paramContext, Handler paramHandler, int paramInt)
  {
    if (network == null)
    {
      Port = paramInt;
      network = new NetWork(paramContext, paramHandler);
    }
    return network;
  }
  
  public boolean checkNetworkState()
    throws NullPointerException
  {
    boolean bool = false;
    ConnectivityManager localConnectivityManager = (ConnectivityManager)StartActivity.getStartAvtivity().getSystemService("connectivity");
    if (localConnectivityManager.getActiveNetworkInfo() != null) {
      bool = localConnectivityManager.getActiveNetworkInfo().isAvailable();
    }
    if (!bool) {
      return false;
    }
    return isNetworkAvailable(localConnectivityManager);
  }
  
  /* Error */
  public boolean connect()
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: aload_0
    //   3: monitorenter
    //   4: new 120	android/os/Message
    //   7: dup
    //   8: invokespecial 121	android/os/Message:<init>	()V
    //   11: astore_3
    //   12: aload_3
    //   13: iconst_1
    //   14: putfield 124	android/os/Message:what	I
    //   17: aload_0
    //   18: getfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   21: ifnull +34 -> 55
    //   24: aload_0
    //   25: getfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   28: invokevirtual 129	java/net/Socket:isConnected	()Z
    //   31: ifeq +24 -> 55
    //   34: aload_3
    //   35: ldc -125
    //   37: putfield 135	android/os/Message:obj	Ljava/lang/Object;
    //   40: aload_0
    //   41: getfield 51	com/deepinfar/NetWork/NetWork:ToastHandler	Landroid/os/Handler;
    //   44: aload_3
    //   45: invokevirtual 141	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   48: pop
    //   49: iload_2
    //   50: istore_1
    //   51: aload_0
    //   52: monitorexit
    //   53: iload_1
    //   54: ireturn
    //   55: aload_0
    //   56: getfield 53	com/deepinfar/NetWork/NetWork:context	Landroid/content/Context;
    //   59: ldc -113
    //   61: invokevirtual 146	android/content/Context:getSystemService	(Ljava/lang/String;)Ljava/lang/Object;
    //   64: checkcast 148	android/net/wifi/WifiManager
    //   67: astore 4
    //   69: iload_2
    //   70: istore_1
    //   71: aload 4
    //   73: invokevirtual 151	android/net/wifi/WifiManager:isWifiEnabled	()Z
    //   76: ifeq -25 -> 51
    //   79: aload 4
    //   81: invokevirtual 155	android/net/wifi/WifiManager:getDhcpInfo	()Landroid/net/DhcpInfo;
    //   84: pop
    //   85: aload_0
    //   86: invokestatic 161	com/tools/file/FileDataUtils:getFileDataUtils	()Lcom/tools/file/FileDataUtils;
    //   89: ldc -93
    //   91: invokevirtual 167	com/tools/file/FileDataUtils:getTagData	(Ljava/lang/String;)Ljava/lang/String;
    //   94: putfield 169	com/deepinfar/NetWork/NetWork:IP	Ljava/lang/String;
    //   97: invokestatic 161	com/tools/file/FileDataUtils:getFileDataUtils	()Lcom/tools/file/FileDataUtils;
    //   100: ldc -85
    //   102: invokevirtual 167	com/tools/file/FileDataUtils:getTagData	(Ljava/lang/String;)Ljava/lang/String;
    //   105: invokestatic 177	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   108: putstatic 86	com/deepinfar/NetWork/NetWork:Port	I
    //   111: aload_0
    //   112: new 126	java/net/Socket
    //   115: dup
    //   116: invokespecial 178	java/net/Socket:<init>	()V
    //   119: putfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   122: new 180	java/net/InetSocketAddress
    //   125: dup
    //   126: aload_0
    //   127: getfield 169	com/deepinfar/NetWork/NetWork:IP	Ljava/lang/String;
    //   130: getstatic 86	com/deepinfar/NetWork/NetWork:Port	I
    //   133: invokespecial 183	java/net/InetSocketAddress:<init>	(Ljava/lang/String;I)V
    //   136: astore 4
    //   138: aload_0
    //   139: getfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   142: aload 4
    //   144: sipush 5000
    //   147: invokevirtual 186	java/net/Socket:connect	(Ljava/net/SocketAddress;I)V
    //   150: aload_0
    //   151: new 188	java/io/DataOutputStream
    //   154: dup
    //   155: aload_0
    //   156: getfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   159: invokevirtual 192	java/net/Socket:getOutputStream	()Ljava/io/OutputStream;
    //   162: invokespecial 195	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   165: putfield 197	com/deepinfar/NetWork/NetWork:dataOutputStream	Ljava/io/DataOutputStream;
    //   168: aload_0
    //   169: new 199	java/io/DataInputStream
    //   172: dup
    //   173: aload_0
    //   174: getfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   177: invokevirtual 203	java/net/Socket:getInputStream	()Ljava/io/InputStream;
    //   180: invokespecial 206	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   183: putfield 208	com/deepinfar/NetWork/NetWork:dataInputStream	Ljava/io/DataInputStream;
    //   186: aload_3
    //   187: ldc -46
    //   189: putfield 135	android/os/Message:obj	Ljava/lang/Object;
    //   192: aload_0
    //   193: getfield 51	com/deepinfar/NetWork/NetWork:ToastHandler	Landroid/os/Handler;
    //   196: aload_3
    //   197: invokevirtual 141	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   200: pop
    //   201: new 212	java/lang/Thread
    //   204: dup
    //   205: aload_0
    //   206: getfield 49	com/deepinfar/NetWork/NetWork:ReadThread	Ljava/lang/Runnable;
    //   209: invokespecial 215	java/lang/Thread:<init>	(Ljava/lang/Runnable;)V
    //   212: invokevirtual 218	java/lang/Thread:start	()V
    //   215: iconst_1
    //   216: istore_1
    //   217: goto -166 -> 51
    //   220: astore 4
    //   222: aload_0
    //   223: invokevirtual 220	com/deepinfar/NetWork/NetWork:checkNetworkState	()Z
    //   226: ifne +33 -> 259
    //   229: aload_3
    //   230: ldc -34
    //   232: putfield 135	android/os/Message:obj	Ljava/lang/Object;
    //   235: aload_0
    //   236: getfield 51	com/deepinfar/NetWork/NetWork:ToastHandler	Landroid/os/Handler;
    //   239: aload_3
    //   240: invokevirtual 141	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   243: pop
    //   244: aload 4
    //   246: invokevirtual 225	java/io/IOException:printStackTrace	()V
    //   249: iload_2
    //   250: istore_1
    //   251: goto -200 -> 51
    //   254: astore_3
    //   255: aload_0
    //   256: monitorexit
    //   257: aload_3
    //   258: athrow
    //   259: aload_3
    //   260: ldc -29
    //   262: putfield 135	android/os/Message:obj	Ljava/lang/Object;
    //   265: goto -30 -> 235
    //   268: astore 5
    //   270: aload_3
    //   271: ldc -29
    //   273: putfield 135	android/os/Message:obj	Ljava/lang/Object;
    //   276: goto -41 -> 235
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	279	0	this	NetWork
    //   50	201	1	bool1	boolean
    //   1	249	2	bool2	boolean
    //   11	229	3	localMessage	android.os.Message
    //   254	17	3	localObject1	Object
    //   67	76	4	localObject2	Object
    //   220	25	4	localIOException	java.io.IOException
    //   268	1	5	localNullPointerException	NullPointerException
    // Exception table:
    //   from	to	target	type
    //   55	69	220	java/io/IOException
    //   71	215	220	java/io/IOException
    //   4	49	254	finally
    //   55	69	254	finally
    //   71	215	254	finally
    //   222	235	254	finally
    //   235	249	254	finally
    //   259	265	254	finally
    //   270	276	254	finally
    //   222	235	268	java/lang/NullPointerException
    //   259	265	268	java/lang/NullPointerException
  }
  
  public Context getContext()
  {
    return this.context;
  }
  
  public SendRovData getSendRovData()
  {
    return this.sendRovData;
  }
  
  public byte getmBuf(int paramInt)
  {
    return this.mBuf[paramInt];
  }
  
  public byte[] getmBuf()
  {
    return this.mBuf;
  }
  
  public boolean isNetworkAvailable(ConnectivityManager paramConnectivityManager)
  {
    paramConnectivityManager = paramConnectivityManager.getNetworkInfo(1).getState();
    return (paramConnectivityManager == NetworkInfo.State.CONNECTED) || (paramConnectivityManager == NetworkInfo.State.CONNECTING);
  }
  
  /* Error */
  public void send(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 120	android/os/Message
    //   5: dup
    //   6: invokespecial 121	android/os/Message:<init>	()V
    //   9: astore_3
    //   10: aload_3
    //   11: iconst_1
    //   12: putfield 124	android/os/Message:what	I
    //   15: aload_0
    //   16: getfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   19: ifnull +72 -> 91
    //   22: aload_0
    //   23: getfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   26: invokevirtual 129	java/net/Socket:isConnected	()Z
    //   29: istore_2
    //   30: iload_2
    //   31: ifeq +60 -> 91
    //   34: aload_0
    //   35: getfield 197	com/deepinfar/NetWork/NetWork:dataOutputStream	Ljava/io/DataOutputStream;
    //   38: aload_1
    //   39: invokevirtual 255	java/io/DataOutputStream:write	([B)V
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore_1
    //   46: aload_3
    //   47: ldc_w 257
    //   50: putfield 135	android/os/Message:obj	Ljava/lang/Object;
    //   53: aload_0
    //   54: getfield 51	com/deepinfar/NetWork/NetWork:ToastHandler	Landroid/os/Handler;
    //   57: aload_3
    //   58: invokevirtual 141	android/os/Handler:sendMessage	(Landroid/os/Message;)Z
    //   61: pop
    //   62: aload_0
    //   63: getfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   66: invokevirtual 260	java/net/Socket:close	()V
    //   69: aload_0
    //   70: aconst_null
    //   71: putfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   74: goto -32 -> 42
    //   77: astore_1
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_1
    //   81: athrow
    //   82: astore_1
    //   83: aload_0
    //   84: aconst_null
    //   85: putfield 71	com/deepinfar/NetWork/NetWork:socket	Ljava/net/Socket;
    //   88: goto -19 -> 69
    //   91: aload_0
    //   92: invokevirtual 57	com/deepinfar/NetWork/NetWork:connect	()Z
    //   95: pop
    //   96: goto -54 -> 42
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	NetWork
    //   0	99	1	paramArrayOfByte	byte[]
    //   29	2	2	bool	boolean
    //   9	49	3	localMessage	android.os.Message
    // Exception table:
    //   from	to	target	type
    //   34	42	45	java/io/IOException
    //   2	30	77	finally
    //   34	42	77	finally
    //   46	62	77	finally
    //   62	69	77	finally
    //   69	74	77	finally
    //   83	88	77	finally
    //   91	96	77	finally
    //   62	69	82	java/io/IOException
  }
  
  public void setSendRovData(SendRovData paramSendRovData)
  {
    this.sendRovData = paramSendRovData;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/NetWork/NetWork.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */