package com.deepinfar.Task.CameraTask;

import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.VideoData;
import com.deepinfar.Task.Function;

public class CameraOperate_Sub
  implements Function
{
  private final String[] Index;
  private int[] Scale;
  private final int[] maxNum;
  private final int[] minNum;
  private NetWork netWork;
  private SendRovData sendRovData;
  
  public CameraOperate_Sub(String paramString, int paramInt1, int paramInt2)
  {
    this.Index = new String[] { paramString };
    this.minNum = new int[] { paramInt1 };
    this.maxNum = new int[] { paramInt2 };
    this.Scale = new int[this.Index.length];
    paramInt1 = 0;
    for (;;)
    {
      if (paramInt1 >= this.Scale.length)
      {
        this.netWork = NetWork.getNetwork();
        this.sendRovData = this.netWork.getSendRovData();
        return;
      }
      this.Scale[paramInt1] = ((this.maxNum[paramInt1] - this.minNum[paramInt1]) / 150);
      if (this.Scale[paramInt1] <= 0) {
        this.Scale[paramInt1] = 1;
      }
      paramInt1 += 1;
    }
  }
  
  public CameraOperate_Sub(String paramString, int paramInt1, int paramInt2, int paramInt3)
  {
    this.Index = new String[] { paramString };
    this.minNum = new int[] { paramInt1 };
    this.maxNum = new int[] { paramInt2 };
    this.Scale = new int[this.Index.length];
    paramInt1 = 0;
    for (;;)
    {
      if (paramInt1 >= this.Scale.length)
      {
        this.netWork = NetWork.getNetwork();
        this.sendRovData = this.netWork.getSendRovData();
        return;
      }
      this.Scale[paramInt1] = paramInt3;
      if (this.Scale[paramInt1] <= 0) {
        this.Scale[paramInt1] = 1;
      }
      paramInt1 += 1;
    }
  }
  
  public CameraOperate_Sub(String[] paramArrayOfString, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    this.Index = paramArrayOfString;
    this.minNum = paramArrayOfInt1;
    this.maxNum = paramArrayOfInt2;
    this.Scale = new int[paramArrayOfString.length];
    int i = 0;
    for (;;)
    {
      if (i >= this.Scale.length)
      {
        this.netWork = NetWork.getNetwork();
        this.sendRovData = this.netWork.getSendRovData();
        return;
      }
      int j = paramArrayOfInt2[i];
      int k = paramArrayOfInt1[i];
      this.Scale[i] = ((j - k) / 150);
      if (this.Scale[i] <= 0) {
        this.Scale[i] = 1;
      }
      i += 1;
    }
  }
  
  public CameraOperate_Sub(String[] paramArrayOfString, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
  {
    this.Index = paramArrayOfString;
    this.minNum = paramArrayOfInt1;
    this.maxNum = paramArrayOfInt2;
    this.Scale = new int[paramArrayOfString.length];
    int i = 0;
    for (;;)
    {
      if (i >= this.Scale.length)
      {
        this.netWork = NetWork.getNetwork();
        this.sendRovData = this.netWork.getSendRovData();
        return;
      }
      this.Scale[i] = paramArrayOfInt3[i];
      if (this.Scale[i] <= 0) {
        this.Scale[i] = 1;
      }
      i += 1;
    }
  }
  
  public boolean FunctionStart(int paramInt1, int paramInt2)
  {
    SendRovData.VideoData localVideoData = this.sendRovData.getVideoData("MAIN");
    if (localVideoData != null)
    {
      paramInt1 = 0;
      if (paramInt1 < this.Index.length) {}
    }
    else
    {
      return true;
    }
    Object localObject2 = localVideoData.getDataInt(this.Index[paramInt1]);
    Object localObject1 = localObject2;
    if (((Integer)localObject2).intValue() - this.Scale[paramInt1] <= this.minNum[paramInt1]) {
      localObject1 = Integer.valueOf(this.minNum[paramInt1]);
    }
    localObject2 = this.Index[paramInt1];
    if (((Integer)localObject1).intValue() <= this.minNum[paramInt1]) {}
    for (paramInt2 = this.minNum[paramInt1];; paramInt2 = ((Integer)localObject1).intValue() - this.Scale[paramInt1])
    {
      localVideoData.UpdataData((String)localObject2, Integer.valueOf(paramInt2));
      paramInt1 += 1;
      break;
    }
  }
  
  public boolean FunctionStop()
  {
    return true;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/CameraTask/CameraOperate_Sub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */