package com.tools.Image;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import java.io.ByteArrayOutputStream;

public class ImageOperate
{
  public static Drawable BitmapToDrawable(Context paramContext, Bitmap paramBitmap)
  {
    return new BitmapDrawable(paramContext.getResources(), paramBitmap);
  }
  
  public static Bitmap bitmapShadow(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Object localObject1 = new Matrix();
    ((Matrix)localObject1).preScale(1.0F, -1.0F);
    Object localObject2 = Bitmap.createBitmap(paramBitmap, 0, j / 2, i, j / 2, (Matrix)localObject1, false);
    localObject1 = Bitmap.createBitmap(i, j / 2 + j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas((Bitmap)localObject1);
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, null);
    Paint localPaint = new Paint();
    localCanvas.drawRect(0.0F, j, i, j + 4, localPaint);
    localCanvas.drawBitmap((Bitmap)localObject2, 0.0F, j + 4, null);
    localObject2 = new Paint();
    ((Paint)localObject2).setShader(new LinearGradient(0.0F, paramBitmap.getHeight(), 0.0F, ((Bitmap)localObject1).getHeight() + 4, 1895825407, 16777215, Shader.TileMode.CLAMP));
    ((Paint)localObject2).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawRect(0.0F, j, i, ((Bitmap)localObject1).getHeight() + 4, (Paint)localObject2);
    return (Bitmap)localObject1;
  }
  
  public static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1) {}
    for (Object localObject = Bitmap.Config.ARGB_8888;; localObject = Bitmap.Config.RGB_565)
    {
      localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
      Canvas localCanvas = new Canvas((Bitmap)localObject);
      paramDrawable.setBounds(0, 0, i, j);
      paramDrawable.draw(localCanvas);
      return (Bitmap)localObject;
    }
  }
  
  public static byte[] getBitmapByte(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }
  
  public static Bitmap getByteBitmap(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length != 0) {
      return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    }
    return null;
  }
  
  public static Bitmap invertedImage(Bitmap paramBitmap)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Object localObject1 = new Matrix();
    ((Matrix)localObject1).preScale(1.0F, -1.0F);
    Object localObject2 = Bitmap.createBitmap(paramBitmap, 0, j / 2, i, j / 2, (Matrix)localObject1, false);
    localObject1 = Bitmap.createBitmap(i, j / 2 + j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas((Bitmap)localObject1);
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, null);
    Paint localPaint = new Paint();
    localCanvas.drawRect(0.0F, j, i, j + 4, localPaint);
    localCanvas.drawBitmap((Bitmap)localObject2, 0.0F, j + 4, null);
    localObject2 = new Paint();
    ((Paint)localObject2).setShader(new LinearGradient(0.0F, paramBitmap.getHeight(), 0.0F, ((Bitmap)localObject1).getHeight() + 4, 1895825407, 16777215, Shader.TileMode.CLAMP));
    ((Paint)localObject2).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawRect(0.0F, j, i, ((Bitmap)localObject1).getHeight() + 4, (Paint)localObject2);
    return (Bitmap)localObject1;
  }
  
  public static Bitmap resIdConvertBitmap(Context paramContext, int paramInt)
  {
    return BitmapFactory.decodeResource(paramContext.getResources(), paramInt);
  }
  
  public static Drawable resIdConvertDrawable(Context paramContext, int paramInt)
  {
    Bitmap localBitmap = resIdConvertBitmap(paramContext, paramInt);
    return new BitmapDrawable(paramContext.getResources(), localBitmap);
  }
  
  public static Bitmap roundItBitmap(Bitmap paramBitmap, float paramFloat)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Bitmap localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, i, j);
    RectF localRectF = new RectF(localRect);
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-12434878);
    localCanvas.drawRoundRect(localRectF, paramFloat, paramFloat, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }
  
  public static Bitmap zoomBitmap(Bitmap paramBitmap, float paramFloat1, float paramFloat2)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(paramFloat1 / i, paramFloat2 / j);
    return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
  }
  
  public static Drawable zoomDrawable(Drawable paramDrawable, int paramInt1, int paramInt2)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    paramDrawable = drawableToBitmap(paramDrawable);
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(paramInt1 / i, paramInt2 / j);
    return new BitmapDrawable(Bitmap.createBitmap(paramDrawable, 0, 0, i, j, localMatrix, true));
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/Image/ImageOperate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */