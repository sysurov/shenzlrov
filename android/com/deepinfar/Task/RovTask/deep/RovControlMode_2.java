package com.deepinfar.Task.RovTask.deep;

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
import com.deepinfar.Task.RovTask.LoadTimerData;
import com.deepinfar.Task.RovTask.OnTimerUpdata;
import com.deepinfar.Task.RovTask.RovParameter_Add;
import com.deepinfar.Task.RovTask.RovParameter_Sub;
import com.deepinfar.rov.MainFragment;
import com.deepinfar.rov.ReceiveFrafment;
import com.tools.Bytetools.ByteToBinary;
import com.tools.Bytetools.ByteUtils;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import java.io.IOException;
import java.util.Map;

public class RovControlMode_2
  extends FunctionOnDeployTask
{
  public static final int HIGH_SPEED = 100;
  public static final int LOW_SPEED = 50;
  public static final String OnTimerUpdataTag = "RovControlMode_2";
  public static int Y_Translation_MAX = 100;
  public static int Y_Translation_MIN = -100;
  public static int Z_Translation_MAX;
  public static int Z_Translation_MIN;
  public static int coefficient = 100;
  public FileDataUtils fileDataUtils;
  public Function functionA;
  public FunctionAdapter functionAdapter;
  public Function functionNull;
  public Function functionY;
  public OnTimerUpdata loadOnTimerUpdata = new OnTimerUpdata()
  {
    public void onTimerUpdata()
    {
      RovDialog localRovDialog = MainFragment.getMainView().getDialog();
      Object localObject = ToastClass.getToastClass();
      this.loadtime += 1;
      if ((this.loadtime <= 600) && (!LoadTimerData.HandoverFinish))
      {
        String str = ByteToBinary.getBinaryStrFromByte(NetWork.getNetwork().getmBuf(24));
        if (str.length() == 8)
        {
          char[] arrayOfChar = new char[2];
          arrayOfChar[0] = str.charAt(4);
          arrayOfChar[1] = str.charAt(5);
          switch (arrayOfChar[1] - '0' + (arrayOfChar[0] - '0' << 1))
          {
          }
        }
      }
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return;
                } while (!LoadTimerData.OnStartLoading);
                RovControlMode_2.this.functionAdapter = FunctionAdapter.getFunctionAdapter();
                RovControlMode_2.this.functionAdapter.setOnTimerUpdata("RovControlMode_2", RovControlMode_2.this.onTimerUpdata);
                ((ToastClass)localObject).ToastshowString("切换完成");
                this.loadtime = 0;
                LoadTimerData.HandoverFinish = true;
              } while ((localRovDialog == null) || (!localRovDialog.getDialogTag().equals("modeDialog")));
              ((modeSelection)localRovDialog).setLoadTimeOut(true);
              localObject = new Message();
              ((Message)localObject).what = 4;
              ((Message)localObject).obj = "切换成功";
              ((modeSelection)localRovDialog).getUpdataUI().sendMessage((Message)localObject);
              return;
            } while (LoadTimerData.OnStartLoading);
            LoadTimerData.OnStartLoading = true;
            ((ToastClass)localObject).ToastshowString("切换中，请稍等片刻。。。");
            return;
          } while (!LoadTimerData.OnStartLoading);
          ((ToastClass)localObject).ToastshowString("切换失败，请重新操作");
          this.loadtime = 0;
          LoadTimerData.HandoverFinish = true;
        } while ((localRovDialog == null) || (!localRovDialog.getDialogTag().equals("modeDialog")));
        ((modeSelection)localRovDialog).getUpdataUI().sendEmptyMessage(1);
        ((modeSelection)localRovDialog).setLoadTimeOut(true);
        localObject = new Message();
        ((Message)localObject).what = 4;
        ((Message)localObject).obj = "切换失败，请重新操作";
        ((modeSelection)localRovDialog).getUpdataUI().sendMessage((Message)localObject);
        return;
        Log.e("Error", "切换超时");
        LoadTimerData.HandoverFinish = true;
        RovControlMode_2.this.functionAdapter = FunctionAdapter.getFunctionAdapter();
        RovControlMode_2.this.functionAdapter.setOnTimerUpdata("RovControlMode_2", RovControlMode_2.this.onTimerUpdata);
      } while ((localRovDialog == null) || (!localRovDialog.getDialogTag().equals("modeDialog")));
      localObject = new Message();
      ((Message)localObject).what = 4;
      ((Message)localObject).obj = "切换失败，请重新操作";
      ((modeSelection)localRovDialog).getUpdataUI().sendMessage((Message)localObject);
    }
  };
  public OnTimerUpdata onTimerUpdata = new OnTimerUpdata()
  {
    public void onTimerUpdata() {}
  };
  
  static
  {
    Z_Translation_MAX = 9999;
    Z_Translation_MIN = 0;
  }
  
  public RovControlMode_2()
  {
    Z_Translation_MAX = RovInitData.MAX_DEEP;
    Z_Translation_MIN = RovInitData.MIN_DEEP;
    this.functionA = new RovParameter_Sub("Z_Translation", 1, 10, Z_Translation_MIN, Z_Translation_MAX);
    this.functionY = new RovParameter_Add("Z_Translation", 1, 10, Z_Translation_MIN, Z_Translation_MAX);
    this.functionNull = new NullFunction();
    NetWork localNetWork = NetWork.getNetwork();
    localNetWork.getSendRovData().getRovData().UpdataData("Z_Translation", Integer.valueOf(ByteUtils.ToBytesIsInt(new byte[] { localNetWork.getmBuf(34), localNetWork.getmBuf(35) })));
    LoadTimerData.OnStartLoading = false;
  }
  
  public void FunctionDeploy()
  {
    try
    {
      this.fileDataUtils = FileDataUtils.getFileDataUtils();
      this.functionAdapter = FunctionAdapter.getFunctionAdapter();
      this.functionAdapter.UpdateFunctions(this.fileDataUtils.getTagData("Y"), this.functionA);
      this.functionAdapter.UpdateFunctions(this.fileDataUtils.getTagData("A"), this.functionY);
      this.functionAdapter.UpdateFunctions(this.fileDataUtils.getTagData("L2"), this);
      if (LoadTimerData.HandoverStart)
      {
        LoadTimerData.OnStartLoading = false;
        this.functionAdapter.setOnTimerUpdata("RovControlMode_2", this.loadOnTimerUpdata);
        this.loadOnTimerUpdata.loadtime = 0;
        LoadTimerData.HandoverFinish = false;
        return;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
      if (!LoadTimerData.HandoverFinish)
      {
        this.functionAdapter.setOnTimerUpdata("RovControlMode_2", this.loadOnTimerUpdata);
        return;
      }
      this.functionAdapter.setOnTimerUpdata("RovControlMode_2", this.onTimerUpdata);
    }
  }
  
  public boolean FunctionStart(int paramInt1, int paramInt2)
  {
    return true;
  }
  
  public boolean FunctionStop()
  {
    ToastClass localToastClass = ToastClass.getToastClass();
    Handler localHandler = ReceiveFrafment.getReceiveFrafment().getMainHandler();
    Message localMessage = new Message();
    localMessage.what = 3;
    switch (coefficient)
    {
    default: 
      coefficient = 100;
      localToastClass.ToastshowString("切换为高速模式");
      localMessage.arg1 = 2;
    }
    for (;;)
    {
      localHandler.sendMessage(localMessage);
      return true;
      coefficient = 100;
      localToastClass.ToastshowString("切换为高速模式");
      localMessage.arg1 = 2;
      continue;
      coefficient = 50;
      localToastClass.ToastshowString("切换为低速模式");
      localMessage.arg1 = 1;
    }
  }
  
  public OnTimerUpdata getOnTimerUpdata()
  {
    OnTimerUpdata localOnTimerUpdata2 = (OnTimerUpdata)FunctionAdapter.getFunctionAdapter().getOnTimerUpdata().get("RovControlMode_2");
    OnTimerUpdata localOnTimerUpdata1 = localOnTimerUpdata2;
    if (localOnTimerUpdata2 == null) {
      localOnTimerUpdata1 = this.loadOnTimerUpdata;
    }
    return localOnTimerUpdata1;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/RovTask/deep/RovControlMode_2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */