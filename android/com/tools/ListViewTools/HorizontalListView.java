package com.tools.ListViewTools;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListAdapter;
import android.widget.Scroller;
import java.util.LinkedList;
import java.util.Queue;

public class HorizontalListView
  extends AdapterView<ListAdapter>
{
  protected ListAdapter mAdapter;
  public boolean mAlwaysOverrideTouch = true;
  protected int mCurrentX;
  private boolean mDataChanged = false;
  private DataSetObserver mDataObserver = new DataSetObserver()
  {
    public void onChanged()
    {
      synchronized (HorizontalListView.this)
      {
        HorizontalListView.this.mDataChanged = true;
        HorizontalListView.this.invalidate();
        HorizontalListView.this.requestLayout();
        return;
      }
    }
    
    public void onInvalidated()
    {
      HorizontalListView.this.reset();
      HorizontalListView.this.invalidate();
      HorizontalListView.this.requestLayout();
    }
  };
  private int mDisplayOffset = 0;
  private GestureDetector mGesture;
  private int mLeftViewIndex = -1;
  private int mMaxX = Integer.MAX_VALUE;
  protected int mNextX;
  private GestureDetector.OnGestureListener mOnGesture = new GestureDetector.SimpleOnGestureListener()
  {
    private boolean isEventWithinView(MotionEvent paramAnonymousMotionEvent, View paramAnonymousView)
    {
      Rect localRect = new Rect();
      int[] arrayOfInt = new int[2];
      paramAnonymousView.getLocationOnScreen(arrayOfInt);
      int i = arrayOfInt[0];
      int j = paramAnonymousView.getWidth();
      int k = arrayOfInt[1];
      localRect.set(i, k, i + j, k + paramAnonymousView.getHeight());
      return localRect.contains((int)paramAnonymousMotionEvent.getRawX(), (int)paramAnonymousMotionEvent.getRawY());
    }
    
    public boolean onDown(MotionEvent paramAnonymousMotionEvent)
    {
      return HorizontalListView.this.onDown(paramAnonymousMotionEvent);
    }
    
    public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      return HorizontalListView.this.onFling(paramAnonymousMotionEvent1, paramAnonymousMotionEvent2, paramAnonymousFloat1, paramAnonymousFloat2);
    }
    
    public void onLongPress(MotionEvent paramAnonymousMotionEvent)
    {
      int j = HorizontalListView.this.getChildCount();
      int i = 0;
      for (;;)
      {
        if (i >= j) {}
        View localView;
        do
        {
          return;
          localView = HorizontalListView.this.getChildAt(i);
          if (!isEventWithinView(paramAnonymousMotionEvent, localView)) {
            break;
          }
        } while (HorizontalListView.this.mOnItemLongClicked == null);
        HorizontalListView.this.mOnItemLongClicked.onItemLongClick(HorizontalListView.this, localView, HorizontalListView.this.mLeftViewIndex + 1 + i, HorizontalListView.this.mAdapter.getItemId(HorizontalListView.this.mLeftViewIndex + 1 + i));
        return;
        i += 1;
      }
    }
    
    public boolean onScroll(MotionEvent arg1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      synchronized (HorizontalListView.this)
      {
        paramAnonymousMotionEvent2 = HorizontalListView.this;
        paramAnonymousMotionEvent2.mNextX += (int)paramAnonymousFloat1;
        HorizontalListView.this.requestLayout();
        return true;
      }
    }
    
    public boolean onSingleTapConfirmed(MotionEvent paramAnonymousMotionEvent)
    {
      int i = 0;
      for (;;)
      {
        if (i >= HorizontalListView.this.getChildCount()) {}
        for (;;)
        {
          return true;
          View localView = HorizontalListView.this.getChildAt(i);
          if (!isEventWithinView(paramAnonymousMotionEvent, localView)) {
            break;
          }
          if (HorizontalListView.this.mOnItemClicked != null) {
            HorizontalListView.this.mOnItemClicked.onItemClick(HorizontalListView.this, localView, HorizontalListView.this.mLeftViewIndex + 1 + i, HorizontalListView.this.mAdapter.getItemId(HorizontalListView.this.mLeftViewIndex + 1 + i));
          }
          if (HorizontalListView.this.mOnItemSelected != null) {
            HorizontalListView.this.mOnItemSelected.onItemSelected(HorizontalListView.this, localView, HorizontalListView.this.mLeftViewIndex + 1 + i, HorizontalListView.this.mAdapter.getItemId(HorizontalListView.this.mLeftViewIndex + 1 + i));
          }
        }
        i += 1;
      }
    }
  };
  private AdapterView.OnItemClickListener mOnItemClicked;
  private AdapterView.OnItemLongClickListener mOnItemLongClicked;
  private AdapterView.OnItemSelectedListener mOnItemSelected;
  private Queue<View> mRemovedViewQueue = new LinkedList();
  private int mRightViewIndex = 0;
  protected Scroller mScroller;
  
  public HorizontalListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initView();
  }
  
  private void addAndMeasureChild(View paramView, int paramInt)
  {
    ViewGroup.LayoutParams localLayoutParams2 = paramView.getLayoutParams();
    ViewGroup.LayoutParams localLayoutParams1 = localLayoutParams2;
    if (localLayoutParams2 == null) {
      localLayoutParams1 = new ViewGroup.LayoutParams(-1, -1);
    }
    addViewInLayout(paramView, paramInt, localLayoutParams1, true);
    paramView.measure(View.MeasureSpec.makeMeasureSpec(getWidth(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(getHeight(), Integer.MIN_VALUE));
  }
  
  private void fillList(int paramInt)
  {
    int i = 0;
    View localView = getChildAt(getChildCount() - 1);
    if (localView != null) {
      i = localView.getRight();
    }
    fillListRight(i, paramInt);
    i = 0;
    localView = getChildAt(0);
    if (localView != null) {
      i = localView.getLeft();
    }
    fillListLeft(i, paramInt);
  }
  
  private void fillListLeft(int paramInt1, int paramInt2)
  {
    for (;;)
    {
      if ((paramInt1 + paramInt2 <= 0) || (this.mLeftViewIndex < 0)) {
        return;
      }
      View localView = this.mAdapter.getView(this.mLeftViewIndex, (View)this.mRemovedViewQueue.poll(), this);
      addAndMeasureChild(localView, 0);
      paramInt1 -= localView.getMeasuredWidth();
      this.mLeftViewIndex -= 1;
      this.mDisplayOffset -= localView.getMeasuredWidth();
    }
  }
  
  private void fillListRight(int paramInt1, int paramInt2)
  {
    for (;;)
    {
      if ((paramInt1 + paramInt2 >= getWidth()) || (this.mRightViewIndex >= this.mAdapter.getCount())) {
        return;
      }
      View localView = this.mAdapter.getView(this.mRightViewIndex, (View)this.mRemovedViewQueue.poll(), this);
      addAndMeasureChild(localView, -1);
      paramInt1 += localView.getMeasuredWidth();
      if (this.mRightViewIndex == this.mAdapter.getCount() - 1) {
        this.mMaxX = (this.mCurrentX + paramInt1 - getWidth());
      }
      if (this.mMaxX < 0) {
        this.mMaxX = 0;
      }
      this.mRightViewIndex += 1;
    }
  }
  
  private void initView()
  {
    try
    {
      this.mLeftViewIndex = -1;
      this.mRightViewIndex = 0;
      this.mDisplayOffset = 0;
      this.mCurrentX = 0;
      this.mNextX = 0;
      this.mMaxX = Integer.MAX_VALUE;
      this.mScroller = new Scroller(getContext());
      this.mGesture = new GestureDetector(getContext(), this.mOnGesture);
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  private void positionItems(int paramInt)
  {
    int i;
    if (getChildCount() > 0)
    {
      this.mDisplayOffset += paramInt;
      i = this.mDisplayOffset;
      paramInt = 0;
    }
    for (;;)
    {
      if (paramInt >= getChildCount()) {
        return;
      }
      View localView = getChildAt(paramInt);
      int j = localView.getMeasuredWidth();
      localView.layout(i, 0, i + j, localView.getMeasuredHeight());
      i += localView.getPaddingRight() + j;
      paramInt += 1;
    }
  }
  
  private void removeNonVisibleItems(int paramInt)
  {
    View localView = getChildAt(0);
    if ((localView == null) || (localView.getRight() + paramInt > 0)) {}
    for (localView = getChildAt(getChildCount() - 1);; localView = getChildAt(getChildCount() - 1))
    {
      if ((localView == null) || (localView.getLeft() + paramInt < getWidth()))
      {
        return;
        this.mDisplayOffset += localView.getMeasuredWidth();
        this.mRemovedViewQueue.offer(localView);
        removeViewInLayout(localView);
        this.mLeftViewIndex += 1;
        localView = getChildAt(0);
        break;
      }
      this.mRemovedViewQueue.offer(localView);
      removeViewInLayout(localView);
      this.mRightViewIndex -= 1;
    }
  }
  
  private void reset()
  {
    try
    {
      initView();
      removeAllViewsInLayout();
      requestLayout();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.dispatchTouchEvent(paramMotionEvent) | this.mGesture.onTouchEvent(paramMotionEvent);
  }
  
  public ListAdapter getAdapter()
  {
    return this.mAdapter;
  }
  
  public View getSelectedView()
  {
    return null;
  }
  
  protected boolean onDown(MotionEvent paramMotionEvent)
  {
    this.mScroller.forceFinished(true);
    return true;
  }
  
  protected boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    try
    {
      this.mScroller.fling(this.mNextX, 0, (int)-paramFloat1, 0, 0, this.mMaxX, 0, 0);
      requestLayout();
      return true;
    }
    finally {}
  }
  
  /* Error */
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: iload_2
    //   5: iload_3
    //   6: iload 4
    //   8: iload 5
    //   10: invokespecial 250	android/widget/AdapterView:onLayout	(ZIIII)V
    //   13: aload_0
    //   14: getfield 153	com/tools/ListViewTools/HorizontalListView:mAdapter	Landroid/widget/ListAdapter;
    //   17: astore 6
    //   19: aload 6
    //   21: ifnonnull +6 -> 27
    //   24: aload_0
    //   25: monitorexit
    //   26: return
    //   27: aload_0
    //   28: getfield 64	com/tools/ListViewTools/HorizontalListView:mDataChanged	Z
    //   31: ifeq +26 -> 57
    //   34: aload_0
    //   35: getfield 175	com/tools/ListViewTools/HorizontalListView:mCurrentX	I
    //   38: istore_2
    //   39: aload_0
    //   40: invokespecial 75	com/tools/ListViewTools/HorizontalListView:initView	()V
    //   43: aload_0
    //   44: invokevirtual 218	com/tools/ListViewTools/HorizontalListView:removeAllViewsInLayout	()V
    //   47: aload_0
    //   48: iload_2
    //   49: putfield 177	com/tools/ListViewTools/HorizontalListView:mNextX	I
    //   52: aload_0
    //   53: iconst_0
    //   54: putfield 64	com/tools/ListViewTools/HorizontalListView:mDataChanged	Z
    //   57: aload_0
    //   58: getfield 188	com/tools/ListViewTools/HorizontalListView:mScroller	Landroid/widget/Scroller;
    //   61: invokevirtual 254	android/widget/Scroller:computeScrollOffset	()Z
    //   64: ifeq +14 -> 78
    //   67: aload_0
    //   68: aload_0
    //   69: getfield 188	com/tools/ListViewTools/HorizontalListView:mScroller	Landroid/widget/Scroller;
    //   72: invokevirtual 257	android/widget/Scroller:getCurrX	()I
    //   75: putfield 177	com/tools/ListViewTools/HorizontalListView:mNextX	I
    //   78: aload_0
    //   79: getfield 177	com/tools/ListViewTools/HorizontalListView:mNextX	I
    //   82: ifgt +16 -> 98
    //   85: aload_0
    //   86: iconst_0
    //   87: putfield 177	com/tools/ListViewTools/HorizontalListView:mNextX	I
    //   90: aload_0
    //   91: getfield 188	com/tools/ListViewTools/HorizontalListView:mScroller	Landroid/widget/Scroller;
    //   94: iconst_1
    //   95: invokevirtual 240	android/widget/Scroller:forceFinished	(Z)V
    //   98: aload_0
    //   99: getfield 177	com/tools/ListViewTools/HorizontalListView:mNextX	I
    //   102: aload_0
    //   103: getfield 53	com/tools/ListViewTools/HorizontalListView:mMaxX	I
    //   106: if_icmplt +19 -> 125
    //   109: aload_0
    //   110: aload_0
    //   111: getfield 53	com/tools/ListViewTools/HorizontalListView:mMaxX	I
    //   114: putfield 177	com/tools/ListViewTools/HorizontalListView:mNextX	I
    //   117: aload_0
    //   118: getfield 188	com/tools/ListViewTools/HorizontalListView:mScroller	Landroid/widget/Scroller;
    //   121: iconst_1
    //   122: invokevirtual 240	android/widget/Scroller:forceFinished	(Z)V
    //   125: aload_0
    //   126: getfield 175	com/tools/ListViewTools/HorizontalListView:mCurrentX	I
    //   129: aload_0
    //   130: getfield 177	com/tools/ListViewTools/HorizontalListView:mNextX	I
    //   133: isub
    //   134: istore_2
    //   135: aload_0
    //   136: iload_2
    //   137: invokespecial 259	com/tools/ListViewTools/HorizontalListView:removeNonVisibleItems	(I)V
    //   140: aload_0
    //   141: iload_2
    //   142: invokespecial 261	com/tools/ListViewTools/HorizontalListView:fillList	(I)V
    //   145: aload_0
    //   146: iload_2
    //   147: invokespecial 263	com/tools/ListViewTools/HorizontalListView:positionItems	(I)V
    //   150: aload_0
    //   151: aload_0
    //   152: getfield 177	com/tools/ListViewTools/HorizontalListView:mNextX	I
    //   155: putfield 175	com/tools/ListViewTools/HorizontalListView:mCurrentX	I
    //   158: aload_0
    //   159: getfield 188	com/tools/ListViewTools/HorizontalListView:mScroller	Landroid/widget/Scroller;
    //   162: invokevirtual 266	android/widget/Scroller:isFinished	()Z
    //   165: ifne -141 -> 24
    //   168: aload_0
    //   169: new 11	com/tools/ListViewTools/HorizontalListView$3
    //   172: dup
    //   173: aload_0
    //   174: invokespecial 267	com/tools/ListViewTools/HorizontalListView$3:<init>	(Lcom/tools/ListViewTools/HorizontalListView;)V
    //   177: invokevirtual 271	com/tools/ListViewTools/HorizontalListView:post	(Ljava/lang/Runnable;)Z
    //   180: pop
    //   181: goto -157 -> 24
    //   184: astore 6
    //   186: aload_0
    //   187: monitorexit
    //   188: aload 6
    //   190: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	191	0	this	HorizontalListView
    //   0	191	1	paramBoolean	boolean
    //   0	191	2	paramInt1	int
    //   0	191	3	paramInt2	int
    //   0	191	4	paramInt3	int
    //   0	191	5	paramInt4	int
    //   17	3	6	localListAdapter	ListAdapter
    //   184	5	6	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	19	184	finally
    //   27	57	184	finally
    //   57	78	184	finally
    //   78	98	184	finally
    //   98	125	184	finally
    //   125	181	184	finally
  }
  
  public void scrollTo(int paramInt)
  {
    try
    {
      this.mScroller.startScroll(this.mNextX, 0, paramInt - this.mNextX, 0);
      requestLayout();
      return;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void setAdapter(ListAdapter paramListAdapter)
  {
    if (this.mAdapter != null) {
      this.mAdapter.unregisterDataSetObserver(this.mDataObserver);
    }
    this.mAdapter = paramListAdapter;
    this.mAdapter.registerDataSetObserver(this.mDataObserver);
    reset();
  }
  
  public void setOnItemClickListener(AdapterView.OnItemClickListener paramOnItemClickListener)
  {
    this.mOnItemClicked = paramOnItemClickListener;
  }
  
  public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener paramOnItemLongClickListener)
  {
    this.mOnItemLongClicked = paramOnItemLongClickListener;
  }
  
  public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener paramOnItemSelectedListener)
  {
    this.mOnItemSelected = paramOnItemSelectedListener;
  }
  
  public void setSelection(int paramInt) {}
}


/* Location:              /home/sunny/dex2jar-2.0/classes-dex2jar.jar!/com/tools/ListViewTools/HorizontalListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */