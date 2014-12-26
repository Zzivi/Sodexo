package com.zzivi.sodexo.base.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


import com.zzivi.sodexo.R;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class CustomFontTextView extends TextView {
    private static Map<String, Typeface> fonts = new HashMap<String, Typeface>();

    public CustomFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    public CustomFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomFontTextView(Context context) {
        super(context);
        init(null);
    }

    private void init(AttributeSet attrs) {
        if (!isInEditMode()) {
            if (attrs != null) {
                TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);
                setTypeface(getFont(getContext(), a));
                a.recycle();
            }
        }
    }

    public static Typeface getFont(Context context, TypedArray a) {
        int fontNameValue = a.getInt(R.styleable.CustomFontTextView_font, -1);
        String fontName = getFontName(fontNameValue);

        Typeface font = fonts.get(fontName);
        if (font == null) {
            font = Typeface.createFromAsset(context.getAssets(), fontName);
            fonts.put(fontName, font);
//                    Log.i("FONT", "loaded font '" + fontName + "'");
        }
        return font;
    }

    public static String getFontName(int value) {
        if (value == 1) {
            return "Roboto-Medium.ttf";
        } else if (value == 2) {
            return "Roboto-Bold.ttf";
        } else if (value == 3) {
            return "Roboto-Italic.ttf";
        } else {
            return "Roboto-Regular.ttf";
        }
    }
}
