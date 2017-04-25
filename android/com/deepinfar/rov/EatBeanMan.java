package com.deepinfar.rov;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.view.View;
import android.view.View.MeasureSpec;

public class EatBeanMan
  extends View
{
  int cAngle = 20;
  int csAngle = -10;
  boolean isDrawing = false;
  Handler mHandler = new Handler(new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default: 
        return false;
      }
      EatBeanMan.this.invalidate();
      if (EatBeanMan.this.sweepAngle >= 360)
      {
        EatBeanMan.this.csAngle = 10;
        EatBeanMan.this.cAngle = -20;
      }
      for (;;)
      {
        paramAnonymousMessage = EatBeanMan.this;
        paramAnonymousMessage.startAngle += EatBeanMan.this.csAngle;
        paramAnonymousMessage = EatBeanMan.this;
        paramAnonymousMessage.sweepAngle += EatBeanMan.this.cAngle;
        EatBeanMan.this.mHandler.sendEmptyMessageDelayed(101, 40L);
        break;
        if (EatBeanMan.this.sweepAngle <= 270)
        {
          EatBeanMan.this.csAngle = -10;
          EatBeanMan.this.cAngle = 20;
        }
      }
    }
  });
  Paint paintC = new Paint();
  Paint paintEye;
  RectF rectF;
  int startAngle = 45;
  int sweepAngle = 270;
  
  public EatBeanMan(Context paramContext)
  {
    super(paramContext);
    this.paintC.setColor(65280);
    this.paintC.setAntiAlias(true);
    this.paintEye = new Paint();
    this.paintEye.setColor(-16777216);
    this.paintEye.setAntiAlias(true);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.drawArc(this.rectF, this.startAngle, this.sweepAngle, true, this.paintC);
    paramCanvas.drawCircle(this.rectF.right / 5.0F * 3.0F, this.rectF.bottom / 4.0F, 10.0F, this.paintEye);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.makeMeasureSpec(300, 1073741824);
    if (!this.isDrawing)
    {
      this.mHandler.sendEmptyMessage(101);
      this.isDrawing = true;
      this.rectF = new RectF(0.0F, 0.0F, 300.0F, 300.0F);
    }
    super.onMeasure(paramInt1, paramInt1);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/EatBeanMan.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */