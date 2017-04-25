package com.deepinfar.rov;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.deepinfar.Dialog.LsattrDialog;
import com.deepinfar.adapter.ListUpdataUI;
import com.deepinfar.adapter.ReveiveAdapter;
import com.tools.ListViewTools.HorizontalListView;
import com.tools.wifiTools.WifiIntensity;
import java.util.Timer;
import java.util.TimerTask;

public class ReceiveFrafment
  extends Fragment
{
  public static ReceiveFrafment receiveFrafment;
  public static View rootView = null;
  public ImageView RunState;
  public ImageView WifiSignal;
  public ReveiveAdapter adapter;
  public HorizontalListView dataList;
  ListUpdataUI dataListUpdataUI;
  ListUpdataUI hListViewUpdataUI;
  public Handler mainHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      for (;;)
      {
        label64:
        try
        {
          int i = paramAnonymousMessage.what;
          switch (i)
          {
          default: 
            return;
          }
        }
        finally {}
        switch (paramAnonymousMessage.arg1)
        {
        case 1: 
          ReceiveFrafment.this.WifiSignal.setImageResource(2130837553);
          continue;
          ReceiveFrafment.this.WifiSignal.setImageResource(2130837556);
          break;
        case 2: 
          ReceiveFrafment.this.WifiSignal.setImageResource(2130837555);
          break;
        case 3: 
          ReceiveFrafment.this.WifiSignal.setImageResource(2130837554);
        }
      }
      switch (paramAnonymousMessage.arg1)
      {
      }
      for (;;)
      {
        ReceiveFrafment.this.RunState.setImageResource(2130837536);
        break;
        ReceiveFrafment.this.RunState.setImageResource(2130837536);
        break;
        ReceiveFrafment.this.RunState.setImageResource(2130837535);
        break;
        ReceiveFrafment.this.RunState.setImageResource(2130837534);
        break;
        break label64;
      }
    }
  };
  View olderSelectView = null;
  public final String[] readIndex = { "12,13", "10,11", "34,35" };
  public final double[] readScale = { 10.0D, 10.0D, 100.0D };
  public final double[] sendScale = { 10.0D, 10.0D, 100.0D };
  public final String[] sendString = { "Azimuth_Posture", "Pitching_Posture", "Z_Translation" };
  public TimerTask timerTask = new TimerTask()
  {
    public void run()
    {
      if (ReceiveFrafment.this.hListViewUpdataUI != null) {
        ReceiveFrafment.this.hListViewUpdataUI.onListUpdataUI();
      }
      if (ReceiveFrafment.this.dataListUpdataUI != null) {
        ReceiveFrafment.this.dataListUpdataUI.onListUpdataUI();
      }
    }
  };
  public Timer timerUpdata = new Timer();
  public WifiIntensity wifiIntensity;
  
  public static ReceiveFrafment getReceiveFrafment()
  {
    return receiveFrafment;
  }
  
  public static View getRootView()
  {
    return rootView;
  }
  
  public Handler getMainHandler()
  {
    return this.mainHandler;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (rootView == null) {
      rootView = paramLayoutInflater.inflate(2130903053, null);
    }
    paramLayoutInflater = (ViewGroup)rootView.getParent();
    if (paramLayoutInflater != null) {
      paramLayoutInflater.removeView(rootView);
    }
    this.dataList = ((HorizontalListView)rootView.findViewById(2131361847));
    this.wifiIntensity = new WifiIntensity(this.mainHandler, getActivity());
    this.WifiSignal = ((ImageView)rootView.findViewById(2131361850));
    this.RunState = ((ImageView)rootView.findViewById(2131361851));
    this.dataList.setEnabled(false);
    this.adapter = new ReveiveAdapter(getActivity());
    this.dataList.setAdapter(this.adapter);
    this.dataListUpdataUI = this.adapter.getListUpdataUI();
    this.timerUpdata.schedule(this.timerTask, 500L, 100L);
    if ((!LsattrDialog.bReceiveDataSwitch) || (!LsattrDialog.bMainSwitch)) {
      rootView.setVisibility(8);
    }
    receiveFrafment = this;
    return rootView;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/ReceiveFrafment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */