package com.orenda.bottom_tab_navigation.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.orenda.bottom_tab_navigation.R;

public class PLButton extends MaterialButton {
    public enum PLButtonVariant { PRIMARY, SECONDARY, OUTLINED, TEXT_ONLY, ROUNDED }

    public PLButton(Context context) {
        super(context);
        init(null);
    }

    public PLButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PLButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) return;

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PLButton);
        int variantValue = a.getInt(R.styleable.PLButton_btn_variant, 0);  // Default to PRIMARY
        a.recycle();

        applyVariant(PLButtonVariant.values()[variantValue]);
    }

    public void applyVariant(PLButtonVariant variant) {
        switch (variant) {
            case PRIMARY:
                setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green_300));
                setTextColor(R.color.green_900);
                setCornerRadius(Integer.MAX_VALUE);
                break;
            case SECONDARY:
                setBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
                setTextColor(R.color.green_900);
                break;
            case OUTLINED:
                setBackgroundColor(Color.TRANSPARENT);
                setTextColor(ContextCompat.getColor(getContext(), R.color.green_300));
                setStrokeColorResource(R.color.green_300);
                setStrokeWidth(2);
                break;
            case TEXT_ONLY:
                setBackgroundColor(Color.TRANSPARENT);
                setElevation(0);
                setStrokeWidth(0);
                setOutlineProvider(null);
                setTextColor(ContextCompat.getColor(getContext(), R.color.green_900));
                break;
            case ROUNDED:
                setBackgroundColor(ContextCompat.getColor(getContext(), R.color.green_300));
                setTextColor(R.color.green_900);
                setCornerRadius(20);
                break;
        }
    }
}
