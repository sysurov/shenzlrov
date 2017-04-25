package com.deepinfar.Task;

import com.deepinfar.Task.RovTask.OnTimerUpdata;

public abstract class FunctionOnDeployTask
  implements Function, FunctionAdapter.OnDeployTask
{
  public abstract OnTimerUpdata getOnTimerUpdata();
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/FunctionOnDeployTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */