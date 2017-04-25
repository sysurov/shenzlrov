package com.deepinfar.Dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnGenericMotionListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.deepinfar.adapter.ParameterSettingsList;
import com.deepinfar.rov.MainFragment;
import com.deepinfar.rov.StartActivity;
import com.tools.file.FileDataUtils;
import java.io.FileNotFoundException;
import java.io.IOException;

public class RovDialog
  extends DialogFragment
  implements View.OnGenericMotionListener, View.OnKeyListener
{
  public static DialogFragment Dialog;
  public static Dialog vDialog;
  public float Init_Alpha = 0.5F;
  public FileDataUtils fileDataUtils = null;
  public Handler mainHandler;
  public FragmentManager manager;
  public String tag;
  
  public static Dialog getVDialog()
  {
    return vDialog;
  }
  
  public static RovDialog newInstance(RovDialog paramRovDialog, Handler paramHandler, FragmentManager paramFragmentManager, String paramString)
  {
    paramRovDialog.mainHandler = paramHandler;
    paramRovDialog.tag = paramString;
    paramRovDialog.manager = paramFragmentManager;
    try
    {
      paramRovDialog.fileDataUtils = FileDataUtils.getFileDataUtils();
      Dialog = paramRovDialog;
      return paramRovDialog;
    }
    catch (FileNotFoundException paramHandler)
    {
      for (;;)
      {
        paramRovDialog.fileDataUtils = null;
      }
    }
    catch (IOException paramHandler)
    {
      for (;;)
      {
        paramRovDialog.fileDataUtils = null;
      }
    }
  }
  
  public String getDialogTag()
  {
    return this.tag;
  }
  
  public View init(View paramView)
  {
    paramView.setFocusable(true);
    paramView.setFocusableInTouchMode(true);
    paramView.requestFocus();
    paramView.setOnGenericMotionListener(this);
    paramView.setOnKeyListener(this);
    return paramView;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramViewGroup;
  }
  
  public void onDestroy()
  {
    this.tag = "null";
    super.onDestroy();
  }
  
  public void onDetach()
  {
    Log.i("onDetach", "onDetach");
    super.onDetach();
  }
  
  public boolean onGenericMotion(View paramView, MotionEvent paramMotionEvent)
  {
    return MainFragment.getMainView().getRockerDirection().onGenericMotion(paramView, paramMotionEvent);
  }
  
  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    paramView = StartActivity.getStartAvtivity();
    switch (paramKeyEvent.getAction())
    {
    default: 
      return true;
    case 0: 
      return paramView.onKeyDown(paramInt, paramKeyEvent);
    }
    return paramView.onKeyUp(paramInt, paramKeyEvent);
  }
  
  public void onResume()
  {
    WindowManager.LayoutParams localLayoutParams = getDialog().getWindow().getAttributes();
    getDialog().getWindow().setFlags(4, 4);
    getDialog().getWindow().setAttributes(localLayoutParams);
    if (this.fileDataUtils == null) {}
    for (localLayoutParams.alpha = 0.5F;; localLayoutParams.alpha = ((float)(f + ParameterSettingsList.MIN_SEEKBAR / 100.0D)))
    {
      super.onResume();
      return;
      String str = this.fileDataUtils.getTagData("窗口透明度");
      float f = this.Init_Alpha;
      try
      {
        int i = Integer.parseInt(str);
        f = i / 100.0F;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          f = this.Init_Alpha;
        }
      }
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Dialog/RovDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */