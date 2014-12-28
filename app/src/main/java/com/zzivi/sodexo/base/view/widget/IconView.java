package com.zzivi.sodexo.base.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.zzivi.sodexo.R;

/**
 *
 */
public class IconView extends TextView {

	private static Typeface font;

	public IconView(Context context) {
		super(context);
		init(null);
	}

	public IconView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public IconView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(attrs);
	}

	private void init(AttributeSet attributeSet) {
        if (!isInEditMode()) {
            if (font == null) {
                font = Typeface.createFromAsset(getContext().getAssets(), "icomoon.ttf");
            }

            setTypeface(font);

            if (attributeSet != null) {
                TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet,
                        R.styleable.IconView);
                int icon = typedArray.getInt(R.styleable.IconView_icons, -1);
                if (icon != -1) {
                    setIcon(icon);
                }
            }
        }
	}

	public void setIcon(int icon) {
		char value = (char)('\ue600' + icon);
		setText(String.valueOf(value));
	}
}
