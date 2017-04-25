package com.deepinfar.rov;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.deepinfar.Dialog.LsattrDialog;
import com.tools.Image.ImageOperate;

public class Alarm_Fragment
  extends Fragment
{
  public static final int AlarmGone = 0;
  public static Alarm_Fragment alarm_Fragment;
  public static final int bFireShow = 2;
  public static final int bWaterShow = 1;
  public static final int critical_Fire = 6000;
  public static final int critical_Humidity = 60;
  public static final int humidity_pressure = 800;
  public static View rootView = null;
  private ImageView ImAlarm;
  private TextView alarmExplain;
  public Bitmap bFire;
  public Bitmap bWater;
  public Handler rootViewUpdataUI = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (!LsattrDialog.bAlarmShow)
      {
        Alarm_Fragment.rootView.setVisibility(8);
        return;
      }
      switch (paramAnonymousMessage.what)
      {
      default: 
        return;
      case 0: 
        Alarm_Fragment.rootView.setVisibility(8);
        Alarm_Fragment.this.show = false;
        return;
      case 1: 
        Alarm_Fragment.this.updataImage(Alarm_Fragment.this.getbWater());
        Alarm_Fragment.this.alarmExplain.setText("ROV可能漏水了");
        Alarm_Fragment.rootView.setVisibility(0);
        Alarm_Fragment.this.show = true;
        return;
      }
      Alarm_Fragment.this.updataImage(Alarm_Fragment.this.getbFire());
      Alarm_Fragment.this.alarmExplain.setText("ROV可能过热了");
      Alarm_Fragment.rootView.setVisibility(0);
      Alarm_Fragment.this.show = true;
    }
  };
  private boolean show = false;
  
  public static Alarm_Fragment getAlarm_Fragment()
  {
    return alarm_Fragment;
  }
  
  public Handler getRootViewUpdataUI()
  {
    return this.rootViewUpdataUI;
  }
  
  public Bitmap getbFire()
  {
    return this.bFire;
  }
  
  public Bitmap getbWater()
  {
    return this.bWater;
  }
  
  public boolean isShow()
  {
    return this.show;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (rootView == null) {
      rootView = paramLayoutInflater.inflate(2130903055, null);
    }
    paramLayoutInflater = (ViewGroup)rootView.getParent();
    if (paramLayoutInflater != null) {
      paramLayoutInflater.removeView(rootView);
    }
    this.ImAlarm = ((ImageView)rootView.findViewById(2131361856));
    this.alarmExplain = ((TextView)rootView.findViewById(2131361857));
    rootView.setVisibility(8);
    this.bWater = ImageOperate.resIdConvertBitmap(getActivity(), 2130837552);
    this.bFire = ImageOperate.resIdConvertBitmap(getActivity(), 2130837517);
    alarm_Fragment = this;
    return rootView;
  }
  
  public void setShow(boolean paramBoolean)
  {
    this.show = paramBoolean;
  }
  
  public void setbFire(Bitmap paramBitmap)
  {
    this.bFire = paramBitmap;
  }
  
  public void setbWater(Bitmap paramBitmap)
  {
    this.bWater = paramBitmap;
  }
  
  public void updataImage(Bitmap paramBitmap)
  {
    this.ImAlarm.setImageBitmap(paramBitmap);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/Alarm_Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */