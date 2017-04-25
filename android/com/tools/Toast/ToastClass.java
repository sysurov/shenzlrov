package com.tools.Toast;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class ToastClass
{
  private static ToastClass toastClass;
  private Handler ToastHandler;
  public Context context;
  public Toast toast = null;
  
  private ToastClass(Context paramContext, Handler paramHandler)
  {
    this.context = paramContext;
    this.ToastHandler = paramHandler;
  }
  
  public static ToastClass getToastClass()
    throws NullPointerException
  {
    if (toastClass == null) {
      throw new NullPointerException();
    }
    return toastClass;
  }
  
  public static ToastClass getToastClass(Context paramContext)
  {
    if ((toastClass == null) || (toastClass.context != paramContext)) {
      toastClass = new ToastClass(paramContext, null);
    }
    return toastClass;
  }
  
  public static ToastClass getToastClass(Context paramContext, Handler paramHandler)
  {
    if ((toastClass == null) || (toastClass.context != paramContext)) {
      toastClass = new ToastClass(paramContext, paramHandler);
    }
    return toastClass;
  }
  
  public void ToastFinish()
  {
    this.toast.cancel();
  }
  
  public void ToastshowId(int paramInt)
  {
    if (this.toast != null) {
      this.toast.setText(paramInt);
    }
    for (;;)
    {
      this.toast.show();
      return;
      this.toast = Toast.makeText(this.context, paramInt, 0);
    }
  }
  
  public void ToastshowString(String paramString)
  {
    for (;;)
    {
      try
      {
        if (this.toast == null) {
          continue;
        }
        this.toast.setText(paramString);
        this.toast.show();
      }
      catch (Exception localException)
      {
        if (this.ToastHandler == null) {
          continue;
        }
        Message localMessage = new Message();
        localMessage.obj = paramString;
        localMessage.what = 1;
        this.ToastHandler.sendMessage(localMessage);
        continue;
      }
      finally {}
      return;
      this.toast = Toast.makeText(this.context, paramString, 0);
    }
  }
  
  public Toast getToast()
  {
    return this.toast;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/Toast/ToastClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */