package com.deepinfar.Task.RovTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.deepinfar.Dialog.RovDialog;
import com.deepinfar.Dialog.modeSelection;
import com.deepinfar.Dialog.modeSelection.loadTimeThread;
import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.RovData;
import com.deepinfar.ProtocolData.RovInitData;
import com.deepinfar.Task.Function;
import com.deepinfar.Task.FunctionAdapter;
import com.deepinfar.Task.FunctionAdapter.OnDeployTask;
import com.deepinfar.Task.FunctionOnDeployTask;
import com.deepinfar.Task.RockerTask.RockerMode_null;
import com.deepinfar.Task.RockerTask.deep.RockerMode_2_course;
import com.deepinfar.Task.RockerTask.deep.RockerMode_2_speed;
import com.deepinfar.Task.RovTask.CalibrationDeep.RovControlMode_5;
import com.deepinfar.Task.RovTask.deep.RovControlMode_2;
import com.deepinfar.rov.MainFragment;
import com.tools.Toast.ToastClass;

public class RovModeConvert
  implements Function
{
  private static RovModeConvert RovModeConvert;
  public int Model = 0;
  private Context context;
  private Handler handler;
  public int nowMode = -1;
  private FunctionAdapter.OnDeployTask rockerTask = new RockerMode_null();
  private FunctionOnDeployTask rovTask;
  private FunctionOnDeployTask rovTask_deep;
  private FunctionOnDeployTask rovTask_run;
  private FunctionOnDeployTask rovTask_stop;
  ToastClass toastClass;
  
  private RovModeConvert(Context paramContext, Handler paramHandler)
  {
    this.context = paramContext;
    this.handler = paramHandler;
    this.rovTask = new RovControlMode_null(paramContext);
    paramHandler = FunctionAdapter.getFunctionAdapter();
    paramHandler.setRockerTask(this.rockerTask);
    paramHandler.setRovTask(this.rovTask);
    this.rovTask_deep = new RovControlMode_5();
    this.rovTask_stop = new RovControlMode_null(paramContext);
    this.rovTask_run = new RovControlMode_2();
    RovModeConvert = this;
  }
  
  private void StartTime(long paramLong, boolean paramBoolean)
  {
    Object localObject = MainFragment.getMainView().getDialog();
    if ((localObject != null) && (((RovDialog)localObject).getDialogTag().equals("modeDialog")))
    {
      localObject = (modeSelection)localObject;
      modeSelection.loadTimeThread localloadTimeThread = ((modeSelection)localObject).getloadTimeThread(paramLong, paramBoolean);
      if (((modeSelection)localObject).isLoadTimeOut()) {
        new Thread(localloadTimeThread).start();
      }
    }
  }
  
  public static RovModeConvert getRovModeConvert()
    throws NullPointerException
  {
    if (RovModeConvert == null) {
      throw new NullPointerException();
    }
    return RovModeConvert;
  }
  
  public static RovModeConvert getRovModeConvert(Context paramContext, Handler paramHandler)
  {
    if (RovModeConvert == null) {
      RovModeConvert = new RovModeConvert(paramContext, paramHandler);
    }
    return RovModeConvert;
  }
  
  public boolean FunctionStart(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  public boolean FunctionStop()
  {
    this.toastClass = ToastClass.getToastClass();
    Object localObject = NetWork.getNetwork();
    NetWork.SendFlags = true;
    SendRovData.RovData localRovData = ((NetWork)localObject).getSendRovData().getRovData();
    SendRovData localSendRovData = ((NetWork)localObject).getSendRovData();
    FunctionAdapter localFunctionAdapter = FunctionAdapter.getFunctionAdapter();
    Message localMessage = new Message();
    localMessage.what = 3;
    localObject = "停止模式 \nROV停止运动";
    if (this.nowMode != -1) {}
    label140:
    int j;
    switch (this.nowMode)
    {
    case 1: 
    default: 
      switch (this.Model)
      {
      default: 
        int i = 0;
        if (RovInitData.Course) {
          i = 1;
        }
        j = i;
        if (RovInitData.Inertia)
        {
          if (i == 0) {
            j = 2;
          }
        }
        else
        {
          label164:
          if (!LoadTimerData.HandoverStart) {
            break label700;
          }
          StartTime(30000L, true);
          this.toastClass.ToastshowString("请等待操作完成");
        }
        break;
      }
      break;
    }
    for (;;)
    {
      localRovData.UpdataData("Course_StartStop", Integer.valueOf(j));
      localFunctionAdapter.setRovTask(this.rovTask);
      localFunctionAdapter.setRockerTask(this.rockerTask);
      localSendRovData.getRovData().UpdataData("Work_Pattern", Integer.valueOf(this.Model));
      this.handler.sendMessage(localMessage);
      LoadTimerData.HandoverStart = false;
      LoadTimerData.DeepCalibrationStart = false;
      return true;
      this.Model = 2;
      this.nowMode = -1;
      break;
      this.Model = 0;
      this.nowMode = -1;
      break;
      this.rockerTask = new RockerMode_null();
      this.Model = 0;
      localObject = "停止模式 \nROV停止运动";
      localMessage.arg1 = 0;
      if ((!LoadTimerData.HandoverFinish) && (this.rovTask == this.rovTask_run)) {
        this.rovTask_stop.getOnTimerUpdata().loadtime = this.rovTask_run.getOnTimerUpdata().loadtime;
      }
      localFunctionAdapter.removeOnTimerUpdata("RovControlMode_2");
      this.rovTask = this.rovTask_stop;
      break label140;
      if (RovInitData.Course)
      {
        this.rockerTask = new RockerMode_2_course();
        label376:
        this.Model = 2;
        localObject = "开始模式(";
        switch (RovControlMode_2.coefficient)
        {
        default: 
          label412:
          if (!RovInitData.Course) {
            break;
          }
        }
      }
      for (localObject = localObject + "航向) \n 摇杆 -> 左-前进,航向 : 右-姿态 \n 方向键 -> YA-深度";; localObject = localObject + "差速) \n 摇杆 -> 左-前进,转向 : 右-姿态 \n 方向键 -> YA-深度")
      {
        if ((!LoadTimerData.HandoverFinish) && (this.rovTask == this.rovTask_stop)) {
          this.rovTask_run.getOnTimerUpdata().loadtime = this.rovTask_stop.getOnTimerUpdata().loadtime;
        }
        localFunctionAdapter.removeOnTimerUpdata("RovControlMode_null");
        this.rovTask = this.rovTask_run;
        break;
        this.rockerTask = new RockerMode_2_speed();
        break label376;
        localObject = "开始模式(" + "高速，";
        localMessage.arg1 = 2;
        break label412;
        localObject = "开始模式(" + "低速，";
        localMessage.arg1 = 1;
        break label412;
      }
      LoadTimerData.DeepCalibrationStart = true;
      LoadTimerData.DeepCalibrationFinish = false;
      this.rovTask = this.rovTask_deep;
      this.rockerTask = new RockerMode_null();
      localObject = "深度校准";
      if (this.Model == -98)
      {
        ((RovControlMode_5)this.rovTask).setOnTimerUpdata(((RovControlMode_5)this.rovTask).getOnTimerUpdata_deep());
        localRovData.UpdataData("Attitude_Control", Integer.valueOf(Integer.parseInt("01000000", 2)));
      }
      this.nowMode = localRovData.getDataInt("Work_Pattern").intValue();
      this.Model = 0;
      break label140;
      j = 3;
      break label164;
      label700:
      this.toastClass.ToastshowString((String)localObject);
    }
  }
  
  public int getModel()
  {
    return this.Model;
  }
  
  public FunctionAdapter.OnDeployTask getRovTask()
  {
    return this.rovTask;
  }
  
  public void setModel(int paramInt)
  {
    this.Model = paramInt;
  }
  
  public void setNowMode(int paramInt)
  {
    this.nowMode = paramInt;
  }
  
  public void setRockerTask(FunctionAdapter.OnDeployTask paramOnDeployTask)
  {
    this.rockerTask = paramOnDeployTask;
    FunctionAdapter.getFunctionAdapter().setRockerTask(paramOnDeployTask);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/RovTask/RovModeConvert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */