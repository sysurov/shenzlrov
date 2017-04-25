package com.tools.FloatViewService;

import android.annotation.SuppressLint;
import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import com.deepinfar.rov.MainFragment;

public class FloatViewService
  extends Service
{
  private static final String TAG = "FloatViewService";
  private LinearLayout mFloatLayout;
  private ImageButton mFloatView;
  private WindowManager mWindowManager;
  private WindowManager.LayoutParams wmParams;
  
  @SuppressLint({"InflateParams"})
  private void createFloatView()
  {
    this.wmParams = new WindowManager.LayoutParams();
    Application localApplication = getApplication();
    getApplication();
    this.mWindowManager = ((WindowManager)localApplication.getSystemService("window"));
    this.wmParams.type = 2002;
    this.wmParams.format = 1;
    this.wmParams.flags = 8;
    this.wmParams.gravity = 51;
    this.wmParams.x = 0;
    this.wmParams.y = 152;
    this.wmParams.width = -2;
    this.wmParams.height = -2;
    this.mFloatLayout = ((LinearLayout)LayoutInflater.from(getApplication()).inflate(2130903045, null));
    this.mWindowManager.addView(this.mFloatLayout, this.wmParams);
    this.mFloatView = ((ImageButton)this.mFloatLayout.findViewById(2131361799));
    this.mFloatLayout.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
    this.mFloatView.setOnTouchListener(new View.OnTouchListener()
    {
      boolean isClick;
      
      @SuppressLint({"ClickableViewAccessibility"})
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        default: 
        case 0: 
        case 2: 
          do
          {
            return false;
            this.isClick = false;
            return false;
          } while ((!this.isClick) && (Math.abs(FloatViewService.this.wmParams.x - (int)paramAnonymousMotionEvent.getRawX()) <= FloatViewService.this.mFloatView.getWidth()) && (Math.abs(FloatViewService.this.wmParams.y - (int)paramAnonymousMotionEvent.getRawY()) <= FloatViewService.this.mFloatView.getHeight()));
          this.isClick = true;
          FloatViewService.this.wmParams.x = ((int)paramAnonymousMotionEvent.getRawX() - FloatViewService.this.mFloatView.getMeasuredWidth() / 2);
          FloatViewService.this.wmParams.y = ((int)paramAnonymousMotionEvent.getRawY() - FloatViewService.this.mFloatView.getMeasuredHeight() / 2);
          FloatViewService.this.mWindowManager.updateViewLayout(FloatViewService.this.mFloatLayout, FloatViewService.this.wmParams);
          return true;
        }
        int i = FloatViewService.this.mWindowManager.getDefaultDisplay().getWidth();
        if (FloatViewService.this.wmParams.x >= i / 2) {}
        for (FloatViewService.this.wmParams.x = i;; FloatViewService.this.wmParams.x = 0)
        {
          FloatViewService.this.mWindowManager.updateViewLayout(FloatViewService.this.mFloatLayout, FloatViewService.this.wmParams);
          return this.isClick;
        }
      }
    });
    this.mFloatView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        int i = 8;
        boolean bool = false;
        paramAnonymousView = MainFragment.getMainView().getDownMenu();
        if (paramAnonymousView.getVisibility() == 8) {
          i = 0;
        }
        paramAnonymousView.setVisibility(i);
        if (paramAnonymousView.getVisibility() == 0) {
          bool = true;
        }
        com.deepinfar.Dialog.LsattrDialog.bMenuBar = bool;
      }
    });
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }
  
  public void onCreate()
  {
    super.onCreate();
    createFloatView();
  }
  
  public void onDestroy()
  {
    super.onDestroy();
    if (this.mFloatLayout != null) {
      this.mWindowManager.removeView(this.mFloatLayout);
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/FloatViewService/FloatViewService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */