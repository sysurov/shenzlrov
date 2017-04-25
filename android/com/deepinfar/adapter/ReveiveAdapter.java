package com.deepinfar.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.deepinfar.NetWork.NetWork;
import com.deepinfar.NetWork.SendRovData;
import com.tools.Bytetools.ByteUtils;
import java.text.DecimalFormat;

public class ReveiveAdapter
  extends BaseAdapter
{
  public static boolean ViewGone = false;
  private ListUpdataUI ListUpdataUI = new ListUpdataUI()
  {
    public void onListUpdataUI()
    {
      ReveiveAdapter.this.UpdataUIHandler.sendEmptyMessage(1);
    }
  };
  public Handler UpdataUIHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      ReveiveAdapter.this.notifyDataSetChanged();
    }
  };
  public Context context;
  private String[] dataIndex;
  @SuppressLint({"UseSparseArrays"})
  public DataMap[] dataMaps;
  private String[] dataName;
  private String[] dataPrecision;
  private LayoutInflater mInflater;
  public Resources res;
  
  public ReveiveAdapter(Context paramContext)
  {
    this.context = paramContext;
    this.res = paramContext.getResources();
    this.dataName = this.res.getStringArray(2130968583);
    this.dataIndex = this.res.getStringArray(2130968584);
    this.dataPrecision = this.res.getStringArray(2130968585);
    this.dataMaps = new DataMap[this.dataName.length];
    this.mInflater = ((LayoutInflater)paramContext.getSystemService("layout_inflater"));
  }
  
  public static double getDouble(double paramDouble)
  {
    return new Double(new DecimalFormat("0.0").format(paramDouble).toString()).doubleValue();
  }
  
  public int getCount()
  {
    return this.dataName.length;
  }
  
  public Object getItem(int paramInt)
  {
    return null;
  }
  
  public long getItemId(int paramInt)
  {
    return paramInt;
  }
  
  public ListUpdataUI getListUpdataUI()
  {
    return this.ListUpdataUI;
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.mInflater.inflate(2130903064, null);
      paramViewGroup = new DataMap(paramView);
      paramView.setTag(paramViewGroup);
    }
    for (;;)
    {
      updataReveice(paramInt, paramViewGroup);
      return paramView;
      paramViewGroup = (DataMap)paramView.getTag();
    }
  }
  
  public void updataReveice(int paramInt, DataMap paramDataMap)
  {
    if (paramInt == 0)
    {
      paramDataMap.setParameterNameText(this.dataName[paramInt]);
      localObject1 = this.dataIndex[paramInt].split(";");
      if (localObject1.length == 2)
      {
        paramDataMap.setReadDataText(localObject1[0]);
        paramDataMap.setSendDataText(localObject1[1]);
      }
    }
    NetWork localNetWork;
    SendRovData localSendRovData;
    do
    {
      return;
      paramDataMap.setReadDataText("接受数据");
      paramDataMap.setSendDataText("发送数据");
      return;
      paramDataMap.getDataName().setText(this.dataName[paramInt]);
      localNetWork = NetWork.getNetwork();
      localSendRovData = localNetWork.getSendRovData();
      d2 = Double.valueOf(this.dataPrecision[paramInt]).doubleValue();
      localObject1 = this.dataIndex[paramInt].split(";");
    } while (localObject1.length != 2);
    Object localObject5 = localObject1[0];
    Object localObject4 = localObject1[1];
    Object localObject2 = null;
    Object localObject3 = null;
    Object localObject1 = localObject2;
    if (localObject5 != null)
    {
      localObject1 = localObject2;
      if (!((String)localObject5).equals("null")) {
        localObject1 = ((String)localObject5).split("\\,");
      }
    }
    localObject2 = localObject3;
    if (localObject4 != null)
    {
      localObject2 = localObject3;
      if (!((String)localObject4).equals("null")) {
        localObject2 = ((String)localObject4).split("\\,");
      }
    }
    double d1 = d2;
    int i;
    if (localObject1 != null)
    {
      d1 = d2;
      if (localObject1.length <= 2)
      {
        d1 = d2;
        if (localObject1.length > 0)
        {
          i = Integer.parseInt(localObject1[0]);
          if (localObject1.length != 1) {
            break label367;
          }
          d1 = ByteUtils.ToByteIsSignedInt(localNetWork.getmBuf(i));
          label260:
          d2 = Double.valueOf(this.dataPrecision[paramInt]).doubleValue();
          d1 *= d2;
          if (d1 != (int)d1) {
            break label407;
          }
          paramDataMap.setReadDataText(String.valueOf((int)d1));
          d1 = d2;
        }
      }
    }
    label299:
    if ((localObject2 != null) && (localObject2.length <= 2) && (localObject2.length > 0))
    {
      paramInt = Integer.parseInt(localObject2[0]);
      if (localObject2.length != 1) {
        break label424;
      }
    }
    for (double d2 = ByteUtils.ToByteIsSignedInt(localSendRovData.getSendBytes(paramInt));; d2 = ByteUtils.ToBytesIsInt(new byte[] { localSendRovData.getSendBytes(paramInt), localSendRovData.getSendBytes(i) }))
    {
      d1 = d2 * d1;
      if (d1 != (int)d1) {
        break label464;
      }
      paramDataMap.setSendDataText(String.valueOf((int)d1));
      return;
      label367:
      int j = Integer.parseInt(localObject1[1]);
      d1 = ByteUtils.ToBytesIsInt(new byte[] { localNetWork.getmBuf(i), localNetWork.getmBuf(j) });
      break label260;
      label407:
      paramDataMap.setReadDataText(String.valueOf(getDouble(d1)));
      d1 = d2;
      break label299;
      break;
      label424:
      i = Integer.parseInt(localObject2[1]);
    }
    label464:
    paramDataMap.setSendDataText(String.valueOf(getDouble(d1)));
  }
  
  public class DataMap
  {
    private TextView dataName;
    private TextView readData;
    private TextView sendData;
    private View view;
    
    public DataMap(View paramView)
    {
      this.view = paramView;
      initView();
    }
    
    public TextView getDataName()
    {
      return this.dataName;
    }
    
    public void initView()
    {
      this.dataName = ((TextView)this.view.findViewById(2131361874));
      this.readData = ((TextView)this.view.findViewById(2131361875));
      this.sendData = ((TextView)this.view.findViewById(2131361876));
      this.dataName.setText("");
      this.readData.setText("");
      this.sendData.setText("");
    }
    
    public void setParameterNameText(String paramString)
    {
      this.dataName.setText(paramString);
    }
    
    public void setReadDataText(String paramString)
    {
      this.readData.setText(paramString);
    }
    
    public void setSendDataText(String paramString)
    {
      this.sendData.setText(paramString);
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/adapter/ReveiveAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */