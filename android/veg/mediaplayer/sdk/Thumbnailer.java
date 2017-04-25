package veg.mediaplayer.sdk;

import android.content.Context;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class Thumbnailer
{
  private final String TAG = "Thumbnailer(" + hashCode() + ")";
  protected transient Context context;
  private int heightLayout = 0;
  protected float mHeight;
  private int mVideoHeight = 0;
  private int mVideoWidth = 0;
  protected float mWidth;
  protected ByteBuffer outbuff = null;
  protected ThumbnailerState state = ThumbnailerState.Closed;
  private ThumbnailFrame thumbFrame = null;
  protected ThumbnailerConfig thumbnailerConfig = null;
  protected Thread thumbnailerThread = null;
  protected ThumbnailerWorker thumbnailerWorker = null;
  protected SystemUtils.WaitNotify waitOpen = new SystemUtils.WaitNotify();
  protected SystemUtils.WaitNotify waitStartClose = new SystemUtils.WaitNotify();
  private int widthLayout = 0;
  
  static {}
  
  public Thumbnailer(Context paramContext)
  {
    this.context = paramContext;
    this.thumbnailerConfig = new ThumbnailerConfig();
  }
  
  public void Close()
  {
    if (this.state == ThumbnailerState.Closed)
    {
      this.thumbnailerThread = null;
      this.thumbnailerWorker = null;
      this.thumbFrame = null;
      return;
    }
    this.state = ThumbnailerState.Closing;
    if ((this.thumbnailerThread == null) || (this.thumbnailerWorker == null) || (this.state == ThumbnailerState.Closed))
    {
      this.waitStartClose.notify("waitStartClose notify.. ");
      this.thumbnailerThread = null;
      this.thumbnailerWorker = null;
      this.thumbFrame = null;
      this.state = ThumbnailerState.Closed;
      return;
    }
    if ((!this.thumbnailerThread.isAlive()) && (this.thumbnailerWorker != null) && (this.thumbnailerWorker.thumbnailer_inst == 0))
    {
      this.waitStartClose.notify("waitStartClose notify.. ");
      this.thumbnailerThread = null;
      this.thumbnailerWorker = null;
      this.thumbFrame = null;
      this.state = ThumbnailerState.Closed;
      return;
    }
    if ((this.thumbnailerWorker != null) && (this.thumbnailerWorker.thumbnailer_inst != 0)) {
      nativeThumbnailerInterrupt(this.thumbnailerWorker.thumbnailer_inst);
    }
    this.waitStartClose.notify("waitStartClose notify.. ");
    this.thumbnailerWorker.finish = true;
    if ((this.state != ThumbnailerState.Closed) && (this.state != ThumbnailerState.Closing)) {
      nativeThumbnailerInterrupt(this.thumbnailerWorker.thumbnailer_inst);
    }
    try
    {
      this.thumbnailerThread.join();
      this.thumbnailerThread = null;
      this.thumbnailerWorker = null;
      this.thumbFrame = null;
      this.state = ThumbnailerState.Closed;
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      for (;;)
      {
        localInterruptedException.printStackTrace();
      }
    }
  }
  
  public Object Open(String paramString)
  {
    if (this.thumbnailerThread != null) {
      Close();
    }
    if (this.thumbnailerThread == null)
    {
      this.thumbnailerWorker = new ThumbnailerWorker(this, this.TAG);
      this.thumbnailerWorker.finish = false;
      this.thumbnailerConfig.setConnectionUrl(paramString);
      if ((this.thumbnailerConfig.getConnectionUrl() != null) && (!this.thumbnailerConfig.getConnectionUrl().isEmpty()) && (this.thumbnailerConfig.getConnectionUrl().indexOf("mms://") == 0)) {
        this.thumbnailerConfig.setConnectionUrl(this.thumbnailerConfig.getConnectionUrl().replace("mms://", "mmsh://"));
      }
      this.thumbnailerConfig.setNumberOfCPUCores(1);
      this.state = ThumbnailerState.Opening;
      this.thumbnailerThread = new Thread(this.thumbnailerWorker, "ThumbnailerThread");
      this.thumbnailerThread.start();
    }
    return this.waitOpen.getObject();
  }
  
  public Object Open(ThumbnailerConfig paramThumbnailerConfig)
  {
    if (this.thumbnailerThread != null) {
      Close();
    }
    if (this.thumbnailerThread == null)
    {
      if (paramThumbnailerConfig != null) {
        this.thumbnailerConfig = new ThumbnailerConfig(paramThumbnailerConfig);
      }
      this.thumbnailerWorker = new ThumbnailerWorker(this, this.TAG);
      this.thumbnailerWorker.finish = false;
      if ((this.thumbnailerConfig.getConnectionUrl() != null) && (!this.thumbnailerConfig.getConnectionUrl().isEmpty()) && (this.thumbnailerConfig.getConnectionUrl().indexOf("mms://") == 0)) {
        this.thumbnailerConfig.setConnectionUrl(this.thumbnailerConfig.getConnectionUrl().replace("mms://", "mmsh://"));
      }
      this.state = ThumbnailerState.Opening;
      this.thumbnailerThread = new Thread(this.thumbnailerWorker, "ThumbnailerThread");
      this.thumbnailerThread.start();
    }
    return this.waitOpen.getObject();
  }
  
  public ThumbnailFrame getFrame()
  {
    if ((this.thumbnailerWorker == null) || (this.thumbnailerWorker.thumbnailer_inst == 0) || (getState() != ThumbnailerState.Opened)) {}
    for (;;)
    {
      return null;
      int i = this.thumbnailerConfig.getOutWidth() * this.thumbnailerConfig.getOutHeight() * 4;
      if (i < 4) {
        continue;
      }
      if (this.outbuff == null)
      {
        this.outbuff = ByteBuffer.allocateDirect(i);
        this.outbuff.order(ByteOrder.nativeOrder());
      }
      ??? = new int[1];
      ???[0] = 0;
      int[] arrayOfInt = new int[1];
      arrayOfInt[0] = 0;
      i = nativeThumbnailerGetFrame(this.thumbnailerWorker.thumbnailer_inst, this.outbuff, (int[])???, arrayOfInt);
      if (i > 0)
      {
        if (this.thumbFrame == null) {
          this.thumbFrame = new ThumbnailFrame();
        }
        this.thumbFrame.setWidth(???[0]);
        this.thumbFrame.setHeight(arrayOfInt[0]);
        if (this.outbuff == null) {}
      }
      synchronized (this.outbuff)
      {
        if (this.outbuff != null) {
          this.outbuff.limit(i);
        }
        if (this.outbuff != null) {
          this.thumbFrame.setData(this.outbuff.slice());
        }
        if (i <= 0) {
          continue;
        }
        return this.thumbFrame;
      }
    }
  }
  
  public String getInfo()
  {
    if ((this.thumbnailerWorker == null) || (this.thumbnailerWorker.thumbnailer_inst == 0) || (getState() != ThumbnailerState.Opened)) {
      return "";
    }
    return nativeThumbnailerGetInfo(this.thumbnailerWorker.thumbnailer_inst);
  }
  
  public ThumbnailerState getState()
  {
    return this.state;
  }
  
  public native int nativeThumbnailerClose(int paramInt);
  
  public native int nativeThumbnailerGetFrame(int paramInt, ByteBuffer paramByteBuffer, int[] paramArrayOfInt1, int[] paramArrayOfInt2);
  
  public native String nativeThumbnailerGetInfo(int paramInt);
  
  public native int nativeThumbnailerInit(Thumbnailer paramThumbnailer);
  
  public native int nativeThumbnailerInterrupt(int paramInt);
  
  public native int nativeThumbnailerOpen(int paramInt1, String paramString, int paramInt2, int paramInt3);
  
  public native int nativeThumbnailerUninit(int paramInt);
  
  public class ThumbnailFrame
  {
    private int height = 0;
    private ByteBuffer outbuff = null;
    private int width = 0;
    
    public ThumbnailFrame() {}
    
    public ByteBuffer getData()
    {
      return this.outbuff;
    }
    
    public int getHeight()
    {
      return this.height;
    }
    
    public int getWidth()
    {
      return this.width;
    }
    
    public void setData(ByteBuffer paramByteBuffer)
    {
      this.outbuff = paramByteBuffer;
    }
    
    public void setHeight(int paramInt)
    {
      this.height = paramInt;
    }
    
    public void setWidth(int paramInt)
    {
      this.width = paramInt;
    }
  }
  
  public static enum ThumbnailerState
  {
    private static final Map<Integer, ThumbnailerState> typesByValue;
    private final int value;
    
    static
    {
      int i = 0;
      Opening = new ThumbnailerState("Opening", 0, 0);
      Opened = new ThumbnailerState("Opened", 1, 1);
      Closing = new ThumbnailerState("Closing", 2, 2);
      Closed = new ThumbnailerState("Closed", 3, 3);
      ENUM$VALUES = new ThumbnailerState[] { Opening, Opened, Closing, Closed };
      typesByValue = new HashMap();
      ThumbnailerState[] arrayOfThumbnailerState = values();
      int j = arrayOfThumbnailerState.length;
      for (;;)
      {
        if (i >= j) {
          return;
        }
        ThumbnailerState localThumbnailerState = arrayOfThumbnailerState[i];
        typesByValue.put(Integer.valueOf(localThumbnailerState.value), localThumbnailerState);
        i += 1;
      }
    }
    
    private ThumbnailerState(int paramInt1)
    {
      this.value = paramInt1;
    }
    
    public static int forType(ThumbnailerState paramThumbnailerState)
    {
      return paramThumbnailerState.value;
    }
    
    public static ThumbnailerState forValue(int paramInt)
    {
      return (ThumbnailerState)typesByValue.get(Integer.valueOf(paramInt));
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/veg/mediaplayer/sdk/Thumbnailer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */