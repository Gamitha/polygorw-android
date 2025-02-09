package com.orenda.bottom_tab_navigation.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.google.android.material.textview.MaterialTextView;
import com.orenda.bottom_tab_navigation.R;

public class PLText extends MaterialTextView {

    public enum PLTextVariant {
        H1, H2, H3, H4, H5, H6,
        SUBTITLE1, SUBTITLE2,
        BODY1, BODY2;
    }

    public PLText(Context context) {
        super(context);
        init(null);
    }

    public PLText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PLText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs == null) return;

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.PLText);
        int variantValue = a.getInt(R.styleable.PLText_txt_variant, 0);
        a.recycle();

        applyVariant(PLTextVariant.values()[variantValue]);
    }

    public void applyVariant(PLTextVariant variant) {
        switch (variant) {
            case H1:
                throw new UnsupportedOperationException("H1 Not implemented yet");
            case H2:
                throw new UnsupportedOperationException("H2 Not implemented yet");
            case H3:
                setTextSize(22);
                setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                setTypeface(ResourcesCompat.getFont(this.getContext(), R.font.poppins_semibold));
                break;
            case H4:
                throw new UnsupportedOperationException("H4 Not implemented yet");
            case H5:
                throw new UnsupportedOperationException("H5 Not implemented yet");
            case H6:
                throw new UnsupportedOperationException("H6 Not implemented yet");
            case SUBTITLE1:
                throw new UnsupportedOperationException("SUBTITLE1 Not implemented yet");
            case SUBTITLE2:
                throw new UnsupportedOperationException("SUBTITLE2 Not implemented yet");
            case BODY1:
                setTextSize(16);
                setTextColor(ContextCompat.getColor(getContext(), R.color.black));
                setTypeface(ResourcesCompat.getFont(this.getContext(), R.font.poppins_regular));
                break;
            case BODY2:
                throw new UnsupportedOperationException("BODY2 Not implemented yet");
        }
    }
}
