package com.deepinfar.adapter;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class BluetoothSettingList
  extends BaseAdapter
{
  public Context context;
  public ArrayList<Map<String, String>> data;
  public String[] dataMenu;
  private boolean edit = false;
  public ToastClass toastClass;
  
  public BluetoothSettingList(Context paramContext, String[] paramArrayOfString, ArrayList<Map<String, String>> paramArrayList, boolean paramBoolean)
  {
    this.context = paramContext;
    this.toastClass = ToastClass.getToastClass(paramContext);
    this.data = paramArrayList;
    this.dataMenu = paramArrayOfString;
    this.edit = paramBoolean;
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
  
  public View getView(int paramInt, View paramView, final ViewGroup paramViewGroup)
  {
    final Object localObject = (LayoutInflater)this.context.getSystemService("layout_inflater");
    if (paramView == null) {
      paramView = ((LayoutInflater)localObject).inflate(2130903060, paramViewGroup, false);
    }
    for (;;)
    {
      paramViewGroup = (TextView)paramView.findViewById(2131361865);
      localObject = (EditText)paramView.findViewById(2131361866);
      ((EditText)localObject).setFocusable(this.edit);
      ((EditText)localObject).setEnabled(this.edit);
      ((EditText)localObject).setFocusableInTouchMode(this.edit);
      Map localMap = (Map)this.data.get(0);
      paramViewGroup.setText(this.dataMenu[paramInt]);
      ((EditText)localObject).setText((CharSequence)localMap.get(this.dataMenu[paramInt]));
      ((EditText)localObject).setOnEditorActionListener(new TextView.OnEditorActionListener()
      {
        public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          paramAnonymousKeyEvent = null;
          try
          {
            FileDataUtils localFileDataUtils = FileDataUtils.getFileDataUtils();
            paramAnonymousKeyEvent = localFileDataUtils;
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
          paramAnonymousKeyEvent.updata(paramViewGroup.getText().toString(), paramAnonymousTextView.getText().toString());
          paramAnonymousKeyEvent.writerFile();
          return false;
        }
      });
      ((EditText)localObject).setOnKeyListener(new View.OnKeyListener()
      {
        public boolean onKey(View paramAnonymousView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
        {
          String str1 = String.valueOf(paramAnonymousInt);
          localObject.setText(String.valueOf(paramAnonymousInt));
          paramAnonymousView = null;
          try
          {
            paramAnonymousKeyEvent = FileDataUtils.getFileDataUtils();
            paramAnonymousView = paramAnonymousKeyEvent;
          }
          catch (FileNotFoundException paramAnonymousKeyEvent)
          {
            for (;;)
            {
              String str2;
              paramAnonymousKeyEvent.printStackTrace();
            }
          }
          catch (IOException paramAnonymousKeyEvent)
          {
            for (;;)
            {
              paramAnonymousKeyEvent.printStackTrace();
            }
          }
          paramAnonymousKeyEvent = paramViewGroup.getText().toString();
          str2 = paramAnonymousView.getTagData(paramAnonymousKeyEvent);
          if ((str2 == null) || (str2.equals("")) || (!str1.equals(str2)))
          {
            paramAnonymousView.updata(paramAnonymousKeyEvent, str1);
            paramAnonymousView.writerFile();
          }
          return true;
        }
      });
      return paramView;
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/adapter/BluetoothSettingList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */