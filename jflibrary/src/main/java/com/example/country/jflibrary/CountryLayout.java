package com.example.country.jflibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Jeffrey on 24/05/2018.
 */

public class CountryLayout extends android.support.v7.widget.AppCompatAutoCompleteTextView{
    private Drawable mClearIconDrawable;
    private boolean mClearIconDrawWhenFocused = true;
    private boolean mIsClearIconShown = false;

    public CountryLayout(Context context) {
        super(context);
    }

    public CountryLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CountryLayout(Context context, AttributeSet attrs, int defStyleAttr) {
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

        initSpinner();
    }


    ArrayAdapter<String> adapter;
    public void initSpinner(){
        String[] countryName = getResources().getStringArray(R.array.country_name);
        String[] countryCode = getResources().getStringArray(R.array.country_code);


        HashMap<String[], String[]> countryMap = new HashMap<>();
        countryMap.put(countryName, countryCode);

        adapter = new ArrayAdapter<String>(getContext(),android.R.layout.select_dialog_singlechoice, countryName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setAdapter(adapter);
    }

    private String[] objectNames;
    private String[] objectCodes;
    public void setObjectNames(String[] objectNames){
        this.objectNames = objectNames;
        adapter.clear();
        adapter.addAll(objectNames);
    }

    public void setObjectCodes(String[] objectCodes){
        this.objectCodes = objectCodes;
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


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (hasFocus()) {
            showClearIcon(true);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isClearIconTouched(event)) {
//      setText(null);
//      event.setAction(MotionEvent.ACTION_CANCEL);
//      showClearIcon(true);
            showDropDown();
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

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        showClearIcon(true);
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
    }
}
