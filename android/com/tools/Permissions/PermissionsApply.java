package com.tools.Permissions;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class PermissionsApply
{
  public static PermissionsApply permissionsApply;
  private final int REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 124;
  
  public PermissionsApply()
  {
    permissionsApply = this;
  }
  
  @SuppressLint({"NewApi"})
  private boolean addPermission(Activity paramActivity, List<String> paramList, String paramString)
  {
    if (paramActivity.checkSelfPermission(paramString) != 0)
    {
      paramList.add(paramString);
      if (!paramActivity.shouldShowRequestPermissionRationale(paramString)) {
        return false;
      }
    }
    return true;
  }
  
  public static PermissionsApply getPermissionsApply()
  {
    return permissionsApply;
  }
  
  private void showMessageOKCancel(Activity paramActivity, String paramString, DialogInterface.OnClickListener paramOnClickListener)
  {
    new AlertDialog.Builder(paramActivity).setMessage(paramString).setPositiveButton("OK", paramOnClickListener).setNegativeButton("Cancel", null).create().show();
  }
  
  public abstract void FailureExecute();
  
  @SuppressLint({"NewApi"})
  public void insertDummyContactWrapper(Activity paramActivity, Jurisdictions... paramVarArgs)
  {
    for (;;)
    {
      ArrayList localArrayList2;
      ArrayList localArrayList1;
      int i;
      try
      {
        if (Build.VERSION.SDK_INT < 23)
        {
          succeedExecute();
          return;
        }
        localArrayList2 = new ArrayList();
        localArrayList1 = new ArrayList();
        i = 0;
        if (i >= paramVarArgs.length)
        {
          paramVarArgs = new String[localArrayList1.size()];
          if (paramVarArgs.length <= 0) {
            break label136;
          }
          i = 0;
          if (i < paramVarArgs.length) {
            break label115;
          }
          paramActivity.requestPermissions(paramVarArgs, 124);
          continue;
        }
        if (addPermission(paramActivity, localArrayList1, paramVarArgs[i].getJurisdictionName())) {
          break label143;
        }
      }
      finally {}
      localArrayList2.add(paramVarArgs[i].getTag());
      break label143;
      label115:
      paramVarArgs[i] = ((String)localArrayList1.get(i));
      i += 1;
      continue;
      label136:
      succeedExecute();
      continue;
      label143:
      i += 1;
    }
  }
  
  @SuppressLint({"NewApi"})
  public void onRequestPermissionsResult(Activity paramActivity, int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    switch (paramInt)
    {
    default: 
      paramActivity.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
      return;
    }
    HashMap localHashMap = new HashMap();
    paramInt = 0;
    int i;
    if (paramInt >= paramArrayOfString.length)
    {
      i = 1;
      paramInt = 0;
    }
    for (;;)
    {
      if (paramInt >= localHashMap.size())
      {
        if (i == 0) {
          break label173;
        }
        succeedExecute();
        return;
        localHashMap.put(paramArrayOfString[paramInt], Integer.valueOf(paramArrayOfInt[paramInt]));
        paramInt += 1;
        break;
      }
      int j = ((Integer)localHashMap.get(paramArrayOfString[paramInt])).intValue();
      if (j != 0) {
        i = 0;
      }
      Toast.makeText(paramActivity, paramInt + "    " + paramArrayOfString[paramInt] + "    " + j, 0).show();
      paramInt += 1;
    }
    label173:
    FailureExecute();
  }
  
  public abstract void succeedExecute();
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/Permissions/PermissionsApply.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */