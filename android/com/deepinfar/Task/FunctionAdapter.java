package com.deepinfar.Task;

import android.app.Activity;
import android.content.res.Resources;
import com.deepinfar.Task.RovTask.LoadTimerData;
import com.deepinfar.Task.RovTask.OnTimerUpdata;
import com.deepinfar.rov.MainFragment;
import com.tools.Toast.ToastClass;
import com.tools.file.FileDataUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class FunctionAdapter
{
  private static FunctionAdapter functionAdapter;
  public Map<String, Function> Functions;
  private Timer adapterTimer = new Timer();
  private OnDeployTask cameraTask;
  private OnDeployTask onDeployTask;
  private Map<String, OnTimerUpdata> onTimerUpdata = new HashMap();
  private OnDeployTask rockerTask;
  private FunctionOnDeployTask rovTask;
  private ToastClass toastClass;
  
  private FunctionAdapter()
  {
    try
    {
      this.toastClass = ToastClass.getToastClass();
      InitFunctions();
      this.adapterTimer.schedule(new TimerTask()
      {
        public void run()
        {
          int i;
          if (FunctionAdapter.this.onTimerUpdata != null) {
            i = 0;
          }
          for (;;)
          {
            if (i >= LoadTimerData.OnTimerUpdataTag.length) {
              return;
            }
            OnTimerUpdata localOnTimerUpdata = (OnTimerUpdata)FunctionAdapter.this.onTimerUpdata.get(LoadTimerData.OnTimerUpdataTag[i]);
            if (localOnTimerUpdata != null) {
              localOnTimerUpdata.onTimerUpdata();
            }
            i += 1;
          }
        }
      }, 50L, 50L);
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      for (;;)
      {
        localNullPointerException.printStackTrace();
      }
    }
  }
  
  public static FunctionAdapter getFunctionAdapter()
  {
    if (functionAdapter == null) {
      functionAdapter = new FunctionAdapter();
    }
    return functionAdapter;
  }
  
  public static void setFunctionAdapter(FunctionAdapter paramFunctionAdapter)
  {
    functionAdapter = paramFunctionAdapter;
  }
  
  /* Error */
  public boolean FunctionStart(String paramString, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 4
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield 78	com/deepinfar/Task/FunctionAdapter:Functions	Ljava/util/Map;
    //   9: aload_1
    //   10: invokeinterface 84 2 0
    //   15: checkcast 86	com/deepinfar/Task/Function
    //   18: astore 5
    //   20: aload 5
    //   22: ifnonnull +43 -> 65
    //   25: iload_3
    //   26: ifne +39 -> 65
    //   29: aload_0
    //   30: invokevirtual 53	com/deepinfar/Task/FunctionAdapter:InitFunctions	()V
    //   33: aload_0
    //   34: getfield 88	com/deepinfar/Task/FunctionAdapter:onDeployTask	Lcom/deepinfar/Task/FunctionAdapter$OnDeployTask;
    //   37: invokeinterface 91 1 0
    //   42: aload_0
    //   43: getfield 78	com/deepinfar/Task/FunctionAdapter:Functions	Ljava/util/Map;
    //   46: aload_1
    //   47: invokeinterface 84 2 0
    //   52: checkcast 86	com/deepinfar/Task/Function
    //   55: astore_1
    //   56: aload_1
    //   57: ifnonnull +3 -> 60
    //   60: aload_0
    //   61: monitorexit
    //   62: iload 4
    //   64: ireturn
    //   65: aload 5
    //   67: ifnull -7 -> 60
    //   70: aload 5
    //   72: iload_2
    //   73: iload_3
    //   74: invokeinterface 94 3 0
    //   79: istore 4
    //   81: goto -21 -> 60
    //   84: astore_1
    //   85: aload_0
    //   86: monitorexit
    //   87: aload_1
    //   88: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	89	0	this	FunctionAdapter
    //   0	89	1	paramString	String
    //   0	89	2	paramInt1	int
    //   0	89	3	paramInt2	int
    //   1	79	4	bool	boolean
    //   18	53	5	localFunction	Function
    // Exception table:
    //   from	to	target	type
    //   5	20	84	finally
    //   29	56	84	finally
    //   70	81	84	finally
  }
  
  /* Error */
  public boolean FunctionStop(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 78	com/deepinfar/Task/FunctionAdapter:Functions	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 84 2 0
    //   12: checkcast 86	com/deepinfar/Task/Function
    //   15: astore 4
    //   17: aload 4
    //   19: astore_3
    //   20: aload 4
    //   22: ifnonnull +42 -> 64
    //   25: aload_0
    //   26: invokevirtual 53	com/deepinfar/Task/FunctionAdapter:InitFunctions	()V
    //   29: aload_0
    //   30: getfield 88	com/deepinfar/Task/FunctionAdapter:onDeployTask	Lcom/deepinfar/Task/FunctionAdapter$OnDeployTask;
    //   33: invokeinterface 91 1 0
    //   38: aload_0
    //   39: getfield 78	com/deepinfar/Task/FunctionAdapter:Functions	Ljava/util/Map;
    //   42: aload_1
    //   43: invokeinterface 84 2 0
    //   48: checkcast 86	com/deepinfar/Task/Function
    //   51: astore_1
    //   52: aload_1
    //   53: astore_3
    //   54: aload_1
    //   55: ifnonnull +9 -> 64
    //   58: iconst_0
    //   59: istore_2
    //   60: aload_0
    //   61: monitorexit
    //   62: iload_2
    //   63: ireturn
    //   64: aload_3
    //   65: invokeinterface 99 1 0
    //   70: istore_2
    //   71: goto -11 -> 60
    //   74: astore_1
    //   75: aload_0
    //   76: monitorexit
    //   77: aload_1
    //   78: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	79	0	this	FunctionAdapter
    //   0	79	1	paramString	String
    //   59	12	2	bool	boolean
    //   19	46	3	localObject	Object
    //   15	6	4	localFunction	Function
    // Exception table:
    //   from	to	target	type
    //   2	17	74	finally
    //   25	52	74	finally
    //   64	71	74	finally
  }
  
  public void InitFunctions()
  {
    this.Functions = new HashMap();
    localObject1 = null;
    try
    {
      localObject2 = FileDataUtils.getFileDataUtils();
      localObject1 = localObject2;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      for (;;)
      {
        Object localObject2;
        int j;
        localFileNotFoundException.printStackTrace();
      }
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        NullFunction localNullFunction;
        int i;
        localIOException.printStackTrace();
        continue;
        String str = localIOException[i];
        this.Functions.put(((FileDataUtils)localObject1).getTagData(str), localNullFunction);
        i += 1;
      }
    }
    localObject2 = MainFragment.getMainView().getActivity().getResources().getStringArray(2130968581);
    localNullFunction = new NullFunction();
    j = localObject2.length;
    i = 0;
    if (i >= j) {
      return;
    }
  }
  
  public void UpdateFunctions(String paramString, Function paramFunction)
  {
    this.Functions.put(paramString, paramFunction);
  }
  
  public OnDeployTask getCameraTask()
  {
    return this.cameraTask;
  }
  
  public Function getFunctions(String paramString)
  {
    return (Function)this.Functions.get(paramString);
  }
  
  public OnDeployTask getOnDeployTask()
  {
    return this.onDeployTask;
  }
  
  public Map<String, OnTimerUpdata> getOnTimerUpdata()
  {
    return this.onTimerUpdata;
  }
  
  public OnDeployTask getRockerTask()
  {
    return this.rockerTask;
  }
  
  public FunctionOnDeployTask getRovTask()
  {
    return this.rovTask;
  }
  
  public void removeFunctions(String paramString)
  {
    if ((Function)this.Functions.get(paramString) != null) {
      this.Functions.remove(paramString);
    }
  }
  
  public void removeOnTimerUpdata(String paramString)
  {
    this.onTimerUpdata.remove(paramString);
  }
  
  public void setCameraTask(OnDeployTask paramOnDeployTask)
  {
    this.cameraTask = paramOnDeployTask;
    if (paramOnDeployTask != null) {
      paramOnDeployTask.FunctionDeploy();
    }
  }
  
  public void setOnDeployTask(OnDeployTask paramOnDeployTask)
  {
    this.onDeployTask = paramOnDeployTask;
    if (paramOnDeployTask != null) {
      paramOnDeployTask.FunctionDeploy();
    }
  }
  
  public void setOnTimerUpdata(String paramString, OnTimerUpdata paramOnTimerUpdata)
  {
    this.onTimerUpdata.put(paramString, paramOnTimerUpdata);
  }
  
  public void setRockerTask(OnDeployTask paramOnDeployTask)
  {
    this.rockerTask = paramOnDeployTask;
  }
  
  public void setRovTask(FunctionOnDeployTask paramFunctionOnDeployTask)
  {
    this.rovTask = paramFunctionOnDeployTask;
    if (paramFunctionOnDeployTask != null) {
      paramFunctionOnDeployTask.FunctionDeploy();
    }
  }
  
  public static abstract interface OnDeployTask
  {
    public abstract void FunctionDeploy();
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/Task/FunctionAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */