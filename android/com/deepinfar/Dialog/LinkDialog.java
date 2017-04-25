package com.deepinfar.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import com.tools.ip.IPTools;
import com.tools.ip.IPUtils;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LinkDialog
  extends RovDialog
{
  private static final String ROVMODE = "ROVMODE";
  private static final String VIDEOVIDEO = "VIDEOMODE";
  public IPUtils IPUtils = new IPTools();
  private EditText IpPort;
  private EditText IpSite;
  private TextView Link_Port;
  private TextView Link_Site;
  public TextView ROV_Ip_Init;
  private boolean ROV_Model = true;
  public TextView ROV_Port_Init;
  private Button Rov_Dialog_Btn;
  private CheckBox SaveFile;
  private Button Start_Link;
  private Button Video_Dialog_Btn;
  private boolean edit = false;
  public FileDataUtils fileDataUtils;
  private View.OnClickListener onclack;
  private LinearLayout portLinearLayout;
  private String sMode = "ROVMODE";
  public ToastClass toastClass;
  
  public static final RovDialog newInstance(Handler paramHandler, FragmentManager paramFragmentManager, String paramString)
  {
    LinkDialog localLinkDialog = new LinkDialog();
    RovDialog.newInstance(localLinkDialog, paramHandler, paramFragmentManager, paramString);
    return localLinkDialog;
  }
  
  public static final RovDialog newInstance(Handler paramHandler, FragmentManager paramFragmentManager, String paramString, boolean paramBoolean)
  {
    LinkDialog localLinkDialog = new LinkDialog();
    RovDialog.newInstance(localLinkDialog, paramHandler, paramFragmentManager, paramString);
    localLinkDialog.edit = paramBoolean;
    return localLinkDialog;
  }
  
  public View init(View paramView)
  {
    this.Rov_Dialog_Btn = ((Button)paramView.findViewById(2131361801));
    this.Video_Dialog_Btn = ((Button)paramView.findViewById(2131361802));
    this.Start_Link = ((Button)paramView.findViewById(2131361811));
    this.Link_Site = ((TextView)paramView.findViewById(2131361803));
    this.Link_Port = ((TextView)paramView.findViewById(2131361806));
    this.IpSite = ((EditText)paramView.findViewById(2131361804));
    this.IpPort = ((EditText)paramView.findViewById(2131361807));
    this.IpSite.setFocusable(this.edit);
    this.IpSite.setEnabled(this.edit);
    this.IpPort.setFocusable(this.edit);
    this.IpPort.setEnabled(this.edit);
    this.IpSite.setFocusableInTouchMode(this.edit);
    this.IpPort.setFocusableInTouchMode(this.edit);
    this.portLinearLayout = ((LinearLayout)paramView.findViewById(2131361805));
    this.SaveFile = ((CheckBox)paramView.findViewById(2131361808));
    this.onclack = new Onclack(null);
    this.Rov_Dialog_Btn.setOnClickListener(this.onclack);
    this.Video_Dialog_Btn.setOnClickListener(this.onclack);
    this.Start_Link.setOnClickListener(this.onclack);
    this.IpSite.setText(this.fileDataUtils.getTagData("RovIP地址"));
    this.IpPort.setText(this.fileDataUtils.getTagData("Rov端口"));
    this.ROV_Ip_Init = ((TextView)paramView.findViewById(2131361809));
    this.ROV_Port_Init = ((TextView)paramView.findViewById(2131361810));
    this.ROV_Ip_Init.setText(getActivity().getString(2131165212) + "    " + "192.168.99.10");
    this.ROV_Port_Init.setText(getActivity().getString(2131165213) + "    " + "5001");
    paramView.setFocusableInTouchMode(true);
    return super.init(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    getDialog().requestWindowFeature(1);
    try
    {
      this.toastClass = ToastClass.getToastClass();
    }
    catch (NullPointerException paramViewGroup)
    {
      try
      {
        for (;;)
        {
          this.fileDataUtils = FileDataUtils.getFileDataUtils();
          paramLayoutInflater = paramLayoutInflater.inflate(2130903046, null);
          init(paramLayoutInflater);
          return paramLayoutInflater;
          paramViewGroup = paramViewGroup;
          this.toastClass = ToastClass.getToastClass(getActivity());
        }
      }
      catch (FileNotFoundException paramViewGroup)
      {
        for (;;)
        {
          this.toastClass.ToastshowString("离线文件打开异常");
        }
      }
      catch (IOException paramViewGroup)
      {
        for (;;)
        {
          this.toastClass.ToastshowString("离线文件加载异常");
        }
      }
    }
  }
  
  public void onResume()
  {
    super.onResume();
    getDialog().getWindow().setLayout(1300, 800);
    if ((this.tag == null) || (this.tag == "null")) {
      dismiss();
    }
  }
  
  public void startRovLink()
  {
    String str1 = this.IpSite.getText().toString();
    String str2 = this.IpPort.getText().toString();
    String str3 = this.IPUtils.isIpLegal(str1);
    if (str3 != null)
    {
      this.toastClass.ToastshowString(str3);
      this.IpSite.setText("");
      return;
    }
    str3 = this.IPUtils.isPortLegal(str2);
    if (str3 != null)
    {
      this.toastClass.ToastshowString(str3);
      this.IpPort.setText("");
      return;
    }
    this.fileDataUtils.updata("RovIP地址", str1);
    this.fileDataUtils.updata("Rov端口", str2);
    if (this.SaveFile.isChecked()) {
      this.fileDataUtils.writerFile();
    }
    dismiss();
  }
  
  public void startVideoLink()
  {
    String str = this.IpSite.getText().toString();
    this.fileDataUtils.updata("视频地址", str);
    if (this.SaveFile.isChecked()) {
      this.fileDataUtils.writerFile();
    }
    dismiss();
    this.mainHandler.sendEmptyMessage(0);
  }
  
  private class Onclack
    implements View.OnClickListener
  {
    private Onclack() {}
    
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default: 
        return;
      case 2131361801: 
        LinkDialog.this.sMode = "ROVMODE";
        paramView.setBackgroundColor(65280);
        LinkDialog.this.Video_Dialog_Btn.setBackgroundColor(-1);
        LinkDialog.this.IpSite.setText(LinkDialog.this.fileDataUtils.getTagData("RovIP地址"));
        LinkDialog.this.IpPort.setText(LinkDialog.this.fileDataUtils.getTagData("Rov端口"));
        LinkDialog.this.Link_Site.setText(2131165203);
        LinkDialog.this.Link_Port.setText(2131165204);
        LinkDialog.this.portLinearLayout.setVisibility(0);
        LinkDialog.this.ROV_Ip_Init.setText(LinkDialog.this.getActivity().getString(2131165212) + "    " + "192.168.99.10");
        LinkDialog.this.ROV_Port_Init.setText(LinkDialog.this.getActivity().getString(2131165213) + "    " + "5001");
        LinkDialog.this.IpSite.setHint(LinkDialog.this.getActivity().getString(2131165212) + "    " + "192.168.99.10");
        LinkDialog.this.ROV_Model = true;
        return;
      case 2131361802: 
        LinkDialog.this.sMode = "VIDEOMODE";
        paramView.setBackgroundColor(65280);
        LinkDialog.this.Rov_Dialog_Btn.setBackgroundColor(-1);
        LinkDialog.this.Link_Site.setText(2131165205);
        LinkDialog.this.Link_Port.setText("");
        LinkDialog.this.IpSite.setText(LinkDialog.this.fileDataUtils.getTagData("视频地址"));
        LinkDialog.this.portLinearLayout.setVisibility(8);
        LinkDialog.this.ROV_Ip_Init.setText(LinkDialog.this.getActivity().getString(2131165214) + "    " + "rtsp://192.168.99.51/1");
        LinkDialog.this.ROV_Port_Init.setText("");
        LinkDialog.this.IpSite.setHint(LinkDialog.this.getActivity().getString(2131165214) + "    " + "rtsp://192.168.99.51/1");
        LinkDialog.this.ROV_Model = false;
        return;
      }
      if (LinkDialog.this.ROV_Model)
      {
        LinkDialog.this.startRovLink();
        return;
      }
      LinkDialog.this.startVideoLink();
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Dialog/LinkDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */