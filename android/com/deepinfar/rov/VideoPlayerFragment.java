package com.deepinfar.rov;

import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.net.wifi.WifiManager.MulticastLock;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ScaleGestureDetector;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.deepinfar.NetWork.NetWork;
import com.deepinfar.ProtocolData.RovInitData;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import com.tools.network.UDPSend;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import veg.mediaplayer.sdk.MediaPlayer;
import veg.mediaplayer.sdk.MediaPlayer.MediaPlayerCallback;
import veg.mediaplayer.sdk.MediaPlayer.PlayerModes;
import veg.mediaplayer.sdk.MediaPlayer.PlayerNotifyCodes;
import veg.mediaplayer.sdk.MediaPlayer.PlayerProperties;
import veg.mediaplayer.sdk.MediaPlayer.PlayerRecordFlags;
import veg.mediaplayer.sdk.MediaPlayer.VideoShot;
import veg.mediaplayer.sdk.MediaPlayerConfig;

public class VideoPlayerFragment
  extends Fragment
  implements MediaPlayer.MediaPlayerCallback
{
  public static int LastStatus = 0;
  private static final String TAG = "MediaPlayerTest";
  private static VideoPlayerFragment VideoPlayerFragment;
  public static ArrayAdapter<String> edtIpAddressAdapter;
  public static View rootView;
  public ImageView Photo;
  public ScaleGestureDetector detectors = null;
  private SharedPreferences.Editor editor;
  private Handler handler = new Handler()
  {
    String sCode;
    String sText;
    String strText = "Connecting";
    
    public void handleMessage(Message paramAnonymousMessage)
    {
      Object localObject = (MediaPlayer.PlayerNotifyCodes)paramAnonymousMessage.obj;
      switch (paramAnonymousMessage.arg1)
      {
      default: 
        switch (localObject)
        {
        case ARP_STOPPED: 
        case CP_INIT_FAILED: 
        case CP_INTERRUPTED: 
        case CP_RECORD_CLOSED: 
        case CP_RECORD_STARTED: 
        case CRP_STOPPED: 
        case PLP_BUILD_SUCCESSFUL: 
        case PLP_CLOSE_FAILED: 
        case PLP_CLOSE_STARTING: 
        case PLP_CLOSE_SUCCESSFUL: 
        case PLP_EOS: 
        case PLP_ERROR: 
        case PLP_PLAY_FAILED: 
        case PLP_PLAY_PLAY: 
        case PLP_PLAY_STOP: 
        case PLP_SEEK_COMPLETED: 
        case VDP_INIT_FAILED: 
        case VDP_STOPPED: 
        case VRP_INIT_FAILED: 
        default: 
          VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.Busy;
        }
        break;
      }
      for (;;)
      {
        VideoPlayerFragment.LastStatus = paramAnonymousMessage.arg1;
        return;
        UDPSend.updataTime(true);
        VideoPlayerFragment.this.loadVideo.setVisibility(8);
        break;
        if (VideoPlayerFragment.LastStatus != 8)
        {
          VideoPlayerFragment.this.resterConnectVideo(false);
          break;
        }
        if (NetWork.getNetwork().checkNetworkState()) {
          break;
        }
        VideoPlayerFragment.this.toastClass.ToastshowString("wifi连接异常,程序可能运行异常,正在重新连接！");
        VideoPlayerFragment.this.resterConnectVideo(false);
        break;
        if (VideoPlayerFragment.this.reconnect_type == VideoPlayerFragment.PlayerConnectType.Reconnecting) {}
        for (this.strText = "Reconnecting";; this.strText = "Connecting")
        {
          VideoPlayerFragment.this.startProgressTask(this.strText);
          VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.Busy;
          VideoPlayerFragment.this.reconnect_type = VideoPlayerFragment.PlayerConnectType.Normal;
          break;
        }
        Log.e("MediaPlayerTest", "视频连接成功");
        continue;
        this.sText = VideoPlayerFragment.this.player.getPropString(MediaPlayer.PlayerProperties.PP_PROPERTY_PLP_RESPONSE_TEXT);
        this.sCode = VideoPlayerFragment.this.player.getPropString(MediaPlayer.PlayerProperties.PP_PROPERTY_PLP_RESPONSE_CODE);
        Log.i("MediaPlayerTest", "=Status PLP_BUILD_SUCCESSFUL: Response sText=" + this.sText + " sCode=" + this.sCode);
        continue;
        VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.Busy;
        VideoPlayerFragment.this.showVideoView();
        VideoPlayerFragment.this.isPlayer = true;
        continue;
        VideoPlayerFragment.this.resterConnectVideo(false);
        continue;
        VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.ReadyForUse;
        VideoPlayerFragment.this.stopProgressTask();
        continue;
        VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.Busy;
        VideoPlayerFragment.this.stopProgressTask();
        VideoPlayerFragment.this.setUIDisconnected();
        continue;
        VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.ReadyForUse;
        VideoPlayerFragment.this.stopProgressTask();
        VideoPlayerFragment.this.setUIDisconnected();
        continue;
        VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.ReadyForUse;
        VideoPlayerFragment.this.stopProgressTask();
        VideoPlayerFragment.this.setUIDisconnected();
        continue;
        this.sText = VideoPlayerFragment.this.player.getPropString(MediaPlayer.PlayerProperties.PP_PROPERTY_PLP_RESPONSE_TEXT);
        this.sCode = VideoPlayerFragment.this.player.getPropString(MediaPlayer.PlayerProperties.PP_PROPERTY_PLP_RESPONSE_CODE);
        Log.i("MediaPlayerTest", "=Status PLP_BUILD_FAILED: Response sText=" + this.sText + " sCode=" + this.sCode);
        VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.ReadyForUse;
        VideoPlayerFragment.this.stopProgressTask();
        VideoPlayerFragment.this.setUIDisconnected();
        continue;
        VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.ReadyForUse;
        VideoPlayerFragment.this.stopProgressTask();
        VideoPlayerFragment.this.setUIDisconnected();
        continue;
        VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.ReadyForUse;
        VideoPlayerFragment.this.stopProgressTask();
        VideoPlayerFragment.this.setUIDisconnected();
        continue;
        VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.ReadyForUse;
        VideoPlayerFragment.this.stopProgressTask();
        VideoPlayerFragment.this.setUIDisconnected();
        continue;
        Log.v("MediaPlayerTest", "=handleMessage CP_RECORD_STARTED");
        continue;
        Log.v("MediaPlayerTest", "=handleMessage CP_RECORD_STOPPED");
        localObject = VideoPlayerFragment.this.player.RecordGetFileName(0);
        Toast.makeText(VideoPlayerFragment.this.getActivity().getApplicationContext(), "Record Stopped. File " + (String)localObject, 1).show();
        continue;
        if (VideoPlayerFragment.this.player_state != VideoPlayerFragment.PlayerStates.Busy)
        {
          VideoPlayerFragment.this.stopProgressTask();
          VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.Busy;
          VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.ReadyForUse;
          VideoPlayerFragment.this.setUIDisconnected();
          continue;
          if (VideoPlayerFragment.this.player_state != VideoPlayerFragment.PlayerStates.Busy)
          {
            VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.Busy;
            VideoPlayerFragment.this.player_state = VideoPlayerFragment.PlayerStates.ReadyForUse;
            VideoPlayerFragment.this.setUIDisconnected();
            Toast.makeText(VideoPlayerFragment.this.getActivity().getApplicationContext(), "Demo Version!", 0).show();
          }
          VideoPlayerFragment.this.resterConnectVideo(true);
        }
      }
    }
  };
  private boolean isPlayer = false;
  private boolean is_record = false;
  public int loadTime = 0;
  private ProgressBar loadVideo;
  private int[] mColorSwapBuf = null;
  private int mOldMsg = 0;
  private StatusProgressTask mProgressTask = null;
  public ViewSizes mSurfaceSizes = null;
  private WifiManager.MulticastLock multicastLock = null;
  public Handler photoVisibility = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 1) {
        VideoPlayerFragment.this.Photo.setVisibility(0);
      }
      while (paramAnonymousMessage.what != 0) {
        return;
      }
      VideoPlayerFragment.this.Photo.setVisibility(8);
    }
  };
  private MediaPlayer player = null;
  private PlayerStates player_state = PlayerStates.ReadyForUse;
  private boolean playing = false;
  private PlayerConnectType reconnect_type = PlayerConnectType.Normal;
  public Timer timer;
  private TimerTask timerTask;
  private ToastClass toastClass;
  private Object waitOnMe = new Object();
  
  public static <T> void executeAsyncTask(AsyncTask<T, ?, ?> paramAsyncTask, T... paramVarArgs)
  {
    paramAsyncTask.execute(paramVarArgs);
  }
  
  private String getSDCardPath()
  {
    File localFile = null;
    if (Environment.getExternalStorageState().equals("mounted")) {
      localFile = Environment.getExternalStorageDirectory();
    }
    return localFile.toString();
  }
  
  public static VideoPlayerFragment getVideoPlayerFragment()
  {
    return VideoPlayerFragment;
  }
  
  private void showVideoView()
  {
    this.player.setVisibility(0);
    this.loadVideo.setVisibility(8);
    this.player.getSurfaceView().getHolder().setFormat(-2);
  }
  
  private void startProgressTask(String paramString)
  {
    stopProgressTask();
    this.mProgressTask = new StatusProgressTask(paramString);
    executeAsyncTask(this.mProgressTask, new String[] { paramString });
  }
  
  private void stopProgressTask()
  {
    if (this.mProgressTask != null)
    {
      this.mProgressTask.stopTask();
      this.mProgressTask.cancel(true);
    }
  }
  
  public int OnReceiveData(ByteBuffer paramByteBuffer, int paramInt, long paramLong)
  {
    Log.e("MediaPlayerTest", "Form Native Player OnReceiveData: size: " + paramInt + ", pts: " + paramLong);
    return 0;
  }
  
  public void Record(View paramView)
  {
    if ((this.player == null) || (!this.isPlayer))
    {
      this.toastClass.ToastshowString("视频未连接");
      return;
    }
    boolean bool;
    int i;
    String str;
    if (this.is_record)
    {
      bool = false;
      this.is_record = bool;
      if (!this.is_record) {
        break label174;
      }
      if (this.player == null) {
        break label198;
      }
      i = MediaPlayer.PlayerRecordFlags.forType(MediaPlayer.PlayerRecordFlags.PP_RECORD_AUTO_START) | MediaPlayer.PlayerRecordFlags.forType(MediaPlayer.PlayerRecordFlags.PP_RECORD_DISABLE_AUDIO) | MediaPlayer.PlayerRecordFlags.forType(MediaPlayer.PlayerRecordFlags.PP_RECORD_SPLIT_BY_TIME);
      str = getSDCardPath() + RovInitData.VideoFile_PATH;
      FileDataUtils.makeRootDirectory(str);
      if (!isApm()) {
        break label156;
      }
      this.player.RecordSetup(str, i, 0, 0, "AM");
    }
    for (;;)
    {
      this.player.RecordStart();
      paramView.setBackgroundResource(2130837550);
      this.toastClass.ToastshowString("开始录制");
      return;
      bool = true;
      break;
      label156:
      this.player.RecordSetup(str, i, 0, 0, "PM");
    }
    label174:
    if (this.player != null)
    {
      this.player.RecordStop();
      this.toastClass.ToastshowString("停止录制");
    }
    label198:
    paramView.setBackgroundResource(2130837551);
  }
  
  public void SavePhoto()
  {
    Bitmap localBitmap = shot();
    if (localBitmap != null)
    {
      saveMyBitmap(localBitmap);
      this.Photo.setImageBitmap(localBitmap);
      this.photoVisibility.sendEmptyMessage(1);
      this.loadTime = 15;
      return;
    }
    this.toastClass.ToastshowString("拍照失败");
  }
  
  public int Status(int paramInt)
  {
    MediaPlayer.PlayerNotifyCodes localPlayerNotifyCodes = MediaPlayer.PlayerNotifyCodes.forValue(paramInt);
    if ((this.handler == null) || (localPlayerNotifyCodes == null)) {
      return 0;
    }
    Log.e("MediaPlayerTest", "Form Native Player status: " + paramInt);
    int i = $SWITCH_TABLE$veg$mediaplayer$sdk$MediaPlayer$PlayerNotifyCodes()[MediaPlayer.PlayerNotifyCodes.forValue(paramInt).ordinal()];
    Message localMessage = new Message();
    localMessage.obj = localPlayerNotifyCodes;
    localMessage.arg1 = paramInt;
    this.handler.removeMessages(this.mOldMsg);
    this.mOldMsg = localMessage.what;
    this.handler.sendMessage(localMessage);
    return 0;
  }
  
  public void connectVideo(String paramString)
    throws NullPointerException
  {
    if ((this.player == null) || (paramString.isEmpty())) {
      return;
    }
    this.player.Close();
    MediaPlayerConfig localMediaPlayerConfig = new MediaPlayerConfig();
    localMediaPlayerConfig.setConnectionUrl(paramString);
    localMediaPlayerConfig.setDecoderLatency(0);
    localMediaPlayerConfig.setConnectionNetworkProtocol(1);
    localMediaPlayerConfig.setVolumeBoost(0);
    localMediaPlayerConfig.setFadeOnChangeFFSpeed(0);
    localMediaPlayerConfig.setConnectionBufferingType(1);
    localMediaPlayerConfig.setSynchroNeedDropVideoFrames(0);
    localMediaPlayerConfig.setConnectionBufferingSize(100);
    localMediaPlayerConfig.setConnectionDetectionTime(1000);
    localMediaPlayerConfig.setConnectionBufferingTime(100);
    localMediaPlayerConfig.setDecodingType(1);
    localMediaPlayerConfig.setSynchroEnable(0);
    localMediaPlayerConfig.setEnableAspectRatio(1);
    localMediaPlayerConfig.setDataReceiveTimeout(5000);
    localMediaPlayerConfig.setNumberOfCPUCores(0);
    if (this.is_record)
    {
      int i = MediaPlayer.PlayerRecordFlags.forType(MediaPlayer.PlayerRecordFlags.PP_RECORD_AUTO_START);
      int j = MediaPlayer.PlayerRecordFlags.forType(MediaPlayer.PlayerRecordFlags.PP_RECORD_DISABLE_AUDIO);
      paramString = getSDCardPath() + RovInitData.VideoFile_PATH;
      FileDataUtils.makeRootDirectory(paramString);
      localMediaPlayerConfig.setRecordPath(paramString);
      localMediaPlayerConfig.setRecordFlags(i | j);
      localMediaPlayerConfig.setRecordSplitTime(0);
      localMediaPlayerConfig.setRecordSplitSize(0);
      if (isApm()) {
        localMediaPlayerConfig.setRecordPrefix("AM");
      }
    }
    for (;;)
    {
      Log.v("MediaPlayerTest", "conf record=" + this.is_record);
      this.player.Open(localMediaPlayerConfig, this);
      localMediaPlayerConfig.setMode(MediaPlayer.PlayerModes.PP_MODE_RECORD);
      this.isPlayer = true;
      return;
      localMediaPlayerConfig.setRecordPrefix("PM");
      continue;
      localMediaPlayerConfig.setRecordPath("");
      localMediaPlayerConfig.setRecordFlags(0);
      localMediaPlayerConfig.setRecordSplitTime(0);
      localMediaPlayerConfig.setRecordSplitSize(0);
      if (isApm()) {
        localMediaPlayerConfig.setRecordPrefix("AM");
      } else {
        localMediaPlayerConfig.setRecordPrefix("PM");
      }
    }
  }
  
  public Bitmap getFrameAsBitmap(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    localBitmap.copyPixelsFromBuffer(paramByteBuffer);
    return localBitmap;
  }
  
  public String getRecordPath()
  {
    File localFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "RecordsMediaPlayer");
    if ((!localFile.exists()) && (!localFile.mkdirs()) && (!localFile.isDirectory()))
    {
      Log.e("MediaPlayerTest", "<=getRecordPath() failed to create directory path=" + localFile.getPath());
      return "";
    }
    return localFile.getPath();
  }
  
  public String getSDPath()
  {
    File localFile = null;
    if (Environment.getExternalStorageState().equals("mounted")) {
      localFile = Environment.getExternalStorageDirectory();
    }
    return localFile.toString();
  }
  
  int get_record_flags()
  {
    return MediaPlayer.PlayerRecordFlags.forType(MediaPlayer.PlayerRecordFlags.PP_RECORD_AUTO_START) | MediaPlayer.PlayerRecordFlags.forType(MediaPlayer.PlayerRecordFlags.PP_RECORD_SPLIT_BY_TIME) | MediaPlayer.PlayerRecordFlags.forType(MediaPlayer.PlayerRecordFlags.PP_RECORD_DISABLE_VIDEO);
  }
  
  public boolean isApm()
  {
    long l = System.currentTimeMillis();
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(l);
    localCalendar.get(10);
    return localCalendar.get(9) == 0;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (rootView == null) {
      rootView = paramLayoutInflater.inflate(2130903054, null);
    }
    paramLayoutInflater = (ViewGroup)rootView.getParent();
    if (paramLayoutInflater != null) {
      paramLayoutInflater.removeView(rootView);
    }
    try
    {
      this.toastClass = ToastClass.getToastClass();
      this.player = ((MediaPlayer)rootView.findViewById(2131361853));
      this.loadVideo = ((ProgressBar)rootView.findViewById(2131361855));
      this.Photo = ((ImageView)rootView.findViewById(2131361854));
      this.Photo.setVisibility(8);
      this.player.getSurfaceView().getHolder().setFormat(-2);
    }
    catch (NullPointerException paramLayoutInflater)
    {
      try
      {
        paramLayoutInflater = FileDataUtils.getFileDataUtils();
        paramViewGroup = "rtsp:192.168.99.52/1";
        if (paramLayoutInflater != null) {
          paramViewGroup = paramLayoutInflater.getTagData("视频地址");
        }
      }
      catch (IOException paramLayoutInflater)
      {
        try
        {
          for (;;)
          {
            connectVideo(paramViewGroup);
            VideoPlayerFragment = this;
            this.timerTask = new TimerTask()
            {
              public void run()
              {
                if ((VideoPlayerFragment.this.loadTime == 0) && (VideoPlayerFragment.this.Photo.getVisibility() != 8)) {
                  VideoPlayerFragment.this.photoVisibility.sendEmptyMessage(0);
                }
                while (VideoPlayerFragment.this.loadTime <= 0) {
                  return;
                }
                VideoPlayerFragment localVideoPlayerFragment = VideoPlayerFragment.this;
                localVideoPlayerFragment.loadTime -= 1;
              }
            };
            this.timer = new Timer();
            this.timer.schedule(this.timerTask, 100L, 100L);
            return rootView;
            paramLayoutInflater = paramLayoutInflater;
            this.toastClass = ToastClass.getToastClass(getActivity());
          }
          paramLayoutInflater = paramLayoutInflater;
          paramLayoutInflater = null;
        }
        catch (NullPointerException paramLayoutInflater)
        {
          for (;;) {}
        }
      }
    }
  }
  
  public void onDestroy()
  {
    Log.e("SDL", "onDestroy()");
    if (this.player != null)
    {
      this.player.Stop();
      this.player.onDestroy();
    }
    stopProgressTask();
    System.gc();
    if (this.multicastLock != null)
    {
      this.multicastLock.release();
      this.multicastLock = null;
    }
    this.timer.cancel();
    super.onDestroy();
  }
  
  public void onLowMemory()
  {
    Log.e("SDL", "onLowMemory()");
    super.onLowMemory();
    if (this.player != null) {
      this.player.onLowMemory();
    }
  }
  
  public void onResume()
  {
    Log.e("SDL", "onResume()");
    super.onResume();
    if (this.player != null) {
      this.player.onResume();
    }
  }
  
  public void onStart()
  {
    Log.e("SDL", "onStart()");
    super.onStart();
    if (this.player != null) {
      this.player.onStart();
    }
  }
  
  public void onStop()
  {
    Log.e("SDL", "onStop()");
    super.onStop();
  }
  
  public void resterConnectVideo(boolean paramBoolean)
  {
    try
    {
      FileDataUtils localFileDataUtils = FileDataUtils.getFileDataUtils();
      str = "rtsp:192.168.99.52/1";
      if (localFileDataUtils != null) {
        str = localFileDataUtils.getTagData("视频地址");
      }
      this.loadVideo.setVisibility(0);
    }
    catch (IOException localIOException)
    {
      try
      {
        String str;
        connectVideo(str);
        if (paramBoolean) {
          this.toastClass.ToastshowString("视频重新连接中");
        }
        return;
        localIOException = localIOException;
        Object localObject = null;
      }
      catch (NullPointerException localNullPointerException)
      {
        for (;;) {}
      }
    }
  }
  
  /* Error */
  public void saveMyBitmap(Bitmap paramBitmap)
  {
    // Byte code:
    //   0: new 827	java/text/SimpleDateFormat
    //   3: dup
    //   4: ldc_w 829
    //   7: invokespecial 830	java/text/SimpleDateFormat:<init>	(Ljava/lang/String;)V
    //   10: astore_2
    //   11: aload_2
    //   12: aload_2
    //   13: new 832	java/util/Date
    //   16: dup
    //   17: invokespecial 833	java/util/Date:<init>	()V
    //   20: invokevirtual 837	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   23: invokevirtual 841	java/text/SimpleDateFormat:parse	(Ljava/lang/String;)Ljava/util/Date;
    //   26: astore_3
    //   27: new 392	java/lang/StringBuilder
    //   30: dup
    //   31: aload_0
    //   32: invokevirtual 843	com/deepinfar/rov/VideoPlayerFragment:getSDPath	()Ljava/lang/String;
    //   35: invokestatic 446	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   38: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   41: getstatic 846	com/deepinfar/ProtocolData/RovInitData:PhotoFile_PATH	Ljava/lang/String;
    //   44: invokevirtual 405	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   47: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   50: astore 4
    //   52: aload 4
    //   54: invokestatic 456	com/tools/file/FileDataUtils:makeRootDirectory	(Ljava/lang/String;)V
    //   57: new 346	java/io/File
    //   60: dup
    //   61: new 392	java/lang/StringBuilder
    //   64: dup
    //   65: aload 4
    //   67: invokestatic 446	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   70: invokespecial 396	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   73: aload_2
    //   74: aload_3
    //   75: invokevirtual 837	java/text/SimpleDateFormat:format	(Ljava/util/Date;)Ljava/lang/String;
    //   78: invokevirtual 405	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: ldc_w 848
    //   84: invokevirtual 405	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: invokevirtual 409	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   90: invokespecial 849	java/io/File:<init>	(Ljava/lang/String;)V
    //   93: astore_3
    //   94: aconst_null
    //   95: astore_2
    //   96: new 851	java/io/FileOutputStream
    //   99: dup
    //   100: aload_3
    //   101: invokespecial 854	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   104: astore_3
    //   105: aload_3
    //   106: astore_2
    //   107: aload_1
    //   108: getstatic 860	android/graphics/Bitmap$CompressFormat:JPEG	Landroid/graphics/Bitmap$CompressFormat;
    //   111: bipush 100
    //   113: aload_2
    //   114: invokevirtual 864	android/graphics/Bitmap:compress	(Landroid/graphics/Bitmap$CompressFormat;ILjava/io/OutputStream;)Z
    //   117: pop
    //   118: aload_2
    //   119: invokevirtual 867	java/io/FileOutputStream:flush	()V
    //   122: aload_2
    //   123: invokevirtual 870	java/io/FileOutputStream:close	()V
    //   126: return
    //   127: astore_1
    //   128: ldc_w 872
    //   131: ldc_w 874
    //   134: invokestatic 415	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   137: pop
    //   138: return
    //   139: astore_3
    //   140: aload_3
    //   141: invokevirtual 877	java/io/FileNotFoundException:printStackTrace	()V
    //   144: goto -37 -> 107
    //   147: astore_1
    //   148: aload_1
    //   149: invokevirtual 878	java/io/IOException:printStackTrace	()V
    //   152: goto -30 -> 122
    //   155: astore_1
    //   156: aload_1
    //   157: invokevirtual 878	java/io/IOException:printStackTrace	()V
    //   160: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	161	0	this	VideoPlayerFragment
    //   0	161	1	paramBitmap	Bitmap
    //   10	113	2	localObject1	Object
    //   26	80	3	localObject2	Object
    //   139	2	3	localFileNotFoundException	java.io.FileNotFoundException
    //   50	16	4	str	String
    // Exception table:
    //   from	to	target	type
    //   11	27	127	java/text/ParseException
    //   96	105	139	java/io/FileNotFoundException
    //   118	122	147	java/io/IOException
    //   122	126	155	java/io/IOException
  }
  
  public void setUIDisconnected()
  {
    this.playing = false;
  }
  
  public Bitmap shot()
  {
    MediaPlayer.VideoShot localVideoShot;
    if (this.player != null)
    {
      Log.e("SDL", "getVideoShot()");
      localVideoShot = this.player.getVideoShot(-1, -1);
      if (localVideoShot != null) {}
    }
    else
    {
      return null;
    }
    return getFrameAsBitmap(localVideoShot.getData(), localVideoShot.getWidth(), localVideoShot.getHeight());
  }
  
  private static enum PlayerConnectType
  {
    Normal,  Reconnecting;
  }
  
  private static enum PlayerStates
  {
    Busy,  ReadyForUse;
  }
  
  class Retrievedata
    extends AsyncTask<String, Void, String>
  {
    public String URL = "";
    
    Retrievedata() {}
    
    protected String doInBackground(String... paramVarArgs)
    {
      paramVarArgs = download(new String[] { "http://resitest.cbs.boschsecurity.com/url/URL" });
      Log.v("MediaPlayerTest", "URL RTSP: " + paramVarArgs);
      this.URL = paramVarArgs;
      return paramVarArgs;
    }
    
    /* Error */
    protected String download(String... paramVarArgs)
    {
      // Byte code:
      //   0: new 69	java/net/URL
      //   3: dup
      //   4: aload_1
      //   5: iconst_0
      //   6: aaload
      //   7: invokespecial 70	java/net/URL:<init>	(Ljava/lang/String;)V
      //   10: invokevirtual 74	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   13: checkcast 76	java/net/HttpURLConnection
      //   16: astore_1
      //   17: aload_1
      //   18: invokevirtual 79	java/net/HttpURLConnection:connect	()V
      //   21: aload_1
      //   22: invokevirtual 83	java/net/HttpURLConnection:getResponseCode	()I
      //   25: sipush 200
      //   28: if_icmpeq +35 -> 63
      //   31: new 42	java/lang/StringBuilder
      //   34: dup
      //   35: ldc 85
      //   37: invokespecial 47	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   40: aload_1
      //   41: invokevirtual 83	java/net/HttpURLConnection:getResponseCode	()I
      //   44: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   47: ldc 90
      //   49: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   52: aload_1
      //   53: invokevirtual 93	java/net/HttpURLConnection:getResponseMessage	()Ljava/lang/String;
      //   56: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   59: invokevirtual 55	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   62: areturn
      //   63: aload_1
      //   64: invokevirtual 96	java/net/HttpURLConnection:getContentLength	()I
      //   67: istore_2
      //   68: ldc 40
      //   70: new 42	java/lang/StringBuilder
      //   73: dup
      //   74: ldc 98
      //   76: invokespecial 47	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
      //   79: iload_2
      //   80: invokevirtual 88	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
      //   83: invokevirtual 55	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   86: invokestatic 61	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
      //   89: pop
      //   90: aload_1
      //   91: invokevirtual 102	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   94: astore_1
      //   95: new 104	java/io/BufferedReader
      //   98: dup
      //   99: new 106	java/io/InputStreamReader
      //   102: dup
      //   103: aload_1
      //   104: ldc 108
      //   106: invokespecial 111	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
      //   109: invokespecial 114	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
      //   112: astore_1
      //   113: aload_1
      //   114: invokevirtual 117	java/io/BufferedReader:readLine	()Ljava/lang/String;
      //   117: astore_1
      //   118: aload_1
      //   119: areturn
      //   120: astore_1
      //   121: ldc 40
      //   123: ldc 119
      //   125: invokestatic 61	android/util/Log:v	(Ljava/lang/String;Ljava/lang/String;)I
      //   128: pop
      //   129: aload_1
      //   130: invokevirtual 120	java/lang/Exception:toString	()Ljava/lang/String;
      //   133: areturn
      //   134: astore_1
      //   135: aload_1
      //   136: invokevirtual 123	java/lang/Exception:printStackTrace	()V
      //   139: aconst_null
      //   140: areturn
      //   141: astore_1
      //   142: aload_1
      //   143: invokevirtual 124	java/io/UnsupportedEncodingException:printStackTrace	()V
      //   146: goto -7 -> 139
      //   149: astore_1
      //   150: aload_1
      //   151: invokevirtual 125	java/io/IOException:printStackTrace	()V
      //   154: goto -15 -> 139
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	157	0	this	Retrievedata
      //   0	157	1	paramVarArgs	String[]
      //   67	13	2	i	int
      // Exception table:
      //   from	to	target	type
      //   0	63	120	java/lang/Exception
      //   63	95	120	java/lang/Exception
      //   113	118	134	java/lang/Exception
      //   95	113	141	java/io/UnsupportedEncodingException
      //   113	118	141	java/io/UnsupportedEncodingException
      //   135	139	141	java/io/UnsupportedEncodingException
      //   95	113	149	java/io/IOException
      //   113	118	149	java/io/IOException
      //   135	139	149	java/io/IOException
    }
  }
  
  private class StatusProgressTask
    extends AsyncTask<String, Void, Boolean>
  {
    Rect bounds = new Rect();
    boolean stop = false;
    String strProgressText;
    String strProgressTextSrc;
    
    public StatusProgressTask(String paramString)
    {
      this.strProgressTextSrc = paramString;
    }
    
    protected Boolean doInBackground(String... paramVarArgs)
    {
      int i;
      label63:
      int j;
      for (;;)
      {
        try
        {
          if (this.stop) {
            return Boolean.valueOf(true);
          }
          "Disconnected.....".length();
          this.strProgressText = (this.strProgressTextSrc + "...");
          i = 4;
        }
        catch (Exception paramVarArgs)
        {
          boolean bool;
          continue;
        }
        try
        {
          Thread.sleep(300L);
          bool = this.stop;
          if (bool) {
            return Boolean.valueOf(true);
          }
        }
        catch (InterruptedException paramVarArgs)
        {
          this.stop = true;
          continue;
          if (i <= 3)
          {
            this.strProgressText = this.strProgressTextSrc;
            j = 0;
          }
        }
      }
      for (;;)
      {
        if (this.stop) {
          break label63;
        }
        j = i + 1;
        i = j;
        if (j > 3)
        {
          i = 1;
          this.strProgressText = this.strProgressTextSrc;
        }
        if (!isCancelled()) {
          break;
        }
        break label63;
        do
        {
          this.strProgressText += ".";
          j += 1;
        } while (j < i);
      }
    }
    
    protected void onCancelled()
    {
      super.onCancelled();
    }
    
    protected void onPostExecute(Boolean paramBoolean)
    {
      super.onPostExecute(paramBoolean);
      VideoPlayerFragment.this.mProgressTask = null;
    }
    
    protected void onPreExecute()
    {
      super.onPreExecute();
    }
    
    public void stopTask()
    {
      this.stop = true;
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/VideoPlayerFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */