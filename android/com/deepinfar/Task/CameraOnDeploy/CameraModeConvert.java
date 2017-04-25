package com.deepinfar.Task.CameraOnDeploy;

import com.deepinfar.Task.Function;
import com.deepinfar.Task.FunctionAdapter;
import com.tools.Toast.ToastClass;

public class CameraModeConvert
  implements Function
{
  private static CameraModeConvert CameraModeConvert;
  public int Model = 0;
  ToastClass toastClass;
  
  private CameraModeConvert()
  {
    CameraModeConvert = this;
    FunctionAdapter.getFunctionAdapter().setCameraTask(new ModelOnDeployTask_1());
  }
  
  public static CameraModeConvert getCameraModeConvert()
    throws NullPointerException
  {
    if (CameraModeConvert == null) {
      CameraModeConvert = new CameraModeConvert();
    }
    return CameraModeConvert;
  }
  
  public boolean FunctionStart(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  public boolean FunctionStop()
  {
    FunctionAdapter localFunctionAdapter = FunctionAdapter.getFunctionAdapter();
    ModelOnDeployTask_1 localModelOnDeployTask_1 = new ModelOnDeployTask_1();
    switch (this.Model)
    {
    }
    for (;;)
    {
      localFunctionAdapter.setCameraTask(localModelOnDeployTask_1);
      this.toastClass = ToastClass.getToastClass();
      this.toastClass.ToastshowString("旋转模式 \n Y:A-上下旋转 \n X:B-左右旋转");
      return true;
      localModelOnDeployTask_1 = new ModelOnDeployTask_1();
      this.Model = 0;
    }
  }
  
  public int getModel()
  {
    return this.Model;
  }
  
  public void setModel(int paramInt)
  {
    this.Model = paramInt;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/CameraOnDeploy/CameraModeConvert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */