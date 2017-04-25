package com.deepinfar.rov;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ViewSwitcher.ViewFactory;
import com.deepinfar.Dialog.PlaybackDialog;
import com.deepinfar.adapter.PictureList;
import com.tools.Image.ImageOperate;
import com.tools.file.PictureFiles;
import java.util.List;

public class PictureActivity
  extends Activity
{
  private static final String TAG = "GallertTest2";
  List<PictureFiles> Pictures;
  Drawable bitmap;
  private int downX;
  private ImageSwitcher is;
  private int position;
  private int upX;
  
  private void initView()
  {
    this.is = ((ImageSwitcher)findViewById(2131361794));
    this.is.setFactory(new ViewSwitcher.ViewFactory()
    {
      public View makeView()
      {
        ImageView localImageView = new ImageView(PictureActivity.this);
        localImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        localImageView.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        return localImageView;
      }
    });
    updataAnimation(17432576, 17432577);
    this.is.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        boolean bool = false;
        if (paramAnonymousMotionEvent.getAction() == 0)
        {
          PictureActivity.this.downX = ((int)paramAnonymousMotionEvent.getX());
          bool = true;
        }
        while (paramAnonymousMotionEvent.getAction() != 1) {
          return bool;
        }
        PictureActivity.this.upX = ((int)paramAnonymousMotionEvent.getX());
        if (PictureActivity.this.upX - PictureActivity.this.downX > 100) {
          if (PictureActivity.this.position - 1 < 0) {
            PictureActivity.this.position = (PictureActivity.this.Pictures.size() - 1);
          }
        }
        for (;;)
        {
          PictureActivity.this.bitmap = ImageOperate.BitmapToDrawable(PictureActivity.this.getApplicationContext(), ((PictureFiles)PictureActivity.this.Pictures.get(PictureActivity.this.position)).getBitmap());
          PictureActivity.this.is.setImageDrawable(PictureActivity.this.bitmap);
          return true;
          paramAnonymousView = PictureActivity.this;
          paramAnonymousView.position -= 1;
          continue;
          if (PictureActivity.this.downX - PictureActivity.this.upX <= 100) {
            break;
          }
          if ((PictureActivity.this.position + 1 >= PictureActivity.this.Pictures.size()) || (PictureActivity.this.position + 1 < 0))
          {
            PictureActivity.this.position = 0;
          }
          else
          {
            paramAnonymousView = PictureActivity.this;
            paramAnonymousView.position += 1;
          }
        }
        return true;
      }
    });
  }
  
  private void updataAnimation(int paramInt1, int paramInt2)
  {
    this.is.setInAnimation(AnimationUtils.loadAnimation(this, paramInt1));
    this.is.setOutAnimation(AnimationUtils.loadAnimation(this, paramInt2));
  }
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903042);
    paramBundle = getIntent();
    PlaybackDialog localPlaybackDialog = (PlaybackDialog)MainFragment.getMainView().getDialog();
    if (localPlaybackDialog == null) {
      finish();
    }
    this.Pictures = ((PictureList)localPlaybackDialog.getPictureAdapter()).getPictureFile();
    this.position = paramBundle.getIntExtra("Position", 0);
    this.bitmap = ImageOperate.BitmapToDrawable(getApplicationContext(), ((PictureFiles)this.Pictures.get(this.position)).getBitmap());
    initView();
    this.is.setImageDrawable(this.bitmap);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/PictureActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */