package com.tools.ViewTools.Switch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SlideSwitchView
  extends View
{
  private final int MAX_ALPHA = 255;
  private int mAlpha = 255;
  private float mCurrentX = 0.0F;
  private Rect mDest = null;
  private boolean mEnabled = true;
  private boolean mFlag = false;
  private boolean mIsScrolled = false;
  private float mLastX = 0.0F;
  private int mMoveDeltX = 0;
  private int mMoveLength;
  private Paint mPaint = null;
  private Rect mSrc = null;
  private Bitmap mSwitchBottom;
  private Bitmap mSwitchFrame;
  private Bitmap mSwitchMask;
  private boolean mSwitchOn = true;
  private Bitmap mSwitchThumb;
  private Bitmap mSwitchThumbNormal;
  private Bitmap mSwitchThumbPressed;
  private OnSwitchChangedListener switchListener = null;
  
  public SlideSwitchView(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public SlideSwitchView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public SlideSwitchView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }
  
  public void init()
  {
    this.mSwitchThumbPressed = BitmapFactory.decodeResource(getResources(), 2130837508);
    this.mSwitchThumbNormal = BitmapFactory.decodeResource(getResources(), 2130837509);
    this.mSwitchBottom = BitmapFactory.decodeResource(getResources(), 2130837506);
    this.mSwitchFrame = BitmapFactory.decodeResource(getResources(), 2130837510);
    this.mSwitchMask = BitmapFactory.decodeResource(getResources(), 2130837511);
    this.mSwitchThumb = this.mSwitchThumbNormal;
    this.mMoveLength = (this.mSwitchBottom.getWidth() - this.mSwitchFrame.getWidth());
    this.mDest = new Rect(0, 0, this.mSwitchFrame.getWidth(), this.mSwitchFrame.getHeight());
    this.mSrc = new Rect();
    this.mPaint = new Paint();
    this.mPaint.setAntiAlias(true);
    this.mPaint.setAlpha(255);
    this.mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if ((this.mMoveDeltX > 0) || ((this.mMoveDeltX == 0) && (this.mSwitchOn))) {
      if (this.mSrc != null) {
        this.mSrc.set(this.mMoveLength - this.mMoveDeltX, 0, this.mSwitchBottom.getWidth() - this.mMoveDeltX, this.mSwitchFrame.getHeight());
      }
    }
    for (;;)
    {
      paramCanvas.saveLayerAlpha(new RectF(this.mDest), this.mAlpha, 31);
      paramCanvas.drawBitmap(this.mSwitchBottom, this.mSrc, this.mDest, null);
      paramCanvas.drawBitmap(this.mSwitchThumb, this.mSrc, this.mDest, null);
      paramCanvas.drawBitmap(this.mSwitchFrame, 0.0F, 0.0F, null);
      paramCanvas.drawBitmap(this.mSwitchMask, 0.0F, 0.0F, this.mPaint);
      paramCanvas.restore();
      return;
      if (((this.mMoveDeltX < 0) || ((this.mMoveDeltX == 0) && (!this.mSwitchOn))) && (this.mSrc != null)) {
        this.mSrc.set(-this.mMoveDeltX, 0, this.mSwitchFrame.getWidth() - this.mMoveDeltX, this.mSwitchFrame.getHeight());
      }
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(this.mSwitchFrame.getWidth(), this.mSwitchFrame.getHeight());
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!this.mEnabled) {
      return true;
    }
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      invalidate();
      return true;
      this.mSwitchThumb = this.mSwitchThumbPressed;
      this.mLastX = paramMotionEvent.getX();
      continue;
      this.mCurrentX = paramMotionEvent.getX();
      this.mMoveDeltX = ((int)(this.mCurrentX - this.mLastX));
      if (this.mMoveDeltX > 10) {
        this.mIsScrolled = true;
      }
      if (((this.mSwitchOn) && (this.mMoveDeltX < 0)) || ((!this.mSwitchOn) && (this.mMoveDeltX > 0)))
      {
        this.mFlag = true;
        this.mMoveDeltX = 0;
      }
      if (Math.abs(this.mMoveDeltX) > this.mMoveLength) {
        if (this.mMoveDeltX <= 0) {
          break label177;
        }
      }
      label177:
      for (int i = this.mMoveLength;; i = -this.mMoveLength)
      {
        this.mMoveDeltX = i;
        invalidate();
        break;
      }
      this.mSwitchThumb = this.mSwitchThumbNormal;
      label213:
      boolean bool;
      if (!this.mIsScrolled)
      {
        if (this.mSwitchOn)
        {
          i = this.mMoveLength;
          this.mMoveDeltX = i;
          if (!this.mSwitchOn) {
            break label274;
          }
        }
        label274:
        for (bool = false;; bool = true)
        {
          this.mSwitchOn = bool;
          if (this.switchListener != null) {
            this.switchListener.onSwitchChange(this, this.mSwitchOn);
          }
          invalidate();
          this.mMoveDeltX = 0;
          break;
          i = -this.mMoveLength;
          break label213;
        }
      }
      this.mIsScrolled = false;
      if ((Math.abs(this.mMoveDeltX) > 0) && (Math.abs(this.mMoveDeltX) < this.mMoveLength / 2))
      {
        this.mMoveDeltX = 0;
        invalidate();
      }
      else
      {
        if ((Math.abs(this.mMoveDeltX) > this.mMoveLength / 2) && (Math.abs(this.mMoveDeltX) <= this.mMoveLength))
        {
          if (this.mMoveDeltX > 0)
          {
            i = this.mMoveLength;
            label364:
            this.mMoveDeltX = i;
            if (!this.mSwitchOn) {
              break label425;
            }
          }
          label425:
          for (bool = false;; bool = true)
          {
            this.mSwitchOn = bool;
            if (this.switchListener != null) {
              this.switchListener.onSwitchChange(this, this.mSwitchOn);
            }
            invalidate();
            this.mMoveDeltX = 0;
            break;
            i = -this.mMoveLength;
            break label364;
          }
        }
        if ((this.mMoveDeltX == 0) && (this.mFlag))
        {
          this.mMoveDeltX = 0;
          this.mFlag = false;
        }
      }
    }
  }
  
  public void setChecked(boolean paramBoolean)
  {
    this.mSwitchOn = paramBoolean;
    invalidate();
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    this.mEnabled = paramBoolean;
    if (paramBoolean) {}
    for (int i = 255;; i = 127)
    {
      this.mAlpha = i;
      super.setEnabled(paramBoolean);
      invalidate();
      return;
    }
  }
  
  public void setOnChangeListener(OnSwitchChangedListener paramOnSwitchChangedListener)
  {
    this.switchListener = paramOnSwitchChangedListener;
  }
  
  public void setmSwitchBottom(Bitmap paramBitmap)
  {
    this.mSwitchBottom = paramBitmap;
  }
  
  public void toggle()
  {
    if (this.mSwitchOn) {}
    for (boolean bool = false;; bool = true)
    {
      setChecked(bool);
      return;
    }
  }
  
  public static abstract interface OnSwitchChangedListener
  {
    public abstract void onSwitchChange(SlideSwitchView paramSlideSwitchView, boolean paramBoolean);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/ViewTools/Switch/SlideSwitchView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */