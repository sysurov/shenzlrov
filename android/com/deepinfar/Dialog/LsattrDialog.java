package com.deepinfar.Dialog;

import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.VideoData;
import com.deepinfar.rov.Alarm_Fragment;
import com.deepinfar.rov.ControlFragment;
import com.deepinfar.rov.MainFragment;
import com.deepinfar.rov.ReceiveFrafment;
import com.tools.ViewTools.Switch.SlideSwitchView;
import com.tools.ViewTools.Switch.SlideSwitchView.OnSwitchChangedListener;

public class LsattrDialog
  extends RovDialog
  implements SlideSwitchView.OnSwitchChangedListener
{
  public static boolean bAlarmShow = true;
  public static boolean bControlSwitch;
  public static boolean bMainSwitch = true;
  public static boolean bMenuBar;
  public static boolean bOSDShow;
  public static boolean bReceiveDataSwitch;
  public SlideSwitchView AlarmShow;
  public SlideSwitchView ControlSwitch;
  public SlideSwitchView MainSwitch;
  public SlideSwitchView MenuBar;
  public SlideSwitchView OSDShow;
  public SlideSwitchView ReceiveDataSwitch;
  
  static
  {
    bControlSwitch = true;
    bReceiveDataSwitch = true;
    bMenuBar = true;
    bOSDShow = false;
  }
  
  public static final RovDialog newInstance(Handler paramHandler, FragmentManager paramFragmentManager, String paramString)
  {
    LsattrDialog localLsattrDialog = new LsattrDialog();
    RovDialog.newInstance(localLsattrDialog, paramHandler, paramFragmentManager, paramString);
    return localLsattrDialog;
  }
  
  public void AlarmVisibility(boolean paramBoolean)
  {
    Alarm_Fragment localAlarm_Fragment = Alarm_Fragment.getAlarm_Fragment();
    if (!paramBoolean) {
      localAlarm_Fragment.getRootViewUpdataUI().sendEmptyMessage(-1);
    }
    while (bAlarmShow) {
      return;
    }
    localAlarm_Fragment.getRootViewUpdataUI().sendEmptyMessage(-1);
    localAlarm_Fragment.setShow(bAlarmShow);
  }
  
  public void ControlVisibility(boolean paramBoolean)
  {
    View localView = ControlFragment.getRootView();
    if (!paramBoolean)
    {
      localView.setVisibility(8);
      return;
    }
    if (bControlSwitch)
    {
      localView.setVisibility(0);
      return;
    }
    localView.setVisibility(8);
  }
  
  public void MainVisibility()
  {
    if (bMainSwitch)
    {
      ControlVisibility(true);
      ReceiveVisibility(true);
      MenuBarVisibility(true);
      OSDVisibility(true);
    }
    for (;;)
    {
      AlarmVisibility(true);
      return;
      ControlVisibility(false);
      ReceiveVisibility(false);
      MenuBarVisibility(false);
      OSDVisibility(false);
    }
  }
  
  public void MenuBarVisibility(boolean paramBoolean)
  {
    LinearLayout localLinearLayout = MainFragment.getMainView().getDownMenu();
    if (!paramBoolean)
    {
      localLinearLayout.setVisibility(8);
      return;
    }
    if (bMenuBar)
    {
      localLinearLayout.setVisibility(0);
      return;
    }
    localLinearLayout.setVisibility(8);
  }
  
  public void OSDVisibility(boolean paramBoolean)
  {
    SendRovData.VideoData localVideoData = NetWork.getNetwork().getSendRovData().getVideoData("MAIN");
    if (!paramBoolean)
    {
      localVideoData.UpdataData("OSD_SW", Integer.valueOf(0));
      return;
    }
    if (bOSDShow)
    {
      localVideoData.UpdataData("OSD_SW", Integer.valueOf(1));
      this.ReceiveDataSwitch.setChecked(false);
      bReceiveDataSwitch = false;
      ReceiveVisibility(true);
      return;
    }
    localVideoData.UpdataData("OSD_SW", Integer.valueOf(0));
  }
  
  public void ReceiveVisibility(boolean paramBoolean)
  {
    View localView = ReceiveFrafment.getRootView();
    if (!paramBoolean)
    {
      localView.setVisibility(8);
      return;
    }
    if (bReceiveDataSwitch)
    {
      localView.setVisibility(0);
      return;
    }
    localView.setVisibility(8);
  }
  
  public View init(View paramView)
  {
    this.MainSwitch = ((SlideSwitchView)paramView.findViewById(2131361812));
    this.ControlSwitch = ((SlideSwitchView)paramView.findViewById(2131361813));
    this.ReceiveDataSwitch = ((SlideSwitchView)paramView.findViewById(2131361814));
    this.MenuBar = ((SlideSwitchView)paramView.findViewById(2131361815));
    this.OSDShow = ((SlideSwitchView)paramView.findViewById(2131361816));
    this.AlarmShow = ((SlideSwitchView)paramView.findViewById(2131361817));
    this.MainSwitch.setOnChangeListener(this);
    this.ControlSwitch.setOnChangeListener(this);
    this.ReceiveDataSwitch.setOnChangeListener(this);
    this.MenuBar.setOnChangeListener(this);
    this.OSDShow.setOnChangeListener(this);
    this.AlarmShow.setOnChangeListener(this);
    updataEnabled();
    updataActive();
    return super.init(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    getDialog().requestWindowFeature(1);
    paramLayoutInflater = paramLayoutInflater.inflate(2130903047, null);
    init(paramLayoutInflater);
    return paramLayoutInflater;
  }
  
  public void onResume()
  {
    super.onResume();
    getDialog().getWindow().setLayout(1000, 800);
    if ((this.tag == null) || (this.tag == "null")) {
      dismiss();
    }
  }
  
  public void onSwitchChange(SlideSwitchView paramSlideSwitchView, boolean paramBoolean)
  {
    switch (paramSlideSwitchView.getId())
    {
    }
    for (;;)
    {
      updataEnabled();
      MainVisibility();
      return;
      bMainSwitch = paramBoolean;
      continue;
      bControlSwitch = paramBoolean;
      continue;
      bReceiveDataSwitch = paramBoolean;
      continue;
      bMenuBar = paramBoolean;
      continue;
      bOSDShow = paramBoolean;
      continue;
      bAlarmShow = paramBoolean;
    }
  }
  
  public void updataActive()
  {
    this.MainSwitch.setChecked(bMainSwitch);
    this.ControlSwitch.setChecked(bControlSwitch);
    this.ReceiveDataSwitch.setChecked(bReceiveDataSwitch);
    this.MenuBar.setChecked(bMenuBar);
    this.OSDShow.setChecked(bOSDShow);
    this.AlarmShow.setChecked(bAlarmShow);
  }
  
  public void updataEnabled()
  {
    this.ControlSwitch.setEnabled(bMainSwitch);
    this.ReceiveDataSwitch.setEnabled(bMainSwitch);
    this.MenuBar.setEnabled(bMainSwitch);
    this.OSDShow.setEnabled(bMainSwitch);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Dialog/LsattrDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */