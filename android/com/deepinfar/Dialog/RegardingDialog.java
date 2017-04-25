package com.deepinfar.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import com.deepinfar.ProtocolData.RovInitData;
import com.deepinfar.rov.HelpActivity;
import com.tools.Toast.ToastClass;

public class RegardingDialog
  extends RovDialog
{
  public Button HelpButton;
  public Button OfficialWebsite;
  public View RegardingView;
  public Button SoftwareUpdata;
  public Button SoftwareVersion;
  public RegaDingOncack regaDingOncack;
  public ToastClass toastClass;
  
  public static final RovDialog newInstance(Handler paramHandler, FragmentManager paramFragmentManager, String paramString)
  {
    RegardingDialog localRegardingDialog = new RegardingDialog();
    RovDialog.newInstance(localRegardingDialog, paramHandler, paramFragmentManager, paramString);
    return localRegardingDialog;
  }
  
  public View init(View paramView)
  {
    this.SoftwareVersion = ((Button)paramView.findViewById(2131361821));
    this.SoftwareUpdata = ((Button)paramView.findViewById(2131361822));
    this.OfficialWebsite = ((Button)paramView.findViewById(2131361824));
    this.HelpButton = ((Button)paramView.findViewById(2131361823));
    this.regaDingOncack = new RegaDingOncack();
    this.SoftwareVersion.setOnClickListener(this.regaDingOncack);
    this.SoftwareUpdata.setOnClickListener(this.regaDingOncack);
    this.OfficialWebsite.setOnClickListener(this.regaDingOncack);
    this.HelpButton.setOnClickListener(this.regaDingOncack);
    return super.init(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    getDialog().requestWindowFeature(1);
    paramLayoutInflater = paramLayoutInflater.inflate(2130903049, null);
    this.toastClass = ToastClass.getToastClass();
    init(paramLayoutInflater);
    this.RegardingView = paramLayoutInflater;
    return paramLayoutInflater;
  }
  
  public void onResume()
  {
    super.onResume();
    getDialog().getWindow().setLayout(1300, 800);
    if ((this.tag == null) || (this.tag == "null")) {
      dismiss();
    }
  }
  
  class RegaDingOncack
    implements View.OnClickListener
  {
    RegaDingOncack() {}
    
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default: 
        return;
      case 2131361821: 
        RegardingDialog.this.toastClass.ToastshowString("当前版本  ： " + RovInitData.ROV_VERSION);
        return;
      case 2131361822: 
        RegardingDialog.this.toastClass.ToastshowString("当前为最高版本");
        return;
      case 2131361823: 
        paramView = new Intent();
        paramView.setClass(RegardingDialog.this.getActivity(), HelpActivity.class);
        RegardingDialog.this.getActivity().startActivity(paramView);
        HelpActivity.needHelp = true;
        return;
      }
      RegardingDialog.this.toastClass.ToastshowString("正在建设中");
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Dialog/RegardingDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */