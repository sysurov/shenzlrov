package com.deepinfar.Task.RovTask;

import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.RovData;
import com.deepinfar.Task.Function;

public class RovPosture_Empty
  implements Function
{
  public boolean FunctionStart(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  public boolean FunctionStop()
  {
    SendRovData.RovData localRovData = NetWork.getNetwork().getSendRovData().getRovData();
    localRovData.UpdataData("Pitching_Posture", Integer.valueOf(0));
    localRovData.UpdataData("Roll_Posture", Integer.valueOf(0));
    return true;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/RovTask/RovPosture_Empty.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */