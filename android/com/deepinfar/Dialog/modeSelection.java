package com.deepinfar.Dialog;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.deepinfar.ProtocolData.RovInitData;
import com.deepinfar.Task.RovTask.RovModeConvert;
import com.deepinfar.rov.MainFragment;
import com.tools.Image.ImageOperate;
import com.tools.ViewTools.Switch.SlideSwitchView;
import com.tools.ViewTools.Switch.SlideSwitchView.OnSwitchChangedListener;

public class modeSelection
  extends RovDialog
{
  public static final int EnabledFalse = 0;
  public static final int EnabledTrue = 1;
  public static final int iExplainShow = 4;
  public static final int iUpdataChecked = 1;
  public static final int iUpdataEnabled = 3;
  public static final int iUpdataLoadTime = 2;
  private static loadTimeThread loadTimeThread;
  private static View rootView;
  private boolean BModeSwitchSit = true;
  private SlideSwitchView ModeSwitch;
  private Handler UpdataUI = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      RovDialog localRovDialog = MainFragment.getMainView().getDialog();
      switch (paramAnonymousMessage.what)
      {
      }
      label108:
      label198:
      do
      {
        for (;;)
        {
          return;
          modeSelection.this.updataChecked();
          return;
          try
          {
            int j = paramAnonymousMessage.arg1;
            if ((localRovDialog != null) && (localRovDialog.getDialogTag().equals("modeDialog")))
            {
              ((modeSelection)localRovDialog).updataLoadTime(j);
              if ((j >= 100) || (j < 0))
              {
                ((modeSelection)localRovDialog).getLoadText().setVisibility(8);
                i = 0;
                if (paramAnonymousMessage.arg2 != 1) {
                  break label198;
                }
                i = 1;
              }
              while ((j < 100) && (j >= 0) && (i == 0))
              {
                ((modeSelection)localRovDialog).getModeSwitch().setEnabled(false);
                ((modeSelection)localRovDialog).getCalibration_deep().setEnabled(false);
                ((modeSelection)localRovDialog).getInertia().setEnabled(false);
                ((modeSelection)localRovDialog).getCourse().setEnabled(false);
                return;
                ((modeSelection)localRovDialog).getLoadText().setVisibility(0);
                break label108;
                int k = paramAnonymousMessage.arg2;
                if (k == 0) {
                  i = 0;
                }
              }
              int i = paramAnonymousMessage.arg1;
              if ((localRovDialog != null) && (localRovDialog.getDialogTag().equals("modeDialog")))
              {
                if (i == 1)
                {
                  ((modeSelection)localRovDialog).getModeSwitch().setEnabled(true);
                  ((modeSelection)localRovDialog).getCalibration_deep().setEnabled(true);
                  ((modeSelection)localRovDialog).getInertia().setEnabled(true);
                  ((modeSelection)localRovDialog).getCourse().setEnabled(true);
                  modeSelection.vDialog.setCanceledOnTouchOutside(true);
                  modeSelection.vDialog.setCancelable(true);
                  return;
                }
                ((modeSelection)localRovDialog).getModeSwitch().setEnabled(false);
                ((modeSelection)localRovDialog).getCalibration_deep().setEnabled(false);
                ((modeSelection)localRovDialog).getInertia().setEnabled(false);
                ((modeSelection)localRovDialog).getCourse().setEnabled(false);
                modeSelection.vDialog.setCanceledOnTouchOutside(false);
                modeSelection.vDialog.setCancelable(false);
                return;
              }
            }
          }
          catch (NullPointerException paramAnonymousMessage) {}
        }
      } while (paramAnonymousMessage.obj == null);
      paramAnonymousMessage = (String)paramAnonymousMessage.obj;
      modeSelection.this.explainShow.setText(paramAnonymousMessage);
      modeSelection.this.explainShow.setVisibility(0);
      return;
    }
  };
  private Button calibration_deep;
  private ToggleButton course;
  private TextView explainShow;
  private ToggleButton inertia;
  public TextView loadText;
  private ProgressBar loadTime;
  private SlideSwitchView.OnSwitchChangedListener onSwitchChangedListener = new SlideSwitchView.OnSwitchChangedListener()
  {
    public void onSwitchChange(SlideSwitchView paramAnonymousSlideSwitchView, boolean paramAnonymousBoolean)
    {
      paramAnonymousSlideSwitchView = RovModeConvert.getRovModeConvert();
      if (paramAnonymousBoolean) {
        paramAnonymousSlideSwitchView.setModel(2);
      }
      for (;;)
      {
        modeSelection.this.BModeSwitchSit = paramAnonymousBoolean;
        paramAnonymousSlideSwitchView.FunctionStop();
        return;
        paramAnonymousSlideSwitchView.setModel(0);
      }
    }
  };
  private View.OnClickListener onclack;
  private View.OnClickListener toggleButtonOnclack;
  
  public static final RovDialog newInstance(Handler paramHandler, FragmentManager paramFragmentManager, String paramString)
  {
    modeSelection localmodeSelection = new modeSelection();
    RovDialog.newInstance(localmodeSelection, paramHandler, paramFragmentManager, paramString);
    return localmodeSelection;
  }
  
  private void updataChecked()
  {
    this.inertia.setChecked(RovInitData.Inertia);
    this.course.setChecked(RovInitData.Course);
  }
  
  public Button getCalibration_deep()
  {
    return this.calibration_deep;
  }
  
  public ToggleButton getCourse()
  {
    return this.course;
  }
  
  public ToggleButton getInertia()
  {
    return this.inertia;
  }
  
  public TextView getLoadText()
  {
    return this.loadText;
  }
  
  public SlideSwitchView getModeSwitch()
  {
    return this.ModeSwitch;
  }
  
  public Handler getUpdataUI()
  {
    return this.UpdataUI;
  }
  
  public loadTimeThread getloadTimeThread(long paramLong, boolean paramBoolean)
  {
    if (loadTimeThread == null) {
      loadTimeThread = new loadTimeThread(paramLong, paramBoolean, null);
    }
    for (;;)
    {
      return loadTimeThread;
      if (loadTimeThread.isTimeOut()) {
        loadTimeThread = new loadTimeThread(paramLong, paramBoolean, null);
      } else {
        loadTimeThread.restartTime(paramLong, paramBoolean);
      }
    }
  }
  
  public View init(View paramView)
  {
    this.ModeSwitch = ((SlideSwitchView)paramView.findViewById(2131361831));
    this.calibration_deep = ((Button)paramView.findViewById(2131361826));
    this.inertia = ((ToggleButton)paramView.findViewById(2131361828));
    this.course = ((ToggleButton)paramView.findViewById(2131361827));
    this.loadTime = ((ProgressBar)paramView.findViewById(2131361829));
    this.loadText = ((TextView)paramView.findViewById(2131361830));
    this.explainShow = ((TextView)paramView.findViewById(2131361832));
    this.explainShow.setVisibility(8);
    this.loadText.setVisibility(8);
    updataChecked();
    this.toggleButtonOnclack = new ToggleButtonOnclack(null);
    this.onclack = new Onclack(null);
    this.ModeSwitch.setOnChangeListener(this.onSwitchChangedListener);
    if (RovModeConvert.getRovModeConvert().getModel() == 0) {}
    for (this.BModeSwitchSit = true;; this.BModeSwitchSit = false)
    {
      this.ModeSwitch.setmSwitchBottom(ImageOperate.resIdConvertBitmap(getActivity(), 2130837507));
      this.ModeSwitch.setChecked(this.BModeSwitchSit);
      this.calibration_deep.setOnClickListener(this.onclack);
      this.inertia.setOnClickListener(this.toggleButtonOnclack);
      this.course.setOnClickListener(this.toggleButtonOnclack);
      return super.init(paramView);
    }
  }
  
  public boolean isLoadTimeOut()
  {
    if (loadTimeThread == null) {
      return true;
    }
    return loadTimeThread.isTimeOut();
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    getDialog().requestWindowFeature(1);
    rootView = paramLayoutInflater.inflate(2130903050, null);
    init(rootView);
    vDialog = getDialog();
    return rootView;
  }
  
  public void onResume()
  {
    super.onResume();
    getDialog().getWindow().setLayout(1300, 800);
    if ((this.tag == null) || (this.tag == "null")) {
      dismiss();
    }
  }
  
  public void setLoadTimeOut(boolean paramBoolean)
  {
    if (loadTimeThread != null) {
      loadTimeThread.setTimeOut(paramBoolean);
    }
  }
  
  public void updataLoadTime(int paramInt)
    throws NullPointerException
  {
    try
    {
      this.loadTime.setProgress(paramInt);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      throw new NullPointerException();
    }
  }
  
  private class Onclack
    implements View.OnClickListener
  {
    private RovModeConvert mode_rov;
    
    private Onclack() {}
    
    public void onClick(View paramView)
    {
      this.mode_rov = RovModeConvert.getRovModeConvert();
      if (paramView.getId() == 2131361826)
      {
        this.mode_rov.setModel(-98);
        this.mode_rov.FunctionStop();
        modeSelection.this.explainShow.setVisibility(8);
      }
    }
  }
  
  private class ToggleButtonOnclack
    implements View.OnClickListener
  {
    private ToggleButtonOnclack() {}
    
    public void onClick(View paramView)
    {
      boolean bool = ((ToggleButton)paramView).isChecked();
      for (;;)
      {
        try
        {
          RovModeConvert localRovModeConvert = RovModeConvert.getRovModeConvert();
          switch (paramView.getId())
          {
          default: 
            localRovModeConvert.setNowMode(localRovModeConvert.Model);
            localRovModeConvert.FunctionStop();
            return;
          }
        }
        catch (NullPointerException paramView)
        {
          return;
        }
        RovInitData.Inertia = bool;
        com.deepinfar.Task.RovTask.LoadTimerData.HandoverStart = true;
        modeSelection.this.explainShow.setVisibility(8);
        continue;
        RovInitData.Course = bool;
      }
    }
  }
  
  private class loadTimeThread
    implements Runnable
  {
    private long LoadTime;
    private int NowProgress;
    private long NowTime;
    private double Scale;
    private boolean TimeOut = true;
    private boolean ViewOnclack = false;
    
    private loadTimeThread(long paramLong, boolean paramBoolean)
    {
      this.LoadTime = paramLong;
      this.Scale = (paramLong / 100.0D);
      this.ViewOnclack = paramBoolean;
    }
    
    public void ThreadSleep(long paramLong)
    {
      try
      {
        Thread.sleep(paramLong);
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        Log.e("Error", "ModeSelection 更新等待进度条失败");
      }
    }
    
    public long getLoadTime()
    {
      return this.LoadTime;
    }
    
    public int getNowProgress()
    {
      return this.NowProgress;
    }
    
    public long getNowTime()
    {
      return this.NowTime;
    }
    
    public double getScale()
    {
      return this.Scale;
    }
    
    public boolean isTimeOut()
    {
      return this.TimeOut;
    }
    
    public void restartTime(long paramLong, boolean paramBoolean)
    {
      this.LoadTime = paramLong;
      this.Scale = (this.LoadTime / 100.0D);
      this.ViewOnclack = paramBoolean;
      this.NowTime = 0L;
      this.NowProgress = 0;
      this.TimeOut = false;
    }
    
    public void run()
    {
      int j = 1;
      for (;;)
      {
        try
        {
          this.TimeOut = false;
          Handler localHandler = modeSelection.this.getUpdataUI();
          Message localMessage = new Message();
          localMessage.what = 2;
          this.NowTime = 0L;
          this.NowProgress = 0;
          localMessage.arg1 = this.NowProgress;
          if (this.ViewOnclack)
          {
            i = 1;
            localMessage.arg2 = i;
            localHandler.sendMessage(localMessage);
            if ((this.NowTime + this.Scale >= this.LoadTime) || (this.TimeOut) || (this.NowProgress >= 100))
            {
              localMessage = new Message();
              this.NowTime = this.LoadTime;
              localMessage.what = 2;
              this.NowProgress = 100;
              localMessage.arg1 = this.NowProgress;
              if (!this.ViewOnclack) {
                break label275;
              }
              i = j;
              localMessage.arg2 = i;
              localHandler.sendMessage(localMessage);
              this.TimeOut = true;
            }
          }
          else
          {
            i = 0;
            continue;
          }
          ThreadSleep((int)this.Scale);
          this.NowTime = ((this.NowTime + this.Scale));
          localMessage = new Message();
          localMessage.what = 2;
          this.NowProgress += 1;
          localMessage.arg1 = this.NowProgress;
          if (this.ViewOnclack)
          {
            i = 1;
            localMessage.arg2 = i;
            localHandler.sendMessage(localMessage);
            continue;
          }
          i = 0;
        }
        finally {}
        continue;
        label275:
        int i = 0;
      }
    }
    
    public void setLoadTime(long paramLong)
    {
      this.LoadTime = paramLong;
    }
    
    public void setNowProgress(int paramInt)
    {
      this.NowProgress = paramInt;
    }
    
    public void setNowTime(long paramLong)
    {
      this.NowTime = paramLong;
    }
    
    public void setScale(double paramDouble)
    {
      this.Scale = paramDouble;
    }
    
    public void setTimeOut(boolean paramBoolean)
    {
      this.TimeOut = paramBoolean;
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Dialog/modeSelection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */