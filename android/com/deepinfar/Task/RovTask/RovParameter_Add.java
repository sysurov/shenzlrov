package com.deepinfar.Task.RovTask;

import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.RovData;
import com.deepinfar.Task.Function;

public class RovParameter_Add
  implements Function
{
  private String TAG;
  private int TimeNum = 4;
  private int maxParameter;
  private int minParameter;
  private NetWork netWork;
  private SendRovData.RovData rovData;
  private int scale = -1;
  private SendRovData sendRovData;
  
  public RovParameter_Add(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    this.TAG = paramString;
    this.TimeNum = paramInt1;
    this.minParameter = paramInt2;
    this.maxParameter = paramInt3;
    this.netWork = NetWork.getNetwork();
    this.sendRovData = this.netWork.getSendRovData();
    this.rovData = this.sendRovData.getRovData();
  }
  
  public RovParameter_Add(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.TAG = paramString;
    this.TimeNum = paramInt1;
    this.scale = paramInt2;
    this.minParameter = paramInt3;
    this.maxParameter = paramInt4;
    this.netWork = NetWork.getNetwork();
    this.sendRovData = this.netWork.getSendRovData();
    this.rovData = this.sendRovData.getRovData();
  }
  
  public boolean FunctionStart(int paramInt1, int paramInt2)
  {
    if ((this.scale > 0) && (paramInt2 % this.TimeNum == 0))
    {
      paramInt1 = this.rovData.getDataInt(this.TAG).intValue();
      localRovData = this.rovData;
      str = this.TAG;
      if (this.scale + paramInt1 >= this.maxParameter)
      {
        paramInt1 = this.maxParameter;
        localRovData.UpdataData(str, Integer.valueOf(paramInt1));
      }
    }
    while (paramInt2 % this.TimeNum != 0) {
      for (;;)
      {
        return true;
        paramInt1 = this.scale + paramInt1;
      }
    }
    int i = this.rovData.getDataInt(this.TAG).intValue();
    paramInt2 /= this.TimeNum * 2;
    paramInt1 = paramInt2;
    if (paramInt2 <= 0) {
      paramInt1 = 1;
    }
    SendRovData.RovData localRovData = this.rovData;
    String str = this.TAG;
    if (i + paramInt1 >= this.maxParameter) {}
    for (paramInt1 = this.maxParameter;; paramInt1 = i + paramInt1)
    {
      localRovData.UpdataData(str, Integer.valueOf(paramInt1));
      break;
    }
  }
  
  public boolean FunctionStop()
  {
    return true;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/RovTask/RovParameter_Add.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */