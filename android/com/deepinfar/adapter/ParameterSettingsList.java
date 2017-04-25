package com.deepinfar.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.deepinfar.Dialog.settingDialog;
import com.deepinfar.rov.MainFragment;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParameterSettingsList
  extends BaseAdapter
{
  public static int MIN_SEEKBAR = 30;
  public float alpha;
  @SuppressLint({"HandlerLeak"})
  private Handler alphaHandler = new Handler()
  {
    @SuppressLint({"HandlerLeak"})
    public void handleMessage(Message paramAnonymousMessage)
    {
      Object localObject = (Map)paramAnonymousMessage.obj;
      float f = (float)(((Float)((Map)localObject).get("alpha")).floatValue() + ParameterSettingsList.MIN_SEEKBAR / 100.0D);
      switch (paramAnonymousMessage.what)
      {
      default: 
        return;
      case 1: 
        ((View)((Map)localObject).get("View")).setAlpha(f);
        return;
      }
      paramAnonymousMessage = (Dialog)((Map)localObject).get("Dialog");
      localObject = paramAnonymousMessage.getWindow().getAttributes();
      paramAnonymousMessage.getWindow().setFlags(4, 4);
      ((WindowManager.LayoutParams)localObject).alpha = f;
      paramAnonymousMessage.getWindow().setAttributes((WindowManager.LayoutParams)localObject);
      paramAnonymousMessage.show();
    }
  };
  public Context context;
  public ArrayList<Map<String, String>> data;
  public String[] dataMenu;
  public ToastClass toastClass;
  
  public ParameterSettingsList(Context paramContext, String[] paramArrayOfString, ArrayList<Map<String, String>> paramArrayList)
  {
    this.context = paramContext;
    this.toastClass = ToastClass.getToastClass(paramContext);
    this.data = paramArrayList;
    this.dataMenu = paramArrayOfString;
  }
  
  private void alpha(View paramView, float paramFloat)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("View", paramView);
    localHashMap.put("alpha", Float.valueOf(paramFloat));
    paramView = new Message();
    paramView.what = 1;
    paramView.obj = localHashMap;
    this.alphaHandler.sendMessage(paramView);
  }
  
  private void dialogAlpha(Dialog paramDialog, float paramFloat)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Dialog", paramDialog);
    localHashMap.put("alpha", Float.valueOf(paramFloat));
    paramDialog = new Message();
    paramDialog.what = 2;
    paramDialog.obj = localHashMap;
    this.alphaHandler.sendMessage(paramDialog);
  }
  
  public int getCount()
  {
    return this.dataMenu.length;
  }
  
  public Object getItem(int paramInt)
  {
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  @SuppressLint({"InflateParams"})
  public View getView(int paramInt, View paramView, final ViewGroup paramViewGroup)
  {
    Object localObject = (LayoutInflater)this.context.getSystemService("layout_inflater");
    if (paramView == null) {
      paramView = ((LayoutInflater)localObject).inflate(2130903061, paramViewGroup, false);
    }
    for (;;)
    {
      paramViewGroup = (TextView)paramView.findViewById(2131361867);
      LinearLayout localLinearLayout = (LinearLayout)paramView.findViewById(2131361868);
      localObject = (SeekBar)paramView.findViewById(2131361869);
      final Map localMap = (Map)this.data.get(0);
      try
      {
        i = Integer.parseInt((String)localMap.get(this.dataMenu[paramInt]));
        localLinearLayout.setVisibility(0);
        ((SeekBar)localObject).setProgress(i);
        paramViewGroup.setText(this.dataMenu[paramInt]);
        ((SeekBar)localObject).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
          public void onProgressChanged(SeekBar paramAnonymousSeekBar, int paramAnonymousInt, boolean paramAnonymousBoolean)
          {
            float f;
            if (paramViewGroup.getText().toString().equals("界面透明度"))
            {
              f = paramAnonymousSeekBar.getProgress() / 100.0F;
              if (f != 0.0F) {
                ParameterSettingsList.this.alpha(MainFragment.getMainFragmentView(), f);
              }
            }
            do
            {
              do
              {
                return;
              } while (!paramViewGroup.getText().toString().equals("窗口透明度"));
              f = paramAnonymousSeekBar.getProgress() / 100.0F;
            } while (f == 0.0F);
            ParameterSettingsList.this.dialogAlpha(settingDialog.getVDialog(), f);
          }
          
          public void onStartTrackingTouch(SeekBar paramAnonymousSeekBar) {}
          
          public void onStopTrackingTouch(SeekBar paramAnonymousSeekBar)
          {
            Object localObject = null;
            try
            {
              FileDataUtils localFileDataUtils = FileDataUtils.getFileDataUtils();
              localObject = localFileDataUtils;
            }
            catch (FileNotFoundException localFileNotFoundException)
            {
              for (;;)
              {
                localFileNotFoundException.printStackTrace();
              }
            }
            catch (IOException localIOException)
            {
              for (;;)
              {
                localIOException.printStackTrace();
              }
            }
            if (localObject != null)
            {
              localMap.put(paramViewGroup.getText().toString(), String.valueOf(paramAnonymousSeekBar.getProgress()));
              ((FileDataUtils)localObject).updata(paramViewGroup.getText().toString(), String.valueOf(paramAnonymousSeekBar.getProgress()));
              ((FileDataUtils)localObject).writerFile();
            }
          }
        });
        return paramView;
      }
      catch (Exception localException)
      {
        for (;;)
        {
          int i = 0;
        }
      }
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/adapter/ParameterSettingsList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */