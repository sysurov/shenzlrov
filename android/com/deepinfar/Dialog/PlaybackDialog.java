package com.deepinfar.Dialog;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.deepinfar.ProtocolData.RovInitData;
import com.deepinfar.adapter.PictureList;
import com.deepinfar.adapter.PlayBackList;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlaybackDialog
  extends RovDialog
{
  private BaseAdapter PictureAdapter;
  public Button PictureButton;
  private ListView PlayBackListView;
  private BaseAdapter PlaybackAdapter;
  private View PlaybackView;
  public Button VideoButton;
  public Context context;
  private FileDataUtils fileDataUtils;
  private View.OnClickListener onClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      switch (paramAnonymousView.getId())
      {
      default: 
        return;
      case 2131361818: 
        PlaybackDialog.this.VideoButton.setBackgroundColor(65280);
        PlaybackDialog.this.PictureButton.setBackgroundColor(-1);
        PlaybackDialog.this.PlayBackListView.setAdapter(PlaybackDialog.this.PlaybackAdapter);
        return;
      }
      PlaybackDialog.this.VideoButton.setBackgroundColor(-1);
      PlaybackDialog.this.PictureButton.setBackgroundColor(65280);
      PlaybackDialog.this.PlayBackListView.setAdapter(PlaybackDialog.this.PictureAdapter);
    }
  };
  private ToastClass toastClass;
  
  public static final RovDialog newInstance(Context paramContext, Handler paramHandler, FragmentManager paramFragmentManager, String paramString)
  {
    PlaybackDialog localPlaybackDialog = new PlaybackDialog();
    RovDialog.newInstance(localPlaybackDialog, paramHandler, paramFragmentManager, paramString);
    localPlaybackDialog.context = paramContext;
    try
    {
      localPlaybackDialog.toastClass = ToastClass.getToastClass();
      return localPlaybackDialog;
    }
    catch (NullPointerException paramContext)
    {
      localPlaybackDialog.toastClass = ToastClass.getToastClass(localPlaybackDialog.context);
    }
    return localPlaybackDialog;
  }
  
  public BaseAdapter getPictureAdapter()
  {
    return this.PictureAdapter;
  }
  
  public View getPlaybackView()
  {
    return this.PlaybackView;
  }
  
  public View init(View paramView)
  {
    this.PlayBackListView = ((ListView)paramView.findViewById(2131361820));
    this.VideoButton = ((Button)paramView.findViewById(2131361818));
    this.PictureButton = ((Button)paramView.findViewById(2131361819));
    try
    {
      this.fileDataUtils = FileDataUtils.getFileDataUtils();
      String str1 = this.fileDataUtils.getSDPath() + RovInitData.VideoFile_PATH;
      String str2 = this.fileDataUtils.getSDPath() + RovInitData.PhotoFile_PATH;
      this.PlaybackAdapter = new PlayBackList(getActivity(), this.mainHandler, new String[] { str1 });
      this.PictureAdapter = new PictureList(getActivity(), this.mainHandler, new String[] { str2 });
      this.PlayBackListView.setAdapter(this.PlaybackAdapter);
      this.PlayBackListView.setOverScrollMode(2);
      this.VideoButton.setOnClickListener(this.onClickListener);
      this.PictureButton.setOnClickListener(this.onClickListener);
      return super.init(paramView);
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      this.toastClass.ToastshowString("文件打开异常，找不到文件");
      return super.init(paramView);
    }
    catch (IOException localIOException)
    {
      this.toastClass.ToastshowString("文件打开错误，请重启本程序");
    }
    return super.init(paramView);
  }
  
  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    getDialog().requestWindowFeature(1);
    paramLayoutInflater = paramLayoutInflater.inflate(2130903048, null);
    init(paramLayoutInflater);
    this.PlaybackView = paramLayoutInflater;
    return paramLayoutInflater;
  }
  
  public void onResume()
  {
    super.onResume();
    getDialog().getWindow().setLayout(1300, 800);
    if ((this.tag == null) || (this.tag == "null")) {
      dismiss();
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Dialog/PlaybackDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */