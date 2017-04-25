package com.tools.Permissions;

import android.annotation.SuppressLint;
import android.app.Activity;

public abstract class ActivityRoot
  extends Activity
{
  @SuppressLint({"NewApi"})
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfInt)
  {
    PermissionsApply localPermissionsApply = PermissionsApply.getPermissionsApply();
    if (localPermissionsApply != null)
    {
      localPermissionsApply.onRequestPermissionsResult(this, paramInt, paramArrayOfString, paramArrayOfInt);
      return;
    }
    super.onRequestPermissionsResult(paramInt, paramArrayOfString, paramArrayOfInt);
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/Permissions/ActivityRoot.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */