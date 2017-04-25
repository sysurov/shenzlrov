package com.deepinfar.rov;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.deepinfar.NetWork.SendRovData.VideoData;
import com.deepinfar.Task.CameraOnDeploy.ModelOnDeployTask_1;
import com.deepinfar.Task.FunctionAdapter;
import com.deepinfar.Task.InitOnDeployTask;
import com.tools.FloatViewService.FloatViewService;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Map;

@SuppressLint({"WrongCall"})
public class StartActivity
  extends Activity
{
  private static StartActivity startAvtivity;
  public Handler ToastHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      if (paramAnonymousMessage.what == 1)
      {
        paramAnonymousMessage = (String)paramAnonymousMessage.obj;
        StartActivity.this.toastClass.ToastshowString(paramAnonymousMessage);
      }
    }
  };
  private FileDataUtils fileDataUtils;
  private FunctionAdapter functionAdapter;
  private View main;
  public View.OnTouchListener onTouchListener = new View.OnTouchListener()
  {
    private int PointerCount;
    private float afterLenght;
    private float afterX_0;
    private float afterX_1;
    private float afterY_0;
    private float afterY_1;
    private float beforeLenght;
    private float beforeX_0;
    private float beforeX_1;
    private float beforeY_0;
    private float beforeY_1;
    
    public void moveWithFinger(MotionEvent paramAnonymousMotionEvent)
    {
      if (this.PointerCount != 0) {
        return;
      }
      switch (paramAnonymousMotionEvent.getAction())
      {
      case 1: 
      default: 
        return;
      case 0: 
        this.beforeX_0 = paramAnonymousMotionEvent.getX();
        this.beforeY_0 = paramAnonymousMotionEvent.getY();
        return;
      }
      this.afterX_0 = paramAnonymousMotionEvent.getX();
      this.afterY_0 = paramAnonymousMotionEvent.getY();
      paramAnonymousMotionEvent = NetWork.getNetwork().getSendRovData().getVideoData("MAIN");
      if (this.afterX_0 > this.beforeX_0 + 50.0F)
      {
        updataSend(paramAnonymousMotionEvent.getDatas(), "Brightness_Left", true, ModelOnDeployTask_1.Brightness_MIN, ModelOnDeployTask_1.Brightness_MAX, 1);
        updataSend(paramAnonymousMotionEvent.getDatas(), "Brightness_Right", true, ModelOnDeployTask_1.Brightness_MIN, ModelOnDeployTask_1.Brightness_MAX, 1);
      }
      for (;;)
      {
        this.beforeX_0 = this.afterX_0;
        this.beforeY_0 = this.afterY_0;
        return;
        if (this.afterX_0 < this.beforeX_0 - 50.0F)
        {
          updataSend(paramAnonymousMotionEvent.getDatas(), "Brightness_Left", false, ModelOnDeployTask_1.Brightness_MIN, ModelOnDeployTask_1.Brightness_MAX, 1);
          updataSend(paramAnonymousMotionEvent.getDatas(), "Brightness_Right", false, ModelOnDeployTask_1.Brightness_MIN, ModelOnDeployTask_1.Brightness_MAX, 1);
        }
        else if (this.afterY_0 < this.beforeY_0 - 50.0F)
        {
          updataSend(paramAnonymousMotionEvent.getDatas(), "Vertical_Turn", true, ModelOnDeployTask_1.Vertical_Turn_MIN, ModelOnDeployTask_1.Vertical_Turn_MAX, 1);
        }
        else
        {
          if (this.afterY_0 <= this.beforeY_0 + 50.0F) {
            break;
          }
          updataSend(paramAnonymousMotionEvent.getDatas(), "Vertical_Turn", false, ModelOnDeployTask_1.Vertical_Turn_MIN, ModelOnDeployTask_1.Vertical_Turn_MAX, 1);
        }
      }
    }
    
    @SuppressLint({"ClickableViewAccessibility"})
    public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
    {
      if (paramAnonymousMotionEvent.getPointerCount() == 1) {
        moveWithFinger(paramAnonymousMotionEvent);
      }
      if (paramAnonymousMotionEvent.getAction() == 1) {
        this.PointerCount = (paramAnonymousMotionEvent.getPointerCount() - 1);
      }
      return true;
    }
    
    public void updataSend(Map<String, Integer> paramAnonymousMap, String paramAnonymousString, boolean paramAnonymousBoolean, int paramAnonymousInt1, int paramAnonymousInt2)
    {
      for (;;)
      {
        int j;
        try
        {
          j = (paramAnonymousInt2 - paramAnonymousInt1) / 50;
          i = j;
          if (j <= 0) {
            i = 1;
          }
          j = ((Integer)paramAnonymousMap.get(paramAnonymousString)).intValue();
          if (paramAnonymousBoolean)
          {
            j += i;
            break label84;
            paramAnonymousMap.put(paramAnonymousString, Integer.valueOf(paramAnonymousInt2));
          }
          else
          {
            j -= i;
          }
        }
        finally {}
        label84:
        int i = j;
        if (j >= paramAnonymousInt2) {
          i = paramAnonymousInt2;
        }
        paramAnonymousInt2 = i;
        if (i <= paramAnonymousInt1) {
          paramAnonymousInt2 = paramAnonymousInt1;
        }
      }
    }
    
    public void updataSend(Map<String, Integer> paramAnonymousMap, String paramAnonymousString, boolean paramAnonymousBoolean, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
    {
      int i = paramAnonymousInt3;
      if (paramAnonymousInt3 <= 0) {
        i = 1;
      }
      for (;;)
      {
        try
        {
          paramAnonymousInt3 = ((Integer)paramAnonymousMap.get(paramAnonymousString)).intValue();
          if (paramAnonymousBoolean)
          {
            i = paramAnonymousInt3 + i;
            break label74;
            paramAnonymousMap.put(paramAnonymousString, Integer.valueOf(paramAnonymousInt2));
          }
          else
          {
            i = paramAnonymousInt3 - i;
          }
        }
        finally {}
        label74:
        paramAnonymousInt3 = i;
        if (i >= paramAnonymousInt2) {
          paramAnonymousInt3 = paramAnonymousInt2;
        }
        paramAnonymousInt2 = paramAnonymousInt3;
        if (paramAnonymousInt3 <= paramAnonymousInt1) {
          paramAnonymousInt2 = paramAnonymousInt1;
        }
      }
    }
  };
  public ToastClass toastClass;
  
  public static Bitmap convertViewToBitmap(View paramView)
  {
    paramView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    paramView.layout(0, 0, paramView.getMeasuredWidth(), paramView.getMeasuredHeight());
    paramView.buildDrawingCache();
    paramView = paramView.getDrawingCache();
    if (paramView != null)
    {
      System.out.println("这不是nullde1");
      Log.d("nullde1", "nullde1");
      return paramView;
    }
    System.out.println("这nullnulllnulnlul");
    return paramView;
  }
  
  public static Bitmap getLoacalBitmap(String paramString)
  {
    try
    {
      paramString = BitmapFactory.decodeStream(new FileInputStream(paramString));
      return paramString;
    }
    catch (FileNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  public static StartActivity getStartAvtivity()
  {
    return startAvtivity;
  }
  
  public static void savePic(Bitmap paramBitmap, String paramString)
  {
    try
    {
      paramString = new FileOutputStream(paramString);
      if (paramString != null) {}
      return;
    }
    catch (FileNotFoundException paramBitmap)
    {
      try
      {
        paramBitmap.compress(Bitmap.CompressFormat.PNG, 90, paramString);
        paramString.flush();
        paramString.close();
        return;
      }
      catch (IOException paramBitmap)
      {
        for (;;) {}
      }
      catch (FileNotFoundException paramBitmap)
      {
        for (;;) {}
      }
      paramBitmap = paramBitmap;
      paramBitmap.printStackTrace();
      return;
    }
    catch (IOException paramBitmap)
    {
      paramBitmap.printStackTrace();
      return;
    }
  }
  
  public static void setStartAvtivity(StartActivity paramStartActivity)
  {
    startAvtivity = paramStartActivity;
  }
  
  public Bitmap getFrameAsBitmap(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    localBitmap.copyPixelsFromBuffer(paramByteBuffer);
    return localBitmap;
  }
  
  public void onBackPressed()
  {
    new AlertDialog.Builder(this).setTitle("确认退出吗？").setIcon(17301659).setPositiveButton("确定", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        StartActivity.this.finish();
        System.exit(0);
      }
    }).setNegativeButton("返回", new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {}
    }).show();
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getLayoutInflater();
    this.main = LayoutInflater.from(this).inflate(2130903043, null);
    this.main.setSystemUiVisibility(2);
    setKeepScreenOn(this, true);
    try
    {
      this.toastClass = ToastClass.getToastClass();
      this.functionAdapter = FunctionAdapter.getFunctionAdapter();
    }
    catch (NullPointerException paramBundle)
    {
      try
      {
        this.fileDataUtils = FileDataUtils.getFileDataUtils();
        this.functionAdapter.setOnDeployTask(new InitOnDeployTask(getApplicationContext(), ReceiveFrafment.getReceiveFrafment().getMainHandler()));
        this.main.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            int i = StartActivity.this.main.getSystemUiVisibility();
            if (i == 2) {
              StartActivity.this.main.setSystemUiVisibility(0);
            }
            do
            {
              return;
              if (i == 0)
              {
                StartActivity.this.main.setSystemUiVisibility(2);
                return;
              }
            } while (i != 1);
            StartActivity.this.main.setSystemUiVisibility(2);
          }
        });
        setContentView(this.main);
        startAvtivity = this;
        this.main.setOnTouchListener(this.onTouchListener);
        return;
        paramBundle = paramBundle;
        this.toastClass = ToastClass.getToastClass(getApplicationContext(), this.ToastHandler);
      }
      catch (FileNotFoundException paramBundle)
      {
        for (;;)
        {
          this.toastClass.ToastshowString("重要文件加载出错，请重启或联系客服。");
        }
      }
      catch (IOException paramBundle)
      {
        for (;;)
        {
          this.toastClass.ToastshowString("重要文件加载出错，请重启或联系客服。");
        }
      }
    }
  }
  
  public void onDestroy()
  {
    super.onDestroy();
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = false;
    if (paramKeyEvent.getAction() == 0)
    {
      paramKeyEvent.startTracking();
      bool = this.functionAdapter.FunctionStart(String.valueOf(paramInt), paramInt, paramKeyEvent.getRepeatCount());
    }
    if (bool) {
      return true;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  @SuppressLint({"WrongCall"})
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      onBackPressed();
      return super.onKeyUp(paramInt, paramKeyEvent);
    }
    if (this.functionAdapter.FunctionStop(String.valueOf(paramInt))) {
      return true;
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }
  
  protected void onStart()
  {
    startService(new Intent(this, FloatViewService.class));
    super.onStart();
  }
  
  protected void onStop()
  {
    stopService(new Intent(this, FloatViewService.class));
    super.onStop();
  }
  
  public void setKeepScreenOn(Activity paramActivity, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramActivity.getWindow().addFlags(128);
      return;
    }
    paramActivity.getWindow().clearFlags(128);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/StartActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */