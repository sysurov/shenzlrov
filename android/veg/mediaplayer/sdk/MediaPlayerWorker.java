package veg.mediaplayer.sdk;

import android.graphics.Color;
import android.os.Build.VERSION;
import android.view.SurfaceView;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

class MediaPlayerWorker
  implements Runnable
{
  private static final String TAG = "MediaPlayerWorker";
  public volatile boolean finish = false;
  public boolean isPreview = false;
  private MediaPlayer owner = null;
  public long[] player_inst = new long[1];
  
  public MediaPlayerWorker(MediaPlayer paramMediaPlayer, boolean paramBoolean)
  {
    this.player_inst[0] = 0L;
    this.isPreview = paramBoolean;
    this.owner = paramMediaPlayer;
  }
  
  public void run()
  {
    this.owner.waitStartOpenThread.notify("Notify start open thread... ");
    if ((this.owner.mIS_WINDOW) && (!this.owner.mUseExternalSurface)) {
      this.owner.waitSurfaceCreated.wait("Wait surface created... ");
    }
    int i;
    if (this.owner.playerConfig.getConnectionUrl().contains(".m3u8"))
    {
      this.owner.abrGetHLSStreams();
      i = 20;
      if ((!this.finish) && (this.owner.abrHLSStreams == null) && (i > 0)) {
        break label320;
      }
      if (this.owner.playerConfig.getM3U8Id() < 0) {
        this.owner.playerConfig.setM3U8Id(0);
      }
      this.owner.abrCurrentPlayedStreamId = this.owner.playerConfig.getM3U8Id();
      if ((this.owner.abrHLSStreams == null) || (this.owner.abrHLSStreams.size() <= this.owner.abrCurrentPlayedStreamId)) {
        break label368;
      }
      this.owner.playerConfig.setExtStream(((M3U8.HLSStream)this.owner.abrHLSStreams.get(this.owner.abrCurrentPlayedStreamId)).ext_stream);
    }
    for (;;)
    {
      this.owner.nativePlayerInit(this.player_inst, this.owner);
      if ((this.player_inst[0] != 0L) && (!this.finish)) {
        break label382;
      }
      this.owner.waitOpenSource.notify("Open source notify.. ");
      this.owner.waitOpenMediaCodec.notify("Open mediacodec notify.. ");
      this.owner.nativePlayerUninit(this.player_inst);
      this.player_inst[0] = 0L;
      this.finish = false;
      this.owner.closeMediaCodec();
      this.owner.queueSurfaceCreate.clear();
      this.owner.mPlayerThread = null;
      this.owner.abrClose();
      return;
      try
      {
        label320:
        synchronized (this.owner.waitGetHLSStreams)
        {
          this.owner.waitGetHLSStreams.wait(100L);
          i -= 1;
        }
      }
      catch (InterruptedException localInterruptedException1) {}
      label368:
      this.owner.playerConfig.setExtStream(0);
    }
    label382:
    if (this.owner.Callback != null) {
      this.owner.nativePlayerSetCallback(this.player_inst, this.owner.Callback);
    }
    this.owner.nativePlayerSetOptions(this.player_inst, this.owner.playerConfig.getConnectionNetworkProtocol(), this.owner.playerConfig.getConnectionDetectionTime(), this.owner.playerConfig.getConnectionBufferingType(), this.owner.playerConfig.getConnectionBufferingTime(), this.owner.playerConfig.getConnectionBufferingSize(), this.owner.playerConfig.getConnectionTimeout(), this.owner.playerConfig.getInterruptOnClose(), this.owner.playerConfig.getDecodingType(), this.owner.playerConfig.getDecoderLatency(), this.owner.playerConfig.getRendererType(), this.owner.playerConfig.getSynchroEnable(), this.owner.playerConfig.getSynchroNeedDropVideoFrames(), this.owner.playerConfig.getDropOnFastPlayback(), this.owner.playerConfig.getEnableColorVideo(), this.owner.playerConfig.getEnableAspectRatio(), this.owner.playerConfig.getAspectRatioZoomModePercent(), this.owner.playerConfig.getAspectRatioMoveModeX(), this.owner.playerConfig.getAspectRatioMoveModeY(), this.owner.playerConfig.getNumberOfCPUCores(), (int)this.owner.playerConfig.getBogoMIPS(), Color.red(this.owner.playerConfig.getColorBackground()), Color.green(this.owner.playerConfig.getColorBackground()), Color.blue(this.owner.playerConfig.getColorBackground()), Color.alpha(this.owner.playerConfig.getColorBackground()), this.owner.playerConfig.getSslKey(), this.owner.playerConfig.getStartOffest(), this.owner.playerConfig.getStartPreroll(), this.owner.playerConfig.getStartPath(), this.owner.playerConfig.getStartCookies(), this.owner.playerConfig.getExtStream());
    this.owner.nativePlayerRecordSetOptions(this.player_inst, this.owner.playerConfig.getRecordPath(), this.owner.playerConfig.getRecordFlags(), this.owner.playerConfig.getRecordSplitTime(), this.owner.playerConfig.getRecordSplitSize(), this.owner.playerConfig.getRecordPrefix());
    this.owner.nativePlayerAudioSelect(this.player_inst, this.owner.playerConfig.getSelectedAudio());
    if (this.owner.playerConfig.getFFRate() != 0) {
      this.owner.nativePlayerSetFFRate(this.player_inst, this.owner.playerConfig.getFFRate());
    }
    if (this.owner.playerConfig.getVolumeDetectMaxSamples() != 0) {
      this.owner.nativePlayerStartVolumeDetect(this.player_inst, this.owner.playerConfig.getVolumeDetectMaxSamples());
    }
    if (this.owner.playerConfig.getVolumeBoost() != 0) {
      this.owner.nativePlayerSetVolumeBoost(this.player_inst, this.owner.playerConfig.getVolumeBoost());
    }
    Object localObject1;
    if (this.owner.playerConfig.getMode() == MediaPlayer.PlayerModes.PP_MODE_RECORD.val())
    {
      this.owner.nativePlayerSetRecordOnly(this.player_inst, 1);
      this.owner.nativePlayerRecordSetTrimPositions(this.player_inst, this.owner.playerConfig.getRecordTrimPosStart(), this.owner.playerConfig.getRecordTrimPosEnd());
      if (this.owner.playerConfig.getFadeOnStart() == 1) {
        this.owner.m_fade_time = (System.nanoTime() + 200000000L);
      }
      localObject1 = this.owner.playerConfig.subtitlePaths.iterator();
      label1032:
      if (((Iterator)localObject1).hasNext()) {
        break label1312;
      }
      this.owner.nativePlayerSubtitleSelect(this.player_inst, this.owner.playerConfig.getSelectedSubtitle());
      if (!this.isPreview) {
        break label1337;
      }
    }
    label1312:
    label1337:
    for (long l = this.owner.nativePlayerOpenAsPreview(this.player_inst, this.owner.playerConfig.getConnectionUrl(), 0, this.owner.playerConfig.getDataReceiveTimeout());; l = this.owner.nativePlayerOpen(this.player_inst, this.owner.playerConfig.getConnectionUrl(), 0, this.owner.playerConfig.getDataReceiveTimeout()))
    {
      if ((l == 0L) && (!this.finish)) {
        break label1587;
      }
      this.owner.waitOpenSource.notify("Open source notify.. ");
      this.owner.waitOpenMediaCodec.notify("Open mediacodec notify.. ");
      this.owner.nativePlayerClose(this.player_inst);
      this.owner.nativePlayerUninit(this.player_inst);
      this.player_inst[0] = 0L;
      this.finish = false;
      this.owner.closeMediaCodec();
      this.owner.queueSurfaceCreate.clear();
      this.owner.mPlayerThread = null;
      this.owner.abrClose();
      return;
      if ((this.owner.playerConfig.getMode() & MediaPlayer.PlayerModes.PP_MODE_RECORD.val()) == 0) {
        this.owner.nativePlayerSetRecordOnly(this.player_inst, 2);
      }
      if ((this.owner.mIS_WINDOW) || (this.owner.mUseExternalSurface) || (this.owner.mSurface != null) || (this.owner.playerConfig.getMode() != MediaPlayer.PlayerModes.PP_MODE_AUDIO.val())) {
        break;
      }
      this.owner.nativePlayerSetAudioOnly(this.player_inst, 1);
      break;
      String str = (String)((Iterator)localObject1).next();
      this.owner.SubtitleSourceAdd(str);
      break label1032;
    }
    for (;;)
    {
      try
      {
        Thread.sleep(200L);
        if (!this.finish) {
          if (this.owner.nativePlayerIsPlaying(this.player_inst) == 0) {
            continue;
          }
        }
      }
      catch (InterruptedException localInterruptedException2)
      {
        this.finish = true;
        continue;
        if (this.owner.abrSetPlayedStreamId == this.owner.abrCurrentPlayedStreamId) {
          continue;
        }
        this.owner.abrChangeStream(this.owner.abrSetPlayedStreamId);
        if (this.owner.playerConfig.getEnableABR() != 1) {
          break label1587;
        }
        this.owner.abrCheckBitrateAndChangeStream();
      }
      localObject1 = this.owner.getSurfaceView();
      if (localObject1 != null) {
        ((SurfaceView)localObject1).post(new Runnable()
        {
          public void run()
          {
            if (Build.VERSION.SDK_INT >= 11) {
              MediaPlayerWorker.this.owner.jb_setalpha(0.0F);
            }
          }
        });
      }
      this.owner.abrClose();
      this.owner.waitOpenSource.notify("Open source notify.. ");
      this.owner.waitOpenMediaCodec.notify("Open mediacodec notify.. ");
      this.owner.nativePlayerClose(this.player_inst);
      this.owner.nativePlayerUninit(this.player_inst);
      this.player_inst[0] = 0L;
      this.finish = false;
      this.owner.closeMediaCodec();
      this.owner.queueSurfaceCreate.clear();
      this.owner.mPlayerThread = null;
      return;
      label1587:
      if (!this.finish) {}
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/veg/mediaplayer/sdk/MediaPlayerWorker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */