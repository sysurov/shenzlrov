package com.deepinfar.rov;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.VideoView;
import com.deepinfar.adapter.PlayBackList;

public class VideoPlayerActivity
  extends Activity
{
  private VideoView VideoPlayer;
  View main;
  String path;
  
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getLayoutInflater();
    this.main = LayoutInflater.from(this).inflate(2130903044, null);
    this.main.setSystemUiVisibility(2);
    this.main.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        int i = VideoPlayerActivity.this.main.getSystemUiVisibility();
        if (i == 2) {
          VideoPlayerActivity.this.main.setSystemUiVisibility(0);
        }
        do
        {
          return;
          if (i == 0)
          {
            VideoPlayerActivity.this.main.setSystemUiVisibility(2);
            return;
          }
        } while (i != 1);
        VideoPlayerActivity.this.main.setSystemUiVisibility(2);
      }
    });
    setContentView(this.main);
    this.path = getIntent().getStringExtra(PlayBackList.TAG);
    this.VideoPlayer = ((VideoView)findViewById(2131361798));
    this.VideoPlayer.setVideoPath(this.path);
    this.VideoPlayer.start();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/VideoPlayerActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */