package com.deepinfar.Task.RovTask.CalibrationDeep;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.deepinfar.Dialog.RovDialog;
import com.deepinfar.Dialog.modeSelection;
import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.RovData;
import com.deepinfar.Task.Function;
import com.deepinfar.Task.FunctionAdapter;
import com.deepinfar.Task.FunctionOnDeployTask;
import com.deepinfar.Task.NullFunction;
import com.deepinfar.Task.RovTask.LoadTimerData;
import com.deepinfar.Task.RovTask.OnTimerUpdata;
import com.deepinfar.Task.RovTask.RovModeConvert;
import com.deepinfar.rov.MainFragment;
import com.tools.Bytetools.ByteToBinary;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import java.io.IOException;
import java.util.Map;

public class RovControlMode_5
  extends FunctionOnDeployTask
{
  public static final String OnTimerUpdataTag = "RovControlMode_5";
  public Context context;
  public Function nullFunction = new NullFunction();
  OnTimerUpdata onTimerUpdata = new OnTimerUpdata()
  {
    public void onTimerUpdata() {}
  };
  private OnTimerUpdata onTimerUpdata_deep = new OnTimerUpdata()
  {
    public void onTimerUpdata()
    {
      for (;;)
      {
        RovDialog localRovDialog;
        Object localObject2;
        Object localObject3;
        try
        {
          localRovDialog = MainFragment.getMainView().getDialog();
          localObject2 = FunctionAdapter.getFunctionAdapter();
          localObject3 = ByteToBinary.getBinaryStrFromByte(NetWork.getNetwork().getmBuf(24));
          if ((this.loadtime >= 60) || (LoadTimerData.DeepCalibrationFinish)) {
            break label310;
          }
          char[] arrayOfChar = new char[2];
          arrayOfChar[0] = ((String)localObject3).charAt(0);
          arrayOfChar[1] = ((String)localObject3).charAt(1);
          int i = arrayOfChar[1];
          int j = arrayOfChar[0];
          localObject3 = RovModeConvert.getRovModeConvert();
          switch (i - 48 + (j - 48 << 1))
          {
          case 0: 
            this.loadtime += 1;
            return;
          }
        }
        finally {}
        if (LoadTimerData.OnStartLoading)
        {
          RovControlMode_5.this.rovData.UpdataData("Attitude_Control", Integer.valueOf(0));
          RovControlMode_5.this.toastClass.ToastshowString("操作完成");
          this.loadtime = 0;
          ((FunctionAdapter)localObject2).removeOnTimerUpdata("RovControlMode_5");
          LoadTimerData.DeepCalibrationFinish = true;
          ((RovModeConvert)localObject3).FunctionStop();
          if ((localRovDialog != null) && (localRovDialog.getDialogTag().equals("modeDialog")))
          {
            localObject2 = new Message();
            ((Message)localObject2).what = 3;
            ((Message)localObject2).arg1 = 1;
            ((modeSelection)localRovDialog).getUpdataUI().sendMessage((Message)localObject2);
            localObject2 = new Message();
            ((Message)localObject2).what = 4;
            ((Message)localObject2).obj = "深度校准完成";
            ((modeSelection)localRovDialog).getUpdataUI().sendMessage((Message)localObject2);
            continue;
            if (!LoadTimerData.OnStartLoading)
            {
              LoadTimerData.OnStartLoading = true;
              continue;
              Log.e("Error", "深度异常3");
              continue;
              label310:
              if (this.loadtime >= 60)
              {
                Log.e("Error", "深度校准超时");
                localObject3 = RovModeConvert.getRovModeConvert();
                RovControlMode_5.this.rovData.UpdataData("Attitude_Control", Integer.valueOf(0));
                RovControlMode_5.this.toastClass.ToastshowString("操作失败,延时过高");
                this.loadtime = 0;
                RovControlMode_5.this.rovData.UpdataData("Attitude_Control", Integer.valueOf(Integer.parseInt("10000000", 2)));
                LoadTimerData.DeepCalibrationFinish = true;
                ((RovModeConvert)localObject3).FunctionStop();
                ((FunctionAdapter)localObject2).removeOnTimerUpdata("RovControlMode_5");
                if ((localObject1 != null) && (((RovDialog)localObject1).getDialogTag().equals("modeDialog")))
                {
                  localObject2 = new Message();
                  ((Message)localObject2).what = 3;
                  ((Message)localObject2).arg1 = 1;
                  ((modeSelection)localObject1).getUpdataUI().sendMessage((Message)localObject2);
                  localObject2 = new Message();
                  ((Message)localObject2).what = 4;
                  ((Message)localObject2).obj = "深度校准超时，请重新操作";
                  ((modeSelection)localObject1).getUpdataUI().sendMessage((Message)localObject2);
                }
              }
            }
          }
        }
      }
    }
  };
  public SendRovData.RovData rovData = NetWork.getNetwork().getSendRovData().getRovData();
  public ToastClass toastClass = ToastClass.getToastClass();
  
  public void FunctionDeploy()
  {
    try
    {
      FileDataUtils.getFileDataUtils();
      FunctionAdapter.getFunctionAdapter().setOnTimerUpdata("RovControlMode_5", this.onTimerUpdata);
      this.onTimerUpdata_deep.loadtime = 0;
      RovDialog localRovDialog = MainFragment.getMainView().getDialog();
      if ((localRovDialog != null) && (localRovDialog.getDialogTag().equals("modeDialog")))
      {
        Message localMessage = new Message();
        localMessage.what = 3;
        localMessage.arg1 = 0;
        ((modeSelection)localRovDialog).getUpdataUI().sendMessage(localMessage);
      }
      LoadTimerData.OnStartLoading = false;
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
  
  public boolean FunctionStart(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  public boolean FunctionStop()
  {
    return true;
  }
  
  public OnTimerUpdata getOnTimerUpdata()
  {
    OnTimerUpdata localOnTimerUpdata2 = (OnTimerUpdata)FunctionAdapter.getFunctionAdapter().getOnTimerUpdata().get("RovControlMode_5");
    OnTimerUpdata localOnTimerUpdata1 = localOnTimerUpdata2;
    if (localOnTimerUpdata2 == null) {
      localOnTimerUpdata1 = this.onTimerUpdata_deep;
    }
    return localOnTimerUpdata1;
  }
  
  public OnTimerUpdata getOnTimerUpdata_deep()
  {
    return this.onTimerUpdata_deep;
  }
  
  public void setOnTimerUpdata(OnTimerUpdata paramOnTimerUpdata)
  {
    if (paramOnTimerUpdata != null)
    {
      this.onTimerUpdata = paramOnTimerUpdata;
      FunctionAdapter.getFunctionAdapter().setOnTimerUpdata("RovControlMode_5", paramOnTimerUpdata);
      LoadTimerData.OnStartLoading = false;
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/RovTask/CalibrationDeep/RovControlMode_5.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */