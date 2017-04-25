package com.deepinfar.rov;

import android.util.Log;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.SimpleOnScaleGestureListener;

class ScaleListener
  extends ScaleGestureDetector.SimpleOnScaleGestureListener
{
  public static final float MAX_ZOOM = 1.0F;
  public static final float MIN_ZOOM = 0.7F;
  public float scaleFactor = 1.0F;
  public boolean zoom = false;
  
  public boolean onScale(ScaleGestureDetector paramScaleGestureDetector)
  {
    this.scaleFactor *= paramScaleGestureDetector.getScaleFactor();
    this.scaleFactor = Math.max(0.7F, Math.min(this.scaleFactor, 1.0F));
    Log.e("Player", "onScale " + this.scaleFactor);
    return true;
  }
  
  public boolean onScaleBegin(ScaleGestureDetector paramScaleGestureDetector)
  {
    Log.e("Player", "onScaleBegin");
    this.zoom = true;
    return true;
  }
  
  public void onScaleEnd(ScaleGestureDetector paramScaleGestureDetector)
  {
    Log.e("Player", "onScaleEnd");
    this.zoom = false;
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/ScaleListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */