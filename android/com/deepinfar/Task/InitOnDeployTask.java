package com.deepinfar.Task;

import android.content.Context;
import android.os.Handler;
import com.deepinfar.Task.CameraOnDeploy.CameraModeConvert;
import com.deepinfar.Task.CameraTask.PhotoTask;
import com.deepinfar.Task.CameraTask.VideoTask;
import com.deepinfar.Task.RovTask.RovModeConvert;
import com.deepinfar.rov.MainFragment;
import com.tools.file.FileDataUtils;
import java.io.IOException;

public class InitOnDeployTask
  implements FunctionAdapter.OnDeployTask
{
  private Handler Handler;
  private Context context;
  private FunctionAdapter functionAdapter = FunctionAdapter.getFunctionAdapter();
  
  public InitOnDeployTask(Context paramContext, Handler paramHandler)
  {
    this.context = paramContext;
    this.Handler = paramHandler;
  }
  
  public void FunctionDeploy()
  {
    Object localObject1 = null;
    try
    {
      localObject2 = FileDataUtils.getFileDataUtils();
      localObject1 = localObject2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Object localObject2;
        FunctionOnDeployTask localFunctionOnDeployTask;
        localIOException.printStackTrace();
      }
    }
    this.functionAdapter.UpdateFunctions(((FileDataUtils)localObject1).getTagData("R1"), new PhotoTask(MainFragment.getMainView().CameraButton));
    this.functionAdapter.UpdateFunctions(((FileDataUtils)localObject1).getTagData("R2"), new VideoTask(MainFragment.getMainView().VideoButton));
    localObject2 = RovModeConvert.getRovModeConvert(this.context, this.Handler);
    localFunctionOnDeployTask = this.functionAdapter.getRovTask();
    this.functionAdapter.UpdateFunctions(((FileDataUtils)localObject1).getTagData("L2"), localFunctionOnDeployTask);
    this.functionAdapter.UpdateFunctions(((FileDataUtils)localObject1).getTagData("L1"), (Function)localObject2);
    CameraModeConvert.getCameraModeConvert();
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/InitOnDeployTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */