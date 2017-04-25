package com.deepinfar.Task.RockerTask.deep;

import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.RovData;
import com.deepinfar.Task.FunctionAdapter.OnDeployTask;
import com.deepinfar.Task.RovTask.deep.RovControlMode_2;
import com.tools.Bytetools.ByteUtils;

public class RockerMode_2_speed
  implements FunctionAdapter.OnDeployTask
{
  public int Azimuth_Posture_MAX = 100;
  public int Azimuth_Posture_MIN = -100;
  public FunctionAdapter.OnDeployTask OnDeployTask = new FunctionAdapter.OnDeployTask()
  {
    public void FunctionDeploy()
    {
      RockerMode_2_speed.this.netWork = NetWork.getNetwork();
      RockerMode_2_speed.this.sendRovData = RockerMode_2_speed.this.netWork.getSendRovData();
      RockerMode_2_speed.this.rovData = RockerMode_2_speed.this.sendRovData.getRovData();
      int j = RockerMode_2_speed.this.sendRovData.getAXIS_X();
      int k = RockerMode_2_speed.this.sendRovData.getAXIS_RovY();
      int m = RockerMode_2_speed.this.sendRovData.getAXIS_Z();
      int i = RockerMode_2_speed.this.sendRovData.getAXIS_RovRZ();
      j = RockerMode_2_speed.this.updataCoordinate(j, 30);
      k = RockerMode_2_speed.this.updataCoordinate(k, 30);
      m = RockerMode_2_speed.this.updataCoordinate(m, 15);
      i = RockerMode_2_speed.this.updataCoordinate(i, 15);
      j = RockerMode_2_speed.this.updataXY(j);
      k = RockerMode_2_speed.this.updataXY(k);
      RockerMode_2_speed.this.updataXY(m);
      i = -(int)(RockerMode_2_speed.this.updataXY(i) * 4.5D);
      if (i != 0)
      {
        RockerMode_2_speed.this.rovData.UpdataData("Work_Pattern", Integer.valueOf(1));
        RockerMode_2_speed.this.rovData.UpdataData("Z_Translation", Integer.valueOf(ByteUtils.ToBytesIsInt(new byte[] { RockerMode_2_speed.this.netWork.getmBuf(34), RockerMode_2_speed.this.netWork.getmBuf(35) })));
      }
      for (;;)
      {
        RockerMode_2_speed.this.rovData.UpdataData("Pitching_Posture", Integer.valueOf(i));
        switch (RovControlMode_2.coefficient)
        {
        default: 
          RockerMode_2_speed.this.rovData.UpdataData("Y_Translation", Integer.valueOf(k));
          RockerMode_2_speed.this.rovData.UpdataData("Azimuth_Posture", Integer.valueOf(j));
          return;
          if (RockerMode_2_speed.this.rovData.getDataInt("Work_Pattern").intValue() == 1) {
            RockerMode_2_speed.this.rovData.UpdataData("Work_Pattern", Integer.valueOf(2));
          }
          break;
        }
      }
      RockerMode_2_speed.this.rovData.UpdataData("Y_Translation", Integer.valueOf(k / 2));
      RockerMode_2_speed.this.rovData.UpdataData("Azimuth_Posture", Integer.valueOf(j));
    }
  };
  public int Y_Translation_MAX = 100;
  public int Y_Translation_MIN = -100;
  public NetWork netWork;
  public FunctionAdapter.OnDeployTask nowOnDeployTask = this.OnDeployTask;
  public SendRovData.RovData rovData;
  public SendRovData sendRovData;
  public boolean start = false;
  private int updataNum = -4;
  
  public void FunctionDeploy()
  {
    if (this.nowOnDeployTask != null) {
      this.nowOnDeployTask.FunctionDeploy();
    }
  }
  
  public int updataCoordinate(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt2 < 50)
    {
      i = paramInt2;
      if (paramInt2 > 5) {}
    }
    else
    {
      i = 15;
    }
    if ((paramInt1 >= -i) && (paramInt1 <= i)) {}
    double d;
    do
    {
      return 0;
      d = 100.0D / (100 - i);
      if (paramInt1 > i) {
        return (int)((paramInt1 - i) * d);
      }
    } while (paramInt1 >= -i);
    return (int)((paramInt1 + i) * d);
  }
  
  public int updataSpeed(int paramInt1, int paramInt2)
  {
    int i;
    if (paramInt2 == 0) {
      i = 0;
    }
    do
    {
      do
      {
        return i;
        if (((paramInt1 < 0) && (paramInt2 > 0)) || ((paramInt1 > 0) && (paramInt2 < 0))) {
          return 0;
        }
        if (paramInt2 <= 0) {
          break;
        }
        i = paramInt2;
      } while (paramInt1 >= paramInt2);
      if (paramInt1 + 1 >= this.Y_Translation_MAX) {
        paramInt1 = this.Y_Translation_MAX;
      }
      for (;;)
      {
        return paramInt1;
        paramInt1 += 1;
      }
      i = paramInt2;
    } while (paramInt1 < paramInt2);
    if (paramInt1 - 1 <= this.Y_Translation_MIN) {
      paramInt1 = this.Y_Translation_MIN;
    }
    for (;;)
    {
      return paramInt1;
      paramInt1 -= 1;
    }
  }
  
  public int updataXY(int paramInt)
  {
    int i;
    if (paramInt > 100) {
      i = 100;
    }
    do
    {
      return i;
      i = paramInt;
    } while (paramInt >= -100);
    return -100;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/RockerTask/deep/RockerMode_2_speed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */