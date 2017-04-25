package com.tools.ViewTools.VerticalSeekBarTools;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.SeekBar;

public class MLVerticalSeekBar
  extends SeekBar
{
  private OnSeekBarChangeListener mOnSeekBarChangeListener;
  
  public MLVerticalSeekBar(Context paramContext)
  {
    super(paramContext);
  }
  
  public MLVerticalSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  public MLVerticalSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }
  
  private void attemptClaimDrag()
  {
    if (getParent() != null) {
      getParent().requestDisallowInterceptTouchEvent(true);
    }
  }
  
  private void trackTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = getHeight();
    int j = getPaddingBottom();
    int k = getPaddingTop();
    int m = (int)paramMotionEvent.getY();
    float f;
    if (m > i - getPaddingBottom()) {
      f = 0.0F;
    }
    for (;;)
    {
      setProgress((int)(f * getMax()));
      return;
      if (m < getPaddingTop()) {
        f = 1.0F;
      } else {
        f = (i - getPaddingBottom() - m) / (i - j - k);
      }
    }
  }
  
  public int getProgress()
  {
    try
    {
      int i = super.getProgress();
      return i;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  protected void onDraw(Canvas paramCanvas)
  {
    try
    {
      paramCanvas.rotate(-90.0F);
      paramCanvas.translate(-getHeight(), 0.0F);
      super.onDraw(paramCanvas);
      return;
    }
    finally
    {
      paramCanvas = finally;
      throw paramCanvas;
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    try
    {
      super.onMeasure(paramInt2, paramInt1);
      setMeasuredDimension(getMeasuredHeight(), getMeasuredWidth() + 100);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  void onProgressRefresh()
  {
    if (this.mOnSeekBarChangeListener != null) {
      this.mOnSeekBarChangeListener.onProgressChanged(this, getProgress(), isPressed());
    }
  }
  
  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt2, paramInt1, paramInt4, paramInt3);
  }
  
  void onStartTrackingTouch()
  {
    if (this.mOnSeekBarChangeListener != null) {
      this.mOnSeekBarChangeListener.onStartTrackingTouch(this);
    }
  }
  
  void onStopTrackingTouch()
  {
    if (this.mOnSeekBarChangeListener != null) {
      this.mOnSeekBarChangeListener.onStopTrackingTouch(this);
    }
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!isEnabled()) {
      return false;
    }
    switch (paramMotionEvent.getAction())
    {
    }
    for (;;)
    {
      return true;
      setPressed(true);
      onStartTrackingTouch();
      trackTouchEvent(paramMotionEvent);
      continue;
      trackTouchEvent(paramMotionEvent);
      onProgressRefresh();
      attemptClaimDrag();
      continue;
      trackTouchEvent(paramMotionEvent);
      onStopTrackingTouch();
      setPressed(false);
      invalidate();
      continue;
      onStopTrackingTouch();
      setPressed(false);
      invalidate();
    }
  }
  
  public void setOnSeekBarChangeListener(OnSeekBarChangeListener paramOnSeekBarChangeListener)
  {
    this.mOnSeekBarChangeListener = paramOnSeekBarChangeListener;
  }
  
  public void setProgress(int paramInt)
  {
    try
    {
      super.setProgress(paramInt);
      onSizeChanged(getWidth(), getHeight(), 0, 0);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public static abstract interface OnSeekBarChangeListener
  {
    public abstract void onProgressChanged(MLVerticalSeekBar paramMLVerticalSeekBar, int paramInt, boolean paramBoolean);
    
    public abstract void onStartTrackingTouch(MLVerticalSeekBar paramMLVerticalSeekBar);
    
    public abstract void onStopTrackingTouch(MLVerticalSeekBar paramMLVerticalSeekBar);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/ViewTools/VerticalSeekBarTools/MLVerticalSeekBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */