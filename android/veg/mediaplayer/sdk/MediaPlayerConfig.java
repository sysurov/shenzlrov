package veg.mediaplayer.sdk;

import java.util.ArrayList;

public class MediaPlayerConfig
{
  private static final String TAG = "MediaPlayerConfig";
  private int ConnectionTimeout = 60000;
  private int EnableInterruptOnClose = 1;
  private int aspectRatioMode = 1;
  private int aspectRatioMoveModeX = -1;
  private int aspectRatioMoveModeY = -1;
  private int aspectRatioZoomModePercent = 100;
  private float bogoMIPS = 0.0F;
  private int colorBackground = -16777216;
  private int connectionBufferingSize = 0;
  private int connectionBufferingTime = 1000;
  private int connectionBufferingType = 0;
  private int connectionDetectionTime = 5000;
  private int connectionNetworkProtocol = -1;
  private String connectionUrl = "";
  private int dataReceiveTimeout = 60000;
  private int decoderLatency = 0;
  private int decodingType = 0;
  private int enableABR = 0;
  private int enableAudio = 1;
  private int enableColorVideo = 1;
  private int ext_stream = 0;
  private int fade_on_rate = 1;
  private int fade_on_seek = 1;
  private int fade_on_start = 1;
  private int ff_rate = 0;
  private int nm3u8_id = 0;
  private int numberOfCPUCores = 1;
  private int playerMode = MediaPlayer.PlayerModes.PP_MODE_ALL.val();
  private int record_flags = 0;
  private String record_path = "";
  private String record_prefix = "";
  private int record_split_size = 0;
  private int record_split_time = 0;
  private long record_trim_pos_end = -1L;
  private long record_trim_pos_start = -1L;
  private int rendererType = 1;
  private int selectAudio = 0;
  private int selectSubtitle = -1;
  private String sslKey = "";
  private String startCookies = "";
  private long startOffest = Long.MIN_VALUE;
  private String startPath = "";
  private int startPreroll = 0;
  public ArrayList<String> subtitlePaths = new ArrayList();
  private int synchroEnable = 1;
  private int synchroNeedDropFramesOnFF = 1;
  private int synchroNeedDropVideoFrames = 0;
  private int volume_boost = 0;
  private int volume_detect_max_samples = 0;
  
  public MediaPlayerConfig()
  {
    resetToDefault();
  }
  
  public MediaPlayerConfig(String paramString1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, int paramInt10, int paramInt11, int paramInt12, int paramInt13, int paramInt14, int paramInt15, int paramInt16, int paramInt17, int paramInt18, int paramInt19, int paramInt20, int paramInt21, float paramFloat, String paramString2, long paramLong, int paramInt22, String paramString3, String paramString4, String paramString5, int paramInt23, int paramInt24, int paramInt25, String paramString6, int paramInt26, int paramInt27, int paramInt28)
  {
    this.connectionUrl = paramString1;
    this.connectionNetworkProtocol = paramInt1;
    this.connectionDetectionTime = paramInt2;
    this.connectionBufferingType = paramInt3;
    this.connectionBufferingTime = paramInt4;
    this.connectionBufferingSize = paramInt5;
    this.dataReceiveTimeout = paramInt6;
    this.ConnectionTimeout = paramInt7;
    this.EnableInterruptOnClose = paramInt8;
    this.decodingType = paramInt9;
    this.decoderLatency = paramInt10;
    this.rendererType = paramInt11;
    this.synchroEnable = paramInt12;
    this.synchroNeedDropVideoFrames = paramInt13;
    this.enableColorVideo = paramInt14;
    this.aspectRatioMode = paramInt15;
    this.aspectRatioZoomModePercent = paramInt16;
    this.aspectRatioMoveModeX = paramInt17;
    this.aspectRatioMoveModeY = paramInt18;
    this.enableAudio = paramInt19;
    this.colorBackground = paramInt20;
    this.numberOfCPUCores = paramInt21;
    this.bogoMIPS = paramFloat;
    this.sslKey = paramString2;
    this.startOffest = paramLong;
    this.startPreroll = paramInt22;
    this.startPath = paramString3;
    this.startCookies = paramString4;
    this.record_path = paramString5;
    this.record_flags = paramInt23;
    this.record_split_time = paramInt24;
    this.record_split_size = paramInt25;
    this.record_prefix = paramString6;
    this.selectAudio = paramInt26;
    this.selectSubtitle = paramInt27;
    this.enableABR = paramInt28;
  }
  
  public MediaPlayerConfig(MediaPlayerConfig paramMediaPlayerConfig)
  {
    this.connectionUrl = paramMediaPlayerConfig.connectionUrl;
    this.connectionNetworkProtocol = paramMediaPlayerConfig.connectionNetworkProtocol;
    this.connectionDetectionTime = paramMediaPlayerConfig.connectionDetectionTime;
    this.connectionBufferingType = paramMediaPlayerConfig.connectionBufferingType;
    this.connectionBufferingTime = paramMediaPlayerConfig.connectionBufferingTime;
    this.connectionBufferingSize = paramMediaPlayerConfig.connectionBufferingSize;
    this.dataReceiveTimeout = paramMediaPlayerConfig.dataReceiveTimeout;
    this.ConnectionTimeout = paramMediaPlayerConfig.ConnectionTimeout;
    this.EnableInterruptOnClose = paramMediaPlayerConfig.EnableInterruptOnClose;
    this.decodingType = paramMediaPlayerConfig.decodingType;
    this.decoderLatency = paramMediaPlayerConfig.decoderLatency;
    this.rendererType = paramMediaPlayerConfig.rendererType;
    this.synchroEnable = paramMediaPlayerConfig.synchroEnable;
    this.synchroNeedDropVideoFrames = paramMediaPlayerConfig.synchroNeedDropVideoFrames;
    this.enableColorVideo = paramMediaPlayerConfig.enableColorVideo;
    this.aspectRatioMode = paramMediaPlayerConfig.aspectRatioMode;
    this.aspectRatioZoomModePercent = paramMediaPlayerConfig.aspectRatioZoomModePercent;
    this.aspectRatioMoveModeX = paramMediaPlayerConfig.aspectRatioMoveModeX;
    this.aspectRatioMoveModeY = paramMediaPlayerConfig.aspectRatioMoveModeY;
    this.enableAudio = paramMediaPlayerConfig.enableAudio;
    this.colorBackground = paramMediaPlayerConfig.colorBackground;
    this.numberOfCPUCores = paramMediaPlayerConfig.numberOfCPUCores;
    this.bogoMIPS = paramMediaPlayerConfig.bogoMIPS;
    this.sslKey = paramMediaPlayerConfig.sslKey;
    this.startOffest = paramMediaPlayerConfig.startOffest;
    this.startPreroll = paramMediaPlayerConfig.startPreroll;
    this.startPath = paramMediaPlayerConfig.startPath;
    this.startCookies = paramMediaPlayerConfig.startCookies;
    this.ext_stream = paramMediaPlayerConfig.ext_stream;
    this.nm3u8_id = paramMediaPlayerConfig.nm3u8_id;
    this.record_path = paramMediaPlayerConfig.record_path;
    this.record_flags = paramMediaPlayerConfig.record_flags;
    this.record_split_time = paramMediaPlayerConfig.record_split_time;
    this.record_split_size = paramMediaPlayerConfig.record_split_size;
    this.record_prefix = paramMediaPlayerConfig.record_prefix;
    this.record_trim_pos_start = paramMediaPlayerConfig.record_trim_pos_start;
    this.record_trim_pos_end = paramMediaPlayerConfig.record_trim_pos_end;
    this.selectAudio = paramMediaPlayerConfig.selectAudio;
    this.selectSubtitle = paramMediaPlayerConfig.selectSubtitle;
    this.subtitlePaths = paramMediaPlayerConfig.subtitlePaths;
    this.ff_rate = paramMediaPlayerConfig.ff_rate;
    this.volume_detect_max_samples = paramMediaPlayerConfig.volume_detect_max_samples;
    this.volume_boost = paramMediaPlayerConfig.volume_boost;
    this.playerMode = paramMediaPlayerConfig.playerMode;
    this.enableABR = paramMediaPlayerConfig.enableABR;
  }
  
  public int getAspectRatioMode()
  {
    return this.aspectRatioMode;
  }
  
  public int getAspectRatioMoveModeX()
  {
    return this.aspectRatioMoveModeX;
  }
  
  public int getAspectRatioMoveModeY()
  {
    return this.aspectRatioMoveModeY;
  }
  
  public int getAspectRatioZoomModePercent()
  {
    return this.aspectRatioZoomModePercent;
  }
  
  public float getBogoMIPS()
  {
    return this.bogoMIPS;
  }
  
  public int getColorBackground()
  {
    return this.colorBackground;
  }
  
  public int getConnectionBufferingSize()
  {
    return this.connectionBufferingSize;
  }
  
  public int getConnectionBufferingTime()
  {
    return this.connectionBufferingTime;
  }
  
  public int getConnectionBufferingType()
  {
    return this.connectionBufferingType;
  }
  
  public int getConnectionDetectionTime()
  {
    return this.connectionDetectionTime;
  }
  
  public int getConnectionNetworkProtocol()
  {
    return this.connectionNetworkProtocol;
  }
  
  public int getConnectionTimeout()
  {
    return this.ConnectionTimeout;
  }
  
  public String getConnectionUrl()
  {
    return this.connectionUrl;
  }
  
  public int getDataReceiveTimeout()
  {
    return this.dataReceiveTimeout;
  }
  
  public int getDecoderLatency()
  {
    return this.decoderLatency;
  }
  
  public int getDecodingType()
  {
    return this.decodingType;
  }
  
  public int getDropOnFastPlayback()
  {
    return this.synchroNeedDropFramesOnFF;
  }
  
  public int getEnableABR()
  {
    return this.enableABR;
  }
  
  public int getEnableAspectRatio()
  {
    return this.aspectRatioMode;
  }
  
  public int getEnableAudio()
  {
    return this.enableAudio;
  }
  
  public int getEnableColorVideo()
  {
    return this.enableColorVideo;
  }
  
  public int getExtStream()
  {
    return this.ext_stream;
  }
  
  public int getFFRate()
  {
    return this.ff_rate;
  }
  
  public int getFadeOnChangeFFSpeed()
  {
    return this.fade_on_rate;
  }
  
  public int getFadeOnSeek()
  {
    return this.fade_on_seek;
  }
  
  public int getFadeOnStart()
  {
    return this.fade_on_start;
  }
  
  public int getInterruptOnClose()
  {
    return this.EnableInterruptOnClose;
  }
  
  public int getM3U8Id()
  {
    return this.nm3u8_id;
  }
  
  public int getMode()
  {
    return this.playerMode;
  }
  
  public int getNumberOfCPUCores()
  {
    return this.numberOfCPUCores;
  }
  
  public int getRecordFlags()
  {
    return this.record_flags;
  }
  
  public String getRecordPath()
  {
    return this.record_path;
  }
  
  public String getRecordPrefix()
  {
    return this.record_prefix;
  }
  
  public int getRecordSplitSize()
  {
    return this.record_split_size;
  }
  
  public int getRecordSplitTime()
  {
    return this.record_split_time;
  }
  
  public long getRecordTrimPosEnd()
  {
    return this.record_trim_pos_end;
  }
  
  public long getRecordTrimPosStart()
  {
    return this.record_trim_pos_start;
  }
  
  public int getRendererType()
  {
    return this.rendererType;
  }
  
  public int getSelectedAudio()
  {
    return this.selectAudio;
  }
  
  public int getSelectedSubtitle()
  {
    return this.selectSubtitle;
  }
  
  public String getSslKey()
  {
    return this.sslKey;
  }
  
  public String getStartCookies()
  {
    return this.startCookies;
  }
  
  public long getStartOffest()
  {
    return this.startOffest;
  }
  
  public String getStartPath()
  {
    return this.startPath;
  }
  
  public int getStartPreroll()
  {
    return this.startPreroll;
  }
  
  public int getSynchroEnable()
  {
    return this.synchroEnable;
  }
  
  public int getSynchroNeedDropVideoFrames()
  {
    return this.synchroNeedDropVideoFrames;
  }
  
  public int getVolumeBoost()
  {
    return this.volume_boost;
  }
  
  public int getVolumeDetectMaxSamples()
  {
    return this.volume_detect_max_samples;
  }
  
  public void print() {}
  
  public void resetToDefault()
  {
    this.connectionUrl = "";
    this.connectionNetworkProtocol = -1;
    this.connectionDetectionTime = 5000;
    this.connectionBufferingType = 0;
    this.connectionBufferingTime = 3000;
    this.connectionBufferingSize = 0;
    this.dataReceiveTimeout = 30000;
    this.ConnectionTimeout = 60000;
    this.EnableInterruptOnClose = 1;
    this.decodingType = 0;
    this.decoderLatency = 0;
    this.rendererType = 1;
    this.synchroEnable = 1;
    this.synchroNeedDropVideoFrames = 0;
    this.synchroNeedDropFramesOnFF = 1;
    this.enableColorVideo = 1;
    this.aspectRatioMode = 1;
    this.aspectRatioZoomModePercent = 100;
    this.aspectRatioMoveModeX = -1;
    this.aspectRatioMoveModeY = -1;
    this.enableAudio = 1;
    this.colorBackground = -16777216;
    this.numberOfCPUCores = 0;
    this.bogoMIPS = 0.0F;
    this.sslKey = "";
    this.ext_stream = 0;
    this.nm3u8_id = 0;
    this.startOffest = Long.MIN_VALUE;
    this.startPreroll = 0;
    this.startPath = "";
    this.startCookies = "";
    this.ff_rate = 0;
    this.volume_detect_max_samples = 0;
    this.volume_boost = 0;
    this.fade_on_start = 1;
    this.fade_on_seek = 1;
    this.fade_on_rate = 1;
    this.record_path = "";
    this.record_flags = 0;
    this.record_split_time = 0;
    this.record_split_size = 0;
    this.record_prefix = "";
    this.record_trim_pos_start = -1L;
    this.record_trim_pos_end = -1L;
    this.selectAudio = 0;
    this.selectSubtitle = -1;
    this.subtitlePaths.clear();
    this.playerMode = MediaPlayer.PlayerModes.PP_MODE_ALL.val();
    this.enableABR = 0;
  }
  
  public void setAspectRatioMode(int paramInt)
  {
    this.aspectRatioMode = paramInt;
  }
  
  public void setAspectRatioMoveModeX(int paramInt)
  {
    this.aspectRatioMoveModeX = paramInt;
  }
  
  public void setAspectRatioMoveModeY(int paramInt)
  {
    this.aspectRatioMoveModeY = paramInt;
  }
  
  public void setAspectRatioZoomModePercent(int paramInt)
  {
    this.aspectRatioZoomModePercent = paramInt;
  }
  
  public void setBogoMIPS(float paramFloat)
  {
    this.bogoMIPS = paramFloat;
  }
  
  public void setColorBackground(int paramInt)
  {
    this.colorBackground = paramInt;
  }
  
  public void setConnectionBufferingSize(int paramInt)
  {
    this.connectionBufferingSize = paramInt;
  }
  
  public void setConnectionBufferingTime(int paramInt)
  {
    this.connectionBufferingTime = paramInt;
  }
  
  public void setConnectionBufferingType(int paramInt)
  {
    this.connectionBufferingType = paramInt;
  }
  
  public void setConnectionDetectionTime(int paramInt)
  {
    this.connectionDetectionTime = paramInt;
  }
  
  public void setConnectionNetworkProtocol(int paramInt)
  {
    this.connectionNetworkProtocol = paramInt;
  }
  
  public void setConnectionTimeout(int paramInt)
  {
    this.ConnectionTimeout = paramInt;
  }
  
  public void setConnectionUrl(String paramString)
  {
    this.connectionUrl = paramString;
  }
  
  public void setDataReceiveTimeout(int paramInt)
  {
    this.dataReceiveTimeout = paramInt;
  }
  
  public void setDecoderLatency(int paramInt)
  {
    this.decoderLatency = paramInt;
  }
  
  public void setDecodingType(int paramInt)
  {
    this.decodingType = paramInt;
  }
  
  public void setDropOnFastPlayback(int paramInt)
  {
    this.synchroNeedDropFramesOnFF = paramInt;
  }
  
  public void setEnableABR(int paramInt)
  {
    this.enableABR = paramInt;
  }
  
  public void setEnableAspectRatio(int paramInt)
  {
    this.aspectRatioMode = paramInt;
  }
  
  public void setEnableAudio(int paramInt)
  {
    this.enableAudio = paramInt;
  }
  
  public void setEnableColorVideo(int paramInt)
  {
    this.enableColorVideo = paramInt;
  }
  
  public void setExtStream(int paramInt)
  {
    this.ext_stream = paramInt;
  }
  
  public void setFFRate(int paramInt)
  {
    this.ff_rate = paramInt;
  }
  
  public void setFadeOnChangeFFSpeed(int paramInt)
  {
    this.fade_on_rate = paramInt;
  }
  
  public void setFadeOnSeek(int paramInt)
  {
    this.fade_on_seek = paramInt;
  }
  
  public void setFadeOnStart(int paramInt)
  {
    this.fade_on_start = paramInt;
  }
  
  public void setInterruptOnClose(int paramInt)
  {
    this.EnableInterruptOnClose = paramInt;
  }
  
  public void setM3U8Id(int paramInt)
  {
    this.nm3u8_id = paramInt;
  }
  
  public void setMode(int paramInt)
  {
    this.playerMode = paramInt;
  }
  
  public void setMode(MediaPlayer.PlayerModes paramPlayerModes)
  {
    this.playerMode = paramPlayerModes.val();
  }
  
  public void setNumberOfCPUCores(int paramInt)
  {
    this.numberOfCPUCores = paramInt;
  }
  
  public void setRecordFlags(int paramInt)
  {
    this.record_flags = paramInt;
  }
  
  public void setRecordPath(String paramString)
  {
    this.record_path = paramString;
  }
  
  public void setRecordPrefix(String paramString)
  {
    this.record_prefix = paramString;
  }
  
  public void setRecordSplitSize(int paramInt)
  {
    this.record_split_size = paramInt;
  }
  
  public void setRecordSplitTime(int paramInt)
  {
    this.record_split_time = paramInt;
  }
  
  public void setRecordTrimPosEnd(long paramLong)
  {
    this.record_trim_pos_end = paramLong;
  }
  
  public void setRecordTrimPosStart(long paramLong)
  {
    this.record_trim_pos_start = paramLong;
  }
  
  public void setRendererType(int paramInt)
  {
    this.rendererType = paramInt;
  }
  
  public void setSelectedAudio(int paramInt)
  {
    this.selectAudio = paramInt;
  }
  
  public void setSelectedSubtitle(int paramInt)
  {
    this.selectSubtitle = paramInt;
  }
  
  public void setSslKey(String paramString)
  {
    this.sslKey = paramString;
  }
  
  public void setStartCookies(String paramString)
  {
    this.startCookies = paramString;
  }
  
  public void setStartOffest(long paramLong)
  {
    this.startOffest = paramLong;
  }
  
  public void setStartPath(String paramString)
  {
    this.startPath = paramString;
  }
  
  public void setStartPreroll(int paramInt)
  {
    this.startPreroll = paramInt;
  }
  
  public void setSynchroEnable(int paramInt)
  {
    this.synchroEnable = paramInt;
  }
  
  public void setSynchroNeedDropVideoFrames(int paramInt)
  {
    this.synchroNeedDropVideoFrames = paramInt;
  }
  
  public void setVolumeBoost(int paramInt)
  {
    this.volume_boost = paramInt;
  }
  
  public void setVolumeDetectMaxSamples(int paramInt)
  {
    this.volume_detect_max_samples = paramInt;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/veg/mediaplayer/sdk/MediaPlayerConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */