package com.orenda.polygrow.ui.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.orenda.polygrow.R;

import com.squareup.picasso.Picasso;

public class PLImageCardView extends CardView {
    private ImageView imageView;
    private int imageResId;
    private boolean isActive = false;
    private int activeOutlineColor;
    private int outlineWidth;

    public PLImageCardView(Context context) {
        super(context);
        init(context, null);
    }

    public PLImageCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PLImageCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        this.imageView = new ImageView(context);
        this.imageView.setLayoutParams(new LayoutParams(150, 150));
        this.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.addView(this.imageView);
        activeOutlineColor = ContextCompat.getColor(context, R.color.green_300);
        outlineWidth = context.getResources().getDimensionPixelSize(R.dimen.outline_width);
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PLImageCardView);
            try {
                imageResId = typedArray.getResourceId(R.styleable.PLImageCardView_src, 0);
                if (imageResId != 0) {
                    imageView.setImageResource(imageResId);
                }
                int cornerRadius = typedArray.getDimensionPixelSize(R.styleable.PLImageCardView_cardCornerRadius, 0);
                setRadius(cornerRadius);
                isActive = typedArray.getBoolean(R.styleable.PLImageCardView_active, false);
                updateOutline();
            } finally {
                typedArray.recycle();
            }
        }
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onImageCardClickListener != null) {
                    onImageCardClickListener.onImageCardClick(PLImageCardView.this);
                }
            }
        });
    }

    public void setImageResource(int resId) {
        imageView.setImageResource(resId);
    }

    public void setImageResource(String url) {
        Picasso.get().load(url).into(imageView);
    }

    public void setImageDrawable(Drawable drawable) {
        imageView.setImageDrawable(drawable);
    }

    public void setActive(boolean active) {
        isActive = active;
        updateOutline();
    }

    public boolean isActive() {
        return isActive;
    }

    public void setCardCornerRadius(int radius) {
        setRadius(radius);
    }

    private void updateOutline() {
        if (isActive) {
            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.RECTANGLE);
            shape.setCornerRadius(getRadius());
            shape.setStroke(outlineWidth, activeOutlineColor);
            setForeground(shape);
        } else {
            setForeground(null);
        }
    }

    public interface OnImageCardClickListener {
        void onImageCardClick(PLImageCardView imageCardView);
    }

    private OnImageCardClickListener onImageCardClickListener;

    public void setOnImageCardClickListener(OnImageCardClickListener listener) {
        this.onImageCardClickListener = listener;
    }
}