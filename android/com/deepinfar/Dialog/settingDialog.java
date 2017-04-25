package com.deepinfar.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.deepinfar.Task.FunctionAdapter;
import com.deepinfar.Task.FunctionAdapter.OnDeployTask;
import com.deepinfar.adapter.BluetoothSettingList;
import com.deepinfar.adapter.ParameterSettingsList;
import com.tools.file.FileDataUtils;
import com.tools.network.UDPSend;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class settingDialog
  extends RovDialog
{
  public String[] BluetoothMenu;
  public Button bluetoothButton;
  public BaseAdapter bluetoothSettingList;
  public ArrayList<Map<String, String>> bluetoothlistArrays = new ArrayList();
  private boolean edit = false;
  public FileDataUtils fileDataUtils;
  public ListView listView;
  private Onclack onclack;
  public Button parameterButton;
  public String[] parameterMenu;
  public BaseAdapter parameterSettingsList;
  public ArrayList<Map<String, String>> parameterlistArrays = new ArrayList();
  public Resources res;
  public Button updata_time;
  
  public static final RovDialog newInstance(Handler paramHandler, FragmentManager paramFragmentManager, String paramString)
  {
    settingDialog localsettingDialog = new settingDialog();
    RovDialog.newInstance(localsettingDialog, paramHandler, paramFragmentManager, paramString);
    return localsettingDialog;
  }
  
  public static final RovDialog newInstance(Handler paramHandler, FragmentManager paramFragmentManager, String paramString, boolean paramBoolean)
  {
    settingDialog localsettingDialog = new settingDialog();
    RovDialog.newInstance(localsettingDialog, paramHandler, paramFragmentManager, paramString);
    localsettingDialog.edit = paramBoolean;
    return localsettingDialog;
  }
  
  public View init(View paramView)
  {
    this.listView = ((ListView)paramView.findViewById(2131361836));
    this.parameterButton = ((Button)paramView.findViewById(2131361833));
    this.bluetoothButton = ((Button)paramView.findViewById(2131361834));
    this.updata_time = ((Button)paramView.findViewById(2131361835));
    this.parameterButton.setBackgroundColor(65280);
    this.bluetoothButton.setBackgroundColor(-1);
    this.parameterMenu = this.res.getStringArray(2130968580);
    HashMap localHashMap = new HashMap();
    String[] arrayOfString = this.parameterMenu;
    int j = arrayOfString.length;
    int i = 0;
    if (i >= j)
    {
      this.parameterlistArrays.add(localHashMap);
      this.parameterSettingsList = new ParameterSettingsList(getActivity(), this.parameterMenu, this.parameterlistArrays);
      this.BluetoothMenu = this.res.getStringArray(2130968581);
      localHashMap = new HashMap();
      arrayOfString = this.BluetoothMenu;
      j = arrayOfString.length;
      i = 0;
    }
    for (;;)
    {
      if (i >= j)
      {
        this.bluetoothlistArrays.add(localHashMap);
        this.bluetoothSettingList = new BluetoothSettingList(getActivity(), this.BluetoothMenu, this.bluetoothlistArrays, this.edit);
        this.listView.setAdapter(this.parameterSettingsList);
        this.listView.setOverScrollMode(2);
        this.onclack = new Onclack();
        this.parameterButton.setOnClickListener(this.onclack);
        this.bluetoothButton.setOnClickListener(this.onclack);
        this.updata_time.setOnClickListener(this.onclack);
        return super.init(paramView);
        str = arrayOfString[i];
        localHashMap.put(str, this.fileDataUtils.getTagData(str));
        i += 1;
        break;
      }
      String str = arrayOfString[i];
      localHashMap.put(str, this.fileDataUtils.getTagData(str));
      i += 1;
    }
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    getDialog().requestWindowFeature(1);
    paramLayoutInflater = paramLayoutInflater.inflate(2130903051, null);
    this.res = getActivity().getResources();
    try
    {
      this.fileDataUtils = FileDataUtils.getFileDataUtils();
      paramLayoutInflater = init(paramLayoutInflater);
      RovDialog.vDialog = getDialog();
      return paramLayoutInflater;
    }
    catch (FileNotFoundException paramViewGroup)
    {
      for (;;)
      {
        paramViewGroup.printStackTrace();
      }
    }
    catch (IOException paramViewGroup)
    {
      for (;;)
      {
        paramViewGroup.printStackTrace();
      }
    }
  }
  
  public void onDestroy()
  {
    FunctionAdapter.getFunctionAdapter().getOnDeployTask().FunctionDeploy();
    super.onDestroy();
  }
  
  public void onResume()
  {
    super.onResume();
    getDialog().getWindow().setLayout(1500, 850);
    if ((this.tag == null) || (this.tag == "null")) {
      dismiss();
    }
  }
  
  class Onclack
    implements View.OnClickListener
  {
    Onclack() {}
    
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default: 
        return;
      case 2131361833: 
        settingDialog.this.parameterButton.setBackgroundColor(65280);
        settingDialog.this.listView.setAdapter(settingDialog.this.parameterSettingsList);
        settingDialog.this.bluetoothButton.setBackgroundColor(-1);
        return;
      case 2131361834: 
        settingDialog.this.parameterButton.setBackgroundColor(-1);
        settingDialog.this.listView.setAdapter(settingDialog.this.bluetoothSettingList);
        settingDialog.this.bluetoothButton.setBackgroundColor(65280);
        return;
      }
      UDPSend.updataTime(false);
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Dialog/settingDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */