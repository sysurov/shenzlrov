package com.deepinfar.rov;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnGenericMotionListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.deepinfar.Dialog.LinkDialog;
import com.deepinfar.Dialog.LsattrDialog;
import com.deepinfar.Dialog.PlaybackDialog;
import com.deepinfar.Dialog.RegardingDialog;
import com.deepinfar.Dialog.RovDialog;
import com.deepinfar.Dialog.modeSelection;
import com.deepinfar.Dialog.settingDialog;
import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.Task.Function;
import com.deepinfar.Task.FunctionAdapter;
import com.deepinfar.adapter.ParameterSettingsList;
import com.tools.Toast.ToastClass;
import com.tools.ViewTools.Button.LongClickButton;
import com.tools.ViewTools.Button.LongClickButton.LongClickRepeatListener;
import com.tools.ViewTools.RockerView;
import com.tools.ViewTools.RockerView.RockerChangeListener;
import com.tools.file.FileDataUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainFragment
  extends Fragment
{
  private static boolean CruisoBoolean = false;
  public static final String INFO_DIALOG = "infoDialog";
  public static final String LINK_DIALOG = "linkDialog";
  public static String LiftNum;
  public static String LiftString = "LiftString";
  public static final String Lsattr_DIALOG = "lsattrDialog";
  public static final String MAIN_DATA = "MAIN";
  public static final String MODE_DIALOG = "modeDialog";
  public static final String PLAYBACK_DIALOG = "playbackDialog";
  public static String RightNum;
  public static String RightString = "RightString";
  public static String Rov_Model;
  public static final String SETTING_DIALOG = "settingDialog";
  private static MainFragment mainFragment;
  private static View rootView;
  public Button CameraButton;
  public Handler DataSendHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 1)
      {
        float f1 = MainFragment.this.sendRovData.getAXIS_X();
        float f2 = MainFragment.this.sendRovData.getAXIS_Y();
        MainFragment.this.DirectionView.UpdataRocketView(f1, f2);
        f1 = MainFragment.this.sendRovData.getAXIS_Z();
        f2 = MainFragment.this.sendRovData.getAXIS_RZ();
        MainFragment.this.RollView.UpdataRocketView(f1, f2);
      }
    }
  };
  public Timer DataSendTimer = new Timer();
  public LongClickButton DeepAdd;
  public LongClickButton DeepSub;
  public RockerView DirectionView;
  public LinearLayout DownMenu;
  private boolean KeyDownSit = false;
  public LinearLayout LeftLayout;
  public Button LinkOrSettingButton;
  public Button NetbodButton;
  public Button RePlayButton;
  LongClickButton.LongClickRepeatListener RepeatListener = new LongClickButton.LongClickRepeatListener()
  {
    public void repeatAction(int paramAnonymousInt, View paramAnonymousView)
    {
      MainFragment.this.updataDeep(20, paramAnonymousView);
    }
  };
  public RockerView RollView;
  public Button SettingButton;
  public Button Show_SW;
  public Handler ToastHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 1)
      {
        paramAnonymousMessage = (String)paramAnonymousMessage.obj;
        MainFragment.this.toastClass.ToastshowString(paramAnonymousMessage);
      }
    }
  };
  public Button VideoButton;
  public RovDialog dialog;
  public FileDataUtils fileDataUtils;
  public Button infoButton;
  public Handler mainHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      for (;;)
      {
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
        paramAnonymousMessage = new Intent();
        paramAnonymousMessage.setClass(StartActivity.getStartAvtivity(), M_ROV_Activity.class);
        StartActivity.getStartAvtivity().finish();
        if (StartActivity.getStartAvtivity().isFinishing())
        {
          StartActivity.getStartAvtivity().startActivity(paramAnonymousMessage);
          StartActivity.getStartAvtivity().finish();
          continue;
          paramAnonymousMessage = (String)paramAnonymousMessage.obj;
          MainFragment.this.toastClass.ToastshowString(paramAnonymousMessage);
        }
      }
    }
  };
  private View.OnClickListener mainOnclack;
  public Map<String, String> map = null;
  public NetWork netWork;
  View.OnLongClickListener onLongClickListener = new View.OnLongClickListener()
  {
    public boolean onLongClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default: 
        return true;
      case 2131361840: 
        MainFragment.this.dialog = LinkDialog.newInstance(MainFragment.this.mainHandler, MainFragment.this.getFragmentManager(), "linkDialog", true);
        MainFragment.this.dialog.show(MainFragment.this.getFragmentManager(), "linkDialog");
        return true;
      }
      MainFragment.this.dialog = LinkDialog.newInstance(MainFragment.this.mainHandler, MainFragment.this.getFragmentManager(), "settingDialog", true);
      MainFragment.this.dialog.show(MainFragment.this.getFragmentManager(), "settingDialog");
      return true;
    }
  };
  private View.OnGenericMotionListener rockerDirection;
  public SendRovData sendRovData;
  public ToastClass toastClass;
  
  static
  {
    LiftNum = "LiftNum";
    RightNum = "RightNum";
    Rov_Model = "Rov_Model";
  }
  
  public static View getMainFragmentView()
  {
    return rootView;
  }
  
  public static MainFragment getMainView()
  {
    return mainFragment;
  }
  
  private void init(View paramView)
  {
    this.Show_SW = ((Button)paramView.findViewById(2131361846));
    this.NetbodButton = ((Button)paramView.findViewById(2131361839));
    this.LinkOrSettingButton = ((Button)paramView.findViewById(2131361840));
    this.infoButton = ((Button)paramView.findViewById(2131361842));
    this.VideoButton = ((Button)paramView.findViewById(2131361844));
    this.SettingButton = ((Button)paramView.findViewById(2131361841));
    this.RePlayButton = ((Button)paramView.findViewById(2131361843));
    this.CameraButton = ((Button)paramView.findViewById(2131361845));
    this.DeepAdd = ((LongClickButton)paramView.findViewById(2131361861));
    this.DeepSub = ((LongClickButton)paramView.findViewById(2131361862));
    this.LeftLayout = ((LinearLayout)paramView.findViewById(2131361860));
    this.DownMenu = ((LinearLayout)paramView.findViewById(2131361838));
    this.DirectionView = ((RockerView)paramView.findViewById(2131361859));
    this.DirectionView.setRockerChangeListener(new RockerView.RockerChangeListener()
    {
      public void report(float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        int i = (int)(paramAnonymousFloat1 / 144.0F * 100.0F);
        int j = (int)(paramAnonymousFloat2 / 144.0F * 100.0F);
        MainFragment.this.sendRovData.setAXIS_X(i);
        MainFragment.this.sendRovData.setAXIS_Y(j);
      }
    });
    this.RollView = ((RockerView)paramView.findViewById(2131361858));
    this.RollView.setRockerChangeListener(new RockerView.RockerChangeListener()
    {
      public void report(float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        int i = (int)(paramAnonymousFloat1 / 144.0F * 100.0F);
        int j = (int)(paramAnonymousFloat2 / 144.0F * 100.0F);
        MainFragment.this.sendRovData.setAXIS_Z(i);
        MainFragment.this.sendRovData.setAXIS_RZ(j);
      }
    });
    this.mainOnclack = new MainOnclack(getActivity());
    this.Show_SW.setOnClickListener(this.mainOnclack);
    this.NetbodButton.setOnClickListener(this.mainOnclack);
    this.LinkOrSettingButton.setOnClickListener(this.mainOnclack);
    this.LinkOrSettingButton.setOnLongClickListener(this.onLongClickListener);
    this.CameraButton.setOnClickListener(this.mainOnclack);
    this.VideoButton.setOnClickListener(this.mainOnclack);
    this.SettingButton.setOnClickListener(this.mainOnclack);
    this.SettingButton.setOnLongClickListener(this.onLongClickListener);
    this.RePlayButton.setOnClickListener(this.mainOnclack);
    this.infoButton.setOnClickListener(this.mainOnclack);
    this.DeepAdd.setLongClickRepeatListener(this.RepeatListener);
    this.DeepSub.setLongClickRepeatListener(this.RepeatListener);
    this.DeepAdd.setOnClickListener(this.mainOnclack);
    this.DeepSub.setOnClickListener(this.mainOnclack);
  }
  
  public static boolean isCruisoBoolean()
  {
    return CruisoBoolean;
  }
  
  public static void setCruisoBoolean(boolean paramBoolean)
  {
    CruisoBoolean = paramBoolean;
  }
  
  public static void setView(View paramView)
  {
    rootView = paramView;
  }
  
  private void updataDeep(int paramInt, View paramView)
  {
    try
    {
      this.fileDataUtils = FileDataUtils.getFileDataUtils();
      FunctionAdapter localFunctionAdapter = FunctionAdapter.getFunctionAdapter();
      switch (paramView.getId())
      {
      default: 
        this.toastClass.ToastshowString("按钮异常");
        return;
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
      paramView = localIOException.getFunctions(this.fileDataUtils.getTagData("Y"));
    }
    for (;;)
    {
      paramView.FunctionStart(Integer.parseInt(this.fileDataUtils.getTagData("左")), paramInt);
      return;
      paramView = localIOException.getFunctions(this.fileDataUtils.getTagData("A"));
    }
  }
  
  public RovDialog getDialog()
  {
    return this.dialog;
  }
  
  public LinearLayout getDownMenu()
  {
    return this.DownMenu;
  }
  
  public Handler getMainHandler()
  {
    return this.mainHandler;
  }
  
  public int getMapInt(Map<String, String> paramMap, String paramString)
  {
    paramMap = getMapString(paramMap, paramString);
    if (paramMap.equals("")) {
      return -1;
    }
    try
    {
      int i = Integer.parseInt(paramMap);
      return i;
    }
    catch (NumberFormatException paramMap) {}
    return -1;
  }
  
  public String getMapString(Map<String, String> paramMap, String paramString)
  {
    paramString = (String)paramMap.get(paramString);
    if (paramString != null)
    {
      paramMap = paramString;
      if (!paramString.equals("")) {}
    }
    else
    {
      paramMap = "";
    }
    return paramMap;
  }
  
  public View.OnGenericMotionListener getRockerDirection()
  {
    return this.rockerDirection;
  }
  
  public Button getVideoButton()
  {
    return this.VideoButton;
  }
  
  @SuppressLint({"NewApi"})
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (rootView == null) {
      rootView = paramLayoutInflater.inflate(2130903052, null);
    }
    paramLayoutInflater = (ViewGroup)rootView.getParent();
    if (paramLayoutInflater != null) {
      paramLayoutInflater.removeView(rootView);
    }
    try
    {
      this.toastClass = ToastClass.getToastClass();
    }
    catch (NullPointerException paramLayoutInflater)
    {
      try
      {
        for (;;)
        {
          this.fileDataUtils = FileDataUtils.getFileDataUtils();
          new Thread(new Runnable()
          {
            public void run()
            {
              MainFragment.this.netWork = NetWork.getNetwork(MainFragment.this.getActivity(), MainFragment.this.ToastHandler, 60000);
              MainFragment.this.sendRovData = MainFragment.this.netWork.getSendRovData();
            }
          }).start();
          while (this.sendRovData == null) {}
          init(rootView);
          this.DataSendTimer.schedule(new TimerTask()
          {
            public void run()
            {
              MainFragment.this.DataSendHandler.sendEmptyMessage(1);
            }
          }, 100L, 50L);
          int i = Integer.parseInt(this.fileDataUtils.getTagData("界面透明度"));
          rootView.setAlpha((float)(i / 100.0D + ParameterSettingsList.MIN_SEEKBAR / 100.0D));
          this.rockerDirection = new RockerDirection();
          rootView.setFocusable(true);
          rootView.setFocusableInTouchMode(true);
          rootView.requestFocus();
          rootView.setOnGenericMotionListener(this.rockerDirection);
          mainFragment = this;
          if ((!LsattrDialog.bMenuBar) || (!LsattrDialog.bMainSwitch)) {
            this.DownMenu.setVisibility(8);
          }
          return rootView;
          paramLayoutInflater = paramLayoutInflater;
          this.toastClass = ToastClass.getToastClass(getActivity(), this.ToastHandler);
        }
      }
      catch (FileNotFoundException paramLayoutInflater)
      {
        for (;;)
        {
          this.toastClass.ToastshowString("文件打开异常");
          paramLayoutInflater.printStackTrace();
        }
      }
      catch (IOException paramLayoutInflater)
      {
        for (;;)
        {
          this.toastClass.ToastshowString("文件打开异常");
          paramLayoutInflater.printStackTrace();
        }
      }
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  class MainOnclack
    implements View.OnClickListener
  {
    public int VideoStartOrStop = -1;
    public Context context;
    
    public MainOnclack(Context paramContext)
    {
      this.context = paramContext;
    }
    
    @SuppressLint({"NewApi"})
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131361847: 
      case 2131361848: 
      case 2131361849: 
      case 2131361850: 
      case 2131361851: 
      case 2131361852: 
      case 2131361853: 
      case 2131361854: 
      case 2131361855: 
      case 2131361856: 
      case 2131361857: 
      case 2131361858: 
      case 2131361859: 
      case 2131361860: 
      default: 
        return;
      case 2131361846: 
        MainFragment.this.dialog = LsattrDialog.newInstance(MainFragment.this.mainHandler, MainFragment.this.getFragmentManager(), "lsattrDialog");
        MainFragment.this.dialog.show(MainFragment.this.getFragmentManager(), "lsattrDialog");
        return;
      case 2131361839: 
        MainFragment.this.dialog = modeSelection.newInstance(MainFragment.this.mainHandler, MainFragment.this.getFragmentManager(), "modeDialog");
        MainFragment.this.dialog.show(MainFragment.this.getFragmentManager(), "modeDialog");
        return;
      case 2131361840: 
        MainFragment.this.dialog = LinkDialog.newInstance(MainFragment.this.mainHandler, MainFragment.this.getFragmentManager(), "linkDialog");
        MainFragment.this.dialog.show(MainFragment.this.getFragmentManager(), "linkDialog");
        return;
      case 2131361841: 
        MainFragment.this.dialog = settingDialog.newInstance(MainFragment.this.mainHandler, MainFragment.this.getFragmentManager(), "settingDialog");
        MainFragment.this.dialog.show(MainFragment.this.getFragmentManager(), "settingDialog");
        return;
      case 2131361842: 
        MainFragment.this.dialog = RegardingDialog.newInstance(MainFragment.this.mainHandler, MainFragment.this.getFragmentManager(), "infoDialog");
        MainFragment.this.dialog.show(MainFragment.this.getFragmentManager(), "infoDialog");
        return;
      case 2131361844: 
        VideoPlayerFragment.getVideoPlayerFragment().Record(paramView);
        return;
      case 2131361843: 
        MainFragment.this.dialog = PlaybackDialog.newInstance(StartActivity.getStartAvtivity(), MainFragment.this.mainHandler, MainFragment.this.getFragmentManager(), "playbackDialog");
        MainFragment.this.dialog.show(MainFragment.this.getFragmentManager(), "playbackDialog");
        return;
      case 2131361845: 
        VideoPlayerFragment.getVideoPlayerFragment().SavePhoto();
        return;
      case 2131361861: 
        MainFragment.this.updataDeep(20, paramView);
        return;
      }
      MainFragment.this.updataDeep(20, paramView);
    }
  }
  
  class RockerDirection
    implements View.OnGenericMotionListener
  {
    RockerDirection() {}
    
    public boolean onGenericMotion(View paramView, MotionEvent paramMotionEvent)
    {
      float f1 = paramMotionEvent.getAxisValue(11);
      float f2 = paramMotionEvent.getAxisValue(14);
      int i = (int)(f1 * 100.0F);
      int j = (int)(f2 * 100.0F);
      MainFragment.this.sendRovData.setAXIS_Z(i);
      MainFragment.this.sendRovData.setAXIS_RZ(j);
      f1 = paramMotionEvent.getAxisValue(0);
      f2 = paramMotionEvent.getAxisValue(1);
      i = (int)(f1 * 100.0F);
      j = (int)(f2 * 100.0F);
      if ((i == 0) && (j == 0))
      {
        MainFragment.this.sendRovData.setAXIS_X(0);
        MainFragment.this.sendRovData.setAXIS_Y(0);
        return false;
      }
      MainFragment.this.sendRovData.setAXIS_X(i);
      MainFragment.this.sendRovData.setAXIS_Y(j);
      return true;
    }
  }
  
  public class SeeBarSendData
  {
    private Timer SeeBarTimer = new Timer();
    int standardNum = 0;
    
    public SeeBarSendData() {}
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/MainFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */