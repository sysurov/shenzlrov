package com.deepinfar.Task.CameraTask;

import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.VideoData;
import com.deepinfar.Task.Function;
import com.tools.Toast.ToastClass;

public class Equilibrium
  implements Function
{
  private final String[] Index;
  private final int[] maxNum;
  private int[] maxValueScale;
  private final int[] minNum;
  private int[] minValueScale;
  private NetWork netWork;
  private SendRovData sendRovData;
  public ToastClass toastClass;
  
  public Equilibrium(String[] paramArrayOfString, int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    this.Index = paramArrayOfString;
    this.minNum = paramArrayOfInt1;
    this.maxNum = paramArrayOfInt2;
    this.maxValueScale = new int[this.Index.length];
    this.minValueScale = new int[this.Index.length];
    int i = 0;
    for (;;)
    {
      if (i >= this.Index.length)
      {
        this.netWork = NetWork.getNetwork();
        this.sendRovData = this.netWork.getSendRovData();
        this.toastClass = ToastClass.getToastClass();
        return;
      }
      this.maxValueScale[i] = (Math.abs(paramArrayOfInt2[i]) / 150);
      this.minValueScale[i] = (Math.abs(paramArrayOfInt1[i]) / 150);
      if (this.maxValueScale[i] <= 0) {
        this.maxValueScale[i] = 1;
      }
      if (this.minValueScale[i] <= 0) {
        this.minValueScale[i] = 1;
      }
      i += 1;
    }
  }
  
  public boolean FunctionStart(int paramInt1, int paramInt2)
  {
    Object localObject1 = this.sendRovData.getVideoData("MAIN");
    Object localObject2;
    Object localObject3;
    String str;
    if (localObject1 != null)
    {
      localObject2 = ((SendRovData.VideoData)localObject1).getDataInt(this.Index[0]);
      localObject3 = this.Index[0];
      if (((Integer)localObject2).intValue() < this.maxNum[0]) {
        break label218;
      }
      paramInt1 = this.maxNum[0];
      ((SendRovData.VideoData)localObject1).UpdataData((String)localObject3, Integer.valueOf(paramInt1));
      localObject2 = "" + this.Index[0] + "  " + ((SendRovData.VideoData)localObject1).getDataInt(this.Index[0]) + "\n";
      localObject3 = ((SendRovData.VideoData)localObject1).getDataInt(this.Index[1]);
      str = this.Index[1];
      if (((Integer)localObject3).intValue() > this.minNum[1]) {
        break label234;
      }
    }
    label218:
    label234:
    for (paramInt1 = this.minNum[1];; paramInt1 = ((Integer)localObject3).intValue() - this.minValueScale[1])
    {
      ((SendRovData.VideoData)localObject1).UpdataData(str, Integer.valueOf(paramInt1));
      localObject1 = localObject2 + this.Index[1] + "  " + ((SendRovData.VideoData)localObject1).getDataInt(this.Index[1]);
      this.toastClass.ToastshowString((String)localObject1);
      return true;
      paramInt1 = ((Integer)localObject2).intValue() + this.maxValueScale[0];
      break;
    }
  }
  
  public boolean FunctionStop()
  {
    return true;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/CameraTask/Equilibrium.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */