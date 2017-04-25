package com.tools.ViewTools.Button;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import java.lang.ref.WeakReference;

public class LongClickButton
  extends Button
{
  private MyHandler handler;
  private long intervalTime;
  private LongClickRepeatListener repeatListener;
  
  public LongClickButton(Context paramContext)
  {
    super(paramContext);
    init();
  }
  
  public LongClickButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }
  
  public LongClickButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  private void init()
  {
    this.handler = new MyHandler(this);
    setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramAnonymousView)
      {
        new Thread(new LongClickButton.LongClickThread(LongClickButton.this, paramAnonymousView, null)).start();
        return true;
      }
    });
  }
  
  public void setLongClickRepeatListener(LongClickRepeatListener paramLongClickRepeatListener)
  {
    setLongClickRepeatListener(paramLongClickRepeatListener, 100L);
  }
  
  public void setLongClickRepeatListener(LongClickRepeatListener paramLongClickRepeatListener, long paramLong)
  {
    this.repeatListener = paramLongClickRepeatListener;
    this.intervalTime = paramLong;
  }
  
  public static abstract interface LongClickRepeatListener
  {
    public abstract void repeatAction(int paramInt, View paramView);
  }
  
  private class LongClickThread
    implements Runnable
  {
    private int num;
    private View view;
    
    private LongClickThread(View paramView)
    {
      this.view = paramView;
    }
    
    public void run()
    {
      for (;;)
      {
        if (!LongClickButton.this.isPressed()) {
          return;
        }
        this.num += 1;
        if (this.num % 5 == 0)
        {
          Message localMessage = new Message();
          localMessage.obj = this.view;
          localMessage.what = 1;
          localMessage.arg1 = this.num;
          LongClickButton.this.handler.sendMessage(localMessage);
        }
        SystemClock.sleep(LongClickButton.this.intervalTime / 5L);
      }
    }
  }
  
  private static class MyHandler
    extends Handler
  {
    private WeakReference<LongClickButton> ref;
    
    MyHandler(LongClickButton paramLongClickButton)
    {
      this.ref = new WeakReference(paramLongClickButton);
    }
    
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      View localView = (View)paramMessage.obj;
      LongClickButton localLongClickButton = (LongClickButton)this.ref.get();
      if ((localLongClickButton != null) && (localLongClickButton.repeatListener != null)) {
        localLongClickButton.repeatListener.repeatAction(paramMessage.arg1, localView);
      }
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/ViewTools/Button/LongClickButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */