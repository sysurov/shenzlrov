package com.deepinfar.Task.CameraOnDeploy;

import com.deepinfar.Task.CameraTask.CameraOperate_Add;
import com.deepinfar.Task.CameraTask.CameraOperate_Sub;
import com.deepinfar.Task.FunctionAdapter;
import com.deepinfar.Task.FunctionAdapter.OnDeployTask;
import com.tools.file.FileDataUtils;
import java.io.IOException;

public class ModelOnDeployTask_1
  implements FunctionAdapter.OnDeployTask
{
  public static int Brightness_MAX;
  public static int Brightness_MIN;
  public static int Horizontal_Turn_MAX;
  public static int Horizontal_Turn_MIN;
  public static int Vertical_Turn_MAX = 100;
  public static int Vertical_Turn_MIN = -100;
  public static final int[] maxNum = { 100, 100 };
  public static final int[] minNum;
  FileDataUtils fileDataUtils;
  FunctionAdapter functionAdapter;
  
  static
  {
    Horizontal_Turn_MAX = 100;
    Horizontal_Turn_MIN = -100;
    Brightness_MAX = 100;
    Brightness_MIN = 0;
    minNum = new int[2];
  }
  
  public void FunctionDeploy()
  {
    try
    {
      this.fileDataUtils = FileDataUtils.getFileDataUtils();
      String[] arrayOfString = new String[2];
      arrayOfString[0] = "Brightness_Left";
      arrayOfString[1] = "Brightness_Right";
      this.functionAdapter = FunctionAdapter.getFunctionAdapter();
      this.functionAdapter.UpdateFunctions(this.fileDataUtils.getTagData("上"), new CameraOperate_Add("Vertical_Turn", Vertical_Turn_MIN, Vertical_Turn_MAX, 1));
      this.functionAdapter.UpdateFunctions(this.fileDataUtils.getTagData("下"), new CameraOperate_Sub("Vertical_Turn", Vertical_Turn_MIN, Vertical_Turn_MAX, 1));
      this.functionAdapter.UpdateFunctions(this.fileDataUtils.getTagData("左"), new CameraOperate_Sub(arrayOfString, minNum, maxNum));
      this.functionAdapter.UpdateFunctions(this.fileDataUtils.getTagData("右"), new CameraOperate_Add(arrayOfString, minNum, maxNum));
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/CameraOnDeploy/ModelOnDeployTask_1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */