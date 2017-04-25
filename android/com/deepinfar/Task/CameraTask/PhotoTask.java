package com.deepinfar.Task.CameraTask;

import android.widget.Button;
import com.deepinfar.Task.Function;
import com.tools.Toast.ToastClass;

public class PhotoTask
  implements Function
{
  private Button button;
  public ToastClass toastClass;
  
  public PhotoTask(Button paramButton)
  {
    this.button = paramButton;
    this.toastClass = ToastClass.getToastClass();
  }
  
  public boolean FunctionStart(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  public boolean FunctionStop()
  {
    this.button.performClick();
    return true;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/CameraTask/PhotoTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */