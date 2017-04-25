package com.deepinfar.rov;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Help_1Fragment
  extends Fragment
{
  private View inflateAndSetupView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle, int paramInt)
  {
    return paramLayoutInflater.inflate(paramInt, paramViewGroup, false);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return inflateAndSetupView(paramLayoutInflater, paramViewGroup, paramBundle, 2130903057);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/Help_1Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */