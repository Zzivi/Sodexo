package com.zzivi.sodexo.base.view.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.zzivi.sodexo.R;


/**
 *
 */
public class IconTextView extends LinearLayout {
    private IconView iconView;
    private CustomFontTextView textView;

    public IconTextView(Context context) {
        super(context);
        init(null);
    }

    public IconTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public IconTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }

    private void init(AttributeSet attributeSet) {
        if (!isInEditMode()) {
            View root = inflate(getContext(), R.layout.widget_icon_text_view, this);

            iconView = (IconView) root.findViewById(R.id.iv_icon_text_icon);
            textView = (CustomFontTextView) root.findViewById(R.id.tv_icon_text_text);

            loadStateFromAttrs(attributeSet);
        }
    }

    private void loadStateFromAttrs(AttributeSet attributeSet) {
        if (attributeSet == null) {
            return; // quick exit
        }

        TypedArray a = null;
        try {
            a = getContext().obtainStyledAttributes(attributeSet, R.styleable.IconTextView);

            int icon = a.getInt(R.styleable.IconTextView_icons, -1);
            int textColor = a.getColor(R.styleable.IconTextView_icontextview_textColor, R.color.dark_gray);
            int iconColor = a.getColor(R.styleable.IconTextView_icontextview_iconColor, R.color.holocolorsapptheme_color);
            String text = a.getString(R.styleable.IconTextView_icontextview_text);

            if (icon != -1) {
                iconView.setIcon(icon);
            }

            textView.setTextColor(textColor);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, a.getDimension(R.styleable.IconTextView_icontextview_textSize, getResources().getDimension(R.dimen.font_size_normal)));
            textView.setTypeface(CustomFontTextView.getFont(getContext(), a));

            iconView.setTextColor(iconColor);
            iconView.setTextSize(TypedValue.COMPLEX_UNIT_PX, a.getDimension(R.styleable.IconTextView_icontextview_iconSize, getResources().getDimension(R.dimen.font_size_normal)));

            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) iconView.getLayoutParams();
            params.rightMargin = (int) a.getDimension(R.styleable.IconTextView_icontextview_iconPadding, 0);

            if (text != null) {
                textView.setText(text);
            }
        } finally {
            if (a != null) {
                a.recycle(); // ensure this is always called
            }
        }
    }

    public void setText(int resId) {
        textView.setText(resId);
    }

    public void setText(CharSequence text) {
        textView.setText(text);
    }

    public void setTextColor(int color) {
        textView.setTextColor(color);
        iconView.setTextColor(color);
    }

    public void setIconColor(int color) {
        iconView.setTextColor(color);
    }

    public void setIcon(int icon) {
        if (icon < 0) {
            iconView.setVisibility(View.INVISIBLE);
        } else {
            iconView.setVisibility(View.VISIBLE);
            iconView.setIcon(icon);
        }
    }
}
