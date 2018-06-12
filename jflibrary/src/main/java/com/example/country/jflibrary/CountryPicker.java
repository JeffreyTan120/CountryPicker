package com.example.country.jflibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

/**
 *
 */
public class CountryPicker extends AppCompatEditText implements TextWatcher {

  private Drawable mClearIconDrawable;

  private boolean mIsClearIconShown = false;

  private boolean mClearIconDrawWhenFocused = true;

  public CountryPicker(Context context) {
    this(context, null);
  }

  public CountryPicker(Context context, AttributeSet attrs) {
    this(context, attrs, android.R.attr.editTextStyle);
  }

  public CountryPicker(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(attrs, defStyleAttr);
  }

  private void init(AttributeSet attrs, int defStyle) {
    // Load attributes
    final TypedArray a =
        getContext().obtainStyledAttributes(attrs, R.styleable.CountryPicker, defStyle, 0);

    if (a.hasValue(R.styleable.CountryPicker_clearIconDrawable)) {
      mClearIconDrawable = a.getDrawable(R.styleable.CountryPicker_clearIconDrawable);

      if (mClearIconDrawable != null) {
        mClearIconDrawable.setCallback(this);
      }
    }

    setMaxLines(1);
    setSingleLine(true);

    mClearIconDrawWhenFocused = a
        .getBoolean(R.styleable.CountryPicker_clearIconDrawWhenFocused, true);

    a.recycle();
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    // no operation
  }

  @Override
  public void afterTextChanged(Editable s) {
  }

  @Override
  public Parcelable onSaveInstanceState() {
    Parcelable superState = super.onSaveInstanceState();
    return mIsClearIconShown ? new ClearIconSavedState(superState, true)
        : superState;
  }

  @Override
  public void onRestoreInstanceState(Parcelable state) {
    if (!(state instanceof ClearIconSavedState)) {
      super.onRestoreInstanceState(state);
      return;
    }
    ClearIconSavedState savedState = (ClearIconSavedState) state;
    super.onRestoreInstanceState(savedState.getSuperState());
    mIsClearIconShown = savedState.isClearIconShown();
    showClearIcon(true);
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
    if (hasFocus()) {
      showClearIcon(true);
    }
  }

  @Override
  protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
    showClearIcon(true);
    super.onFocusChanged(focused, direction, previouslyFocusedRect);
  }

  @SuppressLint("ClickableViewAccessibility")
  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (isClearIconTouched(event)) {
//      setText(null);
//      event.setAction(MotionEvent.ACTION_CANCEL);
//      showClearIcon(true);

      return false;
    }
    return super.onTouchEvent(event);
  }



  private boolean isClearIconTouched(MotionEvent event) {
    if (!mIsClearIconShown) {
      return false;
    }

    final int touchPointX = (int) event.getX();

    final int widthOfView = getWidth();
    final int compoundPaddingRight = getCompoundPaddingRight();

    return touchPointX >= widthOfView - compoundPaddingRight;
  }

  private void showClearIcon(boolean show) {
    if (show) {
      // show icon on the right
      if (mClearIconDrawable != null) {
        mClearIconDrawable.setBounds(0,0,50,50);
        setCompoundDrawablesWithIntrinsicBounds(null, null, mClearIconDrawable, null);
      } else {
        setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.drop_down_arrow, 0);
      }
    } else {
      // remove icon
      setCompoundDrawables(null, null, null, null);
    }
    mIsClearIconShown = show;
  }

  protected static class ClearIconSavedState extends BaseSavedState {

    public static final Creator<ClearIconSavedState> CREATOR =
        new Creator<ClearIconSavedState>() {
          @Override
          public ClearIconSavedState createFromParcel(Parcel source) {
            return new ClearIconSavedState(source);
          }

          @Override
          public ClearIconSavedState[] newArray(int size) {
            return new ClearIconSavedState[size];
          }
        };
    private final boolean mIsClearIconShown;

    private ClearIconSavedState(Parcel source) {
      super(source);
      mIsClearIconShown = source.readByte() != 0;
    }

    ClearIconSavedState(Parcelable superState, boolean isClearIconShown) {
      super(superState);
      mIsClearIconShown = isClearIconShown;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
      super.writeToParcel(out, flags);
      out.writeByte((byte) (mIsClearIconShown ? 1 : 0));
    }

    boolean isClearIconShown() {
      return mIsClearIconShown;
    }
  }
}
