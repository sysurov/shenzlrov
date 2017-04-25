package com.deepinfar.rov;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.deepinfar.Dialog.LsattrDialog;

public class ControlFragment
  extends Fragment
{
  public static ControlFragment ControlFragment;
  public static View RootView;
  
  public static ControlFragment getControlFragment()
  {
    return ControlFragment;
  }
  
  public static View getRootView()
  {
    return RootView;
  }
  
  private View inflateAndSetupView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle, int paramInt)
  {
    return paramLayoutInflater.inflate(paramInt, paramViewGroup, false);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    RootView = inflateAndSetupView(paramLayoutInflater, paramViewGroup, paramBundle, 2130903056);
    ControlFragment = this;
    if ((!LsattrDialog.bControlSwitch) || (!LsattrDialog.bMainSwitch)) {
      RootView.setVisibility(8);
    }
    return RootView;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/ControlFragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */