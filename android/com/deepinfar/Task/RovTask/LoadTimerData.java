package com.deepinfar.Task.RovTask;

public class LoadTimerData
{
  public static boolean DeepCalibrationFinish = false;
  public static boolean DeepCalibrationStart;
  public static boolean HandoverFinish;
  public static boolean HandoverStart;
  public static boolean OnStartLoading;
  public static String[] OnTimerUpdataTag = { "RovControlMode_2", "RovControlMode_5", "RovControlMode_null" };
  
  static
  {
    OnStartLoading = false;
    HandoverStart = false;
    HandoverFinish = true;
    DeepCalibrationStart = false;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/RovTask/LoadTimerData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */