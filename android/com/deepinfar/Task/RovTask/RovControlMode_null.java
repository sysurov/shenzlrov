package com.deepinfar.Task.RovTask;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.deepinfar.Dialog.RovDialog;
import com.deepinfar.Dialog.modeSelection;
import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.RovData;
import com.deepinfar.ProtocolData.RovInitData;
import com.deepinfar.Task.Function;
import com.deepinfar.Task.FunctionAdapter;
import com.deepinfar.Task.FunctionOnDeployTask;
import com.deepinfar.Task.NullFunction;
import com.deepinfar.rov.MainFragment;
import com.tools.Bytetools.ByteToBinary;
import com.tools.Bytetools.ByteUtils;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import java.io.IOException;
import java.util.Map;

public class RovControlMode_null
  extends FunctionOnDeployTask
{
  public static final String OnTimerUpdataTag = "RovControlMode_null";
  public Context context;
  public OnTimerUpdata loadOnTimerUpdata = new OnTimerUpdata()
  {
    public void onTimerUpdata()
    {
      RovDialog localRovDialog = MainFragment.getMainView().getDialog();
      Object localObject = ToastClass.getToastClass();
      this.loadtime += 1;
      do
      {
        RovModeConvert localRovModeConvert;
        for (;;)
        {
          try
          {
            localRovModeConvert = RovModeConvert.getRovModeConvert();
            if ((this.loadtime > 600) || (LoadTimerData.HandoverFinish)) {
              break;
            }
            String str = ByteToBinary.getBinaryStrFromByte(NetWork.getNetwork().getmBuf(24));
            char[] arrayOfChar = new char[2];
            arrayOfChar[0] = str.charAt(4);
            arrayOfChar[1] = str.charAt(5);
            switch (arrayOfChar[1] - '0' + (arrayOfChar[0] - '0' << 1))
            {
            default: 
              return;
            }
          }
          catch (NullPointerException localNullPointerException)
          {
            return;
          }
          if (LoadTimerData.OnStartLoading)
          {
            ((ToastClass)localObject).ToastshowString("切换完成");
            FunctionAdapter.getFunctionAdapter().setOnTimerUpdata("RovControlMode_null", RovControlMode_null.this.onTimerUpdata);
            this.loadtime = 0;
            LoadTimerData.HandoverFinish = true;
            localRovModeConvert.setNowMode(-1);
            if ((localNullPointerException != null) && (localNullPointerException.getDialogTag().equals("modeDialog")))
            {
              ((modeSelection)localNullPointerException).setLoadTimeOut(true);
              localObject = new Message();
              ((Message)localObject).what = 4;
              ((Message)localObject).obj = "切换成功";
              ((modeSelection)localNullPointerException).getUpdataUI().sendMessage((Message)localObject);
              return;
              if (!LoadTimerData.OnStartLoading)
              {
                LoadTimerData.OnStartLoading = true;
                ((ToastClass)localObject).ToastshowString("切换中，请稍等片刻。。。");
                return;
                if (LoadTimerData.OnStartLoading)
                {
                  ((ToastClass)localObject).ToastshowString("切换失败，请重新操作");
                  FunctionAdapter.getFunctionAdapter().setOnTimerUpdata("RovControlMode_null", RovControlMode_null.this.onTimerUpdata);
                  LoadTimerData.HandoverFinish = true;
                  localRovModeConvert.setNowMode(-1);
                  this.loadtime = 0;
                  if ((localNullPointerException != null) && (localNullPointerException.getDialogTag().equals("modeDialog")))
                  {
                    ((modeSelection)localNullPointerException).setLoadTimeOut(true);
                    localObject = new Message();
                    ((Message)localObject).what = 4;
                    ((Message)localObject).obj = "切换失败，请重新操作";
                    ((modeSelection)localNullPointerException).getUpdataUI().sendMessage((Message)localObject);
                    return;
                  }
                }
              }
            }
          }
        }
        Log.e("Error", "切换超时");
        ((ToastClass)localObject).ToastshowString("切换失败，请重新操作");
        FunctionAdapter.getFunctionAdapter().setOnTimerUpdata("RovControlMode_null", RovControlMode_null.this.onTimerUpdata);
        LoadTimerData.HandoverFinish = true;
        localRovModeConvert.setNowMode(-1);
        this.loadtime = 0;
      } while ((localNullPointerException == null) || (!localNullPointerException.getDialogTag().equals("modeDialog")));
      localObject = new Message();
      ((Message)localObject).what = 4;
      ((Message)localObject).obj = "切换失败，请重新操作";
      ((modeSelection)localNullPointerException).getUpdataUI().sendMessage((Message)localObject);
    }
  };
  public Function nullFunction = new NullFunction();
  OnTimerUpdata onTimerUpdata = new OnTimerUpdata()
  {
    public void onTimerUpdata()
    {
      Object localObject = NetWork.getNetwork();
      SendRovData.RovData localRovData = ((NetWork)localObject).getSendRovData().getRovData();
      localRovData.UpdataData("Pitching_Posture", Integer.valueOf(ByteUtils.ToBytesIsInt(new byte[] { ((NetWork)localObject).getmBuf(10), ((NetWork)localObject).getmBuf(11) })));
      localRovData.UpdataData("Z_Translation", Integer.valueOf(ByteUtils.ToBytesIsInt(new byte[] { ((NetWork)localObject).getmBuf(22), ((NetWork)localObject).getmBuf(23) })));
      if ((NetWork.ReadFlags) && (!NetWork.SendFlags))
      {
        localObject = ByteToBinary.getBinaryStrFromByte(((NetWork)localObject).getmBuf(21)).toCharArray();
        if ((localObject.length != 8) || (localObject[6] != '1')) {
          break label152;
        }
        RovInitData.Inertia = true;
        i = 0;
        if (RovInitData.Inertia) {
          if (0 != 0) {
            break label166;
          }
        }
      }
      label152:
      label166:
      for (int i = 2;; i = 3)
      {
        localRovData.UpdataData("Course_StartStop", Integer.valueOf(i));
        NetWork.SendFlags = true;
        return;
        if (localObject.length != 8) {
          break;
        }
        RovInitData.Inertia = false;
        break;
      }
    }
  };
  public Resources res;
  
  public RovControlMode_null(Context paramContext)
  {
    this.res = paramContext.getResources();
  }
  
  public void FunctionDeploy()
  {
    Object localObject1 = null;
    try
    {
      localObject2 = FileDataUtils.getFileDataUtils();
      localObject1 = localObject2;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        Object localObject2;
        localIOException.printStackTrace();
      }
      if (LoadTimerData.HandoverFinish) {
        break label137;
      }
      localIOException.setOnTimerUpdata("RovControlMode_null", this.loadOnTimerUpdata);
      return;
      localIOException.setOnTimerUpdata("RovControlMode_null", this.onTimerUpdata);
    }
    localObject2 = FunctionAdapter.getFunctionAdapter();
    ((FunctionAdapter)localObject2).UpdateFunctions(((FileDataUtils)localObject1).getTagData("Y"), this.nullFunction);
    ((FunctionAdapter)localObject2).UpdateFunctions(((FileDataUtils)localObject1).getTagData("A"), this.nullFunction);
    ((FunctionAdapter)localObject2).UpdateFunctions(((FileDataUtils)localObject1).getTagData("X"), this.nullFunction);
    ((FunctionAdapter)localObject2).UpdateFunctions(((FileDataUtils)localObject1).getTagData("B"), this.nullFunction);
    ((FunctionAdapter)localObject2).UpdateFunctions(((FileDataUtils)localObject1).getTagData("L2"), this);
    if (LoadTimerData.HandoverStart)
    {
      LoadTimerData.OnStartLoading = false;
      ((FunctionAdapter)localObject2).setOnTimerUpdata("RovControlMode_null", this.loadOnTimerUpdata);
      this.loadOnTimerUpdata.loadtime = 0;
      LoadTimerData.HandoverFinish = false;
      return;
    }
    label137:
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
    OnTimerUpdata localOnTimerUpdata2 = (OnTimerUpdata)FunctionAdapter.getFunctionAdapter().getOnTimerUpdata().get("RovControlMode_null");
    OnTimerUpdata localOnTimerUpdata1 = localOnTimerUpdata2;
    if (localOnTimerUpdata2 == null) {
      localOnTimerUpdata1 = this.loadOnTimerUpdata;
    }
    return localOnTimerUpdata1;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/RovTask/RovControlMode_null.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */