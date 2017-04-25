package com.tools.wifiTools;

import android.app.Activity;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import java.util.Timer;
import java.util.TimerTask;

public class WifiIntensity
{
  private Handler handler;
  private int level;
  private Timer timer;
  private WifiInfo wifiInfo = null;
  private WifiManager wifiManager = null;
  
  public WifiIntensity(Handler paramHandler, Activity paramActivity)
  {
    this.handler = paramHandler;
    this.wifiManager = ((WifiManager)paramActivity.getSystemService("wifi"));
    StartTimer();
  }
  
  private void StartTimer()
  {
    this.timer = new Timer();
    this.timer.schedule(new TimerTask()
    {
      public void run()
      {
        WifiIntensity.this.wifiInfo = WifiIntensity.this.wifiManager.getConnectionInfo();
        WifiIntensity.this.level = WifiIntensity.this.wifiInfo.getRssi();
        if (WifiIntensity.this.handler == null) {
          return;
        }
        Message localMessage = new Message();
        localMessage.what = 2;
        if ((WifiIntensity.this.level <= 0) && (WifiIntensity.this.level >= -35))
        {
          localMessage.arg1 = 1;
          WifiIntensity.this.handler.sendMessage(localMessage);
          return;
        }
        if ((WifiIntensity.this.level < -35) && (WifiIntensity.this.level >= -70))
        {
          localMessage.arg1 = 2;
          WifiIntensity.this.handler.sendMessage(localMessage);
          return;
        }
        if ((WifiIntensity.this.level < -70) && (WifiIntensity.this.level >= -99))
        {
          localMessage.arg1 = 3;
          WifiIntensity.this.handler.sendMessage(localMessage);
          return;
        }
        localMessage.arg1 = 4;
        WifiIntensity.this.handler.sendMessage(localMessage);
      }
    }, 500L, 1000L);
  }
  
  public int getLevel()
  {
    return this.level;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/wifiTools/WifiIntensity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */