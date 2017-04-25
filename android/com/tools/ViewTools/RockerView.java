package com.tools.ViewTools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.tools.Image.BitmapUtils;

public class RockerView
  extends View
{
  private Bitmap mBmpRockerBg;
  private Bitmap mBmpRockerBtn;
  public PointF mCenterPoint;
  private float mRockerBg_R;
  private float mRockerBg_X;
  private float mRockerBg_Y;
  private float mRockerBtn_R;
  private float mRockerBtn_X;
  private float mRockerBtn_Y;
  RockerChangeListener mRockerChangeListener = null;
  private OnUpdataRocketView onUpdataRocketView;
  
  public RockerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mBmpRockerBg = BitmapFactory.decodeResource(paramContext.getResources(), 2130837538);
    this.mBmpRockerBtn = BitmapFactory.decodeResource(paramContext.getResources(), 2130837539);
    this.mBmpRockerBg = BitmapUtils.zoomImage(this.mBmpRockerBg, 160.0D, 160.0D);
    this.mBmpRockerBtn = BitmapUtils.zoomImage(this.mBmpRockerBtn, 40.0D, 40.0D);
    getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
    {
      public boolean onPreDraw()
      {
        RockerView.this.getViewTreeObserver().removeOnPreDrawListener(this);
        RockerView.this.mCenterPoint = new PointF(RockerView.this.getWidth() / 2, RockerView.this.getHeight() / 2);
        RockerView.this.mRockerBg_X = RockerView.this.mCenterPoint.x;
        RockerView.this.mRockerBg_Y = RockerView.this.mCenterPoint.y;
        RockerView.this.mRockerBtn_X = RockerView.this.mCenterPoint.x;
        RockerView.this.mRockerBtn_Y = RockerView.this.mCenterPoint.y;
        float f = RockerView.this.mBmpRockerBg.getWidth() / (RockerView.this.mBmpRockerBg.getWidth() + RockerView.this.mBmpRockerBtn.getWidth());
        RockerView.this.mRockerBg_R = (RockerView.this.getWidth() * f / 2.0F);
        RockerView.this.mRockerBtn_R = ((1.0F - f) * RockerView.this.getWidth() / 2.0F);
        return true;
      }
    });
    new Thread(new Runnable()
    {
      public void run()
      {
        for (;;)
        {
          RockerView.this.postInvalidate();
          try
          {
            Thread.sleep(100L);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
      }
    }).start();
  }
  
  public void UpdataRocketView(float paramFloat1, float paramFloat2)
  {
    if (this.mCenterPoint == null) {
      return;
    }
    paramFloat1 = paramFloat1 / 100.0F * this.mCenterPoint.x;
    paramFloat2 = paramFloat2 / 100.0F * this.mCenterPoint.y;
    int i = (int)(this.mCenterPoint.x + paramFloat1);
    int j = (int)(this.mCenterPoint.y + paramFloat2);
    if ((paramFloat1 != 0.0F) || (paramFloat2 != 0.0F))
    {
      if (Math.sqrt(Math.pow(this.mRockerBg_X - i, 2.0D) + Math.pow(this.mRockerBg_Y - j, 2.0D)) >= this.mRockerBg_R)
      {
        double d = getRad(this.mRockerBg_X, this.mRockerBg_Y, i, j);
        getXY(this.mRockerBg_X, this.mRockerBg_Y, this.mRockerBg_R, d);
        return;
      }
      this.mRockerBtn_X = i;
      this.mRockerBtn_Y = j;
      return;
    }
    this.mRockerBtn_X = this.mCenterPoint.x;
    this.mRockerBtn_Y = this.mCenterPoint.y;
  }
  
  public double getRad(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramFloat1 = paramFloat3 - paramFloat1;
    paramFloat3 = (float)Math.acos(paramFloat1 / (float)Math.sqrt(Math.pow(paramFloat1, 2.0D) + Math.pow(paramFloat2 - paramFloat4, 2.0D)));
    paramFloat1 = paramFloat3;
    if (paramFloat4 < paramFloat2) {
      paramFloat1 = -paramFloat3;
    }
    return paramFloat1;
  }
  
  public void getXY(float paramFloat1, float paramFloat2, float paramFloat3, double paramDouble)
  {
    this.mRockerBtn_X = ((float)(paramFloat3 * Math.cos(paramDouble)) + paramFloat1);
    this.mRockerBtn_Y = ((float)(paramFloat3 * Math.sin(paramDouble)) + paramFloat2);
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.drawBitmap(this.mBmpRockerBg, null, new Rect((int)(this.mRockerBg_X - this.mRockerBg_R), (int)(this.mRockerBg_Y - this.mRockerBg_R), (int)(this.mRockerBg_X + this.mRockerBg_R), (int)(this.mRockerBg_Y + this.mRockerBg_R)), null);
    paramCanvas.drawBitmap(this.mBmpRockerBtn, null, new Rect((int)(this.mRockerBtn_X - this.mRockerBtn_R), (int)(this.mRockerBtn_Y - this.mRockerBtn_R), (int)(this.mRockerBtn_X + this.mRockerBtn_R), (int)(this.mRockerBtn_Y + this.mRockerBtn_R)), null);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) || (paramMotionEvent.getAction() == 2)) {
      if (Math.sqrt(Math.pow(this.mRockerBg_X - (int)paramMotionEvent.getX(), 2.0D) + Math.pow(this.mRockerBg_Y - (int)paramMotionEvent.getY(), 2.0D)) >= this.mRockerBg_R)
      {
        double d = getRad(this.mRockerBg_X, this.mRockerBg_Y, paramMotionEvent.getX(), paramMotionEvent.getY());
        getXY(this.mRockerBg_X, this.mRockerBg_Y, this.mRockerBg_R, d);
        if (this.mRockerChangeListener != null) {
          this.mRockerChangeListener.report(this.mRockerBtn_X - this.mCenterPoint.x, this.mRockerBtn_Y - this.mCenterPoint.y);
        }
      }
    }
    do
    {
      do
      {
        return true;
        this.mRockerBtn_X = ((int)paramMotionEvent.getX());
        this.mRockerBtn_Y = ((int)paramMotionEvent.getY());
        break;
      } while (paramMotionEvent.getAction() != 1);
      this.mRockerBtn_X = this.mCenterPoint.x;
      this.mRockerBtn_Y = this.mCenterPoint.y;
    } while (this.mRockerChangeListener == null);
    this.mRockerChangeListener.report(0.0F, 0.0F);
    return true;
  }
  
  public void setOnUpdataRocketView(OnUpdataRocketView paramOnUpdataRocketView)
  {
    this.onUpdataRocketView = paramOnUpdataRocketView;
  }
  
  public void setRockerChangeListener(RockerChangeListener paramRockerChangeListener)
  {
    this.mRockerChangeListener = paramRockerChangeListener;
  }
  
  public static abstract interface OnUpdataRocketView
  {
    public abstract void UpdataRocketView(float paramFloat1, float paramFloat2);
  }
  
  public static abstract interface RockerChangeListener
  {
    public abstract void report(float paramFloat1, float paramFloat2);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/ViewTools/RockerView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */