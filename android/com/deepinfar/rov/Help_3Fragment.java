package com.deepinfar.rov;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class Help_3Fragment
  extends Fragment
{
  public Button StartButton;
  
  private View inflateAndSetupView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle, int paramInt)
  {
    paramLayoutInflater = paramLayoutInflater.inflate(paramInt, paramViewGroup, false);
    this.StartButton = ((Button)paramLayoutInflater.findViewById(2131361864));
    if (HelpActivity.needHelp) {
      this.StartButton.setText("返回");
    }
    this.StartButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (HelpActivity.needHelp) {
          Help_3Fragment.this.getActivity().finish();
        }
        for (;;)
        {
          HelpActivity.needHelp = false;
          return;
          paramAnonymousView = new Intent();
          paramAnonymousView.setClass(Help_3Fragment.this.getActivity(), M_ROV_Activity.class);
          Help_3Fragment.this.startActivity(paramAnonymousView);
          Help_3Fragment.this.getActivity().finish();
        }
      }
    });
    return paramLayoutInflater;
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return inflateAndSetupView(paramLayoutInflater, paramViewGroup, paramBundle, 2130903059);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/Help_3Fragment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */