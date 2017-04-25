package veg.mediaplayer.sdk;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;

class ThumbnailerWorker
  implements Runnable
{
  private String TAG = "Thread:";
  public volatile boolean finish = false;
  private Thumbnailer owner = null;
  public int thumbnailer_inst = 0;
  
  public ThumbnailerWorker(Thumbnailer paramThumbnailer, String paramString)
  {
    this.owner = paramThumbnailer;
    this.TAG += paramString;
  }
  
  public void destroyBuffer(Buffer paramBuffer)
  {
    Object localObject;
    if (paramBuffer.isDirect()) {
      localObject = paramBuffer;
    }
    try
    {
      if (!paramBuffer.getClass().getName().equals("java.nio.DirectByteBuffer"))
      {
        localObject = paramBuffer.getClass().getDeclaredField("att");
        ((Field)localObject).setAccessible(true);
        localObject = (Buffer)((Field)localObject).get(paramBuffer);
      }
      paramBuffer = localObject.getClass().getMethod("cleaner", new Class[0]);
      paramBuffer.setAccessible(true);
      paramBuffer = paramBuffer.invoke(localObject, new Object[0]);
      localObject = paramBuffer.getClass().getMethod("clean", new Class[0]);
      ((Method)localObject).setAccessible(true);
      ((Method)localObject).invoke(paramBuffer, new Object[0]);
      return;
    }
    catch (Exception paramBuffer) {}
  }
  
  public void run()
  {
    if (this.owner.state == Thumbnailer.ThumbnailerState.Closing)
    {
      this.finish = false;
      this.owner.state = Thumbnailer.ThumbnailerState.Closed;
      this.owner.waitOpen.notify("Open notify...");
      if (this.owner.outbuff != null)
      {
        this.owner.outbuff.clear();
        destroyBuffer(this.owner.outbuff);
        this.owner.outbuff = null;
      }
    }
    do
    {
      do
      {
        return;
        this.owner.state = Thumbnailer.ThumbnailerState.Opening;
        this.thumbnailer_inst = this.owner.nativeThumbnailerInit(this.owner);
        if ((this.thumbnailer_inst != 0) && (!this.finish)) {
          break;
        }
        this.owner.nativeThumbnailerUninit(this.thumbnailer_inst);
        this.thumbnailer_inst = 0;
        this.finish = false;
        this.owner.state = Thumbnailer.ThumbnailerState.Closed;
        this.owner.waitOpen.notify("Open notify...");
      } while (this.owner.outbuff == null);
      this.owner.outbuff.clear();
      destroyBuffer(this.owner.outbuff);
      this.owner.outbuff = null;
      return;
      if ((this.owner.nativeThumbnailerOpen(this.thumbnailer_inst, this.owner.thumbnailerConfig.getConnectionUrl(), this.owner.thumbnailerConfig.getOutWidth(), this.owner.thumbnailerConfig.getOutHeight()) == 0) && (!this.finish)) {
        break;
      }
      this.owner.nativeThumbnailerClose(this.thumbnailer_inst);
      this.owner.nativeThumbnailerUninit(this.thumbnailer_inst);
      this.thumbnailer_inst = 0;
      this.finish = false;
      this.owner.state = Thumbnailer.ThumbnailerState.Closed;
      this.owner.waitOpen.notify("waitOpen notify...");
    } while (this.owner.outbuff == null);
    this.owner.outbuff.clear();
    destroyBuffer(this.owner.outbuff);
    this.owner.outbuff = null;
    return;
    this.owner.state = Thumbnailer.ThumbnailerState.Opened;
    this.owner.waitOpen.notify("waitOpen notify...");
    this.owner.waitStartClose.wait("waitStartClose wait.. ");
    this.owner.state = Thumbnailer.ThumbnailerState.Closing;
    this.owner.nativeThumbnailerClose(this.thumbnailer_inst);
    this.owner.nativeThumbnailerUninit(this.thumbnailer_inst);
    this.thumbnailer_inst = 0;
    this.finish = false;
    if (this.owner.outbuff != null) {}
    synchronized (this.owner.outbuff)
    {
      this.owner.outbuff.clear();
      destroyBuffer(this.owner.outbuff);
      this.owner.outbuff = null;
      this.owner.state = Thumbnailer.ThumbnailerState.Closed;
      return;
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/veg/mediaplayer/sdk/ThumbnailerWorker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */