package com.deepinfar.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.deepinfar.rov.StartActivity;
import com.deepinfar.rov.VideoPlayerActivity;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import com.tools.file.VideoFiles;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PlayBackList
  extends BaseAdapter
{
  public static String TAG = "Playback_Player";
  public Context context;
  public String[] filePath;
  public Handler mainHandler;
  public ToastClass toastClass;
  public List<VideoFiles> videoFile;
  
  public PlayBackList(Context paramContext, Handler paramHandler, String... paramVarArgs)
  {
    this.context = paramContext;
    this.toastClass = ToastClass.getToastClass(paramContext);
    this.filePath = paramVarArgs;
    this.videoFile = getVideoFile(paramVarArgs);
    this.mainHandler = paramHandler;
  }
  
  public int getCount()
  {
    return this.videoFile.size();
  }
  
  public Object getItem(int paramInt)
  {
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public List<VideoFiles> getVideoFile(String... paramVarArgs)
  {
    if (this.videoFile == null) {
      this.videoFile = new ArrayList();
    }
    Object localObject1 = new ArrayList();
    int j = paramVarArgs.length;
    int i = 0;
    if (i >= j)
    {
      i = 0;
      if (i < ((List)localObject1).size()) {
        break label111;
      }
      i = 0;
    }
    for (;;)
    {
      if (i >= this.videoFile.size())
      {
        return this.videoFile;
        localObject1 = FileDataUtils.getFileDir((List)localObject1, paramVarArgs[i], new String[] { "avi", "mp4", "AVI", "MP4" });
        i += 1;
        break;
        label111:
        Object localObject2 = (File)((List)localObject1).get(i);
        paramVarArgs = new VideoFiles((File)localObject2, false);
        localObject2 = ((File)localObject2).getName();
        j = 0;
        for (;;)
        {
          if (j >= this.videoFile.size()) {}
          while (((VideoFiles)this.videoFile.get(j)).getVideoFile().getName().equals(localObject2))
          {
            this.videoFile.add(paramVarArgs);
            i += 1;
            break;
          }
          j += 1;
        }
      }
      if (((VideoFiles)this.videoFile.get(i)).getVideoFile().getName().contains("Collect_OK_")) {
        ((VideoFiles)this.videoFile.get(i)).setCollect(true);
      }
      i += 1;
    }
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    playBackOnclack localplayBackOnclack = new playBackOnclack(paramInt);
    Object localObject = (LayoutInflater)this.context.getSystemService("layout_inflater");
    ImageView localImageView1;
    ImageView localImageView2;
    if (paramView == null)
    {
      paramView = ((LayoutInflater)localObject).inflate(2130903063, paramViewGroup, false);
      paramViewGroup = (TextView)paramView.findViewById(2131361871);
      localObject = (ImageView)paramView.findViewById(2131361872);
      localImageView1 = (ImageView)paramView.findViewById(2131361873);
      localImageView2 = (ImageView)paramView.findViewById(2131361870);
      String[] arrayOfString = ((VideoFiles)this.videoFile.get(paramInt)).getVideoFile().getName().split("Collect_OK_");
      paramViewGroup.setText(arrayOfString[(arrayOfString.length - 1)]);
      if (!((VideoFiles)this.videoFile.get(paramInt)).getCollect()) {
        break label178;
      }
      localImageView2.setImageResource(2130837513);
    }
    for (;;)
    {
      paramViewGroup.setOnClickListener(localplayBackOnclack);
      ((ImageView)localObject).setOnClickListener(localplayBackOnclack);
      localImageView1.setOnClickListener(localplayBackOnclack);
      localImageView2.setOnClickListener(localplayBackOnclack);
      return paramView;
      break;
      label178:
      localImageView2.setImageResource(2130837512);
    }
  }
  
  class playBackOnclack
    implements View.OnClickListener
  {
    private int position;
    
    playBackOnclack(int paramInt)
    {
      this.position = paramInt;
    }
    
    public void onClick(View paramView)
    {
      File localFile = ((VideoFiles)PlayBackList.this.videoFile.get(this.position)).getVideoFile();
      String str1 = ((VideoFiles)PlayBackList.this.videoFile.get(this.position)).getVideoFile().getPath();
      switch (paramView.getId())
      {
      default: 
        return;
      case 2131361871: 
      case 2131361872: 
        paramView = new Intent();
        paramView.setClass(PlayBackList.this.context, VideoPlayerActivity.class);
        paramView.putExtra(PlayBackList.TAG, str1);
        StartActivity.getStartAvtivity().startActivity(paramView);
        return;
      case 2131361873: 
        if (!((VideoFiles)PlayBackList.this.videoFile.get(this.position)).getCollect())
        {
          if (localFile.delete())
          {
            PlayBackList.this.toastClass.ToastshowString("删除成功");
            PlayBackList.this.videoFile.remove(this.position);
            PlayBackList.this.notifyDataSetChanged();
            return;
          }
          PlayBackList.this.toastClass.ToastshowString("删除失败");
          return;
        }
        PlayBackList.this.toastClass.ToastshowString("文件已被收藏");
        return;
      }
      boolean bool = ((VideoFiles)PlayBackList.this.videoFile.get(this.position)).getCollect();
      String str2 = localFile.getParent();
      str1 = localFile.getName();
      if (bool)
      {
        paramView = str1;
        if (str1.contains("Collect_OK_"))
        {
          paramView = str1.split("Collect_OK_");
          paramView = paramView[(paramView.length - 1)];
        }
        ((VideoFiles)PlayBackList.this.videoFile.get(this.position)).setCollect(false);
      }
      for (;;)
      {
        paramView = new File(str2 + "/" + paramView);
        localFile.renameTo(paramView);
        ((VideoFiles)PlayBackList.this.videoFile.get(this.position)).setVideoFile(paramView);
        PlayBackList.this.notifyDataSetChanged();
        return;
        paramView = "Collect_OK_" + str1;
        ((VideoFiles)PlayBackList.this.videoFile.get(this.position)).setCollect(true);
      }
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/adapter/PlayBackList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */