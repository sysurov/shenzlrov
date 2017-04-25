package com.deepinfar.Task.RockerTask.deep;

import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.RovData;
import com.deepinfar.Task.FunctionAdapter.OnDeployTask;
import com.deepinfar.Task.RovTask.deep.RovControlMode_2;
import com.tools.Bytetools.ByteUtils;

public class RockerMode_2_course
  implements FunctionAdapter.OnDeployTask
{
  public int Azimuth_Posture_MAX = 1800;
  public int Azimuth_Posture_MIN = 63736;
  public FunctionAdapter.OnDeployTask OnDeployTask = new FunctionAdapter.OnDeployTask()
  {
    public void FunctionDeploy()
    {
      RockerMode_2_course.this.netWork = NetWork.getNetwork();
      RockerMode_2_course.this.sendRovData = RockerMode_2_course.this.netWork.getSendRovData();
      RockerMode_2_course.this.rovData = RockerMode_2_course.this.sendRovData.getRovData();
      int j = RockerMode_2_course.this.sendRovData.getAXIS_X();
      int k = RockerMode_2_course.this.sendRovData.getAXIS_RovY();
      int m = RockerMode_2_course.this.sendRovData.getAXIS_Z();
      int i = RockerMode_2_course.this.sendRovData.getAXIS_RovRZ();
      j = RockerMode_2_course.this.updataCoordinate(j, 30);
      k = RockerMode_2_course.this.updataCoordinate(k, 30);
      int i1 = RockerMode_2_course.this.updataCoordinate(m, 15);
      i = RockerMode_2_course.this.updataCoordinate(i, 15);
      int n = RockerMode_2_course.this.updataXY(j);
      m = RockerMode_2_course.this.updataXY(k);
      RockerMode_2_course.this.updataXY(i1);
      i = -(int)(RockerMode_2_course.this.updataXY(i) * 4.5D);
      if (i != 0)
      {
        RockerMode_2_course.this.rovData.UpdataData("Work_Pattern", Integer.valueOf(1));
        RockerMode_2_course.this.rovData.UpdataData("Z_Translation", Integer.valueOf(ByteUtils.ToBytesIsInt(new byte[] { RockerMode_2_course.this.netWork.getmBuf(34), RockerMode_2_course.this.netWork.getmBuf(35) })));
        label252:
        RockerMode_2_course.this.rovData.UpdataData("Pitching_Posture", Integer.valueOf(i));
        k = RockerMode_2_course.this.rovData.getDataInt("Azimuth_Posture").intValue();
        i = Math.abs(n) / 5;
        j = i;
        if (i <= 0) {
          j = 1;
        }
        if (n <= 0) {
          break label427;
        }
        if (k + j <= RockerMode_2_course.this.Azimuth_Posture_MAX) {
          break label420;
        }
        i = RockerMode_2_course.this.Azimuth_Posture_MIN;
      }
      label420:
      label427:
      do
      {
        for (;;)
        {
          RockerMode_2_course.this.rovData.UpdataData("Azimuth_Posture", Integer.valueOf(i));
          switch (RovControlMode_2.coefficient)
          {
          default: 
            RockerMode_2_course.this.rovData.UpdataData("Y_Translation", Integer.valueOf(m));
            return;
            if (RockerMode_2_course.this.rovData.getDataInt("Work_Pattern").intValue() != 1) {
              break label252;
            }
            RockerMode_2_course.this.rovData.UpdataData("Work_Pattern", Integer.valueOf(2));
            break label252;
            i = k + j;
          }
        }
        i = k;
      } while (n >= 0);
      if (k - j < RockerMode_2_course.this.Azimuth_Posture_MIN) {}
      for (i = RockerMode_2_course.this.Azimuth_Posture_MAX;; i = k - j) {
        break;
      }
      RockerMode_2_course.this.rovData.UpdataData("Y_Translation", Integer.valueOf(m / 2));
    }
  };
  public int Y_Translation_MAX = 100;
  public int Y_Translation_MIN = -100;
  public NetWork netWork;
  public FunctionAdapter.OnDeployTask nowOnDeployTask = this.OnDeployTask;
  public SendRovData.RovData rovData;
  public SendRovData sendRovData;
  public boolean start = false;
  
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


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/RockerTask/deep/RockerMode_2_course.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */