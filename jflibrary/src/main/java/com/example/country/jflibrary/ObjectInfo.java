package com.example.country.jflibrary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ArrayAdapter;

import java.util.HashMap;

/**
 * Created by Jeffrey on 24/05/2018.
 */

public class ObjectInfo {
    public String[] objectNames;
    public String[] objectCodes;

    public ObjectInfo(String[] objectNames, String[] objectCodes){
        this.objectNames = objectNames;
        this.objectCodes = objectCodes;
    }

    public String getObjectName(int position){
        if (objectNames.length > 0 && position < objectNames.length){
            return objectNames[position];
        }

        return "";
    }

    public String getObjectCodes(int position){
        if (objectCodes.length > 0 && position < objectNames.length){
            return objectCodes[position];
        }
        return "";
    }
}
