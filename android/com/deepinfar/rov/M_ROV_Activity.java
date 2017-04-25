package com.deepinfar.rov;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.tools.Permissions.ActivityRoot;
import com.tools.Permissions.Jurisdictions;
import com.tools.Permissions.PermissionsApply;
import com.tools.file.FileDataUtils;

public class M_ROV_Activity
  extends ActivityRoot
{
  protected static final String TAG = "MainActivity/Vlc";
  public static boolean VideoShow = true;
  private FileDataUtils fileDataUtils;
  public ImageView loading;
  View main;
  
  private void showMessageOKCancel(Activity paramActivity, String paramString, DialogInterface.OnClickListener paramOnClickListener1, DialogInterface.OnClickListener paramOnClickListener2)
  {
    new AlertDialog.Builder(paramActivity).setMessage(paramString).setPositiveButton("我再想想", paramOnClickListener2).setNegativeButton("我去设置", paramOnClickListener1).create().show();
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getLayoutInflater();
    this.main = LayoutInflater.from(this).inflate(2130903041, null);
    this.main.setSystemUiVisibility(2);
    this.main.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        int i = M_ROV_Activity.this.main.getSystemUiVisibility();
        if (i == 2) {
          M_ROV_Activity.this.main.setSystemUiVisibility(0);
        }
        do
        {
          return;
          if (i == 0)
          {
            M_ROV_Activity.this.main.setSystemUiVisibility(2);
            return;
          }
        } while (i != 1);
        M_ROV_Activity.this.main.setSystemUiVisibility(2);
      }
    });
    setContentView(this.main);
    paramBundle = getSharedPreferences("SHARE_APP_TAG", 0);
    if (Boolean.valueOf(paramBundle.getBoolean("FIRST", true)).booleanValue())
    {
      paramBundle.edit().putBoolean("FIRST", false).commit();
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            Thread.sleep(1000L);
            Intent localIntent = new Intent();
            localIntent.setClass(M_ROV_Activity.this, HelpActivity.class);
            M_ROV_Activity.this.startActivity(localIntent);
            M_ROV_Activity.this.finish();
            return;
          }
          catch (InterruptedException localInterruptedException)
          {
            for (;;)
            {
              localInterruptedException.printStackTrace();
            }
          }
        }
      }).start();
      return;
    }
    new PermissionsApply()
    {
      public void FailureExecute()
      {
        M_ROV_Activity.this.showMessageOKCancel(M_ROV_Activity.this, "程序崩溃了，因为它没有得到需要的权限，请在设置页面更改权限属性。", new DialogInterface.OnClickListener()new DialogInterface.OnClickListener
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            paramAnonymous2DialogInterface = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            paramAnonymous2DialogInterface.setData(Uri.parse("package:" + M_ROV_Activity.this.getPackageName()));
            M_ROV_Activity.this.startActivity(paramAnonymous2DialogInterface);
            M_ROV_Activity.this.finish();
          }
        }, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            M_ROV_Activity.this.finish();
          }
        });
      }
      
      public void succeedExecute()
      {
        M_ROV_Activity.this.startControl();
      }
    }.insertDummyContactWrapper(this, new Jurisdictions[] { new Jurisdictions("android.permission.WRITE_EXTERNAL_STORAGE", "读取您的SD卡中的内容"), new Jurisdictions("android.permission.INTERNET", "访问网络") });
  }
  
  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    getMenuInflater().inflate(2131296256, paramMenu);
    return true;
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  @SuppressLint({"NewApi"})
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
  }
  
  public void startControl()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(1000L);
          Intent localIntent = new Intent();
          localIntent.setClass(M_ROV_Activity.this, StartActivity.class);
          M_ROV_Activity.this.startActivity(localIntent);
          M_ROV_Activity.this.finish();
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;)
          {
            localInterruptedException.printStackTrace();
          }
        }
      }
    }).start();
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/M_ROV_Activity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */