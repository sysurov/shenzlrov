package com.deepinfar.rov;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

public class HelpActivity
  extends FragmentActivity
{
  private static final int TAB_INDEX_COUNT = 3;
  private static final int TAB_INDEX_ONE = 0;
  private static final int TAB_INDEX_THREE = 2;
  private static final int TAB_INDEX_TWO = 1;
  public static boolean needHelp = false;
  private Fragment mFragment1 = new Help_1Fragment();
  private Fragment mFragment2 = new Help_2Fragment();
  private Fragment mFragment3 = new Help_3Fragment();
  private ViewPager mViewPager;
  private ViewPagerAdapter mViewPagerAdapter;
  
  private void setUpViewPager()
  {
    this.mViewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
    this.mViewPager = ((ViewPager)findViewById(2131361792));
    this.mViewPager.setAdapter(this.mViewPagerAdapter);
  }
  
  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130903040);
    setUpViewPager();
  }
  
  protected void onDestroy()
  {
    super.onDestroy();
  }
  
  public class ViewPagerAdapter
    extends FragmentPagerAdapter
  {
    public ViewPagerAdapter(FragmentManager paramFragmentManager)
    {
      super();
    }
    
    public int getCount()
    {
      return 3;
    }
    
    public Fragment getItem(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        throw new IllegalStateException("No fragment at position " + paramInt);
      case 0: 
        return HelpActivity.this.mFragment1;
      case 1: 
        return HelpActivity.this.mFragment2;
      }
      return HelpActivity.this.mFragment3;
    }
    
    public CharSequence getPageTitle(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        return null;
      case 0: 
        return "fragment_help1";
      case 1: 
        return "fragment_help2";
      }
      return "fragment_help3";
    }
  }
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/deepinfar/rov/HelpActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */