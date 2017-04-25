package com.deepinfar.Task.CameraTask;

import android.widget.Button;
import com.deepinfar.Task.Function;

public class VideoTask
  implements Function
{
  Button button;
  
  public VideoTask(Button paramButton)
  {
    this.button = paramButton;
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


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/CameraTask/VideoTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */